# Desktop Shortcut Instructions

## Creating a Desktop Shortcut on Windows

### Method 1: Manual Shortcut Creation (Easiest)

1. **Navigate** to your project folder:
   ```
   C:\Users\Asus\Downloads\Railway-Reservation-Management-main\Railway-Reservation-Management-main
   ```

2. **Right-click** on `LAUNCH.bat`

3. Select **"Send to" → "Desktop (create shortcut)"**

4. **Done!** You now have a desktop shortcut

### Method 2: Custom Icon (Optional)

1. Follow Method 1 first to create the shortcut

2. **Right-click** the desktop shortcut → **Properties**

3. Click **"Change Icon"** button

4. Browse and select an icon (or use default)

5. Click **OK** → **Apply**

---

## Using the Shortcut

Simply **double-click the desktop shortcut** to:
1. ✅ Automatically compile the project
2. ✅ Launch the application
3. ✅ Start using the Railway Reservation System!

---

## Troubleshooting

### If the shortcut doesn't work:

1. **Check Java is installed**:
   - Open Command Prompt
   - Type: `java -version`
   - Should show Java 8 or higher

2. **Update shortcut path**:
   - Right-click shortcut → Properties
   - Verify "Start in" points to project folder
   - Verify "Target" points to LAUNCH.bat

3. **Run as Administrator** (if needed):
   - Right-click shortcut
   - Select "Run as administrator"

---

## What LAUNCH.bat Does

```batch
1. Compiles all Java files
2. Displays compilation status
3. Launches the application
4. Shows any errors if they occur
```

---

**That's it! Enjoy your one-click Railway Reservation System!** 🚂
