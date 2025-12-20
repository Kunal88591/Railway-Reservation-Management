# Railway Reservation Management System - Setup Guide

## Quick Start

### Method 1: Using Run Scripts

#### On Linux/Mac:
```bash
chmod +x run.sh
./run.sh
```

#### On Windows:
```cmd
run.bat
```

### Method 2: Manual Compilation

#### Step 1: Compile the project
```bash
# Navigate to project root
cd /workspaces/Railway-Reservation-Management

# Create bin directory
mkdir bin

# Navigate to src
cd src

# Compile all files
javac -d ../bin models/*.java exceptions/*.java utils/*.java gui/*.java
```

#### Step 2: Run the application
```bash
# Navigate to bin directory
cd ../bin

# Run the application
java gui.RailwayReservationApp
```

### Method 3: Using IDE

1. Open the project in your favorite IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Set `src` as the source folder
3. Run `gui/RailwayReservationApp.java` as the main class

## Default Login Credentials

### Admin Account
- **Username**: admin
- **Password**: admin123

### Customer Account
- Register a new account using the Registration page

## Features Overview

### Customer Features
1. **Search Trains**: Search available trains by route and date
2. **Book Tickets**: Book tickets with multiple passengers
3. **Manage Bookings**: View and cancel your bookings
4. **Profile**: View and update profile information

### Admin Features
1. **Manage Trains**: Add, view, and delete trains
2. **View Bookings**: Monitor all system bookings
3. **View Users**: Access user database
4. **Reports**: View comprehensive analytics and statistics

## Sample Data

The system comes pre-loaded with sample trains:
- **12345** - Rajdhani Express (Delhi to Mumbai)
- **12346** - Shatabdi Express (Delhi to Chandigarh)
- **12347** - Duronto Express (Mumbai to Kolkata)
- **12348** - Garib Rath (Delhi to Bangalore)
- **12349** - Humsafar Express (Chennai to Delhi)

## System Requirements

- Java 8 or higher
- Minimum 512 MB RAM
- Any operating system (Windows, Linux, macOS)

## Troubleshooting

### Issue: "javac: command not found"
- **Solution**: Install JDK and add it to your PATH

### Issue: GUI not appearing
- **Solution**: Ensure you have Java GUI libraries (Swing is included in JDK)

### Issue: Compilation errors
- **Solution**: Make sure you're compiling from the correct directory and have all files

## Directory Structure

```
Railway-Reservation-Management/
├── src/
│   ├── models/
│   ├── exceptions/
│   ├── utils/
│   └── gui/
├── bin/              (created after compilation)
├── run.sh           (Linux/Mac run script)
├── run.bat          (Windows run script)
├── README.md
└── SETUP.md
```

## Support

For issues or questions, please refer to the README.md file or create an issue on GitHub.

---

Happy Railway Booking! 🚂
