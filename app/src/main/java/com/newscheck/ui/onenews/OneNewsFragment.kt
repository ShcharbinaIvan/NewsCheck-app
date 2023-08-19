package com.newscheck.ui.onenews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.newscheck.databinding.FragmentOneNewsBinding
import com.newscheck.model.News
import dagger.hilt.android.AndroidEntryPoint

private const val NEWS_EXTRA = "newsExtra"

@AndroidEntryPoint
class OneNewsFragment : Fragment() {

    private var binding: FragmentOneNewsBinding? = null

    private val viewModel: OneNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneNewsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val news = arguments?.getParcelable<News>(NEWS_EXTRA)
        binding?.run {
            Glide.with(oneImageView.context)
                .load(news?.image)
                .into(oneImageView)
            oneNewsTextView.text = news?.description
            titleTextView.text = news?.title
            sourceTextView.text = news?.source
            dateTextView.text = news?.published_at?.substring(0, 10)
            favoriteImageView.setOnClickListener {
                if (news != null) {
                    viewModel.saveNews(news)
                    
                }
            }
            linkTextView.setOnClickListener {
                val address: Uri = Uri.parse(news?.url)
                val openLinkIntent = Intent(Intent.ACTION_VIEW, address)
                startActivity(openLinkIntent)
            }
        }
    }

    companion object {
        const val TAG = "OneNewsFragment"

        fun getFragment(news: News) =
            OneNewsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(NEWS_EXTRA, news)
                }
            }
    }
}