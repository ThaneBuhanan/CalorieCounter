package com.thanebuhanan.caloriecounter.ui.setup


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thanebuhanan.caloriecounter.CalorieCounterApplication
import com.thanebuhanan.caloriecounter.databinding.FragmentSetupBinding
import javax.inject.Inject

class SetupFragment : Fragment() {

    @Inject
    lateinit var viewModel: SetupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as CalorieCounterApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSetupBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.buttonGain.setOnClickListener {
            viewModel.saveUser(true)
        }
        binding.buttonLose.setOnClickListener {
            viewModel.saveUser(false)
        }

        viewModel.navigateToHome.observe(viewLifecycleOwner) {
            findNavController().navigate(
                SetupFragmentDirections.actionSetupFragmentToHomeFragment()
            )
        }

        return binding.root
    }
}