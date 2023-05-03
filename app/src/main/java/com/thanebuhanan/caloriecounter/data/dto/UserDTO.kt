package com.thanebuhanan.caloriecounter.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user")
data class UserDTO(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val age: Int,
    val heightFt: Int,
    val heightIn: Int,
    val weight: Int,
    val hasGoalGain: Boolean,
)
