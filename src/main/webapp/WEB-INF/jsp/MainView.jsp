<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>URL KEEP</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/style.css" rel="stylesheet">
</head>

<body>
<header>
    <p class="header_title_text">URL KEEP</p>
</header>

<section>
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-6">
                <a href="/user/links" role="button" class="btn btn-primary">URL 등록</a>

                <span>${userName}님 안녕하세요 </span><a href="/logout" class="btn btn-info active" role="button">Logout</a>
            </div>
        </div>
    </div>
    <br/>

    <div class="link_table">
        <ul class="title">
            <li class="col no">NO</li>
            <li class="url">URL</li>
            <li class="content">내용</li>
            <li class="time">최종수정시간</li>
        </ul>

        <c:forEach var="link" items="${links}" varStatus="linkStatus">
            <ul class="link">
                <li class="col no"><a href="/user/links/${link.id}">${linkStatus.count}</a></li>
                <li class="url"><a href=${link.url}>${link.url}</a></li>
                <li class="content">${link.content}</li>
                <li class="time">${link.convertedModifyDate}</li>
            </ul>
        </c:forEach>
    </div>
</section>

</body>
</html>
