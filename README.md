# Homework 1: Simple Math Game
Michael Santoro
CS 414-02

## Description
Simple Math Game is a fast-paced game that tests your math skiulls. The game presents a random problem along with a result, either correct or incorrect. The player must quickly decide whether the given result is correct or incorrect.

### Generating a random problem

#### Generating numbers one and two
Each question displayed to the user is randomly generated. Both numbers are two digits, and number one will always be greater than number two. As a result, we get the following rules for both numbers. 
- ***11 ≤ num1 < 100***
- ***10 ≤ num2 < 99***
- ***num1 > num2***
- ***correctAnswer > 0***

The random generation of num1 and num2 is handled by a function called ***generateRandomNumber(lower, upper)*** that uses the nextInt() method and returns and integer between the values of *lower* and *upper*.

#### Generating a random operator
After num1 and num2 are generated, an operator must be generated. The operators are stored in an array, ***operators***, where:
- operators[0] = "+"
- operators[1] = "-"
- operators[2] = "*"

An array was used here to store the operators to allow the expansion of other operators such as division. To add division, Instead of adding the logic for division to many places, I would just have to add "/" to the end of the array, and add an additional conditional in the ***calculateCorrectAnswer()*** function.

The random generation of the operation is handled by a function called ***generateRandomOperator()*** which uses the nextInt() method using ***operators.size*** as its argument, ensuring the randomly selected operator will always be an item of the ***operators*** array.

#### Determining if the correct answer should be displayed
Sometimes we want the wrong answer to be displayed to the screen to challenge the player. I determined if a wrong answer should be displayed by utilizing the ***generateRandomNumber()*** function discussed earlier, with the bounds being 0 and 2, making the only possible values returned 0 or 1. This result is stored in the ***correct*** value. 

If ***correct*** is true or 1, the game will display the correct answer. If ***correct*** is false or 0, an offset will be generated, and the wrong answer will be displayed to the player. We get the following rules for this offset:
- ***-10 < offset < 10***
- ***offset != 0***

### Checking the correct answer when the player selects and answer
When the player presses either the correct or incorrect button, We have to determine if the player was correct or not, and update the score accordingly. To calculate the correct answer, I used a function called ***calculateCorrectAnswer()***, which returns an int of the correct answer.

The player will increase their score if:
- The displayed answer was correct and the player pressed the correct button
- The displayed answer was incorrect and the player pressed the incorrect button

The player will decrease their score if:
- The displayed answer was correct and the player pressed the incorrect button
- The displayed answer was incorrect and the player pressed the correct button

No matter which button the player presses, the correct answer will be displayed as feedback to the player.

#### Answer Streaks
The game keeps track of back-to-back correct answers given by using a variable called ***streak***. Every 3 answers in a row the user gets right, the game will use Toast to give a congratulatory message.

### Resetting the game
Upon pressing the reset button, the score, streak, and questions will be reset to 0, and a new problem will be generated for the user.

## References
- https://kotlinlang.org/docs/arrays.html#when-to-use-arrays
- In Class Example 1 (Simple Number Game)
