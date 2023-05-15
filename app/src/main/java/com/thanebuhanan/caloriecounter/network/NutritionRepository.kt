package com.thanebuhanan.caloriecounter.network

import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject
import javax.inject.Singleton

interface NutritionRepository {

}

@Singleton
@ContributesBinding(NutritionRepository::class)
class NutritionRepositoryImpl @Inject constructor() : NutritionRepository {

}