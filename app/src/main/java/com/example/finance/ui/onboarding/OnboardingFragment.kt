package com.example.finance.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.finance.R
import com.example.finance.databinding.FragmentOnboardingBinding
import com.example.finance.exhaustive
import kotlinx.coroutines.flow.collect

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentImg = 1

        binding.apply {
            btNext.setOnClickListener {
                viewModel.onNextClicked()
            }

            btSkip.setOnClickListener {
                viewModel.onSkipClicked()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.imageEvent.collect { event ->
                when (event) {
                    is OnboardingViewModel.ImageEvent.Next -> {
                        binding.apply {
                            if (viewModel.currentImg == 2) {
                                ivNavItem1.setImageResource(R.drawable.inactive_nav_item)
                                ivNavItem2.setImageResource(R.drawable.active_nav_item)
                                ivPoster.setImageResource(R.drawable.second_onboarding)
                            }
                            else {
                                ivNavItem2.setImageResource(R.drawable.inactive_nav_item)
                                ivNavItem3.setImageResource(R.drawable.active_nav_item)
                                ivPoster.setImageResource(R.drawable.third_onboarding)
                                btNext.text = "Finish"
                            }
                        }
                    }
                    is OnboardingViewModel.ImageEvent.Skip, OnboardingViewModel.ImageEvent.Finish -> {
                        val action = OnboardingFragmentDirections.actionOnboardingFragmentToVerificationFragment()
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}