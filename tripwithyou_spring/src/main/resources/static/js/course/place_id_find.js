const googleMapsScript = document.createElement('script');
googleMapsScript.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyD4pSdMw_RNfVqiYSSBKYZVB6tbFe8s_jQ&libraries=geometry,places&callback=initMap';
document.head.appendChild(googleMapsScript);

let map;
let geocoder;
function initMap() {
    let acorn = new google.maps.LatLng(37.554, 126.9206);

    map = new google.maps.Map(
        document.getElementById('map'), {
            center: acorn,
            zoom: 19
        });
    geocoder = new google.maps.Geocoder();
    const placeList = document.querySelectorAll(".placeIdDiv");
    const locListCard = document.getElementById("locListCard");
    placeList.forEach((place)=>{
        geocoder
            .geocode({ placeId: place.value })
            .then(({ results }) => {
                if (results[0]) {
                    locListCard.innerHTML+=`
                            <div class="card p-0 draggable item" draggable="true" style="width:100%;">
                                <div class="row g-0">
                                    <div class="col-sm-4">
                                        <img src="/img/course/hallasan.jpg" class="img-fluid rounded-start"
                                             style="height:100%"
                                             alt="한라산">
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body p-1">
                                            <h5 class="card-title">${results[0].name}</h5>
                                            <p class="card-text p-0 m-0 ">${results[0].formatted_address}</p>
                                            <div class="moreInfo text-end">
                                                <a class="card-text p-0 m-0" href="#"><small
                                                    class="text-muted">더보기</small></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>`;
                } else {
                    window.alert("No results found");
                }
            })
            .catch((e) => window.alert("Geocoder failed due to: " + e));
    });
}