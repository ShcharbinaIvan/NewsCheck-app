package com.newscheck.ui.allnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.newscheck.databinding.FragmentAllNewsBinding

import com.newscheck.ui.allnews.adapter.AllNewsAdapter
import com.newscheck.model.News
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
        viewModel.getNews()
        viewModel.news.observe(viewLifecycleOwner) {
            setListNews(it)
        }
    }

    private fun setListNews(list: ArrayList<News>) {
        binding?.newsRecyclerView?.run {
            if (adapter == null) {
                adapter = AllNewsAdapter()
                layoutManager = LinearLayoutManager(requireContext())
            }
            (adapter as? AllNewsAdapter)?.submitList(list)
            adapter?.notifyDataSetChanged()
        }
    }
}