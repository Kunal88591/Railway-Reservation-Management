#!/bin/bash

# Railway Reservation System - Build and Run Script

echo "🚂 Railway Reservation Management System"
echo "=========================================="
echo ""

# Create bin directory if it doesn't exist
if [ ! -d "bin" ]; then
    mkdir bin
    echo "✓ Created bin directory"
fi

# Compile all Java files
echo "📦 Compiling Java files..."
cd src

# Find and compile all Java files
find . -name "*.java" > sources.txt
javac -d ../bin @sources.txt

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    rm sources.txt
    
    # Run the application
    echo ""
    echo "🚀 Starting Railway Reservation System..."
    echo ""
    cd ../bin
    java gui.RailwayReservationApp
else
    echo "✗ Compilation failed!"
    rm sources.txt
    exit 1
fi
