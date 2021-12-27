package com.example.pocpracticeapp.ui.datalist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imageloader.ImageLoader
import com.example.pocpracticeapp.databinding.ItemCharacterBinding
import com.example.pocpracticeapp.domain.model.ResultsItem
import com.google.gson.Gson

class CharactersAdapter(private val listener: CharacterItemListener, private val imageLoader: ImageLoader) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter(characterId: String)
    }

    private val items = ArrayList<ResultsItem>()

    fun setItems(items: List<ResultsItem>?) {
        this.items.clear()
        items?.let { this.items.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemCharacterBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener, imageLoader)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(items[position])


    class CharacterViewHolder(
        private val itemBinding: ItemCharacterBinding,
        private val listener: CharactersAdapter.CharacterItemListener,
        private val imageLoader : ImageLoader
    ) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {


        private lateinit var characterEntry: ResultsItem

        init {
            itemBinding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: ResultsItem) {
            this.characterEntry = item
            itemBinding.name.text = item.name
            itemBinding.speciesAndStatus.text = """${item.name} - ${item.price}"""
            if (item.imageUrls?.get(0) != null) {
                imageLoader.displayImage(item.imageUrls[0], itemBinding.image)
            }

//        Glide.with(itemBinding.root)
//            .load(item.imageUrls?.get(0))
//            .transform(CircleCrop())
//            .into(itemBinding.image)
        }

        override fun onClick(v: View?) {
            listener.onClickedCharacter(Gson().toJson(characterEntry))
        }
    }
}

