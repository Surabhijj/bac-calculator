/*
ASSIGNMENT NUMBER : Home Work 4
NAME : Deep Rajesh Furiya
       Surabhi Jagadeesh
*/
package com.example.group10hw03;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ViewDrinkFragment extends Fragment {


    private static final String DRINKS_KEY ="DRINKS_KEY";
    private ArrayList<Drink> drinks;

    RecyclerView recyclerView;

    ViewDrinkRecyclerViewAdapter adapater;
    RecyclerView.LayoutManager layoutManager;


    public ViewDrinkFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ViewDrinkFragment newInstance(ArrayList<Drink> drinks) {
        ViewDrinkFragment fragment = new ViewDrinkFragment();
        Bundle args = new Bundle();
        args.putSerializable(DRINKS_KEY, drinks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drinks = (ArrayList<Drink>) getArguments().getSerializable(DRINKS_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_drink, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("View Drinks");
        recyclerView = view.findViewById(R.id.recyclerViewContainer);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        view.findViewById(R.id.sortAlcoholAsc).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Collections.sort(drinks, new Drink());
                adapater.notifyDataSetChanged();
                Log.d("DEMO", "Sorting Alcohol Ascending");
            }
        });

        view.findViewById(R.id.sortAlcoholDesc).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Collections.sort(drinks, new Drink().reversed());
                adapater.notifyDataSetChanged();
                Log.d("DEMO", "Sorting Alcohol Descending");
            }
        });

        view.findViewById(R.id.sortDateAddedAsc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(drinks, new Comparator<Drink>() {
                    @Override
                    public int compare(Drink d1, Drink d2) {
                        return d1.timestamp.compareTo(d2.timestamp);
                    }
                });
                adapater.notifyDataSetChanged();
                Log.d("DEMO", "Sorting Date Ascending");
            }
        });

        view.findViewById(R.id.sortDateAddedDesc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(drinks, new Comparator<Drink>() {
                    @Override
                    public int compare(Drink d1, Drink d2) {
                        return d2.timestamp.compareTo(d1.timestamp);
                    }
                });
                adapater.notifyDataSetChanged();
                Log.d("DEMO", "Sorting Date Descending");
            }
        });

        adapater = new ViewDrinkRecyclerViewAdapter(drinks);
        recyclerView.setAdapter(adapater);
    }

    viewDrink vlistener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof viewDrink) {
            vlistener = (viewDrink) context;
        }
    }
    public interface viewDrink {
        void newBACLevel(ArrayList<Drink> updatedDrinks);
    }
}