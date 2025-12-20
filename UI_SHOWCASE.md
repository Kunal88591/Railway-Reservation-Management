# 🎯 Railway Reservation System - Project Showcase

## 📸 What You'll See

This document describes what each screen of the application looks like and what it does.

---

## 🎨 User Interface Tour

### 1. Login Screen 🔐
**First Impression:**
- Large centered login form with blue border
- Railway emoji (🚂) in title
- Professional color scheme
- Two prominent buttons: Login (Blue) & Register (Green)
- Shows default admin credentials
- Clean, modern design

**What You Can Do:**
- Enter username and password
- Click Login to access system
- Click Register to create new account
- See immediate feedback on errors

---

### 2. Registration Screen ✍️
**Design:**
- Green-bordered form (indicates new account)
- "Create New Account" heading
- 7 input fields in organized layout
- Two buttons: Register (Green) & Back to Login (Dark)

**Input Fields:**
1. Full Name
2. Username
3. Email
4. Phone Number
5. Password
6. Confirm Password

**Features:**
- Real-time validation
- Clear error messages
- Password masking
- All fields required

---

### 3. Customer Dashboard 🏠

#### Navigation Bar (Top)
- **Left**: 🚂 Railway Reservation System
- **Center**: Welcome message with user name
- **Right**: Red Logout button
- **Background**: Professional blue (#2980b9)

#### Sidebar (Left)
Dark background with white text icons:
- 🔍 Search Trains
- 📋 My Bookings
- 👤 Profile

Hover Effect: Changes to blue

#### Main Content Area
Changes based on sidebar selection (CardLayout)

---

### 4. Search Trains Panel 🔍

**Search Form (Top Section):**
- White card with shadow
- "Search Trains" heading in blue
- Three inputs in a row:
  - **From**: Dropdown (cities)
  - **To**: Dropdown (cities)
  - **Date**: Text field (YYYY-MM-DD)
- Blue "Search Trains" button with magnifying glass icon

**Results Table (Bottom Section):**
- Professional table with headers:
  - Train No. | Train Name | Type | Departure | Arrival | Available Seats | Fare | Action
- Header row: Blue background, white text
- Each row height: 40px
- Green "Book" button in Action column
- Scrollable if many results

**Sample Data Display:**
```
12345 | Rajdhani Express | Superfast | 16:00 | 08:30 | 100 | ₹1500.00 | [Book]
```

---

### 5. Booking Dialog 🎫

**Modal Dialog (700x600):**

**Top Section - Train Details:**
- Light blue card showing:
  - Train: 12345 - Rajdhani Express
  - Route: Delhi → Mumbai
  - Available Seats: 100

**Middle Section - Passenger Form:**
- Number of passengers selector (1-6)
- Dynamic passenger forms (changes with count)
- Each passenger card shows:
  - Name field
  - Age spinner
  - Gender dropdown
  - ID Type dropdown
  - ID Number field
- All in bordered cards labeled "Passenger 1", "Passenger 2", etc.

**Bottom Section:**
- Payment method dropdown
- **Large Total Fare** in green: "Total Fare: ₹1500.00"
- Green "Confirm Booking" button
- Red "Cancel" button

---

### 6. My Bookings Panel 📋

**Header:**
- "My Bookings" title (blue)
- Blue "🔄 Refresh" button on right

**Table:**
- Columns: PNR | Train | Date | Passengers | Fare | Status | Action
- Status shown as: CONFIRMED, CANCELLED
- Action button:
  - Red "Cancel" for confirmed bookings
  - Blue "View" for cancelled/completed

**Features:**
- Click Cancel: Shows confirmation dialog
- Click View: Shows complete booking details in popup
- Auto-refresh after actions
- Scrollable table

**Sample Row:**
```
PNR123... | 12345 - Rajdhani | 25 Dec 2025 | 2 | ₹3000.00 | CONFIRMED | [Cancel]
```

---

### 7. Profile Panel 👤

**Design:**
- Large white card with user icon (👤) in title
- Two-column layout

**Information Displayed:**
- User ID
- Username
- Full Name
- Email
- Phone
- Role (CUSTOMER/ADMIN)
- Member Since (formatted date)
- Status (Active ✓ in green)

**Action:**
- Yellow "Change Password" button at bottom
- Opens dialog with 3 password fields

---

### 8. Admin Dashboard 🔧

#### Top Bar
- **Red background** (different from customer)
- "🔧 Admin Dashboard" title
- "Admin: [name]" in center
- Dark "Logout" button

#### Sidebar
Same dark background, different menu:
- 🚂 Manage Trains
- 📋 View Bookings
- 👥 View Users
- 📊 Reports

---

### 9. Manage Trains Panel 🚂

**Header:**
- "Manage Trains" title
- Green "➕ Add Train" button
- Blue "🔄 Refresh" button

**Table:**
- Columns: Train No. | Name | Source | Destination | Date | Departure | Total Seats | Available | Fare | Actions
- Red "Delete" button in Actions column
- Shows all trains in system

**Add Train Dialog:**
- Modal popup (600x500)
- Scrollable form with fields:
  - Train Number
  - Train Name
  - Source City
  - Destination City
  - Date (date picker)
  - Departure Time (HH:MM)
  - Arrival Time (HH:MM)
  - Total Seats (spinner: 10-500)
  - Fare (decimal)
  - Type (dropdown: Express/Superfast/Passenger)
- Green "Save" and Red "Cancel" buttons

---

### 10. View All Bookings Panel 📊

**Statistics Cards (Top):**
Four colored cards in a row:
1. **Blue Card**: Total Bookings
2. **Green Card**: Confirmed bookings count
3. **Red Card**: Cancelled bookings count
4. **Yellow Card**: Total Revenue

**Table Below:**
- All bookings from all users
- Columns: PNR | User ID | Train | Date | Passengers | Fare | Status | Booking Date
- Read-only (no action buttons)
- Sortable by clicking headers
- Scrollable

---

### 11. View Users Panel 👥

**Header:**
- "All Users" title
- Blue "🔄 Refresh" button

**Table:**
- Columns: User ID | Username | Full Name | Email | Phone | Role | Registration Date | Status
- Shows all registered users
- Status: Active/Inactive
- Role: CUSTOMER/ADMIN
- Read-only view
- Scrollable

**Sample Data:**
```
U000001 | admin | System Admin | admin@railway.com | 9876543210 | ADMIN | 20 Dec 2025 | Active
U000002 | john_doe | John Doe | john@email.com | 9123456789 | CUSTOMER | 20 Dec 2025 | Active
```

---

### 12. Reports Panel 📈

**Layout:**
Vertical scrollable panel with multiple sections

**Section 1 - Overall Statistics:**
Four large stat cards:
- Total Users (Blue border)
- Total Trains (Green border)
- Total Bookings (Yellow border)
- Total Revenue (Red border)

Each card shows:
- Title on top
- Large number in center (colored)

**Section 2 - Train Statistics:**
White card with:
- "Train Statistics" heading
- Text area showing:
  - Total trains
  - Trains by type breakdown
  - Total seats
  - Occupied seats
  - Occupancy rate percentage

**Section 3 - Booking Statistics:**
Similar card showing:
- Total bookings
- Bookings by status breakdown
- Today's bookings
- Total passengers
- Average booking value

**Section 4 - Revenue Report:**
Card displaying:
- Total revenue
- Today's revenue
- Revenue breakdown by payment method

---

## 🎨 Color Scheme

### Primary Colors:
- **Primary Blue**: #2980b9 (Headers, buttons)
- **Secondary Blue**: #3498db (Hover effects)
- **Success Green**: #27ae60 (Success, confirm)
- **Danger Red**: #e74c3c (Delete, cancel)
- **Warning Yellow**: #f1c40f (Warnings, change)
- **Dark Gray**: #2c3e50 (Sidebar, text)
- **Light Gray**: #ecf0f1 (Background)
- **White**: #ffffff (Cards, forms)

### Typography:
- **Title**: Segoe UI Bold 28px
- **Heading**: Segoe UI Bold 18px
- **Subheading**: Segoe UI Bold 14px
- **Normal**: Segoe UI Regular 13px
- **Small**: Segoe UI Regular 11px

---

## ✨ Interactive Elements

### Buttons:
- **Rounded corners**
- **Shadow on hover**
- **Color darkens on hover**
- **Hand cursor on hover**
- **40px height standard**

### Input Fields:
- **Light border**
- **Padding: 8px 12px**
- **Focus: Blue border**
- **35-40px height**

### Tables:
- **Striped rows** (alternating colors)
- **Hover effect** on rows
- **Blue header** background
- **40-45px row height**
- **Sortable columns**

### Dialogs:
- **Modal** (blocks background)
- **Centered** on screen
- **Shadow effect**
- **Close button** (X)
- **Confirm/Cancel buttons**

---

## 🎭 User Experience Features

### Feedback:
- ✅ **Success**: Green checkmark, "Success!" message
- ❌ **Error**: Red X, descriptive error message
- ⚠️ **Warning**: Yellow triangle, warning text
- ❓ **Confirm**: Question icon, Yes/No buttons

### Transitions:
- **Smooth panel switching** (CardLayout)
- **No page reloads**
- **Instant updates**
- **Loading indicators** where needed

### Accessibility:
- **Clear labels** on all inputs
- **Tooltips** on buttons
- **Keyboard navigation** supported
- **Tab order** logical
- **Focus indicators** visible

---

## 📱 Responsive Behavior

### Window Size: 1200x700
- Minimum size enforced
- Centered on screen
- Scrollbars appear when needed
- Panels adapt to content

### Components:
- **Tables**: Horizontal scroll if needed
- **Forms**: Vertical scroll if many fields
- **Cards**: Stack vertically on small space
- **Buttons**: Maintain minimum size

---

## 🎯 Navigation Flow

### Customer Journey:
```
Login → Dashboard → Search Trains → Book Ticket → My Bookings → Profile
           ↓          ↓               ↓             ↓            ↓
        Sidebar    Filter         Confirm      View/Cancel   Change Password
```

### Admin Journey:
```
Login → Admin Dashboard → Manage Trains → View Bookings → View Users → Reports
           ↓                  ↓               ↓              ↓           ↓
        Sidebar          Add/Delete       Monitor       User Info    Analytics
```

---

## 💡 Visual Highlights

### What Makes It Professional:
1. **Consistent color palette** throughout
2. **Professional spacing** and alignment
3. **Clear visual hierarchy** (titles → headings → content)
4. **Intuitive icons** for better recognition
5. **Subtle shadows** for depth
6. **Smooth hover effects** for interactivity
7. **Clear action buttons** with colors indicating purpose
8. **Well-organized tables** with alternating rows
9. **Modal dialogs** for focused tasks
10. **Statistical cards** with visual appeal

---

## 🏆 Best UI Practices Followed

✅ Consistency in design elements
✅ Clear visual feedback for actions
✅ Intuitive navigation structure
✅ Readable typography and spacing
✅ Color coding for different actions
✅ Confirmation for destructive actions
✅ Error prevention through validation
✅ Helpful error messages
✅ Logical grouping of related items
✅ Responsive to user interactions

---

**The entire UI is designed to be intuitive, professional, and user-friendly!** 🎨✨
