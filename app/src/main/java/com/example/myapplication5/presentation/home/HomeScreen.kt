package com.example.myapplication5.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myapplication5.navigation.Screen
import com.example.myapplication5.presentation.home.components.CustomSearchBar
import com.example.myapplication5.presentation.home.components.LazyRowComponent
import com.example.myapplication5.utils.ImageUtils.base64ToBitmap


@Composable
fun HomeScreen(navController: NavController) {

    val viewModel= hiltViewModel<HomeScreenViewModel>()

    val allContacts=viewModel.allContacts.collectAsStateWithLifecycle()
    val recentAdded = viewModel.recentAdded.collectAsStateWithLifecycle()

    var searchText = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                CustomSearchBar(searchText = searchText)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "Recent Added",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRowComponent( recentAdded.value,navController)
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "My Contacts (${allContacts.value.size})",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // items fonksiyonu için allContacts direkt veri sağlıyoruz
            items(allContacts.value.size) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable{ navController.navigate(Screen.Detail(id = allContacts.value[it].id)) }
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.background),
                        contentAlignment = Alignment.Center
                    ) {
                        // Eğer image boşsa, ismin ilk harflerini göster
                        if ((allContacts.value[it].image.isEmpty())) {
                            Text(
                                text = "${allContacts.value[it].name.first().uppercase()}${allContacts.value[it].surname.first().uppercase()}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            // Burada bir resim gösterme işlemi yapılabilir (dinamik resim yükleme)
                            val bitmap = remember(allContacts.value[it].image) {
                                base64ToBitmap(allContacts.value[it].image)
                            }
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "${allContacts.value[it].name} ${allContacts.value[it].surname}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = allContacts.value[it].email,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { navController.navigate(Screen.Dialer) },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(25.dp)
        ) {
            Icon(imageVector = Icons.Default.Phone, contentDescription = "Dialer Screen")
        }
    }
}

