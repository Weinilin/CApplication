package com.example.aw.capplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Aw on 9/7/2018.
 */

public class DashboardFragment extends Fragment {
    private String username = "";
    private static final int DIALOG_FRAGMENT = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_dashboard, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //for adding topic by user
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       //go sigin page if no user
                                       if (username.equals("")) {

                                           SignInDialogFragment signInDialogFragment = new SignInDialogFragment();
                                           signInDialogFragment.setTargetFragment(DashboardFragment.this, DIALOG_FRAGMENT);
                                           signInDialogFragment.show(getFragmentManager(), "SignIn Dialog");

                                       }
                                   }
                               }
        );
    }
}
