package com.example.jackwell

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ProgressTrackingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_tracking)

        val day1Progress: EditText = findViewById(R.id.day1_progress)
        val day2Progress: EditText = findViewById(R.id.day2_progress)
        val day3Progress: EditText = findViewById(R.id.day3_progress)
        val homeIcon: ImageButton = findViewById(R.id.home_icon)
        val barChartIcon: ImageButton = findViewById(R.id.bar_chart_icon)
        val bellIcon: ImageButton = findViewById(R.id.bell_icon)
        val profileIcon: ImageButton = findViewById(R.id.profile_icon)

        homeIcon.setOnClickListener {
            // Handle home icon click
        }

        barChartIcon.setOnClickListener {
            // Handle bar chart icon click
        }

        bellIcon.setOnClickListener {
            // Handle bell icon click
        }

        profileIcon.setOnClickListener {
            // Handle profile icon click
        }
    }
}
