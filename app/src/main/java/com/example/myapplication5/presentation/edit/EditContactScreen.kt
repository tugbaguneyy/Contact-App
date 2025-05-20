package com.example.myapplication5.presentation.edit

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication5.data.local.ContactEntity
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication5.utils.ImageUtils.base64ToBitmap
import com.example.myapplication5.utils.ImageUtils.bitmapToBase64

@Composable
fun EditContactScreen(navController: NavController) {
    val viewModel = hiltViewModel<EditViewModel>()
    val form = viewModel.formState.value
    val contact = viewModel.contact.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    // İlk veri geldiğinde formu doldur
    LaunchedEffect(contact.id) {
        viewModel.onFieldChange(
            name = contact.name,
            surname = contact.surname,
            email = contact.email,
            phone = contact.phone,
            image = contact.image
        )
    }

    val imageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            val base64Image = bitmapToBase64(bitmap)
            viewModel.onFieldChange(image = base64Image)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 32.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            // 📷 Profil Fotoğrafı
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.tertiary)
                        .clickable { imageLauncher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (form.image.isEmpty()) {
                        Icon(
                            imageVector = Icons.Default.ImageSearch,
                            contentDescription = "Profile Image",
                            tint = MaterialTheme.colorScheme.background,
                            modifier = Modifier.size(60.dp)
                        )
                    } else {
                        val bitmap = remember(form.image) {
                            base64ToBitmap(form.image)
                        }
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // İsim Alanı
            OutlinedTextField(
                value = form.name,
                onValueChange = { viewModel.onFieldChange(name = it) },
                label = { Text("İsim") },
                isError = !form.isNameValid,
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Name") },
                shape = RoundedCornerShape(50),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            if (!form.isNameValid) {
                Text("İsim boş bırakılamaz", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Soyisim Alanı
            OutlinedTextField(
                value = form.surname,
                onValueChange = { viewModel.onFieldChange(surname = it) },
                label = { Text("Soyisim") },
                isError = !form.isSurnameValid,
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Surname") },
                shape = RoundedCornerShape(50),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            if (!form.isSurnameValid) {
                Text("Soyisim boş bırakılamaz", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Email Alanı
            OutlinedTextField(
                value = form.email,
                onValueChange = { viewModel.onFieldChange(email = it) },
                label = { Text("Email") },
                isError = !form.isEmailValid,
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(50),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            if (!form.isEmailValid) {
                Text("Geçerli bir e-posta girin", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Telefon Alanı
            OutlinedTextField(
                value = form.phone,
                onValueChange = { viewModel.onFieldChange(phone = it) },
                label = { Text("Telefon Numarası") },
                isError = !form.isPhoneValid,
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Phone") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                shape = RoundedCornerShape(50),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            if (!form.isPhoneValid) {
                Text("Geçerli bir telefon numarası girin", color = Color.Red, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Alt Butonlar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier.weight(1f)
            ) {
                Text("İptal", color = MaterialTheme.colorScheme.primary.copy(0.5f))
            }

            Spacer(modifier = Modifier.width(16.dp))

            TextButton(
                onClick = {
                    val updatedContact = ContactEntity(
                        id = contact.id,
                        name = form.name.trim(),
                        surname = form.surname.trim(),
                        email = form.email.trim(),
                        phone = form.phone.trim(),
                        image = form.image
                    )
                    viewModel.update(updatedContact)
                    navController.navigateUp()
                },
                enabled = form.isFormValid,
                modifier = Modifier.weight(1f)
            ) {
                Text("Tamam", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
