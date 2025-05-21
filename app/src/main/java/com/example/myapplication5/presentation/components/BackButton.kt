package com.example.myapplication5.presentation.components

import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.ArrowBackIosNew


@Composable
fun BackButton(
    navController: NavController,
    modifier: Modifier = Modifier,
    iconSize: Dp = 24.dp,
    buttonSize: Dp = 40.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    tint: Color = MaterialTheme.colorScheme.primary,
    contentDescription: String = "Back"
) {
    IconButton(
        onClick = { navController.navigateUp() },
        modifier = modifier
            .size(buttonSize)
            .background(backgroundColor, CircleShape)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(iconSize)
        )
    }
}
