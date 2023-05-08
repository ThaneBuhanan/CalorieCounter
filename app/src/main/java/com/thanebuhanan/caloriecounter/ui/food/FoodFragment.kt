package com.thanebuhanan.caloriecounter.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thanebuhanan.caloriecounter.databinding.FragmentFoodBinding

class FoodFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFoodBinding.inflate(inflater, container, false)

        return binding.root
    }
}