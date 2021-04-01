package com.vb.purchaseslist

import android.app.Application
import com.vb.purchaseslist.di.Injector.buildMainComponent

class PurchasesApp : Application() {


    override fun onCreate() {
        super.onCreate()
        buildMainComponent(applicationContext)
    }

}