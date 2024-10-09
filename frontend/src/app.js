import express from 'express';
import cors from 'cors';
import fetch from 'node-fetch';
import path from 'path';
import { fileURLToPath } from 'url';

// Helper to get __dirname since it's not available in ES modules
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware setup
app.use(cors());
app.use(express.json());
app.use(express.static(path.join(__dirname, '..', 'public'))); // Serve static files from the 'public' folder

// ---------- Activity Routes ----------

// Serve `activity.html` at the `/activity` route
app.get('/activity', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'activity.html'));
});

// Serve `addActivity.html` at the `/activity/add` route
app.get('/activity/add', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'addActivity.html'));
});

// Serve `seeActivity.html` at the `/activity/:id` route
app.get('/activity/:id', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'seeActivity.html'));
});

// Fetch activities from the backend API
app.get('/api/activity', async (req, res) => {
    try {
        const response = await fetch('http://localhost:8080/api/activity');
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
        const response = await fetch(`http://localhost:8080/api/activity/${activityId}`);
        const activity = await response.json();
        res.json(activity);
    } catch (error) {
        res.status(500).send('Error fetching activity: ' + error.message);
    }
});

// Add a new activity
app.post('/api/activity/add', async (req, res) => {
    try {
        const activityData = req.body;
        const postResponse = await fetch('http://localhost:8080/api/activity/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(activityData)
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

// Update an existing activity
app.post('/api/activity/:id', async (req, res) => {
    try {
        const activityId = req.params.id;
        const activityData = req.body;

        const postResponse = await fetch(`http://localhost:8080/api/activity/${activityId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(activityData)
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

// Delete an activity
app.delete('/api/activity/:id', async (req, res) => {
    try {
        const activityId = req.params.id;
        const deleteResponse = await fetch(`http://localhost:8080/api/activity/${activityId}`, { method: 'DELETE' });

        if (deleteResponse.ok) {
            res.status(204).send('Activity deleted successfully.');
        } else {
            res.status(deleteResponse.status).send('Failed to delete activity: ' + deleteResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error deleting activity: ' + error.message);
    }
});

// ---------- Booking Routes ----------

// Serve `booking.html` at the `/booking` route
app.get('/booking', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'booking.html'));
});

// Serve `addBooking.html` at the `/booking/add` route
app.get('/booking/add', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'addBooking.html'));
});

// Serve `seeActivity.html` at the `/activity/:id` route
app.get('/booking/:id', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'seeBooking.html'));
});


// Fetch all bookings from the backend API
app.get('/api/booking', async (req, res) => {
    try {
        const response = await fetch('http://localhost:8080/api/booking');
        const bookings = await response.json();
        res.json(bookings);
    } catch (error) {
        res.status(500).send('Error fetching bookings: ' + error.message);
    }
});

// Fetch a specific booking by ID
app.get('/api/booking/:id', async (req, res) => {
    const bookingId = req.params.id;
    try {
        const response = await fetch(`http://localhost:8080/api/booking/${bookingId}`);
        const booking = await response.json();
        res.json(booking);
    } catch (error) {
        res.status(500).send('Error fetching booking: ' + error.message);
    }
});

