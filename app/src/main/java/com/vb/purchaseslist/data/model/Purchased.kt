package com.vb.purchaseslist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchased_items_table")
data class Purchased(val name: String?, val image: String?) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
