const placeList = document.querySelectorAll(".placeIdDiv");
const locListCard = document.getElementById("locListCard");
placeList.forEach((json_str)=>{
    const json = JSON.parse(json_str.value);
    locListCard.innerHTML+=`
        <div class="card p-0 draggable item" draggable="true" style="width:100%;"
            data-name="${json.name}"
            data-address="${json.address}"
            data-rate="${json.rate}">
            <div class="row g-0">
                <div class="col-sm-4">
                    <img src="" 
                    class="img-fluid rounded-start"
                    style="height:100%"
                    alt="${json.name}">
                </div>
                <div class="col-md-8">
                    <div class="card-body p-1">
                        <h5 class="card-title">${json.name}</h5>
                        <p class="card-text p-0 m-0 ">${json.address}</p>
                        <div class="moreInfo text-end">
                            <a class="card-text p-0 m-0" href="#"><small
                                class="text-muted">더보기</small></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>`;
});