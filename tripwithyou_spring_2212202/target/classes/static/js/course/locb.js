function recommendToggle(){
    const hiddenBox = document.querySelector("#hiddenBox");
    hiddenBox.classList.toggle("d-none");
}
const recLocCardList = document.getElementById("recLocCardList");
const recFoodCardList = document.getElementById("recFoodCardList");
const recSleepCardList=document.getElementById("recSleepCardList");
const tourBtn = document.getElementById("tourBtn");
const foodBtn = document.getElementById("foodBtn");
const sleepBtn = document.getElementById("sleepBtn");

console.log(recFoodCardList);
console.log(recSleepCardList);
console.log(recLocCardList);

document.getElementById("tourBtn").onclick=()=>{
    tourBtn.classList.add("active");
    foodBtn.classList.remove("active");
    sleepBtn.classList.remove("active");

    recLocCardList.classList.remove("d-none");
    recFoodCardList.classList.add("d-none");
    recSleepCardList.classList.add("d-none");
};

document.getElementById("foodBtn").onclick=()=>{
    tourBtn.classList.remove("active");
    foodBtn.classList.add("active");
    sleepBtn.classList.remove("active");

    recLocCardList.classList.add("d-none");
    recFoodCardList.classList.remove("d-none");
    recSleepCardList.classList.add("d-none");
}

document.getElementById("sleepBtn").onclick=()=>{
    tourBtn.classList.remove("active");
    foodBtn.classList.remove("active");
    sleepBtn.classList.add("active");

    recLocCardList.classList.add("d-none");
    recFoodCardList.classList.add("d-none");
    recSleepCardList.classList.remove("d-none");
}

