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
        View addTextView = getLayoutInflater().inflate(R.layout.row_edittext_sgpa,null,false);
        EditText creditText = (EditText)addTextView.findViewById(R.id.credit);
        EditText SGPAText = (EditText)addTextView.findViewById(R.id.sgpa);
        ImageButton closeButton = (ImageButton) addTextView.findViewById(R.id.remove);
        closeButton.setOnClickListener(v -> layoutList.removeView(addTextView));
        layoutList.addView(addTextView);

    }
}