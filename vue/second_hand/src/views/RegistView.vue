<template>
    <div class="regist">
      <div class="header">
        <img src="../assets/icons/logo/login.jpg" class="login_logo">
        <p style="font-size:15px;margin-right:10px;font-family:'楷体'" @click="gohome">先看一看</p>
      </div>
      <div class="UserInfo">
        <h2 class="title">注&nbsp;&nbsp;册</h2>
        <van-form>
          <van-cell-group inset>
            <van-field
              v-model="registInfo.username"
              name="用户名"
              label="用户名"
              placeholder="用户名"
              autocomplete="off"
              :rules="[{ required: true, message: '请填写用户名' }]"
            />
            <van-field
              v-model="registInfo.password"
              :type="isPasswordshow ? 'text':'password'"
              name="确认密码"
              label="密码"
              placeholder="密码"
              autocomplete="off"
              :right-icon="isPasswordshow ? 'eye-o' : 'closed-eye'"
              @click-right-icon="changepwdshow"
              :rules="[{ required: true, message: '请填写密码' }]"
            />
            <van-field
              v-model="confirmpassword"
              :type="isPasswordshow2 ? 'text':'password'"
              name="密码"
              label="确认密码"
              placeholder="请确认密码"
              autocomplete="off"
              :right-icon="isPasswordshow2 ? 'eye-o' : 'closed-eye'"
              @click-right-icon="changepwdshow2"
              :rules="[{ required: true, message: '请填写密码' }]"
            />
            <van-field
              v-model="registInfo.phone"
              name="手机号"
              label="手机号"
              placeholder="手机号"
              autocomplete="off"
            />
          </van-cell-group>
          <img :src="iconurl" >
          <div style="margin: 16px;">
<!--            <van-button round block type="primary" native-type="submit" style="margin-bottom: 20px;margin-top:35px;" @click="login">-->
<!--              登录-->
<!--            </van-button>-->
            <van-button round block type="primary" native-type="submit" @click="regist">
              注册
            </van-button>
            <van-button round block type="primary" native-type="submit" @click="combacklogin" style="margin-top:10px;">
              返回
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

import axios from "axios";

export default {
  name: "RegistView",
  data(){
    return {
      iconurl:"",
        registInfo: {
            username:"",
            password:"",
            phone: ""
        },
        confirmpassword: "",
      isPasswordshow:"",
      isPasswordshow2:""
    };
  },
  methods:{
    changepwdshow(){
      this.isPasswordshow = !this.isPasswordshow;
    },
    changepwdshow2(){
      this.isPasswordshow2 = !this.isPasswordshow2;
    },
    regist(){
      //判断一下两次的密码输入是否一致 并且是否合法


      axios({
        url:"userservlet?action=regist",
        method:"POST",
        data:this.registInfo
      }).then(res =>{
        console.log(res);
        if (res.data.code == 202){
          this.$toast.fail(res.data.msg);
        }else {
          this.$toast.success(res.data.msg);
          this.$router.push("/login");
        }
      }).catch(err =>{
        console.log(err);
      })
    },
    combacklogin(){
      this.$router.push("/login");
    }
  },
}
</script>

<style lang="less" scoped>

</style>