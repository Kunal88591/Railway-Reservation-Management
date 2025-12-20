@echo off
cls
echo ========================================
echo Railway Reservation Management System
echo ========================================
echo.
echo Initializing application...
echo.

echo [Step 1/2] Compiling source files...
javac -encoding UTF-8 -d bin -sourcepath src src\gui\*.java src\models\*.java src\utils\*.java src\exceptions\*.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [ERROR] Compilation failed! Please check the errors above.
    echo.
    echo Press any key to exit...
    pause > nul
    exit /b 1
)

echo [Step 1/2] Compilation completed successfully! ✓
echo.

echo [Step 2/2] Starting application...
echo.
java -cp bin gui.RailwayReservationApp

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [ERROR] Application failed to start!
    echo.
    echo Press any key to exit...
    pause > nul
    exit /b 1
)

echo.
echo Application closed successfully.
pause
