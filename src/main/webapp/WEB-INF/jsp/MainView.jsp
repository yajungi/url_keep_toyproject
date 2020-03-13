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
            </div>
        </div>
    </div>
    <br/>

    <table class="table table-horizontal table-bordered">
        <thead class="thead-strong">
        <tr>
            <th>NO</th>
            <th>URL</th>
            <th>내용</th>
            <th>최종수정시간</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="link" items="${links.content}" varStatus="linkStatus">
                <tr>
                    <td class="td_no">${linkStatus.count}</td>
                    <td class="td_url">${link.url}</td>
                    <td class="td_content">${link.content}</td>
                    <td>${link.modifyDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>

<script type="text/javascript" src="/resources/js/main.js"></script>
</body>
</html>
