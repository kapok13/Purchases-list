package com.vb.purchaseslist.ui.main.purchases

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.vb.purchaseslist.R

class DeleteAllItemsDialog(private val deleteAllItemsCallback: () -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        with(AlertDialog.Builder(activity)) {
            setMessage(R.string.delete_confirmation_message)
            setPositiveButton(R.string.ok) { dialog, _ ->
                deleteAllItemsCallback.invoke()
                dialog.dismiss()
            }
            setNegativeButton(R.string.no) { dialog, _ -> dialog.dismiss() }
            create()
        }

}