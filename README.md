# timer-bddtest

<h2>1. How to build and run the code</h2>

	- Clone the git respository at: https://github.com/crisbbbm/timer-bddtest

	- The repository contains an Eclipse Java project called timertest build with Maven.
	
	- The project contains a BDD testing framework that tests a timer functionality on the website http://www.e.ggtimer.com/.
	
	- The BDD test scenarios were sepcified using the Gerkin language and were built using the browser automation library Selenium
	
	- Please make sure you have access to internet when running the tests.
	
	- It is expected that some tests will fail - There are bugs in the timer application! (see section 6)

	- In order to build and run the code, the following are required:

	1. Add the chromedriver.exe file to a folder called 'Drivers' on the root directory of the C partition 		
	(eg. C:\Drivers\chromedriver.exe) 

	2. The project can be run in two ways:

	A. From Eclipse IDE

		- Import the project into Eclipse IDE 
		- Make sure you have Maven plugin in Eclipse
		- Run the application as a JUnit Project
		- The run from IDE gives a better overview of the test results

	B. Using Maven from cmd

		- Run 'mvn test' in the project directory (where the pom.xml file is located) in order to run all tests
		- Make sure you have Maven installed on your computer 
		- Configure the Windows environment variable to contain the Maven installation path


<h2>2. Platform limitations:</h2>

	- The project works only on Windows platform
	
<h2>4. Highlights:</h2>
	
	- The frameworks makes it easy to add a different wed driver to test with (FireFox, IE etc.)
	
	- Test coverage is increased by using boundary value technique and equivalence partitioning
	
	- Some short scenarios test the expected timeout, but others scenartios that run longer, focus on verifying 
	the correct countdown. The number of seconds to wait for a scenario to run can be specified as test data in the BDD steps.

	
<h2>5. Improvements:</h2>
	
	
	- use a headless browser for running the tests in the background without opening the physical browser. It is possible to do 
	so using, for example, PhantomJS with GhostDriver. The current solution has the possibility to use PhantomJS web driver, but the 
	implmentation is not ready
	
	- implement test scenarios for more complex user input (eg. "23minutes3seconds", "4minutes5hours" etc.)
	
	- implement test scenarios for the four special timers described on the website
	
	- make the project compatibile with more platforms(eg. Linux )
	
	- make the tests more stable by removing the latency created by the web driver (maybe using a headless browser)
	
	- test the audio part
	
<h2>6. Bugs:</h2>

	1.Bug description: Whenever the user input is invalid (negative number or random text that do not mach the time units), the 	
	alert dialog opens again after closing it first.
	
	 Expected behaviour: The alert dialog should open only once and dissapear after the alert was accepted.
	   
	2. Bug description: The user input of type "45minutess", "10secondss", "7hourss" (please notice the double 's') is considered 
	valid. Moreover, it adds extra hours to the timer. 
	
	Expected behaviour: Input of type "minutess", "secondss", "hourss" should be considered invalid.
	   
	3. POSSIBLE Bug
	 Bug Description: Whenever the timer reaches "1 second", it waits another 2 seconds before notifying the user about the timeout. 
	 
	 Expected behaviour: When the timer reaches "1 second", it should wait another seconds and immediately notify the user about the 
	 timeout.
	 
	 Note: The tests were changed to accept this 'bug', as it is not clear if it is a bug or intended functionality - can be 
	 discussed 
	 
