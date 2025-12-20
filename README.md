# Railway Reservation Management System

A comprehensive Railway Reservation Management System built with Java and Swing GUI, implementing Object-Oriented Programming principles, exception handling, and modular design.

## 🚀 Features

### Customer Features
- **User Registration & Login**: Secure authentication system
- **Train Search**: Search trains by source, destination, and date
- **Ticket Booking**: Book tickets with passenger details
- **Booking Management**: View and cancel bookings
- **Profile Management**: View and update profile information

### Admin Features
- **Train Management**: Add, view, and delete trains
- **Booking Overview**: View all bookings in the system
- **User Management**: View all registered users
- **Reports & Analytics**: Comprehensive statistics and revenue reports

## 🏗️ Architecture

### Object-Oriented Design
The project follows OOP principles with:
- **Encapsulation**: All model classes have private fields with getters/setters
- **Inheritance**: Exception hierarchy with custom exceptions
- **Abstraction**: Clean separation between data models and business logic
- **Modularity**: Separate packages for models, utilities, exceptions, and GUI

### Project Structure
```
src/
├── models/           # Data models
│   ├── Train.java
│   ├── Passenger.java
│   ├── User.java
│   └── Booking.java
├── exceptions/       # Custom exceptions
│   ├── RailwayReservationException.java
│   ├── BookingException.java
│   ├── AuthenticationException.java
│   ├── ValidationException.java
│   ├── InsufficientSeatsException.java
│   └── TrainNotFoundException.java
├── utils/            # Utility classes
│   ├── DatabaseManager.java
│   ├── SessionManager.java
│   └── ValidationUtils.java
└── gui/              # GUI components
    ├── RailwayReservationApp.java
    ├── UIStyles.java
    ├── LoginPanel.java
    ├── RegisterPanel.java
    ├── CustomerDashboard.java
    ├── AdminDashboard.java
    └── [other panels...]
```

## 🎨 User Interface

The application features a modern, user-friendly GUI with:
- **Attractive Color Scheme**: Professional color palette with proper contrasts
- **Responsive Layout**: Adaptive panels that work seamlessly
- **Interactive Components**: Buttons with hover effects and styled inputs
- **Intuitive Navigation**: Easy-to-use sidebar navigation
- **Clear Feedback**: Success/error messages for all operations

## 🔧 Technical Implementation

### Exception Handling
- Comprehensive exception hierarchy for different error scenarios
- Graceful error handling with user-friendly error messages
- Validation exceptions for input verification
- Booking exceptions for reservation failures

### Data Management
- In-memory database using ConcurrentHashMap for thread-safety
- Singleton pattern for DatabaseManager and SessionManager
- Sample data initialization for testing
- CRUD operations for all entities

### Validation
- Email validation using regex patterns
- Phone number validation (Indian format)
- Username and password strength validation
- Age and seat count validation

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

### Compilation
```bash
# Navigate to the src directory
cd /workspaces/Railway-Reservation-Management/src

# Compile all Java files
javac -d ../bin models/*.java exceptions/*.java utils/*.java gui/*.java

# Or compile all at once
find . -name "*.java" -print | xargs javac -d ../bin
```

### Running the Application
```bash
# Navigate to the bin directory
cd /workspaces/Railway-Reservation-Management/bin

# Run the application
java gui.RailwayReservationApp
```

### Default Credentials
- **Admin Login**:
  - Username: `admin`
  - Password: `admin123`

- **Customer**: Register a new account through the registration page

## 📊 Features Showcase

### Customer Dashboard
1. **Search Trains**: Filter trains by route and date
2. **Book Tickets**: Select train, add passenger details, choose payment method
3. **My Bookings**: View all bookings with PNR, cancel confirmed bookings
4. **Profile**: View user profile and change password

### Admin Dashboard
1. **Manage Trains**: Add new trains, view all trains, delete trains
2. **View Bookings**: Monitor all bookings with status and revenue
3. **View Users**: See all registered users and their details
4. **Reports**: Analytics including:
   - Total users, trains, bookings, and revenue
   - Train statistics by type and occupancy
   - Booking statistics by status
   - Revenue by payment method

## 🎯 Key Design Patterns

1. **Singleton Pattern**: DatabaseManager, SessionManager
2. **MVC Pattern**: Separation of models, views, and controllers
3. **Factory Pattern**: ID generation for users, passengers, and bookings
4. **Observer Pattern**: Table updates on data changes

## 🔐 Security Features

- Password-based authentication
- Session management for logged-in users
- Role-based access control (Customer vs Admin)
- Input validation to prevent invalid data

## 📈 Scalability

The modular design allows easy extension:
- Add new train types
- Implement database connectivity (MySQL, PostgreSQL)
- Add payment gateway integration
- Implement email/SMS notifications
- Add more report types

## 🛠️ Future Enhancements

- Database persistence using JDBC
- Advanced search filters
- Seat selection visualization
- Waiting list management
- Email/SMS notifications
- Print/Download ticket functionality
- Multi-language support

## 👨‍💻 Developer Notes

The codebase follows Java best practices:
- Proper naming conventions
- Comprehensive documentation
- Exception handling at all levels
- Clean code principles
- Reusable components

## 📝 License

This project is developed for educational purposes.

## 🤝 Contributing

Feel free to fork this project and submit pull requests for improvements.

---

**Developed with ❤️ using Java and Swing**