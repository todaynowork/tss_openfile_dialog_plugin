cordova.define("cordova/plugin/openfile", function(require, exports, module) { 

	var exec = require('cordova/exec');
	
	module.exports = {
		
		open : function(message,successCB,failCB) {
	        exec(successCB, failCB, "OpenFile", "open", message);
		}
	
	};

});

if (!window.plugins) {
	window.plugins = {};
}

window.plugins.openfile = cordova.require("cordova/plugin/openfile");