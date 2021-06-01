package com.example.tryengapp.utils

import com.example.tryengapp.Const
import com.example.tryengapp.adapters.model.Task
import kotlin.random.Random

object Utils {

    private val titles = listOf(
        "Brainstorm Activity", "Word-translation Activity", "Translation-word Activity", "Listening Activity"
    )

    private val wordsQuantity = listOf(
        "0 words", "0 words", "0 words", "0 words"
    )

    private val icons = listOf(
        "brainstorm", "word_translation", "translation_word", "listening"
    )

    private val descriptions = listOf(
        "Попрактикуємось \n разом!",
        "Виберіть правильний \n переклад слова",
        "Запам'ятай переклад \n слова",
        "Нажми на іконку і вибери \n правильний переклад"
    )

    private val dataSourceLinks = listOf(
        Const.BRAINSTORM_LINK, Const.WORD_TO_TRANSLATION_LINK,
        Const.TRANSLATION_TO_WORD_LINK, Const.LISTENING_LINK
    )

    private fun initializeTaskList(): MutableList<Task> {
        val tasksList = mutableListOf<Task>()
        for (i in 0..3) {
            tasksList.add(
                Task(titles[i], wordsQuantity[i], icons[i], descriptions[i], dataSourceLinks[i])
            )
        }
        return tasksList
    }

    val tasksList = initializeTaskList()

    private val userAvatar = listOf(
        "cat_devil", "cat_music", "cat_space", "cat_selfie"    )

    fun getRandomUserAvatar() = userAvatar[Random.nextInt(userAvatar.size)]
}