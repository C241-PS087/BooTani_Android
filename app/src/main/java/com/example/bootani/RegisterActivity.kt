package com.example.bootani

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bootani.databinding.ActivityRegisterBinding
import com.example.bootani.viewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.signUp.setOnClickListener {
            val username = binding.nameInput.text.toString().trim()
            val emailOrPhone = binding.emailOrPhoneNumber.text.toString().trim()
            val password = binding.password.text.toString().trim()
            val confirmPassword = binding.confirmPassword.text.toString().trim()

            registerViewModel.signUp(SignUpRequest(username, emailOrPhone, password, confirmPassword))
        }

        binding.etBackButton.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }

        registerViewModel.signUpResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                val userResponse = response.body()
                Toast.makeText(this, userResponse?.message, Toast.LENGTH_LONG).show()

                // Start LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // Optional: if you want RegisterActivity to be removed from the back stack
            } else {
                val errorResponse = response.errorBody()?.string()
                Toast.makeText(this, errorResponse, Toast.LENGTH_LONG).show()
            }
        })
    }
}