<template>
    <div class="my">
      <div class="my-Info">
          <p style="margin: 0;padding: 40px 0 0 0;font-size:20px;letter-spacing:5px;">用户{{userInfo.username}}</p>
      </div>
      <van-button block type="primary" @click="gomyorder" style="margin:5px 0 5px 0;border-bottom-right-radius:25px;border-top-right-radius:25px;">我的订单</van-button>
      <van-button block type="primary" @click="combackloging" style="margin:5px 0 5px 0;border-bottom-right-radius:25px;border-top-right-radius:25px;">退出登录</van-button>
      <van-button block type="primary" @click="goeditname" style="margin:5px 0 5px 0;border-bottom-right-radius:25px;border-top-right-radius:25px;">修改用户信息</van-button>

    </div>
</template>

<script>
import "../assets/Css/my.css"

export default {
  name: "MyView",
  data(){
    return{
      userInfo:{
        userid:"",
        username:"",
      }
    }
  },
  created() {
    this.userInfo.userid = this.$store.getters.getuserid;
    this.userInfo.username = this.$store.getters.getusername;
    console.log(this.userInfo.userid);
  },
  methods:{
      combackloging(){
        this.$router.push("/login");
      },
      gomyorder(){
        this.$router.push("/main/my/order");
      },
      goeditname(){
        this.$router.push("/main/my/editusername");
      }


  }
}
</script>

<style scoped>

</style>