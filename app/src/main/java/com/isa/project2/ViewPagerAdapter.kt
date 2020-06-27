package com.isa.project2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){

    private val JUMLAH_MENU = 5

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> { return CovidIndoFragment() }
            1 -> { return CovidFragment() }
            2 -> { return CovidGlobalFragment() }
            3 -> { return DampakPencegahanFragment() }
            4 -> { return ProfilFragment() }
            else -> {
                return CovidFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}