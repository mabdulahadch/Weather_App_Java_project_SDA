const express = require('express');
const cors = require('cors'); // Import the cors middleware
const fs = require('fs');
const app = express();
const port = 3000;

// Enable CORS for all routes
app.use(cors());

// Serve static files from the 'public' directory
app.use(express.static('public'));

// Middleware to parse JSON bodies
app.use(express.json());

// Endpoint to handle writing city name to file
app.post('/writeCityName', (req, res) => {
    const cityName = req.body.cityName;
    const filePath = 'value.txt'; // Adjust the file path accordingly

    // Write city name to file
    fs.appendFile(filePath, cityName + '\n', (err) => {
        if (err) {
            console.error('Error writing to file:', err);
            res.status(500).send('Error writing to file');
            return;
        }
        console.log('City name has been written to file successfully.');
        res.status(200).send('City name has been written to file successfully.');
    });
});

// Start the server
app.listen(port, () => {
    console.log(`Server is listening on port ${port}`);
});
