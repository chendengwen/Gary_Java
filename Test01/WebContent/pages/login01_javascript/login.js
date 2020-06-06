// 基本的 Ajax 请求的示例

// 获取ajax对象
function ajaxObject() {
	var xmlHttp;
	try {
		// 创建了一个能向服务器发出 HTTP 请求的类的实例
		// Firefox, Opera 8.0+, Safari
		xmlHttp = new XMLHttpRequest();
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("您的浏览器不支持AJAX！");
                return false;
			}
		}
	}
	
	return xmlHttp;
}

// ajax post请求
function ajaxGet(url) {
	var ajax = ajaxObject();
	ajax.open("GET",url,true);
	ajax.setRequestHeader("Content-Type","application/json;charset=utf-8");
	//onreadystatechange 是异步的，那么这就意味着它将可在任何时候被调用。
	//这种类型的函数被称为回调函数——一旦某些处理完成后，它就会被调用。在此案例中，这个处理发生在服务器。
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) { // readyState 4 代表已向服务器发送请求
			if (ajax.status == 200) {
				var response = JSON.parse(ajax.responseText); // 返回的文本
				console.log("返回的数据");
				console.log(response);
				console.log("...................");
			}else{
				console.log("Error: "+ ajax.status); // 在此次请求中发生的错误
			}
		}
	}
	
	// 发送请求,参数为 null 
	ajax.send(null);
}

let titleUrl = '../../login';
ajaxGet(titleUrl);