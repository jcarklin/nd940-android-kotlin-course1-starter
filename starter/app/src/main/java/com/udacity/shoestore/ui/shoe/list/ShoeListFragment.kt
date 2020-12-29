package com.udacity.shoestore.ui.shoe.list

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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
        sharedViewModel.shoeList.forEach {
                shoe -> LayoutShoeItemBinding.inflate(inflater, binding.shoeListLayout, true).shoeModel=shoe
        }
        sharedViewModel.fabClicked.observe(viewLifecycleOwner, Observer {
            isClicked -> if (isClicked) {
                //navigate to details
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListDestinationToShoeDetailsFragment())
                sharedViewModel.onFabClickedFinished()
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
        // menu.findItem(R.id.share)?.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        sharedViewModel.onLoggedOut()
        return super.onOptionsItemSelected(item)
    }
}