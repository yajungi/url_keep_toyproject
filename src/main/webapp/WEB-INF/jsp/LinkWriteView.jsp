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
    <p class="header_title_text">URL 등록</p>
</header>

<section>
    <div class="link_form_middle_section">
        <form id="url_form">
            <div class="link_form_middle_section_layer">
                <p>
                    <span class="link">
                        <label for="input_link">URL LINK</label>
                    </span>
                </p>
                <input type="url" placeholder="https://www.naver.com/" id="input_link" name="link" required/>
            </div>
            <div class="link_form_middle_section_layer">
                <p>
                    <span class="link">
                        <label for="input_description">Description</label>
                    </span>
                </p>
                <input type="text" placeholder="네이버 홈페이지" id="input_description" name="description" required/>
            </div>
            <div class="link_form_middle_section_layer">
                <a href="/" id="link_form_button_main"><span id="link_form_button_main_font">&lt;이전</span></a>
                <button type="reset" class="link_form_button">내용 지우기</button>
                <button type="submit" id="submit_button" form="url_form" class="link_form_button">등록</button>
            </div>
        </form>
    </div>
</section>

<script type="text/javascript" src="/resources/js/linkwrite.js"></script>
</body>
</html>