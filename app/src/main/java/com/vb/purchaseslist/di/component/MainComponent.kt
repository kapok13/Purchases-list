package com.vb.purchaseslist.di.component

import com.vb.purchaseslist.PurchasesApp
import com.vb.purchaseslist.di.module.InsertionModule
import com.vb.purchaseslist.di.module.MainModule
import com.vb.purchaseslist.di.module.PurchasedModule
import com.vb.purchaseslist.di.module.PurchasesModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [MainModule::class])
@Singleton
interface MainComponent {


    fun inject(app: PurchasesApp)


    fun addInsertionComponent(insertionModule: InsertionModule): InsertionComponent


    fun addPurchasedComponent(purchasedModule: PurchasedModule): PurchasedComponent


    fun addPurchasesComponent(purchasesModule: PurchasesModule): PurchasesComponent


}