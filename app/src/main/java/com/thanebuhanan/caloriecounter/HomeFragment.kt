package com.thanebuhanan.caloriecounter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thanebuhanan.caloriecounter.data.dto.DayDTO
import com.thanebuhanan.caloriecounter.databinding.FragmentHomeBinding
import com.thanebuhanan.caloriecounter.ui.DayAdapter
import com.thanebuhanan.caloriecounter.ui.DayListener

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        val adapter = DayAdapter(DayListener { dayId ->
//            ViewModel.onSleepNightClicked(nightId)
        })
        binding.listOfDays.adapter = adapter
        adapter.addHeaderAndSubmitList(listOf(
            DayDTO(name = "spoof_one")
        ))
        return binding.root
    }
}