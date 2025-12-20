# 🚀 Quick Start Shortcut Setup

This guide helps you create a desktop shortcut to launch the Railway Reservation System with a single click.

---

## 🪟 Windows Setup

### Method 1: Double-Click START.bat
1. Simply **double-click** `START.bat` in the project folder
2. The application will compile and launch automatically

### Method 2: Create Desktop Shortcut
1. Right-click on `START.bat`
2. Select **"Send to"** → **"Desktop (create shortcut)"**
3. (Optional) Rename the shortcut to "Railway Reservation"
4. (Optional) Right-click shortcut → **Properties** → **Change Icon** to customize

**Alternative:** Right-click `run.bat` → Create Shortcut → Move to Desktop

---

## 🐧 Linux Setup

### Method 1: Desktop File (Recommended)
1. Make the desktop file executable:
   ```bash
   chmod +x Railway-Reservation.desktop
   chmod +x run.sh
   ```

2. **For GNOME/Ubuntu Desktop:**
   ```bash
   # Copy to desktop
   cp Railway-Reservation.desktop ~/Desktop/
   chmod +x ~/Desktop/Railway-Reservation.desktop
   
   # Or install to applications menu
   mkdir -p ~/.local/share/applications
   cp Railway-Reservation.desktop ~/.local/share/applications/
   ```

3. Double-click the icon on your desktop to launch!

### Method 2: Simple Script Launcher
1. Make START.sh executable:
   ```bash
   chmod +x START.sh
   ```

2. Create a desktop launcher:
   ```bash
   # Copy to desktop
   cp START.sh ~/Desktop/
   ```

3. Double-click `START.sh` to launch

### Method 3: Application Menu Entry
1. Edit `Railway-Reservation.desktop` and update the `Exec` path:
   ```
   Exec=bash -c "cd /path/to/your/Railway-Reservation-Management && ./run.sh"
   ```
   Replace `/path/to/your/` with your actual project path

2. Install to applications:
   ```bash
   desktop-file-install --dir=~/.local/share/applications Railway-Reservation.desktop
   update-desktop-database ~/.local/share/applications
   ```

3. Search for "Railway Reservation System" in your application launcher

---

## 🍎 macOS Setup

### Method 1: Terminal Shortcut
1. Make START.sh executable:
   ```bash
   chmod +x START.sh
   chmod +x run.sh
   ```

2. Create an Automator application:
   - Open **Automator**
   - Choose **Application**
   - Add **"Run Shell Script"** action
   - Paste:
     ```bash
     cd /path/to/Railway-Reservation-Management
     ./START.sh
     ```
   - Save as `Railway Reservation.app` to Desktop

3. Double-click the app to launch!

### Method 2: Dock Shortcut
1. Follow Method 1 to create the `.app`
2. Drag the `.app` to your Dock for quick access

---

## ✨ Quick Test

After setup, test your shortcut:

1. **Click the shortcut** → Application should compile and launch
2. **Login** with default credentials:
   - Username: `admin`
   - Password: `admin123`
3. Explore the booking system!

---

## 🔧 Troubleshooting

### Windows
- **"Java is not recognized"**: Add Java to PATH environment variable
- **Window closes immediately**: Right-click shortcut → Properties → Change "Run" to "Maximized"

### Linux
- **"Permission denied"**: Run `chmod +x run.sh START.sh Railway-Reservation.desktop`
- **Desktop file won't launch**: Right-click → Properties → Allow executing file as program

### macOS
- **"Cannot be opened"**: Right-click → Open → Click Open (to bypass Gatekeeper)
- **Java not found**: Install Java JDK 8 or later from Oracle or use Homebrew

---

## 📝 Notes

- First launch compiles the code (takes ~10 seconds)
- Subsequent launches are faster (uses compiled classes)
- Default credentials are in [docs/SETUP.md](docs/SETUP.md)
- For development, use your IDE for better debugging

---

## 🎯 Pro Tip

Create shortcuts for both **Development** and **Production**:
- **Dev Shortcut**: Opens in IDE (IntelliJ/Eclipse/VS Code)
- **Run Shortcut**: Uses START.bat/START.sh for quick testing

Happy booking! 🚂💨
