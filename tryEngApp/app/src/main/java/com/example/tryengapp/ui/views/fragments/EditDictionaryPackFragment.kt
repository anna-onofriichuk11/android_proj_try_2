package com.example.tryengapp.views.fragments

import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.google.android.material.snackbar.Snackbar
import com.example.tryengapp.R
import com.example.tryengapp.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_edit_dictionary_pack.*
import kotlinx.android.synthetic.main.fragment_edit_dictionary_pack.root
import kotlinx.android.synthetic.main.fragment_edit_dictionary_pack.wordsRecycler
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class EditDictionaryPackFragment : BaseFragment() {

    private val sharedViewModel: SharedDictionaryViewModel by inject()

    override val menuResultId = R.menu.menu_edit_pack
    override val toolbar = R.id.toolbar
    override val contentResultId = R.layout.edit_dictlist_fragm



    override fun setViews(view: View) {
        sharedViewModel.loadPacks()
        sharedViewModel.loadFavoriteWords()
    }


    override fun setListeners() {
        btnRenamePack.setOnClickListener { showBottomSheet() }
        btnDeletePack.setOnClickListener {
            Snackbar.make(root, getString(R.string.future_text), Snackbar.LENGTH_LONG).show()
        }
    }


}