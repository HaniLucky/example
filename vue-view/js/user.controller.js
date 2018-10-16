//方法名自定义
init();

//方法名要一致
function init() {
	console.log("初始化成功");
}

$(function() {
	console.log("初始化方法查询成功");
	vm.queryAll();
})


// 初始化数据
var users = []

// 创建vue实例
var vm = new Vue({

	el: '#app',
	data: {
		users: users
	},
	methods: {
		queryAll: queryAll,
		clear: clear
	},

});
