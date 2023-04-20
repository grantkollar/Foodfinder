package app.foodfinderapp.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.foodfinderapp.Application.Companion.context
import app.foodfinderapp.dao.FirebaseAuthDAO.signUp
import app.foodfinderapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater) // Inflate the layout and bind its views
        setContentView(binding.root) // Set the root view of the binding object as the content view

        binding.registerPasswordDelete.setOnClickListener {
            onBackPressed()
        }

        // Set up the click listener for the register button
        binding.agreeRegister.setOnClickListener {
            val name = binding.enteredName.text.toString()
            val email = binding.enteredEmail.text.toString()
            val password = binding.enteredPassword.text.toString()

            // Validate the input
            if (name.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val emailRegex = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$".toRegex()
            if (!emailRegex.matches(email)) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            Toast.makeText(this, "register...", Toast.LENGTH_SHORT).show()

            signUp(email, password) { firebaseUser, exception ->
                if (exception == null) {
                    // User was created successfully, log the user in and navigate to home screen
                    Log.d(TAG, "User created successfully")
                    // Update user profile if necessary
                    firebaseUser?.updateProfile(
                        UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build())
                    // Navigate to home screen
                    val intent = Intent(context, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                } else {
                    // User creation failed, display an error message to the user
                    Log.w(TAG, "User creation failed", exception)
                    Toast.makeText(this, "User creation failed", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }
}
