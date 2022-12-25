function createMarker(place) {
    new google.maps.Marker({
        position: place.geometry.location,
        map: map
    });
}

let map;
let service;
let infowindow;

function initMap() {
    let acorn = new google.maps.LatLng(37.554, 126.9206);

    infowindow = new google.maps.InfoWindow();

    map = new google.maps.Map(
        document.getElementById('map'), {center: acorn, zoom: 19});

    let request = {
        query: '',
        fields: ['name', 'geometry'],
    };

    let service = new google.maps.places.PlacesService(map);

    service.findPlaceFromQuery(request, function(results, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
        for (let i = 0; i < results.length; i++) {
            createMarker(results[i]);
        }
        map.setCenter(results[0].geometry.location);
        }
    });
}