<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/custom.css">
    <script src="js/bootstrap.js"></script>
    <script src="js/dialog.js"></script>
    <title>Location: ${locationName}</title>
</head>
<body>
<div class="container text-center">
    <div class="row">
        <div class="col-3">
            <img class="img-fluid" src="${pageContext.request.contextPath}/images/${avatar}">
            <h3>Username: ${userName}</h3>
            <h3>Inventory: </h3>
            <lu class="list-group list-group-flush">
                <c:forEach items="${inventory}" var="item">
                    <li class="list-group-item">${item.getName()} </li>
                </c:forEach>
            </lu>

            <h3>Quests :</h3>
            <lu id="user-quests" class="list-group list-group-flush">
                <c:forEach items="${activeQuests}" var="quest">
                    <li id="${quest.getId()}" class="list-group-item">${quest.getDescription()} (In progress)</li>
                </c:forEach>
                <c:forEach items="${finishedQuests}" var="quest">
                    <li id="${quest.getId()}" class="list-group-item">${quest.getDescription()} (Done)</li>
                </c:forEach>
            </lu>
        </div>
        <div class="col-6">
            <h1>Location: ${locationName}</h1>
            <h5 class="text-italics">Description: ${locationDescription}</h5>
            <img class="img-fluid" src="${pageContext.request.contextPath}/images/${locationImage}">
        </div>
        <div class="col-3">
            <div class="d-flex align-items-start flex-column">
                <H3 class="mb-auto p-2">Items:</H3>
                <ul class="list-group list-group-flush mb-auto p-2">
                    <c:forEach items="${items}" var="item">
                        <li class="list-group-item">
                            <form action="${pageContext.request.contextPath}/location" method="post">
                                <input type="hidden" name="itemId" value="${item.getId()}">
                                <button class="btn btn-warning" type="submit">${item.getName()}</button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>

                <h3 class="p-2 margin-top180">
                    NPC:
                </h3>
                <c:forEach items="${npcs}" var="npc">
                    <button id="dialogueStarter" type="button" class="btn btn-primary p-2" data-bs-toggle="modal"
                            data-bs-target="#exampleModal">
                        <div style="visibility: hidden;" id="dialogStarterValue">${npc.getDialogStartingMessage()}</div>
                        <div style="visibility: hidden;" id="npcId">${npc.getId()}</div>
                        Talk to ${npc.getName()}
                    </button>


                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Talking to ${npc.getName()}</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div id="dialogue-text" class="modal-body">
                                    ...
                                </div>
                                <div class="modal-footer">
                                    <button type="button" id="close-dialog-button" class="btn btn-secondary"
                                            data-bs-dismiss="modal">
                                        Close
                                    </button>
                                    <button id="answer-button1" type="button" class="btn btn-primary">Answer 1</button>
                                    <div style="visibility: hidden;" id="answer-message-id1"></div>
                                    <button id="answer-button2" type="button" class="btn btn-primary">Answer 2</button>
                                    <div style="visibility: hidden;" id="answer-message-id2"></div>
                                    <button id="quest-accept-button" type="button" class="btn btn-primary"
                                            data-bs-dismiss="modal">
                                        Accept quest
                                    </button>
                                    <div style="visibility: hidden;" id="quest-id-value"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="d-flex justify-content-center flex-column">
        <div>
            <H3>Available locations:</H3>
            <lu class="d-flex justify-content-center list-group list-group-horizontal">
                <c:forEach items="${locations}" var="location">
                    <li class="list-group-item">
                        <form action="${pageContext.request.contextPath}/location" method="post">
                            <input type="hidden" name="locationId" value="${location.getId()}">
                            <button class="btn btn-success" type="submit">${location.getName()}</button>
                        </form>
                    </li>
                </c:forEach>
            </lu>
        </div>
    </div>
</div>
</body>
</html>