package com.vb.purchaseslist.data.repository

import com.vb.purchaseslist.data.model.Purchased
import com.vb.purchaseslist.data.model.Purchase
import io.reactivex.Flowable

class PurchasesItemsRepoImpl(private val itemsDao: ItemsDao) : PurchasesItemsRepo {


    override fun getPurchased(): Flowable<List<Purchased>> = itemsDao.getPurchased()


    override fun getPurchases(): Flowable<List<Purchase>> = itemsDao.getPurchases()


    override fun insertPurchase(purchase: Purchase) = itemsDao.insertPurchase(purchase)


    override fun insertPurchased(purchased: Purchased) =
        itemsDao.insertPurchased(purchased)


    override fun deleteAllPurchases() = itemsDao.deleteAllPurchases()


    override fun deletePurchase(purchase: Purchase) = itemsDao.deletePurchase(purchase)


}