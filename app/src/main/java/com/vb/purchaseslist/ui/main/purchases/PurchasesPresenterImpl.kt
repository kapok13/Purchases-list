package com.vb.purchaseslist.ui.main.purchases

import com.vb.purchaseslist.data.model.Purchased
import com.vb.purchaseslist.data.model.Purchase
import com.vb.purchaseslist.data.repository.PurchasesItemsRepo
import io.reactivex.Flowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PurchasesPresenterImpl(private val purchasesItemsRepo: PurchasesItemsRepo) :
    PurchasesPresenter {

    private val purchasesScope = CoroutineScope(Job() + Dispatchers.Main)

    private val checkedItems = mutableListOf<Purchase>()


    override fun checkItem(purchase: Purchase) {
        checkedItems.add(purchase)
    }


    override fun uncheckItem(purchase: Purchase) {
        checkedItems.remove(purchase)
    }


    override fun getNumberOfCheckedItems() = checkedItems.size


    private fun insertPurchased(purchase: Purchase) {
        purchasesItemsRepo.insertPurchased(Purchased(purchase.name, purchase.image))
    }


    override fun buyPurchase(purchase: Purchase) {
        purchasesScope.launch(Dispatchers.IO) {
            insertPurchased(purchase)
            purchasesItemsRepo.deletePurchase(purchase)
        }
    }


    override fun buyCheckedPurchases() {
        purchasesScope.launch(Dispatchers.IO) {
            checkedItems.forEach {
                insertPurchased(it)
                purchasesItemsRepo.deletePurchase(it)
            }
            checkedItems.clear()
        }
    }


    override fun deleteAllPurchases(allPurchasesList: List<Purchase>) {
        purchasesScope.launch(Dispatchers.IO) {
            allPurchasesList.forEach { insertPurchased(it) }
            purchasesItemsRepo.deleteAllPurchases()
        }
    }


    override fun getPurchases(): Flowable<List<Purchase>> = purchasesItemsRepo.getPurchases()


}