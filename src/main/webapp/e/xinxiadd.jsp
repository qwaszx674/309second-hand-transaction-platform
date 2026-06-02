<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="import.jsp" %>
<!DOCTYPE html >
<html>
<head>
	<title>发布信息</title>

	<link href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
	<link href="${pageContext.request.contextPath}/e/css/box.all.css" type="text/css"></link>
	<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.3/dist/vue-resource.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/element-ui@2.15.14/lib/theme-chalk/index.css" rel="stylesheet"/>
	<script src="https://cdn.jsdelivr.net/npm/element-ui@2.15.14/lib/index.js"></script>
	<style>
		.wrap { width: 1200px; margin: 0 auto; padding: 20px 0; }
		.line-title { margin-bottom: 20px; color: #666; }
		.line-title a { color: #e74c3c; text-decoration: none; }
		.main-content { background: #fff; padding: 20px; border-radius: 8px; }
		.grid { width: 100%; border-collapse: collapse; }
		.grid td { padding: 12px; border: 1px solid #eee; }
		.grid td:first-child { text-align: right; background: #f9f9f9; }
		.input-txt { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; }
		textarea { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; resize: vertical; }
	</style>
</head>
<body>
<div id="app">
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
					<li><a href="${pageContext.request.contextPath}/e/huiyuan/modifyinfo.jsp">会员中心</a></li>
				</ul>
			</nav>
		</div>
	</header>

	<div style="min-height: 600px;" class="wrap">
		<div class="line-title">
			当前位置：<a href="${pageContext.request.contextPath}/e/index.jsp">首页</a> &gt;&gt;<span id="lanmuName">分类</span>/<span id="subtypeName">子分类</span>
		</div>

		<div id="loginContent" class="main-content" style="display: none;">
			<table class="grid" width="100%">
				<tr>
					<td width="10%" align="right">标题:</td>
					<td width="*">
						<input id="title" name="title" placeholder="请输入标题" class="input-txt" type="text"/>
					</td>
				</tr>
				<tr>
					<td align="right">标签:</td>
					<td>
						<select id="tagid" class="input-txt">
							<option value="">请选择标签</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">介绍:</td>
					<td colspan="3">
						<textarea id="dcontent" rows="10" cols="80" placeholder="请输入内容"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<button id="submitBtn" class="btn-submit">提交</button>
					</td>
				</tr>
			</table>
		</div>

		<div id="notLoginContent" style="font-size:18px;padding:20px 120px;">
			登录后才能发布帖子 ,<a href="${pageContext.request.contextPath}/e/index.jsp" style="color:#e9ab32;">登录</a>
		</div>
	</div>
</div>
</body>
</html>
<div class="fn-clear"></div>

<style>
	.btn-submit { padding: 10px 30px; background: #e74c3c; color: #fff; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; }
	.btn-submit:hover { background: #c0392b; }
</style>

<script type="text/javascript">
	var hostHead = '${pageContext.request.contextPath}';
	var isLoggedIn = "${sessionScope.huiyuan.username}" != "";
	var huiyuanId = "${sessionScope.huiyuan.id}";
	var lmid = "${param.lmid}";
	var subtypeid = "${param.subtypeid}";

	window.onload = function() {
		if (isLoggedIn) {
			document.getElementById('loginContent').style.display = 'block';
			document.getElementById('notLoginContent').style.display = 'none';
			loadTags();
			loadLanmu();
			document.getElementById('submitBtn').addEventListener('click', submitHandler);
		} else {
			document.getElementById('loginContent').style.display = 'none';
			document.getElementById('notLoginContent').style.display = 'block';
		}
	};

	function loadLanmu() {
		if (!lmid) return;
		fetch(hostHead + '/admin/lanmu/info', {
			method: 'POST',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: 'id=' + lmid,
			credentials: 'include'
		}).then(function(response) {
			return response.json();
		}).then(function(data) {
			if (data && data.data) {
				document.getElementById('lanmuName').textContent = data.data.name || '分类';
				if (data.data.subtypes && data.data.subtypes.length > 0) {
					var found = data.data.subtypes.find(function(c) { return c.id == subtypeid; });
					document.getElementById('subtypeName').textContent = (found ? found.name : data.data.subtypes[0].name) || '子分类';
				}
			}
		}).catch(function(err) {
			console.log('加载分类失败:', err);
		});
	}

	function loadTags() {
		fetch(hostHead + '/admin/stag/list', {
			method: 'POST',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: 'ispaged=-1',
			credentials: 'include'
		}).then(function(response) {
			return response.json();
		}).then(function(data) {
			if (data && data.data) {
				var select = document.getElementById('tagid');
				data.data.forEach(function(item) {
					var option = document.createElement('option');
					option.value = item.id;
					option.textContent = item.name;
					select.appendChild(option);
				});
			}
		}).catch(function(err) {
			console.log('加载标签失败:', err);
		});
	}

	function submitHandler() {
		var title = document.getElementById('title').value.trim();
		var tagid = document.getElementById('tagid').value;
		var dcontent = document.getElementById('dcontent').value.trim();

		if (!title) {
			alert('请输入标题');
			return;
		}

		var params = 'name=' + encodeURIComponent(title);
		params += '&typeid=' + encodeURIComponent(lmid || '1');
		params += '&subtypeid=' + encodeURIComponent(subtypeid || '1');
		params += '&tupian=/upload/nopic.jpg';
		params += '&clickcount=0';
		params += '&pubren=' + encodeURIComponent(huiyuanId);
		params += '&state=1';
		params += '&tuijian=1';
		params += '&tagid=' + encodeURIComponent(tagid || '1');
		params += '&jieshao=' + encodeURIComponent(dcontent);
		params += '&hyjia=0';
		params += '&kucun=1';
		params += '&danwei=件';
		params += '&spstate=2';

		fetch(hostHead + '/admin/shangpin/save', {
			method: 'POST',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: params,
			credentials: 'include'
		}).then(function(response) {
			return response.json();
		}).then(function(data) {
			if (data && data.stateCode > 0) {
				alert('发布成功');
				window.location.href = hostHead + '/e/index.jsp';
			} else {
				alert(data && data.des || '发布失败');
			}
		}).catch(function(err) {
			console.error('发布错误:', err);
			alert('发布失败');
		});
	}
</script>