package com.vb.purchaseslist.ui.main.purchased

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vb.purchaseslist.R
import com.vb.purchaseslist.databinding.FragmentPurchasedBinding
import com.vb.purchaseslist.di.Injector.clearPurchasedComponent
import com.vb.purchaseslist.di.Injector.getPurchasedComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PurchasedFragment : Fragment(R.layout.fragment_purchased) {

    private val purchasedBinding by lazy { FragmentPurchasedBinding.bind(requireView()) }

    private var recyclerAdapter: PurchasedRecyclerAdapter? = null

    private var purchasedDisposable: Disposable? = null

    @Inject
    lateinit var purchasedPresenter: PurchasedPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPurchasedComponent()?.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        subscribeToPurchasedData()
    }


    private fun subscribeToPurchasedData() {
        purchasedDisposable = purchasedPresenter.getPurchased()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { recyclerAdapter?.submitPurchasedList(it) }
    }


    private fun initRecycler() {
        with(purchasedBinding.purchasedRecycler) {
            recyclerAdapter = PurchasedRecyclerAdapter()
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        purchasedDisposable?.dispose()
        clearPurchasedComponent()
    }


}