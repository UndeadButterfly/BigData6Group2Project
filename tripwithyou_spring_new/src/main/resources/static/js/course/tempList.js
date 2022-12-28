const tpCards = document.querySelectorAll(".tpCard");
console.log(tpCards)
tpCards.forEach(card=>{
    const button = card.querySelector(".toggleBtn");
    const back = card.querySelector(".back");
    button.onclick =(e)=>{
        back.classList.toggle("backToggle");
    }
})