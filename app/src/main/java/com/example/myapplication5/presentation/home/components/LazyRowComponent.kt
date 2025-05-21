package com.example.myapplication5.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication5.data.local.ContactEntity
import com.example.myapplication5.navigation.Screen
import com.example.myapplication5.presentation.components.ProfileImage


@Composable
fun LazyRowComponent(contactList: List<ContactEntity>, navController: NavController) {
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
        items(contactList.size) {
            val contact = contactList[it]
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ProfileImage(
                    imageBase64 = contact.image,
                    name = contact.name,
                    surname = contact.surname,
                    modifier = Modifier.size(70.dp),
                    backgroundColor = Color.LightGray,
                    onClick = { navController.navigate(Screen.Detail(id = contact.id)) }
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = contact.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(Modifier.width(16.dp))
        }
    }
}
