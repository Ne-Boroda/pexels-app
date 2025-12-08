package com.example.pexelsapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "Search"
) {
    androidx.compose.material3.TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier,
        placeholder = { androidx.compose.material3.Text(hint) },
        leadingIcon = {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                androidx.compose.material3.IconButton(
                    onClick = { onQueryChange("") }
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear"
                    )
                }
            }
        },
        singleLine = true,
        colors = androidx.compose.material3.TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF333333),
            unfocusedContainerColor = Color(0xFF333333),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}