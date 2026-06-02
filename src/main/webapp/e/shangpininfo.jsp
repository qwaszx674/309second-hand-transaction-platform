<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>商品详情 - 二手交易平台</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/box.all.css" type="text/css"></link>
	<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.3/dist/vue-resource.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/element-ui@2.15.14/lib/theme-chalk/index.css" rel="stylesheet"/>
	<script src="https://cdn.jsdelivr.net/npm/element-ui@2.15.14/lib/index.js"></script>
	<style>
		.goods-detail { display: flex; gap: 30px; margin: 20px 0; }
		.goods-images { width: 400px; }
		.goods-images .main-img { width: 100%; height: 400px; object-fit: cover; }
		.goods-images .thumbnails { display: flex; gap: 10px; margin-top: 10px; }
		.goods-images .thumbnails img { width: 80px; height: 80px; object-fit: cover; cursor: pointer; }
		.goods-info { flex: 1; }
		.goods-title { font-size: 24px; font-weight: bold; margin-bottom: 20px; }
		.goods-price { font-size: 32px; color: #e74c3c; margin-bottom: 20px; }
		.goods-meta { margin-bottom: 20px; }
		.goods-meta div { margin-bottom: 10px; }
		.goods-meta label { display: inline-block; width: 80px; color: #666; }
		.goods-desc { margin-top: 20px; }
		.goods-desc h3 { margin-bottom: 10px; }
		.goods-desc p { line-height: 1.8; }
		.action-buttons { margin-top: 30px; }
		.action-buttons button { margin-right: 15px; padding: 12px 30px; font-size: 16px; border: none; border-radius: 4px; cursor: pointer; }
		.btn-favorite { background: #f39c12; color: #fff; }
		.btn-contact { background: #e74c3c; color: #fff; }
		.btn-favorite:hover, .btn-contact:hover { opacity: 0.9; }
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

	<div class="wrap">
		<div v-if="shangpin" class="goods-detail">
			<div class="goods-images">
				<img :src="hostHead + (shangpin.images && shangpin.images.length > 0 ? shangpin.images[0] : '/upload/banner.jpg')" class="main-img" />
				<div class="thumbnails">
					<img v-for="(img, index) in shangpin.images" :key="index" :src="hostHead + img" @click="changeMainImage(img)" />
				</div>
			</div>
			<div class="goods-info">
				<div class="goods-title">{{shangpin.name}}</div>
				<div class="goods-price">¥{{shangpin.hyjia}}</div>
				<div class="goods-meta">
					<div><label>品牌：</label>{{shangpin.pinpai || '未知'}}</div>
					<div><label>成色：</label>{{getConditionText(shangpin.chengse)}}</div>
					<div><label>分类：</label>{{shangpin.lanmuName || '未分类'}}</div>
					<div><label>地区：</label>{{shangpin.diqu || '未知'}}</div>
					<div><label>发布时间：</label>{{shangpin.createTime || '未知'}}</div>
					<div><label>卖家：</label>{{shangpin.sellerName || '匿名'}}</div>
				</div>
				<div class="goods-desc">
					<h3>商品描述</h3>
					<p>{{shangpin.beizhu || '暂无描述'}}</p>
				</div>
				<div class="action-buttons">
					<button class="btn-favorite" @click="addToFavorite">收藏商品</button>
					<button class="btn-contact" @click="contactSeller">联系卖家</button>
				</div>
			</div>
		</div>
		<div v-else class="no-record-tip">
			<div class="content">
				<i class="fa fa-warning"></i>商品不存在或已下架
			</div>
		</div>
	</div>

	<el-dialog title="留言给卖家" :visible.sync="showContact" width="400px">
		<el-form :model="messageForm" label-width="80px">
			<el-form-item label="留言内容">
				<el-textarea v-model="messageForm.content" rows="4" placeholder="请输入您的留言..."></el-textarea>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button @click="showContact = false">取消</el-button>
			<el-button type="primary" @click="sendMessage" :loading="sending">发送</el-button>
		</div>
	</el-dialog>

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
			shangpin: null,
			showContact: false,
			sending: false,
			messageForm: {
				content: ''
			}
		},
		created() {
			this.loadGoods();
		},
		methods: {
			loadGoods() {
				let id = "${param.id}";
				if (!id) return;
				this.$http.post('admin/shangpin/load', {id: id}).then(res => {
					if (res.data.data) {
						this.shangpin = res.data.data;
						this.shangpin.images = this.shangpin.tupian ? this.shangpin.tupian.split('$;') : [];
					}
				});
			},
			changeMainImage(img) {
				document.querySelector('.main-img').src = this.hostHead + img;
			},
			getConditionText(chengse) {
				if (!chengse) return '未知';
				let map = {'new': '全新', 'likeNew': '9成新', 'good': '8成新', 'normal': '7成新', 'poor': '较差'};
				return map[chengse] || chengse;
			},
			addToFavorite() {
				this.$http.post('admin/shoucang/save', {listingId: this.shangpin.id}).then(res => {
					if (res.data.stateCode > 0) {
						this.$message.success('收藏成功');
					} else {
						this.$message.error(res.data.des || '收藏失败');
					}
				});
			},
			contactSeller() {
				this.showContact = true;
			},
			sendMessage() {
				if (!this.messageForm.content.trim()) {
					this.$message.error('请输入留言内容');
					return;
				}
				this.sending = true;
				this.$http.post('admin/message/send', {
					receiverId: this.shangpin.sellerId,
					listingId: this.shangpin.id,
					content: this.messageForm.content
				}).then(res => {
					this.sending = false;
					if (res.data.stateCode > 0) {
						this.$message.success('留言发送成功');
						this.showContact = false;
						this.messageForm.content = '';
					} else {
						this.$message.error(res.data.des || '发送失败');
					}
				});
			}
		}
	});
</script>
</body>
</html>