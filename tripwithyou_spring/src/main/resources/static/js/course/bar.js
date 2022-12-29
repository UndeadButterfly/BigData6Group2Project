const leftBarBtn = document.querySelector("#leftBarBtn");
const leftBar = document.getElementById("left-bar");
leftBarBtn.onclick=(e)=>{
    leftBar.classList.toggle("drop-down");
}

const rightBarBtn = document.querySelector("#rightBarBtn");
const rightBar = document.getElementById("right-bar");
rightBarBtn.onclick=(e)=>{
    rightBar.classList.toggle("drop-down");
}
