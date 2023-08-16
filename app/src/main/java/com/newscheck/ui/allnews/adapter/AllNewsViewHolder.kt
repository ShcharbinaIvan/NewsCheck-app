package com.newscheck.ui.allnews.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.newscheck.databinding.ItemAllNewsBinding
import com.newscheck.model.News


class AllNewsViewHolder(private val binding: ItemAllNewsBinding) : ViewHolder(binding.root) {

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