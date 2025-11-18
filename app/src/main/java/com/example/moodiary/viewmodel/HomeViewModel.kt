package com.example.moodiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.mapper.convertString
import com.example.domain.usecase.ObserveDiariesUseCase
import com.example.moodiary.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    observeDiariesUseCase: ObserveDiariesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            observeDiariesUseCase().collect { diaries ->
                val today = Date(System.currentTimeMillis()).convertString()
                val todayDiaries = diaries.filter { diary ->
                    (diary.timeStamp?.convertString() ?: today) == today
                }

                _uiState.update { currentState ->
                    currentState.copy(
                        diaries = diaries,
                        todayDiaries = todayDiaries
                    )
                }
            }
        }
    }
}
