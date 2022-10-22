######SlackUIAutomation

Automate slack UI on web browser to evaluate the message sent to slack from the slack client.
An account on slack with slack with a workspace Automationteam and Channel #browser-automation

###Pre-Requistes
Windows machine server/mac-os
Chrome Browser Installed
Java JDK 17
Apache Maven 3.6.1
Slack Account created with Automationteam as workspace name with #browser-automation channel.

Maven Selenium project which is running in headless mode without any issues in your local machine

####Commands to Checkout the code and steps to execute:
Pull main branch
git clone https://github.com/monaa91/SlackUIAutomation.git

To test against your local environment, simply execute the:

mvn -B package --file pom.xml

####Command to run tests on CI(GITHUB) Right now the repo is hosted on GIT, up on each commit to main and on each pull request the build will run. Developer doesn’t have to do anything explicitly to kick the build.

#Extent report will be generated in the below location:

/reports/Extent**.html

#Sure fire report which is a default report for TestNG will be generated in the below location:

/target/surefire-reports/index.html


###FLOW of the Program:
TestBase.java is the class where the driver is getting initiated and closed on test start and completion respectively. Using @BeforeSuite and @AfterSuite annotations.

SendMessage.java is the Test class which extends the base class. It has 3 test cases which needs to be executed in sequential manner.

1.	Sending a message to slack.
2.	Validating the message in the saved Items.
3.	Searching the message in the search bar using the search term “has:star” As the search doesn’t include the message right away to deal with this in MessagingPage.java (PageObject) a method is created searchMessageinslack which retries the search until the first message from list is same as the message sent.   Instead of using thread.sleep.
4.	An exception is captured whenever a test case fails inside the CustomListeners.java
5.	Construction of Pojos in the repo is done using Java builder pattern. This gives the user ability to improve the readability and sending dynamic values in test cases reducing the burden of creating whole object again.








