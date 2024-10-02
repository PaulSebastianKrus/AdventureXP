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
// Serve activity.html at the /activity/add route
app.get('/activity/add', (req, res) => {
    const __dirname = path.dirname(new URL(import.meta.url).pathname); // Get the current directory
    res.sendFile(path.join(__dirname, '..', 'public', 'addActivity.html')); // Corrected path
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
