package com.newscheck.ui.allnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.newscheck.R
import com.newscheck.databinding.FragmentAllNewsBinding

import com.newscheck.ui.allnews.adapter.AllNewsAdapter
import com.newscheck.model.News
import com.newscheck.ui.onenews.OneNewsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllNewsFragment : Fragment() {

    private var binding: FragmentAllNewsBinding? = null

    private val viewModel: AllNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllNewsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews("")
        binding?.filterImageView?.setOnClickListener {
            showPopup(it)
        }
        viewModel.news.observe(viewLifecycleOwner) {
            setListNews(it)
        }
    }

    private fun setListNews(list: ArrayList<News>) {
        binding?.newsRecyclerView?.run {
            if (adapter == null) {
                adapter = AllNewsAdapter { news, _ ->
                    goToNews(news)
                }
                layoutManager = LinearLayoutManager(requireContext())
            }
            (adapter as? AllNewsAdapter)?.submitList(list)
            adapter?.notifyDataSetChanged()
        }
    }

    private fun goToNews(news: News) {
        viewModel.oneNews.postValue(news)
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerNavigation, OneNewsFragment.getFragment(news))
            .addToBackStack(OneNewsFragment.TAG)
            .commit()
    }

    private fun showPopup(v: View) {
        val popup = PopupMenu(requireContext(), v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_filter_news, popup.menu)
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.general -> {
                    viewModel.getNews(getString(R.string.general))
                }

                R.id.entertainment -> {
                    viewModel.getNews(getString(R.string.entertainment))
                }

                R.id.sports -> {
                    viewModel.getNews(getString(R.string.sports))
                }

            }
            return@setOnMenuItemClickListener true
        }
        popup.show()
    }
}