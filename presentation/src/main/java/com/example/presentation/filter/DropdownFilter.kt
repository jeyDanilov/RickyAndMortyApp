package com.example.presentation.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Composable that renders three dropdown filters: Status, Gender, Species.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownFilter(
    selectedStatus: String?,
    onStatusChange: (String?) -> Unit,
    selectedGender: String?,
    onGenderChange: (String?) -> Unit,
    selectedSpecies: String?,
    onSpeciesChange: (String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding( WindowInsets.statusBars.asPaddingValues()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // Status dropdown (Alive / Dead / Unknown).
        DropdownSelector(
            label = "Статус",
            options = listOf("Alive", "Dead", "Unknown"),
            selectedOption = selectedStatus,
            onOptionSelected = onStatusChange
        )

        // Gender dropdown (Male / Female / Unknown).
        DropdownSelector(
            label = "Пол",
            options = listOf("Male", "Female", "Unknown"),
            selectedOption = selectedGender,
            onOptionSelected = onGenderChange
        )

        // Species dropdown (Human / Alien / Robot).
        DropdownSelector(
            label = "Вид",
            options = listOf("Human", "Alien", "Robot"),
            selectedOption = selectedSpecies,
            onOptionSelected = onSpeciesChange
        )
    }
}

// Reusable dropdown selector with clear and reset options.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropdownSelector(
    label: String,
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedOption ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // List of selectable options.
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
            // Option to clear the selection.
            DropdownMenuItem(
                text = { Text("Очистить") },
                onClick = {
                    onOptionSelected(null)
                    expanded = false
                }
            )
        }
    }
}
