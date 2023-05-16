package com.thanebuhanan.caloriecounter.ui.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thanebuhanan.caloriecounter.databinding.FragmentDayBinding

class DayFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dayId = DayFragmentArgs.fromBundle(requireArguments()).dayId
        val binding = FragmentDayBinding.inflate(inflater, container, false)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(DayFragmentDirections.actionDayFragmentToFoodFragment())
        }

        return binding.root
    }
}