package com.example.jackwell

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BMIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bmi_title)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton = findViewById<Button>(R.id.back)
        backButton.setOnClickListener {
            val intent = Intent(this, WorkoutPlannerActivity::class.java)
            startActivity(intent)
        }



        // Get references to UI elements
        val weightInput = findViewById<EditText>(R.id.weight_input)
        val heightInput = findViewById<EditText>(R.id.height_input)
        val calculateButton = findViewById<Button>(R.id.calculate_bmi)
        val resultText = findViewById<TextView>(R.id.bmi_result)

        calculateButton.setOnClickListener {
            val weightStr = weightInput.text.toString()
            val heightStr = heightInput.text.toString()

            if (weightStr.isEmpty() || heightStr.isEmpty()) {
                Toast.makeText(this, "Please enter weight and height", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weight = weightStr.toFloat()
            val height = heightStr.toFloat()

            if (height <= 0 || weight <= 0) {
                Toast.makeText(this, "Enter valid weight and height", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Calculate BMI
            val bmi = weight / (height * height)
            val bmiCategory = getBMICategory(bmi)

            resultText.text = "Your BMI: %.2f\nCategory: %s".format(bmi, bmiCategory)
        }
    }

    // Function to categorize BMI result
    private fun getBMICategory(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi in 18.5..24.9 -> "Normal weight"
            bmi in 25.0..29.9 -> "Overweight"
            else -> "Obese"
        }
    }
}