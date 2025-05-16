package com.example.myapplication5.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myapplication5.presentation.detail.components.ContactRow


@Composable
fun DetailScreen(navController: NavController)
{
    val viewModel = hiltViewModel<DetailViewModel>()

    val contact = viewModel.contact.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF81D4FA), // Açık mavi
                        Color(0xFF0091EA)  // Koyu mavi
                    )
                )
            )
    ) {
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.2f), CircleShape),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.background
            )
        }
        // Profile, Name and Edit Button (outside the card)
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${contact.value.name.first().uppercase()}${contact.value.surname.first().uppercase()}",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Name
            Text(
                text = "${contact.value.name.replaceFirstChar { it.uppercase() }} ${contact.value.surname.replaceFirstChar { it.uppercase() }}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Edit Contact Button
            Row(
                modifier = Modifier
                    .clickable { /* Edit action */ }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Edit Contact",
                    color = Color.White
                )
            }
        }

        // Bottom Card with Contact Info (60% of screen height)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f) // Kart %60 ekran yüksekliğini kapsayacak
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                if(contact.value.phone.isNotEmpty()) {
                    ContactRow(
                        icon = Icons.Default.Phone,
                        title = "WhatsApp",
                        data = contact.value.phone,
                        isNumber = true
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if(contact.value.email.isNotEmpty()){
                    ContactRow(
                        icon = Icons.Default.Email,
                        title = "Email",
                        data = contact.value.email,
                        isNumber = false
                    )
                }
            }
        }
    }
}


