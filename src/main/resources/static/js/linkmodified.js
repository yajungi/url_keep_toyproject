window.addEventListener("DOMContentLoaded", () => {
    const id = document.querySelector("#input_id").value;

    const STATUS_SUCCESS = 200;
    const STATUS_BAD_REQUEST = 400;

    const xmlHttpRequest = new XMLHttpRequest();

    document.querySelector("#url_form").addEventListener("submit", () => {
        let linkFormJson = {};
        linkFormJson.url = document.querySelector("#input_link").value;
        linkFormJson.content = document.querySelector("#input_description").value;

        xmlHttpRequest.onreadystatechange = () => {
            if (xmlHttpRequest.status === STATUS_SUCCESS && xmlHttpRequest.readyState === xmlHttpRequest.DONE) {
                alert("LINK가 정상적으로 수정되었습니다.");
            } else if(xmlHttpRequest.status === STATUS_BAD_REQUEST && xmlHttpRequest.readyState === xmlHttpRequest.DONE) {
                alert("오류가 발생했습니다. 잠시후에 다시 시도해주세요.");
            }
        };

        xmlHttpRequest.open("PUT", `/api/links/${id}`, false);
        xmlHttpRequest.setRequestHeader("Content-type", "application/json; charset=utf-8");
        xmlHttpRequest.send(JSON.stringify(linkFormJson));
    });

    document.querySelector("#url_remove_button").addEventListener("click", () => {
        xmlHttpRequest.onreadystatechange = () => {
            if (xmlHttpRequest.status === STATUS_SUCCESS && xmlHttpRequest.readyState === xmlHttpRequest.DONE) {
                alert("LINK가 정상적으로 삭제되었습니다.");
                window.location.href = "/";
            } else if(xmlHttpRequest.status === STATUS_BAD_REQUEST && xmlHttpRequest.readyState === xmlHttpRequest.DONE) {
                alert("오류가 발생했습니다. 잠시후에 다시 시도해주세요.");
            }
        };

        xmlHttpRequest.open("DELETE", `/api/links/${id}`, false);
        xmlHttpRequest.setRequestHeader("Content-type", "application/json; charset=utf-8");
        xmlHttpRequest.send();
    });
})