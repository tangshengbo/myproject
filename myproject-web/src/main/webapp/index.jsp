<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>

<head>
  <title>显示用户信息</title>

  <style type="text/css">
    table,td{
      border: 1px solid;
      border-collapse: collapse;
    }
  </style>

</head>
<%

    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
    System.out.println(userDetails.getAuthorities()+"\t"+userDetails.getPassword()+"\t"+userDetails.getUsername());


%>
<body>
<div>
  <form class="f1" action="${ctx}/courses/add" method="post" >
    <fieldset >
      <legend>表单的注册 <sec:authentication property="name"/> </legend>
      <table width=100% >
        <tbody>
        <tr ><td class=“left” width=40% align="right"><label for="t1">姓 名：</label></td>
          <td class="right"><input type="text" id="t1" name="name"></td>
        </tr>
        <tr><td class=“left” width=40% align="right"><label for="Password1">Money：</label></td>
          <td class="right"><input id="Password1" name="money" /></td>
        </tr>
        <tr><td class=“left” width=40% align="right"><label for="e1">id：</label></td>
          <td class="right"><input id="e1" name="id" ></td>
        </tr>
        <tr><td class=“left” width=40% align="right"><label for="1">性 别：</label></td>
          <td class="right"><input type="radio" id="1" name="ssex" value="nan" />男<!-- name设置成一样的就行了-->
            <input type="radio" id="2" name="ssex" value="nv" />女
          </td>
        </tr>
        <tr><td class=“left” width=40% align="right">地 区：</td>
          <td><select id="selc" name="place">
            <option value="quanzhou">泉州</option>
            <option value="xiamen">厦门</option>
            <option value="zhangzhou" >漳州</option>
          </select>
          </td>
        </tr>
        <tr><td class=“left” width=40% align="right"><label for="txtarea">简 介：</label></td>
          <td><textarea id="txtarea">${pageContext.request.contextPath},${pageContext.request.servletPath}</textarea></td>
        </tr>
        <tr><td class=“left” width=40% align="right">兴 趣：</td>
          <td><input type="checkbox" id="cbox1" name="dushu" value="c1">读书
            <input type="checkbox" id="cbox2" name="yundong" value="c2">运动
            <input type="checkbox" id="cbox3"name="chihe" value="c3">吃喝
          </td>
        </tr>
        <tr><td class=“left” width=40% align="right">上 传：</td>
          <td> <input type="file" id="f1" name="file" value="File1" /></td>
        </tr>
        <tr><td class=“left” width=40% align="right" rowspan=2>
          <input id="Submit1" type="submit" value="提 交" />
        </td>
          <td> <input id="Reset1" type="reset" value="重 置" />
          </td>
        </tr>
        </tbody>
      </table>
    </fieldset>
  </form>
</div>

</table>
</body>
</html>