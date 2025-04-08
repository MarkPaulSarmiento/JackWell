package com.example.jackwell

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle

import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProgressTrackingActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference  // Firebase Database Reference
    private lateinit var day1Progress: EditText
    private lateinit var day2Progress: EditText
    private lateinit var day3Progress: EditText
    private lateinit var saveProgressButton: AppCompatButton
    private lateinit var viewChartButton: AppCompatButton
    private lateinit var homeButton: ImageButton
    private lateinit var barChartButton: ImageButton
    private lateinit var profileButton: ImageButton


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_tracking)

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance().reference.child("progressData")

        // Initialize UI Components
        day1Progress = findViewById(R.id.day1_progress)
        day2Progress = findViewById(R.id.day2_progress)
        day3Progress = findViewById(R.id.day3_progress)
        saveProgressButton = findViewById(R.id.save_progress)
        viewChartButton = findViewById(R.id.view_chart)
        homeButton = findViewById(R.id.home_icon)
        barChartButton = findViewById(R.id.bar_chart_icon)
        profileButton = findViewById(R.id.profile_icon)




        // Save Progress Button Click
        saveProgressButton.setOnClickListener {
            saveProgress()
        }

        // View Progress Chart Button Click
        viewChartButton.setOnClickListener {
            startActivity(Intent(this, ProgressChartActivity::class.java))
        }

        // Navigation Buttons
        homeButton.setOnClickListener {
            startActivity(Intent(this, WorkoutPlannerActivity::class.java))
        }

        barChartButton.setOnClickListener {
            startActivity(Intent(this, ProgressChartActivity::class.java))
        }

        profileButton.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun saveProgress() {
        val day1 = day1Progress.text.toString()
        val day2 = day2Progress.text.toString()
        val day3 = day3Progress.text.toString()

        if (day1.isEmpty() || day2.isEmpty() || day3.isEmpty()) {
            Toast.makeText(this, "Please enter progress for all days", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a HashMap to store progress data
        val progressData = hashMapOf(
            "day1" to day1.toInt(),
            "day2" to day2.toInt(),
            "day3" to day3.toInt()
        )

        // Push data to Firebase
        database.setValue(progressData)
            .addOnSuccessListener {
                Toast.makeText(this, "Progress saved successfully!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to save progress", Toast.LENGTH_SHORT).show()
            }
    }
}
