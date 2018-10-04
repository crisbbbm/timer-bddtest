Feature: Timer feature
  As a user, I want to use the timer, being notified every seconds how much time I have left

    Scenario Outline: Check if the timer decreases and stops 
    Given user is on timer page
     When user enters <userInput> in timer
      And user clicks the go button
      And user leaves the timer running for maximum <maxLoops> seconds
     Then the timer <expectedResult> and it decreses by <countdownSeconds> seconds
  
    Examples: 
      | scenarioName         | userInput  | expectedResult | countdownSeconds | maxLoops | 
      | NegativeInput_Fail_1 | -10        | fails          | 0                | 6        | 
      | NegativeInput_Fail_2 | -1         | fails          | 0                | 6        | 
      | NegativeInput_Fail_3 | 0          | fails          | 0                | 6        | 
      | InvalidInput_Fail_1  | whatever   | fails          | 0                | 6        | 
      | InvalidInput_Fail_2  | 3secondss  | fails          | 0                | 6        | 
      | InvalidInput_Fail_3  | 10minutess | fails          | 0                | 6        | 
      | InvalidInput_Fail_4  | 20hourss   | fails          | 0                | 6        | 
      | Countdown_Success_1  | 1          | works          | 1                | 6        | 
      | Countdown_Success_2  | 5          | works          | 1                | 6        | 
      | Countdown_Success_3  | 60         | works          | 1                | 6        | 
      | Countdown_Success_4  | 59         | works          | 1                | 6        | 
      | Countdown_Success_5  | 61seconds  | works          | 1                | 6        | 
      | Countdown_Success_6  | 100seconds | works          | 1                | 6        | 
      | Countdown_Success_7  | 1minute    | works          | 1                | 6        | 
      | Countdown_Success_8  | 30minutes  | works          | 1                | 6        | 
      | Countdown_Success_9  | 59minutes  | works          | 1                | 6        | 
      | Countdown_Success_10 | 65minutes  | works          | 1                | 6        | 
      | Countdown_Success_11 | 100minutes | works          | 1                | 6        | 
      | Countdown_Success_12 | 1hour      | works          | 1                | 6        | 
      | Countdown_Success_13 | 20hours    | works          | 1                | 6        | 
			
		
