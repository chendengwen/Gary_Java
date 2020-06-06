/*
 * @param {string}opt.type  http连接的方式，包括POST和GET两种方式
 * @param {string}opt.url  发送请求的url
 * @param {boolean}opt.async  是否为异步请求，true为异步的，false为同步的
 * @param {object}opt.data  发送的参数，格式为对象类型
 * @param {function}opt.success  ajax发送并接收成功调用的回调函数
 */
function ajax(opt) {
	opt = opt || {};
	opt.method = opt.method.toUpperCase() || 'POST';
	opt.url = opt.url || '';
	opt.saync = opt.async || true;
	opt.data = opt.data || null;
	opt.success = opt.success || function () {};
	var xmlHttp = null;
	if (XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}else{
		xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');;
		
	}
	
	var params = [];
	for ( var key in opt.data) {
		params.push(key + '=' + opt.data[key]);
	}
	
	var postData = params.join('&');
	if (opt.method.toUpperCase() === 'POST') {
		xmlHttp.open(opt.method,opt.url,opt.async);
		xmlHttp.setRequestHeader("Content-Type","application/json;charset=utf-8");
	}else if (opt.method === 'GET') {
		xmlHttp.open(opt.method,opt.url + '?' + postData,opt.async);
		xmlHttp.setRequestHeader("Content-Type","application/json;charset=utf-8");
	}
	
	xmlHttp.onreadystatechange = function() {
		console.log('onreadystatechanged');
		console.log(xmlHttp.readyState);
		console.log(xmlHttp.status);
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			opt.success(JSON.parse(xmlHttp.responseText));
		}else{
			console.log("Error: "+ ajax.status); // 在此次请求中发生的错误
		}
	}
	
	if (opt.method.toUpperCase() === 'POST') {
		xmlHttp.send(postData);
	}else if (opt.method === 'GET') {
		xmlHttp.send(null);
	}
}

ajax({
	method:'GET',
	url:'../../login',
	success: function(responseJsonData) {
		// 对获取的数据进行操作
		console.log('异步回调成功');
	}
});