package com.vb.purchaseslist.ui.main.purchases

import com.vb.purchaseslist.data.model.Purchase
import io.reactivex.Flowable

interface PurchasesPresenter {


    fun checkItem(purchase: Purchase)


    fun uncheckItem(purchase: Purchase)


    fun getNumberOfCheckedItems(): Int


    fun buyPurchase(purchase: Purchase)


    fun buyCheckedPurchases()


    fun deleteAllPurchases(allPurchasesList: List<Purchase>)


    fun getPurchases(): Flowable<List<Purchase>>


}