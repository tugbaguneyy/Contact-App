package com.example.myapplication5.presentation.home

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myapplication5.navigation.Screen
import com.example.myapplication5.presentation.components.ProfileImage
import com.example.myapplication5.presentation.home.components.CustomSearchBar
import com.example.myapplication5.presentation.home.components.LazyRowComponent

@Composable
fun HomeScreen(navController: NavController) {

    val viewModel= hiltViewModel<HomeScreenViewModel>()

    val recentAdded = viewModel.recentAdded.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val filteredContacts = viewModel.filteredContacts.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                CustomSearchBar(
                    searchText = searchQuery,
                    onSearchTextChanged = {viewModel.onSearchQueryChanged(it)}
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "Son Eklenenler",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRowComponent( recentAdded.value,navController)
                Spacer(modifier = Modifier.height(24.dp))
                Row{
                    Text(
                        modifier = Modifier.padding(start = 15.dp),
                        text = "Kişilerim ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "(${filteredContacts.value.size})",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(filteredContacts.value.size) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable{ navController.navigate(Screen.Detail(id = filteredContacts.value[it].id)) }
                ) {
                    ProfileImage(
                        imageBase64 = filteredContacts.value[it].image,
                        name = filteredContacts.value[it].name,
                        surname = filteredContacts.value[it].surname,
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "${filteredContacts.value[it].name} ${filteredContacts.value[it].surname}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = filteredContacts.value[it].email,
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

