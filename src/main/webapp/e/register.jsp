<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>用户注册 - 二手交易平台</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
	<style>
		.register-container { width: 400px; margin: 50px auto; background: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 20px rgba(0,0,0,0.1); }
		.register-title { text-align: center; font-size: 24px; margin-bottom: 30px; color: #333; }
		.form-group { margin-bottom: 20px; }
		.form-group label { display: block; margin-bottom: 5px; font-size: 14px; color: #666; }
		.form-group input { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; font-size: 14px; }
		.form-group input:focus { outline: none; border-color: #e74c3c; }
		.btn { width: 100%; padding: 12px; background: #e74c3c; color: #fff; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; }
		.btn:hover { background: #c0392b; }
		.login-link { text-align: center; margin-top: 20px; }
		.login-link a { color: #e74c3c; text-decoration: none; }
		.login-link a:hover { text-decoration: underline; }
		.error-message { color: #e74c3c; font-size: 12px; margin-top: 10px; text-align: center; }
	</style>
</head>
<body>
	<header class="site-header">
		<div class="header-wrap">
			<div class="logo">
				<h1><a href="${pageContext.request.contextPath}/">二手交易平台</a></h1>
			</div>
			<nav class="nav">
				<ul>
					<li><a href="${pageContext.request.contextPath}/e/index.jsp">首页</a></li>
					<li><a href="${pageContext.request.contextPath}/e/shangpinlist.jsp">商品列表</a></li>
					<li><a href="${pageContext.request.contextPath}/e/xinxiadd.jsp">发布信息</a></li>
				</ul>
			</nav>
		</div>
	</header>
	
	<div class="register-container">
		<div class="register-title">用户注册</div>
		
		<form id="registerForm" method="post" action="${pageContext.request.contextPath}/admin/huiyuan/register">
			<div class="form-group">
				<label>用户名 *</label>
				<input type="text" name="username" placeholder="请输入用户名" required />
			</div>
			<div class="form-group">
				<label>密码 *</label>
				<input type="password" name="password" placeholder="请输入密码（至少6位）" required />
			</div>
			<div class="form-group">
				<label>确认密码 *</label>
				<input type="password" name="confirmPassword" placeholder="请确认密码" required />
			</div>
			<div class="form-group">
				<label>手机号</label>
				<input type="tel" name="mobile" placeholder="请输入手机号（可选）" />
			</div>
			<div class="form-group">
				<label>邮箱</label>
				<input type="email" name="email" placeholder="请输入邮箱（可选）" />
			</div>
			<button type="submit" class="btn">注册</button>
		</form>
		
		<div class="login-link">已有账号？<a href="${pageContext.request.contextPath}/e/index.jsp">立即登录</a></div>
		<div class="error-message" id="errorMsg"></div>
	</div>

	<script type="text/javascript">
		document.getElementById('registerForm').addEventListener('submit', function(e) {
			e.preventDefault();
			
			var username = document.querySelector('input[name="username"]').value.trim();
			var password = document.querySelector('input[name="password"]').value;
			var confirmPassword = document.querySelector('input[name="confirmPassword"]').value;
			var mobile = document.querySelector('input[name="mobile"]').value;
			var email = document.querySelector('input[name="email"]').value;
			
			if (!username) {
				document.getElementById('errorMsg').textContent = '请输入用户名';
				return;
			}
			
			if (password.length < 6) {
				document.getElementById('errorMsg').textContent = '密码长度不能少于6位';
				return;
			}
			
			if (password != confirmPassword) {
				document.getElementById('errorMsg').textContent = '两次输入的密码不一致';
				return;
			}
			
			var params = 'username=' + encodeURIComponent(username);
			params += '&password=' + encodeURIComponent(password);
			if (mobile) {
				params += '&mobile=' + encodeURIComponent(mobile);
			}
			if (email) {
				params += '&email=' + encodeURIComponent(email);
			}
			
			fetch('${pageContext.request.contextPath}/admin/huiyuan/register', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				body: params,
				credentials: 'include'
			}).then(function(response) {
				return response.json();
			}).then(function(data) {
				if (data.stateCode > 0) {
					alert('注册成功！即将跳转到首页');
					window.location.href = '${pageContext.request.contextPath}/e/index.jsp';
				} else {
					document.getElementById('errorMsg').textContent = data.des || '注册失败';
				}
			}).catch(function(error) {
				document.getElementById('errorMsg').textContent = '注册失败，请重试';
			});
		});
	</script>
</body>
</html>