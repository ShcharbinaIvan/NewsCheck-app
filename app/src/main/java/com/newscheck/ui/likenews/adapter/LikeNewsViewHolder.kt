package com.newscheck.ui.likenews.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.newscheck.databinding.ItemLikeNewsBinding
import com.newscheck.model.News

class LikeNewsViewHolder(private val binding: ItemLikeNewsBinding) : ViewHolder(binding.root) {

    val textView=binding.titleTextView

    fun bind(data: News) {
        binding.run {
            titleTextView.text = data.title
            sourceTextView.text = data.source
            Glide.with(imageNewsImageView.context)
                .load(data.image)
                .into(imageNewsImageView)
        }
    }

}