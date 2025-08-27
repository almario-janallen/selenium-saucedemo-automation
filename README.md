# Selenium SauceDemo Automation

This project contains automated test scripts for [SauceDemo](https://www.saucedemo.com/) using **Selenium WebDriver**, **Java**, and **TestNG**.  
The goal is to demonstrate end-to-end testing of an e-commerce workflow — from login to check out — using clean automation practices.

## 🚀 Features
- **Login Tests**: Valid/invalid credentials, locked-out users.
- **Cart Tests**: Add to cart, remove from cart, cart badge count validation.
- **Checkout Tests**: User info validation, order overview, complete checkout.
- **E2E Happy Path**: Full flow from login → add to cart → checkout → order confirmation.
- **Page Object Model (POM)** for maintainability and reusability.
- Logging and reporting support.

## 🛠️ Tech Stack
- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Maven** (for dependencies and build)

## 📂 Project Structure
```markdown
src/
├── main/
│    └── java/
│         ├── pages/       # Page Object classes (e.g., LoginPage)
│         └── utils/       # Helper classes (WebDriverFactory, ExcelUtil, etc.)
└── test/
     ├── java/
     │     ├── listeners/   # TestNG listeners (TestListener)
     │     └── tests/       # Test classes (LoginTest)
     └── resources/         # Test data files (Excel, CSV)
           └──testdata/
```
## ▶️ Running Tests
1. Clone the repo:
   ```bash
   git clone https://github.com/your-username/selenium-saucedemo-automation.git
2. Navigate to project folder
    ```bash
   cd selenium-saucedemo-automation
3. Run with Maven
    ```bash
   mvn clean test

📊 Test Coverage
- Login functionality
- Cart operations
- Checkout flow
- End-to-end purchase journey