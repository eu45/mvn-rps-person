<%--
  Created by IntelliJ IDEA.
  User: 16085
  Date: 2020/8/21
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>书城登录</title>
    <%@include file="/view/comman/header.jsp"%>
  </head>
  <body>
    <div id="login_header">
      <img class="logo_img" alt="" src="static/img/logo.gif">
    </div>
    <div class="login_banner">
      <div class="l_content">
        <span class="login_word">欢迎登陆</span>
      </div>
      <div id="content">
        <div class="login_form">
          <div class="login_box">
            <div class="tit">
              <h1>书城会员</h1>
              <a href="http://localhost:8010/BookStore/view/User/regist.jsp">注册</a>
            </div>
            <div class="msg_cont">
              <b></b>
              <span class="errorMsg">
                ${empty requestScope.msg?"请输入用户名和密码":requestScope.msg}
              </span>
            </div>
            <div class="form">
              <form action="register?action=first" method="post">
                <input type="hidden" name="action" value="first"/>
                <label>用户名称：</label>
                <input class="itxt" type="text" placeholder="请输入用户名"
                        autocomplete="off" tabindex="1" name="username"
                        value="${requestScope.username}"/>
                <br/>
                <br/>
                <label>用户密码:</label>
                <input class="itxt" type="password" placeholder="请输入密码"
                        autocomplete="off" tabindex="1" name="password"/>
                <br/>
                <br/>
                <input type="submit" value="登录" id="sub_btn"/>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  <%@include file="/view/comman/footer.jsp"%>
  </body>
</html>
