/*
ASSIGNMENT NUMBER : Home Work 4
NAME : Deep Rajesh Furiya
       Surabhi Jagadeesh
*/

package com.example.group10hw03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class AddDrinkFragment extends Fragment {


    public AddDrinkFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Add Drinks");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_drink, container, false);

        v.findViewById(R.id.cancelButtonAddDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDrinklistener.cancelAddNewDrink();
            }
        });

        RadioGroup drinkSizeRadio = v.findViewById(R.id.drinkRadio);
        SeekBar seekbar = v.findViewById(R.id.alcoholSlider);
        TextView alcoholPercentDisplay = v.findViewById(R.id.alcoholPercentageDisplay);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                alcoholPercentDisplay.setText(String.valueOf(i) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        v.findViewById(R.id.addDrinkBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer drink = 0;
                Integer error = 0;
                if (drinkSizeRadio.getCheckedRadioButtonId() == R.id.radioButton1oz){
                    drink = 1;
                }else if (drinkSizeRadio.getCheckedRadioButtonId() == R.id.radioButton5oz){
                    drink = 5;
                }else if (drinkSizeRadio.getCheckedRadioButtonId() == R.id.radioButton12oz){
                    drink = 12;
                }else{
                    error = 1;
                    Toast.makeText(getActivity(), "Please select drink size to proceed", Toast.LENGTH_SHORT).show();
                }

                if (error != 1) {

                    Double alcholVal = 0.0;
                    alcholVal = Double.valueOf(seekbar.getProgress());

                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                    Drink drinkObj = new Drink(alcholVal,drink, new Date());
                    addDrinklistener.addDrink(drinkObj);
                }

            }
        });

        return v;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddDrinkListener){
            addDrinklistener = (AddDrinkListener) context;
        }else{
            throw new RuntimeException(context.toString()+" should implement AddDrinkListner");
        }
    }

    AddDrinkListener addDrinklistener;

    interface AddDrinkListener{
        void cancelAddNewDrink();
        void addDrink(Drink drink);
    }


}