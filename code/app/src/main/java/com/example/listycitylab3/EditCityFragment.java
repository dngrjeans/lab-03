package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    interface EditCityDialogueListener {
        void editCity (String name, String province);
    }

    private EditCityFragment.EditCityDialogueListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof EditCityFragment.EditCityDialogueListener) {
            listener = (EditCityFragment.EditCityDialogueListener) context;
        } else {
            throw new RuntimeException(context + "must implement EditCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@NonNull Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);
        EditText editCityText = view.findViewById(R.id.change_city_text);
        EditText editProvinceText = view.findViewById(R.id.change_province_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        return builder
                .setView(view)
                .setTitle("Edit City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Edit", (dialog, which) -> {
                    String cityName = editCityText.getText().toString();
                    String provinceName = editProvinceText.getText().toString();
                    listener.editCity(cityName, provinceName);
                })
                .create();
    }
}

