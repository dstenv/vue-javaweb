<template>
    <div class="editgood">
      <!--      顶部导航-->
      <van-nav-bar
        title="修改信息"
        left-text="返回"
        left-arrow
        @click-left="comeback"
      />
        <div class="editgood-name">
            <p>修改物品名称：</p>
            <input type="text" v-model="good.gname" style="width:170px;height:20px;border-radius:10px;margin-top: 15px;">
            <input type="submit" value="修改" @click="updategname" style="margin:12px 0 0 4px;height:30px;width:55px;border-radius:10px;">
        </div>
        <div class="editgood-information">
          <p>修改物品介绍：</p>
          <input type="text" v-model="good.ginformation" style="width:170px;height:20px;border-radius:10px;margin-top: 15px;">
          <input type="submit" value="修改" @click="updateginfo" style="margin:12px 0 0 4px;height:30px;width:55px;border-radius:10px;">
        </div>

        <div class="editgood-price">
          <p>修改物品价格：</p>
          <input type="text" v-model="good.gprice" style="width:170px;height:20px;border-radius:10px;margin-top: 15px;">
          <input type="submit" value="修改" @click="updategprice" style="margin:12px 0 0 4px;height:30px;width:55px;border-radius:10px;">
        </div>
    </div>
</template>

<script>
import "../assets/Css/editgood.css"
import axios from "axios";

export default {
  name: "EditGoodView",
  data(){
      return{
        //当前登录状态下用户信息
        loginuser:{
          uid:"",
          username:""
        },

        //修改的商品对象
        good:{
          gid:"",
          gname:"",
          ginformation:"",
          gprice:"",
          gcoverpic:"",
          gpic:"",
          gpic2:"",
          gpic3:"",
          gtype:"",
        },
      }
    },
    created() {
        //获得登录的用户信息
        this.loginuser.uid = this.$store.getters.getuserid;
        this.loginuser.username = this.$store.getters.getusername;
        this.good.gid = this.$route.query.gid;
        console.log(this.$route.query.gid);
    },
    methods:{
      comeback(){
        this.$router.go(-1);
      },

      updategname(){
        this.$toast.loading({
            // 吐司的文本
            message: "加载中...",
            // 禁止背景点击
            forbidClick: true,
            // 展示时长
            duration: 0
          })
        axios({
          url:"goodServlet?action=updategoodname",
          method:"POST",
          data:this.good
        }).then(res =>{
          this.$toast.clear();
          console.log(res);
        }).catch(err =>{
          console.log(err);
        })
      }
    }
}
</script>

<style scoped>

</style>