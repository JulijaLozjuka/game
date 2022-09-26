<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/bootstrap.js"></script>
    <script src="js/dialog.js"></script>
    <title>Location: ${locationName}</title>
</head>
<body>
<h1>Location: ${locationName}</h1>
<h2>Description: ${locationDescription}</h2>
<h2>Username: ${userName}</h2>
<h2>Inventory</h2>
<c:forEach items="${inventory}" var="item">
    <li>
        <div>${item.getName()}</div>
    </li>
</c:forEach>
<H3>Available locations:</H3>
<c:forEach items="${locations}" var="location">
    <li>
        <form action="${pageContext.request.contextPath}/location" method="post">
            <input type="hidden" name="locationId" value="${location.getId()}">
            <button type="submit">${location.getName()}</button>
        </form>
    </li>
</c:forEach>
<H3>Items:</H3>
<ul>
    <c:forEach items="${items}" var="item">
        <li>
            <form action="${pageContext.request.contextPath}/location" method="post">
                <input type="hidden" name="itemId" value="${item.getId()}">
                <button type="submit">${item.getName()}</button>
            </form>
        </li>
    </c:forEach>
</ul>
<h3>
    NPC:
</h3>
<c:forEach items="${npcs}" var="npc">
    <button id="dialogueStarter" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
        <div id="dialogStarterValue">${npc.getDialogStartingMessage()}</div>
        Talk to ${npc.getName()}
    </button>
</c:forEach>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div id="dialogue-text" class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button id="answer-button1" type="button" class="btn btn-primary">Save changes</button>
                <button id="answer-button2" type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>