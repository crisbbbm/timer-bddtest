# timer-bddtest

1. How to build and run the code

Please download the code from the following git respository:
//link

The repository contains an Eclipse Java project called "timertest" build with Maven.
The project contains a BDD testing framework that tests a timer functionality on the website "http://www.e.ggtimer.com/".
Please make sure you have access to internet when running the tests.
When running the tests, some will fail - There are bugs in the timer application! (see section 6)


In order to build and run the code, the following are required:
	1. Download ChromeWebDriver fro Selenium from the following link:
	//link
	2. Add the Chrome WebDriver executable to a folder called "Drivers" on you root directory (ex."C:\Drivers\chromedriver.exe")
	3. The program can be run in two ways:

		A. From Eclipse IDE
			- Import the project into Eclipse IDE and run it as a JUnit test
			- The run from IDE gives a better overview of the test results

		B. Using Maven
			- Make sure you have Maven installed on your computer 
			- Configure the Windows environment variable to contain the Maven installation 
			- From the project, run "mvn test" command in order to run all defined tests in the project
			
2. Platform limitations:

	
4. Highlights:
	
	- It is easy to test with other web driver. The framework makes it possible to easily add a specifc web driver. 
	- 
	
5. Improvements:
	
	- use a headless browser for running the tests in the background without opening the physical browser. It is possible to do so using, for example, PhantomJS with GhostDriver.
	 The current solution has a choice for PhantomJS web driver, but the implmentation is not ready
	
	- implement test scenarios for more complex user input (eg. "23minutes3seconds", "4minutes5hours" etc.)
	
	- implement test scenarios for the four special timers described on the website
	
	- make the tests more stable by removing the latency created by the web driver (maybe using a headless browser)
	
	- test the audio part
	
	
6. Bugs:

	1. Bug description: Whenever the user input is invalid (negative number or random text that do not mach the time units), the alert dialog opens again after closing it first.
	   Expected behaviour: The alert dialog should open only once and dissapear after the alert was accepted.
	   
	2. Bug description: The user input of type "45minutess", "10secondss", "7hourss" (please notice the double 's') is considered valid. Moreover, it adds extra hours to the timer. 
	   Expected behaviour: Input of type "minutess", "secondss", "hourss" should be considered invalid.
	   
	3. POSSIBLE Bug
	 Bug Description: Whenever the timer reaches "1 second", it waits another 2 seconds before notifying the user about the timeout. 
	 Expected behaviour: When the timer reaches "1 second", it should wait another seconds and immediately notify the user about the timeout.
	 Note: The tests were changed to accept this 'bug', as it it not clear if it is a bug or a functionality - can be discussed 
	 
