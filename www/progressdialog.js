cordova.define("cordova/plugin/progressdialog", function(require, exports, module) { 

	var exec = require('cordova/exec');
	
	module.exports = {

		/**  
31.     * 一共5个参数  
32.       第一个 :成功回调  
33.       第二个 :失败回调  
34.       第三个 :将要调用的类的配置名字(在config.xml中配置 稍后在下面会讲解)  
35.       第四个 :调用的方法名(一个类里可能有多个方法 靠这个参数区分)  
36.       第五个 :传递的参数  以json的格式  
37.     */ 
		
		show : function(message,successCB,failCB) {
	        exec(successCB, failCB, "ProgressDialog", "show", message);
		},
		
		hide : function(message,successCB,failCB) {
			exec(successCB, failCB, "ProgressDialog", "hide", message);
		}
	
	};

});

if (!window.plugins) {
	window.plugins = {};
}

console.log("将插件注入cordova...");
window.plugins.progressdialog = cordova.require("cordova/plugin/progressdialog");
console.log("注入结果：" + typeof(window.plugins.progressdialog));