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
                        toFragment(AllNewsFragment())
                        return@setOnItemSelectedListener true
                    }

                    R.id.profile -> {
                        toFragment(ProfileFragment())
                        return@setOnItemSelectedListener true

                    }

                    else -> return@setOnItemSelectedListener true
                }
            }
    }

    private fun toFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerNavigation, fragment)
            .commit()
    }

}