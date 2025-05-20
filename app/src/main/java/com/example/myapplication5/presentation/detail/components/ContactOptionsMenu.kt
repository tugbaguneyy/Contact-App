package com.example.myapplication5.presentation.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ContactOptionsMenu(
    onEditClick: () -> Unit,
    onDeleteConfirmed: () -> Unit,
    navController: NavController
) {
    var menuExpanded = remember { mutableStateOf(false) }
    var showDialog = remember { mutableStateOf(false) }

    Box {
        IconButton(
            onClick = { menuExpanded.value = true }
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        DropdownMenu(
            expanded = menuExpanded.value,
            onDismissRequest = { menuExpanded.value = false },
            offset = DpOffset(0.dp, 0.dp)
        ) {
            DropdownMenuItem(
                text = { Text("Düzenle") },
                onClick = {
                    menuExpanded.value = false
                    onEditClick()
                },
                leadingIcon = {
                    Icon(Icons.Default.Edit, contentDescription = null)
                }
            )
            DropdownMenuItem(
                text = { Text("Sil") },
                onClick = {
                    menuExpanded.value = false
                    showDialog.value = true
                },
                leadingIcon = {
                    Icon(Icons.Default.Delete, contentDescription = null)
                }
            )
        }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Emin misiniz?") },
                text = { Text("Bu kişiyi çöp kutusuna taşımak istediğinize emin misiniz?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog.value = false
                            onDeleteConfirmed()
                            navController.navigateUp()
                        }
                    ) {
                        Text("Tamam")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDialog.value = false }
                    ) {
                        Text("İptal")
                    }
                }
            )
        }
    }
}


