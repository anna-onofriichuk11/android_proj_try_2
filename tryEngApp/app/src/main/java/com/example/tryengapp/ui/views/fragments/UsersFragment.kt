package com.example.tryengapp.views.fragments

import android.view.View
import androidx.lifecycle.Observer
import com.google.firebase.database.*
import com.example.tryengapp.R
import com.example.tryengapp.views.base.BaseFragment
import com.example.tryengapp.views.base.BaseViewStatus
import com.example.tryengapp.views.fragments.CommunicationFragment
import com.example.tryengapp.views.UserViewStatus
import com.example.tryengapp.views.viewmodels.UsersViewModel
import kotlinx.android.synthetic.main.fragment_users.*
import org.koin.android.ext.android.inject

class UsersFragment: BaseFragment() {

    private val viewModel: UsersViewModel by inject()

    override val menuResultId: Int? = null
    override val contentResultId = R.layout.user_fragm
    override val toolbar: Int? = null


    override fun setViews(view: View) {

    }



    override fun setObservers() {

    }


}