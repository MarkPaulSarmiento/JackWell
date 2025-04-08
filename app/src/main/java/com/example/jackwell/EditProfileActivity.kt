    package com.example.jackwell

    import android.os.Bundle
    import android.widget.EditText
    import android.widget.Button
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity
    import com.google.firebase.database.DatabaseReference
    import com.google.firebase.database.FirebaseDatabase
    import com.google.firebase.auth.FirebaseAuth

    class EditProfileActivity : AppCompatActivity() {
        private lateinit var databaseReference: DatabaseReference
        private lateinit var auth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_edit_profile)

            val nameEditText = findViewById<EditText>(R.id.name)
            val ageEditText = findViewById<EditText>(R.id.age_edit_text)
            val weightEditText = findViewById<EditText>(R.id.weight_edit_text)
            val heightEditText = findViewById<EditText>(R.id.height_edit_text)
            val genderEditText = findViewById<EditText>(R.id.gender_edit_text)
            val saveButton = findViewById<Button>(R.id.save_button)

            auth = FirebaseAuth.getInstance()
            databaseReference = FirebaseDatabase.getInstance().reference

            saveButton.setOnClickListener {
                val name = nameEditText.text.toString()
                val age = ageEditText.text.toString()
                val weight = weightEditText.text.toString()
                val height = heightEditText.text.toString()
                val gender = genderEditText.text.toString()

                if (name.isEmpty() || age.isEmpty() || weight.isEmpty() || height.isEmpty() || gender.isEmpty()) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                } else {
                    saveUserData(name, age, weight, height, gender)
                }
            }
        }

        private fun saveUserData(name: String, age: String, weight: String, height: String, gender: String) {
            val userId = auth.currentUser?.uid

            if (userId != null) {
                val userMap = mapOf(
                    "name" to name,
                    "age" to age,
                    "weight" to weight,
                    "height" to height,
                    "gender" to gender
                )

                databaseReference.child("Users").child(userId).setValue(userMap)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Profile saved successfully", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to save profile", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
            }
        }
    }
