import express from 'express';
import cors from 'cors';
import fetch from 'node-fetch';
import path from 'path';

const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors());
app.use(express.json());
app.use(express.static('public')); // This line serves static files from the public folder

// Serve index.html at the /activity route
app.get('/activity', (req, res) => {
    const __dirname = path.dirname(new URL(import.meta.url).pathname); // Get the current directory
    res.sendFile(path.join(__dirname, '..', 'public', 'index.html')); // Corrected path
});

// Fetch activities from backend API
app.get('/api/activities', async (req, res) => {
    try {
        const response = await fetch('http://localhost:8080/api/activity'); // Fetch from Spring Boot
        const activities = await response.json();
        res.json(activities);
    } catch (error) {
        res.status(500).send('Error fetching activities: ' + error.message);
    }
});

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
