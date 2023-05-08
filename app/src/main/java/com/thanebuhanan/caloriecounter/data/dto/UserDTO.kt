package com.thanebuhanan.caloriecounter.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user")
data class UserDTO(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val weight: Int,
    val goalCalories: Int,
    val goalProtein: Int,
    val hasGoalGain: Boolean,
)
