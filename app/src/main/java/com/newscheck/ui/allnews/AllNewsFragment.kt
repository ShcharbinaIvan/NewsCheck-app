package com.newscheck.ui.allnews

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.newscheck.R
import com.newscheck.databinding.FragmentAllNewsBinding
import com.newscheck.ui.allnews.adapter.AllNewsAdapter
import com.newscheck.model.News
import com.newscheck.ui.onenews.OneNewsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            viewModel.getNetworkState()
                .collect {
                    if (it) {
                        viewModel.getNews("")
                        viewModel.news.observe(viewLifecycleOwner) { it1 ->
                            setListNews(it1)
                            binding?.filterImageView?.setOnClickListener { it2 ->
                                showPopup(it2)
                            }
                        }
                    } else {
                        binding?.noInternetImageView?.visibility = View.VISIBLE
                        Toast.makeText(context, getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
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