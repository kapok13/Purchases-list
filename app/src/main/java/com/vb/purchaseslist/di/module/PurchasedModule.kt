package com.vb.purchaseslist.di.module

import com.vb.purchaseslist.data.repository.PurchasesItemsRepo
import com.vb.purchaseslist.ui.main.purchased.PurchasedPresenter
import com.vb.purchaseslist.ui.main.purchased.PurchasedPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class PurchasedModule {


    @Provides
    fun providePurchasedPresenter(purchasesItemsRepo: PurchasesItemsRepo): PurchasedPresenter =
        PurchasedPresenterImpl(purchasesItemsRepo)


}