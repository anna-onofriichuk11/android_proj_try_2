package com.example.tryengapp.views.fragments

import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.example.tryengapp.R
import com.example.tryengapp.views.base.BaseFragment
import com.example.tryengapp.views.base.BaseViewStatus
import com.example.tryengapp.views.DictionaryViewStatus
import kotlinx.android.synthetic.main.fragment_dictionary_packs.*
import org.koin.android.ext.android.inject

class DictionaryPacksFragment: BaseFragment() {

    private val sharedViewModel: SharedDictionaryViewModel by inject()

    override val menuResultId: Int? = null
    override val toolbar = R.id.toolbar
    override val contentResultId = R.layout.dictlist_fragm



    override fun setViews(view: View) {
        setupRecyclerView()
        sharedViewModel.loadPacks()
    }

    override fun setListeners() {
        layoutAddNewPack.setOnClickListener {
            showAddPackBottomSheet()
        }
    }

    override fun setObservers() {
        sharedViewModel.getUserPacks()
        sharedViewModel.getStatusBase()
    }


}