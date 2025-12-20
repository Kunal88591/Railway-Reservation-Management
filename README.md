# 🚂 Railway Reservation Management System

A professional Railway Reservation Management System built with **Java** and **Swing GUI**, demonstrating advanced **Object-Oriented Programming** principles, comprehensive **exception handling**, and modular architecture.

[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.java.com)
[![GUI](https://img.shields.io/badge/GUI-Swing-blue.svg)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![License](https://img.shields.io/badge/License-Educational-green.svg)](LICENSE)

## 📋 Table of Contents
- [Features](#-features)
- [Quick Start](#-quick-start)
- [Project Structure](#-project-structure)
- [Screenshots](#-screenshots)
- [Documentation](#-documentation)
- [Technologies](#-technologies-used)

---

## ✨ Features

### 👤 Customer Features
- ✅ **User Registration & Login** - Secure authentication with validation
- ✅ **Train Search** - Search by source, destination, and date
- ✅ **Ticket Booking** - Book for 1-6 passengers with details
- ✅ **Booking Management** - View bookings with PNR, cancel tickets
- ✅ **Profile Management** - View and update profile, change password

### 🔧 Admin Features
- ✅ **Train Management** - Add, view, and delete trains
- ✅ **Booking Overview** - Monitor all system bookings
- ✅ **User Management** - View all registered users
- ✅ **Reports & Analytics** - Revenue, occupancy, and booking statistics

---

## 🚀 Quick Start

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Windows/Linux/Mac with graphical display

### Running the Application

**Option 1: Using Scripts (Recommended)**
```bash
# Linux/Mac
chmod +x run.sh
./run.sh

# Windows
run.bat
```

**Option 2: Manual Compilation**
```bash
# Compile
cd src
javac -d ../bin models/*.java exceptions/*.java utils/*.java gui/*.java

# Run
cd ../bin
java gui.RailwayReservationApp
```

### Default Credentials
- **Admin**: `admin` / `admin123`
- **Customer**: Register a new account

---

## 📁 Project Structure

```
Railway-Reservation-Management/
├── src/
│   ├── models/          # Data entities (Train, User, Booking, Passenger)
│   ├── exceptions/      # Custom exception hierarchy (6 classes)
│   ├── utils/           # Business logic (Database, Session, Validation)
│   └── gui/             # Swing GUI components (14 panels)
├── bin/                 # Compiled .class files
├── docs/                # Documentation
│   ├── SETUP.md        # Installation guide
│   └── USER_GUIDE.md   # Complete user manual
├── README.md           # This file (Project overview)
├── run.sh              # Linux/Mac startup script
└── run.bat             # Windows startup script
```

### Package Overview
| Package | Classes | Purpose |
|---------|---------|---------|
| `models` | 4 | Core data entities |
| `exceptions` | 6 | Error handling hierarchy |
| `utils` | 3 | Business logic & validation |
| `gui` | 14 | User interface components |

---

## 📸 Screenshots

### Login & Registration
- Modern login screen with validation
- User-friendly registration form
- Real-time input validation

### Customer Dashboard
- Train search with filters
- Interactive booking dialog
- Booking management table
- Profile view and editing

### Admin Dashboard
- Train management panel
- System-wide booking overview
- User management interface
- Comprehensive analytics & reports

---

## 📚 Documentation

- **[docs/SETUP.md](docs/SETUP.md)** - Detailed installation and setup instructions
- **[docs/USER_GUIDE.md](docs/USER_GUIDE.md)** - Complete user manual with step-by-step guides
- **[CONTRIBUTING.md](CONTRIBUTING.md)** - Contribution guidelines
- **[LICENSE](LICENSE)** - MIT License
- **Source Code** - Well-documented with JavaDoc comments

---

## 🛠️ Technologies Used

### Core Technologies
- **Language**: Java 8+
- **GUI Framework**: Swing
- **Architecture**: MVC Pattern

### Key Concepts Demonstrated
- ✅ **OOP Principles**: Encapsulation, Inheritance, Polymorphism, Abstraction
- ✅ **Design Patterns**: Singleton, Factory, MVC, Observer
- ✅ **Exception Handling**: Custom exception hierarchy
- ✅ **Data Structures**: HashMap, ArrayList, ConcurrentHashMap
- ✅ **Java 8 Features**: LocalDate, LocalTime, Streams, Lambda expressions

### Code Statistics
- **Total Files**: 27 Java files
- **Lines of Code**: ~3,500+
- **Classes**: 27
- **Methods**: 200+
- **Documentation**: Complete JavaDoc comments

---

## 🏗️ Architecture Highlights

### Object-Oriented Design
```
┌─────────────────────────────────────────┐
│           Presentation Layer            │
│         (GUI Components - Swing)        │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│          Business Logic Layer           │
│    (Utils - Database, Session, Valid.)  │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│            Data Layer                   │
│         (Models - Entities)             │
└─────────────────────────────────────────┘
```

### Exception Hierarchy
```
RailwayReservationException (Base)
    ├── BookingException
    │       └── InsufficientSeatsException
    ├── AuthenticationException
    ├── ValidationException
    └── TrainNotFoundException
```

---

## 🎯 Key Features Implementation

### 1. **Session Management**
- Singleton pattern for user sessions
- Role-based access control (Customer/Admin)
- Secure authentication

### 2. **Data Management**
- In-memory database using ConcurrentHashMap
- Thread-safe operations
- CRUD operations for all entities
- Easy to extend to JDBC/database

### 3. **Validation System**
- Email validation (regex)
- Phone validation (Indian format)
- Password strength validation
- Input sanitization

### 4. **Booking System**
- Multi-passenger booking (1-6)
- Seat availability management
- PNR generation
- Booking cancellation with seat release

### 5. **Admin Analytics**
- Revenue tracking by payment method
- Occupancy rate calculations
- Booking statistics by status
- User registration metrics

---

## 🚀 Features in Detail

### Customer Module
1. **Registration**
   - Form validation
   - Unique username check
   - Password confirmation

2. **Train Search**
   - Filter by source, destination, date
   - Real-time availability display
   - Fare information

3. **Ticket Booking**
   - Dynamic passenger forms
   - Multiple payment methods
   - Seat auto-assignment
   - Instant confirmation with PNR

4. **Booking Management**
   - View all bookings
   - Cancel confirmed tickets
   - Detailed booking information

### Admin Module
1. **Train Management**
   - Add new trains
   - View all trains
   - Delete trains
   - Update train details

2. **System Monitoring**
   - All bookings overview
   - User list with details
   - System statistics

3. **Reports**
   - Revenue reports
   - Occupancy analytics
   - Booking trends
   - Payment method distribution

---

## 🎨 UI/UX Features

### Design Elements
- **Color Scheme**: Professional blue/green palette
- **Typography**: Segoe UI font family
- **Layout**: Responsive GridBagLayout and CardLayout
- **Components**: Custom styled buttons, tables, and forms

### User Experience
- Clear visual feedback for all actions
- Confirmation dialogs for critical operations
- Error messages with helpful information
- Intuitive navigation with sidebar menus
- Smooth transitions between panels

---

## 🔐 Security Features

- Password-based authentication
- Session management
- Role-based access control
- Input validation at all entry points
- SQL injection prevention (in-memory DB)

---

## 📈 Scalability & Extensions

### Easy to Extend
The modular architecture allows for:
- ✅ Database integration (MySQL/PostgreSQL)
- ✅ Payment gateway integration
- ✅ Email/SMS notifications
- ✅ Advanced seat selection
- ✅ Waiting list management
- ✅ PDF ticket generation
- ✅ REST API layer
- ✅ Mobile app backend

---

## 🎓 Learning Outcomes

This project demonstrates:
1. Professional Java application development
2. Swing GUI programming
3. MVC architecture implementation
4. Design pattern application
5. Exception handling best practices
6. Clean code principles
7. Project documentation

---

## 👨‍💻 Development

### Code Quality
- ✅ Consistent naming conventions
- ✅ Comprehensive JavaDoc comments
- ✅ Modular package structure
- ✅ Single Responsibility Principle
- ✅ DRY (Don't Repeat Yourself)

### Best Practices
- Exception handling at all levels
- Input validation
- Resource management
- Clear separation of concerns
- Testable code structure

---

## 📝 Sample Data

The system comes pre-loaded with:

### Trains
- **12345** Rajdhani Express (Delhi → Mumbai)
- **12346** Shatabdi Express (Delhi → Chandigarh)
- **12347** Duronto Express (Mumbai → Kolkata)
- **12348** Garib Rath (Delhi → Bangalore)
- **12349** Humsafar Express (Chennai → Delhi)

### Cities
Delhi, Mumbai, Chandigarh, Kolkata, Bangalore, Chennai

---

## 🤝 Contributing

This is an educational project. Feel free to:
- Fork the repository
- Submit pull requests
- Report issues
- Suggest improvements

---

## 📄 License

This project is developed for educational purposes.

---

## 👤 Author

**Kunal**
- GitHub: [@Kunal88591](https://github.com/Kunal88591)

---

## 🙏 Acknowledgments

- Built using Java SE and Swing framework
- Inspired by real-world railway reservation systems
- Implements industry-standard design patterns

---

## 📞 Support

For questions or issues:
1. Check [docs/SETUP.md](docs/SETUP.md) for installation help
2. Read [docs/USER_GUIDE.md](docs/USER_GUIDE.md) for usage instructions
3. Create an issue on GitHub
4. See [CONTRIBUTING.md](CONTRIBUTING.md) for contribution guidelines

---

**⭐ If you find this project helpful, please give it a star!**

---

*Built with ❤️ using Java and Swing*