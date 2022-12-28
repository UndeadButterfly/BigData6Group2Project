function dragstart(ev) {
    console.log("dragStart");
    // Change the source element's background color to signify drag has started
    ev.currentTarget.style.border = "dashed";
    // Add the id of the drag source element to the drag data payload so
    // it is available when the drop event is fired
    ev.dataTransfer.setData("text", ev.target.id);
    // Tell the browser both copy and move are possible
    ev.target.classList.add("dragging");
    ev.effectAllowed = "copyMove";
}

function dragover(ev) {
    console.log("dragOver");
    // Change the target element's border to signify a drag over event
    // has occurred
    ev.currentTarget.style.background = "lightblue";
    ev.preventDefault();
}


function drop(ev) {
    console.log("Drop");
    ev.preventDefault();
    // Get the id of drag source element (that was added to the drag data
    // payload by the dragstart event handler)
    var id = ev.dataTransfer.getData("text");
    const dragCopies = document.querySelectorAll(".dragCopy");
    const items = document.querySelectorAll(".item");
    const dests = document.querySelectorAll(".dest");
    const dragBoxes = document.querySelectorAll(".dragBox");
    for (let j = 1; j < dests.length + 1; j++) {
        for (let i = 1; i < items.length + 1; i++) {
            if (id == `src_move${i}` && ev.target.id == `day${j}`) {
                ev.target.appendChild(document.getElementById(id));
            }
        }
    }
    for (let j = 1; j < dests.length + 1; j++) {
        for (let i = 1; i < dragCopies.length + 1; i++) {
            if (id == `src_copy${i}` && ev.target.id == `day${j}`) {
                var nodeCopy = document.getElementById(id).cloneNode(true);
                nodeCopy.id = "newId";
                ev.target.appendChild(nodeCopy);
                let vtype = nodeCopy.getElementsByClassName("transport-card-title")[0].innerText;
                let json = {
                    vehicleNo : null,
                    courseNo : null,
                    vday : null,
                    vorder : null,
                    vtype : vtype,
                    memo : null
                }
                nodeCopy.innerHTML += '<button class="btn btn-warning btn-sm delete m-0" type="button">삭제하기</button>';
                nodeCopy.innerHTML += '<textarea name="memo" rows="2" cols="20" class="overflow-scroll vmemo" placeholder="추가 설명"></textarea>'
                nodeCopy.innerHTML += `<p class="cardJson" style="display: none;">${JSON.stringify(json)}</p>`
                deleteBtn();
            }
        }
    }
}
function deleteBtn() {
    const newIds = document.querySelectorAll("#newId");
    newIds.forEach(newId => {
        console.log(newId + ": 삭제버튼 눌림");
        const deleteBtn = newId.querySelector(".delete");
        console.log("deleteBtn:" + deleteBtn);
        deleteBtn.addEventListener("click", e => {
            newId.remove();
        })
    })
}
function dragend(ev) {
    console.log("dragEnd");
    // Restore source's border
    ev.target.style.border = "solid black";
    // Remove all of the drag data
    ev.target.classList.remove("dragging");
    ev.dataTransfer.clearData();
}

let startdateInput = document.getElementById("planStartDate");
let enddateInput = document.getElementById("planEndDate");
const planDays =document.getElementById("planDays");
const planDuration = document.forms["plannerRegister"].planDuration;
const dayBoxContainer = document.getElementById("dayContainer")
console.log(startdateInput);
let startdate;
let enddate;
let dayDiffer;
startdateInput.onchange=(e)=>{
    dayBoxContainer.innerHTML='';
    startdate = new Date(startdateInput.value);
    console.log(startdate);
    if (enddate!=null && startdate.getTime() <= enddate.getTime()) {
        dayDiffer = datediff(startdate.getTime(),enddate.getTime());
        console.log(dayDiffer);
        planDays.value = dayDiffer+1;
        planDuration.value = dayDiffer+"박"+(dayDiffer+1)+"일";
        makeDayBoxes(dayDiffer+1);
    }
}

enddateInput.onchange=(e)=>{
    dayBoxContainer.innerHTML='';
    enddate = new Date(enddateInput.value);
    console.log(enddate);
    if (startdate!=null && startdate.getTime() <= enddate.getTime()) {
        dayDiffer = datediff(startdate.getTime(),enddate.getTime());
        console.log(dayDiffer);
        planDays.value = dayDiffer+1;
        planDuration.value = dayDiffer+"박"+(dayDiffer+1)+"일";
        makeDayBoxes(dayDiffer+1);
    }
}

function datediff(first,second){
    return Math.round((second-first)/(1000*60*60*24));
}

function makeDayBoxes(days) {
    for (let i = 0; i < days; i++) {
        dayBoxContainer.innerHTML += `
        <div class="dayBoxes">
            <span>day${i+1}</span>
            <div class="dest dragBox" id="day${i+1}"  uk-sortable="group: sortable-group" ondrop="drop(event);" ondragover="dragover(event);">
            </div>
        </div>
        `;
    }
}

const planForm = document.forms["plannerRegister"];
// json object를 controller에 넘겨주기
//onsubmit 일때 {
// course json을 만들어서 courseJson textarea에 입력
// update json을 만들어서 ..
//*****************FIX: dayDiffer가 먹힐지 모르겠다.
planForm.onsubmit=(e)=>{
    const dayBoxes = dayBoxContainer.querySelectorAll(".dragBox");
    const courseTextarea = planForm.courseJson;
    const uploadTextarea = planForm.uploadJson;
    const placeListTextarea = planForm.placeListJson;
    const vehicleListTextarea = planForm.vehicleListJson;

    let courseJson = {
        courseNo:null,
        startdate:startdateInput.value,
        enddate:enddateInput.value,
        duration:dayDiffer+1,
        image:null,
        budget:null,
        uploadNo:null,
        uploadDto:null,
        coursePlaceList:null,
        vehicleList:null
    }
    let uploadJson = {
        uploadNo:null,
        upType:1,
        userId:planForm.userId.value,
        title:planForm.title.value,
        contents:planForm.contents.value,
        postdate:null,
        views:null,
        likes:null,
        hates:null,
        reports:null,
        upstate:null
    }
    let vehicleList=[];
    let coursePlaceList=[];
    let day=1;
    const newIds = document.querySelectorAll("#newId");
    dayBoxes.forEach(dayBox=> {
        const cards = dayBox.querySelectorAll(".card");
        let order=1;
        cards.forEach(card=>{
            const cardJson = card.querySelector(".cardJson");
            let json = JSON.parse(cardJson.innerText);
            // let memoValue = planForm.memo.value;
            //json을 stringify한 태그 (내부 내용은 vehicle 이나 place일 수 있지만, 클래스는 cardJson)
            if(card.classList.contains("dragCopy")) { //카드가 vehicle 카드더냐
                json.vday=day;
                json.vorder=order;
                const memo = card.querySelector(".vmemo");
                const memoValue = memo.value;
                json.memo = memoValue;
                vehicleList.push(json);
            }
            else if(card.classList.contains("courseplace")){
                json.pday = day;
                json.porder = order;
                coursePlaceList.push(json);
            }
            order++;
        });
        day++;
    });

    courseTextarea.value = JSON.stringify(courseJson);
    uploadTextarea.value = JSON.stringify(uploadJson);
    placeListTextarea.value=JSON.stringify(coursePlaceList);
    vehicleListTextarea.value=JSON.stringify(vehicleList);

}

// vehicleList는 textarea name="vehicleListJson" 에 stringify 해서 입력
// placeList는 textarea name="placeListJson" 에 stringify 해서 입력
// }
