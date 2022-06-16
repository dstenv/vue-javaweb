import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate';

export default createStore({
  state: {
    username:"",
    userid:0
  },
  getters: {
    getusername(state){
      return state.username
    },
    getuserid(state){
      return state.userid
    }
  },
  mutations: {
    setusername(state,user){
      state.userid = user.userInfo.userid;
      state.username = user.userInfo.username;
    }
  },
  actions: {
  },
  modules: {
  },
  plugins:[
    createPersistedState({
      //默认使用localStorage来固化数据，也可使用sessionStorage，配置一样
      storage: window.localStorage,
      reducer(val) {

        return {

          // 只储存state中的值
          username: val.username,
          userid: val.userid


        }

      }
    })
  ]
})
