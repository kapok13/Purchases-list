package com.vb.purchaseslist.ui.main.insert

import com.vb.purchaseslist.data.model.Purchase

interface NewPurchasePresenter {

    fun getCurrentPictureUri(): String?

    fun setCurrentPictureUri(string: String?)

    fun resetCurrentPictureUri()

    fun insertPurchase(purchase: Purchase)

}