package com.example.tryengapp.views.fragments

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.example.tryengapp.R
import com.example.tryengapp.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_favorite_words.*
import org.koin.android.ext.android.inject

class FavoriteWordsFragment: BaseFragment() {

    private val sharedViewModel: SharedDictionaryViewModel by inject()

    override val menuResultId: Int? = null
    override val toolbar = R.id.toolbar
    override val contentResultId = R.layout.favwords_fragm


    override fun setViews(view: View) {
        sharedViewModel.loadFavoriteWords()
        sharedViewModel.loadPacks()
    }


    override fun setListeners() {
        toolbar.setNavigationOnClickListener { baseActivity.onBackPressed() }
    }
}