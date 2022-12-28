const planForm = document.forms["plannerRegister"];
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
const transportList = document.getElementById("transportList");
const cards = transportList.querySelectorAll(".card");
cards.forEach(card=>{
    let vtype = card.querySelector(".transport-card-title").innerText;
    let json = {
        vehicleNo : null,
        courseNo : null,
        vday : null,
        vorder : null,
        vtype : vtype,
        memo : null
    };
    let jsonP = document.createElement("p");
    let jsonString = document.createTextNode(JSON.stringify(json));
    jsonP.append(jsonString);
    jsonP.classList.add("cardJson");
    jsonP.style.display="none";
    card.append(jsonP);
});

const transports = document.querySelectorAll(".transport");
transports.forEach(transport =>{
    if(transport.querySelector(".delete")!=null){
        deleteBtn(transport);
    }
})

function deleteBtn(node) {
    console.log(node + ": 삭제버튼 눌림");
    const deleteBtn = node.querySelector(".delete");
    console.log("deleteBtn:" + deleteBtn);
    deleteBtn.addEventListener("click", e => {
        node.remove();
    });
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
            if (id === `src_move${i}` && ev.target.id === `day${j}`) {
                ev.target.appendChild(document.getElementById(id));
            }
        }
    }
    for (let j = 1; j < dests.length + 1; j++) {
        for (let i = 1; i < dragCopies.length + 1; i++) {
            if (id === `src_copy${i}` && ev.target.id === `day${j}`) {
                var nodeCopy = document.getElementById(id).cloneNode(true);
                ev.target.appendChild(nodeCopy);
                nodeCopy.innerHTML += '<button class="btn btn-warning btn-sm delete m-0" type="button">삭제하기</button>';
                nodeCopy.innerHTML += '<textarea name="memo" rows="2" cols="20" class="overflow-scroll vmemo" placeholder="추가 설명"></textarea>'
                deleteBtn(nodeCopy);
            }
        }
    }
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
const duration =document.getElementById("planDays");
const planDuration = document.forms["plannerRegister"].planDuration;
const dayBoxContainer = document.getElementById("dayContainer")
console.log(startdateInput);
let startdate;
let enddate;
let dayDiffer=planForm.planDuration.value;
startdateInput.onchange=(e)=>{
    startdate = new Date(startdateInput.value);
    console.log(startdate);
    if (enddate!=null && startdate.getTime() <= enddate.getTime()) {
        dayBoxContainer.innerHTML='';
        dayDiffer = Number(datediff(startdate.getTime(),enddate.getTime()))+1;
        console.log(dayDiffer);
        planDuration.value = dayDiffer;
        duration.value = (dayDiffer-1)+"박"+dayDiffer+"일";
        makeDayBoxes(dayDiffer);
    }
}

enddateInput.onchange=(e)=>{
    enddate = new Date(enddateInput.value);
    console.log(enddate);
    if (startdate!=null && startdate.getTime() <= enddate.getTime()) {
        dayBoxContainer.innerHTML='';
        dayDiffer = Number(datediff(startdate.getTime(),enddate.getTime()))+1;
        console.log(dayDiffer);
        planDuration.value = dayDiffer;
        duration.value = (dayDiffer-1)+"박"+dayDiffer+"일";
        makeDayBoxes(dayDiffer);
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
            <div class="dest dragBox overflow-auto" style="height:550px" id="day${i+1}"  uk-sortable="group: sortable-group" ondrop="drop(event);" ondragover="dragover(event);">
            </div>
        </div>
        `;
    }
}
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
        duration:dayDiffer,
        image:null,
        budget:document.getElementById("planBudget").value,
        uploadNo:null,
        uploadDto:null,
        coursePlaceList:null,
        vehicleList:null
    }
    let uploadJson = {
        uploadNo:null,
        upType:2,
        userId:planForm.userId.value,
        title:planForm.title.value,
        contents:planForm.contents.value,
        postdate:null,
        views:0,
        likes:0,
        hates:0,
        reports:0,
        upstate:0
    }
    let vehicleList=[];
    let coursePlaceList=[];
    let day=1;
    dayBoxes.forEach(dayBox=> {
        const cards = dayBox.querySelectorAll(".card");
        let order=1;
        cards.forEach(card=>{
            const cardJson = card.querySelector(".cardJson");
            let json = JSON.parse(cardJson.innerText);
            //json을 stringify한 태그 (내부 내용은 vehicle 이나 place일 수 있지만, 클래스는 cardJson)
            if(card.classList.contains("dragCopy")) { //카드가 vehicle 카드더냐
                json.vday=day;
                json.vorder=order;
                const memo = card.querySelector(".vmemo");
                json.memo = memo.value;
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
