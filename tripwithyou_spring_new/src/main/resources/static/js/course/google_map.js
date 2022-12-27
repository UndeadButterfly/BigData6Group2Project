const googleMapsScript = document.createElement('script');
googleMapsScript.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyD4pSdMw_RNfVqiYSSBKYZVB6tbFe8s_jQ&libraries=geometry,places&callback=initMap';
document.head.appendChild(googleMapsScript);

let map;
let service;
let infowindow;
let geocoder;
let marker;
let places;
const added = document.querySelector(".added");
const searchResults = document.querySelector(".searchResults");

function initMap() {
    let acorn = new google.maps.LatLng(37.554, 126.9206);

    map = new google.maps.Map(
        document.getElementById('map'), {
            center: acorn,
            zoom: 19
        });
    geocoder = new google.maps.Geocoder();

    //지도에 클릭했을때 마커를 찍어준다
    // map.addListener("click", function (e) {
    //     placeMarker(e.latLng);
    //     console.log(e.latLng.toJSON());
    // });
}

// position : 마커를 놓을 자리
// map : 마커가 표시될 지도
// 지도에 주어진 좌표에 마커를 찍는다
function placeMarker(position, map) {
    if(marker)marker.setMap(null);
    marker = new google.maps.Marker({
        position: position,
        map: map
    });
    map.panTo(position);
}

// 검색 단어로 관련 지역들을 띄워준다
function searchPlace(queryString) {
    //요청 쿼리와 받고 싶은 필드들 (정보들)
    let request = {
        query: queryString,
        fields: ['name', 'geometry', 'formatted_address', 'rating'],
    };

    let service = new google.maps.places.PlacesService(map);

    //검색어로 장소 찾기
    service.textSearch(request, function (results, status) {
        //검색결과 장소들 띄우는 박스 비우기
        searchResults.innerHTML = '';
        if (status === google.maps.places.PlacesServiceStatus.OK) {
            for (let i = 0; i < results.length; i++) {
                const json= {
                    cplaceNo : null,
                    name : results[i].name,
                    address : results[i].formatted_address,
                    imgPath : null,
                    rate : results[i].rating,
                    courseNo : null,
                    pday : null,
                    porder : null,
                    memo : null
                }
                searchResults.innerHTML += `
                            <div class="place" 
                                data-lat="${results[i].geometry.location.lat()}"
                                data-lng="${results[i].geometry.location.lng()}"
                                data-name="${results[i].name}"
                                data-address="${results[i].formatted_address}">
                                    <p>${results[i].name}</p>
                                    <p>${results[i].formatted_address}</p>
                                    <p>${results[i].rating}</p>
                                    <!--전송할 값-->   
                                    <p class="json" style="display: none;">${JSON.stringify(json)}</p>
                                    <button type="button" class="find">찾아가기</button>
                                    <button type="button" class="delete">삭제하기</button>
                                    <span class="addBtnContainer">
                                        <button type="button" class="add">추가하기</button>
                                    </span>
                            </div>
                        `;
            }
            map.setCenter(results[0].geometry.location);
            buttonFunctions();
        }
        // <textarea name="json" id="" cols="30" rows="10">${JSON.stringify(json)}</textarea>
    });
}

function buttonFunctions() {
    places = document.querySelectorAll(".place");
    places.forEach(place => {
        const findBtn = place.querySelector(".find");
        const deleteBtn = place.querySelector(".delete");
        const addBtn = place.querySelector(".add");
        findBtn.addEventListener("click", e => {
            let position = new google.maps.LatLng(place.dataset.lat, place.dataset.lng);
            placeMarker(position, map);
        });
        deleteBtn.addEventListener("click", e => {
            place.remove();
        });
        if(addBtn!=null) {
            addBtn.addEventListener("click", e => {
                place.querySelector(".addBtnContainer").innerHTML=""
                added.prepend(place);
                buttonFunctions();
            });
        }
    });
}

let search = document.forms.search;
let searchText = search["search_text"];
search.onsubmit = (e) => {
    if(marker)marker.setMap(null);
    e.preventDefault();
    searchPlace(searchText.value);
    searchText.value = "";
    rightBar.classList.add("drop-down");
}
// initMap();

const addedPlaces = document.forms["addedPlaces"];
addedPlaces.onsubmit=(e)=>{
    const places = addedPlaces.querySelectorAll(".place");
    let jsonList =[];
    places.forEach(place=>{
        const json = place.querySelector(".json");
        jsonList.push(JSON.parse(json.innerText));
    });
    const textValue = document.createTextNode(JSON.stringify(jsonList));
    addedPlaces["json"].innerHTML='';
    addedPlaces["json"].append(textValue);
}