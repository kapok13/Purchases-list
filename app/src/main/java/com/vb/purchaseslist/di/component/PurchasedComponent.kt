package com.vb.purchaseslist.di.component

import com.vb.purchaseslist.di.module.PurchasedModule
import com.vb.purchaseslist.ui.main.purchased.PurchasedFragment
import dagger.Subcomponent

@Subcomponent(modules = [PurchasedModule::class])
interface PurchasedComponent {


    fun inject(purchasedFragment: PurchasedFragment)


}