# 🚂 Railway Reservation System - Quick Reference

## 🚀 Start Application
```bash
# Linux/Mac
./run.sh

# Windows  
run.bat
```

## 🔑 Default Login
- **Admin**: admin / admin123
- **Customer**: Register new account

## 📂 Project Structure
```
src/
├── models/         → Train, Passenger, User, Booking
├── exceptions/     → 6 custom exceptions
├── utils/          → DatabaseManager, SessionManager, ValidationUtils
└── gui/            → 14 panels (Login, Dashboard, Booking, etc.)
```

## 🎯 Main Features

### Customer:
- 🔍 Search trains by route & date
- 🎫 Book tickets (1-6 passengers)
- 📋 View & cancel bookings
- 👤 Manage profile

### Admin:
- 🚂 Add/Delete trains
- 📊 View all bookings
- 👥 View all users
- 📈 Reports & analytics

## 🎨 Sample Data

### Pre-loaded Trains:
| Number | Name | Route |
|--------|------|-------|
| 12345 | Rajdhani Express | Delhi → Mumbai |
| 12346 | Shatabdi Express | Delhi → Chandigarh |
| 12347 | Duronto Express | Mumbai → Kolkata |
| 12348 | Garib Rath | Delhi → Bangalore |
| 12349 | Humsafar Express | Chennai → Delhi |

## ✅ Validation Rules
- **Email**: valid format (user@domain.com)
- **Phone**: 10 digits, starts with 6-9
- **Username**: 3-20 alphanumeric + underscore
- **Password**: min 6 characters
- **Age**: 1-120
- **Seats**: 1-6 per booking

## 💳 Payment Methods
- Credit Card
- Debit Card
- UPI
- Net Banking
- Cash

## 🎫 Booking Flow
1. Search → 2. Select Train → 3. Add Passengers → 4. Choose Payment → 5. Confirm → 6. Get PNR

## 📊 Admin Reports
- Total users, trains, bookings, revenue
- Train statistics by type
- Occupancy rates
- Revenue by payment method
- Daily/total bookings

## 🛠️ Tech Stack
- **Language**: Java 8+
- **GUI**: Swing
- **Architecture**: MVC + OOP
- **Design Patterns**: Singleton, Factory, MVC
- **Data Storage**: In-memory HashMap

## 📝 Files Count
- **Java Files**: 27
- **Model Classes**: 4
- **Exception Classes**: 6
- **Utility Classes**: 3
- **GUI Classes**: 14
- **Documentation**: 5 MD files

## 🎯 Key Classes

### Models:
- `Train` - Train entity with booking logic
- `User` - User accounts (Customer/Admin)
- `Passenger` - Passenger details
- `Booking` - Reservation with PNR

### Managers:
- `DatabaseManager` - Data storage (Singleton)
- `SessionManager` - User sessions (Singleton)
- `ValidationUtils` - Input validation

### GUI:
- `RailwayReservationApp` - Main window
- `CustomerDashboard` - Customer interface
- `AdminDashboard` - Admin interface
- `BookingDialog` - Ticket booking form

## 🔒 Security
- Password authentication
- Session management  
- Role-based access (Customer/Admin)
- Input validation
- Error handling

## 🚨 Common Operations

### Add Train (Admin):
1. Manage Trains → Add Train
2. Fill: Number, Name, Route, Date, Times, Seats, Fare, Type
3. Save

### Book Ticket (Customer):
1. Search Trains → Select train → Book
2. Select passengers count
3. Fill passenger details
4. Choose payment → Confirm

### Cancel Booking:
1. My Bookings → Find booking → Cancel
2. Confirm cancellation
3. Seats released automatically

## 📱 Navigation

### Customer Sidebar:
- 🔍 Search Trains
- 📋 My Bookings
- 👤 Profile

### Admin Sidebar:
- 🚂 Manage Trains
- 📋 View Bookings
- 👥 View Users
- 📊 Reports

## 💡 Tips
- ✅ Always note your PNR after booking
- ✅ Check seat availability before booking
- ✅ Cancel early if plans change
- ✅ Verify passenger details before confirming
- ✅ Admin can monitor system stats in Reports

## 📄 Documentation
- `README.md` - Overview
- `SETUP.md` - Installation
- `FEATURES.md` - Complete features
- `USER_GUIDE.md` - Step-by-step guide
- `PROJECT_SUMMARY.md` - Project details

## 🎓 OOP Concepts Used
- ✅ Encapsulation (private fields, getters/setters)
- ✅ Inheritance (exception hierarchy)
- ✅ Polymorphism (method overriding)
- ✅ Abstraction (interfaces, abstract concepts)

## 🏆 Design Patterns
1. **Singleton** - DatabaseManager, SessionManager
2. **Factory** - ID generation
3. **MVC** - Model-View-Controller separation
4. **Builder** - Complex object creation

## 🔄 Status Types
- **Booking**: CONFIRMED, WAITING, CANCELLED, COMPLETED
- **User**: ACTIVE, INACTIVE
- **Train**: Available, Full

## 📊 Statistics Tracked
- Total bookings
- Confirmed vs Cancelled
- Revenue (total & by payment)
- Occupancy rates
- Daily bookings
- User registrations

## 🚀 Quick Commands
```bash
# Compile
cd src && javac -d ../bin */*.java

# Run
cd bin && java gui.RailwayReservationApp

# Clean
rm -rf bin/*

# List structure
tree src/
```

## 🎯 Project Highlights
- ✨ Modern GUI with attractive design
- 🎨 Professional color scheme
- 🔐 Secure authentication
- 📊 Real-time statistics
- 🎫 Complete booking system
- 📈 Admin analytics
- 📝 Comprehensive documentation
- 🔧 Modular architecture
- ⚡ Exception handling
- 🚀 Easy to run

---

**Quick Tip**: Start with admin login to explore all features, then register as customer to test booking flow! 🎉
