/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thanebuhanan.caloriecounter.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thanebuhanan.caloriecounter.data.dto.DayDTO
import com.thanebuhanan.caloriecounter.databinding.ListItemDayBinding

class DayAdapter(private val onClick: (String) -> Unit) :
    ListAdapter<DayDTO, ViewHolder>(DayDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val day = getItem(position) as DayDTO
        holder.bind(onClick, day)
    }
}

class DayDiffCallback : DiffUtil.ItemCallback<DayDTO>() {
    override fun areItemsTheSame(oldItem: DayDTO, newItem: DayDTO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DayDTO, newItem: DayDTO): Boolean {
        return oldItem == newItem
    }
}

class ViewHolder(private val binding: ListItemDayBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(onClick: (String) -> Unit, item: DayDTO) {
        binding.dayView.text = item.id
        binding.root.setOnClickListener { onClick(item.id) }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemDayBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}


