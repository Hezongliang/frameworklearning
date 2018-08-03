<!DOCTYPE HTML>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<meta name="description" content="">
<meta name="author" content="">

<title>欢迎进入学习平台 - ${userId}</title>

<base href="<%=basePath%>">

<!-- 以下两个插件是用于在IE8支持HTML5元素和媒体查询的，如果不用可移除 -->
<!--[if lt IE 9]>
  <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<link href="lib/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet"></link>

<script src="lib/jquery-3.2.1/jquery-3.2.1.min.js"></script>

<link href="lib/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="lib/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<link href="lib/bootstrap-submenu-2.0.4/dist/css/bootstrap-submenu.min.css" rel="stylesheet"></link>
<script src="lib/bootstrap-submenu-2.0.4/dist/js/bootstrap-submenu.min.js"></script>

<script src="lib/bootbox-4.4/bootbox.min.js"></script>

<link href="css/global.css" rel="stylesheet"></link>
<script src="js/global.js"></script>

<script>
	$(document).ready(function() {
		// 全局变量定义
		var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
		var userId = window.sessionStorage.getItem("UserId");
		
		// 全局jQuery对象定义
		$("#userId").val(window.sessionStorage.getItem("UserId"));
		
		// 初始化菜单
		$('[data-submenu]').submenupicker();
		
		$("#menu-homepage").on("click", function(event) {
			event.preventDefault();//使a自带的方法失效
			$("#reqForm").attr("action", contextPath + "/").attr("method", "get")
			.attr("target", "_top").submit();
		});
		
		$("#menu-user-info").on("click", function(event) {
			event.preventDefault();//使a自带的方法失效
			
	    	$.ajax({
				url : contextPath + "/user/getInfo.do",
				type : "POST",
				cache : false,
				async : false,  //设置为同步
				timeout : 10000, //ms
				dataType : "json",
				data : {
					userId : userId
				},
				error : function (XMLHttpRequest, textStatus, errorThrown) {
					bootbox.dialog({
						title: "获取用户信息请求异常",
						message: "异常信息：<br />状态码：" + XMLHttpRequest.status + "<br />状态：" + XMLHttpRequest.readyState + "<br />错误信息：" + textStatus,
						buttons: {
						    cancel: {
						        label: "关闭",
						        className: "btn-success"
						    }
						}
					});
				},
				success : function (data) {
					if (data.hasError) {
						bootbox.dialog({
							title: "获取用户信息失败",
							message: "失败原因：" + data.errorMessage,
							buttons: {
							    cancel: {
							        label: "关闭",
							        className: "btn-success"
							    }
							}
						});
					} else {
						if ($.isEmptyObject(data.data)) {
							bootbox.dialog({
								title: "获取用户信息失败",
								message: "失败原因：获取不到用户名：" + userId + "对应的用户信息",
								buttons: {
								    cancel: {
								        label: "关闭",
								        className: "btn-success"
								    }
								}
							});
						} else {
							var content = "<table class='table table-bordered table-hover table-striped'>";
							content += "<tr><td>用户名</td><td>" + data.data.userId + "</td></tr>";
							content += "<tr><td>电子邮箱</td><td>" + data.data.email + "</td></tr>";
							content += "<tr><td>注册时间</td><td>" + data.data.createTime + "</td></tr>";
							content += "<tr><td>生效时间</td><td>" + data.data.validTime + "</td></tr>";
							content += "<tr><td>失效时间</td><td>" + data.data.expireTime + "</td></tr>";
							content += "<tr><td>最后一次登录时间</td><td>" + data.data.lastLoginTime + "</td></tr>";
							content += "</table>";
							
							bootbox.dialog({
								title: "用户信息（用户名：" + data.data.userId + "）",
								message: content,
								buttons: {
								    cancel: {
								        label: "关闭",
								        className: "btn-success"
								    }
								}
							});
						}
					}
				}
			});
	    });
		
		$("#menu-logout").on("click", function(event) {
			event.preventDefault();//使a自带的方法失效
			
			bootbox.confirm({
    	        title: "提示",
    	        message: "是否确定退出登录？",
    	        buttons: {
    	            cancel: {
    	                label: "否",
    	                className: "btn-success"
    	            },
    	            confirm: {
    	                label: "是",
    	                className: "btn-danger"
    	            }
    	        },
    	        callback: function (result) {
    	            if (result) {
    	            	$.ajax({
    	    				url : contextPath + "/logout.do",
    	    				type : "POST",
    	    				cache : false,
    	    				async : false,  //设置为同步
    	    				timeout : 10000, //ms
    	    				dataType : "json",
    	    				data : {
    	    					userId : userId
    	    				},
    	    				error : function (XMLHttpRequest, textStatus, errorThrown) {
    	    					bootbox.dialog({
    	    						title: "退出登录请求异常",
    	    						message: "异常信息：<br />状态码：" + XMLHttpRequest.status + "<br />状态：" + XMLHttpRequest.readyState + "<br />错误信息：" + textStatus,
    	    						buttons: {
    	    						    cancel: {
    	    						        label: "关闭",
    	    						        className: "btn-success"
    	    						    }
    	    						}
    	    					});
    	    				},
    	    				success : function (data) {
    	    					if (data.hasError) {
    	    						bootbox.dialog({
    	    							title: "退出登录失败",
    	    							message: "失败原因：" + data.errorMessage,
    	    							buttons: {
    	    							    cancel: {
    	    							        label: "关闭",
    	    							        className: "btn-success"
    	    							    }
    	    							}
    	    						});
    	    					} else {
   	    							window.sessionStorage.clear();
   	    							
   	    							// 此处回到登录页用的是get方式，会把userId追加到url后面，所以设置为disable，属性为disable的input元素不会传入后台
   	    							$("#userId").attr("disabled", "true");
   	    							
   	    							$("#reqForm").attr("action", contextPath + "/").attr("method", "get").attr("target", "_top").submit();
    	    					}
    	    				}
    	    			});
    	            }
    	        }
    	    });
	    });
	});
