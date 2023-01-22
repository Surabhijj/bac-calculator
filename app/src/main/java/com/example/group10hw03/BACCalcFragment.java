/*
ASSIGNMENT NUMBER : Home Work 4
NAME : Deep Rajesh Furiya
       Surabhi Jagadeesh
*/

package com.example.group10hw03;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class BACCalcFragment extends Fragment {

    User bacUser;
//    static public HashMap<Integer,ArrayList> drink = new HashMap<>();
    static public ArrayList<Drink> drink = new ArrayList<>();
    String TAG = "deep";
    Double bac_value = 0.000;
    Integer drinkCount = 0;
    Boolean enableButton = false;
    String stringValueBac = "0.000";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("BAC Calculator");
        stringValueBac = "0.000";

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b_a_c_calc, container, false);

        //VIEW DRINKS BUTTON
        view.findViewById(R.id.viewDrinksButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drink.size() > 0){
                    bacCalcFragmentListener.sendDataToViewPage(drink);
                }else{
                    Toast.makeText(getActivity(), "There are no drinks to show !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // SET WEIGHT BUTTON
        view.findViewById(R.id.setButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bacCalcFragmentListener.goToSetWeightPage();
            }
        });

        //ADD DRINKS BUTTON
        view.findViewById(R.id.addDrinkButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bacCalcFragmentListener.goToAddDrinkPage();
            }
        });
        TextView weightTextView = view.findViewById(R.id.weightText);


        if (bacUser == null) {
            weightTextView.setText("N/A");
        } else {
            weightTextView.setText(bacUser.getWeight() + " lbs ( " + bacUser.getGender() + " )");
        }


            if(drink.size() > 0){
                calculateValues();
            }

            TextView bacValTextView = view.findViewById(R.id.bacLevelOutput);
            TextView drinkCount = view.findViewById(R.id.noOfDrinks);
            TextView text = view.findViewById(R.id.yourStatusDisplay);

            Button viewDrinkBtn = view.findViewById(R.id.viewDrinksButton);
            Button addDrinkBtn = view.findViewById(R.id.addDrinkButton);



            Integer a = 0;
            if (drink.size() > 0) {
                a = drink.size();
            }
            drinkCount.setText(a.toString());


            if (bac_value <= 0.08) {
                text.setText(R.string.your_safe);
                text.setBackgroundColor(Color.GREEN);
            } else if (bac_value <= 0.20) {
                text.setText(R.string.be_careful);
                text.setBackgroundColor(Color.parseColor("#ff9300"));
            } else {
                text.setText(R.string.over_limit);
                text.setBackgroundColor(Color.parseColor("#ff2600"));
            }



            bacValTextView.setText(stringValueBac.toString());
            Log.d(TAG, "onCreateView: "+stringValueBac.toString());
            Log.d(TAG, "onCreateView: -----");
            Log.d(TAG, "onCreateView: " + enableButton.toString());
            viewDrinkBtn.setEnabled(enableButton);
            addDrinkBtn.setEnabled(enableButton);

            if (bac_value >= 0.25) {
                Button addBtn = view.findViewById(R.id.addDrinkButton);
                addBtn.setEnabled(false);
                Toast.makeText(getActivity(), "No more drinks for you", Toast.LENGTH_SHORT).show();
            }
        //RESET BUTTON
        view.findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weightTextView.setText("N/A");
                bacValTextView.setText("0.000");
                drinkCount.setText("0");
                text.setText(R.string.your_safe);
                text.setBackgroundColor(Color.GREEN);
                drink.clear();
                enableButton = false;
                viewDrinkBtn.setEnabled(enableButton);
                addDrinkBtn.setEnabled(enableButton);


            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    BACCalcFragmentListener bacCalcFragmentListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof BACCalcFragmentListener){
            bacCalcFragmentListener = (BACCalcFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString()+"must implement listener");
        }
    }

    interface BACCalcFragmentListener{
        void goToSetWeightPage();
        void goToAddDrinkPage();
        void sendDataToViewPage(ArrayList<Drink> val);
    }

    public void setUserData(User u){
        this.bacUser = u;
        this.enableButton = true;
        this.drink.clear();
        this.bac_value = 0.000;
    }

    public void setDrinkData(ArrayList<Drink> h){
        this.drink = h;
        this.enableButton = true;
    }

    public void calculateValues(){
        Double constant;
        Double finalBAC = 0.000;
        Double totalA = 0.0;
        Double weightOfPerson;

        weightOfPerson = Double.parseDouble(bacUser.weight.toString());

        if (drink.size() > 0) {

            if (bacUser.gender == "MALE") {
                constant = 0.73;
            } else {
                constant = 0.66;
            }

            for (Drink a : drink) {
                totalA += (Double.parseDouble(String.valueOf(a.alcoholPercent)) * Double.parseDouble(String.valueOf(a.drinkSize)) / 100.0);
            }

            bac_value = (totalA * 5.14 / (weightOfPerson * constant));

            DecimalFormat df = new DecimalFormat("0.000");
            stringValueBac = df.format(bac_value).toString();

            Log.d(TAG, "calculateValues: Array->" + drink.toString());
            Log.d(TAG, "calculateValues: " + bac_value.toString());
            enableButton = true;
        }

    }

    public void getDrinkCount(){
        this.drinkCount = drink.size();
    }



}