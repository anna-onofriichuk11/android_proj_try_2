package com.example.tryengapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tryengapp.R
import com.example.tryengapp.adapters.model.Task
import com.example.tryengapp.ui.extensions.load_image
import kotlinx.android.synthetic.main.pack_and_task_item.view.*

class TaskAdapter(
    private val context: Context,
    private val task_list: List<Task>,
    private val on_click: (Task) -> Unit
): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(LayoutInflater.from(context).inflate(R.layout.dictlist_fragm, parent, false))

    override fun getItemCount() = task_list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(task_list[position], context)
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.pack_name
        private val subTitle: TextView = itemView.pack_size
        private val icon: ImageView = itemView.pack_icon

        fun bind(taskItem: Task, context: Context) {
            title.text = taskItem.title
            subTitle.text = taskItem.subtitle
            icon.load_image(context, taskItem.icon)

            itemView.setOnClickListener { on_click(taskItem) }
        }
    }
}