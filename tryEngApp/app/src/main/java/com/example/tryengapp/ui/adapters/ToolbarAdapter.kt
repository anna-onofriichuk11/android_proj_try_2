package com.example.tryengapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.tryengapp.R
import com.example.tryengapp.adapters.model.DictionaryEntity
import com.example.tryengapp.ui.extensions.load_image
import com.example.tryengapp.ui.extensions.setVisibleOrGone
import de.hdodenhof.circleimageview.CircleImageView

class ToolbarAdapter(
    private val dbContext: Context,
    private val dbDictionaryEntitries: List<DictionaryEntity>,
    private val on_arrow_click: () -> Unit
): ArrayAdapter<DictionaryEntity>(dbContext, R.layout.support_simple_spinner_dropdown_item, mDictionaryEntitries) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return get_view_state(position, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return get_drop_down_view_state(position, parent)
    }

    private fun get_drop_down_view_state(position: Int, parent: ViewGroup): View {
        val customView = LayoutInflater.from(dbContext).inflate(R.layout.support_simple_spinner_dropdown_item, parent, false)
        val packName: TextView = customView.findViewById(R.id.pack_name)
        val packIcon: ImageView = customView.findViewById(R.id.pack_icon)
        val packIsChosen: CircleImageView = customView.findViewById(R.id.chosenByUser)
        packName.text = dbDictionaryEntitries[position].name
        packIcon.load_image(dbContext, dbDictionaryEntitries[position].icon)
        packIsChosen.setVisibleOrGone(dbDictionaryEntitries[position].is_choosen)
        return customView
    }

    private fun get_view_state(position: Int, parent: ViewGroup): View {
        val customView = LayoutInflater.from(dbContext).inflate(R.layout.support_simple_spinner_dropdown_item, parent, false)
        val packName: TextView = customView.findViewById(R.id.pack_name)
        val packArrow: ImageButton = customView.findViewById(R.id.pack_arrow)
        packName.text = dbDictionaryEntitries[position].packName
        packArrow.setOnClickListener {
            on_arrow_click.invoke()
        }
        return customView
    }
}