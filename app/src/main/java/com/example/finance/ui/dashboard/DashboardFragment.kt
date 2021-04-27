package com.example.finance.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.finance.R
import com.example.finance.data.DataSource
import com.example.finance.databinding.FragmentDashboardBinding
import com.example.finance.databinding.FragmentOnboardingBinding
import com.example.finance.ui.onboarding.OnboardingViewModel

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val args: DashboardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dashboardAdapter = DashboardAdapter()

        binding.apply {
            rvDashboard.adapter = dashboardAdapter
            tvName.text = args.name
        }
        dashboardAdapter.submitList(DataSource.dashboardData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}