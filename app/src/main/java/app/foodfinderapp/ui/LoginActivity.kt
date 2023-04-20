package app.foodfinderapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.foodfinderapp.Application.Companion.context
import app.foodfinderapp.LoginData
import app.foodfinderapp.MainActivity
import app.foodfinderapp.dao.FirebaseAuthDAO
import app.foodfinderapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater) // Inflate the layout and bind its views
        setContentView(binding.root) // Set the root view of the binding object as the content view

        binding.loginPasswordDelete.setOnClickListener {
            onBackPressed()
        }

        binding.enteredPassword.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }

        //auto fulfill
        //A SharedPreferences object is created to store login information
        val prefs = applicationContext.getSharedPreferences("login_info", Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password", false)
        if (isRemember) {
            binding.enteredEmail.setText(prefs.getString("email", ""))
            binding.enteredPassword.setText(prefs.getString("password", ""))
            binding.rememberPasswordCheckbox.isChecked = true
        }

        //login
        binding.agreeLogin.setOnClickListener {
            val email = binding.enteredEmail.text.toString()
            val password = binding.enteredPassword.text.toString()

            // if empty
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "please email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val emailRegex = "^([a-zA-Z0-9._-])+@([a-zA-Z0-9._-])+$".toRegex()
            if (!emailRegex.matches(email)) {
                Toast.makeText(this, "right email pls", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuthDAO.signIn(email, password) { firebaseUser, exception ->
                if (firebaseUser != null) {
                    //save
                    saveLoginInfo()
                    // change login status
                    LoginData.LOGIN_STATUS = 1
                    // to main
                    val intent = Intent(context, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    Toast.makeText(
                        this@LoginActivity,
                        "Logged in as ${FirebaseAuth.getInstance().currentUser?.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(this, "Login failed: ${exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.convertRegister.setOnClickListener {
            val intent = Intent(context, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    private fun saveLoginInfo() {
        val prefs = applicationContext.getSharedPreferences("login_info", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        if (binding.rememberPasswordCheckbox.isChecked) {
            editor.putString("email", binding.enteredEmail.text.toString())
            editor.putString("password", binding.enteredPassword.text.toString())
            editor.putBoolean("remember_password", true)
        } else {
            editor.clear()
        }
        editor.apply()
    }


}
