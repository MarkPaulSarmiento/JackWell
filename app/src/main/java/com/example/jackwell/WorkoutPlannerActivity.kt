package com.example.jackwell

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class WorkoutPlannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_planner)

        val createPlanButton: Button = findViewById(R.id.create_plan_button)
        val plan1Button: Button = findViewById(R.id.plan1_button)
        val plan2Button: Button = findViewById(R.id.plan2_button)
        val plan3Button: Button = findViewById(R.id.plan3_button)
        val homeIcon: ImageButton = findViewById(R.id.home_icon)
        val barChartIcon: ImageButton = findViewById(R.id.bar_chart_icon)
        val bellIcon: ImageButton = findViewById(R.id.bell_icon)
        val profileIcon: ImageButton = findViewById(R.id.profile_icon)

        createPlanButton.setOnClickListener {
            // Handle create plan button click
        }

        plan1Button.setOnClickListener {
            // Handle plan 1 button click
        }

        plan2Button.setOnClickListener {
            // Handle plan 2 button click
        }

        plan3Button.setOnClickListener {
            // Handle plan 3 button click
        }

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
