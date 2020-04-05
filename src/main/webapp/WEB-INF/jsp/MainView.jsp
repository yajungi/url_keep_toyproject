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
        <div id="count_view">
            <span>현재 저장된 링크 개수 ${count}개</span>
        </div>
    </div>
    <br/>

    <div class="link_table">
        <ul class="link" id="links_list">
            <li class="col no">NO</li>
            <li class="url">URL</li>
            <li class="content">내용</li>
            <li class="time">최종수정시간</li>

            <c:forEach var="link" items="${links}" varStatus="linkStatus">
                <li class="col no"><a href="/user/links/${link.id}">${linkStatus.count}</a></li>
                <li class="url"><a href=${link.url}>${link.url}</a></li>
                <li class="content">${link.content}</li>
                <li class="time">${link.convertedModifyDate}</li>
            </c:forEach>
        </ul>
    </div>

    <input type="hidden" id="input_link_count" value="${count}"/>
    <input type="hidden" id="input_page" value="0"/>
</section>

<footer>
    <div class="button_section">
        <button type="button" id="next_button" class="button_direction" style="display:none">더보기</button>
    </div>
</footer>

<script type="rv-template" id="link_template">
    <li class="col no"><a href="/user/links/{link.id}">{link.count}</a></li>
    <li class="url"><a href={link.url}>{showUrl}</a></li>
    <li class="content">{link.content}</li>
    <li class="time">{link.convertedModifyDate}</li>
</script>

<script type="text/javascript" src="/resources/js/main.js"></script>
</body>
</html>
