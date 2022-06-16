<template>
    <div class="senddata">
<!--      顶部导航-->
        <div class="head">
          <van-search placeholder="输入商品名称" shape="round" class="search"/>
        </div>
      <van-empty image-size="360" description="您暂时还没有发布商品！" v-show="empty"/>

<!--      页面数据-->
        <div class="gooddata" v-show="!empty">
            <div class="good" v-for="(item,index) in data" :key="index">
              <div class="box">
                <van-radio :name="item.gid" v-model="checked" label-disabled @click="getRadiogid(item.gid)"></van-radio>
                <div class="Infobox" @click="gosendgood(item.gid)">
                    <img :src="imgurl + item.gcoverpic + ''" alt="">
                    <div class="Infobox-name">
                      <div class="product-name">{{ item.gname }}</div>
                    </div>
                </div>
                  <div class="Infobox-price">
                    <div class="product-price">￥{{ item.gprice }}</div>
                  </div>
              </div>

            </div>
        </div>
      <div style="position: absolute;bottom:80px;">
        <van-button round type="primary" class="send-deletegood" @click="deletegood">删除</van-button>
      </div>


    </div>
</template>

<script>
import "../assets/Css/senddata.css"
import axios from "axios";
import {Toast} from "vant";

export default {
  name: "SendDataView",
  data(){
    return{
      user:{
        username:"",
        uid:"",
      },
      //用户的发布物品的数据
      data:[],

      //传入后台的购物车信息
      senddata:{
        uid:"",
        gid:""
      },

      shuzu:[],

      //图片路径 利用绝对路径访问 public默认/
      imgurl: "/dataImg/",

      checked:false,

      empty:false,
    }
  },
  created() {
    this.user.uid = this.$store.getters.getuserid;
    this.user.username = this.$store.getters.getusername;
    this.senddata.uid = this.user.uid;

    //获取用户的发布物品的数据
    this.getData();
  },
  methods:{
    getData(){
      this.$toast.loading({
        // 吐司的文本
        message: "加载中...",
        // 禁止背景点击
        forbidClick: true,
        // 展示时长
        duration: 0
      })

      axios({
        url:"goodServlet?action=selectgoodsByuserid",
        method:"POST",
        data:this.user
      }).then(res =>{
        this.$toast.clear();
        this.data = res.data.data;
        if (this.data == null){
          this.empty = !this.empty;
        }
        console.log(res);
      }).catch(err =>{
        this.$toast.clear();
        console.log(err);
      })
    },

    //获得当前选中的单选框的商品id
    getRadiogid(gid){
      this.senddata.gid = gid;
      console.log(this.senddata.gid);
    },

    //删除发布的物品
    deletegood(){
      this.$toast.loading({
          // 吐司的文本
          message: "加载中...",
          // 禁止背景点击
          forbidClick: true,
          // 展示时长
          duration: 0
        })
      axios({
        url:"goodServlet?action=deletegood",
        method:"POST",
        data:this.senddata
      }).then(res =>{
        this.$toast.clear();
        this.data = res.data.data;
        if (res.data.code == 502){
            Toast.fail(res.data.msg)
        }
        console.log(res);
        this.checked = !this.checked;
      }).catch(err =>{
        this.$toast.clear();
        console.log(err);
      })
    },
    gosendgood(gid){
      this.$router.push({path:"/main/senddata/sendgood",query:{gid}})
    }

  }
}
</script>

<style scoped>

</style>