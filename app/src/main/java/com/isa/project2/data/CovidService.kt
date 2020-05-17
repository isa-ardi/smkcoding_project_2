package com.isa.project2.data

import com.isa.project2.CovidGlobalItem
import com.isa.project2.CovidIndoItem
import com.isa.project2.CovidProvinsiItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidService{

    @GET("indonesia/provinsi")
    fun getUsers(): Call<List<CovidProvinsiItem>>
}

interface CovidIndo{
    @GET("indonesia")
    fun getUsers(): Call<List<CovidIndoItem>>
}

interface CovidGlobal{
    @GET("/")
    fun getUsers(): Call<List<CovidGlobalItem>>
}