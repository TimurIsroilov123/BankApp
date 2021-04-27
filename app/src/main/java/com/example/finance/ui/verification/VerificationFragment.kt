package com.example.finance.ui.verification

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.finance.R
import com.example.finance.databinding.FragmentVerificationBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class VerificationFragment : Fragment(R.layout.fragment_verification) {

    private var _binding: FragmentVerificationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VerificationViewModel by viewModels()

    private var uiStateJob: Job? = null

    private val defaultButtonTintColor = "#979797"
    private val onFormValidButtonTintColor = "#000000"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerificationBinding.inflate(inflater, container, false)
        initListeners()
        uiStateJob = collectFlow()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initListeners() {
        binding.apply {
            etName.addTextChangedListener {
                viewModel.setFirstName(it.toString())
            }
            etPassword.addTextChangedListener {
                viewModel.setPassword(it.toString())
            }
            btLogin.setOnClickListener {
                val action = VerificationFragmentDirections.actionVerificationFragmentToDashboardFragment(etName.text.toString())
                findNavController().navigate(action)
            }
        }
    }

    private fun collectFlow() =
        lifecycleScope.launch {
            viewModel.isSubmitEnabled.collect {
                binding.apply {
                    btLogin.isEnabled = it
                    btLogin.backgroundTintList = ColorStateList.valueOf(
                        Color.parseColor(
                            if (it) onFormValidButtonTintColor else defaultButtonTintColor
                        )
                    )
                }
            }
        }

    override fun onStop() {
        uiStateJob?.cancel()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}