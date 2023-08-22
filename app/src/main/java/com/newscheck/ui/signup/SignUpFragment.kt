package com.newscheck.ui.signup

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.newscheck.R
import com.newscheck.databinding.FragmentSignUpBinding
import com.newscheck.ui.NavigationFragment
import com.newscheck.ui.signin.SignInFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var binding: FragmentSignUpBinding? = null

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailFocusListener()
        passwordFocusListener()
        confirmPasswordFocusListener()
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
            goToSignInTextView.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SignInFragment())
                    .commit()
            }
            sigUpButton.setOnClickListener {
                if (validate()) {
                    viewModel.signUp(
                        emailEditText.text.toString(),
                        passwordEditText.text.toString()
                    )
                }
            }
        }
    }

    private fun validate(): Boolean {
        binding?.run {
            emailTextInputLayout.helperText = validateEmail()
            passwordTextInputLayout.helperText = validatePassword()
            confirmpasswordTextInputLayout.helperText = validateConfirmPassword()
        }
        val validEmail = binding?.emailTextInputLayout?.helperText
        val validPassword = binding?.passwordTextInputLayout?.helperText
        val validConfirmPassword = binding?.passwordTextInputLayout?.helperText
        if (validEmail == null && validPassword == null && validConfirmPassword == null) {
            return true
        }
        return false
    }

    private fun emailFocusListener() {
        binding?.emailEditText?.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding?.emailTextInputLayout?.helperText = validateEmail()
            }
        }
    }

    private fun validateEmail(): String? {
        val emailText = binding?.emailEditText?.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return getString(R.string.invalid_email_address)
        }
        return null
    }

    private fun passwordFocusListener() {
        binding?.passwordEditText?.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding?.passwordTextInputLayout?.helperText = validatePassword()
            }
        }
    }

    private fun validatePassword(): String? {
        val passwordText = binding?.passwordEditText?.text.toString()
        if (passwordText.length < 8) {
            return getString(R.string.must_be_8_to_16_characters)
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())) {
            return getString(R.string.must_contain_1_uppercase_character)
        }
        return null
    }

    private fun confirmPasswordFocusListener() {
        binding?.confirmpasswordEditText?.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding?.confirmpasswordTextInputLayout?.helperText = validateConfirmPassword()
            }
        }
    }

    private fun validateConfirmPassword(): String? {
        val confirmPasswordText = binding?.confirmpasswordEditText?.text.toString()
        val passwordText = binding?.passwordEditText?.text.toString()
        if (confirmPasswordText.length < 8) {
            return getString(R.string.must_be_8_to_16_characters)
        }
        if (confirmPasswordText != passwordText) {
            return getString(R.string.password_mismatch)
        }
        return null
    }

}