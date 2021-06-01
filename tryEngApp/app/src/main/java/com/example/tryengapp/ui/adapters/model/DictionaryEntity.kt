package com.example.tryengapp.adapters.model

data class DictionaryEntity(
    var name: String = "",
    val icon: String = "",
    var is_choosen: Boolean = false,
    val words: MutableList<Word> = mutableListOf(),
    var index: Int = 0
)