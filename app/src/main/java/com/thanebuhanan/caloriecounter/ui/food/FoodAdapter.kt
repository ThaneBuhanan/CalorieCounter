package com.thanebuhanan.caloriecounter.ui.food

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thanebuhanan.caloriecounter.R
import com.thanebuhanan.caloriecounter.databinding.ListItemFoodBinding
import com.thanebuhanan.caloriecounter.network.calorieninjas.FoodItem


class FoodAdapter(private val clickListener: FoodListener) :
    ListAdapter<FoodItem, ViewHolder>(FoodDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodItem = getItem(position) as FoodItem
        holder.bind(clickListener, foodItem)
    }
}

class FoodDiffCallback : DiffUtil.ItemCallback<FoodItem>() {
    override fun areItemsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem == newItem
    }
}

class FoodListener(val clickListener: () -> Unit) {
    fun onClick() = clickListener()
}

class ViewHolder(private val binding: ListItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: FoodListener, item: FoodItem) {
        val resources = itemView.context.resources
        val string = resources.getString(
            R.string.food_item,
            item.name,
            item.calories,
            item.protein
        )

        binding.foodView.text = string
//        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemFoodBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}


