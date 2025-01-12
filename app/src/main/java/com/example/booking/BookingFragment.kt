package com.example.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.booking.R

class BookingFragment : Fragment() {

    private var totalTime = 1 // Default value for total time

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_booking, container, false)

        // Booking inputs
        val orderNameEditText = view.findViewById<EditText>(R.id.orderName)
        val bandNameEditText = view.findViewById<EditText>(R.id.bandName)
        val noteEditText = view.findViewById<EditText>(R.id.note)
        val totalTimeTextBooking = view.findViewById<TextView>(R.id.totalTimeTextBooking)

        // Buttons
        val minusButton = view.findViewById<Button>(R.id.minusButton)
        val plusButton = view.findViewById<Button>(R.id.plusButton)
        val continueButton = view.findViewById<Button>(R.id.continueButton)

        // Initialize total time text
        totalTimeTextBooking.text = totalTime.toString()

        // Minus button click listener
        minusButton.setOnClickListener {
            if (totalTime > 1) {
                totalTime--
                totalTimeTextBooking.text = totalTime.toString()
            } else {
                Toast.makeText(context, "Minimum time is 1 hour", Toast.LENGTH_SHORT).show()
            }
        }

        // Plus button click listener
        plusButton.setOnClickListener {
            totalTime++
            totalTimeTextBooking.text = totalTime.toString()
        }

        // Continue button click listener
        continueButton.setOnClickListener {
            val orderName = orderNameEditText.text.toString().trim()
            val bandName = bandNameEditText.text.toString().trim()
            val note = noteEditText.text.toString().trim()

            if (orderName.isNotEmpty() && bandName.isNotEmpty()) {
                // Pass data to PaymentFragment using arguments
                val bundle = Bundle().apply {
                    putString("orderName", orderName)
                    putString("bandName", bandName)
                    putString("note", note)
                    putInt("totalTime", totalTime)
                }
                findNavController().navigate(R.id.action_bookingFragment_to_paymentFragment, bundle)
            } else {
                Toast.makeText(context, "Please fill out all required fields", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}