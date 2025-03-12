package com.example.jackwell

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val homeIcon: ImageButton = findViewById(R.id.home_icon)
        val barChartIcon: ImageButton = findViewById(R.id.bar_chart_icon)
        val profileIcon: ImageButton = findViewById(R.id.profile_icon)

        homeIcon.setOnClickListener {
            // Handle home icon click
        }

        barChartIcon.setOnClickListener {
            // Handle bar chart icon click
        }

        profileIcon.setOnClickListener {
            // Handle profile icon click
        }
    }
}
