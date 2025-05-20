package com.example.myapplication5.presentation.detail.components


import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
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
    val ctx = LocalContext.current

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
            tint = MaterialTheme.colorScheme.surfaceTint,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Contact Information
        Column {
            Text(
                text = title,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 14.sp
                )
            )
            Text(
                text = data,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (isNumber) {

            IconButton(onClick = {
                val smsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$data"))
                try {
                    ctx.startActivity(smsIntent)
                } catch (e: Exception) {
                    Toast.makeText(ctx, "Couldn't open messaging app", Toast.LENGTH_LONG).show()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Message,
                    contentDescription = "Message",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            IconButton(onClick = {
                val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$data"))
                try {
                    ctx.startActivity(callIntent)
                } catch (e: SecurityException) {
                    Toast.makeText(ctx, "An error occurred", Toast.LENGTH_LONG).show()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Call",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        } else {
            IconButton(onClick = {
                val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$data"))
                try {
                    ctx.startActivity(emailIntent)
                } catch (e: Exception) {
                    Toast.makeText(ctx, "Couldn't open email app", Toast.LENGTH_LONG).show()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}