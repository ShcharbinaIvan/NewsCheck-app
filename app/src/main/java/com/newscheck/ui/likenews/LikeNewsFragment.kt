package com.newscheck.ui.likenews

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.newscheck.R
import com.newscheck.databinding.FragmentLikeNewsBinding
import com.newscheck.model.News
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikeNewsFragment : Fragment() {

    private var binding: FragmentLikeNewsBinding? = null

    private val viewModel: LikeNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikeNewsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun showDeleteDialog(news:News) {

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_news))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
//                viewModel.deleteNews(news)
            }
            .setNegativeButton(getString(R.string.no)) { _, _ ->

            }
            .show()
    }
}