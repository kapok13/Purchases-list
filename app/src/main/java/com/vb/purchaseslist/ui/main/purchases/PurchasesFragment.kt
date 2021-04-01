package com.vb.purchaseslist.ui.main.purchases

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vb.purchaseslist.R
import com.vb.purchaseslist.data.model.Purchase
import com.vb.purchaseslist.databinding.FragmentPurchasesBinding
import com.vb.purchaseslist.di.Injector.clearPurchasesComponent
import com.vb.purchaseslist.di.Injector.getPurchasesComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PurchasesFragment : Fragment(R.layout.fragment_purchases) {

    private val purchasesBinding by lazy { FragmentPurchasesBinding.bind(requireView()) }

    private var recyclerAdapter: PurchasesRecyclerAdapter? = null

    private var purchasesDisposable: Disposable? = null

    @Inject
    lateinit var purchasesPresenter: PurchasesPresenter

    private val basketClickCallback = { item: Purchase -> purchasesPresenter.buyPurchase(item) }


    private val checkboxCheckStateCallback = { item: Purchase, isChecked: Boolean ->
        if (isChecked) purchasesPresenter.checkItem(item)
        else purchasesPresenter.uncheckItem(item)
    }


    private val deleteAllItemsCallback = {
        purchasesPresenter.deleteAllPurchases(recyclerAdapter?.getCurrentPurchasesList()!!)
        generateDeletionSnackBar()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPurchasesComponent()?.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        subscribeToPurchasesData()
        initDeleteFab()
    }


    private fun generateDeletionSnackBar() {
        Snackbar.make(
            purchasesBinding.purchasesCoordinator,
            R.string.delete_completion_message,
            Snackbar.LENGTH_SHORT
        ).show()
    }


    private fun initDeleteFab() {
        purchasesBinding.purchasesDeleteFab.setOnClickListener {
            if (recyclerAdapter!!.itemCount > 0) {
                if (purchasesPresenter.getNumberOfCheckedItems() < 1) {
                    DeleteAllItemsDialog(deleteAllItemsCallback).show(
                        requireActivity().supportFragmentManager,
                        "buy confirmation"
                    )
                } else {
                    purchasesPresenter.buyCheckedPurchases()
                }
            } else Snackbar.make(
                purchasesBinding.purchasesCoordinator,
                R.string.empty_recycler_message,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }


    private fun subscribeToPurchasesData() {
        purchasesDisposable = purchasesPresenter.getPurchases()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { recyclerAdapter?.submitPurchasesList(it) }
    }


    private fun initRecycler() {
        with(purchasesBinding.purchasesRecycler) {
            recyclerAdapter =
                PurchasesRecyclerAdapter(basketClickCallback, checkboxCheckStateCallback)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        purchasesDisposable?.dispose()
        clearPurchasesComponent()
    }


}