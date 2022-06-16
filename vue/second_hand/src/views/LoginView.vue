<template>
    <div class="login">
      <div class="header">
        <img src="../assets/icons/logo/login.jpg" class="login_logo">
        <p style="font-size:15px;margin-right:10px;font-family:'楷体'" @click="gohome">先看一看</p>
      </div>
      <div class="UserInfo">
        <h2 class="title">登&nbsp;&nbsp;录</h2>
        <van-form>
          <van-cell-group inset>
            <van-field
              v-model="userInfo.username"
              name="用户名"
              label="用户名"
              placeholder="用户名"
              autocomplete="off"
              :rules="[{ required: true, message: '请填写用户名' }]"
            />
            <van-field
              v-model="userInfo.password"
              :type="isPasswordshow ? 'text':'password'"
              name="密码"
              label="密码"
              placeholder="密码"
              autocomplete="off"
              :right-icon="isPasswordshow ? 'eye-o' : 'closed-eye'"
              @click-right-icon="changepwdshow"
              :rules="[{ required: true, message: '请填写密码' }]"
            />
          </van-cell-group>
          <img :src="iconurl" >
          <div style="margin: 16px;">
            <van-button round block type="primary" native-type="submit" style="margin-bottom: 20px;margin-top:35px;" @click="login">
              登录
            </van-button>
            <van-button round block type="primary" native-type="submit" @click="goregist">
              注册
            </van-button>
          </div>
        </van-form>
<!--        <input type="text" v-model="username" class="usernameinfo">-->
<!--        <input type="text" v-model="password" class="passwordinfo">-->
<!--        <van-button type="primary" class="loginbutton">登录</van-button>-->
<!--        <van-button type="primary" class="registbutton">注册</van-button>-->
      </div>
    </div>
</template>

<script>
import "../assets/Css/login.css"
import axios from "axios";


export default {
  name: "LoginView",
  data(){
    return{
        iconurl:"",
      userInfo:{
        userid:"",
        username:"",
      },
      isPasswordshow:false
    }
  },
  created() {

  },
  methods:{
      changepwdshow(){
          this.isPasswordshow = !this.isPasswordshow;
      },
      login(){
          axios({
            url:"userservlet?action=login",
            method:"POST",
            data:this.userInfo
          }).then(res =>{
            console.log(res);
            this.userInfo.userid = res.data.data.id;
            this.userInfo.username = res.data.data.username;
            this.$store.commit({type: "setusername",userInfo:this.userInfo});
            console.log(this.$store.getters.getuserid);
            this.$router.push("/main/home");
          }).catch(err =>{
            console.log(err);
            this.$router.push("/login");
          })
      },
      gohome(){
        this.$router.push("/main/home");
      },
      goregist(){
        this.$router.push("/regist");
      }


  }

}
</script>

<style scoped>

</style>