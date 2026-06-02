<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../import.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/box.all.css" type="text/css"></link>
<script src="${pageContext.request.contextPath}/webui/jquery/jquery-1.12.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webui/vue/vue.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webui/vue/vue-resource-1.3.4.js"></script>
<link href="${pageContext.request.contextPath}/webui/vue/elementui/theme-chalk/index.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/webui/vue/elementui/index.js"></script>
<link href="${pageContext.request.contextPath}/admin/css/web2table.css" rel="stylesheet" type="text/css"/>
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

	<div class="wrap">
		<div class="line-title">
			当前位置：<a href="${pageContext.request.contextPath}/e/index.jsp">首页</a> &gt;&gt;修改账户信息
		</div>

		<div class="main">
			<div class="main-content">
				<table class="smart-table" cellspacing="1" border="1" width="100%">
					<tr>
						<td class="tlabel" align="right">用户名:</td>
						<td>{{huiyuan.accountname}}</td>
						<td rowspan="6" colspan="2">
							<img :src="hostHead + huiyuan.touxiang" width="150" height="150" style="border-radius: 50%;"/>
						</td>
					</tr>
					<tr>
						<td class="tlabel" align="right">姓名:</td>
						<td>
							<input name="name" v-model="huiyuan.name" class="input-txt" type="text" />
						</td>
					</tr>
					<tr>
						<td class="tlabel" align="right">身份证号:</td>
						<td>
							<input name="idcardno" v-model="huiyuan.idcardno" class="input-txt" type="text" />
						</td>
					</tr>
					<tr>
						<td class="tlabel" align="right">昵称:</td>
						<td><input name="nickname" v-model="huiyuan.nickname" class="input-txt" type="text" /></td>
					</tr>
					<tr>
						<td class="tlabel" align="right">邮箱:</td>
						<td><input name="email" v-model="huiyuan.email" class="input-txt" type="text" /></td>
					</tr>
					<tr>
						<td class="tlabel" align="right">性别:</td>
						<td>
							<el-radio-group v-model="huiyuan.sex">
								<el-radio label="男">男</el-radio>
								<el-radio label="女">女</el-radio>
							</el-radio-group>
						</td>
					</tr>
					<tr>
						<td class="tlabel" align="right">地址:</td>
						<td><input name="address" v-model="huiyuan.address" class="input-txt" type="text" /></td>
						<td class="tlabel" align="right">移动电话:</td>
						<td><input name="mobile" v-model="huiyuan.mobile" class="input-txt" type="text" /></td>
					</tr>
					<tr>
						<td colspan="4">
							<el-button @click="submitHandler" type="danger">提交</el-button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="fn-clear"></div>
<jsp:include page="bottom.jsp"></jsp:include>

<script type="text/javascript">
	Vue.http.options.root = '${pageContext.request.contextPath}';
	Vue.http.options.emulateJSON = true;
	Vue.http.options.xhr = {withCredentials: true};

	let vm = new Vue({
		el: "#app",
		data() {
			return {
				actiontype: 'save',
				errors: "",
				hostHead: '${pageContext.request.contextPath}',
				"huiyuan": {
					"accountname": "",
					"address": "",
					"email": "",
					"idcardno": "",
					"nickname": "",
					"name": "",
					"sex": "",
					"touxiang": "/upload/nopic.jpg",
					"des": ""
				},
			}
		},
		methods: {
			async load() {
				let id = "${sessionScope.huiyuan.id}"
				if (id != "") {
					this.actiontype = "update";
					this.pageTitle = "编辑会员";
					let url = "admin/huiyuan/load";
					this.$http.post(url, {id: id}).then(res => {
						console.log("res", res);
						if (res != null && res.data != null)
							this.huiyuan = res.data;
					});
				}
			},
			async submitHandler() {
				let defaultOptions = {
					url: "admin/huiyuan/save",
					actionTip: "会员新增成功"
				};
				if (this.actiontype == "update") {
					defaultOptions.url = "admin/huiyuan/update";
					defaultOptions.actionTip = "会员更新成功";
				}
				let params = {...this.huiyuan};
				this.$http.post(defaultOptions.url, params).then(res => {
					if (res.data.stateCode <= 0) {
						this.$message.error(res.data.des);
						return;
					}
					this.$message.success(defaultOptions.actionTip);
					window.location.href = this.hostHead + "/e/huiyuan/accountinfo.jsp";
				});
			}
		},
		created() {
			this.load();
		}
	});
</script>
</body>
</html>