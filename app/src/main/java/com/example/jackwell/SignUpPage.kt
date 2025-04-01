package com.example.jackwell

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val usernameEditText: EditText = findViewById(R.id.user_input)
        val emailEditText: EditText = findViewById(R.id.email_input)
        val passwordEditText: EditText = findViewById(R.id.password_input)
        val signUpButton: Button = findViewById(R.id.sign_up_button)
        val alreadyRegisteredText: TextView = findViewById(R.id.already_registered_text)

        val fullText = "Already Registered? Login Here."
        val spannableString = SpannableString(fullText)

        val clickableText = "Login Here."
        val startIndex = fullText.indexOf(clickableText)
        val endIndex = startIndex + clickableText.length

        val clickableSpan = object : android.text.style.ClickableSpan() {

            override fun onClick(widget: View) {
                val intent = Intent(this@SignUpPage, LoginActivity::class.java)
                startActivity(intent)
            }


            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = Color.WHITE
            }
        }
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        alreadyRegisteredText.text = spannableString
        alreadyRegisteredText.movementMethod = LinkMovementMethod.getInstance()
        alreadyRegisteredText.highlightColor = Color.TRANSPARENT


        signUpButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill All The Fields!", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "Password Must Be 6 Characters!", Toast.LENGTH_SHORT).show()
            } else {
                signUpUser(username, email, password)
            }
        }
    }

    private fun signUpUser(username: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        saveUserToFirestore(userId, username, email, password)
                    }
                } else {
                    Toast.makeText(this, "Sign-up Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun saveUserToFirestore(userId: String, username: String, email: String, password: String) {
        val user = hashMapOf(
            "username" to username,
            "email" to email,
            "password" to password
        )

        db.collection("users").document(userId)
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed To Save User", Toast.LENGTH_SHORT).show()
            }
    }
}