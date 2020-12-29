package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel : ViewModel() {

    private val _loggedIn = MutableLiveData("")
    val loggedIn: LiveData<String> get() = _loggedIn

    private val _welcomed = MutableLiveData(false)
    val welcomed: LiveData<Boolean> get() = _welcomed

    private val _shoeList = ArrayList<Shoe>()
    val shoeList: List<Shoe> get() = _shoeList

    private val _fabClicked = MutableLiveData(false)
    val fabClicked: LiveData<Boolean> get() = _fabClicked

    fun onLoggedIn(email: String) {
        _loggedIn.value = email
        addTestShoes()
    }

    fun onLoggedOut() {
        _loggedIn.value = ""
        _shoeList.clear()
    }

    fun addShoe(shoe: Shoe) {
        _shoeList.add(shoe)
    }

    fun clearList() {
        _shoeList.clear()
    }

    fun onFabClicked() {
        _fabClicked.value = true
    }

    fun onFabClickedFinished() {
        _fabClicked.value = false
    }

    fun addTestShoes() {
        _shoeList.add(Shoe("Shoe 1", 6.0, "Company1", "Test Shoe 1"))
        _shoeList.add(Shoe("Shoe 2", 4.0, "Company1", "Test Shoe 2"))
        _shoeList.add(Shoe("Shoe 3", 6.5, "Company1", "Test Shoe 3"))
        _shoeList.add(Shoe("Shoe 4", 12.0, "Company1", "Test Shoe 4"))
    }
}