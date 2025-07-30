# Selenium POM Parallel TestNG Example for SauceDemo

## Features

- Page Object Model (POM) for maintainable tests
- Works with Chrome, Firefox, Safari, and headless browsers
- Parallel execution with TestNG
- ExtentReports with screenshots on pass/fail/skip
- Auto browser driver management
- GitHub Actions CI support (see `.github/workflows/test.yml`)

## Structure

- `factory/DriverFactory.java`: Thread-safe WebDriver for Chrome/Firefox/Safari/Headless
- `pages/`: Page Object classes (Login, Dashboard, PIM)
- `tests/`: TestNG test classes + BaseTest for reporting
- `utils/`: ExtentReports and screenshot utilities
- `pom.xml`: Maven config
- `testng.xml`: Parallel TestNG suite
- `.github/workflows/test.yml`: GitHub Actions for CI

## Running Locally

### Default (Chrome):
```sh
mvn clean test
```

### Firefox:
```sh
mvn clean test -Dbrowser=firefox
```

### Safari (Mac only):
```sh
mvn clean test -Dbrowser=safari
```

### Headless (Chrome or Firefox):
```sh
mvn clean test -Dheadless=true
mvn clean test -Dbrowser=firefox -Dheadless=true
```

### Parallelism

Controlled by `testng.xml` (`parallel="classes"`).

## GitHub Actions

On push/PR, runs on Chrome and Firefox, headless and headed. Artifacts include ExtentReport and screenshots.

## Reports

- Open `test-output/ExtentReport.html` after a run.

## Notes

- Update the PIM page code if your app has a real PIM module.
- Adjust locators as SauceDemo evolves.