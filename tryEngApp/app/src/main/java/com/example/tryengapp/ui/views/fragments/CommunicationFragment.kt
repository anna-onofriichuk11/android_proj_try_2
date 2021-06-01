package com.example.tryengapp.views.fragments

import android.view.View
import com.example.tryengapp.R
import com.nowiwr01.languator.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_communication.*

class CommunicationFragment: BaseFragment() {

    override val menuResultId: Nothing? = null
    override val contentResultId = R.layout.communic_fragm
    override val toolbar = R.id.toolbar

    override fun setViews(view: View) {
        showBottomBar()
        setupViewPager()
    }

    private fun setupViewPager() {

    }
}