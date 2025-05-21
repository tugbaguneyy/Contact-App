package com.example.myapplication5.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.myapplication5.utils.ImageUtils.base64ToBitmap

@Composable
fun ProfileImage(
    imageBase64: String,
    name: String,
    surname: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    fontSize: TextUnit = 18.sp,
    onClick: (() -> Unit)? = null
) {
    val bitmap = remember(imageBase64) {
        if (imageBase64.isNotEmpty()) base64ToBitmap(imageBase64) else null
    }

    val boxModifier = modifier
        .clip(CircleShape)
        .background(backgroundColor)
        .then(
            if (onClick != null) Modifier.clickable { onClick() } else Modifier
        )

    Box(
        modifier = boxModifier,
        contentAlignment = Alignment.Center
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Text(
                text = "${name.firstOrNull()?.uppercase() ?: ""}${surname.firstOrNull()?.uppercase() ?: ""}",
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                color = textColor,
                textAlign = TextAlign.Center
            )
        }
    }
}
