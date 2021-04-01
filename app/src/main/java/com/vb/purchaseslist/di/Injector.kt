package com.vb.purchaseslist.di

import android.content.Context
import com.vb.purchaseslist.di.component.*
import com.vb.purchaseslist.di.module.InsertionModule
import com.vb.purchaseslist.di.module.MainModule
import com.vb.purchaseslist.di.module.PurchasedModule
import com.vb.purchaseslist.di.module.PurchasesModule

object Injector {

    private var mainComponent: MainComponent? = null

    private var insertionComponent: InsertionComponent? = null

    private var purchasesComponent: PurchasesComponent? = null

    private var purchasedComponent: PurchasedComponent? = null


    fun buildMainComponent(context: Context) {
        if (mainComponent == null)
            mainComponent = DaggerMainComponent.builder().mainModule(MainModule(context)).build()
    }


    fun getInsertionComponent(): InsertionComponent? {
        return if (mainComponent != null) {
            if (insertionComponent == null)
                insertionComponent = mainComponent?.addInsertionComponent(InsertionModule())
            insertionComponent
        } else {
            null
        }
    }


    fun getPurchasedComponent(): PurchasedComponent? {
        return if (mainComponent != null) {
            if (purchasedComponent == null)
                purchasedComponent = mainComponent?.addPurchasedComponent(PurchasedModule())
            purchasedComponent
        } else {
            null
        }
    }


    fun getPurchasesComponent(): PurchasesComponent? {
        return if (mainComponent != null) {
            if (purchasesComponent == null)
                purchasesComponent = mainComponent?.addPurchasesComponent(PurchasesModule())
            purchasesComponent
        } else {
            null
        }
    }


    fun clearInsertionComponent() {
        insertionComponent = null
    }


    fun clearPurchasesComponent() {
        purchasesComponent = null
    }


    fun clearPurchasedComponent() {
        purchasedComponent = null
    }


}