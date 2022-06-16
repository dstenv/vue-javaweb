<template>
  <div class="login">
    输入登录信息<br/>
  <!--  <form action="http://localhost:8081/web_war_exploded/testservlet" method="get">-->
  <input type="text" v-model="usernamevalue">
  <input type="text" v-model="passwordvalue">
  <input type="submit" value="提交" @click="login(usernamevalue)">
  </div>
  <!--  </form>-->
</template>


<script>
//import {ValidFrom} from "@/assets/js/VaildFrom"
import axios from "axios"
//import { useRouter } from 'vue-router'

export default {
  name: "LoginViewTest",
  //页面数据
  data() {
    return {
      usernamevalue: "",
      passwordvalue: "",
      userInfo: {
        uid:"",
        username: "",
        password: "",
        phone: "",
      },
    };
  },

  created() {

  },

  methods: {
    login() {
      //const router = useRouter();
      this.userInfo.username = this.usernamevalue;
      this.userInfo.password = this.passwordvalue;
      axios({
        url: "http://localhost:8082/web_war_exploded/userservlet?action=login",
        method: "POST",
        data: this.userInfo
      }).then(res => {
        this.userInfo.uid = res.data.data.id;
        console.log(this.userInfo.uid);
        console.log(res);
        this.$router.push({path:"/main/HomeTest",query:{uname:this.usernamevalue,userid:this.userInfo.uid}});
      }).catch(err => {
        console.log(err);
        this.$router.push("Login");
      })

    }
    // login() {
    //   let userObj = {
    //     nickname: {
    //
    //     },
    //     password:{
    //
    //     },
    //     phone: {
    //
    //     }
    //   }
    //   let ispass = ValidFrom.valid(userObj);
    //
    //   if (ispass){
    //     console.log("111");
    //   }
    //
    // },
  },
}
</script>

<style lang="less" scoped>

</style>
