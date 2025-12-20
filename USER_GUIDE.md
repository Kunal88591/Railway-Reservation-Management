# Railway Reservation System - User Guide

## 🎯 Quick Start Guide

### First Time Setup
1. Navigate to the project directory
2. Run the application using one of these methods:
   - **Linux/Mac**: `./run.sh`
   - **Windows**: Double-click `run.bat`
   - **Manual**: Compile and run as per SETUP.md

### Default Login
- **Admin Username**: admin
- **Admin Password**: admin123

## 📱 Customer Guide

### 1. Registration
**Steps:**
1. Click "Register" button on login screen
2. Fill in the registration form:
   - Full Name (minimum 2 characters)
   - Username (3-20 alphanumeric characters)
   - Email (valid email format)
   - Phone (10-digit Indian mobile number: starts with 6-9)
   - Password (minimum 6 characters)
   - Confirm Password (must match)
3. Click "Register"
4. Return to login screen
5. Login with your new credentials

**Validation Tips:**
- ✅ Valid username: `john_doe123`
- ❌ Invalid username: `jd` (too short)
- ✅ Valid email: `user@example.com`
- ❌ Invalid email: `userexample.com`
- ✅ Valid phone: `9876543210`
- ❌ Invalid phone: `1234567890` (must start with 6-9)

### 2. Searching for Trains
**Steps:**
1. After login, you'll see the Search Trains panel
2. Select **From** (Source city) from dropdown
3. Select **To** (Destination city) from dropdown
4. Enter **Date** in format: YYYY-MM-DD (e.g., 2025-12-25)
   - Or use today's date (pre-filled)
5. Click "🔍 Search Trains"
6. View results in the table below

**Sample Routes Available:**
- Delhi → Mumbai (Rajdhani Express)
- Delhi → Chandigarh (Shatabdi Express)
- Mumbai → Kolkata (Duronto Express)
- Delhi → Bangalore (Garib Rath)
- Chennai → Delhi (Humsafar Express)

### 3. Booking Tickets
**Steps:**
1. Find your train in search results
2. Click "Book" button in the Actions column
3. **Booking Dialog Opens:**
   - View train details at top
   - Select number of passengers (1-6)
   - Fill passenger details for each traveler:
     * Name (required)
     * Age (1-120)
     * Gender (Male/Female/Other)
     * ID Proof Type (Aadhaar/PAN/Passport/Driving License)
     * ID Number (required)
4. Select payment method:
   - Credit Card
   - Debit Card
   - UPI
   - Net Banking
   - Cash
5. Review total fare
6. Click "Confirm Booking"
7. **Success!** Note your PNR number and seat numbers

**Booking Tips:**
- Maximum 6 passengers per booking
- All passenger details are mandatory
- Seat numbers are auto-assigned
- Total fare = Base fare × Number of passengers

### 4. Managing Your Bookings
**Steps:**
1. Click "📋 My Bookings" in sidebar
2. View all your bookings in table format
3. Each booking shows:
   - PNR number
   - Train details
   - Journey date
   - Number of passengers
   - Total fare
   - Status (Confirmed/Cancelled)
   - Action button

**Actions Available:**
- **Cancel**: For confirmed bookings
  - Seats are automatically released
  - Status changes to "Cancelled"
- **View**: For cancelled/completed bookings
  - Shows complete booking details
  - Includes passenger list
  - Shows seat numbers

### 5. Profile Management
**Steps:**
1. Click "👤 Profile" in sidebar
2. View your profile information:
   - User ID
   - Username
   - Full Name
   - Email
   - Phone Number
   - Role
   - Registration Date
   - Account Status
3. Click "Change Password" to update password:
   - Enter current password
   - Enter new password (min 6 characters)
   - Confirm new password
   - Click OK

## 🔧 Admin Guide

### Admin Dashboard Overview
After logging in as admin, you'll see four main sections:

### 1. Manage Trains
**View All Trains:**
- Table showing all trains with:
  - Train Number
  - Name
  - Source and Destination
  - Operation Date
  - Departure Time
  - Total and Available Seats
  - Fare
  - Delete action

