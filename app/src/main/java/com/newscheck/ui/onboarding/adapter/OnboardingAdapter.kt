package com.newscheck.ui.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.newscheck.ui.onboarding.STEP_1
import com.newscheck.ui.onboarding.STEP_2
import com.newscheck.ui.onboarding.STEP_3
import com.newscheck.ui.onboarding.StepOnboardingFragment.Companion.getOnbordingStepFragment

class OnboardingAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val listFragments = arrayListOf(
        getOnbordingStepFragment(STEP_1),
        getOnbordingStepFragment(STEP_2),
        getOnbordingStepFragment(STEP_3)
    )

    override fun getCount() = listFragments.size

    override fun getItem(position: Int): Fragment = listFragments[position]

}