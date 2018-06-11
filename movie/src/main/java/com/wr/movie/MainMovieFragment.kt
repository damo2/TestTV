package com.wr.movie

import com.wr.base.BaseFragment

class MainMovieFragment :BaseFragment(){
    override fun setLayoutResouceId(): Int {
        return R.layout.fragment_main_movie
}
    companion object {
        fun newInstance(): MainMovieFragment {
            val fragment = MainMovieFragment()
            return fragment
        }
    }
}