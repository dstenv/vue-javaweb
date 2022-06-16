<template>
<!--  在展示信息的时候还需要显示一个商品状态 (已完成)-->
    <div class="send-good">
      <!--      顶部导航-->
      <van-nav-bar
        title="商品详情"
        left-text="返回"
        left-arrow
        @click-left="comeback"
      />

      <div class="detaildata">
        <!--        轮播图-->
        <div class="detailbanner-box">
          <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item class="swipe-item">
              <img :src="imgurl + gooddetail.gpic" alt="">
            </van-swipe-item>
            <van-swipe-item class="swipe-item">
              <img :src="imgurl + gooddetail.gpic2" alt="">
            </van-swipe-item>
            <van-swipe-item class="swipe-item">
              <img :src="imgurl + gooddetail.gpic3" alt="">
            </van-swipe-item>
          </van-swipe>
        </div>

        <!--        商品详细数据(可以做个动画 向下弹出 暂时没有时间搞)-->
        <div class="detail">
          <div class="detail-head">
            <div class="detail-name">{{gooddetail.gname}}</div>
            <div class="detail-price">￥{{gooddetail.gprice}}</div>
          </div>
          <div class="hot">
            <p style="font-size:18px;font-family: '华文楷体';">热度值:</p>
            <div class="detail-hot">{{gooddetail.hot}}</div>
          </div>
          <div class="Infomation">
            <p style="font-size:18px;font-family: '华文楷体';margin-top:30px;width:85px;">商品介绍:</p>
            <van-cell-group inset style="width:65%;">
              <van-field
                v-model="gooddetail.ginformation"
                rows="2"
                size="large"
                type="textarea"
                maxlength="50"
                readonly
                placeholder="请输入留言"
                style="padding:5px 8px;"
                show-word-limit
              />
            </van-cell-group>
            <!--            <div class="detail-infomation">{{gooddetail.ginformation}}</div>-->
          </div>
          <div class="user">
            <p style="font-size:18px;font-family: '华文楷体';">状态:</p>
            <div style="font-size:16px;">{{status}}</div>
            <p style="font-size:18px;font-family: '华文楷体';">支付人:</p>
            <div style="font-size:16px;">{{payuser.username? payuser.username:"无"}}</div>
          </div>
        </div>

        <div class="control">
          <van-button round type="primary" class="car-button" @click="delivergood">发货</van-button>
          <van-button round type="primary" class="buy-button" @click="goeditgood(gooddetail.gid)">修改</van-button>
        </div>

      </div>
    </div>
</template>

<script>
import axios from "axios";
import "../assets/Css/sendgood.css"
import {Toast} from "vant";


export default {
  name: "SendGoodView",
  data(){
    return{
        goodinfo:{
          gid:0,
        },

      //支付人信息
      payuser:{},

      //支付人id 用于传给后台找支付人信息
      payid:{
        userid:0,
      },

      //传给后台的发货数据
      delivergoodinfo:{
          uid:0,
          gid:0,
          payid:0,
      },

      //当前登录状态下用户信息
      loginuser:{
        userid:"",
        username:""
      },

      //商品详情数据
      gooddetail:{},
      //商品状态
      status:"售卖中...",

      //图片路径 利用绝对路径访问 public默认/
      imgurl: "/dataImg/",
    }
  },
  created() {
      //获得登录的用户信息
      this.loginuser.userid = this.$store.getters.getuserid;
      this.loginuser.username = this.$store.getters.getusername;

        this.goodinfo.gid = this.$route.query.gid;
        console.log(this.goodinfo.gid);


      this.getgooddetail();
  },
  methods:{
      comeback(){
        this.$router.go(-1);
      },
    getgooddetail(){
      this.$toast.loading({
        // 吐司的文本
        message: "加载中...",
        // 禁止背景点击
        forbidClick: true,
        // 展示时长
        duration: 0
      })
      axios({
        url:"goodServlet?action=selectgoodDetil",
        method:"POST",
        data:this.goodinfo
      }).then(res =>{
        this.$toast.clear();
        //console.log(res);
        this.gooddetail = res.data[0];
        this.userInfo = res.data[1];
        this.payid.userid = this.gooddetail.payuserid;
        if (this.gooddetail.gstatus == 1 ){
          this.status = "已被购买";
        }
        if (this.gooddetail.gstatus == 2 ){
          this.status = "已发货";
        }
        if (this.gooddetail.gstatus == 3 ){
          this.status = "已收货";
        }
        console.log(this.gooddetail);

        console.log(this.payid.userid);
        this.getpayuser();
        //console.log( this.userInfo);
      }).catch(err =>{
        console.log(err);
      })
    },

    getpayuser(){
      this.$toast.loading({
        // 吐司的文本
        message: "加载中...",
        // 禁止背景点击
        forbidClick: true,
        // 展示时长
        duration: 0
      })
      axios({
        url:"userservlet?action=selectuser",
        method:"POST",
        data:this.payid
      }).then(res =>{
        this.$toast.clear();
        console.log(res);
        this.payuser = res.data.data;
        console.log(this.payuser);
      }).catch(err =>{
        console.log(err);
      })
    },

    //发货
    delivergood(){
        this.delivergoodinfo.gid = this.gooddetail.gid;
        this.delivergoodinfo.uid = this.loginuser.userid;
        this.delivergoodinfo.payid = this.gooddetail.payuserid;
        this.$toast.loading({
            // 吐司的文本
            message: "加载中...",
            // 禁止背景点击
            forbidClick: true,
            // 展示时长
            duration: 0
          })
      axios({
        url:"orderServlet?action=sendgood",
        method:"POST",
        data:this.delivergoodinfo
      }).then(res =>{
        this.$toast.clear();
        this.getpayuser();
        Toast.success("发货成功");
        console.log(res);
        this.payuser = res.data;
        console.log(this.payuser);
      }).catch(err =>{
        console.log(err);
      })
    },

    goeditgood(gid){
        this.$router.push({path:"/main/home/sendgood/editgood",query:{gid}});
    }

  }
}
</script>

<style scoped>

</style>