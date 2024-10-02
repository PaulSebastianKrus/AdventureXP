import express from 'express';
import cors from 'cors';
import fetch from 'node-fetch';
import path from 'path';

const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors());
app.use(express.json());
app.use(express.static('public')); // This line serves static files from the public folder

// Serve activity.html at the /activity route
app.get('/activity', (req, res) => {
    const __dirname = path.dirname(new URL(import.meta.url).pathname); // Get the current directory
    res.sendFile(path.join(__dirname, '..', 'public', 'activity.html')); // Corrected path
});
// Serve addActivity.html at the /activity/add route
app.get('/activity/add', (req, res) => {
    const __dirname = path.dirname(new URL(import.meta.url).pathname); // Get the current directory
    res.sendFile(path.join(__dirname, '..', 'public', 'addActivity.html')); // Corrected path
});
// Serve seeActivity.html at the /activity/add route
app.get('/activity/:id', (req, res) => {
    const __dirname = path.dirname(new URL(import.meta.url).pathname); // Get the current directory
    res.sendFile(path.join(__dirname, '..', 'public', 'seeActivity.html')); // Corrected path
});





// Fetch activities from backend API
app.get('/api/activity', async (req, res) => {
    try {
        const response = await fetch('http://localhost:8080/api/activity'); // Fetch from Spring Boot
        const activities = await response.json();
        res.json(activities);
    } catch (error) {
        res.status(500).send('Error fetching activities: ' + error.message);
    }
});

// Fetch a specific activity by ID
app.get('/api/activity/:id', async (req, res) => {
    const activityId = req.params.id;
    try {
        const response = await fetch(`http://localhost:8080/api/activity/${activityId}`); // Adjust according to your Spring Boot API
        const activity = await response.json();
        res.json(activity);
    } catch (error) {
        res.status(500).send('Error fetching activity: ' + error.message);
    }
});

// Handle POST request to add an activity
app.post('/api/activity/add', async (req, res) => {
    try {
        const activityData = req.body; // Get the activity data from the request body

        // Make sure to pass the activity data in the request to the Spring Boot backend
        const postResponse = await fetch('http://localhost:8080/api/activity/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // Set the content type to JSON
            },
            body: JSON.stringify(activityData) // Send the activity data as JSON
        });

        if (postResponse.ok) {
            res.status(201).send('Activity added successfully.'); // Send a success response
        } else {
            res.status(postResponse.status).send('Failed to add activity: ' + postResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error adding activity: ' + error.message);
    }
});



// Handle POST request to update an activity
app.post('/api/activity/:id', async (req, res) => {
    try {
        const activityId = req.params.id; // Get the activity ID from the request parameters
        const activityData = req.body; // Get the activity data from the request body

        // Make sure to pass the activity ID in the request to the Spring Boot backend
        const postResponse = await fetch(`http://localhost:8080/api/activity/${activityId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // Set the content type to JSON
            },
            body: JSON.stringify(activityData) // Send the activity data as JSON
        });

        if (postResponse.ok) {
            res.status(200).send('Activity updated successfully.'); // Send a success response
        } else {
            res.status(postResponse.status).send('Failed to update activity: ' + postResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error updating activity: ' + error.message);
    }
});



// Handle DELETE request to delete an activity
app.delete('/api/activity/:id', async (req, res) => {
    try {
        const activityId = req.params.id;
        const deleteResponse = await fetch(`http://localhost:8080/api/activity/${activityId}`, {
            method: 'DELETE'
        });

        if (deleteResponse.ok) {
            res.status(204).send('Activity deleted successfully.');
        } else {
            res.status(deleteResponse.status).send('Failed to delete activity: ' + deleteResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error deleting activity: ' + error.message);
    }





});










app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
