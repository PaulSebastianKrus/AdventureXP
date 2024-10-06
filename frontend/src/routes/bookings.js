
/*import express from 'express';
import fetch from 'node-fetch';
import path from 'path';

const router = express.Router();
const __dirname = path.dirname(new URL(import.meta.url).pathname);

// Serve the main booking page
router.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, '../../public/booking.html'));
});

// Fetch all bookings from the Java backend
router.get('/api/bookings', async (req, res) => {
    try {
        const response = await fetch('http://localhost:8080/api/bookings');
        const bookings = await response.json();
        res.json(bookings);
    } catch (error) {
        res.status(500).send('Error fetching bookings: ' + error.message);
    }
});

// Fetch a specific booking by ID
router.get('/api/bookings/:id', async (req, res) => {
    try {
        const response = await fetch(`http://localhost:8080/api/bookings/${req.params.id}`);
        const booking = await response.json();
        res.json(booking);
    } catch (error) {
        res.status(500).send('Error fetching booking: ' + error.message);
    }
});

export default router;
*/