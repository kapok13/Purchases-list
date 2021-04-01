package com.vb.purchaseslist.di.component

import com.vb.purchaseslist.di.module.PurchasesModule
import com.vb.purchaseslist.ui.main.purchases.PurchasesFragment
import dagger.Subcomponent

@Subcomponent(modules = [PurchasesModule::class])
interface PurchasesComponent {


    fun inject(purchasesFragment: PurchasesFragment)


}