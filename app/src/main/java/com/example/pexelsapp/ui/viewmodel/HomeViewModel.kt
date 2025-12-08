package com.example.pexelsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.model.Photo
import com.example.pexelsapp.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _featuredCollections = MutableStateFlow(listOf(
        "Nature", "City", "Travel", "Animals", "Food", "People", "Art"
    ))
    val featuredCollections: StateFlow<List<String>> = _featuredCollections.asStateFlow()

    private var currentPage = 1
    private var currentQuery = ""

    init {
        loadCuratedPhotos()
    }

    fun loadCuratedPhotos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val photos = repository.getCuratedPhotos(page = currentPage, perPage = 30)
                _uiState.value = HomeUiState(photos = photos)
            } catch (e: Exception) {
                _uiState.value = HomeUiState(error = e.message)
            }
        }
    }

    fun searchPhotos(query: String) {
        currentQuery = query
        currentPage = 1

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val photos = repository.searchPhotos(query, page = 1, perPage = 30)
                _uiState.value = HomeUiState(photos = photos)
            } catch (e: Exception) {
                _uiState.value = HomeUiState(error = e.message)
            }
        }
    }

    fun loadMore() {
        currentPage++

        viewModelScope.launch {
            val newPhotos = if (currentQuery.isEmpty()) {
                repository.getCuratedPhotos(page = currentPage, perPage = 30)
            } else {
                repository.searchPhotos(currentQuery, page = currentPage, perPage = 30)
            }

            _uiState.value = _uiState.value.copy(
                photos = _uiState.value.photos + newPhotos
            )
        }
    }
}

data class HomeUiState(
    val photos: List<Photo> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String? = null,
    val isNetworkError: Boolean = false
)