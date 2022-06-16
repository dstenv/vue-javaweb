<template>
  <div class="main">
    <!-- 二级路由 -->
    <div>
      <router-view></router-view>
    </div>

    <!-- v-model="active" 默认选中home -->
    <van-tabbar v-model="active" active-color="#1296db" inactive-color="#646566" route fixed>
      <!-- icon: 图标名称或链接; name: 标签名称，作为匹配的标识符 -->
      <van-tabbar-item  v-for="(item, index) in tabBarData" :key=index :icon="item.icon" :name="item.name" :to="item.path" replace>
        <!-- 底部按钮标题 -->
        <span>{{item.title}}</span>

        <!-- 具有插槽作用域的插槽 -->
        <!-- icon: 自定义图标	  active: 是否为选中标签 -->
        <template #icon="props">
          <img :src="props.active ? item.activeImg : item.inactiveImg" style="width:25px; height:25px"/>
        </template>
      </van-tabbar-item>
    </van-tabbar>


  </div>
</template>

<script>


export default {
  name:"MainView",
  data(){
      return {
        active:"Home",
        uid:"",
        username:"",
        tabBarData: [
          {
            title: "首页",
            // 图标名称
            icon: "home-o",
            // 图片链接: 未选中
            inactiveImg: require("../assets/icons/icon/home.png"),
            // 图片链接: 选中
            activeImg: require("../assets/icons/icon/home_avtive.png"),
            // 标签名称, 跳转的路由名称
            name: "Home",
            // 要跳转到的路由的路径
            path:"/main/home",
          },
          {
            title: "发布详情",
            icon: "senddata-o",
            inactiveImg: require("../assets/icons/icon/senddata.png"),
            activeImg: require("../assets/icons/icon/senddata_active.png"),
            name: "SendData",
            path:"/main/senddata",
          },
          {
            title: "购物车",
            icon: "friends-o",
            inactiveImg: require("../assets/icons/icon/shop.png"),
            activeImg: require("../assets/icons/icon/shop_active.png"),
            name: "Shop",
            path:"/main/shop",
          },
          {
            title: "我的",
            icon: "setting-o",
            inactiveImg: require("../assets/icons/icon/my.png"),
            activeImg: require("../assets/icons/icon/my_active.png"),
            name: "My",
            path:"/main/my",
          }
        ],
      };
  },
  created() {
    // this.username = this.$route.query.username;
    // console.log(this.username);
    // this.uid = this.$route.query.userid;
    // console.log(this.uid);
  },
  methods:{

  },
}
</script>

<style lang="less" scoped>
</style>