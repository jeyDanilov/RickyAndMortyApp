package com.example.presentation.filter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
class FilterViewModel @Inject constructor() : ViewModel() {

    private val _selectedStatus = MutableStateFlow<String?>(null)
    val selectedStatus = _selectedStatus.asStateFlow()

    private val _selectedGender = MutableStateFlow<String?>(null)
    val selectedGender = _selectedGender.asStateFlow()

    private val _selectedSpecies = MutableStateFlow<String?>(null)
    val selectedSpecies = _selectedSpecies.asStateFlow()

    fun onStatusChange(value: String?) {
        _selectedStatus.value = value
    }

    fun onGenderChange(value: String?) {
        _selectedGender.value = value
    }

    fun onSpeciesChange(value: String?) {
        _selectedSpecies.value = value
    }
}
