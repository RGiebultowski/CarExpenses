package com.example.wydatkisamochodowe.Activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wydatkisamochodowe.R;

import java.util.Objects;

public class AddCarFragment extends Fragment {

    TextView brand;
    TextView model;
    TextView capacity;
    TextView fuel;

    EditText brandEditText;
    EditText modelEditText;
    EditText capacityEditText;

    Spinner fuelSpinner;

    Button confirmButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_add_car, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        brand = (TextView) view.findViewById(R.id.brand);
        model = (TextView) view.findViewById(R.id.model);
        capacity = (TextView) view.findViewById(R.id.capacity);
        fuel = (TextView) view.findViewById(R.id.fuel);

        brandEditText = (EditText) view.findViewById(R.id.brandEditText);
        modelEditText = (EditText) view.findViewById(R.id.modelEditText);
        capacityEditText = (EditText) view.findViewById(R.id.capacityEditText);

        fuelSpinner = (Spinner) view.findViewById(R.id.fuelSpinner);

        confirmButton = (Button) view.findViewById(R.id.confirmButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()), R.array.fuel_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuelSpinner.setAdapter(adapter);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        brandEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false){
                    validateBrand();
                }
            }
        });

        modelEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false){
                    validateModel();
                }
            }
        });

        capacityEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false){
                    validateCapacity();
                }
            }
        });

        fuelSpinner.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false){
                    validateFuelSpinner();
                }
            }
        });
    }

    private boolean validateBrand(){
        if (TextUtils.isEmpty(brandEditText.getText().toString())){
            String falseMessage = "Podaj markę pojazdu!";
            brand.setText(falseMessage);
            brand.setTextColor(getResources().getColor(R.color.red));
            return false;
        }else {
            String baseMessage = "Marka";
            brand.setText(baseMessage);
            brand.setTextColor(getResources().getColor(R.color.black2));
        }
        return true;
    }

    private boolean validateModel(){
        if (TextUtils.isEmpty(modelEditText.getText().toString())){
            String falseMessage = "Podaj model pojazdu!";
            model.setText(falseMessage);
            model.setTextColor(getResources().getColor(R.color.red));
            return false;
        }else {
            String baseMessage = "Model";
            model.setText(baseMessage);
            model.setTextColor(getResources().getColor(R.color.black2));
        }
        return true;
    }

    private boolean validateCapacity(){
        double userCapacity = Double.parseDouble(capacityEditText.getText().toString());
        if (userCapacity > 7.0){
            String falseMessage = "Podany litraż jest błędny!";
            capacity.setText(falseMessage);
            capacity.setTextColor(getResources().getColor(R.color.red));
            return false;
        }
        if (TextUtils.isEmpty(capacityEditText.getText().toString())){
            String falseMessage = "Podaj pojemność pojazdu!";
            capacity.setText(falseMessage);
            capacity.setTextColor(getResources().getColor(R.color.red));
            return false;
        }else {
            String baseMessage = "Pojemność w litrach";
            capacity.setText(baseMessage);
            capacity.setTextColor(getResources().getColor(R.color.black2));
        }
        return true;
    }

    private boolean validateFuelSpinner(){
        View selectedView = fuelSpinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView){
            TextView selectedTextView = (TextView) selectedView;
            if (selectedTextView.getText().toString().equals("")){
                String falseMessage = "Wybierz rodzaj paliwa!";
                fuel.setText(falseMessage);
                fuel.setTextColor(getResources().getColor(R.color.red));
                return false;
            }
        }else {
            String baseMessage = "Rodzaj paliwa";
            fuel.setText(baseMessage);
            fuel.setTextColor(getResources().getColor(R.color.black2));
        }
        return true;
    }
}