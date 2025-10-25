package com.example.presentation.filter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

// ViewModel for managing character filter state (status, gender, species).
class FilterViewModel @Inject constructor() : ViewModel() {

    // Holds the selected status filter (e.g. Alive, Dead).
    private val _selectedStatus = MutableStateFlow<String?>(null)
    val selectedStatus = _selectedStatus.asStateFlow()

    // Holds the selected gender filter (e.g. Male, Female).
    private val _selectedGender = MutableStateFlow<String?>(null)
    val selectedGender = _selectedGender.asStateFlow()

    // Holds the selected species filter (e.g. Human, Alien).
    private val _selectedSpecies = MutableStateFlow<String?>(null)
    val selectedSpecies = _selectedSpecies.asStateFlow()

    // Updates status filter.
    fun onStatusChange(value: String?) {
        _selectedStatus.value = value
    }

    // Updates gender filter.
    fun onGenderChange(value: String?) {
        _selectedGender.value = value
    }

    // Updates species filter.
    fun onSpeciesChange(value: String?) {
        _selectedSpecies.value = value
    }
}
