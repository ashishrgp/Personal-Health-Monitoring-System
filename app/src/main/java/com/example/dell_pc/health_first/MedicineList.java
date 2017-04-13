package com.example.dell_pc.health_first;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;
import java.util.List;

/**
 * Created by ashish pc on 25-Mar-17.
 */

public class MedicineList extends ArrayAdapter<Medicine>{
    private Activity context;
    List<Medicine> medicines;

    public MedicineList(Activity context, List<Medicine> medicines){
        super(context, R.layout.medicine_list, medicines);
        this.context = context;
        this.medicines = medicines;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.medicine_list, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        Medicine medicine = medicines.get(position);
        textViewName.setText(medicine.getMedicineName());
        return listViewItem;
    }
}
