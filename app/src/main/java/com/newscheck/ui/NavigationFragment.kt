package com.newscheck.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.newscheck.R
import com.newscheck.databinding.FragmentNavigationBinding
import com.newscheck.ui.allnews.AllNewsFragment
import com.newscheck.ui.profile.ProfileFragment

class NavigationFragment : Fragment() {

    private var binding: FragmentNavigationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavigationBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerNavigation, AllNewsFragment())
            .commit()
        binding?.bottomNavigationMenu
            ?.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.allNews -> {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.containerNavigation, AllNewsFragment())
                            .commit()
                        return@setOnItemSelectedListener true
                    }

                    R.id.profile -> {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.containerNavigation, ProfileFragment())
                            .commit()
                        return@setOnItemSelectedListener true

                    }

                    else -> return@setOnItemSelectedListener true
                }
            }
    }

}