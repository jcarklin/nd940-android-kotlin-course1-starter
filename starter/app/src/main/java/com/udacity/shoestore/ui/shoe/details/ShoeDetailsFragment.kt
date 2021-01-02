package com.udacity.shoestore.ui.shoe.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseMethod
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding


class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding
    private lateinit var shoeDetailsViewModel: ShoeDetailsViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        shoeDetailsViewModel = ViewModelProvider(this).get(ShoeDetailsViewModel::class.java)
        binding.shoeViewModel = shoeDetailsViewModel
        binding.setLifecycleOwner { this.lifecycle }

        shoeDetailsViewModel.addShoeClicked.observe(viewLifecycleOwner, {
            isClicked -> if (isClicked) {
                if (shoeDetailsViewModel.isShoeValid()) {
                    sharedViewModel.addShoe(shoeDetailsViewModel.newShoe.value)
                    findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListDestination())
                } else {
                    Toast.makeText(context, R.string.error_enter_shoe_details, Toast.LENGTH_LONG).show()
                }
                shoeDetailsViewModel.onSaveClickedFinished()
            }
        })

        shoeDetailsViewModel.cancelClicked.observe(viewLifecycleOwner, {
            isClicked -> if (isClicked) {
                findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListDestination())
                shoeDetailsViewModel.onCancelClickedFinished()
            }
        })

        return binding.root
    }


}
object ConvertDouble {
    @InverseMethod("stringToDouble")
    @JvmStatic fun doubleToString(value: Double) : String {
        return value.toString()
    }

    @JvmStatic fun stringToDouble(value: String) : Double {
        if (value.isBlank()) {
            return 0.0
        }
        return value.toDouble()
    }

}