package com.nahidislamz.cgpacalculator.ui.main;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    ArrayList <Semister> semisterArrayList = new ArrayList<>();
    float totalCredits = (float) 0.00;
    float totalSGPA = (float) 0.00;
    float sGPA = (float) 0.00;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cgpa, container, false);
        layoutList = root.findViewById(R.id.layout_list);
        buttonAdd = root.findViewById(R.id.button_add);
        buttonReset = root.findViewById(R.id.button_reset);
        buttonCalculate = root.findViewById(R.id.calculate_cgpa);

        addViewFun();addViewFun();

        buttonAdd.setOnClickListener(v -> {
            addViewFun();
        });
        buttonReset.setOnClickListener( v -> {
            layoutList.removeAllViews();
            addViewFun();addViewFun();
        });

        buttonCalculate.setOnClickListener(v ->{
            calculateCGPA();
        });

        return root;
    }
    private  void addViewFun(){
        View addTextView = getLayoutInflater().inflate(R.layout.row_edittext_sgpa,null,false);
        EditText creditText = addTextView.findViewById(R.id.credit);
        EditText SGPAText = addTextView.findViewById(R.id.sgpa);
        ImageButton closeButton = addTextView.findViewById(R.id.remove);
        closeButton.setOnClickListener(v -> layoutList.removeView(addTextView));
        layoutList.addView(addTextView);
        checkAndRead();

    }


    private void checkAndRead() {
        semisterArrayList.clear();
        for(int i=0;i<layoutList.getChildCount();i++){
            View addTextView = layoutList.getChildAt(i);
            EditText creditText = addTextView.findViewById(R.id.credit);
            EditText sGPAText = addTextView.findViewById(R.id.sgpa);

            Semister semister = new Semister();
            if(!creditText.getText().toString().equals("")){
                semister.setCredits(Float.parseFloat(creditText.getText().toString()));
                semister.setsGPA(Float.parseFloat(sGPAText.getText().toString()));

            }else {
                Toast.makeText(getContext(),"Enter All Fields Correctly",Toast.LENGTH_LONG).show();
            }

            semisterArrayList.add(semister);
        }
    }

    private void calculateCGPA(){
        checkAndRead();
        Semister temp ;
        int totalCourse = semisterArrayList.size();
        totalCredits = (float) 0.00;
        totalSGPA = (float) 0.00;
        sGPA = (float) 0.00;

        for (int j=0;j<semisterArrayList.size();j++){
            temp = semisterArrayList.get(j);
            totalCredits+=temp.getCredits();
            totalSGPA += temp.getsGPA();
        }

        sGPA = (totalCredits/totalSGPA);

        String sgpaText = String.format("SGPA: %.2f",sGPA);

        new ViewDialog().showSemisterDialog(getActivity(), totalCourse,String.valueOf(totalCredits),String.valueOf(totalSGPA),sgpaText);
    }
}