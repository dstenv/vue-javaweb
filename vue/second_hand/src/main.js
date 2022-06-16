import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios"
import {Button, Switch} from 'vant'
import {Field} from "vant"
import { Form } from "vant"
import {Tabbar,TabbarItem} from "vant"
import { Cell,CellGroup } from "vant"
import { Toast } from "vant"
import { NavBar } from "vant"
import { Search,Swipe, SwipeItem,Divider } from "vant"
import { RadioGroup,Radio,Icon,Checkbox, CheckboxGroup } from "vant"
import { Dialog,Empty } from 'vant'
import 'lib-flexible/flexible'

//import Vue from 'vue'
// import VueAxios from "vue-axios"

// import {
//     Toast
// } from 'vant'

axios.defaults.baseURL = "http://localhost:8082/second_hand/";
// 配置post请求的axios拦截器 => 在发起请求之前, 先序列化一下请求参数
// axios拦截器 => 发送请求之前执行的代码
axios.interceptors.request.use(config =>{
  // 如果post请求, 需要序列化请求参数
  if(config.method == "post"){
    // 序列化请求参数 => 变成 url 参数
    let paramsString = "";

    for(var key in config.data){
      paramsString+= `${key}=${config.data[key]}&`
    }

    // 裁切掉最后的一个 & 符号, 把字符串赋值给 config.data
    config.data = paramsString.slice(0, -1);
  }

  // 拦截器必须返回config
  return config;
});





createApp(App)
  .use(store)
  .use(Button)
  .use(Field)
  .use(Form)
  .use(Tabbar)
  .use(TabbarItem)
  .use(Cell)
  .use(CellGroup)
  .use(Toast)
  .use(NavBar)
  .use(Search)
  .use(Swipe)
  .use(SwipeItem)
  .use(Divider)
  .use(RadioGroup)
  .use(Radio)
  .use(Icon)
  .use(Checkbox)
  .use(CheckboxGroup)
  .use(Dialog)
  .use(Empty)
  .use(router)
  .mount('#app')
