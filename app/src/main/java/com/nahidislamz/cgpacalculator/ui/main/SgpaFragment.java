package com.nahidislamz.cgpacalculator.ui.main;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nahidislamz.cgpacalculator.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_item;

public class SgpaFragment extends Fragment {

    LinearLayout layoutList;
    Button buttonAdd,buttonReset;
    Button buttonCalculate;
    List<String> gradeList = new ArrayList<>();
    ArrayList<Grades> gradesArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_sgpa, container, false);
        layoutList = root.findViewById(R.id.layout_list);
        buttonAdd = root.findViewById(R.id.button_add);
        buttonReset = root.findViewById(R.id.button_reset);
        buttonCalculate = root.findViewById(R.id.calculate_cgpa);
        gradeList.add("A+  (4.00)");
        gradeList.add("A  (3.75)");
        gradeList.add("A-  (3.50)");
        gradeList.add("B+  (3.25)");
        gradeList.add("B  (3.00)");
        gradeList.add("B-  (2.75)");
        gradeList.add("C+  (2.50)");
        gradeList.add("C  (2.25)");
        gradeList.add("D  (2.00)");
        gradeList.add("F  (0.00)");
        addViewFun();addViewFun();

        buttonAdd.setOnClickListener((View.OnClickListener) v -> {
            addViewFun();
        });
        buttonReset.setOnClickListener((View.OnClickListener) v -> {
            layoutList.removeAllViews();
            addViewFun();addViewFun();
        });

        return root;
    }
    private  void addViewFun(){
        View addTextView = getLayoutInflater().inflate(R.layout.row_edittext,null,false);
        EditText creditText = (EditText)addTextView.findViewById(R.id.credit);
        AppCompatSpinner spinnerTeam = (AppCompatSpinner)addTextView.findViewById(R.id.spinner_grade);
        ImageButton closeButton = (ImageButton) addTextView.findViewById(R.id.remove);
        closeButton.setOnClickListener(v -> layoutList.removeView(addTextView));


        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), simple_spinner_item,gradeList);
        spinnerTeam.setAdapter(arrayAdapter);
        layoutList.addView(addTextView);

    }

    private boolean checkIfValidAndRead() {
        boolean result = true;
        gradesArrayList.clear();
        for(int i=0;i<layoutList.getChildCount();i++){

            View addTextView = layoutList.getChildAt(i);
            EditText creditText = (EditText)addTextView.findViewById(R.id.credit);
            AppCompatSpinner spinnerTeam = (AppCompatSpinner)addTextView.findViewById(R.id.spinner_grade);
            Grades grades = new Grades();





        }

        if(gradesArrayList.size()==0){
            result = false;
            Toast.makeText(getContext(), "Add Cricketers First!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(getContext(), "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }


        return result;
    }




}