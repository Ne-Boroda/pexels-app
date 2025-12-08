package com.example.pexelsapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigation(
    currentRoute: String,
    onHomeClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        containerColor = Color.Black
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Home button
            NavigationBarItem(
                selected = currentRoute == "home",
                onClick = onHomeClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        modifier = Modifier.size(24.dp),
                        tint = if (currentRoute == "home") Color.Red else Color.Gray
                    )
                },
                modifier = Modifier.weight(1f)
            )

            // Bookmarks button
            NavigationBarItem(
                selected = currentRoute == "bookmarks",
                onClick = onBookmarksClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Bookmarks",
                        modifier = Modifier.size(24.dp),
                        tint = if (currentRoute == "bookmarks") Color.Red else Color.Gray
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}