package com.example.booking

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.booking.R

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout langsung dari XML
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        // Inisialisasi ListView
        val listView: ListView = view.findViewById(R.id.listView)

        // Data dummy
        val bookings = listOf(
            Booking("Rabu, 14 Des 2024", "18.00 - 21.00", true),
            Booking("Rabu, 14 Des 2024", "18.00 - 21.00", false),
            Booking("Rabu, 14 Des 2024", "18.00 - 21.00", true)
        )

        // Pasang adapter
        val adapter = BookingListAdapter(requireContext(), bookings)
        listView.adapter = adapter

        return view
    }

    // Data class untuk representasi Booking
    data class Booking(val id: String, val time: String, val isApproved: Boolean)

    // Custom adapter
    inner class BookingListAdapter(
        private val context: Context,
        private val bookings: List<Booking>
    ) : BaseAdapter(), ListAdapter {

        override fun getCount(): Int = bookings.size
        override fun getItem(position: Int): Any = bookings[position]
        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(
                android.R.layout.simple_list_item_2, parent, false
            )

            // Isi data ke item layout bawaan Android
            val booking = bookings[position]
            val text1 = view.findViewById<TextView>(android.R.id.text1)
            val text2 = view.findViewById<TextView>(android.R.id.text2)

            text1.text = "Booking ID - ${booking.id}"
            text2.text = booking.time
            view.setBackgroundColor(
                if (booking.isApproved) Color.parseColor("@android:color/white") else Color.parseColor("#8b0000")
            )


            return view
        }
    }
}