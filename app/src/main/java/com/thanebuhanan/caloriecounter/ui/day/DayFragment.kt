package com.thanebuhanan.caloriecounter.ui.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thanebuhanan.caloriecounter.CalorieCounterApplication
import com.thanebuhanan.caloriecounter.databinding.FragmentDayBinding
import com.thanebuhanan.caloriecounter.ui.food.FoodAdapter
import javax.inject.Inject

class DayFragment : Fragment() {

    @Inject
    lateinit var viewModel: DayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as CalorieCounterApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dayId = DayFragmentArgs.fromBundle(requireArguments()).dayId
        val binding = FragmentDayBinding.inflate(inflater, container, false)

        val adapter = FoodAdapter { foodItem ->
//            viewModel.saveFoodItem(dayId, foodItem)
        }

        binding.listOfFoods.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(DayFragmentDirections.actionDayFragmentToFoodFragment(dayId))
        }
        viewModel.foodItems.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.populateFoodItems(dayId)

        return binding.root
    }
}