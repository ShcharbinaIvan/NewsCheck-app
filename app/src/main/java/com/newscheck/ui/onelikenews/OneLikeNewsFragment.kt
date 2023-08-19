package com.newscheck.ui.onelikenews

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.newscheck.R
import com.newscheck.databinding.FragmentOneLikeNewsBinding
import com.newscheck.model.News
import com.newscheck.ui.likenews.LikeNewsFragment
import dagger.hilt.android.AndroidEntryPoint

private const val NEWS_EXTRA_ONE = "newsExtraOne"

@Suppress("DEPRECATION")
@AndroidEntryPoint
class OneLikeNewsFragment : Fragment() {

    private var binding: FragmentOneLikeNewsBinding? = null

    private val viewModel: OneLikeNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneLikeNewsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val news = arguments?.getParcelable<News>(NEWS_EXTRA_ONE)
        binding?.run {
            Glide.with(oneLikeImageView.context)
                .load(news?.image)
                .into(oneLikeImageView)
            oneNewsTextView.text = news?.description
            titleLikeTextView.text = news?.title
            sourceTextView.text = news?.source
            dateTextView.text = news?.published_at?.substring(0, 10)
            deleteImageView.setOnClickListener {
                    news?.let { it1 -> showDeleteDialog(it1) }
                }
            linkTextView.setOnClickListener {
                val address: Uri = Uri.parse(news?.url)
                val openLinkIntent = Intent(Intent.ACTION_VIEW, address)
                startActivity(openLinkIntent)
            }
        }
    }

        private fun showDeleteDialog(news: News) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_news))
            .setNegativeButton(getString(R.string.no)) { _, _ ->

            }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                viewModel.deleteNews(news)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.containerNavigation, LikeNewsFragment())
                    .commit()
            }
            .show()
    }

    companion object {
        const val TAG = "OneLikeNewsFragment"

        fun getLikeFragment(news: News) =
            OneLikeNewsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(NEWS_EXTRA_ONE, news)
                }
            }
    }
}