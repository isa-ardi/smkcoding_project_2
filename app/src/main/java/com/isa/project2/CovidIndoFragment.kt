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
import com.isa.project2.data.CovidIndo
import com.isa.project2.data.apiRequest
import com.isa.project2.data.httpClient
import com.isa.project2.util.dismissLoading
import com.isa.project2.util.showLoading
import com.isa.project2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_covid_indo.*
import kotlinx.android.synthetic.main.fragment_covid_indo.swipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_covid_indo.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class CovidIndoFragment : Fragment() {

    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_covid_indo, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetCovidIndo()

        mSwipeRefreshLayout = view.swipeRefreshLayout
    }

    private fun callApiGetCovidIndo() {
        showLoading (context!!, swipeRefreshLayout)

        val httpClient = httpClient ()
        val apiRequest = apiRequest <CovidIndo>(httpClient)

        val call = apiRequest.getUsers()
        call.enqueue(object : Callback<List<CovidIndoItem>> {

            override fun onFailure(call: Call<List<CovidIndoItem>>, t: Throwable) {
                dismissLoading (swipeRefreshLayout)
            }

            override fun onResponse(call: Call<List<CovidIndoItem>>, response: Response<List<CovidIndoItem>>) {
                dismissLoading (swipeRefreshLayout)

                when {
                    response.isSuccessful ->

                        when {
                            response.body()?.size != 0 ->

                                tampilCovidIndo(response.body()!!)

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

    private fun tampilCovidIndo(covidIndos: List<CovidIndoItem>) {
        listCovidIndo.layoutManager = LinearLayoutManager(context)
        listCovidIndo.adapter =CovidIndoAdapter(context!!, covidIndos) {
            val covidIndo = it
            tampilToast (context!!, covidIndo.name)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache ()
    }
}
