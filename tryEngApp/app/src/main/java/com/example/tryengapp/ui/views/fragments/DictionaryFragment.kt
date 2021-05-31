package com.example.tryengapp.views.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
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
import kotlinx.android.synthetic.main.fragment_dictionary.*
import kotlinx.android.synthetic.main.fragment_dictionary.toolbar
import kotlinx.android.synthetic.main.fragment_dictionary.wordsRecycler
import kotlinx.android.synthetic.main.layout_not_found.*
import org.koin.android.ext.android.inject

class DictionaryFragment : BaseFragment() {


    override val menuResultId = R.menu.menu_dictionary
    override val contentResultId = R.layout.dict_fragm
    override val toolbar = R.id.toolbar




    override fun setObservers() {
        sharedViewModel.loadWord().observe(this, observerNewWord)
        sharedViewModel.getUserPacks().observe(this, observerPacks)
        sharedViewModel.getFavoriteWords().observe(this, observerFavorite)
    }

    private fun updateUI(packs: List<DictionaryEntity>) {
        allUserPacks.update(packs)
        currentWordsPack = allUserPacks[currentPackPosition].copy()
        setupSpinner()
        setupRecyclerView()
    }
    

    private fun onArrowClick() {
        customSpinner.performClick()
    }

    override fun setListeners() {
        buttonNotFound.setOnClickListener {
            sharedViewModel.loadWordEntity(searchEditText.text())
        }
        toolbar.setOnMenuItemClickListener { doMenuTask(it) }
    }

    private fun doMenuTask(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.editPack -> {
                navigate(DictionaryFragmentDirections.actionDictionaryToEditPack(
                    currentWordsPack.packName, currentWordsPack.packIcon, currentWordsPack.spinnerIndex
                ))
                true
            }
            R.id.addForTraining -> {
                showChooseTrainingBottomSheet()
                true
            }
            else -> false
        }
    }


}