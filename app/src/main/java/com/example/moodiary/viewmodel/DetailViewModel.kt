package com.example.moodiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.mapper.convertString
import com.example.domain.usecase.GetDiaryByIdUseCase
import com.example.moodiary.state.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDiaryById: GetDiaryByIdUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getDiary(diaryId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            getDiaryById(diaryId).onSuccess { diary ->
                _uiState.update { currentState ->
                    currentState.copy(
                        date = diary.timeStamp!!.convertString(),
                        content = diary.content,
                        music = diary.musicUrl,
                        quote = diary.quote,
                        score = diary.emotionScore
                    )
                }
            } .onFailure { exception ->
                _uiState.update { currentState ->
                    currentState.copy(
                        errorMessage = exception.message ?: "알 수 없는 오류가 발생했습니다."
                    )
                }
            }

            _uiState.update { it.copy(isLoading = false) }
        }
    }
}