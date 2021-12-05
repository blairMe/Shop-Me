package com.blair.shopme.view.fragments

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.blair.shopme.databinding.FragmentNewProductBinding
import com.blair.shopme.databinding.SelectPictureDialogBinding
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener


class NewProductFragment : Fragment() {

    lateinit var binding : FragmentNewProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addImageIconSelect()


    }

    //selecting the product image
    fun addImageIconSelect() {
        binding.ivAddProductImage.setOnClickListener {
            //displaying the select image dialog
            val selectImageLocationDialog = Dialog(requireContext())
            val bindingDialog : SelectPictureDialogBinding =
                SelectPictureDialogBinding.inflate(layoutInflater)
            selectImageLocationDialog.setContentView(bindingDialog.root)
            selectImageLocationDialog.show()

            //permissions
            //Camera permission
            bindingDialog.cameraLocation.setOnClickListener {
                Dexter.withContext(requireContext()).withPermissions(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(object: MultiplePermissionsListener{
                        override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                            if(report!!.areAllPermissionsGranted()) {
                                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                startActivityForResult(intent, CAMERA)
                            }
                        }
                        override fun onPermissionRationaleShouldBeShown(
                            permissions: MutableList<PermissionRequest>?,
                            token: PermissionToken?
                        ) {
                            showPermissionsRationalDialog()
                        }
                    }).onSameThread().check()
        }

            //Gallery Permission
            bindingDialog.galleryLocation.setOnClickListener {
                Dexter.withContext(requireContext()).withPermissions(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(object: MultiplePermissionsListener{
                        override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                            if(report!!.areAllPermissionsGranted()) {
                                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                                startActivityForResult(intent, GALLERY)
                            }
                        }
                        override fun onPermissionRationaleShouldBeShown(
                            permissions: MutableList<PermissionRequest>?,
                            token: PermissionToken?
                        ) {
                            showPermissionsRationalDialog()
                        }
                    }).onSameThread().check()
            }
    }
    }

    fun showPermissionsRationalDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Permission")
        builder.setMessage("Please allow access to your Camera")
        builder.setPositiveButton("OK") {dialog, which ->
            Toast.makeText(requireContext(), "Please go to settings and allow permission.", Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("Cancel") {dialog, which ->
            Toast.makeText(requireContext(), "You cancelled", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            if (resultCode == CAMERA) {
                val thumbnail : Bitmap = data?.extras!!.get("Data") as Bitmap
                data.extras.let {
                    Glide.with(this)
                        .load(thumbnail)
                        .centerCrop()
                        .into(binding.ivAddProductImage)


                }
            }

        }
    }

    companion object{
        const val CAMERA = 1
        const val GALLERY = 2
    }



}

