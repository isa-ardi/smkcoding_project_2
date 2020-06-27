package com.isa.project2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation. Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.isa.project2.data.CovidService
import com.isa.project2.data.apiRequest
import com.isa.project2.data.httpClient
import com.isa.project2.util.dismissLoading
import com.isa.project2.util.showLoading
import com.isa.project2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_covid.*
import kotlinx.android.synthetic.main.fragment_covid.view.swipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CovidFragment : Fragment() {

//    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_covid, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetCovidProvinsi()

//        mSwipeRefreshLayout = view.swipeRefreshLayout
    }

    private fun callApiGetCovidProvinsi() {
        showLoading (context!!, swipeRefreshLayout)

        val httpClient = httpClient ()
        val apiRequest = apiRequest <CovidService>(httpClient)

        val call = apiRequest.getUsers()
        call.enqueue(object : Callback<List<CovidProvinsiItem>> {

            override fun onFailure(call: Call<List<CovidProvinsiItem>>, t: Throwable) {
                dismissLoading (swipeRefreshLayout)
            }

            override fun onResponse(call: Call<List<CovidProvinsiItem>>, response: Response<List<CovidProvinsiItem>>) {
                dismissLoading (swipeRefreshLayout)

                when {
                    response.isSuccessful ->

                        when {
                            response.body()?.size != 0 ->

                                tampilCovidProvinsi(response.body()!!)

                            else -> {
                                tampilToast (context!!, "Berhasil" )
                            }
                        }

                    else -> {
                        tampilToast (context!!, "Gagal" )
                    }
                }
            }
        })
    }

    private fun tampilCovidProvinsi(covidProvinsis: List<CovidProvinsiItem>) {
        listCovidProvinsi.layoutManager = LinearLayoutManager(context)
        listCovidProvinsi.adapter = CovidProvinsiAdapter(context!!, covidProvinsis) {
            val covidProvinsi = it
            tampilToast (context!!, covidProvinsi.attributes.provinsi)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache ()
    }
}
