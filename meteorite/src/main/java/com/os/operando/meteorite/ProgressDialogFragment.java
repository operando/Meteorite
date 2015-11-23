package com.os.operando.meteorite;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;

public class ProgressDialogFragment extends DialogFragment {

    private final static String FRAGMENT_TAG = ProgressDialogFragment.class.getName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Uploading Screenshot...");
        return progressDialog;
    }

    public static void show(FragmentManager fragmentManager) {
        if (fragmentManager != null && fragmentManager.findFragmentByTag(FRAGMENT_TAG) == null) {
            new ProgressDialogFragment().show(fragmentManager, FRAGMENT_TAG);
            fragmentManager.executePendingTransactions();
        }
    }

    public static void dismiss(FragmentManager fragmentManager) {
        ProgressDialogFragment dialog = (ProgressDialogFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (dialog != null) {
            dialog.onDismiss(dialog.getDialog());
        }
    }
}
