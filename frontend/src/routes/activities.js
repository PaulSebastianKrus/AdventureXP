/*
import express from 'express';
import fetch from 'node-fetch';
import path from 'path';

const router = express.Router();
const __dirname = path.dirname(new URL(import.meta.url).pathname); // Get the current directory

// Serve the main activity page
router.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, '../../public/activity.html'));
});

// Serve the page to add a new activity
router.get('/add', (req, res) => {
    res.sendFile(path.join(__dirname, '../../public/addActivity.html'));
});

// Serve the page to see a specific activity by ID
router.get('/:id', (req, res) => {
    res.sendFile(path.join(__dirname, '../../public/seeActivity.html'));
});

// Fetch activities from the Java backend
router.get('/api/activity', async (req, res) => {
    try {
        const response = await fetch('http://localhost:8080/api/activity');
        const activities = await response.json();
        res.json(activities);
    } catch (error) {
        res.status(500).send('Error fetching activities: ' + error.message);
    }
});

// Fetch a specific activity by ID
router.get('/api/activity/:id', async (req, res) => {
    try {
        const response = await fetch(`http://localhost:8080/api/activity/${req.params.id}`);
        const activity = await response.json();
        res.json(activity);
    } catch (error) {
        res.status(500).send('Error fetching activity: ' + error.message);
    }
});

// Handle POST request to add a new activity
router.post('/api/activity/add', async (req, res) => {
    try {
        const activityData = req.body;
        const postResponse = await fetch('http://localhost:8080/api/activity/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(activityData),
        });

        if (postResponse.ok) {
            res.status(201).send('Activity added successfully.');
        } else {
            res.status(postResponse.status).send('Failed to add activity: ' + postResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error adding activity: ' + error.message);
    }
});

// Handle POST request to update an existing activity
router.post('/api/activity/:id', async (req, res) => {
    try {
        const activityId = req.params.id;
        const activityData = req.body;

        const postResponse = await fetch(`http://localhost:8080/api/activity/${activityId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(activityData),
        });

        if (postResponse.ok) {
            res.status(200).send('Activity updated successfully.');
        } else {
            res.status(postResponse.status).send('Failed to update activity: ' + postResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error updating activity: ' + error.message);
    }
});

// Handle DELETE request to remove an activity
router.delete('/api/activity/:id', async (req, res) => {
    try {
        const deleteResponse = await fetch(`http://localhost:8080/api/activity/${req.params.id}`, { method: 'DELETE' });

        if (deleteResponse.ok) {
            res.status(204).send('Activity deleted successfully.');
        } else {
            res.status(deleteResponse.status).send('Failed to delete activity: ' + deleteResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error deleting activity: ' + error.message);
    }
});

export default router;
*/