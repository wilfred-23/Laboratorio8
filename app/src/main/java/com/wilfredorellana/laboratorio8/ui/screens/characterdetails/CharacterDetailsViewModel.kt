package com.wilfredorellana.laboratorio8.ui.screens.characterdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.wilfredorellana.laboratorio8.data.Character
import com.wilfredorellana.laboratorio8.data.CharacterDb
import com.wilfredorellana.laboratorio8.navigation.CharacterDetailsRoute
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

class CharacterDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val characterId =
        savedStateHandle.toRoute<CharacterDetailsRoute>().characterId

    private val _uiState = MutableStateFlow(CharacterDetailsUiState())
    val uiState: StateFlow<CharacterDetailsUiState> = _uiState.asStateFlow()

    private var loadJob: Job? = null

    init {
        retry()
    }

    fun showError() {
        loadJob?.cancel()
        _uiState.value = CharacterDetailsUiState(
            isLoading = false,
            hasError = true
        )
    }

    fun retry() {
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