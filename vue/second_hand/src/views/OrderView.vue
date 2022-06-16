<template>
    <div class="order">
      <!--      顶部导航-->
      <van-nav-bar
        title="我的订单"
        left-text="返回"
        left-arrow
        @click-left="comeback"
      />
      <van-empty image-size="200" description="您还没有购买过商品！" v-show="empty"/>

      <div class="order-item" v-show="!empty">
          <div class="orders" v-for="(item,index) in orderinfo" :key="index" @click="goorderdetail(item.orderid)">
              <div class="order_info">
              <div class="order_id">
                <p>订单号：</p>
                <div class="id">
                    {{item.orderid}}
                </div>
              </div>
              <div class="order_address">
                  <p>配送地址：</p>
                  <div class="address">
                    {{item.oaddress}}
                  </div>
              </div>
              <div class="order_status">
                <p>订单状态：</p>
                <div class="status">
                  {{item.ostatus? '已发货':'未发货'}}
                </div>
              </div>
              </div>
              <div class="price">
                  ￥{{item.price}}
              </div>
          </div>
      </div>
    </div>
</template>

<script>
import "../assets/Css/order.css"
import axios from "axios";

export default {
  name: "OrderView",
  data(){
    return{
        loginuser:{
          uid:"",
          username:"",
        },

        empty:false,

        //订单信息
        orderinfo:[],
        //订单状态 0未发货 1已发货 2已收货
        orderitemstatus:"未发货",
        //订单id和对应的商品id
        orderandgood:[],
        //商品信息
        goodinfo:[],

    }
  },
  created() {
    this.loginuser.uid = this.$store.getters.getuserid;
    this.loginuser.username = this.$store.getters.getusername;

    //获取订单信息
    this.getorder();
  },
  methods:{
    comeback(){
      this.$router.go(-1)
    },

    //获取订单信息
    getorder() {
      this.$toast.loading({
          // 吐司的文本
          message: "加载中...",
          // 禁止背景点击
          forbidClick: true,
          // 展示时长
          duration: 0
        })
      axios({
        url:"orderServlet?action=selectOrders",
        method:"POST",
        data:this.loginuser
      }).then(res =>{
        this.$toast.clear();
        if (res.data.data==null){
          this.empty = !this.empty;
        }else {
          this.orderinfo = res.data.data[0];
          this.orderandgood = res.data.data[1];
          this.goodinfo = res.data.data[2];
          console.log(this.orderinfo);
        }

      }).catch(err =>{
        console.log(err);
      })
    },

    goorderdetail(oid){
      this.$router.push({path: "/main/my/order/orderdetail",query:{oid}})
    }
  }
}
</script>

<style scoped>

</style>