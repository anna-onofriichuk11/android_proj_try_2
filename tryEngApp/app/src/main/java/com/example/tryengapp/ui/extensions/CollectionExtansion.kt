package com.example.tryengapp.ui.extensions

import androidx.recyclerview.widget.RecyclerView

fun <T> List<T>.update(newList: List<T>) {
    (this as MutableList<T>).clear()
    this.addAll(newList)
}

fun <T, V: RecyclerView.ViewHolder> List<T>.updateRecyclerView(
    newList: List<T>,
    adapter: RecyclerView.Adapter<V>
) {
    (this as MutableList<T>).clear()
    this.addAll(newList)
    adapter.notifyDataSetChanged()
}