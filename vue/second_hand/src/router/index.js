import { createRouter, createWebHistory} from 'vue-router'
import Main from "../views/MainView"
import LoginTest from "../views/LoginViewTest"


const routes = [
  {
    name: 'Main',
    path: '/main',
    component: Main,
    children: [
    {
      name: "Home",
      path: "home",
      component: ()=> import("../views/HomeView"),
    },
    {
      name: "Senddata",
      path: "senddata",
      component: ()=> import("../views/SendDataView"),
    },
    {
      name: "Shop",
      path: "shop",
      component: ()=> import("../views/ShopView"),
    },
    {
      name: "My",
      path: "my",
      component: ()=> import("../views/MyView"),
    }
    ]
  },
  {
    name: "HomeTest",
    path: "/HomeTest",
    component: () => import("../views/HomeViewTest"),
  },
  {
    name: 'LoginTest',
    path: '/LoginTest',
    component: LoginTest,
  },
  {
    name: 'login',
    path: '/login',
    component: () => import("../views/LoginView"),
  },
  {
    name: 'regist',
    path: '/regist',
    component: () => import("../views/RegistView"),
  },
  {
    name: 'GoodDetail',
    path: '/main/home/gooddetail',
    component: () => import("../views/GoodDetailView")
  },
  {
    name:'Releases',
    path: '/main/home/releases',
    component: () => import("../views/ReleaseView")
  },
  {
    name:'Order',
    path: '/main/my/order',
    component: () => import("../views/OrderView")
  },
  {
    name: 'EditOrder',
    path: '/editorder',
    component: () => import("../views/EditOrderView")
  },
  {
    name:'SendGood',
    path: '/main/senddata/sendgood',
    component: () => import("../views/SendGoodView")
  },
  {
    name:'EditUserName',
    path: '/main/my/editusername',
    component: () => import("../views/EditUserNameView")
  },
  {
    name:'EditGood',
    path: '/main/home/sendgood/editgood',
    component: () => import("../views/EditGoodView")
  },
  {
    name:'OrderDetail',
    path: '/main/my/order/orderdetail',
    component: () => import("../views/OrderDetailView")
  },


  {
      path: '',
      redirect:{
          name: "login",
          path: "/login",
      }
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
