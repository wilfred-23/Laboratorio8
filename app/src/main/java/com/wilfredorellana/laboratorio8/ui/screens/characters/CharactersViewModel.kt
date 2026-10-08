package com.wilfredorellana.laboratorio8.ui.screens.characters

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

data class CharactersUiState(
    val isLoading: Boolean = true,
    val data: List<Character> = emptyList(),
    val hasError: Boolean = false
)

class CharactersViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CharactersUiState())
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    private var loadJob: Job? = null

    init {
        retry()
    }

    fun showError() {
        loadJob?.cancel()
        _uiState.value = CharactersUiState(
            isLoading = false,
            hasError = true
        )
    }

    fun retry() {
        loadJob?.cancel()
        _uiState.value = CharactersUiState()

        loadJob = viewModelScope.launch {
            try {
                delay(4_000)
                _uiState.value = CharactersUiState(
                    isLoading = false,
                    data = CharacterDb.getCharacters()
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = CharactersUiState(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }
}