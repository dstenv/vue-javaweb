<template>
    <div class="shop">
      <!--      顶部导航-->
      <div class="car-nav">
      <van-search placeholder="输入商品名称" shape="round" class="car-search"/>
      </div>
      <div class="head-control">
        <van-button round type="primary" class="delete-one" @click="deleteone">删除</van-button>
        <van-button round type="primary" class="delete-all" @click="deleteall">清空购物车</van-button>
      </div>
      <van-empty image-size="340" description="您的购物车中没有商品！" v-show="empty"/>
<!--      页面数据-->
      <div class="car-item" v-show="!empty">
        <div class="car-data" v-for="(item,index) in car_goods" :key="index" >
              <div class="data-box">
                <van-radio :name="item.gid" v-model="checked" @click="getRadiogid(item.gid)"></van-radio>
                  <div class="pic_name">
                      <img :src="imgurl + item.gcoverpic" alt="">
                      <div class="name">
                          <div>{{item.gname}}</div>
                      </div>
                  </div>
                  <div class="data-box-price">
                      <div>￥{{item.gprice}}</div>
                  </div>
              </div>
          </div>
      </div>

      <div class="car-control">
<!--        <van-button round type="primary" class="car-buy">全选</van-button>-->
        <van-button round type="primary" class="car-selectall" @click="goeditorder(cardata.gid)">购买</van-button>
      </div>

    </div>
</template>

<script>
import axios from "axios";
import "../assets/Css/shop.css"

export default {
  name: "ShopView",
  data(){
    return{
      //当前登录状态下的用户信息
      user:{
        uid:0,
        username:""
      },

      //购物车商品数据
      car_goods:[],

      //传入后台的购物车信息
      cardata:{
        uid:"",
        gid:""
      },

      //图片路径 利用绝对路径访问 public默认/
      imgurl: "/dataImg/",

      checked:false,
      RadioIndex:0,


      empty:false,
    }
  },
  created() {
    this.user.uid = this.$store.getters.getuserid;
    this.user.username = this.$store.getters.getusername;
    this.cardata.uid = this.user.uid;

    //获取用户的购物车数据
    this.getcarItem();
  },
  methods:{
    getcarItem(){
      this.$toast.loading({
          // 吐司的文本
          message: "加载中...",
          // 禁止背景点击
          forbidClick: true,
          // 展示时长
          duration: 0
        })
      axios({
        url:"carServlet?action=selectGoods",
        method:"POST",
        data:this.user
      }).then(res => {
        this.$toast.clear();
        this.car_goods = res.data.data;
        if (this.car_goods == null){
            this.empty = !this.empty;
        }
        console.log(this.car_goods);
      }).catch(err => {
        console.log(err);
      })
    },


    //获得当前选中的商品id
    getRadiogid(gid){
      this.cardata.gid = gid;
      console.log(this.cardata.gid);
    },


    //删除单个商品数据
    deleteone(){
      this.$toast.loading({
          // 吐司的文本
          message: "加载中...",
          // 禁止背景点击
          forbidClick: true,
          // 展示时长
          duration: 0
        })
      axios({
        url: "carServlet?action=delete",
        method: "POST",
        data:this.cardata
      }).then(res =>{
        this.$toast.clear();
        console.log(res);
        this.getcarItem();
      }).catch(err =>{
        console.log(err);
      })
    },
    //删除全部
    deleteall(){
      this.$toast.loading({
        // 吐司的文本
        message: "加载中...",
        // 禁止背景点击
        forbidClick: true,
        // 展示时长
        duration: 0
      })
      axios({
        url: "carServlet?action=deleteall",
        method: "POST",
        data:this.cardata
      }).then(res =>{
        this.$toast.clear();
        console.log(res);
        this.getcarItem();
      }).catch(err =>{
        console.log(err);
      })
    },

    //跳转到订单编辑页面
    goeditorder(gid){
        this.$router.push({path:"/editorder",query:{gid}});
    }

  }
}
</script>

<style scoped>

</style>