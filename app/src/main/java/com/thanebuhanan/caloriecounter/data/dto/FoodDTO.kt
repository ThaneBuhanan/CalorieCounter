package com.thanebuhanan.caloriecounter.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    foreignKeys = [ForeignKey(
        entity = DayDTO::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("day"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class FoodDTO(
    @PrimaryKey val foodId: String = UUID.randomUUID().toString(),
    val name: String,
    val calories: Double,
    val protein: Double,
    @ColumnInfo(index = true)
    val day: String
)
