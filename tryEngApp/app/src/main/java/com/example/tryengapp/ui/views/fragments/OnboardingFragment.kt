package com.example.tryengapp.views.fragments

import com.example.tryengapp.R
import com.example.tryengapp.views.base.BaseFragment
import kotlinx.android.synthetic.main.layout_onboarding.*
import org.koin.android.ext.android.inject

class OnboardingFragment: BaseFragment() {

    private val viewModel: AuthorizationViewModel by inject()

    override val menuResultId: Int? = null
    override val toolbar: Int? = null
    override val contentResultId = R.layout.main_fragment

    override fun setListeners() {
        btnNext.setOnClickListener {
            viewModel.setOnboardingViewed()
            navigate(OnboardingFragmentDirections.actionOnboardingFragmentToHomeFragment())
        }
    }
}