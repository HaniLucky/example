#### Nginx解决跨域问题

##### 后端代码

| 请求地址                   | 返回数据（json数据） |
| -------------------------- | -------------------- |
| http://127.0.0.1:8080//app | Hello World!         |

##### 前端代码

通过nginx做静态资源服务器访问端口8081

```html
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
		</script>
		<script>
			$(document).ready(function(){
			  $("button").click(function(){
                  // 直接请求后端接口访问不通 会爆跨域问题
				// $.get("http://127.0.0.1:8080/app",function(data,status){
                  // 利用同源策略访问  访问的实际地址是  http:127.0.0.1:8081/api/app
				$.get("/api/app",function(data,status){
				  alert("数据：" + data + "\n状态：" + status);
				});
			  });
			});
		</script>
	</head>
<body>
	<button>向页面发送 HTTP GET(跨域)请求，然后获得返回的结果</button>
</body>
</html>
```

##### ngxin配置

```json
#前端工程静态资源服务器(同时使用同源策略解决跨域问题)
	server {
		#访问端口(页面访问端口)
        listen 8081;
        server_name localhost;


        error_page 500 502 503 504 /50x.html;
			location = /50x.html {
			root html;
        }
		
		
        location / {
			# 前端工程根目录
			root C:\Users\Covet\Desktop\origin-view;
			index index.html;
        }
		
		#代理路径 地址是以spi开头的 ‘/api开头的都走这个代理’
		# 将前端访问的后台端口变更为‘前台id:前台端口/api/xxx/xxx’
		location /api {
			#正则表达式匹配路径
			rewrite  ^.+api/?(.*)$ /$1 break;
			include  uwsgi_params;
			#后端端口(后端最终访问的端口)
			proxy_pass   http://127.0.0.1:8080;
		}
		
        location @router {
        rewrite ^.*$ /index.html last;
        }
	}
```

