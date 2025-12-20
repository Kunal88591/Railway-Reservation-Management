@echo off
REM Railway Reservation System - Build and Run Script for Windows

echo Railway Reservation Management System
echo ==========================================
echo.

REM Create bin directory if it doesn't exist
if not exist "bin" (
    mkdir bin
    echo Created bin directory
)

REM Compile all Java files
echo Compiling Java files...
cd src

REM Find and compile all Java files
dir /s /B *.java > sources.txt
javac -d ..\bin @sources.txt

REM Check if compilation was successful
if %errorlevel% equ 0 (
    echo Compilation successful!
    del sources.txt
    
    REM Run the application
    echo.
    echo Starting Railway Reservation System...
    echo.
    cd ..\bin
    java gui.RailwayReservationApp
) else (
    echo Compilation failed!
    del sources.txt
    pause
    exit /b 1
)

pause
