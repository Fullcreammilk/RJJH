<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript" src="/js/jquery-3.1.1.min.js"></script>
<head>
    <title>这是一个测试页面</title>
    <table>
        <th>
            <tr>姓名</tr>
            <tr>年纪</tr>
        </th>
        <c:forEach var="user" items="${list}" >
            <td>
                <tr>${user.name}</tr>
                <tr>${user.age}</tr>
            </td>
        </c:forEach>
    </table>
</head>
<body>

</body>
</html>
