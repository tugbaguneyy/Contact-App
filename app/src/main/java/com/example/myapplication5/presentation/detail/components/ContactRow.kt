package com.example.myapplication5.presentation.detail.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactRow(
    icon: ImageVector,
    title: String,
    data: String,
    isNumber: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Icon
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Contact Information
        Column {
            Text(
                text = title,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            )
            Text(
                text = data,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Action Icons
        if (isNumber) {
            Icon(
                imageVector = Icons.Default.MailOutline,
                contentDescription = "Message",
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Call",
                tint = MaterialTheme.colorScheme.primary
            )
        } else {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}