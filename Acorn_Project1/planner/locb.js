


function myFunction(){
    const recommendBtn = document.querySelector("#hiddenBox");
    // recommendBtn.classList.toggle("foldableBoxRight")
    if(recommendBtn.style.display == "none"){
        recommendBtn.style.display = "block";
        window.setTimeout(function(){
            recommendBtn.style.opacity=1;
            // recommendBtn.style.transform='scale(1)';

        },0);
    }else{
        recommendBtn.style.opacity=0;
        // recommendBtn.style.transform = 'scale(0)';
        window.setTimeout(function(){
            recommendBtn.style.display="none";
        },500);
        
    }
}