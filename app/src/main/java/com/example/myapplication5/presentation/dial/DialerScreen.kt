package com.example.myapplication5.presentation.dial

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication5.presentation.dial.components.DialButton

@Composable
fun DialerScreen(navController: NavController) {
    var input by remember { mutableStateOf("") }
    val ctx = LocalContext.current

    val dialButtons = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("*", "0", "#")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Numara alanı
        Text(
            text = input,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tuş takımı
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            dialButtons.forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    row.forEach { label ->
                        DialButton(label = label) {
                            input += label
                        }
                    }
                }
            }
        }

        // Arama ve silme butonları
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Arama butonu
            IconButton(
                onClick = {
                    val u = Uri.parse("tel:$input")
                    val i = Intent(Intent.ACTION_DIAL, u)
                    try {
                        ctx.startActivity(i)
                    } catch (s: SecurityException) {
                        Toast.makeText(ctx, "An error occurred", Toast.LENGTH_LONG)
                            .show()
                    }
                },
                modifier = Modifier
                    .size(72.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Ara",
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
            }

            // Silme butonu: sağda, küçük ve tertiary renkte
            IconButton(
                onClick = {
                    if (input.isNotEmpty()) {
                        input = input.dropLast(1)
                    }
                },
                modifier = Modifier
                    .offset(x = 90.dp)
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Backspace,
                    contentDescription = "Sil",
                    tint = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f)
                )
            }
        }
    }
}

