<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>二手交易平台</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
	<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.3/dist/vue-resource.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/element-ui@2.15.14/lib/theme-chalk/index.css" rel="stylesheet"/>
	<script src="https://cdn.jsdelivr.net/npm/element-ui@2.15.14/lib/index.js"></script>
</head>
<body>
<div id="app">
	<header class="site-header">
		<div class="header-top">
			<div class="header-top-wrap">
				<div class="top-nav">
					<c:if test="${not empty sessionScope.huiyuan}">
						<span class="welcome">欢迎, ${sessionScope.huiyuan.username}</span>
						<a href="${pageContext.request.contextPath}/e/huiyuan/modifyinfo.jsp" class="top-link">个人中心</a>
						<a href="${pageContext.request.contextPath}/admin/huiyuan/logout" class="top-link">退出登录</a>
					</c:if>
					<c:if test="${empty sessionScope.huiyuan}">
						<a href="javascript:;" onclick="showLoginDialog()" class="top-link">登录</a>
						<a href="${pageContext.request.contextPath}/e/register.jsp" class="top-link">注册</a>
					</c:if>
				</div>
			</div>
		</div>
		<div class="header-wrap">
			<div class="logo">
				<h1><a href="${pageContext.request.contextPath}/">二手交易平台</a></h1>
			</div>
			<nav class="nav">
				<ul>
					<li><a href="${pageContext.request.contextPath}/e/index.jsp">首页</a></li>
					<li><a href="${pageContext.request.contextPath}/e/shangpinlist.jsp">商品列表</a></li>
					<li><a href="${pageContext.request.contextPath}/e/xinxiadd.jsp">发布信息</a></li>
					<li><a href="${pageContext.request.contextPath}/e/huiyuan/modifyinfo.jsp">会员中心</a></li>
				</ul>
			</nav>
			<div class="search-box">
				<input type="text" id="searchKeyword" placeholder="搜索商品..." />
				<button onclick="doSearch()">搜索</button>
			</div>
		</div>
	</header>
	
	<div v-if="showLogin" class="login-modal">
		<div class="modal-overlay" @click="showLogin = false"></div>
		<div class="modal-content">
			<div class="modal-header">用户登录</div>
			<div class="modal-body">
				<div class="form-group">
					<label>用户名</label>
					<input type="text" ref="usernameInput" placeholder="请输入用户名" />
				</div>
				<div class="form-group">
					<label>密码</label>
					<input type="password" ref="passwordInput" placeholder="请输入密码" />
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn-cancel" @click="showLogin = false">取消</button>
				<button class="btn-login" :disabled="loginLoading" @click="handleLogin">
					<span v-if="loginLoading">登录中...</span>
					<span v-else>登录</span>
				</button>
			</div>
		</div>
	</div>
	
	<div class="banner">
		<img src="${pageContext.request.contextPath}/upload/banner.jpg" width="100%" height="300"/>
	</div>

	<div class="wrap">
		<div class="category-section">
			<div class="section-title">
				<h2>商品分类</h2>
			</div>
			<div class="category-grid">
				<div v-for="cat in categoryList" :key="cat.id" class="category-item">
					<a :href="hostHead + '/e/shangpinlist.jsp?categoryId=' + cat.id">
						<div class="category-icon">📦</div>
						<div class="category-name">{{cat.name}}</div>
					</a>
				</div>
			</div>
		</div>
		<div class="section">
			<div class="section-title">
				<h2>最新商品</h2>
				<a href="${pageContext.request.contextPath}/e/shangpinlist.jsp" class="more">查看更多</a>
			</div>
			<div class="goods-grid">
				<div v-for="item in goodsList" :key="item.id" class="goods-item">
					<a :href="hostHead + '/e/shangpininfo.jsp?id=' + item.id">
						<div class="goods-img">
							<img :src="hostHead + (item.images && item.images.length > 0 ? item.images[0] : '/upload/banner.jpg')" alt=""/>
						</div>
						<div class="goods-name">{{item.name}}</div>
						<div class="goods-price">¥{{item.hyjia}}</div>
					</a>
				</div>
				<div v-if="goodsList.length === 0" class="no-goods">
					暂无商品，请发布商品
				</div>
			</div>
		</div>
	</div>

	<footer class="site-footer">
		<div class="footer-wrap">
			<p>&copy; 2026-309 二手交易平台 版权所有</p>
		</div>
	</footer>
</div>

<script type="text/javascript">
	Vue.http.options.root = '${pageContext.request.contextPath}';
	Vue.http.options.emulateJSON = true;
	Vue.http.options.xhr = {withCredentials: true};

	var vm = new Vue({
		el: "#app",
		data: {
			hostHead: '${pageContext.request.contextPath}',
			showLogin: false,
			loginLoading: false,
			goodsList: [],
			categoryList: []
		},
		created() {
			this.loadGoods();
			this.loadCategories();
		},
		methods: {
			loadCategories() {
				this.$http.post('admin/lanmu/list', {
					ispaged: '-1',
					parentid: 0
				}).then(res => {
					if (res.data && res.data.data) {
						this.categoryList = res.data.data;
					}
				});
			},
			loadGoods() {
				this.$http.post('admin/shangpin/pagelist', {
					currentpageindex: 1,
					pagesize: 6,
					spstate: 2,
					state: 1
				}).then(res => {
					if (res.data && res.data.data && res.data.data.list) {
						res.data.data.list.forEach(item => {
							item.images = item.tupian ? item.tupian.split('$;') : [];
						});
						this.goodsList = res.data.data.list;
					}
				});
			},
			handleLogin() {
				var username = document.querySelector('.login-modal input[type="text"]').value.trim();
				var password = document.querySelector('.login-modal input[type="password"]').value.trim();
				
				if (!username) {
					alert('请输入用户名');
					return;
				}
				if (!password) {
					alert('请输入密码');
					return;
				}
				
				this.loginLoading = true;
				var self = this;
				fetch(this.hostHead + '/admin/huiyuan/login', {
					method: 'POST',
					headers: {
						'Content-Type': 'application/x-www-form-urlencoded'
					},
					body: 'username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password),
					credentials: 'include'
				}).then(function(response) {
					return response.json();
				}).then(function(data) {
					self.loginLoading = false;
					if (data.stateCode > 0) {
						alert('登录成功');
						self.showLogin = false;
						window.location.reload();
					} else {
						alert(data.des || '登录失败');
					}
				}).catch(function(err) {
					self.loginLoading = false;
					alert('登录失败');
				});
			}
		}
	});
	
	function showLoginDialog() {
		vm.showLogin = true;
	}
	
	function doSearch() {
		var keyword = document.getElementById('searchKeyword').value;
		if (keyword) {
			window.location.href = '${pageContext.request.contextPath}/e/shangpinlist.jsp?keyword=' + encodeURIComponent(keyword);
		}
	}
</script>
</body>
</html>