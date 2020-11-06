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


public class CgpaFragment extends Fragment {

    private LinearLayout layoutList;
    private final List<String> gradeList = new ArrayList<>();
    private final ArrayList<Credits> creditsList = new ArrayList<>();
    private double grade = 0.00;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cgpa, container, false);
        layoutList = root.findViewById(R.id.layout_list);
        Button buttonAdd = root.findViewById(R.id.button_add);
        Button buttonReset = root.findViewById(R.id.button_reset);
        Button buttonCalculate = root.findViewById(R.id.calculate_cgpa);
        gradeList.add("SELECT GPA");
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
        buttonAdd.setOnClickListener(v -> {
            addViewFun();
        });
        buttonReset.setOnClickListener(v -> {
            layoutList.removeAllViews();
            addViewFun();addViewFun();
        });

        buttonCalculate.setOnClickListener(v -> {
           checkAndRead();
           calculateCGPA();
        });

        return root;
    }
    private  void addViewFun(){
        View addTextView = getLayoutInflater().inflate(R.layout.row_edittext,null,false);
        EditText creditText = addTextView.findViewById(R.id.credit);
        AppCompatSpinner spinnerTeam = addTextView.findViewById(R.id.spinner_grade);
        ImageButton closeButton = addTextView.findViewById(R.id.remove);
        closeButton.setOnClickListener(v -> layoutList.removeView(addTextView));


        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), simple_spinner_item,gradeList);
        spinnerTeam.setAdapter(arrayAdapter);
        layoutList.addView(addTextView);
        checkAndRead();

    }

    private void checkAndRead() {

        creditsList.clear();
        for(int i=0;i<layoutList.getChildCount();i++){
            View addTextView = layoutList.getChildAt(i);
            EditText creditText = addTextView.findViewById(R.id.credit);
            AppCompatSpinner spinnerGrade = addTextView.findViewById(R.id.spinner_grade);
            Credits credits = new Credits();
            if(!creditText.getText().toString().equals("")){
                credits.setCredits(Double.parseDouble(creditText.getText().toString()));

            }else {
                Toast.makeText(getContext(),"Enter Credits", Toast.LENGTH_LONG).show();
            }

            spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){
                        case 1:
                            grade = 4.00;
                            break;

                        case 2:
                            grade = 3.75;
                            break;

                        case 3:
                            grade = 3.50;
                            break;

                        case 4:
                            grade = 3.25;
                            break;

                        case 5:
                            grade = 3.00;
                            break;

                        case 6:
                            grade =  2.75;
                            break;

                        case 7:
                            grade = 2.50;
                            break;

                        case 8:
                            grade = 2.25;
                            break;

                        case 9:
                            grade = 2.00;
                            break;
                        default:
                            grade = 0.00;
                    }

                    Toast.makeText(getContext(),"Grade Selected"+grade, Toast.LENGTH_LONG).show();

                    credits.setGrades(grade);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            creditsList.add(credits);
            credits.setGrades(grade);


        }

    }

    /*private double gradeChooser(int value) {
        switch (value){
            case 1:
                return 4.00;

            case 2:
                return 3.75;

            case 3:
                return 3.50;

            case 4:
                return 3.25;

            case 5:
                return 3.00;

            case 6:
                return 2.75;

            case 7:
                return 2.50;

            case 8:
                return 2.25;

            case 9:
                return 2.00;

        }
        return 0.00;

    }*/

    private void calculateCGPA(){
        checkAndRead();
        int totalCourse = creditsList.size();
        double totalCredits = 0.00;
        double totalPoints = 0.00;
        double CGPA;
        Credits temp ;

        for (int j=0;j<creditsList.size();j++){
            temp = creditsList.get(j);
            totalCredits= totalCredits + temp.getCredits();
            totalPoints = totalPoints + (temp.getGrades()*temp.getCredits());
        }

        CGPA = totalPoints/totalCredits;

        String cgpaText = String.format("CGPA: %.2f",CGPA);

        new ViewDialog().showDialog(getActivity(), totalCourse,String.valueOf(totalCredits),String.valueOf(totalPoints),cgpaText);
    }


}