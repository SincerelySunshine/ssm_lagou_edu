<%--
  Created by IntelliJ IDEA.
  User: 20345
  Date: 2022/9/21
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>JUMP WORDS</title>
</head>
<body>
<div id="">
    <h1 id="show"></h1>
</div>
<script type="text/javascript">
    var str="自从和你相识以来，我平静的心湖再也无法平静了。你的芳姿，你的丽影，你的笑靥，使我难以忘怀，我已被你美妙的风姿深深吸引！";
    var len=str.length;
    var i=1;
    function showword(){
        document.getElementById("show").innerHTML=str.substr(0,i++)+'_';
        if(i>len){
            clearInterval("done");
        }
    }
    var done=setInterval("showword()",500);
</script>
</body>
</html>
