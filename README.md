
# Clients Enrollment API Automation Framework

## Overview
This framework automates the testing of API endpoints related to client enrollment and its associated features. It is built using the **Cucumber framework**, which allows for Behavior-Driven Development (BDD) by defining test scenarios in Gherkin syntax. The framework includes test cases for various client and product interactions, such as retrieving customer lists, managing subscriptions, processing payments, and applying promo codes.

### Note
The original project this example is based on is proprietary and cannot be shared. This project uses a placeholder base URL and endpoints that will not respond in a live environment.

## Project Structure
- **`src/test/java/com/clients/step_definitions`** - Contains step definitions implementing Cucumber steps for API interactions, including customer retrieval, payment processing, and subscription management.
- **`src/test/resources/features`** - Holds Cucumber feature files defining test scenarios for API interactions, written in Gherkin.
- **`config.properties`** - Holds configuration details like the base URL (non-functional in this example).

## Prerequisites
- Java 17+
- Maven
- Cucumber dependencies specified in `pom.xml`

## Reports
Test results generate HTML and JSON reports, accessible in the `target` directory after execution.

## License
The original project is proprietary and cannot be shared. This example version is provided for demonstration purposes only.
