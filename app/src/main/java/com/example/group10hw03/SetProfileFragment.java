/*
ASSIGNMENT NUMBER : Home Work 4
NAME : Deep Rajesh Furiya
       Surabhi Jagadeesh
*/

package com.example.group10hw03;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SetProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment setProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetProfileFragment newInstance(String param1, String param2) {
        SetProfileFragment fragment = new SetProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    EditText weight;
    RadioGroup radioGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_set_profile, container, false);

        v.findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileListener.cancelNewWeight();
            }
        });

        v.findViewById(R.id.setButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                weight = v.findViewById(R.id.enterWeight);
                radioGroup = v.findViewById(R.id.genderRadio);

                String gender = null;
                Integer weightVal;

                if (weight.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter weight", Toast.LENGTH_SHORT).show();
                }else {
                    Integer proceed = 1;
                    if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonMale) {
                        gender = "Male";
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonFemale) {
                        gender = "Female";
                    } else {
                        proceed = 0;
                        Toast.makeText(getActivity(), "Please select gender", Toast.LENGTH_SHORT).show();
                    }

                    weightVal = Integer.parseInt(weight.getText().toString());

                    if (weightVal <= 0){
                        proceed = 0;
                        Toast.makeText(getActivity(), "Please enter proper weight", Toast.LENGTH_SHORT).show();
                    }

                    if (proceed != 0){
                        User user = new User(gender, weightVal);
                        profileListener.setNewWeight(user);
                    }

                }
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SetProfileListener){
            profileListener = (SetProfileListener) context;
        }else{
            throw new RuntimeException(context.toString() + " must impelent SetProfileListener");
        }
    }

    SetProfileListener profileListener;

    public interface SetProfileListener{
        void setNewWeight(User user);
        void cancelNewWeight();
    }
}