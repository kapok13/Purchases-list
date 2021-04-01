package com.vb.purchaseslist.data.repository

import com.vb.purchaseslist.data.model.Purchased
import com.vb.purchaseslist.data.model.Purchase
import io.reactivex.Flowable

interface PurchasesItemsRepo {


    fun getPurchased(): Flowable<List<Purchased>>


    fun getPurchases(): Flowable<List<Purchase>>


    fun insertPurchase(purchase: Purchase)


    fun insertPurchased(purchased: Purchased)


    fun deleteAllPurchases()


    fun deletePurchase(purchase: Purchase)


}