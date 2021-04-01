package com.vb.purchaseslist.ui.main.insert

import com.vb.purchaseslist.data.model.Purchase
import com.vb.purchaseslist.data.repository.PurchasesItemsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewPurchasePresenterImpl(private val purchasesItemsRepo: PurchasesItemsRepo) :
    NewPurchasePresenter {

    private val newPurchaseScope = CoroutineScope(Job() + Dispatchers.Main)

    private var pictureUri: String? = null

    override fun getCurrentPictureUri(): String? = pictureUri

    override fun setCurrentPictureUri(string: String?) {
        pictureUri = string
    }

    override fun resetCurrentPictureUri() {
        pictureUri = null
    }

    override fun insertPurchase(purchase: Purchase) {
        newPurchaseScope.launch(Dispatchers.IO) {
            purchasesItemsRepo.insertPurchase(purchase)
        }
    }
}