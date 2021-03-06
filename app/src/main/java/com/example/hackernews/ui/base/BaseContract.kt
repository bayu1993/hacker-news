package com.example.hackernews.ui.base

interface BaseContract {
    interface Presenter<in V:View>{
        fun onAttach(view:V)
        fun onDetach()
    }
    interface View{
        fun onAttachView()
        fun onDetachView()
    }
}
