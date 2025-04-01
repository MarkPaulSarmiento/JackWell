package com.example.jackwell

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class WorkoutVideosActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private lateinit var exerciseText: TextView
    private lateinit var backButton: androidx.appcompat.widget.AppCompatButton

    private val videoQueue = mutableListOf<String>() // Stores valid video filenames
    private var currentVideoIndex = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_videos)

        videoView = findViewById(R.id.videoView)
        exerciseText = findViewById(R.id.exerciseText)

        backButton = findViewById(R.id.back)
        backButton.setOnClickListener {
            startActivity(Intent(this, WorkoutPlan1::class.java))
            finish()
        }

        // Get exercises for 4 days
        val day1 = intent.getStringExtra("DAY1")?.trim() ?: ""
        val day2 = intent.getStringExtra("DAY2")?.trim() ?: ""
        val day3 = intent.getStringExtra("DAY3")?.trim() ?: ""
        val day4 = intent.getStringExtra("DAY4")?.trim() ?: ""

        // Debugging: Print received exercises
        Log.d("WorkoutVideos", "Day1: $day1, Day2: $day2, Day3: $day3, Day4: $day4")

        // Add non-empty exercises to queue
        listOf(day1, day2, day3, day4).forEach { exercise ->
            val videoFile = getVideoFile(exercise)
            if (videoFile.isNotEmpty()) videoQueue.add(videoFile)
        }

        // Check if there's any valid exercise
        if (videoQueue.isEmpty()) {
            exerciseText.text = "No valid exercises selected!"
            return
        }

        // Play first video
        playNextVideo()

        // Play next video after one finishes
        videoView.setOnCompletionListener {
            currentVideoIndex++
            if (currentVideoIndex < videoQueue.size) {
                playNextVideo()
            }
        }
    }

    // Function to get video file name based on exercise name
    private fun getVideoFile(exercise: String): String {
        return when (exercise.lowercase()) {
            "push ups", "pushup", "push-up" -> "pushup"
            "squats", "squat" -> "squats"
            "burpees", "burpee" -> "burpee"
            "plank", "planking" -> "plank"
            "jumping jacks", "jumpingjacks" -> "jumpingjacks"
            "bench press", "benchpress" -> "benchpress"
            "deadlifts", "deadlift" -> "deadlift"
            "bicep curls", "bicep curl", "bicepcurls" -> "bicepcurls"
            "running", "run" -> "running"
            "leg press", "legpress" -> "legpress"
            "stretching", "stretch" -> "stretching"
            else -> ""
        }
    }

    // Function to play the next video
    private fun playNextVideo() {
        if (currentVideoIndex < videoQueue.size) {
            val videoFileName = videoQueue[currentVideoIndex]
            val videoUri = Uri.parse("android.resource://$packageName/raw/$videoFileName")

            // Set video name and URI
            exerciseText.text = "Now Playing: ${videoQueue[currentVideoIndex]}"
            videoView.setVideoURI(videoUri)

            // Add media controls
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)

            // Start video playback
            videoView.start()
        }
    }
}
