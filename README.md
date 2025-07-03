# 🍕 Pizza Hut Demo - Command Line Ordering System

A comprehensive command-line application for Pizza Hut ordering system built with Java and Gradle. This application demonstrates object-oriented programming principles, JSON data handling, interactive user interface design, and advanced customization features including multiple pizza sizes and addon management with dynamic pricing.

## 📋 Table of Contents

- [About The Project](#about-the-project)
- [Features](#features)
- [Built With](#built-with)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [Contact](#contact)

## 🎯 About The Project

This Pizza Hut Demo application is part of a Java Training Program, designed to showcase fundamental Java development skills including:

- **Object-Oriented Programming**: Clean separation of concerns with dedicated classes
- **JSON Data Handling**: Dynamic menu loading from external JSON files
- **User Input Validation**: Robust error handling and input validation
- **Interactive CLI Design**: Intuitive command-line user interface
- **Build Automation**: Gradle-based project structure and dependency management

The application simulates a real-world pizza ordering system where customers can browse menus, view detailed product information, and place orders with automatic receipt generation.

## ✨ Features

### 🏠 **Home Interface**
- Welcome screen with clear navigation options
- Menu browsing and order placement options
- Graceful exit functionality

### 📖 **Menu Management**
- Dynamic menu loading from JSON configuration (`menuList.json`)
- Multiple pizza sizes support (Large, Medium, Small) with different pricing
- Detailed pizza information display (name, description, size-specific prices)
- Interactive menu navigation with comprehensive input validation

### 🛒 **Advanced Ordering System**
- Support for up to 3 pizza selections per order
- **Size Selection**: Choose from Large, Medium, or Small for each pizza
- **Addon Customization**: Add multiple addons to each pizza
  - Add Cheese (+100.00 LKR)
  - Add Vegetable (+75.00 LKR)
  - Add Prawns (+125.00 LKR)
- **Dynamic Pricing**: Real-time price calculation including base pizza + addons
- Flexible ordering process (can complete order at any stage)
- Real-time order management and validation

### 🧾 **Professional Receipt Generation**
- Detailed receipt formatting with itemized breakdown
- Shows base pizza price by size
- Lists all selected addons with individual prices
- Automatic total calculation for each pizza and overall order
- Professional formatting with clear order summary

### 🛡️ **Robust Error Handling**
- Comprehensive input validation at every step
- User-friendly error messages with clear guidance
- Graceful fallback mechanisms for invalid inputs
- Safe navigation between different menu levels

### 🎨 **Enhanced User Experience**
- Clean, formatted CLI interface
- Intuitive navigation with clear instructions
- Option to go back at any step
- Continuous addon selection until user is satisfied
- Real-time feedback on selections and pricing

## 🛠 Built With

- **Java 21** - Core programming language
- **Gradle** - Build automation and dependency management
- **Gson 2.10.1** - JSON parsing and serialization
- **IntelliJ IDEA** - Development environment

## 🚀 Getting Started

### Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
  ```bash
  java -version
  ```
- **Gradle** (or use the included Gradle Wrapper)
  ```bash
  gradle -version
  ```

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd PizzaHutDemo
   ```

2. **Build the project**
   ```bash
   ./gradlew build
   ```
   *On Windows:*
   ```cmd
   gradlew.bat build
   ```

3. **Run the application**
   ```bash
   ./gradlew run
   ```
   *On Windows:*
   ```cmd
   gradlew.bat run
   ```

   *Alternative: Run directly with Java*
   ```bash
   java -cp build/classes/java/main org.pizzastore.Main
   ```

## 📖 Usage

### Starting the Application

Launch the application using the run command above. You'll be greeted with:

```
================================================================================
 Welcome to PizzaHut App by Shenal! Now you can order pizzas in different sizes!
================================================================================

To View our menu, press [1]
To place an Order, press [2]
Press [x] to exit the store :
```

### Navigation Guide

#### **Menu Viewing** (Press `1`)
- Browse all available pizzas with prices for all sizes (Large, Medium, Small)
- Select any pizza number to view detailed information
- Press `0` to return to the main menu

#### **Placing Orders** (Press `2`)
The ordering process includes these steps for each pizza:

1. **Pizza Selection**: Choose from the available pizzas
2. **Size Selection**: Choose Large, Medium, or Small
3. **Addon Customization**: Add optional addons like cheese, vegetables, or prawns
4. **Order Continuation**: Add up to 3 pizzas total

**Order Options:**
- Select up to 3 pizzas for your order
- Press `E` to complete order early (after selecting at least 1 pizza)
- Press `0` at any time to return to the main menu
- Press `N` to skip addons for any pizza
- Automatic receipt generation upon order completion

**Sample Order Flow:**
```
You selected: Margherita
Please select size:
Press [L] for Large - 1500.00 LKR
Press [M] for Medium - 1200.00 LKR
Press [S] for Small - 900.00 LKR

Would you like to add any addons to your Margherita (Large)?
Available Addons:
================
#1 Add Cheese - 100.00 LKR
#2 Add Vegetable - 75.00 LKR
#3 Add Prawns - 125.00 LKR

Press addon number to add it to your pizza
Press [N] to skip addons and continue
```

#### **Exiting** (Press `x`)
- Safe application termination with farewell message

### Key Components

- **`Main.java`**: Application entry point and user interface logic
- **`Pizza.java`**: Data model representing pizza items with encapsulation
- **`MenuService.java`**: Service layer for menu operations and JSON data loading
- **`menuList.json`**: External configuration file containing pizza menu data

### Architecture Overview

The application follows a clean architecture pattern with clear separation of concerns:

- **Model Layer**: `Pizza.java`, `Addon.java`, `PizzaOrder.java` - Data models with encapsulation
- **Service Layer**: `MenuService.java`, `AddonService.java` - Business logic and data operations
- **Presentation Layer**: `Main.java` - User interface and interaction handling
- **Configuration**: JSON files for external data management

## 🤝 Contributing

Contributions are welcome! Here's how you can help improve this project:

### Development Setup

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. **Make your changes**
4. **Run tests** (when available)
   ```bash
   ./gradlew test
   ```
5. **Commit your changes**
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
6. **Push to the branch**
   ```bash
   git push origin feature/AmazingFeature
   ```
7. **Open a Pull Request**



This is a coding project done by me (ShenalSen) under ActVue©
