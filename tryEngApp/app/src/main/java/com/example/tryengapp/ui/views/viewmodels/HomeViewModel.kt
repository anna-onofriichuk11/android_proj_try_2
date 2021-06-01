package com.example.tryengapp.views.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.tryengapp.views.HomeViewStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.tryengapp.adapters.model.Word
import com.example.tryengapp.adapters.model.User
import com.example.tryengapp.model.PreferencesDataSource
import com.example.tryengapp.views.base.BaseViewModel

class HomeViewModel(
    private val preferencesDataSource: PreferencesDataSource
): BaseViewModel() {

    private lateinit var user_info_listener: ValueEventListener
    private lateinit var brainstorm_listener: ValueEventListener
    private lateinit var word_to_translation_listener: ValueEventListener
    private lateinit var translation_to_word_listener: ValueEventListener
    private lateinit var listening_listener: ValueEventListener

    private val userName = MutableLiveData<String>()
    fun getUserName() = userName

    private val userAvatar = MutableLiveData<String>()
    fun getUserAvatar() = userAvatar

    private val brainstorm_for_training = MutableLiveData<Int>()
    fun getBrainstormPack() = brainstorm_for_training

    private val word_to_translation_for_training = MutableLiveData<Int>()
    fun getWordToTranslationPack() = word_to_translation_for_training

    private val translation_to_word_for_training = MutableLiveData<Int>()
    fun getTranslationToWordPack() = translation_to_word_for_training

    private val listening_for_training = MutableLiveData<Int>()
    fun getListeningPack() = listening_for_training

    private val dbUser = FirebaseAuth.getInstance().currentUser!!
    private val dbReference = FirebaseDatabase.getInstance().getReference("Users").child(dbUser.uid)

    fun getUserInfo() {
        user_info_listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user: User = snapshot.getValue(User::class.java)!!
                saveUserInfo(user.username, user.imageURL)
            }

            override fun onCancelled(error: DatabaseError) {}
        }
        dbReference.addValueEventListener(user_info_listener)
    }

    fun getPackForTraining() {
        val type = object : GenericTypeIndicator<List<Word>>() {}
        brainstorm_listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                val words = snapshot.getValue(type) ?: mutableListOf()
                brainstorm_for_training.postValue(words.size)
            }
        }
        dbReference.child(BRAINSTORM_LINK).addValueEventListener(brainstorm_listener)

        word_to_translation_listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                val words = snapshot.getValue(type) ?: mutableListOf()
                word_to_translation_for_training.postValue(words.size)
            }
        }
        dbReference.child(WORD_TO_TRANSLATION_LINK).addValueEventListener(word_to_translation_for_training)

        translation_to_word_listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                val words = snapshot.getValue(type) ?: mutableListOf()
                translation_to_word_for_training.postValue(words.size)
            }
        }
        dbReference.child(TRANSLATION_TO_WORD_LINK).addValueEventListener(translation_to_word_listener)

        listening_listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                val words = snapshot.getValue(type) ?: mutableListOf()
                listening_for_training.postValue(words.size)
            }
        }
        dbReference.child(LISTENING_LINK).addValueEventListener(listening_listener)
    }

    private fun updateUserName(name: String) {
        val data = hashMapOf<String, Any>(
            "username" to name
        )
        dbReference.updateChildren(data)
    }

    private fun updateUserAvatar(avatar: String) {
        val data = hashMapOf<String, Any>(
            "imageURL" to avatar
        )
        dbReference.updateChildren(data)
    }

    fun saveUserAvatar(avatar: String) {
        userAvatar.postValue(avatar)
        updateUserAvatar(avatar)
    }

    fun saveUserName(name: String) {
        userName.postValue(name)
        updateUserName(name)
    }

    fun saveUserInfo(name: String, avatar: String) {
        setStatusBase(HomeViewStatus.loadUserInfo)
        userName.postValue(name)
        userAvatar.postValue(avatar)
        setStatusBase(HomeViewStatus.successLoadInfo)
    }

    override fun removeListeners() {
        dbReference.removeEventListener(user_info_listener)
        dbReference.child(BRAINSTORM_LINK).removeEventListener(brainstorm_listener)
        dbReference.child(WORD_TO_TRANSLATION_LINK).removeEventListener(word_to_translation_listener)
        dbReference.child(TRANSLATION_TO_WORD_LINK).removeEventListener(translation_to_word_listener)
        dbReference.child(LISTENING_LINK).removeEventListener(listening_listener)
    }
}