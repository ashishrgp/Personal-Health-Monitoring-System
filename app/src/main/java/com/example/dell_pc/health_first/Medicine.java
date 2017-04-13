package com.example.dell_pc.health_first;

import com.google.firebase.database.IgnoreExtraProperties;
/**
 * Created by ashish pc on 25-Mar-17.
 */
@IgnoreExtraProperties
public class Medicine {
    private String medicineId;
    private String medicineName;

    public Medicine(){

    }
    public Medicine(String medicineId, String medicineName) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }
}

