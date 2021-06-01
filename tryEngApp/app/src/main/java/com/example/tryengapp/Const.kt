package com.example.tryengapp

object Const {

    const val BASE_URL = "https://wordsapiv1.p.rapidapi.com/words/"
    const val WORD_HOST_NAME = "x-rapidapi-host"
    const val WORD_HOST_VALUE = "wordsapiv1.p.rapidapi.com"
    const val WORD_KEY_NAME = "x-rapidapi-key"
    const val WORD_KEY_VALUE = BuildConfig.WORDS_API_KEY

    const val TRANSLATE_SITE = "https://translate.yandex.net/api/v1.5/tr.json/"
    const val TRANSLATE_API_KEY = BuildConfig.TRANSLATION_API_KEY

    const val GOOGLE_DROID = "com.google.android.tts"


    const val BRAINSTORM_LINK = "brainstormWords"
    const val WORD_TO_TRANSLATION_LINK  = "wordToTranslationWords"
    const val TRANSLATION_TO_WORD_LINK = "translationToWordWords"
    const val LISTENING_LINK = "listeningWords"
}