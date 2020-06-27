package com.isa.project2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.isa.project2.data.CovidGlobal
import com.isa.project2.data.CovidIndo
import com.isa.project2.data.apiRequest
import com.isa.project2.data.httpClient
import com.isa.project2.util.dismissLoading
import com.isa.project2.util.showLoading
import com.isa.project2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_covid_global.*
import kotlinx.android.synthetic.main.fragment_covid_global.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class CovidGlobalFragment : Fragment() {

//    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_covid_global, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetCovidGlobal()

//        mSwipeRefreshLayout = view.swipeRefreshLayout
    }

    private fun callApiGetCovidGlobal() {
        showLoading (context!!, swipeRefreshLayout)

        val httpClient = httpClient ()
        val apiRequest = apiRequest <CovidGlobal>(httpClient)

        val call = apiRequest.getUsers()
        call.enqueue(object : Callback<List<CovidGlobalItem>> {

            override fun onFailure(call: Call<List<CovidGlobalItem>>, t: Throwable) {
                dismissLoading (swipeRefreshLayout)
            }

            override fun onResponse(call: Call<List<CovidGlobalItem>>, response: Response<List<CovidGlobalItem>>) {
                dismissLoading (swipeRefreshLayout)

                when {
                    response.isSuccessful ->

                        when {
                            response.body()?.size != 0 ->

                                tampilCovidGlobal(response.body()!!)

                            else -> {
                                tampilToast(context!!,"Berhasil")
                            }
                        }

                    else -> {
                        tampilToast (context!!, "Gagal" )
                    }
                }
            }
        })
    }

    private fun tampilCovidGlobal(covidGlobals: List<CovidGlobalItem>) {
        listCovidGlobal.layoutManager = LinearLayoutManager(context)
        listCovidGlobal.adapter =CovidGlobalAdapter(context!!, covidGlobals) {
            val covidGlobal = it
            tampilToast (context!!, covidGlobal.attributes.countryRegion)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache ()
    }
}
