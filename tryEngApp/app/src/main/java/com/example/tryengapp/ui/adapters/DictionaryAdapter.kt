package com.example.tryengapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tryengapp.R
import com.example.tryengapp.ui.adapters.model.DictionaryEntity
import com.example.tryengapp.ui.extensions.load_image
import kotlinx.android.synthetic.main.pack_and_task_item.view.*

class DictionaryAdapter(
    private val context: Context,
    private val dict_List: List<DictionaryEntity>,
    private val on_click: (DictionaryEntity) -> Unit
) : RecyclerView.Adapter<PackAdapter.PackViewHolder>() {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DictionaryViewHolder(LayoutInflater.from(context).inflate(R.layout.dictlist_fragm, parent, false))

    override fun GetItemCount() = dict_List.size

    override fun OnBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        holder.bind(dict_List[position], context)
    }

    inner class DictionaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val dict_name: TextView = itemView.pack_name
        private val dict_size: TextView = itemView.pack_size
        private val dict_icon: ImageView = itemView.pack_icon

        fun bind(dict: DictionaryEntity, context: Context) {
            dict_name.text = dict.name
            dict_size.text = String.format("%s words", dict.words.size)
            dict_icon.load_image(context, dict.icon)

            itemView.setOnClickListener { on_click(dict) }
        }
    }

}