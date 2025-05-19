package com.example.myapplication5.presentation.add

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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import java.io.ByteArrayOutputStream
import android.util.Base64
import androidx.compose.foundation.clickable
import com.example.myapplication5.utils.ImageUtils.bitmapToBase64


@Composable
fun AddContactScreen(navController: NavController) {
    val viewModel = hiltViewModel<AddViewModel>()
    val form = viewModel.formState.value
    val context = LocalContext.current

    // Image picker launcher
    val imageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            val base64Image = bitmapToBase64(bitmap)
            viewModel.onFieldChange(image = base64Image)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp),
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 👇 Resim alanı ve seçim butonu
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .clickable { imageLauncher.launch("image/*") }, // 👈 Image seçici tetikleniyor
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "Profile Image",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(onClick = { imageLauncher.launch("image/*") }) {
                        Text("Resim Yükle")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // 🔽 Aşağıdaki alanlar değişmedi
                    OutlinedTextField(
                        value = form.name,
                        onValueChange = { viewModel.onFieldChange(name = it) },
                        label = { Text("İsim") },
                        isError = !form.isNameValid,
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (!form.isNameValid) {
                        Text("İsim boş bırakılamaz", color = Color.Red, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = form.surname,
                        onValueChange = { viewModel.onFieldChange(surname = it) },
                        label = { Text("Soyisim") },
                        isError = !form.isSurnameValid,
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Surname") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (!form.isSurnameValid) {
                        Text("Soyisim boş bırakılamaz", color = Color.Red, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = form.email,
                        onValueChange = { viewModel.onFieldChange(email = it) },
                        label = { Text("Email") },
                        isError = !form.isEmailValid,
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (!form.isEmailValid) {
                        Text("Geçerli bir e-posta girin", color = Color.Red, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = form.phone,
                        onValueChange = { viewModel.onFieldChange(phone = it) },
                        label = { Text("Telefon Numarası") },
                        isError = !form.isPhoneValid,
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Phone") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (!form.isPhoneValid) {
                        Text("Geçerli bir telefon numarası girin", color = Color.Red, fontSize = 12.sp)
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "İptal",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            TextButton(
                onClick = {
                    val contact = ContactEntity(
                        name = form.name.trim(),
                        surname = form.surname.trim(),
                        email = if (form.email.trim().isNotEmpty()) form.email.trim() else "",
                        phone = if (form.phone.trim().isNotEmpty()) form.phone.trim() else "",
                        image = form.image // 👈 Base64 resim stringi burada
                    )
                    viewModel.insert(contact)
                    navController.navigateUp()
                },
                enabled = form.isFormValid,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text="Tamam",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


