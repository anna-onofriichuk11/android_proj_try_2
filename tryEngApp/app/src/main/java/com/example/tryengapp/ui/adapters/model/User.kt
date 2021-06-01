package com.example.tryengapp.adapters.model

data class User(
    val id: String = "",
    val username: String = "",
    val imageURL: String = "",
    val words: List<DictionaryEntity> = mutableListOf(),
    val status: String = ""
)