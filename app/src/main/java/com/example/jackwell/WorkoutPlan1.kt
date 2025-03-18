package com.example.jackwell

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class WorkoutPlan1 : AppCompatActivity() {
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner
    private lateinit var spinner4: Spinner
    private lateinit var backBtn: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_workout_plan1)

        backBtn = findViewById(R.id.back)
        backBtn.setOnClickListener {
        }

        spinner1 = findViewById<Spinner>(R.id.spinner1)
        ArrayAdapter.createFromResource(this, R.array.day1WorkoutsPlan1, android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinner1.adapter = adapter
        }
        spinner1.onItemSelectedListener

        spinner2 = findViewById<Spinner>(R.id.spinner2)
        ArrayAdapter.createFromResource(this, R.array.day2WorkoutsPlan1, android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinner2.adapter = adapter
        }
        spinner3 = findViewById<Spinner>(R.id.spinner3)
        ArrayAdapter.createFromResource(this, R.array.day3WorkoutsPlan1, android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinner3.adapter = adapter
        }
        spinner4 = findViewById<Spinner>(R.id.spinner4)
        ArrayAdapter.createFromResource(this, R.array.day4WorkoutsPlan1, android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinner4.adapter = adapter
        }
    }

}