package com.udacity.shoestore.ui.shoe.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailsViewModel : ViewModel() {

    private val _newShoe = MutableLiveData(Shoe("",0.0,"",""))
    val newShoe: LiveData<Shoe> get() = _newShoe

    private val _addShoeClicked = MutableLiveData(false)
    val addShoeClicked: LiveData<Boolean> get() = _addShoeClicked

    private val _cancelClicked = MutableLiveData(false)
    val cancelClicked: LiveData<Boolean> get() = _cancelClicked

    fun onSaveClicked() {
        _addShoeClicked.value = true
    }

    fun onSaveClickedFinished() {
        _addShoeClicked.value = false
    }

    fun onCancelClicked() {
        _cancelClicked.value = true
    }

    fun onCancelClickedFinished() {
        _cancelClicked.value = false
    }

    fun isShoeValid(): Boolean {
        return (_newShoe.value!!.name.isNotBlank() && _newShoe.value!!.company.isNotBlank()
                && _newShoe.value!!.size>0.0 && newShoe.value!!.description.isNotBlank())
    }


}