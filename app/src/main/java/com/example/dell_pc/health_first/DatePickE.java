package com.example.dell_pc.health_first;

//Refrence:https://android--examples.blogspot.com/2015/05/how-to-use-datepickerdialog-in-android.html
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.widget.TextView;
import android.widget.DatePicker;
import android.app.Dialog;
import java.util.Calendar;
public class DatePickE extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        //Do something with the date chosen by the user
        TextView tv2 = (TextView) getActivity().findViewById(R.id.edEndDate);
        int m=month+1;
        String stringOfDate = m + "-" + day + "-" + year;//m + "/" + day + "/" + year;
        tv2.setText(stringOfDate);
    }
}
