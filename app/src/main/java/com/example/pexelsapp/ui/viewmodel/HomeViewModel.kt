package com.example.pexelsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.model.Photo
import com.example.pexelsapp.domain.model.UiState
import com.example.pexelsapp.domain.usecase.GetCuratedPhotosUseCase
import com.example.pexelsapp.ui.screens.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCuratedPhotosUseCase: GetCuratedPhotosUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(UiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val photos = getCuratedPhotosUseCase()
                _uiState.value = if (photos.isEmpty()) {
                    UiState.Empty
                } else {
                    UiState.Success(photos)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }

        fun refresh() {
            loadPhotos()
        }
    }
}