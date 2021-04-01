package com.vb.purchaseslist.di.module

import com.vb.purchaseslist.data.repository.PurchasesItemsRepo
import com.vb.purchaseslist.ui.main.insert.NewPurchasePresenter
import com.vb.purchaseslist.ui.main.insert.NewPurchasePresenterImpl
import dagger.Module
import dagger.Provides

@Module
class InsertionModule {


    @Provides
    fun provideNewPurchasePresenter(purchasesItemsRepo: PurchasesItemsRepo): NewPurchasePresenter =
        NewPurchasePresenterImpl(purchasesItemsRepo)


}