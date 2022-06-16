<template xmlns="http://www.w3.org/1999/html">
    <div class="gooddetail">
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
            <p style="font-size:18px;font-family: '华文楷体';">发布人:</p>
            <div style="font-size:16px;">{{ userInfo.username }}</div>
          </div>
        </div>

        <div class="control">
          <van-tabbar style="width:135px;height:60px;margin-left:5px;">
            <van-tabbar-item name="like" icon="thumb-circle-o" @click="increase">喜欢</van-tabbar-item>
            <van-tabbar-item name="dislike" icon="close" @click="reduce">不喜欢</van-tabbar-item>
            <van-tabbar-item name="shoppag" icon="shopping-cart-o" @click="gocar">购物车</van-tabbar-item>
          </van-tabbar>
          <van-button round type="primary" class="car-button" @click="addtocar" :disabled="isshow">加入购物车</van-button>
          <van-button round type="primary" class="buy-button" :disabled="isshow" @click="goeditorder(goodInfo.gid)">购买</van-button>
        </div>

      </div>


    </div>

</template>

<script>
import "../assets/Css/gooddetail.css"
import axios from "axios";
import {Toast} from "vant";

export default {
  name: "GoodDetailView",
  data(){
    return{
      //商品信息
      goodInfo:{
        gid:0,
      },

      //当前登录状态下用户信息
      loginuser:{
        uid:"",
        username:""
      },

      //传入后台的购物车信息
      carInfo:{
        uid:"",
        gid:""
      },

      //发布人信息
      userInfo:{},
      //商品详情数据
      gooddetail:{},


      //图片路径 利用绝对路径访问 public默认/
      imgurl: "/dataImg/",

      isshow:false,
    }
  },
  created() {
    //获得商品id
    this.goodInfo.gid = this.$route.query.gid;
    console.log(this.goodInfo.gid);
    //获得登录的用户信息
    this.loginuser.uid = this.$store.getters.getuserid;
    this.loginuser.username = this.$store.getters.getusername;

    //获得添加购物车的信息传入后台
    this.carInfo.uid = this.loginuser.uid;
    this.carInfo.gid = this.goodInfo.gid;

    //获得商品详情数据
    this.getgooddetail();

  },
  methods:{
    comeback(){
      this.$router.go(-1)
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
        data:this.goodInfo
      }).then(res =>{
        this.$toast.clear();
        //console.log(res);
        this.gooddetail = res.data[0];
        this.userInfo = res.data[1];
        if (this.gooddetail.userid == this.loginuser.uid){
          this.isshow = true;
        };


        console.log(res);
      }).catch(err =>{
        console.log(err);
      })
    },

    //增加商品热度
    increase(){
      this.$toast.loading({
        // 吐司的文本
        message: "加载中...",
        // 禁止背景点击
        forbidClick: true,
        // 展示时长
        duration: 0
      })
      axios({
        url:"goodServlet?action=increasehot",
        method:"POST",
        data:this.goodInfo
      }).then(res => {
        this.$toast.clear();
        console.log(res);
        this.getgooddetail();
      }).catch(err =>{
        console.log(err);
      })
    },

    //降低商品热度
    reduce(){
      this.$toast.loading({
        // 吐司的文本
        message: "加载中...",
        // 禁止背景点击
        forbidClick: true,
        // 展示时长
        duration: 0
      })
      axios({
        url:"goodServlet?action=reducehot",
        method:"POST",
        data:this.goodInfo
      }).then(res => {
        this.$toast.clear();
        console.log(res);
        this.getgooddetail();
      }).catch(err =>{
        console.log(err);
      })
    },

    //添加数据到购物车
    addtocar(){
      this.$toast.loading({
        // 吐司的文本
        message: "加载中...",
        // 禁止背景点击
        forbidClick: true,
        // 展示时长
        duration: 0
      })
      axios({
        url:"carServlet?action=savegoodTocar",
        method:"POST",
        data:this.carInfo
      }).then(res => {
        this.$toast.clear();
        console.log(res);
        this.getgooddetail();
        Toast.success("添加成功");
      }).catch(err =>{
        console.log(err);
      })
    },

    //跳转到购物车页面
    gocar(){
      this.$router.push("/main/shop");
    },

    goeditorder(gid){
      this.$router.push({path:"/editorder",query:{gid}});
    }
  }
}
</script>

<style scoped>

</style>