package com.example.jackwell

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var profilePicture: ImageView
    private lateinit var sharedPref: SharedPreferences
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private lateinit var nameTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var weightTextView: TextView
    private lateinit var heightTextView: TextView
    private lateinit var genderTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val homeIcon: ImageButton = findViewById(R.id.home_icon)
        val barChartIcon: ImageButton = findViewById(R.id.bar_chart_icon)
        val editImageProf: ImageButton = findViewById(R.id.edit)
        val editProfileButton: AppCompatButton = findViewById(R.id.editprofilebtn)
        profilePicture = findViewById(R.id.profile_picture)

        nameTextView = findViewById(R.id.name_value)
        ageTextView = findViewById(R.id.age_value)
        weightTextView = findViewById(R.id.weight_value)
        heightTextView = findViewById(R.id.height_value)
        genderTextView = findViewById(R.id.gender_value)

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference

        editProfileButton.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        // Initialize SharedPreferences
        sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)

        // Load saved profile picture
        loadProfilePicture()

        // Fetch user data from Firebase
        fetchUserData()

        // Activity Result Launcher for Gallery
        val launchGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val imageUri = result.data!!.data
                profilePicture.setImageURI(imageUri)
                saveProfilePicture(imageUri)
            }
        }

        // Open Gallery when edit button is clicked
        editImageProf.setOnClickListener {
            val openGalleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            launchGallery.launch(openGalleryIntent)
        }

        // Navigate to Home
        homeIcon.setOnClickListener {
            startActivity(Intent(this, WorkoutPlannerActivity::class.java))
        }

        // Navigate to Progress Tracking
        barChartIcon.setOnClickListener {
            startActivity(Intent(this, ProgressTrackingActivity::class.java))
        }
    }

    private fun fetchUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            databaseReference.child("Users").child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val name = snapshot.child("name").getValue(String::class.java) ?: "N/A"
                        val age = snapshot.child("age").getValue(String::class.java) ?: "N/A"
                        val weight = snapshot.child("weight").getValue(String::class.java) ?: "N/A"
                        val height = snapshot.child("height").getValue(String::class.java) ?: "N/A"
                        val gender = snapshot.child("gender").getValue(String::class.java) ?: "N/A"

                        nameTextView.text = "Name: $name"
                        ageTextView.text = "Age: $age"
                        weightTextView.text = "Weight: $weight kg"
                        heightTextView.text = "Height: $height cm"
                        genderTextView.text = "Gender: $gender"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error
                }
            })
        }
    }

    private fun saveProfilePicture(uri: Uri?) {
        uri?.let {
            sharedPref.edit().putString("profile_picture", it.toString()).apply()
        }
    }

    private fun loadProfilePicture() {
        val savedUri = sharedPref.getString("profile_picture", null)
        if (savedUri != null) {
            profilePicture.setImageURI(Uri.parse(savedUri))
        }
    }
}
