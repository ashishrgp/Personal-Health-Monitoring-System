package com.example.dell_pc.health_first;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashish pc on 25-Mar-17.
 */

public class Reminder extends AppCompatActivity{
    public static final String Medicine_Name = "com.example.dell_pc.health_first.medicinename";
    public static final String Medicine_ID = "com.example.dell_pc.health_first.medicineid";

    EditText EditTextName;
    Button button8;
    public ListView ListView;

    List<Medicine> medicines;

    DatabaseReference databaseMedicines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder);
        databaseMedicines = FirebaseDatabase.getInstance().getReference("medicines");
        EditTextName = (EditText) findViewById(R.id.EditTextName);
        button8 = (Button) findViewById(R.id.button8);
        ListView = (ListView) findViewById(R.id.ListView);

        medicines = new ArrayList<>();

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMedicine();
            }
        });

    }
    private void addMedicine() {
        String name = EditTextName.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {
            String id = databaseMedicines.push().getKey();
            Medicine medicine = new Medicine(id, name);
            databaseMedicines.child(id).setValue(medicine);
            EditTextName.setText("");
            Toast.makeText(this, "Medicine added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please enter a medicine name", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseMedicines.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                medicines.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Medicine medicine = postSnapshot.getValue(Medicine.class);
                    medicines.add(medicine);
                }
                MedicineList medicineAdapter = new MedicineList(Reminder.this, medicines);
                ListView.setAdapter(medicineAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
