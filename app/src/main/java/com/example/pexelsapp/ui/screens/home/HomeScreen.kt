package com.example.pexelsapp.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pexelsapp.ui.components.BottomNavigation
import com.example.pexelsapp.ui.components.FeaturedCollections
import com.example.pexelsapp.ui.components.PhotoGrid
import com.example.pexelsapp.ui.components.SearchBar
import com.example.pexelsapp.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToBookmarks: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val featuredCollections by viewModel.featuredCollections.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var debouncedQuery by remember { mutableStateOf("") }

    LaunchedEffect(searchQuery) {
        delay(500)
        debouncedQuery = searchQuery
        if (debouncedQuery.isNotEmpty()) {
            viewModel.searchPhotos(debouncedQuery)
        } else {
            viewModel.loadCuratedPhotos()
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        bottomBar = {
            BottomNavigation(
                currentRoute = "home",
                onHomeClick = { /* уже на home */ },
                onBookmarksClick = onNavigateToBookmarks
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.Black
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                // Search Bar
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = {
                        if (searchQuery.isNotEmpty()) {
                            viewModel.searchPhotos(searchQuery)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    hint = "Search for free photos"
                )

                // Featured Collections
                FeaturedCollections(
                    collections = featuredCollections,
                    onCollectionClick = { collection ->
                        searchQuery = collection
                        viewModel.searchPhotos(collection)
                    },
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Photo Grid
                PhotoGrid(
                    photos = uiState.photos,
                    isLoading = uiState.isLoading,
                    onPhotoClick = { photo ->
                        // TODO: открыть детали
                    },
                    onLoadMore = { /* TODO: доделать */ },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}