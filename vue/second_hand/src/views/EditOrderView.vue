<template>
    <div class="editorder">
      <!--      顶部导航-->
<!--      <van-nav-bar-->
<!--        title="编辑订单"-->
<!--        left-text="返回"-->
<!--        left-arrow-->
<!--        @click-left="comeback"-->
<!--      />-->
      <van-dialog :show="show" title="订单信息" style="height:500px" width="350px" messageAlign="left" :showConfirmButton="buttonshow">
      <div class="editorder-item">
          <div class="item-data">
              <div class="item-data-img">
                  <img :src="imgurl + gooddetail.gcoverpic" alt="">
              </div>
            <div class="item-data-info">
              <p>商品名：{{gooddetail.gname}}</p>
              <p style="margin-top:10px;">商品类型：{{gooddetail.gtype}}</p>
              <p style="margin-top:10px;">商品介绍：{{gooddetail.ginformation}}</p>
            </div>
              <div class="item-data-price">
                  <p>￥{{gooddetail.gprice}}</p>
              </div>
          </div>
          <div class="item-data-address">
            <div class="item-data-selectaddress">
              <p>选择地址：</p>
              <select id="city" @click="getaddress">
                <option value="广东省">广东省</option>
                <option value="湖南省">湖南省</option>
                <option value="浙江省">浙江省</option>
                <option value="四川省">四川省</option>
                <option value="江苏省">江苏省</option>
              </select>
              <select id="area" @click="getaddress1">
                <option>广州市</option>
                <option>深圳市</option>
                <option>珠海市</option>
                <option>佛山市</option>
                <option>肇庆市</option>
              </select>
            </div>
            <div class="item-data-input">
              <p>输入详细地址：</p>
              <input type="text" v-model="inputaddress"><br/>
            </div>
            <van-button round type="primary" class="order-back-button" @click="gohome">返回首页</van-button>

            <van-button round type="primary" class="order-buy-button" @click="sendorderInfo">提交订单</van-button>

          </div>
      </div>
      </van-dialog>

    </div>
</template>

<script>
import "../assets/Css/editorder.css"
import axios from "axios";
import {Dialog, Toast} from "vant";

export default {
  name: "EditOrderView",
  data(){
    return{
      good:{
        gid:"",
      },
      loginuser:{
        uid:"",
        username:""
      },

      //商品详情数据
      gooddetail:{},

      //发布人信息
      userInfo:{},

      //选择的地址字符串
      selectaddress:"",
      selectaddress1:"",
      //输入的地址字符串
      inputaddress:"",

      //传送到后台的订单数据
      sendorder:{
        uid:"",
        address:"",
        gid:"",
      },

      //图片路径 利用绝对路径访问 public默认/
      imgurl: "/dataImg/",

      show:true,

      buttonshow:false,
    }
  },
  created() {
    this.loginuser.uid = this.$store.getters.getuserid;
    this.loginuser.username = this.$store.getters.getusername;
    this.good.gid = this.$route.query.gid;
    console.log(this.good.gid);

    this.getgooddetail();
  },
  methods:{
    comeback(){
      this.$router.go(-1)
    },

    //获得商品数据
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
        data:this.good
      }).then(res =>{
        this.$toast.clear();
        //console.log(res);
        this.gooddetail = res.data[0];
        this.userInfo = res.data[1];
        //console.log(res);
      }).catch(err =>{
        console.log(err);
      })
    },


    //选择地址 获得第一个下拉列表的地址值
    getaddress(){
      let select = document.getElementById("city");
      this.selectaddress = select.value;
      //console.log(this.selectaddress);
      select.onchange = function(){
        let optionVal = select.value;
        let area;
        switch(optionVal){
          case '广东省':
            area = document.getElementById("area");
            area.innerHTML = "<option>广州市</option><option>深圳市</option><option>珠海市</option><option>佛山市</option><option>肇庆市</option>";
            break;
          case '湖南省':
            area = document.getElementById("area");
            area.innerHTML = "<option>长沙市</option><option>株洲市</option><option>邵阳市</option><option>岳阳市</option>";
            break;
          case '浙江省':
            area = document.getElementById("area");
            area.innerHTML = "<option>杭州市</option><option>宁波市</option><option>温州市</option><option>嘉兴市</option><option>湖州市</option>";
            break;
          case '四川省':
            area = document.getElementById("area");
            area.innerHTML = "<option>成都市</option><option>绵阳市</option><option>自贡市</option><option>攀枝花市</option><option>泸州市</option>";
            break;
          case '江苏省':
            area = document.getElementById("area");
            area.innerHTML= "<option>常州市</option><option>徐州市</option><option>南京市</option><option>淮安市</option><option>南通市</option><option>宿迁市</option>";
            break;
          default:
            alert("error");
        }
      };
    },

    //获得第二个下拉列表的地址值
    getaddress1(){
      let select1 = document.getElementById("area");
      this.selectaddress1 = select1.value;
      //console.log(this.selectaddress1);
    },

    //生成订单 地址值字符串拼接
    sendorderInfo(){
        this.sendorder.address = this.selectaddress + this.selectaddress1 + this.inputaddress + "";
        console.log(this.sendorder.address);
        this.sendorder.uid = this.loginuser.uid;
        this.sendorder.gid = this.good.gid;
        console.log(this.sendorder);
        this.$toast.loading({
            // 吐司的文本
            message: "加载中...",
            // 禁止背景点击
            forbidClick: true,
            // 展示时长
            duration: 0
          })
        axios({
          url:"orderServlet?action=addOrder",
          method:"POST",
          data:this.sendorder
        }).then(res =>{
          this.$toast.clear();
          Toast.success("购买成功");
          Dialog.close;
          this.$router.push("/main/home");
          console.log(res);
        }).catch(err =>{
          console.log(err);
        })
    },
    gohome(){
      this.$router.push("/main/home");
    },

  },
}
</script>

<style scoped>

</style>