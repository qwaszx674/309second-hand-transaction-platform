<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
    Vue.component('login-dlg', {
        props: ['show'],
        data: function() {
            return {
                accountname: '',
                password: '',
                loading: false
            }
        },
        template: `
            <el-dialog title="用户登录" :visible.sync="show" width="400px">
                <el-form :model="form" label-width="80px">
                    <el-form-item label="用户名">
                        <el-input v-model="accountname" placeholder="请输入用户名"></el-input>
                    </el-form-item>
                    <el-form-item label="密码">
                        <el-input type="password" v-model="password" placeholder="请输入密码"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="show = false">取消</el-button>
                    <el-button type="primary" @click="handleLogin" :loading="loading">登录</el-button>
                </div>
                <div style="text-align: center; margin-top: 10px;">
                    <a href="${pageContext.request.contextPath}/e/register.jsp">注册新账号</a>
                </div>
            </el-dialog>
        `,
        methods: {
            handleLogin: function() {
                if (!this.accountname) {
                    this.$message.error('请输入用户名');
                    return;
                }
                if (!this.password) {
                    this.$message.error('请输入密码');
                    return;
                }
                
                this.loading = true;
                this.$http.post('admin/huiyuan/login', {
                    accountname: this.accountname,
                    password: this.password
                }).then(res => {
                    this.loading = false;
                    if (res.data.stateCode > 0) {
                        this.$message.success('登录成功');
                        this.show = false;
                        window.location.reload();
                    } else {
                        this.$message.error(res.data.des || '登录失败');
                    }
                }).catch(err => {
                    this.loading = false;
                    this.$message.error('登录失败');
                });
            }
        }
    });
</script>