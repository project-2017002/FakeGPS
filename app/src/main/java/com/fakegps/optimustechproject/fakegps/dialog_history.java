package com.fakegps.optimustechproject.fakegps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Created by satyam on 21/7/17.
 */

public class dialog_history extends DialogFragment {

    View parentView;
    RecyclerView recyclerView;
    TextView no_mock;
    Gson gson=new Gson();
    static AlertDialog.Builder builder;
    Button close;

    //////////// HISTORY DIALOG FRAGMENT /////////

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        parentView = inflater.inflate(R.layout.dialog_his, null);
        builder.setView(parentView);

        close=(Button)parentView.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        no_mock=(TextView)parentView.findViewById(R.id.no_mock);
        recyclerView=(RecyclerView)parentView.findViewById(R.id.recycler);

        if(DbHandler.contains(getActivity(),"history")){

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setHasFixedSize(true);
            adapter_history adapter_history=new adapter_history(gson.fromJson(DbHandler.getString(getActivity(),"history","{}"),History.class));
            recyclerView.setAdapter(adapter_history);
        }
        else{
            ////////// DISPLAY MESSAGE IF NO HISTORY ///////////

            recyclerView.setVisibility(View.GONE);
            no_mock.setVisibility(View.VISIBLE);
            no_mock.setText(getResources().getString(R.string.no_loc));
        }

        return builder.create();
    }

}
