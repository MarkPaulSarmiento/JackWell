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
        val barChartIcon: ImageButton = findViewById(R.id.bar_chart_icon)
        val profileIcon: ImageButton = findViewById(R.id.profile_icon)


        plan1Button.setOnClickListener {
            startActivity(Intent(this, WorkoutPlan1::class.java))
        }

        plan2Button.setOnClickListener {
            startActivity(Intent(this, WorkoutPlan1::class.java))
        }

        plan3Button.setOnClickListener {
            startActivity(Intent(this, WorkoutPlan1::class.java))
        }
        plan4Button.setOnClickListener {
            startActivity(Intent(this, WorkoutPlan1::class.java))
        }
        barChartIcon.setOnClickListener {
            startActivity(Intent(this, ProgressTrackingActivity::class.java))
        }
        profileIcon.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

    }
}
