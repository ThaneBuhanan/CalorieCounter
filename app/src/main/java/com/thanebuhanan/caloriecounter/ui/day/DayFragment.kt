package com.thanebuhanan.caloriecounter.ui.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thanebuhanan.caloriecounter.databinding.FragmentDayBinding

class DayFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDayBinding.inflate(inflater, container, false)

        return binding.root
    }
}