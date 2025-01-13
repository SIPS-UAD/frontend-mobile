package com.example.booking.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.booking.EditProfileActivity
import com.example.booking.LoginActivity
import com.example.booking.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            profileViewModel.logout()
            val mIntent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(mIntent)
            requireActivity().finish()
        }
        binding.btnEdit.setOnClickListener {
            val mIntent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(mIntent)
        }
    }
}