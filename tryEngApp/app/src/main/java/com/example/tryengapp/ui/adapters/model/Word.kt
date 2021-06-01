package com.example.tryengapp.adapters.model

import com.example.tryengapp.api.model.Definition

data class Word(
    val word: String = "",
    val translation: String = "",
    val word_info: Definition = Definition(),
    var is_liked: Boolean = false
)