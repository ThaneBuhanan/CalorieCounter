package com.thanebuhanan.caloriecounter.ui.food

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.thanebuhanan.caloriecounter.CalorieCounterApplication
import com.thanebuhanan.caloriecounter.data.local.LocalDB
import com.thanebuhanan.caloriecounter.databinding.FragmentFoodBinding
import com.thanebuhanan.caloriecounter.ui.setup.SetupViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class FoodFragment : Fragment() {

    @Inject
    lateinit var viewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as CalorieCounterApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFoodBinding.inflate(inflater, container, false)
//        binding.viewModel = homeViewModel
//        binding.lifecycleOwner = this
        val adapter = FoodAdapter(FoodListener {

        })

        binding.foodList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            binding.textInputEditText
                .textChanges()
                .filterNotNull()
                .debounce(1500).collectLatest { q ->
                    val query = q.toString()
                    if (query != "") {
                        viewModel.getFoodItems(query)
                    }
                }
        }

        viewModel.foodItems.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }
}

@ExperimentalCoroutinesApi
@CheckResult
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySend(s)
            }
        }
        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}