package com.nahidislamz.cgpacalculator.ui.main;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nahidislamz.cgpacalculator.R;

public class ViewDialog {
    public void showDialog(Activity activity, int total_courses, String total_credits, String total_grades, String total_cgpa) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        TextView total_course = (TextView) dialog.findViewById(R.id.course);
        TextView total_credit = (TextView) dialog.findViewById(R.id.credit);
        TextView total_point = (TextView) dialog.findViewById(R.id.point);
        TextView cgpa = (TextView) dialog.findViewById(R.id.cgpa);
        total_course.setText("Total Course :"+total_courses);
        total_credit.setText("Total Credits :"+total_credits);
        total_point.setText("Total Points :"+total_grades);
        cgpa.setText(total_cgpa);
        ((Button) dialog.findViewById(R.id.okButton)).setOnClickListener((View.OnClickListener) v -> dialog.dismiss());
        dialog.show();
    }


}