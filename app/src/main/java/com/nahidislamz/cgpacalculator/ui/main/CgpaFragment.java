package com.nahidislamz.cgpacalculator.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
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

    LinearLayout layoutList;
    Button buttonAdd,buttonReset;
    Button buttonCalculate;
    List<String> gradeList = new ArrayList<>();
    ArrayList<Credits> creditsList = new ArrayList<>();
    float grade = (float) 0.00;
    float totalCredits = (float) 0.00;
    float CGPA = (float) 0.00;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cgpa, container, false);
        layoutList = root.findViewById(R.id.layout_list);
        buttonAdd = root.findViewById(R.id.button_add);
        buttonReset = root.findViewById(R.id.button_reset);
        buttonCalculate = root.findViewById(R.id.calculate_cgpa);
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
        buttonAdd.setOnClickListener((View.OnClickListener) v -> {
            addViewFun();
        });
        buttonReset.setOnClickListener((View.OnClickListener) v -> {
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
        EditText creditText = (EditText)addTextView.findViewById(R.id.credit);
        AppCompatSpinner spinnerTeam = (AppCompatSpinner)addTextView.findViewById(R.id.spinner_grade);
        ImageButton closeButton = (ImageButton) addTextView.findViewById(R.id.remove);
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
            EditText creditText = (EditText)addTextView.findViewById(R.id.credit);
            AppCompatSpinner spinnerGrade = (AppCompatSpinner)addTextView.findViewById(R.id.spinner_grade);
            Credits credits = new Credits();
            if(!creditText.getText().toString().equals("")){
                credits.setCredits(Float.parseFloat(creditText.getText().toString()));

            }else {
                Toast.makeText(getContext(),"Enter Credits", Toast.LENGTH_LONG).show();
            }

            spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    grade = gradeChooser(position);
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

    private float gradeChooser(int value) {
        switch (value){
            case 1:
                return (float) 4.00;

            case 2:
                return (float) 3.75;

            case 3:
                return (float) 3.50;

            case 4:
                return (float) 3.25;

            case 5:
                return (float) 3.00;

            case 6:
                return (float) 2.75;

            case 7:
                return (float) 2.50;

            case 8:
                return (float) 2.25;

            case 9:
                return (float) 2.00;

        }
        return 0;

    }

    private void calculateCGPA(){
        checkAndRead();
        Credits temp ;
        int totalCourse = creditsList.size();
        float totalPoints = (float) 0.00;
        totalCredits = (float) 0.00;
        CGPA = (float) 0.00;
        for (int j=0;j<creditsList.size();j++){
            temp = creditsList.get(j);
            totalCredits+=temp.getCredits();
            totalPoints += temp.getCredits()*temp.getGrades();
        }

        CGPA = (float) (totalPoints/totalCredits);

        String cgpaText = String.format("CGPA: %.2f",CGPA);

        /*new AlertDialog.Builder(getContext())
                .setTitle("CGPA CALCULATOR")
                .setMessage("Total Course: \t"+totalCourse+"\n\nTotal Credits : \t"+totalCredits+
                        "\n\nTotal Points : \t"+ totalPoints+"\n\n______________________________\n\n  \t\t"+ cgpaText )
                .setCancelable(false)
                .setPositiveButton("Ok", (dialog, which) -> {
                   dialog.dismiss();
                })
                .show();*/
        new ViewDialog().showDialog(getActivity(), totalCourse,String.valueOf(totalCredits),String.valueOf(totalPoints),cgpaText);
    }


}