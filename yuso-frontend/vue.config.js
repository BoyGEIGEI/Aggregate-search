const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
});

module.exports = {
	lintOnSave:false,  // 修改成false 就是不检查了
  css: {
        loaderOptions: {
           less: {
            lessOptions: {
               javascriptEnabled: true
             }
           }
         }
       }
}
