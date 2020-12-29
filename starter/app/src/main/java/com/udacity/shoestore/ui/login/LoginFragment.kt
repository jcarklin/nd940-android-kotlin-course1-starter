package com.udacity.shoestore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentLoginBinding

/**
 * Fragment for starting login screen of app
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.buttonLogin.setOnClickListener { loginUser() }
        binding.buttonRegister.setOnClickListener { loginUser() }


        return binding.root
    }


    private fun loginUser() {
        if (binding.etEmailAddress.text.isBlank() || binding.etPassword.text.isBlank()) {
            Toast.makeText(context, R.string.error_enter_login_details, Toast.LENGTH_LONG).show()
        } else {
            sharedViewModel.onLoggedIn(binding.etEmailAddress.text.toString())
        }
    }
}