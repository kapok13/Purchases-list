package com.vb.purchaseslist.ui.main.purchased

import com.vb.purchaseslist.data.model.Purchased
import io.reactivex.Flowable

interface PurchasedPresenter {


    fun getPurchased(): Flowable<List<Purchased>>


}