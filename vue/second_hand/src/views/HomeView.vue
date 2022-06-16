<template>
  <div class="home">
    <!--        顶部导航-->
    <div class="home-nav">
      <div class="t1">欢迎光临,{{this.user.username}}</div>
      <van-search placeholder="输入商品名称" shape="round" class="search"/>
    </div>

    <!-- 页面内容 -->
    <div class="home-content">
      <!--        轮播图-->
      <div class="banner-box">
        <van-swipe class="my-swipe" :autoplay="2000" indicator-color="white" @change="changebannerIndex">
          <van-swipe-item v-for="(item,index) in banner" :key="index" class="banner-item">
            <img :src="item.img" alt="">
          </van-swipe-item>
        </van-swipe>
      </div>

      <!--        数据-->
      <div class="home-TJdata">
        <div class="product-title">
          <van-button round icon="plus" type="primary" @click="gorelease"/>
          <p style="color:black;font-size:16px;">人气推荐</p>
          <select id="product-type" @change="getproductBytype" style="margin-top: 5px;width: 60px;height: 40px;">
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

        <div class="product">
          <div class="product-item" v-for="(item,index) in productData" :key="index" @click="godetail(item.gid)">
            <div class="imgbox">
              <img :src="imgurl + item.gcoverpic + ''" alt="">
            </div>
            <div class="productInfobox">
              <div class="product-dataname">{{ item.gname }}</div>
              <div class="product-price">价格:{{ item.gprice }}</div>
            </div>


          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import axios from "axios"
import "../assets/Css/home.css"

export default {
  name: "HomeView",
  data() {
    return {
      user: {
        userid: "",
        username: "",
      },

      //  轮播图数据
      bannerIndex: 0,
      banner: [
        {
          img: require('../assets/Imgs/banner1.png'),
        },
        {
          img: require('../assets/Imgs/banner2.png'),
        },
        {
          img: require('../assets/Imgs/banner3.png'),
        },
        {
          img: require('../assets/Imgs/banner4.png'),
        },
      ],

      //商品推荐数据
      productData: [],

      //保存选择的商品类型
      good: {
        gid: "",
        gtype: "",
      },

      //图片路径 利用绝对路径访问 public默认/
      imgurl: "/dataImg/"
    }
  },
  created() {
    this.user.userid = this.$store.getters.getuserid;
    this.user.username = this.$store.getters.getusername;
     // console.log(this.user.userid);
      console.log("登录用户为：" + this.user.username);

    //获取商品推荐数据
    this.getproduct();

  },
  methods: {
    changebannerIndex(index) {
      this.bannerIndex = index;
    },

    getproduct() {
      this.$toast.loading({
        // 吐司的文本
        message: "加载中...",
        // 禁止背景点击
        forbidClick: true,
        // 展示时长
        duration: 0
      })

      axios({
        url: "goodServlet?action=selectGoods",
        method: "POST",
        data: null
      }).then(res => {
        this.$toast.clear();
        this.productData = res.data.data;
        //this.productData[0].gcoverpic = this.imgurl + this.productData[0].gcoverpic;
        console.log(this.productData);
        //console.log(this.productDataimgurl);
        //console.log(this.productData[0].gcoverpic);
      }).catch(err => {
        console.log(err);
      })
    },

    //根据所选择的类型展示商品
    getproductBytype() {
      let select = document.getElementById("product-type");
      this.good.gtype = select.value;
      //console.log(this.good.gtype);
      if (this.good.gtype == "全部") {
        this.$toast.loading({
          // 吐司的文本
          message: "加载中...",
          // 禁止背景点击
          forbidClick: true,
          // 展示时长
          duration: 0
        })

        axios({
          url: "goodServlet?action=selectGoods",
          method: "POST",
          data: null
        }).then(res => {
          this.$toast.clear();
          this.productData = res.data.data;
          //this.productData[0].gcoverpic = this.imgurl + this.productData[0].gcoverpic;
          console.log(this.productData);
          //console.log(this.productDataimgurl);
          console.log(this.productData[0].gcoverpic);
        }).catch(err => {
          console.log(err);
        })
      } else {
        this.$toast.loading({
          // 吐司的文本
          message: "加载中...",
          // 禁止背景点击
          forbidClick: true,
          // 展示时长
          duration: 0
        })
        axios({
          url: "goodServlet?action=selectGoodsBytype",
          method: "POST",
          data: this.good
        }).then(res => {
          this.$toast.clear();
          this.productData = res.data.data;
          //this.productData[0].gcoverpic = this.imgurl + this.productData[0].gcoverpic;
          console.log(this.productData);
          //console.log(this.productDataimgurl);
          //console.log(this.productData[0].gcoverpic);
        }).catch(err => {
          console.log(err);
        })
      }

    },

    //跳转到详情页面
    godetail(gid){
      //console.log(gid);
      this.good.gid = gid;
      this.$router.push({path:"/main/home/gooddetail",query:{gid:this.good.gid}})
    },

    //跳转到发布商品的页面
    gorelease(){
      this.$router.push("/main/home/releases");
    }
  }
}
</script>

<style scoped>

</style>