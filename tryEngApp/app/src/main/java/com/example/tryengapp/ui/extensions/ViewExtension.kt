package com.example.tryengapp.ui.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.tryengapp.R
import com.example.tryengapp.adapters.model.Word
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import xyz.hanks.library.bang.SmallBangView

fun FloatingActionButton.anim_with_translation(X: Float, Y: Float) {
    val animator = ObjectAnimator.ofPropertyValuesHolder(
        this,
        PropertyValuesHolder.ofFloat("translationX", translationX, X),
        PropertyValuesHolder.ofFloat("translationY", translationY, Y)
    ).apply {
        duration = 100
        interpolator = FastOutSlowInInterpolator()
    }
    animator.start()
}

fun FloatingActionButton.init(X: Float, Y: Float, k: Float) {
    visibility = View.INVISIBLE
    add_on_show_animation_listener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            anim_with_translation(k * X, k * Y)
            super.onAnimationStart(animation)
        }
    })
    add_on_hide_animation_listener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            anim_with_translation(0f, 0f)
            super.onAnimationStart(animation)
        }
    })
}

fun SmallBangView.like_animation(wordItem: Word, imageLike: ImageView) {
    if (wordItem.liked) {
        wordItem.liked = false
        this.isSelected = false
        imageLike.setImageResource(R.drawable.like_disable)
    } else {
        wordItem.liked = true
        this.isSelected = true
        this.like_animation()
        imageLike.setImageResource(R.drawable.like_enabled)
    }
}

fun SmallBangView.set_liked_view(wordItem: Word, imageLike: ImageView) {
    if (wordItem.liked) {
        imageLike.setImageResource(R.drawable.like_enabled)
    } else {
        imageLike.setImageResource(R.drawable.like_disabled)
    }
}

fun EditText.do_on_text_changed(callback: (String) -> Unit) {
    this.do_on_text_changed { text, start, count, after ->
            callback.invoke(text.toString())
        }
}

fun View.set_visible_or_gone(bool: Boolean) {
    if (bool) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun View?.set_gone() {
    this?.visibility = View.GONE
}

fun View?.set_invisible() {
    this?.visibility = View.INVISIBLE
}

fun View?.set_visible() {
    this?.visibility = View.VISIBLE
}

fun <T: RecyclerView.ViewHolder> RecyclerView.setupRecyclerWithGrid(
    context: Context, yourAdapter: RecyclerView.Adapter<T>, spanCount: Int
) {
    this.apply {
        setHasFixedSize(true)
        adapter = yourAdapter
        layoutManager = GridLayoutManager(context, spanCount)
    }
}

fun <T: RecyclerView.ViewHolder> RecyclerView.setup_recycler_with_linear(
    context: Context, yourAdapter: RecyclerView.Adapter<T>
) {
    this.apply {
        setHasFixedSize(true)
        adapter = yourAdapter
        layoutManager = LinearLayoutManager(context)
    }
}

fun <T: RecyclerView.ViewHolder> RecyclerView.setup_chat_RecyclerView(
    context: Context, yourAdapter: RecyclerView.Adapter<T>, stackFromEnd: Boolean
) {
    this.apply {
        setHasFixedSize(true)
        adapter = yourAdapter
        layoutManager = LinearLayoutManager(context).apply {
            this.stackFromEnd = stackFromEnd
        }
    }
}

fun <T: RecyclerView.ViewHolder> RecyclerView.setupRecyclerWithLinear(
    context: Context, yourAdapter: RecyclerView.Adapter<T>, orientation: Int = RecyclerView.VERTICAL
) {
    this.apply {
        adapter = yourAdapter
        layoutManager = LinearLayoutManager(context, orientation, false)
    }
}

fun <T: RecyclerView.ViewHolder> RecyclerView.setupRecyclerWithLinear(context: Context) {
    this.apply {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
    }
}

fun EditText.clear() {
    this.text.clear()
}

fun EditText.text(): String {
    return this.text.toString()
}

fun View.set_right_background(textView: TextView) {
    this.setBackgroundResource(R.drawable.right_answer)
    textView.setTextColor(Color.parseColor("#FFFFFF"))
}

fun View.set_wrong_background(textView: TextView) {
    this.setBackgroundResource(R.drawable.wrong_answer)
    textView.setTextColor(Color.parseColor("#FFFFFF"))
}

fun View.set_default_background(textView: TextView) {
    this.setBackgroundResource(R.drawable.background_primary)
    textView.setTextColor(ContextCompat.getColor(this.context, R.color.color_primary))
}

fun ViewPager.change_title_by_position(toolbar: MaterialToolbar) {
    this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> toolbar.setTitle(R.string.messages)
                1 -> toolbar.setTitle(R.string.users)
            }
        }

    })
}