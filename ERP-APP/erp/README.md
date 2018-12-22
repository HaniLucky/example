项目中遇到的坑

​	项目中用到的技术点 shiro，freemarker，poi

安全相关

shiro配置的大致流程

​	重要概念

​		RBAC

​	shiro比较重要的对象

​		Subject(主题信息，例如用户信息)

​		SecurityManager （shiro的核心，shiro的大脑）

​		Realm（提供用户信息，和资源信息）

​	Subject：即“当前操作用户”。但是，在Shiro中，Subject这一概念并不仅仅指人，也可以是第三方进程、后台帐户（Daemon Account）或其他类似事物。它仅仅意味着“当前跟软件交互的东西”。但考虑到大多数目的和用途，你可以把它认为是Shiro的“用户”概念。 　　

​	Subject代表了当前用户的安全操作，SecurityManager则管理所有用户的安全操作。 

　　SecurityManager：它是Shiro框架的核心，典型的Facade模式，Shiro通过SecurityManager来管理内部组件实例，并通过它来提供安全管理的各种服务。 　

​	Realm： Realm充当了Shiro与应用安全数据间的“桥梁”或者“连接器”。也就是说，当对用户执行认证（登录）和授权（访问控制）验证时，Shiro会从应用配置的Realm中查找用户及其权限信息。 　　从这个意义上讲，Realm实质上是一个安全相关的DAO：它封装了数据源的连接细节，并在需要时将相关数据提供给Shiro。当配置Shiro时，你必须至少指定一个Realm，用于认证和（或）授权。配置多个Realm是可以的，但是至少需要一个。 　　Shiro内置了可以连接大量安全数据源（又名目录）的Realm，如LDAP、关系数据库（JDBC）、类似INI的文本配置资源以及属性文件等。如果缺省的Realm不能满足需求，你还可以插入代表自定义数据源的自己的Realm实现。 

1.配置一个过滤器

​	web.xml中配置，拦截所有请求都进入shiro

2.创建一个自定义Realm

​	实现 AuthorizingRealm 接口

​	重写认证 doGetAuthenticationInfo 和授权 doGetAuthorizationInfo 方法

​	认证主要作用于 请求主体（比如用户）的合法性（登陆）

3.编写拦截规则

​	例如：登录页，没有权限跳转页，校验用户是否有权访问资源

​	判断是否有资源访问



安全相关（页面资源安全，接口资源安全）

​	页面安全：

​		通过shiro来控制，只放开登录页（login.html），错误页(error.html也就是未授权页面)以及静态资源（js,css等，静态资源必须要放开）

​	接口安全：

​		未认证只允许访问login方法

​		所有接口的地址都不允许未认证进行访问

误区：用户角色，角色权限应该由开发人员开设计，shiro等安全框架只提供认证和鉴权。不会返回用户角色列表，角色菜单列表等这些数据。这些数据还是应该由开发人员设计是实现



路径的问题

静态资源（项目根目录，也就是webapp下的资源，WEB-INF下的资源没有测试过，）不进springMvc控制器

​	springMvc中添加配置

​		<mvc:default-servlet-handler />

​	静态资源放在webapp下任何用户都能访问，但是springmvc配置将/下的所有请求都进入到前端控制器。

需要将静态资源放行

坑：WEB-INF下的资源必须要经过springmvc进行页面跳转，但是如果jsp页面可以通过标签可以获取到资源（js,css）等的绝对路径，但是如果是html资源路径的问题不好解决

建议：页面资源都放在webapp根目录下，web-inf下只放web.xml

