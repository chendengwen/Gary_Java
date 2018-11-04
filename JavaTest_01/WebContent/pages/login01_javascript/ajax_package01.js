// Ajax 方法
  
// 惰性载入创建 xhr 对象
function createXHR(){
	if ('XMLHttpRequest' in window ){  
		createXHR = function(){
			return new XMLHttpRequest();
		};
	} else if('ActiveXObject' in window ){
		createXHR = function(){
			return new ActiveXObject("Msxml2.XMLHTTP");
		};
	} else {
		createXHR = function(){
			throw new Error("Ajax is not supported by this browser");
		};
	}
  
	return createXHR();
}
  
// Ajax 执行
function request(ajaxData){
  
	var xhr = createXHR();
	ajaxData.before && ajaxData.before();
  
	// 通过事件来处理异步请求
	xhr.onreadystatechange = function(){
		if( xhr.readyState == 4 ){
			if( xhr.status == 200 ){
				if( ajaxData.dataType == 'json' ){
					// 获取服务器返回的 json 对象
					jsonStr = xhr.responseText;
					json1 = eval('(' + jsonStr + ')'),
					json2 = (new Function('return ' + jsonStr))();
					ajaxData.callback(json2);
					// ajaxData.callback(JSON.parse(xhr.responseText)); // 原生方法，IE6/7 不支持
				} else ajaxData.callback(xhr.responseText);
			} else {
				ajaxData.error && ajaxData.error(xhr.responseText);
			}
		}
	};
  
	// 设置请求参数
	xhr.open(ajaxData.type, ajaxData.url);
  
	if( ajaxData.noCache == true ) xhr.setRequestHeader('If-Modified-Since', '0');
	if( ajaxData.data ){
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhr.send( ajaxData.data );
	} else {
		xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
		xhr.send( null );
	}
  
	return xhr;
}
  
function post(ajaxData){
	ajaxData.type = 'POST';
	var _result = request(ajaxData);
	return _result;
}
  
function get(ajaxData){
	ajaxData.type = 'GET';
	ajaxData.data = null;
	var _result = request(ajaxData);
  
	return _result;
}