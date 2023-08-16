package com.newscheck.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.newscheck.R
import com.newscheck.databinding.FragmentSignInBinding
import com.newscheck.ui.NavigationFragment
import com.newscheck.ui.signup.SignUpFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var binding: FragmentSignInBinding? = null

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.run {
            openAllNews = {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, NavigationFragment())
                    .commit()

            }
            error.observe(viewLifecycleOwner) {
                binding?.errorTextView?.run {
                    text = it
                    visibility = View.VISIBLE
                }
            }
        }
        binding?.run {
            goToSignUpTextView.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SignUpFragment())
                    .commit()
            }
            sigInButton.setOnClickListener {
                viewModel.login(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
    }

}