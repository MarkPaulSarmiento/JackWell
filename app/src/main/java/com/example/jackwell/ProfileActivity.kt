package com.example.jackwell

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val homeIcon: ImageButton = findViewById(R.id.home_icon)
        val barChartIcon: ImageButton = findViewById(R.id.bar_chart_icon)
        val editImageProf : ImageButton = findViewById(R.id.edit)
        val profilePicture : ImageView = findViewById(R.id.profile_picture)
        val launchGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {result ->
            profilePicture.setImageBitmap(MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, result.data!!.data))
        }

        editImageProf.setOnClickListener {
            val openGalleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            launchGallery.launch(openGalleryIntent)
        }

        homeIcon.setOnClickListener {
            startActivity(Intent(this, WorkoutPlannerActivity::class.java))
        }

        barChartIcon.setOnClickListener {
            startActivity(Intent(this, ProgressTrackingActivity::class.java))
        }
    }
}
