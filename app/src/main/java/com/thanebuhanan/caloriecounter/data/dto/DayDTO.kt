package com.thanebuhanan.caloriecounter.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "day")
data class DayDTO(
    @PrimaryKey val id: String,
)
