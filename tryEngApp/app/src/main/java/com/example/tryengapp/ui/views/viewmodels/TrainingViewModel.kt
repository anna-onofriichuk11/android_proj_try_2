package com.example.tryengapp.views.viewmodels

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.tryengapp.adapters.model.Word
import com.example.tryengapp.views.base.BaseViewModel

class TrainingViewModel: BaseViewModel() {

    private lateinit var wordsDictListener: ChildEventListener

    private val dbUser = FirebaseAuth.getInstance().currentUser!!
    private val dbReference = FirebaseDatabase.getInstance().getReference("Users").child(dbUser.uid)

    private val trainingDictionary = MutableLiveData<List<Word>>()
    fun getTrainingDict() = trainingDictionary

    fun loadTrainingDict(type: String) {
        wordsDictListener = object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {}
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {}
            override fun onChildRemoved(p0: DataSnapshot) {}

            override fun onChildChanged(snapshot: DataSnapshot, p1: String?) {
                loadWords(snapshot, type)
            }

            override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
                loadWords(snapshot, type)
            }
        }
        dbReference.addChildEventListener(wordsDictListener)
    }

    private fun loadWords(snapshot: DataSnapshot, type: String) {
        val brainstormWords = arrayListOf<Word>()
        for (data in snapshot.children) {
            if (snapshot.key == type) {
                val word = data.getValue(Word::class.java)!!
                if (word.word.isNotEmpty()) {
                    brainstormWords.add(word)
                }
            }
        }
        if (brainstormWords.isNotEmpty()) {
            trainingDictionary.postValue(brainstormWords)
        }
    }

    override fun removeListeners() {
        dbReference.removeEventListener(wordsDictListener)
    }
}