package com.vb.purchaseslist.ui.main.purchased

import com.vb.purchaseslist.data.model.Purchased
import com.vb.purchaseslist.data.repository.PurchasesItemsRepo
import io.reactivex.Flowable

class PurchasedPresenterImpl(private val purchasesItemsRepo: PurchasesItemsRepo) :
    PurchasedPresenter {

    override fun getPurchased(): Flowable<List<Purchased>> = purchasesItemsRepo.getPurchased()

}