package com.udacity.shoestore.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding


/**
 * Fragment with instructions for the user
 */
class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)


        binding.button.setOnClickListener {
            findNavController().navigate(InstructionsFragmentDirections.actionInstructionsDestinationToShoeListFragment())
        }

        return binding.root
    }


}