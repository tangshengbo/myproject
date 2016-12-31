<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/user/register.css"/>

</head>
<body>
<div id="register">
    <h1>Register</h1>
    <form  action="${ctx}/user/register" method="post">
        <input type="text" required="required" placeholder="用户名" name="userName"/>
        <input type="password" required="required" placeholder="密码" name="userPwd"/>
        <button class="but" type="submit">注册</button>
    </form>
</div>
</body>
</html>