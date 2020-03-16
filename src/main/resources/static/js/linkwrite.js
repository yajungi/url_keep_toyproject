window.addEventListener("DOMContentLoaded", () => {
    const CREATE_LINK_URL = "/api/links";
    const STATUS_CREATE = 201;
    const STATUS_BAD_REQUEST = 400;

    document.querySelector("#url_form").addEventListener("submit", () => {
        let linkFormJson = {};
        linkFormJson.url = document.querySelector("#input_link").value;
        linkFormJson.content = document.querySelector("#input_description").value;

        const xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.onreadystatechange = () => {
            if (xmlHttpRequest.status === STATUS_CREATE && xmlHttpRequest.readyState === xmlHttpRequest.DONE) {
                alert("LINK가 정상적으로 등록되었습니다.");
            } else if(xmlHttpRequest.status === STATUS_BAD_REQUEST && xmlHttpRequest.readyState === xmlHttpRequest.DONE) {
                alert("오류가 발생했습니다. 잠시후에 다시 시도해주세요.");
            }
        };

        xmlHttpRequest.open("POST", CREATE_LINK_URL, false);
        xmlHttpRequest.setRequestHeader("Content-type", "application/json; charset=utf-8");
        xmlHttpRequest.send(JSON.stringify(linkFormJson));
    });
})