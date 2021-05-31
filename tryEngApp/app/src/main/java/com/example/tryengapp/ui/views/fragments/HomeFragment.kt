package com.example.tryengapp.views.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.google.android.material.snackbar.Snackbar
import com.example.tryengapp.R
import com.example.tryengapp.utils.Utils
import com.example.tryengapp.views.base.BaseFragment
import com.example.tryengapp.views.base.BaseViewStatus
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_change_profile_info_bottom_sheet.view.*
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by inject()

    override val menuResultId = R.menu.menu_home
    override val contentResultId = R.layout.home_fragm
    override val toolbar = R.id.toolbar


    override fun setViews(view: View) {
        showBottomBar()
    }


    override fun setObservers() {

    }

    override fun setListeners() {
        fab.setOnClickListener {
            if (mini1.isOrWillBeShown) mini1.hide() else mini1.show()
            if (mini2.isOrWillBeShown) mini2.hide() else mini2.show()
            if (mini3.isOrWillBeShown) mini3.hide() else mini3.show()
        }
        toolbar.setOnMenuItemClickListener { doMenuTask(it) }
        mini1.setOnClickListener {
            Snackbar.make(root, getString("This page not available now"), Snackbar.LENGTH_LONG).show()
        }
        mini2.setOnClickListener {
            navigate(HomeFragmentDirections.actionHomeToFavorite())
        }
        mini3.setOnClickListener {
            Snackbar.make(root, getString("This page not available now"), Snackbar.LENGTH_LONG).show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }


}