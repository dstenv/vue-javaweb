<template>
    <div class="orderdetail">
      <!--      顶部导航-->
      <van-nav-bar
        title="订单详情"
        left-text="返回"
        left-arrow
        @click-left="comeback"
      />
      <div class="orderdetail-item" v-for="(item,index) in order_detail" :key="index">
        <van-radio :name="item.gid" v-model="checked" @click="getRadiogid(item.gid)"></van-radio>
        <div class="orderdetail-img">
            <img :src="imgurl + item.gcoverpic" alt="">
        </div>
        <div class="orderdetail-info">
          <div class="orderdetail-name">
            物品名称：{{item.gname}}
          </div>
          <div class="orderdetail-infomation">
            物品介绍：{{item.ginformation}}
          </div>
          <div class="orderdetail-price">
            物品价格：{{item.gprice}}
          </div>
        </div>
        <van-button round type="primary" class="order-recieve" @click="recievegood(item.gstatus)">收货</van-button>
      </div>

    </div>
</template>

<script>
import "../assets/Css/orderdetail.css"
import axios from "axios";
import {Toast} from "vant";

export default {
  name: "OrderDetailView",
  data(){
      return{
        //当前登录状态下用户信息
        loginuser:{
          uid:"",
          username:""
        },

        //传入后台的订单id
        orderid:{
          oid: ""
        },

        //传入后台收货处理的对象
        sendorder:{
          gid:"",
          oid: "",
          payid:""
        },

        //订单信息
        orderinfo:{},
        //接收后台的物品详情信息
        order_detail:[],

        //图片路径 利用绝对路径访问 public默认/
        imgurl: "/dataImg/",

        checked:false,

      }
    },
    created() {
      //获得登录的用户信息
      this.loginuser.uid = this.$store.getters.getuserid;
      this.loginuser.username = this.$store.getters.getusername;
      this.orderid.oid = this.$route.query.oid;
      this.sendorder.oid = this.$route.query.oid;
      console.log(this.$route.query.oid);
      this.getdetails();
    },
    methods:{
      comeback(){
        this.$router.go(-1);
      },

      //获得当前选中的商品id
      getRadiogid(gid){
        this.sendorder.gid = gid;
        console.log(this.sendorder.gid);
        for (let i = 0; i < this.order_detail.length; i++){
          let o = {};
          o = this.order_detail[i];
          console.log(o);
          if (o.gid == this.sendorder.gid){
            this.sendorder.payid = o.payuserid;
          }
        }
        console.log(this.sendorder.payid);
      },
      getdetails(){
        this.$toast.loading({
            // 吐司的文本
            message: "加载中...",
            // 禁止背景点击
            forbidClick: true,
            // 展示时长
            duration: 0
          })
        axios({
          url:"orderServlet?action=selectOrderInfo",
          method:"POST",
          data:this.orderid
        }).then(res =>{
          this.$toast.clear();
          this.order_detail = res.data.data;
        }).catch(err =>{
          console.log(err);
        })
      },

      recievegood(status){
        if (status == 1){
          Toast.fail("未发货，无法购买")
        }
        else if (status == 3){
          Toast.fail("已收货，无法重复收货")
        }
        else {
          this.$toast.loading({
            // 吐司的文本
            message: "加载中...",
            // 禁止背景点击
            forbidClick: true,
            // 展示时长
            duration: 0
          })
          axios({
            url:"orderServlet?action=recievegood",
            method:"POST",
            data:this.sendorder
          }).then(res =>{
            this.$toast.clear();
            if (res.data.code == 708){
              Toast.success("收货成功")
            }else {
              Toast.fail("收货失败")
            }
            console.log(res);
          }).catch(err =>{
            this.$toast.clear();
            console.log(err);
          })
        }

      }
    }
}
</script>

<style scoped>

</style>