</script>
</head>

<body>
	<div class="header">
		<div class="container">
			<div class="row text-center">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					页眉
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<br />
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<nav class="navbar navbar-default">
			<div class="navbar-header">
				<button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">切换导航栏</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>

				<a class="navbar-brand" id="menu-homepage"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
			</div>

			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown">
						<a tabindex="0" data-toggle="dropdown" data-submenu> 个人中心<span class="caret"></span></a>

						<ul class="dropdown-menu">
							<li><a tabindex="0" id="menu-user-info">基本信息</a></li>
							<li class="divider"></li>
							<li><a tabindex="0" id="menu-logout">退出登录</a></li>
						</ul>
					</li>
					
					<li class="dropdown">
						<a tabindex="0" data-toggle="dropdown" data-submenu> 帮助<span class="caret"></span></a>

						<ul class="dropdown-menu">
							<li class="dropdown-submenu">
								<a tabindex="0">系统使用</a>

								<ul class="dropdown-menu">
									<li><a tabindex="0" id="menu-report-finance">常见问题</a></li>
								</ul>
							</li>
							<li class="divider"></li>
							<li class="dropdown-submenu">
								<a tabindex="0">用户</a>

								<ul class="dropdown-menu">
									<li><a tabindex="0" id="menu-report-finance">常见问题</a></li>
								</ul>
							</li>
							<li class="divider"></li>
						</ul>
					</li>
				</ul>
			</div>
		</nav>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<p>欢迎各位在此平台学习</p>
			</div>
		</div>
	</div>
	
	<div class="footer">
		<div class="container">
			<div class="row text-center">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<br />
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					Copyright &copy; 2017. All rights reserved.
				</div>
			</div>
		</div>
	</div>
</body>
</html>
