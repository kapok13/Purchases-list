package com.vb.purchaseslist.ui.main.insert

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.vb.purchaseslist.databinding.DialogImageSourceChooserBinding


class ChooseImageSourceDialog(private val chooserCallback: (code: Int) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        with(AlertDialog.Builder(activity)) {
            val chooserBinding =
                DialogImageSourceChooserBinding.inflate(
                    requireActivity().layoutInflater,
                    null,
                    false
                )
            chooserBinding.dialogSourceCameraText.setOnClickListener {
                chooserCallback.invoke(CAMERA_SOURCE_CODE)
                dismiss()
            }
            chooserBinding.dialogSourceFolderText.setOnClickListener {
                chooserCallback.invoke(FOLDER_SOURCE_CODE)
                dismiss()
            }
            setView(chooserBinding.root)
            create()
        }

}