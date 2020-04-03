const SIZE = 10;
const STATUS_OK = 200;

window.addEventListener("DOMContentLoaded", () => {
    const linkCount = document.querySelector("#input_link_count").value;
    const next_button = document.querySelector("#next_button");

    const currentLinksList = document.querySelector("#links_list");

    if (linkCount >= SIZE) {
        next_button.style.display = "block";
    }

    let inputPage = document.querySelector("#input_page");

    document.querySelector("#next_button").addEventListener("click", () => {
        let page = parseInt(inputPage.value) + 1;

        const xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.open("GET", `/api/links?page=${page}`);
        xmlHttpRequest.setRequestHeader("Content-type", "application/json; charset=utf-8");

        xmlHttpRequest.onload = () => {
            if (xmlHttpRequest.status === STATUS_OK && xmlHttpRequest.readyState === xmlHttpRequest.DONE) {
                inputPage.value = page;

                if (addLink(JSON.parse(xmlHttpRequest.responseText), page) < SIZE) {
                    next_button.style.display = "none";
                }
            }
        };

        xmlHttpRequest.send();
    });

    function addLink(linksJsonResponse, page) {
        const linksList = linksJsonResponse.links;
        const linkTemplate = document.querySelector("#link_template").innerHTML;

        let successAddCount = 0;

        for (let [index, link] of linksList.entries()) {
            const newLink = linkTemplate.replace("{link.id}", link.id)
                .replace("{link.count}", parseInt(page) * parseInt(SIZE) + parseInt(index) + 1)
                .replace("{link.url}", link.url)
                .replace("{showUrl}", link.url)
                .replace("{link.content}", link.content)
                .replace("{link.convertedModifyDate}", link.convertedModifyDate);

            successAddCount++;
            currentLinksList.innerHTML += newLink;
        }

        return successAddCount;
    }
});