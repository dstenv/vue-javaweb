<template>
    <div class="release">
      <!--      顶部导航-->
      <van-nav-bar
        title="填写信息"
        left-text="返回"
        left-arrow
        @click-left="comeback"
      />
        <div  class="release-data">
            <div class="release-data-name">
              <van-icon :name="name_icon" style="margin-top:6px;"></van-icon>
              <p style="margin:3px 0 0 10px;">商品名称</p>
              <input type="text" v-model="goodInfo.gname"><br/>
            </div>
            <div class="release-data-type">
              <van-icon :name="type_icon" style="margin-top:6px;"></van-icon>
              <p style="margin:3px 0 0 10px;">商品类型</p>
              <select id="type" @change="getproductBytype">
                <option value="全部">全部</option>
                <option value="数码">数码</option>
                <option value="服装">服装</option>
                <option value="资料">资料</option>
                <option value="日常">日常</option>
                <option value="电器">电器</option>
                <option value="乐器">乐器</option>
                <option value="其他">其他</option>
              </select>
            </div>
            <div  class="release-data-infomation">
                <van-icon :name="infomation_icon" style="margin-top:6px;"></van-icon>
                <p style="margin:3px 0 0 10px;">商品介绍</p>
                <van-cell-group inset style="width:60%;margin-left:10px;">
                  <van-field
                    v-model="goodInfo.ginformation"
                    rows="2"
                    size="large"
                    type="textarea"
                    maxlength="50"
                    placeholder="请输入留言"
                    style="padding:5px 8px;"
                    show-word-limit
                  />
                </van-cell-group>
            </div>
          <div class="release-data-price">
            <van-icon :name="price_icon" style="margin-top:6px;"></van-icon>
            <p style="margin:3px 0 0 10px;">商品价格</p>
            <input type="text" v-model="goodInfo.gprice"><br/>
          </div>

          <div class="release-data-img">
            <van-icon :name="img_icon" style="margin-top:6px;"></van-icon>
            <p style="margin:3px 0 0 10px;width:100px;">封面图</p>
            <input type="file" ref="file"/>
          </div>
          <div class="release-data-img">
            <van-icon :name="img_icon" style="margin-top:6px;"></van-icon>
            <p style="margin:3px 0 0 10px;width:100px;">详情图1</p>
            <input type="file" ref="file2"/>
          </div>
          <div class="release-data-img">
            <van-icon :name="img_icon" style="margin-top:6px;"></van-icon>
            <p style="margin:3px 0 0 10px;width:100px;">详情图2</p>
            <input type="file" ref="file3"/>
          </div>
          <div class="release-data-img">
            <van-icon :name="img_icon" style="margin-top:6px;"></van-icon>
            <p style="margin:3px 0 0 10px;width:100px;">详情图3</p>
            <input type="file" ref="file4"/>
          </div>
        </div>
      <div class="release-control">
        <van-button round type="primary" @click="pictobase64">确定</van-button>
        <van-button round type="primary" @click="savegood">发布</van-button>
      </div>
    </div>
</template>

<script>
import "../assets/Css/release.css"
import axios from "axios";
import {Toast} from "vant";

export default {
  name: "ReleaseView",
  data(){
    return{
        loginuser:{
          uid:"",
          username:""
        },

      //商品信息
      goodInfo:{
        uid:"",
        gname:"",
        ginformation:"",
        gprice:"",
        gcoverpic:"",
        gpic:"",
        gpic2:"",
        gpic3:"",
        gtype:"",
      },

      //图标
      name_icon:require("../assets/icons/icon/good_name.png"),
      type_icon:require("../assets/icons/icon/good_type.png"),
      infomation_icon:require("../assets/icons/icon/good_infomation.png"),
      price_icon:require("../assets/icons/icon/good_price.png"),
      img_icon:require("../assets/icons/icon/good_pic.png"),
    }
  },
  created() {
      this.loginuser.uid = this.$store.getters.getuserid;
      this.loginuser.username = this.$store.getters.getusername;
      this.goodInfo.uid = this.loginuser.uid;
  },
  methods:{
    //拿到选择的类型的值
    getproductBytype() {
      let select = document.getElementById("type");
      this.goodInfo.gtype = select.value;
      console.log(this.goodInfo.gtype);
    },


    //图片文件转base64
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
        console.log('file3 转 base64结果：' + this.goodInfo.gpic2)
      }
      reader3.onerror = function (error) {
        console.log('Error: ', error)
      }

      let file4 = this.$refs.file4.files[0];
      let reader4 = new FileReader()

      reader4.readAsDataURL(file4)
      reader4.onload = () => {

        this.goodInfo.gpic3 = reader4.result
        console.log('file4 转 base64结果：' + this.goodInfo.gpic3)
      }
      reader4.onerror = function (error) {
        console.log('Error: ', error)
      }
    },

    comeback(){
      this.$router.go(-1)
    },

    savegood(){
      console.log(this.goodInfo);
      axios({
        url:"goodServlet?action=savegood",
        method: "POST",
        data:this.goodInfo
      }).then(res => {
        Toast.success("发布成功");
        console.log(res);
      }).catch(err => {
        console.log(err);
      })
    }
  }
}
</script>

<style scoped>

</style>