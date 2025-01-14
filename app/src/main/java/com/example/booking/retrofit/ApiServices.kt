package com.example.booking.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {
    @POST("register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
