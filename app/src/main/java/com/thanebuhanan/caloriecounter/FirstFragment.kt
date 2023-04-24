package com.thanebuhanan.caloriecounter


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.thanebuhanan.caloriecounter.databinding.FragmentFirstBinding
import data.Network
import kotlinx.coroutines.launch
import retrofit2.HttpException


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        lifecycleScope.launch {

            try {
                val test = Network.nutritionService.getFoodItems(
                    apiKey = "1f87f0f5c6mshdc59119bc0fd661p12b23ajsnf6874192e8d2",
                    query = "Cheese",
                    fields = "item_name,item_id,brand_name,nf_calories,nf_total_fat,nf_protein",
                )

                val testTwo = test
            } catch (yex: HttpException) {
                Log.e("YO", yex.message!!)
                //Log.e("YO", yex.response()!!.raw().)
                val testThree = yex
            }
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}