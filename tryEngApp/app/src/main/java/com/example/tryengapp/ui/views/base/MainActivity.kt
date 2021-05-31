package com.example.tryengapp.languator.views.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.tryengapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {

    var currentNavigateController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_fragment)

        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            setupBottomNavigation()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navGraphIds = listOf(R.navigation.home_navigation, R.navigation.dictionary_navigation, R.navigation.chat_navigation)
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.my_nav_host_fragment,
            intent = intent
        )
        currentNavigateController = controller
    }

    fun showBottomBar() = bottom_navigation.setVisible()

    fun hideBottomBar() = bottom_navigation.setGone()

    fun hideKeyboard() {
        val methodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = currentFocus
        if (view == null) {
            view = View(this)
        }
        methodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        view.clearFocus()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavigateController?.value?.navigateUp() ?: false
    }
}
