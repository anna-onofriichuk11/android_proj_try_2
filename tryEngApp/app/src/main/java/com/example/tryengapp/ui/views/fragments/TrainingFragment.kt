package com.example.tryengapp.views.fragments

import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.google.android.material.snackbar.Snackbar
import com.example.tryengapp.R
import com.example.tryengapp.adapters.TaskWordAdapter
import com.example.tryengapp.adapters.model.Word
import com.example.tryengapp.adapters.model.TaskWord
import com.example.tryengapp.adapters.model.TrainingWordsTask
import com.example.tryengapp.extensions.*
import com.example.tryengapp.views.base.BaseFragment
import com.example.tryengapp.views.viewmodels.TrainingViewModel
import kotlinx.android.synthetic.main.fragment_training.*
import org.koin.android.ext.android.inject
import java.util.*


class TrainingFragment : BaseFragment() {

    private val viewModer: TrainingViewModel by inject()

    override val menuResultId: Int? = null
    override val toolbar = R.id.toolbar
    override val contentResultId = R.layout.training


    override fun setViews(view: View) {
        baseActivity.hideBottomBar()
        initialize()
    }

    override fun setObservers() {
        viewModer.getTrainingPack().observe(this, observerAllWordsList)
    }


    override fun setListeners() {
        btnNext.setOnClickListener { acceptUserAnswer() }
        toolbar.setNavigationOnClickListener { showExitDialog() }
    }


    override fun onBackPressed() {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitDialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}