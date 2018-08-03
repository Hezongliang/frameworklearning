<!DOCTYPE HTML>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<title>中兴软创铁塔项目框架学习平台</title>

<base href="<%=basePath%>">

<!-- 以下两个插件是用于在IE8支持HTML5元素和媒体查询的，如果不用可移除 -->
<!--[if lt IE 9]>
  <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<link href="lib/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet"></link>

<script src="lib/jquery-3.2.1/jquery-3.2.1.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="lib/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="lib/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<link href="lib/bootstrapvalidator-0.5.3/dist/css/bootstrapValidator.min.css" rel="stylesheet"></link>
<script src="lib/bootstrapvalidator-0.5.3/dist/js/bootstrapValidator.min.js"></script>
<script src="lib/bootstrapvalidator-0.5.3/dist/js/language/zh_CN.js"></script>

<script src="lib/bootbox-4.4/bootbox.min.js"></script>

<link href="css/global.css" rel="stylesheet"></link>
<script src="js/global.js"></script>

<script>
$(document).ready(
	function() {
		// 全局变量定义
		var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
		
		// 全局jQuery对象定义
		var $reqForm = $("#reqForm");
		var $userId = $("#userId");
		var $userPassword = $("#userPassword");
		var $loginButton = $("#loginbutton");
		
		$reqForm.bootstrapValidator({
            message: "输入内容验证失败",
            feedbackIcons: {
                valid: "glyphicon glyphicon-ok",
                invalid: "glyphicon glyphicon-remove",
                validating: "glyphicon glyphicon-refresh"
            },
            live: "enabled",
            fields: {
            	userId: {
                    message: "用户名验证失败",
                    validators: {
                        notEmpty: {
                            message: "用户名不能为空"
                        }
                    }
                },
                userPassword: {
                    message: "密码验证失败",
                    validators: {
                        notEmpty: {
                            message: "密码不能为空"
                        }
                    }
                },
                identifyCode: {
                    message: "验证码验证失败",
                    threshold: 4,  //验证码全部输入完成以后，再进行后台验证
                    validators: {
                        notEmpty: {
                            message: "验证码不能为空"
                        },
                        remote: {  //ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
                        	url : contextPath + "/captcha/verifyCode.do",
    						type: "GET",
    						message: "验证码输入错误", //提示消息
    						delay: 20
                        }
                    }
                }
            }
        });

		function login() {
			var userId = $.trim($userId.val());
			var userPassword = $.trim($userPassword.val());

			$.ajax({
				url : contextPath + "/loginCheck.do",
				type : "POST",
				cache : false,
				async : false,
				dataType : "json",
				data : {
					userId : userId,
					userPassword : userPassword,
					loginType : 0
				},
				beforeSend : function() {
					$loginButton.text("登录中");
					$loginButton.attr("disabled", true); //在发起调用之前，先把按钮禁用，防止页面重复提交，调用结束后，再解除禁用
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$loginButton.text("登录");
					$loginButton.attr("disabled", false);
					
					bootbox.dialog({
						title: "登录请求异常",
						message: "异常信息：<br />状态码：" + XMLHttpRequest.status + "<br />状态：" + XMLHttpRequest.readyState + "<br />错误信息：" + textStatus,
						buttons: {
						    cancel: {
						        label: "关闭",
						        className: "btn-success"
						    }
						}
					});
				},
				success : function(data) {
					if (data.hasError) {
						$loginButton.text("登录");
						$loginButton.attr("disabled", false);
						
						bootbox.dialog({
							title: "登录失败",
							message: "失败原因：" + data.errorMessage,
							buttons: {
							    cancel: {
							        label: "关闭",
							        className: "btn-success"
							    }
							}
						});
					} else {
						$loginButton.text("登录成功，即将跳转");

						window.sessionStorage.clear();
						window.sessionStorage.setItem("UserId", userId);

						// 由于使用了bootstrapValidator的原因，此处必须使用document.getElementById的方式进行提交，使用ajax的submit方法提交，页面无反应
						document.getElementById("reqForm").action = contextPath + "/welcome.html";
						document.getElementById("reqForm").method = "post";
						document.getElementById("reqForm").submit();
					}
				}
			});
		}

		// 重新生成验证码（t=Math.random()是为了保证变更servlet生成的图片名称，否则页面图片不会刷新）
		$("#imageCode").on("click", function() {
			$("#imageCode").attr("src", contextPath + "/captcha/getCode.do?t=" + Math.random());
		});
		
		$("#loginbutton").on("click", function() {
			//获取表单对象
		    var bootstrapValidator = $reqForm.data('bootstrapValidator');
			
	        //手动触发验证
	        bootstrapValidator.validate();
	        
	        if (bootstrapValidator.isValid()) {
	        	login();
	        } else {
	        	return false;
	        }
		});
	});
</script>
</head>

<body>
	<div class="header">
		<div class="container">
			<div class="row text-center">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<h1>中兴软创公司铁塔项目现场</h1></br>
					<h2>员工框架学习平台</h2>
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
		<div class="row margin-top-40">
			<div class="col-lg-4 col-md-4 col-sm-4"></div>
			
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
				<form id="reqForm" method="post" action="" role="form">
					<div class="row form-group no-margin">
						<input type="text" id="userId" name="userId" class="input-lg form-control" placeholder="请输入用户名"	autofocus="autofocus" />
					</div>
					<div class="row form-group no-margin">
						<input type="password" id="userPassword" name="userPassword" class="input-lg form-control" placeholder="请输入密码" autofocus="autofocus" />
					</div>
					
					<div class="row form-group no-margin">
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 no-padding">
							<input type="text" id="identifyCode" name="identifyCode" class="col-lg-8 col-md-8 col-sm-8 col-xs-8 input-lg form-control" placeholder="请输入验证码" />
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 no-padding">
							<img alt="验证码" id="imageCode" class="img-thumbnail" src="<%=basePath%>captcha/getCode.do" />
						</div>
						
						<div class="clearfix"></div>
					</div>
					<div class="row form-group no-margin">
						<button id="loginbutton" name="loginbutton" type="button" class="btn btn-primary btn-lg btn-block">登录</button>
					</div>
				</form>
			</div>
			
			<div class="col-lg-4 col-md-4 col-sm-4"></div>
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