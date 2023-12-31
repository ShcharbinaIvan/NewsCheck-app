package com.newscheck.ui.likenews

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.newscheck.R
import com.newscheck.databinding.FragmentLikeNewsBinding
import com.newscheck.model.News
import com.newscheck.ui.likenews.adapter.LikeNewsAdapter
import com.newscheck.ui.onelikenews.OneLikeNewsFragment

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
        viewModel.getEmail()
        viewModel.newsList.observe(viewLifecycleOwner) {
            setListNews(it)
        }
        viewModel.getNews()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setListNews(list: ArrayList<News>) {
        binding?.newsRecyclerView?.run {
            if (adapter == null) {
                adapter = LikeNewsAdapter { news, _ ->
                    goToLikeNews(news)
                }
                layoutManager = LinearLayoutManager(requireContext())
            }
            (adapter as? LikeNewsAdapter)?.submitList(list)
            adapter?.notifyDataSetChanged()
        }
    }


    private fun goToLikeNews(news: News) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerNavigation, OneLikeNewsFragment.getLikeFragment(news))
            .addToBackStack(OneLikeNewsFragment.TAG)
            .commit()
    }

    companion object {
        const val TAG = "LikeNewsFragment"
    }
}