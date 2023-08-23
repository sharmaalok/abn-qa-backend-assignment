# Backend Test Automation Assignment [![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/from-referrer/)

<a href="https://gitpod.io/from-referrer/" style="padding: 10px;">
    <img src="https://gitpod.io/button/open-in-gitpod.svg" width="150" alt="Push">
</a>

## Requirements
### Environment
* GitHub account
* Java 11 (JDK) (optionally)
* Maven 3.6+ (optionally)
* Any IDE you comfortable with (eg. IntelliJ, VSCode)

### Skills
* Java 8+ (coding standards)
* Clean Code
* Maven
* Git, GitLab, GitHub

### Instructions
Fork this project
<details>
<summary>Example</summary>

   ![img.png](doc/img/01_fork_project.png)
</details>

#### Working in Web IDE (preferable)

1. Open Project in [GitPod](https://gitpod.io/from-referrer/):
2. Sing-in with GitHub account
3. Create and commit your solution into your forked repository
4. Create documentation in the README.md under the `Documentation` section
5. IMPORTANT: Enable Repository permissions (e.g. git push) for GitPod when coding from Web IDE here:
   https://gitpod.io/integrations
   <details>
   <summary>Details here</summary>

   Edit permission for GitHub:

   ![img.png](doc/img/02_integration_providers.png)

   ![img.png](doc/img/02_enable_repo_permissions.png)
   </details>

## Documentation
Rest-Assured java library tih TestNG is used  is used in the test framework.

### The project uses the following:

Java 11 as the programming language.
REST Assured as the REST API test automation framework.
Lombok to generate getters, setters and builders.
TestNG as the test runner.
Hamcrest as the matcher library.

### Execution Steps

In order to run the tests.

Clone the repository.

Import the project in a code editor of your choice.

Add the below data in the respective config file. 
token = 
projectId = 
groupId = 

Run the test with below command
	mvn clean test // gets the parameter from qa config property file
	mvn clean install -Denv="<environment name>" // to run the test from specific config property file. Valid values are qa, dev , stage , prod

TestNG suite XML file will be generated post run

<!-- This repository contains TEST-SUITE for Sample Project. This test framework is designed using Rest Assured java library with TestNG framework. It is written in java language. -->

Based on
This suite is currently based on:

java 11
io.rest-assured: 5.3.0 https://rest-assured.io/
com.relevantcodes 2.41.2 https://www.extentreports.com/docs/versions/2/java/doc/
org.testng 6.13.1 https://testng.org/doc/
maven-surefire-plugin
How to setup run test suite
Clone git repository
Navigate to the cloned folder
Open the folder in any code editor
Open the terminal and hit following commands in terminal
To set environment variables

export ACCESS_TOKEN=<private git access token>
export PROJECT_ID=<project id>
mvn clean test 
To run all the tests [This will by default run all specs in parallel mode]

At the root level ‘TestReport.html’ file will be generated. Open the html file in any browser.

You can set the BASE_URI, ACCESS_TOKEN and PROJECT_ID in src/test/resources/configfiles/config.properties file. by default BASE_URI = https://gitlab.com/api/v4

Note : a) If environment variable is not provided it will fetch BASE_URI, ACCESS_TOKEN and PROJECT_ID from config.properties file. b) Only authenticated user has access to api's. It returns only issues created by the current user.

Tests can be executed directly using testNG suite XML file - src/test/resources/testsuites/gitIssuesAPITest.xml
