/*
ASSIGNMENT NUMBER : Home Work 4
NAME : Deep Rajesh Furiya
       Surabhi Jagadeesh
*/



package com.example.group10hw03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BACCalcFragment.BACCalcFragmentListener, SetProfileFragment.SetProfileListener, AddDrinkFragment.AddDrinkListener,ViewDrinkFragment.viewDrink {

    User profile;
    String TAG = "deep";
    static public HashMap<Integer,ArrayList> drinkData = new HashMap<>();
    static public ArrayList<Drink> drinkDataNew = new ArrayList<>();

    static public Integer counterForKey = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView,new BACCalcFragment(),"bac_frag")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSetWeightPage() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView,new SetProfileFragment(),"profile_frag")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToAddDrinkPage() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView,new AddDrinkFragment())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void sendDataToViewPage(ArrayList<Drink> val) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView,new ViewDrinkFragment().newInstance(val))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setNewWeight(User user) {
        profile = user;
        BACCalcFragment bacFrag = (BACCalcFragment) getSupportFragmentManager().findFragmentByTag("bac_frag");
        if (bacFrag != null) {
            bacFrag.setUserData(user);
        }
        cancelNewWeight();
    }

    @Override
    public void cancelNewWeight() {
        getSupportFragmentManager().popBackStackImmediate();
    }


    @Override
    public void cancelAddNewDrink() {
        getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void addDrink(Drink drink) {

        drinkDataNew.add(drink);
        BACCalcFragment bac_frag = (BACCalcFragment) getSupportFragmentManager().findFragmentByTag("bac_frag");
        if (bac_frag != null) {
            bac_frag.setDrinkData(drinkDataNew);
            cancelAddNewDrink();
        }
    }





    @Override
    public void newBACLevel(ArrayList<Drink> updatedDrinks) {
        getSupportFragmentManager().popBackStackImmediate();

        BACCalcFragment bac_frag = (BACCalcFragment) getSupportFragmentManager().findFragmentByTag("bac_frag");
        if (bac_frag != null) {
            bac_frag.setDrinkData(updatedDrinks);
        }
    }
}