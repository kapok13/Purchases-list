package com.vb.purchaseslist.ui.main.purchases

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vb.purchaseslist.R
import com.vb.purchaseslist.data.model.Purchase
import com.vb.purchaseslist.databinding.ItemPurchasesBinding

class PurchasesRecyclerAdapter(
    private val onBasketClick: (item: Purchase) -> Unit,
    private val checkBoxCheckedCallback: (item: Purchase, isChecked: Boolean) -> Unit
) : RecyclerView.Adapter<PurchasesRecyclerAdapter.ShoppingsViewHolder>() {


    private var purchasesList: List<Purchase>? = null


    fun getCurrentPurchasesList(): List<Purchase>? = purchasesList


    fun submitPurchasesList(list: List<Purchase>) {
        purchasesList = list
        notifyDataSetChanged()
    }


    inner class ShoppingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val purchasesBinding = ItemPurchasesBinding.bind(itemView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ShoppingsViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_purchases, parent, false)
    )


    override fun onBindViewHolder(holder: ShoppingsViewHolder, position: Int) {
        if (purchasesList != null) {
            with(purchasesList!![position]) {
                if (!image.isNullOrEmpty()) holder.purchasesBinding.itemPic
                    .setImageURI(Uri.parse(image))
                else {
                    holder.purchasesBinding.itemPic.setImageResource(R.drawable.ic_launcher_background)
                    holder.purchasesBinding.itemPic.visibility = View.INVISIBLE
                }
                if (!name.isNullOrEmpty()) holder.purchasesBinding.itemName.text = name
                holder.purchasesBinding.purchasesPurchaseImage
                    .setOnClickListener { onBasketClick.invoke(this) }
                holder.purchasesBinding.itemCheckbox.setOnCheckedChangeListener { _, isChecked ->
                    checkBoxCheckedCallback.invoke(this, isChecked)
                }
            }
        } else throw NullPointerException("PurchasedRecyclerAdapter does not have list attached. Call submitPurchasesList to attach! ")
    }


    override fun getItemCount() = purchasesList?.size ?: 0


}