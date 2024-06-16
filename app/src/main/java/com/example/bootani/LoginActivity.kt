package com.example.bootani

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bootani.data.RequestLogin
import com.example.bootani.databinding.ActivityLoginBinding
import com.example.bootani.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginButton.setOnClickListener {
            val username = binding.emailOrPhone.text.toString().trim()
            val password =   binding.password.text.toString().trim()
            loginViewModel.login(RequestLogin( username, password))
        }

        binding.etBackButton.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }

       loginViewModel.loginResponse.observe(this, Observer { response ->
    if (response.isSuccessful) {
        val userResponse = response.body()
        Toast.makeText(this, userResponse?.message, Toast.LENGTH_LONG).show()

        // Start MainActivity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Optional: if you want LoginActivity to be removed from the back stack
    } else {
        val errorResponse = response.errorBody()?.string()
        if (errorResponse?.contains("Kredensial tidak valid") == true) {
            Toast.makeText(this, "Invalid username or password. Please try again.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, errorResponse, Toast.LENGTH_LONG).show()
        }
    }
})

       }
    }
