package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel : ViewModel() {


    private val _shoeList = ArrayList<Shoe>()

    private val _shoeListLiveData = MutableLiveData<ArrayList<Shoe>>()
    val shoeListLiveData: LiveData<ArrayList<Shoe>>
        get() = _shoeListLiveData

    private val _loginClicked = MutableLiveData(false)
    val loginClicked: LiveData<Boolean> get() = _loginClicked

    private val _logoutClicked = MutableLiveData(false)
    val logoutClicked: LiveData<Boolean> get() = _logoutClicked

    private val _fabClicked = MutableLiveData(false)
    val fabClicked: LiveData<Boolean> get() = _fabClicked

    fun addShoe(shoe: Shoe?) {
        if (shoe != null) {
            _shoeList.add(shoe)
            _shoeListLiveData.value = _shoeList
        }
    }

    private fun clearList() {
        _shoeList.clear()
        _shoeListLiveData.value = _shoeList
    }

    fun onLoginClicked() {
        _loginClicked.value = true
    }

    fun onLoginClickedFinished() {
        //addTestShoes()
        _loginClicked.value = false
    }

    fun onFabClicked() {
        _fabClicked.value = true
    }

    fun onFabClickedFinished() {
        _fabClicked.value = false
    }

    fun onLogoutClicked() {
        _logoutClicked.value = true
    }

    fun onLogoutClickedFinished() {
        clearList()
        _logoutClicked.value = false
    }

//    fun addTestShoes() {
//        _shoeList.add(Shoe("Shoe 1", 6.0, "Company1", "Test Shoe 1"))
//        _shoeList.add(Shoe("Shoe 2", 4.0, "Company1", "Test Shoe 2"))
//        _shoeList.add(Shoe("Shoe 3", 6.5, "Company1", "Test Shoe 3"))
//        _shoeList.add(Shoe("Shoe 4", 12.0, "Company1", "Test Shoe 4"))
//    }
}