package com.example.jackwell

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class WorkoutPlannerActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_planner)


        val plan1Button: ImageButton = findViewById(R.id.plan1_button)
        val plan2Button: ImageButton = findViewById(R.id.plan2_button)
        val plan3Button: ImageButton = findViewById(R.id.plan3_button)
        val plan4Button: ImageButton = findViewById(R.id.plan4_button)
        val plan5Button: ImageButton = findViewById(R.id.plan5_button)
        val plan6Button: ImageButton = findViewById(R.id.plan6_button)
        val homeIcon: ImageButton = findViewById(R.id.home_icon)
        val barChartIcon: ImageButton = findViewById(R.id.bar_chart_icon)
        val profileIcon: ImageButton = findViewById(R.id.profile_icon)


        plan1Button.setOnClickListener {
            // Handle plan 1 button click
        }

        plan2Button.setOnClickListener {
            // Handle plan 2 button click
        }

        plan3Button.setOnClickListener {
            // Handle plan 3 button click
        }
        plan4Button.setOnClickListener {

        }
        plan5Button.setOnClickListener {

        }
        plan6Button.setOnClickListener {

        }

        homeIcon.setOnClickListener {

        }

        barChartIcon.setOnClickListener {
            startActivity(Intent(this, ProgressTrackingActivity::class.java))
        }


        profileIcon.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

    }
}
