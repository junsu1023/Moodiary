package com.example.moodiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.ObserveDiariesUseCase
import com.example.moodiary.state.HistoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val observeDiariesUseCase: ObserveDiariesUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> get() = _uiState.asStateFlow()

    init {
        observeDiaries()
    }

    private fun observeDiaries() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }

            observeDiariesUseCase().collect { diaries ->
                _uiState.update { currentState ->
                    currentState.copy(
                        diaries = diaries,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun updateFilter(filter: String) {
        _uiState.update { currentState ->
            currentState.copy(
                filter = filter
            )
        }
    }
}