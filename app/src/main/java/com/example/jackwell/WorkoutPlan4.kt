package com.example.jackwell

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class WorkoutPlan4 : AppCompatActivity() {
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner
    private lateinit var spinner4: Spinner
    private lateinit var proceedBtn: Button
    private lateinit var backBtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_plan4)

        // Back Button functionality
        backBtn = findViewById(R.id.back)
        backBtn.setOnClickListener {
            startActivity(Intent(this, WorkoutPlannerActivity::class.java))
        }

        // Spinner setup for days 1 to 4
        spinner1 = findViewById(R.id.spinner1)
        setupSpinner(spinner1, R.array.day1WorkoutsPlan1)

        spinner2 = findViewById(R.id.spinner2)
        setupSpinner(spinner2, R.array.day2WorkoutsPlan1)

        spinner3 = findViewById(R.id.spinner3)
        setupSpinner(spinner3, R.array.day3WorkoutsPlan1)

        spinner4 = findViewById(R.id.spinner4)
        setupSpinner(spinner4, R.array.day4WorkoutsPlan1)

        // Proceed Button functionality
        proceedBtn = findViewById(R.id.proceedBtn)
        proceedBtn.setOnClickListener {
            val intent = Intent(this, WorkoutVideosActivity::class.java)
            intent.putExtra("DAY1", spinner1.selectedItem.toString())
            intent.putExtra("DAY2", spinner2.selectedItem.toString())
            intent.putExtra("DAY3", spinner3.selectedItem.toString())
            intent.putExtra("DAY4", spinner4.selectedItem.toString())
            startActivity(intent)
        }
    }

    private fun setupSpinner(spinner: Spinner, arrayResId: Int) {
        ArrayAdapter.createFromResource(
            this, arrayResId, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}
