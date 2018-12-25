##### Nginx通过反向代理访问tomcat

​	反向代理呢：即以代理服务器来接受internet上的连接请求，然后将请求转发给内部网络上的服务器，并将从服务器上得到的结果返回给internet上请求连接的客户端，此时代理服务器对外就表现为一个服务器。 

##### 配置tomcat

###### 	将项目目录修改为root目录（访问项目时可以不带项目名访问）

​		 删除自带Root项目将项目war包编译解压后项目名修改为ROOT

​	访问路径：http://127.0.0.1:8080

###### 配置nginx代理  配置完成后访问路径为127.0.0.1或localhost访问 正式环境映射为域名

```properties
#反响代理配置  127.0.0.1或localhost访问
    server {
        listen       80;
        # 域名
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        #location / {
        #    root   html;
        #    index  index.html index.htm;
        #}

        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

		# 要进行反响代理的地址  最终访问的地址
		location / {
            proxy_pass   http://127.0.0.1:8080;
        }

    }
```

##### 访问

http://127.0.0.1 或

http://localhost