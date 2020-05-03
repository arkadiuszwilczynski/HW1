package com.aw.hw1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class RingingDialog extends DialogFragment {

    private OnRingingDialogInteractionListener mListener;

    public RingingDialog() {}

    static RingingDialog newInstance() {
        return new RingingDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.long_click_msg));
        builder.setPositiveButton(getString(R.string.ring), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogPositiveClick(RingingDialog.this);
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_cancel_2), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogNegativeClick(RingingDialog.this);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRingingDialogInteractionListener) {
            mListener = (OnRingingDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRingingDialogInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnRingingDialogInteractionListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }
}
