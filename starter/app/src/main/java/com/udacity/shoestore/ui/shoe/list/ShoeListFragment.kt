package com.udacity.shoestore.ui.shoe.list

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.LayoutShoeItemBinding

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.setLifecycleOwner { this.lifecycle }
        setHasOptionsMenu(true)

        sharedViewModel.shoeListLiveData.observe(viewLifecycleOwner, {
            shoeList -> shoeList.forEach { shoe ->
                LayoutShoeItemBinding.inflate(inflater, binding.shoeListLayout, true).shoeModel = shoe
            }
        })

        sharedViewModel.logoutClicked.observe(viewLifecycleOwner, {
                isClicked -> if (isClicked) {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListDestinationToLoginDestination())
            sharedViewModel.onLogoutClickedFinished()
        }
        })
        sharedViewModel.fabClicked.observe(viewLifecycleOwner, {
                isClicked -> if (isClicked) {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListDestinationToShoeDetailsFragment())
            sharedViewModel.onFabClickedFinished()
        }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        sharedViewModel.onLogoutClicked()
        return super.onOptionsItemSelected(item)
    }
}