package edu.sjsu.twitterfeeds.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class FragmentWithLoadingDialogs : Fragment() {

    private lateinit var loadingAlertDialog: AlertDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeLoadingAlertDialog()
    }

    private fun initializeLoadingAlertDialog() {
        loadingAlertDialog =
            AlertDialog.Builder(activity).setMessage("Loading...").setCancelable(false).create()
    }

    protected fun showLoadingDialog() {
        loadingAlertDialog.show()
    }

    protected fun hideLoadingDialog() {
        loadingAlertDialog.dismiss()
    }

    protected fun showSuccessMessage(successMessage: String) {
        AlertDialog.Builder(activity).setMessage(successMessage).setCancelable(true)
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    protected fun showErrorMessage(errorMessage: String) {
        AlertDialog.Builder(activity).setMessage(errorMessage).setCancelable(true)
            .setNegativeButton(errorMessage) { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }
}