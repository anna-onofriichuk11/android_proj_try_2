package com.example.tryengapp.adapters

import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tryengapp.Const
import com.example.tryengapp.R
import com.example.tryengapp.adapters.model.Word
import com.example.tryengapp.extensions.*
import com.example.tryengapp.views.fragments.DictionaryFragment.Companion.CHANGE
import com.example.tryengapp.views.fragments.DictionaryFragment.Companion.NOT_FOUND_TAG
import com.example.tryengapp.views.fragments.DictionaryFragment.Companion.RESPONSE
import kotlinx.android.synthetic.main.word_item.view.*
import xyz.hanks.library.bang.SmallBangView
import java.util.*


class WordAdapter(
    private val context: Context,
    var words_list: List<Word>,
    private val on_like_click: (Word) -> Unit
): RecyclerView.Adapter<WordAdapter.WordViewHolder>(), Filterable {

    private var is_changed = 0
    var contact_list_full: List<Word> = ArrayList(words_list)
    private lateinit var text_to_speech: TextToSpeech

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder =
        WordViewHolder(LayoutInflater.from(context).inflate(R.layout.word_item, parent, false))

    override fun getItemCount() = words_list.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(words_list[position])
    }

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val word: TextView = itemView.textWord
        private val translation: TextView = itemView.textTranslation
        private val btnLike: SmallBangView = itemView.btnLike
        private val btnSound: ImageButton = itemView.btnSound
        private val imageLike: ImageView = itemView.imageLike
        private val example: TextView = itemView.textExample
        private val definition: TextView = itemView.textDefinition

        fun bind(wordItem: Word) {
            word.text = wordItem.word.capitalize()
            translation.text = wordItem.translation.capitalize()
            btnLike.setLikedView(wordItem, imageLike)

            btnLike.setOnClickListener {
                btnLike.likeAnimation(wordItem, imageLike)
                on_like_click.invoke(wordItem)
            }
            btnSound.setOnClickListener {
                text_to_speech.speak(wordItem.word)
            }
            itemView.setOnClickListener {
                if (!wordItem.word_info.definition.isNullOrEmpty()) {
                    definition.text = wordItem.word_info.definition.capitalize()
                    definition.setVisibleOrGone(!definition.isVisible)
                }
                if (!wordItem.word_info.examples.isNullOrEmpty()) {
                        example.text = wordItem.word_info.examples.first().capitalize()
                        example.setVisibleOrGone(!example.isVisible)
                    }
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        text_to_speech = TextToSpeech(context, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS) {
                text_to_speech.language = Locale.ENGLISH
            }
        }, Const.GOOGLE).apply {
            setSpeechRate(0.8f)
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        text_to_speech.shutdown()
    }

    class SearchFilter(
        private val contact_list_full:List<Word>,
        private val listener: (List<Word>) -> Unit
    ) : Filter() {

        private fun filterResults(constraint: CharSequence?): List<Word> {
            val searchList = arrayListOf<Word>()
            if (constraint == null || constraint.isEmpty()) {
                searchList.addAll(contact_list_full)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim()
                for (contact in contact_list_full) {
                    if (contact.word.toLowerCase(Locale.getDefault()).contains(filterPattern))  {
                        searchList.add(contact)
                    }
                }
            }
            return searchList
        }

        override fun performFiltering(charSequence: CharSequence?): FilterResults {
            return FilterResults().apply {
                values = filterResults(charSequence)
            }
        }

        override fun publishResults(p0: CharSequence?, results: FilterResults?) {
            listener(results?.values as List<Word>)
        }
    }

    override fun getFilter(): SearchFilter {
        return SearchFilter(contact_list_full) { resultList ->
            words_list.update(resultList)
            notifyDataSetChanged()
            is_changed = if (words_list.isEmpty()) {
                CHANGE
            } else {
                0
            }
            showNotFoundPage(is_changed)
        }
    }

    private fun showNotFoundPage(isChange: Int) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(Intent().apply {
            action = RESPONSE
            addCategory(Intent.CATEGORY_DEFAULT)
            putExtra(NOT_FOUND_TAG, isChange)
        })
    }
}