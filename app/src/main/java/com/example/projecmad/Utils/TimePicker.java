package com.example.projecmad.Utils;

import android.app.TimePickerDialog;
import android.widget.TextView;

public class TimePicker  implements TimePickerDialog.OnTimeSetListener{
    private TextView tvTime;

    public TimePicker(TextView tvTime) {
        this.tvTime = tvTime;
    }

    @Override
    public void onTimeSet(android.widget.TimePicker timePicker, int hourOfDay, int minute) {
        String formattedTime;
        if (hourOfDay == 0) {
            if (minute < 10) {
                formattedTime = hourOfDay + 12 + ":0" + minute + " am";
            } else {
                formattedTime = hourOfDay + 12 + ":" + minute + " am";
            }
        } else if (hourOfDay > 12) {
            if (minute < 10) {
                formattedTime = hourOfDay - 12 + ":0" + minute + " pm";
            } else {
                formattedTime = hourOfDay - 12 + ":" + minute + " pm";
            }
        } else if (hourOfDay == 12) {
            if (minute < 10) {
                formattedTime = hourOfDay + ":0" + minute + " pm";
            } else {
                formattedTime = hourOfDay + ":" + minute + " pm";
            }
        } else {
            if (minute < 10) {
                formattedTime = hourOfDay + ":" + minute + " am";
            } else {
                formattedTime = hourOfDay + ":" + minute + " am";
            }
        }

        tvTime.setText(formattedTime);
    }
}

