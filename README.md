# 🚂 Railway Reservation Management System

A modern Railway Reservation Management System built with **Java Swing**, featuring a professionally designed UI with complete visibility and intuitive user experience.

[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.java.com)
[![GUI](https://img.shields.io/badge/GUI-Swing-blue.svg)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![License](https://img.shields.io/badge/License-Educational-green.svg)](LICENSE)

## 🚀 Quick Start

### One-Click Launch
Simply **double-click `LAUNCH.bat`** in the project folder to compile and run!

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Windows OS (for batch launcher)

### Test Credentials
- **Admin**: `admin` / `admin123`
- **Demo User 1**: `john_doe` / `pass123`
- **Demo User 2**: `jane_smith` / `pass123`
- **Demo User 3**: `rahul_kumar` / `pass123`

---

## ✨ Features

### 🎨 Modern UI
- **Professional Light Theme** with high-contrast colors
- **Guaranteed Visibility** - all text and buttons clearly visible
- **Responsive Design** - clean, modern interface
- **Intuitive Navigation** - sidebar menus with hover effects

### 👤 Customer Features
- ✅ User Registration & Login with validation
- ✅ Search trains by source, destination, and date
- ✅ Book tickets for 1-6 passengers
- ✅ View and manage bookings (cancel tickets)
- ✅ Profile management and password change

### 🔧 Admin Features
- ✅ Add, view, and delete trains
- ✅ Monitor all system bookings
- ✅ View registered users
- ✅ Generate reports and analytics

---

## 📁 Project Structure

```
Railway-Reservation-Management/
├── src/
│   ├── models/          # Train, User, Booking, Passenger
│   ├── exceptions/      # Custom exception classes
│   ├── utils/           # DatabaseManager, SessionManager, ValidationUtils
│   └── gui/             # All UI panels with modern styling
├── bin/                 # Compiled classes
├── LAUNCH.bat           # One-click launcher
├── README.md            # This file
└── LICENSE              # MIT License
```

---

## 🎯 Demo Data

The system comes with **pre-loaded test data**:

### Users
- **Admin**: admin/ admin123
- **john_doe**: pass123
- **jane_smith**: pass123
- **rahul_kumar**: pass123

### Trains (20+ routes)
- Delhi ↔ Mumbai (Rajdhani Express)
- Delhi ↔ Chandigarh (Shatabdi Express)
- Mumbai ↔ Kolkata (Duronto Express)
- Delhi ↔ Bangalore (Garib Rath)
- Chennai ↔ Delhi (Humsafar Express)
- And many more routes available for today, tomorrow, and day after tomorrow!

---

## 🛠️ Technologies

- **Language**: Java 8+
- **GUI**: Swing with BasicButtonUI for guaranteed rendering
- **Architecture**: MVC Pattern
- **Data Storage**: In-memory (ConcurrentHashMap)
- **Features**: Lambda expressions, Streams, LocalDate/LocalTime

### Key Concepts
- Object-Oriented Programming
- Design Patterns (Singleton, MVC)
- Custom Exception Hierarchy
- Session Management
- Input Validation

---

## 🎨 UI Color Palette

The application uses a professional light theme:

- **Window Background**: #F3F4F6 (Light gray)
- **Panels**: #FFFFFF (White)
- **Primary Buttons**: #2563EB (Blue) with white text
- **Hover**: #1D4ED8 (Darker blue)
- **Text**: #111827 (Dark, high contrast)
- **Tables**: Dark header, white rows, light blue selection
- **Sidebar**: Dark (#1F2937) with blue menu items

---

## 📝 How to Use

1. **Launch**: Double-click `LAUNCH.bat`
2. **Login**: Use admin credentials or register as a customer
3. **Search Trains**: Select source, destination, and date
4. **Book Tickets**: Fill passenger details and confirm
5. **Manage**: View/cancel bookings, update profile

**For Admins:**
- Add/delete trains
- Monitor all bookings
- View user statistics
- Generate reports

---

## 🏗️ Architecture

- **Presentation Layer**: Swing GUI with custom styling
- **Business Logic**: DatabaseManager, SessionManager, ValidationUtils
- **Data Layer**: In-memory storage with models (Train, User, Booking)
- **Exception Handling**: Custom exception hierarchy

---

## 🤝 Contributing

This is an educational project. Feel free to fork, improve, and submit pull requests!

---

## 📄 License

MIT License - See [LICENSE](LICENSE) file

---

## 👤 Author

**Kunal**
- GitHub: [@Kunal88591](https://github.com/Kunal88591)

---

**⭐ If you find this project helpful, please give it a star!**

*Built with ❤️ using Java and Swing*