// Handle POST request to add a booking
app.post('/api/booking/add', async (req, res) => {
    try {
        const bookingData = req.body;
        const postResponse = await fetch('http://localhost:8080/api/booking/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(bookingData)
        });

        if (postResponse.ok) {
            res.status(201).send('Booking added successfully.');
        } else {
            res.status(postResponse.status).send('Failed to add booking: ' + postResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error adding booking: ' + error.message);
    }
});


// Delete an booking
app.delete('/api/booking/:id', async (req, res) => {
    try {
        const bookingID = req.params.id;
        const deleteResponse = await fetch(`http://localhost:8080/api/booking/${bookingID}`, { method: 'DELETE' });

        if (deleteResponse.ok) {
            res.status(204).send('Booking deleted successfully.');
        } else {
            res.status(deleteResponse.status).send('Failed to delete Booking: ' + deleteResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error deleting Booking: ' + error.message);
    }
});

// Update an existing booking
app.post('/api/booking/:id', async (req, res) => {
    try {
        const bookingID = req.params.id;
        const bookingData = req.body;

        const postResponse = await fetch(`http://localhost:8080/api/booking/${bookingID}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(bookingData)
        });

        if (postResponse.ok) {
            res.status(200).send('Booking updated successfully.');
        } else {
            res.status(postResponse.status).send('Failed to update Booking: ' + postResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error updating Booking: ' + error.message);
    }
});

// ---------- Snack Routes ----------

// Serve `kiosk.html` at the `/activity` route
app.get('/kiosk', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'kiosk.html'));
});


// Serve `addSnack.html` at the `/snack/add` route
app.get('/kiosk/add', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'addSnack.html'));
});

// Serve `seeSnack.html` at the `/snack/:id` route
app.get('/kiosk/snack/:id', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'seeSnack.html'));
});









// Fetch all snacks from the backend API
app.get('/api/snack', async (req, res) => {
    try {
        const response = await fetch('http://localhost:8080/api/snack');
        const snack = await response.json();
        res.json(snack);
    } catch (error) {
        res.status(500).send('Error fetching snacks: ' + error.message);
    }
});

// Fetch a specific snack by ID
app.get('/api/snack/:id', async (req, res) => {
    const snackId = req.params.id;
    try {
        const response = await fetch(`http://localhost:8080/api/snack/${snackId}`);
        const snack = await response.json();
        res.json(snack);
    } catch (error) {
        res.status(500).send('Error fetching snack: ' + error.message);
    }
});

// Handle POST request to add a snack
app.post('/api/snack/add', async (req, res) => {
    try {
        const snackData = req.body;
        const postResponse = await fetch('http://localhost:8080/api/snack/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(snackData)
        });

        if (postResponse.ok) {
            res.status(201).send('Snack added successfully.');
        } else {
            res.status(postResponse.status).send('Failed to add snack: ' + postResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error adding snack: ' + error.message);
    }
});


// Delete a snack
app.delete('/api/snack/:id', async (req, res) => {
    try {
        const snackId = req.params.id;
        const deleteResponse = await fetch(`http://localhost:8080/api/snack/${snackId}`, { method: 'DELETE' });

        if (deleteResponse.ok) {
            res.status(204).send('Snack deleted successfully.');
        } else {
            res.status(deleteResponse.status).send('Failed to delete snack: ' + deleteResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error deleting snack: ' + error.message);
    }
});

// Update an existing snack
app.post('/api/snack/:id', async (req, res) => {
    try {
        const snackId = req.params.id;
        const snackData = req.body;

        const postResponse = await fetch(`http://localhost:8080/api/snack/${snackId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(snackData)
        });

        if (postResponse.ok) {
            res.status(200).send('Snack updated successfully.');
        } else {
            res.status(postResponse.status).send('Failed to update snack: ' + postResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error updating snack: ' + error.message);
    }
});

// ---------- Tshirt Routes ----------

// Serve `tshirt.html` at the `/tshirt` route
app.get('/tshirt', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'tshirt.html'));
});

// Serve `addTshirt.html` at the `/tshirt/add` route
app.get('/tshirt/add', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'addTshirt.html'));
});

// Serve `seeTshirt.html` at the `/tshirt/:id` route
app.get('/tshirt/:id', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'seeTshirt.html'));
});

// Fetch all tshirts from the backend API
app.get('/api/tshirt', async (req, res) => {
    try {
        const response = await fetch('http://localhost:8080/api/tshirt');
        const tshirts = await response.json();
        res.json(tshirts);
    } catch (error) {
        res.status(500).send('Error fetching tshirts: ' + error.message);
    }
});

// Fetch a specific tshirt by ID
app.get('/api/tshirt/:id', async (req, res) => {
    const tshirtId = req.params.id;
    try {
        const response = await fetch(`http://localhost:8080/api/tshirt/${tshirtId}`);
        const tshirt = await response.json();
        res.json(tshirt);
    } catch (error) {
        res.status(500).send('Error fetching tshirt: ' + error.message);
    }
});

// Handle POST request to add a tshirt
app.post('/api/tshirt/add', async (req, res) => {
    try {
        const tshirtData = req.body;
        const postResponse = await fetch('http://localhost:8080/api/tshirt/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(tshirtData)
        });

        if (postResponse.ok) {
            res.status(201).send('Tshirt added successfully.');
        } else {
            res.status(postResponse.status).send('Failed to add tshirt: ' + postResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error adding tshirt: ' + error.message);
    }
});

// Delete a tshirt
app.delete('/api/tshirt/:id', async (req, res) => {
    try {
        const tshirtId = req.params.id;
        const deleteResponse = await fetch(`http://localhost:8080/api/tshirt/${tshirtId}`, { method: 'DELETE' });

        if (deleteResponse.ok) {
            res.status(204).send('Tshirt deleted successfully.');
        } else {
            res.status(deleteResponse.status).send('Failed to delete tshirt: ' + deleteResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error deleting tshirt: ' + error.message);
    }
});

// Update an existing tshirt
app.post('/api/tshirt/:id', async (req, res) => {
    try {
        const tshirtId = req.params.id;
        const tshirtData = req.body;

        const postResponse = await fetch(`http://localhost:8080/api/tshirt/${tshirtId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(tshirtData)
        });

        if (postResponse.ok) {
            res.status(200).send('Tshirt updated successfully.');
        } else {
            res.status(postResponse.status).send('Failed to update tshirt: ' + postResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error updating tshirt: ' + error.message);
    }
});


// ---------- Employee Routes ----------

// Serve `employee.html` at the `/employee` route
app.get('/employee', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'employee.html'));
});


// Serve `addEmployee.html` at the `/employee/add` route
app.get('/employee/add', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'addEmployee.html'));
});

// Serve `seeEmployee.html` at the `/employee/:id` route
app.get('/employee/:id', (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'seeEmployee.html'));
});

// Fetch all employees from the backend API
app.get('/api/employee', async (req, res) => {
    try {
        const response = await fetch('http://localhost:8080/api/employee');
        const employees = await response.json();
        res.json(employees);
    } catch (error) {
        res.status(500).send('Error fetching employees: ' + error.message);
    }
});

// Fetch a specific employee by ID
app.get('/api/employee/:id', async (req, res) => {
    const employeeId = req.params.id;
    try {
        const response = await fetch(`http://localhost:8080/api/employee/${employeeId}`);
        const employee = await response.json();
        res.json(employee);
    } catch (error) {
        res.status(500).send('Error fetching employee: ' + error.message);
    }
});

// Handle POST request to add an employee
app.post('/api/employee/add', async (req, res) => {
    try {
        const employeeData = req.body;
        const postResponse = await fetch('http://localhost:8080/api/employee/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(employeeData)
        });

        if (postResponse.ok) {
            res.status(201).send('Employee added successfully.');
        } else {
            res.status(postResponse.status).send('Failed to add employee: ' + postResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error adding employee: ' + error.message);
    }
});

// Delete an employee
app.delete('/api/employee/:id', async (req, res) => {
    try {
        const employeeId = req.params.id;
        const deleteResponse = await fetch(`http://localhost:8080/api/employee/${employeeId}`, { method: 'DELETE' });

        if (deleteResponse.ok) {
            res.status(204).send('Employee deleted successfully.');
        } else {
            res.status(deleteResponse.status).send('Failed to delete employee: ' + deleteResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error deleting employee: ' + error.message);
    }
});

// Update an existing employee
app.post('/api/employee/:id', async (req, res) => {
    try {
        const employeeId = req.params.id;
        const employeeData = req.body;

        const postResponse = await fetch(`http://localhost:8080/api/employee/${employeeId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(employeeData)
        });

        if (postResponse.ok) {
            res.status(200).send('Employee updated successfully.');
        } else {
            res.status(postResponse.status).send('Failed to update employee: ' + postResponse.statusText);
        }
    } catch (error) {
        res.status(500).send('Error updating employee: ' + error.message);
    }
});




// Start the server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
