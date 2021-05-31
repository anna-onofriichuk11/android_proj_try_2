package com.example.tryengapp.views.viewmodels

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.tryengapp.adapters.model.User
import com.example.tryengapp.views.base.BaseViewModel
import com.example.tryengapp.views.UserViewStatus
import timber.log.Timber

class UsersViewModel : BaseViewModel() {

    private lateinit var UserListener: ValueEventListener

    private var dbReference = FirebaseDatabase.getInstance().getReference("Users")

    private val usersList = MutableLiveData<List<User>>()
    fun getUsersList() = usersList

    fun loadUsersList() {
        setStatusBase(UserViewStatus.loading)
        userListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                setStatusBase(UserViewStatus.loading)
                val users = mutableListOf<User>()
                for (data in snapshot.children) {
                    val user = data.getValue(User::class.java)!!
                    users.add(user)
                }
                Timber.tag("Users").db("user list: $users")
                usersList.postValue(users)
                setStatusBase(UserViewStatus.successLoading)
            }
        }
        dbReference.addValueEventListener(userListener)
    }

    override fun removeListeners() {
        dbReference.removeEventListener(userListener)
    }
}