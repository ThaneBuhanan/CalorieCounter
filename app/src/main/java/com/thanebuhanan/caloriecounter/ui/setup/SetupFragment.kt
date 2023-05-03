package com.thanebuhanan.caloriecounter.ui.setup


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.thanebuhanan.caloriecounter.data.local.LocalDB
import com.thanebuhanan.caloriecounter.databinding.FragmentSetupBinding

class SetupFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val nutritionDao = LocalDB.getNutritionDao(requireContext())
        val viewModelFactory = SetupViewModelFactory(nutritionDao)
        val setupViewModel = ViewModelProvider(this, viewModelFactory)[SetupViewModel::class.java]

        val binding = FragmentSetupBinding.inflate(inflater, container, false)
        binding.viewModel = setupViewModel
        binding.buttonGain.setOnClickListener {
            val test = binding.viewModel!!.age.value
            val test2 = test
        }

        return binding.root
    }
}