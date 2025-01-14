package com.example.booking

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.booking.databinding.ActivityLoginBinding
import com.example.booking.retrofit.ApiConfig
import com.example.booking.retrofit.LoginRequest
import com.example.booking.retrofit.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)

        Log.d("LoginActivity", "onCreate called")

        binding.btnLogin.setOnClickListener {
            val email = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                performLogin(email, password)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun performLogin(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)

        val client = ApiConfig.getApiService().login(loginRequest)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.status == 200) {
                        val token = loginResponse.data?.token
                        saveToken(token)
                        Toast.makeText(this@LoginActivity, "Login Successful!", Toast.LENGTH_SHORT).show()
                        navigateToMainActivity()
                    } else {
                        Toast.makeText(this@LoginActivity, loginResponse?.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Login Failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("LoginActivity", "onFailure: ${t.message}", t)
                Toast.makeText(this@LoginActivity, "Login Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun saveToken(token: String?) {
        if (!token.isNullOrEmpty()) {
            sharedPreferences.edit().putString("authToken", token).apply()
        }
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
