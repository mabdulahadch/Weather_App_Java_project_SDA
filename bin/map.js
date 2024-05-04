let map;
let marker;
let searchBox;

function initMap() {
    // Coordinates for Lahore
    const lahore = { lat: 31.5497, lng: 74.3436 };

    map = new google.maps.Map(document.getElementById('map'), {
        center: lahore,
        zoom: 12,
        disableDefaultUI: true // Disable default UI including zoom control
    });

    marker = new google.maps.Marker({
        position: lahore,
        map: map,
        draggable: true
    });

    // Create the search box and link it to the UI element
    const input = document.getElementById('search');
    searchBox = new google.maps.places.SearchBox(input);

    // Bias the SearchBox results towards current map's viewport
    map.addListener('bounds_changed', function() {
        searchBox.setBounds(map.getBounds());
    });

    searchBox.addListener('places_changed', function() {
        const places = searchBox.getPlaces();
        if (places.length == 0) {
            return;
        }
        const place = places[0];

        // Center map on the selected place
        map.panTo(place.geometry.location);

        // Move marker to the selected place
        marker.setPosition(place.geometry.location);

        // Send coordinates to API
        sendCoordinates(place.geometry.location.lat(), place.geometry.location.lng());
    });

    google.maps.event.addListener(marker, 'dragend', function() {
        // Update coordinates
        const position = marker.getPosition();
        updateCoordinates(position.lat(), position.lng());
        
        // Send coordinates to API
        sendCoordinates(position.lat(), position.lng());
    });

    google.maps.event.addListener(map, 'click', function(event) {
        marker.setPosition(event.latLng);

        // Update coordinates
        updateCoordinates(event.latLng.lat(), event.latLng.lng());
        
        // Send coordinates to API
        sendCoordinates(event.latLng.lat(), event.latLng.lng());
    });

    // Update coordinates initially for Lahore
    updateCoordinates(lahore.lat, lahore.lng);
}

function updateCoordinates(latitude, longitude) {
    // Display latitude and longitude separated by comma in top left
    document.getElementById('coordinates').textContent = latitude.toFixed(6) + ', ' + longitude.toFixed(6);
}

function sendCoordinates(latitude, longitude) {
    // Make API call to OpenWeatherMap
    fetch('https://api.openweathermap.org/data/2.5/weather?lat=' + latitude + '&lon=' + longitude + '&appid=7d888618e8c1a5f4afaead050a345b88')
    .then(response => response.json())
    .then(data => {
        console.log('API response:', data);
        // Extract city name
        const cityName = data.name;
        // Update city name
        document.getElementById('place-name').textContent = ' ' + cityName;
        // Write city name to file
        writeCityNameToFile(cityName);
    })
    .catch(error => {
        console.error('Error:', error);
        // Handle error if needed
    });
}
// Add event listener for the Current Location button
document.getElementById('current-location-btn').addEventListener('click', function() {
    // Check if geolocation is supported
    if (navigator.geolocation) {
        // Get exact current location
        navigator.geolocation.getCurrentPosition(function(position) {
            const currentLocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

            // Center map on exact current location
            map.panTo(currentLocation);

            // Move marker to exact current location
            marker.setPosition(currentLocation);

            // Send coordinates to API
            sendCoordinates(currentLocation.lat, currentLocation.lng);
        }, function(error) {
            console.error('Error getting current location:', error);
            // Handle error if needed
        }, {
            enableHighAccuracy: true // Request high accuracy for better results
        });
    } else {
        console.error('Geolocation is not supported by this browser.');
        // Handle error if geolocation is not supported
    }
});

// Function to write city name to file on the server
function writeCityNameToFile(cityName) {
    // Make HTTP POST request to server endpoint
    fetch('http://localhost:3000/writeCityName', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ cityName: cityName })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to write city name to file');
        }
        console.log('City name has been written to file successfully.');
    })
    .catch(error => {
        console.error('Error:', error);
        // Handle error if needed
    });
}