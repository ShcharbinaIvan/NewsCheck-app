package com.newscheck.ui.allnews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.newscheck.databinding.ItemAllNewsBinding
import com.newscheck.model.News


class AllNewsAdapter(
    private var onClick: (note: News, itemView: View) -> Unit
) : ListAdapter<News, AllNewsViewHolder>(
    object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return false
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllNewsViewHolder {
        return AllNewsViewHolder(
            ItemAllNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AllNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.textView.setOnClickListener {
            onClick(getItem(position), it)
        }
    }

}