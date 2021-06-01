package com.example.tryengapp.views.fragments

import android.view.View
import com.example.tryengapp.R
import com.example.tryengapp.views.base.BaseFragment

class ChatsFragment: BaseFragment() {

    override val menuResultId: Int? = null
    override val contentResultId = R.layout.chat
    override val toolbar = null

    override fun setViews(view: View) {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
    }
}