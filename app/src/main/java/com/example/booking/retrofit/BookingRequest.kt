package com.example.booking.retrofit

data class BookingRequest (
    val userId: Int,
    val scheduleId: Int,
    val bandName: String,
    val duration: Int,
    val totalPrice: String,
    val paymenProof: String,
    val notes: String
)