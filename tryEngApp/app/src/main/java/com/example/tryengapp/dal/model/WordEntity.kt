package com.example.tryengapp.dal.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tryengapp.api.model.Definition

@Entity
data class WordEntity(
    @PrimaryKey
    val id: Int,
    val word: String,
    val translation: String,
    @Embedded
    val wordInfo: Definition
)