**Add New Train:**
1. Click "➕ Add Train" button
2. Fill in the form:
   - Train Number (e.g., 12345)
   - Train Name (e.g., Express Train)
   - Source City
   - Destination City
   - Date (YYYY-MM-DD)
   - Departure Time (HH:MM, e.g., 10:00)
   - Arrival Time (HH:MM, e.g., 18:00)
   - Total Seats (10-500)
   - Fare (e.g., 1500.00)
   - Type (Express/Superfast/Passenger)
3. Click "Save"

**Delete Train:**
1. Find the train in the table
2. Click "Delete" button
3. Confirm deletion
4. Train is removed from system

### 2. View Bookings
**Features:**
- Complete list of all bookings in the system
- Statistics cards showing:
  - Total Bookings
  - Confirmed Bookings
  - Cancelled Bookings
  - Total Revenue (from confirmed bookings)
- Sortable table with:
  - PNR
  - User ID
  - Train details
  - Journey date
  - Passenger count
  - Fare
  - Status
  - Booking timestamp

### 3. View Users
**Features:**
- Complete user database
- Table showing:
  - User ID
  - Username
  - Full Name
  - Email
  - Phone Number
  - Role (Customer/Admin)
  - Registration Date
  - Status (Active/Inactive)
- Refresh functionality

### 4. Reports & Analytics
**Overall Statistics:**
- Total Users
- Total Trains
- Total Bookings
- Total Revenue

**Train Statistics:**
- Trains by type (Express, Superfast, Passenger)
- Total seats across all trains
- Occupied seats
- Occupancy rate percentage

**Booking Statistics:**
- Bookings by status
- Today's bookings count
- Total passengers transported
- Average booking value

**Revenue Report:**
- Total revenue
- Today's revenue
- Revenue breakdown by payment method
  - Credit Card
  - Debit Card
  - UPI
  - Net Banking
  - Cash

## 💡 Tips & Tricks

### For Customers:
1. **Search Tips:**
   - Use exact city names from dropdown
   - Can't find route? Try searching nearby cities
   - Trains are available for today and tomorrow

2. **Booking Tips:**
   - Book early for better seat availability
   - Keep ID proof handy while booking
   - Save your PNR number immediately
   - Can book for up to 6 passengers at once

3. **Cancellation:**
   - Only confirmed bookings can be cancelled
   - Cancellation is immediate
   - No partial cancellation (all passengers)
   - Seats are released back to train

### For Admins:
1. **Train Management:**
   - Add trains for future dates in advance
   - Set realistic seat capacities
   - Ensure departure time is before arrival time
   - Delete trains carefully (affects bookings)

2. **Monitoring:**
   - Check reports regularly
   - Monitor occupancy rates
   - Track revenue by payment method
   - Review user registrations

## ❓ Common Questions

**Q: Can I book tickets for past dates?**
A: No, the system prevents booking for dates in the past.

**Q: What happens if I cancel a booking?**
A: The booking status changes to "Cancelled" and seats are released back to the train.

**Q: Can I modify a booking?**
A: Currently, you need to cancel and create a new booking.

**Q: How many passengers can I book for?**
A: You can book 1 to 6 passengers in a single booking.

**Q: What if train is full?**
A: The system will show available seats. If 0, you cannot book.

**Q: Can I change my password?**
A: Yes, from the Profile section using "Change Password" button.

**Q: What payment methods are supported?**
A: Credit Card, Debit Card, UPI, Net Banking, and Cash.

## 🚨 Troubleshooting

**Issue: Cannot login**
- Check username and password
- Ensure account is active
- Try default admin credentials

**Issue: No trains found**
- Verify source and destination are different
- Check if trains exist for that route
- Ensure date is not in the past

**Issue: Cannot book seats**
- Check if enough seats available
- Verify all passenger details are filled
- Ensure date format is correct

**Issue: Application not starting**
- Verify Java is installed (JDK 8+)
- Check compilation was successful
- Try running from command line to see errors

## 📞 Support

For technical issues or questions:
- Check SETUP.md for installation help
- Review FEATURES.md for feature details
- Check console for error messages

---

Enjoy your Railway Reservation System! 🚂✨
