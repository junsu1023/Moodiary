package com.example.moodiary.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.SaveDiaryUseCase
import com.example.moodiary.state.SaveState
import com.example.moodiary.state.WriteScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val saveDiaryUseCase: SaveDiaryUseCase
): ViewModel() {
    var uiState by mutableStateOf(WriteScreenUiState())
        private set

    fun onContentChanged(content: String) {
        uiState = uiState.copy(content = content)
    }


    fun saveDiary() {
        val content = uiState.content
        if(content.isEmpty()) {
            uiState = uiState.copy(
                saveState = SaveState.ERROR,
                errorMessage = "내용을 입력해주세요."
            )

            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(saveState = SaveState.LOADING)

            saveDiaryUseCase(content)
                .onSuccess {
                    uiState = uiState.copy(saveState = SaveState.SUCCESS)
                }.onFailure { exception ->
                    uiState = uiState.copy(
                        saveState = SaveState.ERROR,
                        errorMessage = exception.message
                    )
                }
        }
    }
}