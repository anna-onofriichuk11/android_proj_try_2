package com.example.tryengapp.ui.custom.behaviors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton



class HideFAButtonBehavior() : CoordinatorLayout.Behavior<FloatingActionButton>() {
    constructor(context: Context, attrs: AttributeSet) : this()

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: FloatingActionButton,
        dependency: View
    ): Boolean {
        dependency as SimpleFAB

        return if (dependency.isChecked && dependency.isOrWillBeHidden) {
            child.hide()
            true
        } else if (dependency.isChecked) {
            child.show()
            true
        } else {
            false
        }
    }
}