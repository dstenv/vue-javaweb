<template>
    <div class="editusername">
      <van-nav-bar
        title="修改用户信息"
        left-text="返回"
        left-arrow
        @click-left="comeback"
      />
      <div class="update">
        <p style="font-size:16px;margin-left:45px;font-family:'华文楷体'">修改用户名:</p>
        <!--  <form action="http://localhost:8081/web_war_exploded/testservlet" method="get">-->
        <input type="text" v-model="newusernamevalue" style="width:170px;height:20px;border-radius:10px;">
        <input type="submit" value="修改" @click="updateusername" style="margin:10px 0 0 65px;">
        <p style="font-size:16px;margin-left:45px;font-family:'华文楷体'">修改用户密码:</p>
        <input type="text" v-model="newpasswordvalue" style="width:170px;height:20px;border-radius:10px;">
        <input type="submit" value="修改" @click="updatepassword" style="margin:10px 0 0 65px;">
      </div>
    </div>
</template>

<script>
import axios from "axios";
import "../assets/Css/editusername.css"
import {Toast} from "vant";

export default {
  name: "EditUserNameView",
  data(){
      return{
        newusernamevalue:"",
        newpasswordvalue:"",
        userInfo:{
          uid:"",
          username:"",
          passwor:""
        },
      }
    },
    created() {
      this.userInfo.uid = this.$store.getters.getuserid;
      this.userInfo.username = this.$store.getters.getusername;
    },
    methods:{
      comeback(){
        this.$router.go(-1);
      },
      updateusername() {
        this.userInfo.username = this.newusernamevalue;
        axios({
          url: "userservlet?action=updateusername",
          method: "POST",
          data: this.userInfo
        }).then(res => {
          if (res.data.code == 100){
            Toast.fail(res.data.msg);
          }else
            Toast.success("修改成功")

          console.log(res);
        }).catch(err => {
          console.log(err);
        })
      },
      updatepassword(){
        this.userInfo.password = this.newpasswordvalue;
        axios({
          url:"userservlet?action=updatepassword",
          method: "POST",
          data: this.userInfo
        }).then(res => {
          console.log(res);
        }).catch(err => {
          console.log(err);
        })

      },
    }
}
</script>

<style scoped>

</style>