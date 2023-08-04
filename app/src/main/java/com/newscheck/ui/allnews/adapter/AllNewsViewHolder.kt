package com.newscheck.ui.allnews.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.newscheck.databinding.ItemAllNewsBinding
import com.newscheck.model.News


class AllNewsViewHolder(private val binding: ItemAllNewsBinding) : ViewHolder(binding.root) {

    fun bind(data: News) {
        binding.run {
            textNews.text = data.description
            Glide.with(imageNews.context)
                .load(data.image)
                .into(imageNews)
        }
    }
}