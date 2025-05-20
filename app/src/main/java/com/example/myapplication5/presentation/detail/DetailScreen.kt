package com.example.myapplication5.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myapplication5.presentation.detail.components.ContactOptionsMenu
import com.example.myapplication5.presentation.detail.components.ContactRow
import com.example.myapplication5.utils.ImageUtils.base64ToBitmap


@Composable
fun DetailScreen(navController: NavController) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val contact by viewModel.contact.collectAsStateWithLifecycle()

    val bitmap = remember(contact.image) {
        if (contact.image.isNotEmpty()) base64ToBitmap(contact.image) else null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // 🔙 Üst Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Geri",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            ContactOptionsMenu(
                onEditClick = { /* Düzenleme işlemi */ },
                onDeleteConfirmed = { viewModel.softDeleteContact() },
                navController = navController
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 📷 Profil Fotoğrafı veya Baş Harfler
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    shape = CircleShape
                )
                .align(Alignment.CenterHorizontally),
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
                    text = "${contact.name.firstOrNull()?.uppercase() ?: ""}${contact.surname.firstOrNull()?.uppercase() ?: ""}",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🧾 İsim
        Text(
            text = "${contact.name.replaceFirstChar { it.uppercase() }} ${contact.surname.replaceFirstChar { it.uppercase() }}",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 📱 Telefon
        if (contact.phone.isNotEmpty()) {
            ContactRow(
                icon = Icons.Default.Phone,
                title = "Telefon",
                data = contact.phone,
                isNumber = true
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // 📧 Email
        if (contact.email.isNotEmpty()) {
            ContactRow(
                icon = Icons.Default.Email,
                title = "Email",
                data = contact.email,
                isNumber = false
            )
        }
    }
}



