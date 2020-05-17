package com.isa.project2


import com.google.gson.annotations.SerializedName

data class CovidGlobalItem(
    @SerializedName("attributes")
    val attributes: AttributesX
)