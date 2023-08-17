package com.newscheck.ui.likenews.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.newscheck.databinding.ItemLikeNewsBinding
import com.newscheck.model.News

class LikeNewsAdapter : ListAdapter<News, LikeNewsViewHolder>(
    object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return false
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeNewsViewHolder {
        return LikeNewsViewHolder(
            ItemLikeNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LikeNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}