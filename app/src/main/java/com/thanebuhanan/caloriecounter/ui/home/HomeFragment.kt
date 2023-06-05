package com.thanebuhanan.caloriecounter.ui.home

import android.Manifest
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thanebuhanan.caloriecounter.CalorieCounterApplication
import com.thanebuhanan.caloriecounter.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: HomeViewModel

    private val runningTiramisuOrLater = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    private lateinit var activityResultLauncherPermissions: ActivityResultLauncher<Array<String>>

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

        activityResultLauncherPermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { }
        if (!isNotificationPermissionApproved()) {
            requestNotificationPermission()
        }

        return binding.root
    }

    private fun Fragment.hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission,
        ) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.TIRAMISU)
    private fun isNotificationPermissionApproved(): Boolean {
        if (!runningTiramisuOrLater) {
            return true
        }

        return hasPermission(Manifest.permission.POST_NOTIFICATIONS)
    }

    @TargetApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        activityResultLauncherPermissions.launch(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
    }
}