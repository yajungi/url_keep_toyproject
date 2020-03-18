window.addEventListener("DOMContentLoaded", () => {
    const CREATE_LINK_URL = "/api/links";
    const STATUS_CREATE = 201;
    const STATUS_BAD_REQUEST = 400;
    const STATUS_FORBIDDEN = 403;

    document.querySelector("#submit_button").addEventListener("click", () => {
        let linkFormJson = {};
        linkFormJson.url = document.querySelector("#input_link").value;
        linkFormJson.content = document.querySelector("#input_description").value;

        const xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.open("POST", CREATE_LINK_URL);
        xmlHttpRequest.setRequestHeader("Content-type", "application/json; charset=utf-8");

        xmlHttpRequest.onload = () => {
            if (xmlHttpRequest.status === STATUS_CREATE && xmlHttpRequest.readyState === xmlHttpRequest.DONE) {
                alert("LINK가 정상적으로 등록되었습니다.");
                window.location.href = "/";
            } else if(xmlHttpRequest.status === STATUS_BAD_REQUEST && xmlHttpRequest.readyState === xmlHttpRequest.DONE) {
                alert("오류가 발생했습니다. 잠시후에 다시 시도해주세요.");
            } else if(xmlHttpRequest.status === STATUS_FORBIDDEN && xmlHttpRequest.readyState === xmlHttpRequest.DONE) {
                alert("등록 권한이 없습니다. 관리자한테 문의하세요.");
            }
        };

        xmlHttpRequest.send(JSON.stringify(linkFormJson));
    });

    document.querySelector("#clear_button").addEventListener("click", () => {
        document.querySelector("#input_link").value = "";
        document.querySelector("#input_description").value = "";
    });
})