const validUsername = "student";
const validPassword = "12345";

const loginForm = document.getElementById('loginForm');
const welcomeMessage = document.getElementById('welcomeMessage');
const timestampDiv = document.getElementById('timestamp');

function playBeep() {
    const context = new (window.AudioContext || window.webkitAudioContext)();
    const oscillator = context.createOscillator();
    oscillator.type = 'square';
    oscillator.frequency.setValueAtTime(440, context.currentTime);
    oscillator.connect(context.destination);
    oscillator.start();
    oscillator.stop(context.currentTime + 0.2);
}

let attendanceRecords = [];

loginForm.addEventListener('submit', function(event){
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if(username === validUsername && password === validPassword){
        const now = new Date();
        const timestamp = now.toLocaleString();

        welcomeMessage.textContent = `Welcome, ${username}!`;
        timestampDiv.textContent = `Login Time: ${timestamp}`;

        attendanceRecords.push({ username: username, timestamp: timestamp });

        let attendanceData = "Attendance Summary:\n\n";
        attendanceRecords.forEach(record => {
            attendanceData += `Username: ${record.username}\nTimestamp: ${record.timestamp}\n\n`;
        });

        const blob = new Blob([attendanceData], { type: 'text/plain' });
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = 'attendance_summary.txt';
        link.click();

    } else {
        alert('Incorrect username or password!');
        playBeep();
    }

    loginForm.reset();
});
