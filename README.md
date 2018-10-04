# timer-bddtest

<h2>1. How to build and run the code</h2>

<p>Clone the git respository at: https://github.com/crisbbbm/timer-bddtest.</p>

<p>The repository contains an Eclipse Java project called timertest build with Maven. The project contains a BDD testing framework that tests a timer functionality on the website http://www.e.ggtimer.com/. The BDD test scenarios were sepcified using the Gerkin language and were built using the browser automation library Selenium.</p>

<p>Please make sure you have access to internet when running the tests. It is expected that some tests will fail - There are bugs in the timer application! (see section 5)</p>
<br />
<p>In order to build and run the code, the following are required:

<ol>
	
<li>Add the <em>chromedriver.exe</em> file to a folder called 'Drivers' on the root directory of the C partition (eg.  <em>C:\Drivers\chromedriver.exe</em>)<br /><br /></li>
	
<li>The project can be run in two ways:
<ol>
	<br />
<li>From Eclipse IDE
<ul>
<li>Make sure you have Maven plugin in Eclipse</li>
<li>Import the project into Eclipse IDE</li>
<li>Run the application as a JUnit Project</li>
<li>The run from IDE gives a better overview of the test results</li>
</ul>		
</li>
	<br />
<li>Using Maven from cmd
<ul>
<li>Make sure you have Maven installed on your computer </li>
<li>Configure the Windows environment variable to contain the Maven installation path</li>
<li>Run <code>mvn test</code> in the project directory (where the pom.xml file is located) in order to run all tests</li>
</ul>		
</li>
</ol>
</li>
</ol>
</p>

<h2>2. Platform limitations:</h2>

<p>The project works only on Windows platform.</p>
	
<h2>3. Highlights:</h2>
<ul>
<li>The frameworks makes it easy to add a different wed driver to test with (FireFox, IE etc.)</li>
<li>Test coverage is increased by using boundary value technique and equivalence partitioning</li>
<li>Some short scenarios test the expected timeout, but others scenartios that run longer, focus on verifying the correct countdown. The number of seconds to wait for a scenario to run can be specified as test data in the BDD steps.</li>
</ul>	

	
<h2>4. Improvements:</h2>
	
<ul>
<li>Use a headless browser for running the tests in the background without opening the physical browser. It is possible to do so using, for example, PhantomJS with GhostDriver. The current solution has the possibility to use PhantomJS web driver, but the implmentation is not ready.
</li>
<li>Implement test scenarios for more complex user input (eg. "23minutes3seconds", "4minutes5hours" etc.).</li>
<li>implement test scenarios for the four special timers described on the website.</li>
<li>Make the project compatibile with other platforms(eg. Linux ).</li>
<li>Make the tests more stable by removing the latency created by the web driver (maybe using a headless browser).</li>
<li>Test the audio part.</li>	
</ul>		
	
<h2>5. Bugs:</h2>
<ol>
	<li><strong>Bug title</strong>: Alert dialog opens twice when user input is invalid.<br />
<strong>Bug description</strong>: Whenever the user input is invalid (negative number or random text that do not mach the time units), the alert dialog opens again after closing it first.<br />
<strong>Expected behaviour</strong>: The alert dialog should open only once and dissapear after the alert was accepted. <br />
</li>
<br />
<li><strong>Bug title</strong>: Invalid input is validated as correct.<br />
<strong>Bug description</strong>: The user input of type "45minutess", "10secondss", "7hourss" (please notice the double 's') is considered valid. Moreover, it adds extra hours to the timer.<br />
<strong>Expected behaviour</strong>: Input of type "minutess", "secondss", "hourss" should be considered invalid. <br />
</li>
<br />
<li><strong>Bug title</strong>: POSSIBLE Bug : Timer hangs for 2 seconds before displaying timeout.<br />
<strong>Bug description</strong>: Whenever the timer reaches "1 second", it waits another 2 seconds before notifying the user about the timeout. <br />
<strong>Expected behaviour</strong>: When the timer reaches "1 second", it should wait another seconds and immediately notify the user about the timeout. <br />
<strong>Note</strong>: The tests were changed to accept this 'bug', as it is not clear if it is a bug or intended functionality - can be discussed . <br />
</ol>

	   
	
	 
