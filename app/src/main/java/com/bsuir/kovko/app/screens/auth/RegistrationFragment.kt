package com.bsuir.kovko.app.screens.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bsuir.kovko.app.views.RegistrationViewModel
import com.bsuir.kovko.app.dto.UserDTO
import com.bsuir.kovko.app.utils.observeEvent
import com.bsuir.kovko.databinding.FragmentRegistrationBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel by viewModels<RegistrationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        binding.apply {
            btnSend.setOnClickListener {
                val userDTO = UserDTO(
                    username = edLogin.text.toString(),
                    password = edPassword.text.toString(),
                    password2 = edPassword2.text.toString()
                )
                viewModel.registration(userDTO)
                edLogin.setText("")
                edPassword.setText("")
                edPassword2.setText("")
            }
        }
        observeShowAuthMessageEvent()
        return binding.root
    }

    private fun observeShowAuthMessageEvent() = viewModel.message.observeEvent(viewLifecycleOwner) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

}