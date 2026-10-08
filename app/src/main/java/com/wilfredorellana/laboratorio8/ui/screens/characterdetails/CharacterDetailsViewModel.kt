package com.wilfredorellana.laboratorio8.ui.screens.characterdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilfredorellana.laboratorio8.data.Character
import com.wilfredorellana.laboratorio8.data.CharacterDb
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CharacterDetailsUiState(
    val isLoading: Boolean = true,
    val data: Character? = null,
    val hasError: Boolean = false
)

class CharacterDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CharacterDetailsUiState())
    val uiState: StateFlow<CharacterDetailsUiState> = _uiState.asStateFlow()

    private var requestedId: Int? = null
    private var loadJob: Job? = null

    fun load(characterId: Int) {
        if (requestedId == characterId) return

        requestedId = characterId
        loadJob?.cancel()
        _uiState.value = CharacterDetailsUiState()

        loadJob = viewModelScope.launch {
            try {
                delay(2_000)
                val character = CharacterDb.getCharacterById(characterId)

                _uiState.value = CharacterDetailsUiState(
                    isLoading = false,
                    data = character,
                    hasError = character == null
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = CharacterDetailsUiState(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }
}