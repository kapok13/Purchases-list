package com.vb.purchaseslist.di.component

import com.vb.purchaseslist.di.module.InsertionModule
import com.vb.purchaseslist.ui.main.insert.NewPurchaseFragment
import dagger.Subcomponent

@Subcomponent(modules = [InsertionModule::class])
interface InsertionComponent {


    fun inject(insertionFragment: NewPurchaseFragment)


}