<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${ !empty signInCheck }"><p><c:out value="${ signInCheck }" /></p></c:if>
<form id="login" action="connect" method="post">
                <input name="username" type="text" placeholder="username" value="${ username }">
                <input name="password" type="password" placeholder="password" value="${ password }">
                <input name="password2" type="password" placeholder="retype password">
                <input name="submit" type="submit" value="save">
            </form>

</body>
</html>