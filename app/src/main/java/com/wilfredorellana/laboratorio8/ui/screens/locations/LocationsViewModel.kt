package com.wilfredorellana.laboratorio8.ui.screens.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilfredorellana.laboratorio8.data.Location
import com.wilfredorellana.laboratorio8.data.LocationDb
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LocationsUiState(
    val isLoading: Boolean = true,
    val data: List<Location> = emptyList(),
    val hasError: Boolean = false
)

class LocationsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LocationsUiState())
    val uiState: StateFlow<LocationsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                delay(4_000)
                _uiState.value = LocationsUiState(
                    isLoading = false,
                    data = LocationDb.getLocations()
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = LocationsUiState(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }
}