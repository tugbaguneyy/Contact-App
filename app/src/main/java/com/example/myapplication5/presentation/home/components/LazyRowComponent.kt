package com.example.myapplication5.presentation.home.components

import com.example.myapplication5.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication5.data.local.ContactEntity
import com.example.myapplication5.navigation.Screen


@Composable
fun LazyRowComponent(contactList: List<ContactEntity>,navController: NavController) {
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
        items(contactList.size) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .clickable {
                            navController.navigate(
                                Screen.Detail(
                                    name = contactList[it].name,
                                    surname = contactList[it].surname,
                                    email = contactList[it].email,
                                    image = contactList[it].image,
                                    phone = contactList[it].phone
                                )
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (contactList[it].image.isEmpty()) {
                        Text(
                            text = "${contactList[it].name.first()}${contactList[it].surname.first()}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.ic_launcher_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = contactList[it].name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(Modifier.width(16.dp))
        }
    }
}