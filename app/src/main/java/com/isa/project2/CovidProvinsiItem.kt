package com.isa.project2


import com.google.gson.annotations.SerializedName

data class CovidProvinsiItem(
    @SerializedName("attributes")
    val attributes: Attributes
)