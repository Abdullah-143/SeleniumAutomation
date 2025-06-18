# Selenium Automation Framework

This is a scalable Selenium Test Automation Framework built with Java, TestNG, Maven, and WebDriverManager. It supports parallel cross-browser execution with Chrome and Firefox and follows a clean Page Object Model design

---

## 🔧 Pre-requisites

- Java 20
- Maven 3.6+
- IntelliJ IDEA (or any IDE)
- Chrome and Firefox browsers installed
- Internet access (for WebDriver binaries via WebDriverManager)

---

## ⚙️ Configuration

Update the `config.properties` file located at:

```
src/main/resources/testdata/config.properties
```

With the following keys:

```properties
browser=chrome        # Can be overridden in testNG.xml
website_url=https://your.test.website
```

---

## 🚀 How to Run Tests in Parallel (Chrome + Firefox)

### Step 1: Clean and Run via Maven

```bash
mvn clean test
```

### Step 2: Run Directly from IntelliJ

Right-click `testNG.xml` → **Run 'testNG.xml'**

### Parallel Execution Configuration (testNG.xml)

```xml
<suite name="ParallelTestSuite" parallel="tests" thread-count="2">
    <test name="ChromeTest">
        <parameter name="browser" value="chrome"/>
        <classes>
            <class name="tests.TestAssement"/>
        </classes>
    </test>
    <test name="FirefoxTest">
        <parameter name="browser" value="firefox"/>
        <classes>
            <class name="tests.TestAssement"/>
        </classes>
    </test>
</suite>
```

---

## 📄 Reports

After execution, reports are available in the `/reports` directory.

Check the following files:

- `extent-report.html`

---

## 🧠 Notes

- Uses WebDriverManager (no manual WebDriver setup needed)
- Thread-safe parallel execution using `ThreadLocal<WebDriver>`
- Browser type passed via `<parameter>` in `testNG.xml`
- Reports are generated after every test run

---

## 👨‍💻 Author

**Abdullah Shaikh**  
Senior QA Automation Engineer

---

## ✅ TODO (Optional Enhancements)

- Add ExtentReports or Allure for advanced reporting
- Integrate Jenkins for CI execution
- Add Docker support for isolated browser execution
