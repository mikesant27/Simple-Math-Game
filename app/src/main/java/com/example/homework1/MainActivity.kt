package com.example.homework1

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class MainActivity : AppCompatActivity() {

    private var score = 0
    private var questions = 0
    private var streak = 0
    private val operators = arrayOf("+", "-", "*")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        pickNewEquation()
    }

    fun checkCorrectness(view: View){
        var message = ""
        val num1 = findViewById<TextView>(R.id.textView_Number1).text.toString().toInt()
        val num2 = findViewById<TextView>(R.id.textView_Number2).text.toString().toInt()
        val operator = findViewById<TextView>(R.id.textView_Operator).text.toString()
        val result = findViewById<TextView>(R.id.textView_Result).text.toString().toInt()

        val correctAnswer = calculateCorrectAnswer()

        // If answer was correct and correct button was clicked or
        // answer was incorrect and incorrect button was clicked
        if((view.id == R.id.button_Correct && result == correctAnswer) ||
            view.id == R.id.button_Incorrect && result != correctAnswer){
            score++
            streak++
            message = "That's right! $num1 $operator $num2 should be $correctAnswer"
            findViewById<TextView>(R.id.textView_Feedback).setTextColor(Color.GREEN)
        }
        // If answer was correct and incorrect button was clicked or
        // answer was incorrect and correct button was clicked
        else{
            streak = 0
            message = "Wrong. $num1 $operator $num2 is $correctAnswer"
            findViewById<TextView>(R.id.textView_Feedback).setTextColor(Color.RED)
        }

        // If the player has answered 3 or more in a row correctly & is a multiple of 3
        if(streak > 2 && streak % 3 == 0){
            Toast.makeText(this, "Genius!", Toast.LENGTH_LONG).show()
        }

        questions++
        findViewById<TextView>(R.id.textView_Feedback).text = "$message"
        findViewById<TextView>(R.id.textView_Score).text = "Score: $score/$questions"
        pickNewEquation()
    }

    private fun  pickNewEquation(){
        // Generate the 2 operands and operator
        var num1 = generateRandomNumber(11, 100)
        var num2 = generateRandomNumber(10, 99)
        val operator = generateRandomOperator()

        // Ensures num1 > num2
        if(num1 <= num2){
            // Swap num1 and num2 if num1 > num2
            val temp = num1
            num1 = num2
            num2 = temp
        }

        // Display the generated values in the TextViews
        findViewById<TextView>(R.id.textView_Number1).text = "$num1"
        findViewById<TextView>(R.id.textView_Number2).text = "$num2"
        findViewById<TextView>(R.id.textView_Operator).text = "$operator"

        // value to determine if we should display the correct answer
        // 1 = true, 0 == false
        val correct = (generateRandomNumber(0,2) == 1)

        val correctAnswer = calculateCorrectAnswer()

        // if the correct value is true, display the correct answer
        if(correct) {
            findViewById<TextView>(R.id.textView_Result).text = "$correctAnswer"
        }
        else { // if !correct
            // makes sure the -10 < 10 offset is not 0, resulting in the correct answer being
            // displayed when it shouldn't be
            var offset = generateRandomNumber(-10,10)
            // ensures the offset is never 0, and the fake answer is greater than 0
            while (offset == 0 || correctAnswer + offset <= 0){
                offset = generateRandomNumber(-10,10)
            }
            findViewById<TextView>(R.id.textView_Result).text = "${correctAnswer + offset}"
        }
    }

    // returns an Int where lower <= num < upper
    private fun generateRandomNumber(lower: Int, upper: Int): Int {
        return Random().nextInt(upper - lower) + lower
    }

    // returns a String where the returned value is a random operator from the array
    // this ensures randomness in what operator is selected
    private fun generateRandomOperator(): String{
        return operators[Random().nextInt(operators.size)]
    }

    // resets score, questions, and streak variables and updates it in TextView
    fun resetGame(view: View){
        score = 0
        questions = 0
        streak = 0
        findViewById<TextView>(R.id.textView_Score).text = "Score: $score/$questions"
        findViewById<TextView>(R.id.textView_Feedback).text = ""
        pickNewEquation()
    }

    // calculates the correct answer and returns it
    private fun calculateCorrectAnswer(): Int{
        val num1 = findViewById<TextView>(R.id.textView_Number1).text.toString().toInt()
        val num2 = findViewById<TextView>(R.id.textView_Number2).text.toString().toInt()
        val operator = findViewById<TextView>(R.id.textView_Operator).text.toString()

        if (operator == "+")
            return num1 + num2
        else if (operator == "-")
            return num1 - num2
        else // if operator == *
            return num1 * num2
    }

}