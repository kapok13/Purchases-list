package com.vb.purchaseslist.ui.main.purchased

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vb.purchaseslist.R
import com.vb.purchaseslist.data.model.Purchased
import com.vb.purchaseslist.databinding.ItemPurchasedBinding
import java.lang.NullPointerException

class PurchasedRecyclerAdapter :
    RecyclerView.Adapter<PurchasedRecyclerAdapter.PurchasedViewHolder>() {

    private var purchasesList: List<Purchased>? = null


    fun submitPurchasedList(list: List<Purchased>) {
        purchasesList = list
        notifyDataSetChanged()
    }


    inner class PurchasedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val purchasedBinding = ItemPurchasedBinding.bind(itemView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PurchasedViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_purchased, parent, false)
    )


    override fun onBindViewHolder(holder: PurchasedViewHolder, position: Int) {
        if (purchasesList != null) {
            with(purchasesList!![position]) {
                if (!image.isNullOrEmpty()) holder.purchasedBinding.itemPic
                    .setImageURI(Uri.parse(image))
                else {
                    holder.purchasedBinding.itemPic.setImageResource(R.drawable.ic_launcher_background)
                    holder.purchasedBinding.itemPic.visibility = View.INVISIBLE
                }
                if (!name.isNullOrEmpty()) holder.purchasedBinding.itemName.text = name
            }
        } else throw NullPointerException("PurchasedRecyclerAdapter does not have list attached. Call submitPurchasesList to attach! ")
    }


    override fun getItemCount() = purchasesList?.size ?: 0


}