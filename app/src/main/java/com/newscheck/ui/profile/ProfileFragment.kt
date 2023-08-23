package com.newscheck.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.newscheck.R
import com.newscheck.databinding.FragmentProfileBinding
import com.newscheck.ui.likenews.LikeNewsFragment
import com.newscheck.ui.signin.SignInFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEmail()
        binding?.run {
            favoriteImageView.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.containerNavigation, LikeNewsFragment())
                    .addToBackStack(LikeNewsFragment.TAG)
                    .commit()
            }
            logOutImageView.setOnClickListener {
                viewModel.logOut()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SignInFragment())
                    .commit()
            }
            viewModel.email.observe(viewLifecycleOwner) {
                personTextView.text = it
            }
        }
    }
}