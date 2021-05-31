package com.example.tryengapp.ui.custom.behaviors

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.graphics.ColorUtils
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.example.tryengapp.R
import kotlin.math.max
import kotlin.math.min
import androidx.core.math.MathUtils

class ScrollBehavior() : AppBarLayout.Behavior() {
    constructor(context: Context, attrs: AttributeSet) : this()

    private var dy: Int = 0
    private var bottom: Int = 0
    private var scale: Float = 0f
    private var target_height: Int = 0
    private var parent_height: Int = 0
    private var isStopped: Boolean = false

    private lateinit var target_view: View
    private lateinit var collapsing_view: CollapsingToolbarLayout

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        abl: AppBarLayout,
        layoutDirection: Int
    ): Boolean {
        val superLayout = super.onLayoutChild(parent, abl, layoutDirection)
        if (!::target_view.isInitialized) {
            initialize(abl)
        }
        return superLayout
    }

    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean {
        isStopped = false
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        appbl: AppBarLayout,
        target: View,
        type: Int
    ) {
        isStopped = true
        restore(appbl)
        super.onStopNestedScroll(coordinatorLayout, appbl, target, type)
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        val appblBottom = child.bottom
        if ((dy < 0 && ablBottom >= parent_height) || (dy > 0 && ablBottom > parent_height)) {
            scale(child, dy)
        } else {
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        }
    }

    private fun initialize(abl: AppBarLayout) {
        target_view = abl.findViewById(R.id.userAvatar)
        collapsing_view = abl.getChildAt(0) as CollapsingToolbarLayout
        parent_height = abl.height
        target_height = target_view.height
    }

    private fun restore(abl: AppBarLayout) {
        if (dy > 0) {
            dy = 0
            val anim = ValueAnimator.ofFloat(scale, 1f)
            anim.addUpdateListener {
                val value = it.animatedValue as Float
                target_view.apply {
                    scaleX = value
                    scaleY = value
                }
                val bottomValue = (bottom - (bottom - parent_height) * it.animatedFraction).toInt()
                abl.bottom = bottomValue
                collapsing_view.bottom = bottomValue

                animateTitleColor(it.animatedFraction)
            }
            anim.start()
        }
    }

    private fun scale(abl: AppBarLayout, dY: Int) {
        if (isStopped) {
            return
        }
        dy += -dY
        dy = min(dy, target_height)

        scale = max(1f, 1f + dy.toFloat() / target_height)
        target_view.apply {
            scaleX = scale
            scaleY = scale
        }

        bottom = parent_height + (target_height / 2 * (scale - 1)).toInt()
        abl.bottom = bottom
        collapsing_view.bottom = bottom

        animateTitleColor(1f - dy.toFloat() / target_height)
    }

    private fun animateTitleColor(fraction: Float) {
        val alpha = MathUtils.clamp(255 * fraction, 0f, 255f).toInt()
        val color = ColorUtils.setAlphaComponent(Color.WHITE, alpha)
        collapsing_view.setExpandedTitleColor(color)
    }
}