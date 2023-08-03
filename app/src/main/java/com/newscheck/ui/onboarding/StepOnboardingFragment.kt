package com.newscheck.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.newscheck.R
import com.newscheck.databinding.FragmentStepOnboardingBinding

const val STEP_1 = 1
const val STEP_2 = 2
const val STEP_3 = 3
private const val STEP_EXTRA = "stepExtra"

class StepOnboardingFragment : Fragment() {

    private var binding: FragmentStepOnboardingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStepOnboardingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (arguments?.getInt(STEP_EXTRA) ?: STEP_1) {
            STEP_1 -> {
                binding?.onboardtextTextView?.setText(R.string.first_onboard_text)
                binding?.onboardAnimationView?.setAnimation(R.raw.first_onboard_image)
            }

            STEP_2 -> {
                binding?.onboardtextTextView?.setText(R.string.second_onboard_text)
                binding?.onboardAnimationView?.setAnimation(R.raw.second_onboard_animation)
            }

            STEP_3 -> {
                binding?.onboardtextTextView?.setText(R.string.third_onboard_text)
                binding?.onboardAnimationView?.setAnimation(R.raw.third_onboard_aimation)
            }
        }
    }

    companion object {

        fun getOnbordingStepFragment(stepNumber: Int): StepOnboardingFragment {
            return StepOnboardingFragment().apply {
                arguments = bundleOf(STEP_EXTRA to stepNumber)
            }
        }
    }
}