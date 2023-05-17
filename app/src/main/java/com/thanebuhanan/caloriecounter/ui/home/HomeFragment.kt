package com.thanebuhanan.caloriecounter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thanebuhanan.caloriecounter.CalorieCounterApplication
import com.thanebuhanan.caloriecounter.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as CalorieCounterApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = DayAdapter { dayId ->
            viewModel.onDayClicked(dayId)
            viewModel.doneNavigating()
        }
        binding.listOfDays.adapter = adapter

        viewModel.days.observe(viewLifecycleOwner) { dayDTOs ->
            adapter.submitList(dayDTOs)
        }
        viewModel.goToDayScreen.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDayFragment(it))
                viewModel.doneNavigating()
            }
        }
        binding.listOfDays.layoutManager = LinearLayoutManager(requireContext())
        binding.floatingActionButton.setOnClickListener {
            viewModel.createDay()
        }

        return binding.root
    }
}