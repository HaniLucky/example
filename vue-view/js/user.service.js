function queryAll() {
 	//发送get请求
 	this.$http.get(url + '/user/all').then(function(res) {
 		 this.users = res.body;
		// 模拟json数据
		/*
 		this.users = [{
 			"id": "1",
 			"name": "张三",
 			"sex": "1",
 			"tel": "110",
 			"address": "北京"
 		}, {
 			"id": "2",
 			"name": "李四",
 			"sex": "0",
 			"tel": "112",
 			"address": "杭州"
 		}, {
 			"id": "3",
 			"name": "王二",
 			"sex": "1",
 			"tel": "119",
 			"address": "天津"
 		}];
		*/
 	}, function() {
 		console.log('请求失败处理');
 	});
 }

 function clear() {
 	this.users = '';
 }
