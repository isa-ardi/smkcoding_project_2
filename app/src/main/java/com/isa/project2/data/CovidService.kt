package com.isa.project2.data

import com.isa.project2.CovidProvinsiItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidService{

    @GET("indonesia/provinsi")
    fun getUsers(): Call<List<CovidProvinsiItem>>
}