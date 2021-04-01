package com.vb.purchaseslist.ui.main.insert

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.vb.purchaseslist.R
import com.vb.purchaseslist.data.model.Purchase
import com.vb.purchaseslist.databinding.FragmentInsertItemBinding
import com.vb.purchaseslist.di.Injector.clearInsertionComponent
import com.vb.purchaseslist.di.Injector.getInsertionComponent
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject


const val CAMERA_SOURCE_CODE = 1

const val FOLDER_SOURCE_CODE = 2

class NewPurchaseFragment : Fragment(R.layout.fragment_insert_item), View.OnClickListener {

    private val insertItemBinding by lazy { FragmentInsertItemBinding.bind(requireView()) }

    @Inject
    lateinit var newPurchasePresenter: NewPurchasePresenter


    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) createPurchasePictureChooser()
        }

    private val storageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                try {
                    insertItemBinding.insertPictureImage.setImageURI(it.data?.data)
                    newPurchasePresenter.setCurrentPictureUri(it.data?.data.toString())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                try {
                    val uri = saveBitmapFromCamera(it.data!!.extras!!.get("data") as Bitmap)
                    insertItemBinding.insertPictureImage.setImageURI(uri)
                    newPurchasePresenter.setCurrentPictureUri(uri.toString())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    private fun saveBitmapFromCamera(bitmap: Bitmap): Uri {
        val directory = ContextWrapper(activity?.applicationContext).getDir(
            "cameraImages",
            Context.MODE_PRIVATE
        )
        val file = File(directory, "${System.currentTimeMillis()}.png")
        val fileStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileStream)
        fileStream.close()
        return Uri.fromFile(file)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInsertionComponent()?.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        insertItemBinding.insertPictureImage.setOnClickListener(this)
        insertItemBinding.insertAddFab.setOnClickListener(this)
    }

    private fun checkForInsertConditions() {
        if (insertItemBinding.insertNameEdit.text.isNotEmpty() && newPurchasePresenter.getCurrentPictureUri() != null) {
            newPurchasePresenter.insertPurchase(
                Purchase(
                    insertItemBinding.insertNameEdit.text.toString(),
                    newPurchasePresenter.getCurrentPictureUri()
                )
            )
            Snackbar.make(
                insertItemBinding.insertCoordinator,
                R.string.successful_insertion,
                Snackbar.LENGTH_LONG
            ).show()
            clearFragment()
        } else {
            if (insertItemBinding.insertNameEdit.text.isEmpty() && newPurchasePresenter.getCurrentPictureUri() != null) {
                newPurchasePresenter.insertPurchase(
                    Purchase(
                        null,
                        newPurchasePresenter.getCurrentPictureUri()
                    )
                )
                Snackbar.make(
                    insertItemBinding.insertCoordinator,
                    R.string.successful_insertion,
                    Snackbar.LENGTH_LONG
                ).show()
                clearFragment()
            } else {
                if (insertItemBinding.insertNameEdit.text.isNotEmpty() && newPurchasePresenter.getCurrentPictureUri() == null) {
                    newPurchasePresenter.insertPurchase(
                        Purchase(
                            insertItemBinding.insertNameEdit.text.toString(),
                            null
                        )
                    )
                    Snackbar.make(
                        insertItemBinding.insertCoordinator,
                        R.string.successful_insertion,
                        Snackbar.LENGTH_LONG
                    ).show()
                    clearFragment()
                } else {
                    Snackbar.make(
                        insertItemBinding.insertCoordinator,
                        R.string.insertion_error_message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun clearFragment() {
        newPurchasePresenter.setCurrentPictureUri(null)
        insertItemBinding.insertNameEdit.text.clear()
        insertItemBinding.insertPictureImage.setImageResource(R.drawable.ic_baseline_image_300)
    }

    override fun onClick(v: View?) {
        when (v) {
            insertItemBinding.insertAddFab -> {
                checkForInsertConditions()
            }
            insertItemBinding.insertPictureImage -> {
                newPurchasePresenter.resetCurrentPictureUri()
                checkForExternalStoragePermission(requireContext())
            }
        }
    }

    private fun checkForExternalStoragePermission(context: Context) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        else createPurchasePictureChooser()
    }

    private fun getPictureFromGallery() = storageLauncher
        .launch(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))

    private fun getPictureFromCamera() =
        cameraLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))

    private fun createPurchasePictureChooser() {
        ChooseImageSourceDialog {
            when (it) {
                CAMERA_SOURCE_CODE -> getPictureFromCamera()
                FOLDER_SOURCE_CODE -> getPictureFromGallery()
            }
        }.show(requireActivity().supportFragmentManager, "source chooser")
    }

    override fun onDestroy() {
        super.onDestroy()
        clearInsertionComponent()
    }


}