package app.foodfinderapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import app.foodfinderapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding = ActivityLoginBinding.inflate(layoutInflater) // Inflate the layout and bind its views
        setContentView(binding.root) // Set the root view of the binding object as the content view

        viewModel.setBinding(binding)

        viewModel.toastMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }


        binding.loginPasswordDelete.setOnClickListener {
            onBackPressed()
        }

        // Set up the password text view to start the main activity
        binding.enteredPassword.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }

        // Auto-fill login information if the user previously checked the "remember me" checkbox
        viewModel.loadLoginInfoFromPreferences(binding)

        // Handle login button click
        binding.agreeLogin.setOnClickListener {
            val email = binding.enteredEmail.text.toString()
            val password = binding.enteredPassword.text.toString()

            viewModel.login(email, password, binding.rememberPasswordCheckbox.isChecked)

        }

        binding.convertRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

}
