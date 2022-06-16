

const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // //解决跨域问题
  //   devServer: {
  //     proxy: {
  //       '/api': { // 这里最好有一个 /
  //         target: 'http://localhost:8082/web_war_exploded/', // 后台接口域名
  //         changeOrigin: true, // 是否允许跨域 设置为true
  //         pathRewrite: {  // 重写域名
  //           '^/api': '/'
  //         }
  //       },
  //     }
  //   }
  //配置绝对路径 从public文件夹导入 哪个路由需要就配哪个
  publicPath: '/',

})
