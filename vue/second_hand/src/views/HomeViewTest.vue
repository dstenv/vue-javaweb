<template>
  <div class="home">
    <div class="update">
      修改用户名<br/>
    <!--  <form action="http://localhost:8081/web_war_exploded/testservlet" method="get">-->
    <input type="text" v-model="newusernamevalue">
    <input type="submit" value="提交" @click="updateusername">
      <br/>修改用户密码<br/>
    <input type="text" v-model="newpasswordvalue">
    <input type="submit" value="提交" @click="updatepassword">
      <br/>填写商品信息<br/>
      <input type="text" v-model="goodInfo.gname"><br/>
      <input type="text" v-model="goodInfo.ginformation"><br/>
      <input type="text" v-model="goodInfo.gprice"><br/>
      <input type="text" v-model="goodInfo.gtype"><br/>
      <div>
        <input type="file" ref="file"/>
        <input type="file" ref="file2"/>
        <input type="file" ref="file3"/>
        <input type="file" ref="file4"/>
        <img :src=picpath alt="">
      </div>
<!--      <input type="text" v-model="goodInfo.gcoverpic">-->
<!--      <input type="text" v-model="goodInfo.gpic">-->
<!--      <input type="text" v-model="goodInfo.gpic2">-->
<!--      <input type="text" v-model="goodInfo.gpic3">-->
      <input type="submit" value="确定" @click="pictobase64">
      <input type="submit" value="提交" @click="savegood">
      <input type="submit" value="删除商品" @click="deletegood">
      <input type="submit" value="查看已发布商品" @click="selectgoodsByuser">
      <input type="submit" value="发送数组" @click="sendshuzu">
    </div>
  </div>
</template>

<script>


import axios from "axios";


export default {
  name: "HomeViewTest",
  //页面数据
  data(){
    return {
        received:{
        },
      newusernamevalue:"",
      newpasswordvalue:"",
        userInfo:{
          uid:"",
          username:"",
          passwor:""
        },
        goodInfo:{
          uid:"",
          gname:"iphonex",
          ginformation:"手机9成新",
          gprice:"100",
          gcoverpic:"",
          gpic:"",
          gpic2:"",
          gpic3:"",
          gtype:"",
        },
        picpath:"",
    };
  },
  created(){
    //console.log(this.userInfo.oldusername);
    this.received = this.$route.query;
    console.log(this.received);
    this.goodInfo.uid = this.received.userid;
    console.log(this.goodInfo.uid);
    this.userInfo.uid = this.received.userid;
    console.log(this.userInfo.uid);
  },
  methods:{
    pictobase64(){
      let file = this.$refs.file.files[0];
      let file2 = this.$refs.file2.files[0];
      let reader = new FileReader()
      let reader2 = new FileReader()
      reader.readAsDataURL(file)
      reader.onload = () => {
        //console.log('file 转 base64结果：' + reader.result)
        this.goodInfo.gcoverpic = reader.result
        console.log('file 转 base64结果：' + this.goodInfo.gcoverpic);
      }
      reader.onerror = function (error) {
        console.log('Error: ', error)
      }

      reader2.readAsDataURL(file2)
      reader2.onload = () => {

        this.goodInfo.gpic = reader2.result
        console.log('file2 转 base64结果：' + this.goodInfo.gpic)
      }
      reader2.onerror = function (error) {
        console.log('Error: ', error)
      }

      let file3 = this.$refs.file3.files[0];
      let reader3 = new FileReader()

      reader3.readAsDataURL(file3)
      reader3.onload = () => {

        this.goodInfo.gpic2 = reader3.result
        console.log('file2 转 base64结果：' + this.goodInfo.gpic2)
      }
      reader3.onerror = function (error) {
        console.log('Error: ', error)
      }

      let file4 = this.$refs.file4.files[0];
      let reader4 = new FileReader()

      reader4.readAsDataURL(file4)
      reader4.onload = () => {

        this.goodInfo.gpic3 = reader4.result
        console.log('file2 转 base64结果：' + this.goodInfo.gpic3)
      }
      reader4.onerror = function (error) {
        console.log('Error: ', error)
      }

    },

    updateusername() { //登录页面传用户id过来 修改用户名和密码时直接传用户信息的对象

      this.userInfo.username = this.newusernamevalue;
      axios({
        url: "userservlet?action=updateusername",
        method: "POST",
        data: this.userInfo
      }).then(res => {
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
    savegood(){


      axios({
          url:"goodServlet?action=savegood",
          method: "POST",
          data:this.goodInfo
        }).then(res => {
          console.log(res);
        }).catch(err => {
          console.log(err);
        })


    },
    deletegood(){
      axios({
        url:"goodServlet?action=deletegood",
        method:"POST",
        data:this.goodInfo
      }).then(res => {
        console.log(res);
      }).catch(err => {
        console.log(err);
      })
    },
    selectgoodsByuser(){
      axios({
        url:"goodServlet?action=selectgoodsByuserid",
        method: "POST",
        data:this.goodInfo
      }).then(res => {
        console.log(res);
      }).catch(err => {
        console.log(err);
      })
    },

    sendshuzu() {
        let shuzu =
          {
             id:[1,2,3]
          }

        //console.log(shuzu);
        console.log(JSON.stringify(shuzu));
        axios({
          url:"goodServlet?action=test",
          method: "POST",
          data: shuzu
        }).then(res =>{
          console.log(res);
        }).catch(err =>{
          console.log(err);
        })
    }

  },
}
</script>

<style lang="less" scoped>

</style>
