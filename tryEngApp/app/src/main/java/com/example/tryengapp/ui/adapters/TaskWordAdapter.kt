package com.example.tryengapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tryengapp.R
import com.example.tryengapp.adapters.model.TaskWord
import kotlinx.android.synthetic.main.training_item.view.*

class TaskWordAdapter(
    private val task_list: List<TaskWord>,
    private val on_answer_click: (itemView: View, taskWordItem: TaskWord) -> Unit
): RecyclerView.Adapter<TaskWordAdapter.TaskWordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskWordViewHolder =
        TaskWordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.training_item, parent, false))

    override fun getItemCount() = task_list.size

    override fun onBindViewHolder(holder: TaskWordViewHolder, position: Int) {
        holder.bind(task_list[position])
    }

    inner class TaskWordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val word: TextView = itemView.word

        fun bind(taskWordItem: TaskWord) {
            word.text = taskWordItem.word
            itemView.setDefaultBackground(word)

            itemView.setOnClickListener { on_answer_click.invoke(itemView, taskWordItem) }
        }
    }
}