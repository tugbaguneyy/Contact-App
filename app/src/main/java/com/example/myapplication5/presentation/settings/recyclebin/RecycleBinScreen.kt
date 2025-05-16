package com.example.myapplication5.presentation.settings.recyclebin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults



@Composable
fun RecycleBinScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<RecycleBinViewModel>()
    val deletedContacts = viewModel.deletedContacts.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Üst başlık ve geri tuşu
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.2f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Geri",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = "Çöp Kutusu",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Liste boşsa mesaj
        if (deletedContacts.value.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Çöp kutusunda kişi yok", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn {
                items(deletedContacts.value.size) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row {
                                Text(text = deletedContacts.value[it].name, style = MaterialTheme.typography.titleMedium)
                                Spacer(modifier = Modifier.width(4.dp))
                               Row {
                                   IconButton(
                                       onClick = {
                                           viewModel.restoreContact(deletedContacts.value[it].id)
                                       }
                                   ) {
                                       Icon(
                                           imageVector = Icons.Default.Refresh,
                                           contentDescription = "Restore",
                                           tint = MaterialTheme.colorScheme.surface
                                       )
                                   }

                                   Spacer(modifier = Modifier.width(8.dp))
                                   IconButton(
                                       onClick = {}
                                   ) {
                                       Icon(
                                           imageVector = Icons.Default.Delete,
                                           contentDescription = "Delete",
                                           tint = MaterialTheme.colorScheme.primary
                                       )
                                   }
                               }
                            }
                        }
                    }
                }
            }
        }
    }
}



