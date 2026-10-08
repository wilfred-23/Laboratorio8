package com.wilfredorellana.laboratorio8.ui.screens.locationdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilfredorellana.laboratorio8.data.Location
import com.wilfredorellana.laboratorio8.data.LocationDb
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LocationDetailsUiState(
    val isLoading: Boolean = true,
    val data: Location? = null,
    val hasError: Boolean = false
)

class LocationDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LocationDetailsUiState())
    val uiState: StateFlow<LocationDetailsUiState> = _uiState.asStateFlow()

    private var requestedId: Int? = null
    private var loadJob: Job? = null

    fun load(locationId: Int) {
        if (requestedId == locationId) return

        requestedId = locationId
        loadJob?.cancel()
        _uiState.value = LocationDetailsUiState()

        loadJob = viewModelScope.launch {
            try {
                delay(2_000)
                val location = LocationDb.getLocationById(locationId)

                _uiState.value = LocationDetailsUiState(
                    isLoading = false,
                    data = location,
                    hasError = location == null
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = LocationDetailsUiState(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }
}