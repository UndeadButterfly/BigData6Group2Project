const replyListContainer = document.getElementById("replyListContainer");

function loadRegisterForm(replyNo) {
    const replyRegisterForm = document.forms["replyRegisterForm"];
    const cloneForm = replyRegisterForm.cloneNode(true);
    cloneForm.onsubmit = function (e) {
        registerReply(e, cloneForm);
    }
    cloneForm.fkReplyNo.value = replyNo;
    let selector = "reReplyRegisterContainer" + replyNo;
    const reReplyRegisterContainer = document.getElementById(selector);
    reReplyRegisterContainer.append(cloneForm);
}

async function removeReply(replyNo) {
    let url = "/reply/delete.do?replyNo=" + replyNo;
    const resp = await fetch(url, {method: "DELETE"});
    if (resp.status === 200) {
        const json = await resp.json();
        if (json.state == 1) {
            await loadReplyList();
            alert("삭제 성공!");
        } else {
            alert("삭제할 레코드가 없습니다.");
        }
    } else {
        alert(`삭제 실패! ${resp.status}`);
    }
}

async function modifyReply(formNode) {
    const formData = new FormData(formNode); //queryString 작성 필요 없음 (단!! multipart/form-data 만)
    let url = "/reply/modify.do";
    const resp = await fetch(url, {method: "PUT", body: formData}); //api, 비동기식
    if (resp.status === 200) {
        const json = await resp.json();
        if (json.state == 1) {
            await loadReplyList();
            alert("수정 성공!");
        } else {
            alert("이미 삭제된 레코드 입니다.");
        }
    } else {
        alert(`서버 오류! (${resp.status})`);
    }
}

function init() {
    const pageBtns = replyListContainer.querySelectorAll(".uk-pagination a");
    const replyRegisterForm = document.forms["replyRegisterForm"];
    pageBtns.forEach((btn) => {
        btn.onclick = async (e) => {
            e.preventDefault();
            let btnUrl = e.target.href;
            let queryString = btnUrl.split("?")[1];
            let url = "/reply/[[${uploadNo}]]/list.do?" + queryString;
            const resp = await fetch(url);
            const html = await resp.text();
            replyListContainer.innerHTML = html;
            init();
        }
    });
    replyRegisterForm.onsubmit = function (e) {
        registerReply(e, replyRegisterForm);
    };
}

init();

async function registerReply(e, replyRegisterForm) {
    e.preventDefault();
    let url = "/reply/register.do";
    const formData = new FormData(replyRegisterForm);
    // let queryString="?userId="+replyRegisterForm.userId.value+"&title="+replyRegisterForm.title.value;
    //post 통신으로 하더라도 fetch 로 통신시 파라미터를 queryString 으로 생성해서 통신해야 한다!!
    //multipart/form-data 로 전달할 때는 new FormData(formNode)를 넣으면 자동으로 파라미터 생성
    //Spring Controller 는 blob 을 자동으로 Text 파라미터 처리를 한다.
    const resp = await fetch(url, {method: "POST", body: formData});
    const json = await resp.json();
    console.log(json)
    if (json.state == 1) {
        await loadReplyList();
    }
}

async function loadReplyList() {
    const resp = await fetch("/reply/[[${uploadNo}]]/list.do");
    const html = await resp.text();
    replyListContainer.innerHTML = html;
    init();
}

async function loadReplyModifyForm(replyNo) {
    let url = "/reply/modify.do?replyNo=" + replyNo;
    let selector = "replyContainer" + replyNo;
    const replyContainer = document.getElementById(selector);
    const resp = await fetch(url);
    if (resp.status == 200) {
        let html = await resp.text();
        replyContainer.innerHTML = html;
    }
}
