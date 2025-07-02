# 🍕 Pizza Hut Demo - Command Line Ordering System

A simple yet comprehensive command-line application for Pizza Hut ordering system built with Java and Gradle. This application demonstrates object-oriented programming principles, JSON data handling, and interactive user interface design.

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
- Dynamic menu loading from JSON configuration
- Detailed pizza information display (name, description, price)
- Interactive menu navigation with input validation

### 🛒 **Ordering System**
- Support for up to 3 pizza selections
- Flexible ordering process (can complete order at any stage)
- Real-time order management and validation

### 🧾 **Receipt Generation**
- Professional receipt formatting
- Automatic total calculation
- Order summary with itemized details

### 🛡️ **Error Handling**
- Comprehensive input validation
- User-friendly error messages
- Graceful fallback mechanisms

## 🛠 Built With

- **Java 17+** - Core programming language
- **Gradle** - Build automation and dependency management
- **Gson 2.10.1** - JSON parsing and serialization
- **IntelliJ IDEA** - Recommended IDE

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
===================================
 Welcome to PizzaHut App by Shenal!
===================================

To View our menu, press [1]
To place an Order, press [2]
Press [x] to exit the store :
```

### Navigation Guide

#### **Menu Viewing** (Press `1`)
- Browse all available pizzas with prices
- Select any pizza number to view detailed information
- Press `0` to return to the main menu

#### **Placing Orders** (Press `2`)
- Select up to 3 pizzas for your order
- Press `E` to complete order early (after selecting at least 1 pizza)
- Press `0` at any time to return to the main menu
- Automatic receipt generation upon order completion

#### **Exiting** (Press `x`)
- Safe application termination with farewell message

## 📁 Project Structure

```
PizzaHutDemo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── pizzastore/
│   │   │           ├── Main.java          # Main application entry point
│   │   │           ├── Pizza.java         # Pizza data model
│   │   │           └── MenuService.java   # Menu management service
│   │   └── resources/
│   │       └── menuList.json             # Pizza menu configuration
│   └── test/
│       └── java/                         # Test files (future expansion)
├── build.gradle                          # Gradle build configuration
├── gradlew                               # Gradle wrapper (Unix)
├── gradlew.bat                           # Gradle wrapper (Windows)
└── README.md                             # Project documentation
```

### Key Components

- **`Main.java`**: Application entry point and user interface logic
- **`Pizza.java`**: Data model representing pizza items with encapsulation
- **`MenuService.java`**: Service layer for menu operations and JSON data loading
- **`menuList.json`**: External configuration file containing pizza menu data

## ⚙️ Configuration

### Menu Customization

The pizza menu can be easily customized by editing `src/main/resources/menuList.json`:

```json
[
  {
    "id": 1,
    "name": "BBQ Chicken Pizza",
    "description": "BBQ Chicken breast, Mozzarella Cheese, Bell Pepper",
    "price": 1299.00
  }
]
```

### Adding New Dependencies

To add new dependencies, update the `build.gradle` file:

```gradle
dependencies {
    implementation 'com.google.code.gson:gson:2.10.1'
    // Add your dependencies here
}
```

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

### Coding Standards

- Follow Java naming conventions
- Maintain consistent indentation (4 spaces)
- Add comments for complex logic
- Ensure proper error handling
- Write descriptive commit messages

### Potential Enhancements

- [ ] Add unit tests with JUnit
- [ ] Implement pizza customization options
- [ ] Add order history functionality
- [ ] Integrate with database storage
- [ ] Add GUI interface option
- [ ] Implement discount and promotion system

## 📞 Contact

**Shenal** - Java Training Program Participant

Project Link: [https://github.com/yourusername/PizzaHutDemo](https://github.com/yourusername/PizzaHutDemo)

---

*This project is part of the Java Training Program - Task 1*
