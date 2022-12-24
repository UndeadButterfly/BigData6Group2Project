const dragBox = document.querySelectorAll(".dragBox");
// console.log(dragBox);
let coursePlaceJsonList = [];
let vehicleJsonList=[];

for(let i=0; i<dragBox.length; i++){
    const localCardList = dragBox[i].children;
    for(let j=0; j<localCardList.length; j++){
        const coursePlaceJson={
            name:(localCardList[i].dataset.name)?localCardList[i].dataset.name:null,
            address:(localCardList[i].dataset.address)?localCardList[i].dataset.address:null,
            imgPath:null,
            tel:null,
            openHour:null,
            rate:(localCardList[i].dataset.rate)?localCardList[i].dataset.rate:null,
            type:null,
            courseNo:null,
            pDay:(i+1),
            pOrder:null,
            memo: "나 여기 갈거지롱"
        }
        coursePlaceJsonList.push(coursePlaceJson);
    }
    const vehicleCardList = dragBox[i].children;
    for(let k=0; k<vehicleCardList.length; k++){
        const vehicleJson={
            courseNo:null,
            vDay:(i+1),
            vOrder:null,
            vType:(i),
            memo:"나 차끌고 갈거지롱"
        }
        vehicleJsonList.push(vehicleJson);
    }
}
const planRegisterForm = document.forms["plannerRegister"];
let uploadJson={
    uptype:1,
    userId:(planRegisterForm.userId)?planRegisterForm.userId:null,
    title:(planRegisterForm.title)?planRegisterForm.title:null,
    contents:(planRegisterForm.contents)?planRegisterForm.contents:null,
    postdate:(planRegisterForm.postdate)?planRegisterForm.postdate:null,
    views:0,
    likes:0,
    dislikes:0,
    reports:0,
    upstate:0,
}
const courseJson = {
    startdate:(planRegisterForm.startdate)?planRegisterForm.startdate:null,
    enddate:(planRegisterForm.enddate)?planRegisterForm.enddate:null,
    duration:(planRegisterForm.duration)?planRegisterForm.duration:null,
    image:null,
    budget:(planRegisterForm.budget)?planRegisterForm.budget:null,
    uploadNo:null,
    uploadDto:uploadJson,
    coursePlaceList:coursePlaceJsonList,
    vehicleList:vehicleJsonList
}

const completeJson = JSON.stringify(courseJson);
console.log(completeJson);