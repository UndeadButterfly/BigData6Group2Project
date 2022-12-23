let map;
let service;
let infowindow;
let geocoder;
let markers=[];
let places;
const searchResults = document.querySelector(".searchResults");
// position : 마커를 놓을 자리
// map : 마커가 표시될 지도
// 지도에 주어진 좌표에 마커를 찍는다
function placeMarker(position, map, placeId) {
    let marker = new google.maps.Marker({
        position: position,
        map: map,
        placeId: placeId
    });
    markers.push(marker);
}

// 지도 세팅
function initMap() {
    let acorn = new google.maps.LatLng(37.554, 126.9206);

    map = new google.maps.Map(
        document.getElementById('map'), {center: acorn, zoom: 19});

    let service = new google.maps.places.PlacesService(map);
    geocoder = new google.maps.Geocoder();
    infowindow = new google.maps.InfoWindow();

    //지도에 클릭했을때 마커를 찍어준다
    map.addListener("click", function(e) {
        placeMarker(e.latLng, map);
        console.log(e.latLng.toJSON());
    });
}

// 검색 단어로 관련 지역들을 띄워준다
function searchPlace(queryString) {
    //요청 쿼리와 받고 싶은 필드들 (정보들)
    let request = {
        query: queryString,
        fields: ['name', 'type', 'geometry', 'formatted_address', 'place_id', 'rating', 'user_ratings_total'],
    };

    let service = new google.maps.places.PlacesService(map);

    //검색어로 장소 찾기
    service.textSearch(request, function(results, status) {
        //검색결과 장소들 띄우는 박스 비우기
        searchResults.innerHTML='';
        markers=[];
        if (status === google.maps.places.PlacesServiceStatus.OK) {
            for (let i = 0; i < results.length; i++) {
                placeMarker(results[i].geometry.location, map, results[i].place_id);
                searchResults.innerHTML += `
                            <div class="card p-0" style="width:100%;">
                                <div class="place row g-0"
                                     data-lat="${results[i].geometry.location.lat()}"
                                     data-lng="${results[i].geometry.location.lng()}"
                                     data-name="${results[i].name}"
                                     data-address="${results[i].formatted_address}"
                                     data-place_id="${results[i].place_id}">
                                    <div class="col-sm-4">
                                        <img src="/img/course/hallasan.jpg" class="img-fluid rounded-start"
                                             style="height:100%"
                                             alt="한라산">
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body p-1">
                                            <h5 class="card-title">${results[i].name}</h5>
                                            <p class="card-text p-0 m-0 ">주소:${results[i].formatted_address}</p>
                                            <div class="moreInfo text-end">
                                            <a class="card-text p-0 m-0" href="#">
                                            <small class="text-muted">더보기</small>
                                            </a>
                                            <a class="find card-text p-0 m-0" href="#">
                                            <small class="text-muted">찾기</small>
                                            </a>
                                            <a class="delete card-text p-0 m-0" href="#">
                                            <small class="text-muted">삭제</small>
                                            </a>
                                            <a class="add card-text p-0 m-0" href="#">
                                            <small class="text-muted">추가</small>
                                            </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        `;
            }
            map.setCenter(results[0].geometry.location);
            buttonFunctions();
        }
    });
}

function buttonFunctions() {
    const added = document.querySelector(".added");
    places = document.querySelectorAll(".place");
    places.forEach(place=>{
        const findBtn = place.querySelector(".find");
        const deleteBtn = place.querySelector(".delete");
        const addBtn = place.querySelector(".add");
        findBtn.addEventListener("click", e=>{
            map.panTo(new google.maps.LatLng(place.dataset.lat, place.dataset.lng));
        });
        deleteBtn.addEventListener("click", e=>{
            markers.forEach(marker=>{
                if(marker.placeId==place.dataset.place_id) {
                    marker.setMap(null);
                }
            });
            place.remove();
        });
        addBtn.addEventListener("click", e=>{
            place.innerHTML+=`<input type=checkbox value="${place.dataset.place_id}" style="display:none;" checked>`;
            added.prepend(place);
            buttonFunctions();
        });
    });
}

let search = document.forms.search;
let searchText = search["search_text"];
search.onsubmit=(e)=>{
    e.preventDefault();
    searchPlace(searchText.value);
    searchText.value="";
}
const addedPlaces = document.forms["addedPlaces"];
addedPlaces.onsubmit=(e)=>{
    e.preventDefault();
    console.log();
}