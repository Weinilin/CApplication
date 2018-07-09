package com.example.aw.capplication.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aw.capplication.DashboardFragment;
import com.example.aw.capplication.R;

/**
 * Created by Aw on 9/7/2018.
 */

public class AddTopicFragment extends DialogFragment {
    String finalUsername;
    String finalPassword;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();


        if (getArguments() != null) {

            finalUsername = getArguments().getString(getResources().getString(R.string.USERNAME_TEXT),
                    "");
            finalPassword = getArguments().getString(getResources().getString(R.string.PASSWORD_TEXT), "");
        }
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        builder.setView(inflater.inflate(R.layout.dialog_add_topic, null))
                // Add action buttons
                .setPositiveButton(R.string.ADD_TEXT, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                    }
                })
                .setNegativeButton(R.string.CANCEL_TEXT, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddTopicFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();    //super.onStart() is where dialog.show() is actually called on the underlying dialog, so we have to do it after this point
        AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText content_edittext = getDialog().findViewById(R.id.content_edittext);
                    String content = content_edittext.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString(getResources().getString(R.string.USERNAME_TEXT), finalUsername);
                    bundle.putString(getResources().getString(R.string.PASSWORD_TEXT), finalPassword);
                    bundle.putString(getResources().getString(R.string.TOPIC_CONTENT_TEXT), content);


                }
            });
        }
    }
}



