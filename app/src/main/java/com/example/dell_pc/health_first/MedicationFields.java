package com.example.dell_pc.health_first;

/**
 * Created by Hamed on 3/26/2017.
 */

public class MedicationFields {
    String medicationName;
    String frequencies;
    String startDate;
    String endDate;
   /* int startMonth;
    int startDay;
    int startYear;
    int endMonth;
    int endDay;
    int endYear;  */


    public MedicationFields()
    {

    }


    public MedicationFields(String medicationName,String frequencies,String startDate,String endDate)
    {
        this.medicationName = medicationName;
        this.frequencies=frequencies;
        this.startDate = startDate;
        this.endDate=endDate;

        /* this.startMonth=startMonth;
        this.startDay=startDay;
        this.startYear=startYear;
        this.endMonth=endMonth;
        this.endDay=endDay;
        this.endYear=endYear;  */
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(String frequencies) {
        this.frequencies = frequencies;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

  /*  public int getStartMonth() {return startMonth;}

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartDay() {return startDay;}

    public void setStartDay(int startDay) {this.startDay = startDay;}

    public int getStartYear() {return startYear;}

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndMonth() {return endMonth;}

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getEndDay() {return endDay;}

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getEndYear() {return endYear;}

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }  */
}

