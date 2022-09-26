<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Starting page</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/custom.css">
</head>
<body class="text-center main-image">
<div class="container-fluid">
    <div class="row">
        <div class="col-7 mx-auto">
            <h1 class="white-text">Bug size adventures</h1>
            <form action="start" method="post">
                <div class="col-auto">
                    <input type="text" class="form-control" id="inputLogin" placeholder="Login" name="playerName">
                    <button type="submit" class="button-68">PLay</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
