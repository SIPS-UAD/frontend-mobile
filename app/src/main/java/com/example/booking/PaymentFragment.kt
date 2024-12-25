package com.example.booking

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class PaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_payment, container, false)

        // Retrieve arguments from BookingFragment
        val orderName = arguments?.getString("orderName") ?: "Unknown Order"
        val bandName = arguments?.getString("bandName") ?: "Unknown Band"
        val totalTime = arguments?.getString("totalTime") ?: "1"
        val totalPayment = calculatePayment(totalTime)

        val timerTextView = view.findViewById<TextView>(R.id.countdownText)
        val uploadProofButton = view.findViewById<Button>(R.id.uploadProofButton)
        val payButton = view.findViewById<Button>(R.id.payButton)

        // Start countdown timer
        setupCountdownTimer(timerTextView)

        // Upload proof button logic
        uploadProofButton.setOnClickListener {
            Toast.makeText(context, "Upload proof feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Pay button logic
        payButton.setOnClickListener {
            navigateToDoneFragment(orderName, bandName, totalTime, totalPayment)
        }

        return view
    }

    private fun setupCountdownTimer(timerTextView: TextView) {
        object : CountDownTimer(600000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timerTextView.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                Toast.makeText(context, "Payment time expired!", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun calculatePayment(totalTime: String): String {
        val ratePerHour = 100000 // Example rate
        val hours = totalTime.toIntOrNull() ?: 1
        return "Rp${ratePerHour * hours}"
    }

    private fun navigateToDoneFragment(orderName: String, bandName: String, totalTime: String, totalPayment: String) {
        val bundle = Bundle().apply {
            putString("transactionId", "FGT-12383")
            putString("orderName", orderName)
            putString("bandName", bandName)
            putString("totalTime", totalTime)
            putString("totalPayment", totalPayment)
        }
        findNavController().navigate(R.id.doneFragment, bundle)
    }
}