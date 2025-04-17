package com.example.myapplication5.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication5.model.Contact

class HomeScreenViewModel : ViewModel() {
    val recentAdded = MutableLiveData<List<Contact>>()
    val allContacts = MutableLiveData<List<Contact>>()

    init {
        val kisiListe = listOf(
            Contact(
                image = "",
                name = "İhsan",
                surname = "Arslan",
                email = "ihsan.arslan@gmail.com"
            ),
            Contact(
                image = "",
                name = "Ayşe",
                surname = "Yılmaz",
                email = "ayse.yilmaz@hotmail.com"
            ),
            Contact(
                image = "",
                name = "Mehmet",
                surname = "Kaya",
                email = "mehmetkaya@outlook.com"
            ),
            Contact(
                image = "",
                name = "Zeynep",
                surname = "Demir",
                email = "z.demir@company.com"
            ),
            Contact(
                image = "",
                name = "Can",
                surname = "Öztürk",
                email = "can.ozturk@gmail.com"
            ),
            Contact(
                image = "",
                name = "Elif",
                surname = "Çelik",
                email = "elif.celik@yahoo.com"
            ),
            Contact(
                image = "",
                name = "Burak",
                surname = "Şahin",
                email = "buraksahin@gmail.com"
            ),
            Contact(
                image = "",
                name = "Selin",
                surname = "Yıldız",
                email = "selinyildiz@company.com"
            ),
            Contact(
                image = "",
                name = "Emre",
                surname = "Aydın",
                email = "emre.aydin@outlook.com"
            ),
            Contact(
                image = "",
                name = "Deniz",
                surname = "Koç",
                email = "deniz.koc@gmail.com"
            )
        )

        recentAdded.value = kisiListe.take(3)

        allContacts.value = kisiListe
    }
}