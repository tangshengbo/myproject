<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>

<SCRIPT LANGUAGE="JavaScript">
    function Is360()
    {
        if (window.navigator.userAgent.toLowerCase().indexOf("360se")>=1)
//如果浏览器为360
        {
            alert("别骗我了，这是360浏览器！");
        }
        if(window.external&&window.external.twGetRunPath)
        {
//获取路径
            var r=external.twGetRunPath();
            if(r&&r.toLowerCase().indexOf("360se")>-1)
                alert("别骗我了，这是360浏览器！");
        }
    }
</script>
<head runat="server">
    <title>判断是否为360浏览器</title>
</head>
<body onload="Is360();">
<form id="form1" runat="server">
    <div>
        不要以为改了userAgent，我们就看不出来了！真相只有一个！
    </div>
</form>
</body>
</html>