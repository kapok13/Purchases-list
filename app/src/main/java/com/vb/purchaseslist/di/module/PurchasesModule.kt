package com.vb.purchaseslist.di.module

import com.vb.purchaseslist.data.repository.PurchasesItemsRepo
import com.vb.purchaseslist.ui.main.purchases.PurchasesPresenter
import com.vb.purchaseslist.ui.main.purchases.PurchasesPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class PurchasesModule {


    @Provides
    fun providePurchasesPresenter(purchasesItemsRepo: PurchasesItemsRepo): PurchasesPresenter =
        PurchasesPresenterImpl(purchasesItemsRepo)


}