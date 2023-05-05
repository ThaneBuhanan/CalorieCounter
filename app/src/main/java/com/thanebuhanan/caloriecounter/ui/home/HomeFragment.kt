package com.thanebuhanan.caloriecounter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thanebuhanan.caloriecounter.data.dto.DayDTO
import com.thanebuhanan.caloriecounter.data.local.LocalDB
import com.thanebuhanan.caloriecounter.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        val nutritionDao = LocalDB.getNutritionDao(requireContext())
        val viewModelFactory = HomeViewModelFactory(nutritionDao)
        val homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        val adapter = DayAdapter(DayListener { dayId ->
//            ViewModel.onSleepNightClicked(nightId)
        })
        binding.listOfDays.adapter = adapter

        homeViewModel.days.observe(viewLifecycleOwner) {dayDTOs ->
            adapter.submitList(dayDTOs)
        }

        binding.listOfDays.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }
}