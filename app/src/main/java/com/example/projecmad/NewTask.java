package com.example.projecmad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.projecmad.Model.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class NewTask extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    ImageView imageView;
    EditText editText;
    TextView tvDate;
    TextView timetv;

    ImageButton btPickDate;
    ImageButton btPickTime;
    private TimePickerDialog.OnTimeSetListener timePickerDialogListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    // logic to properly handle
                    // the picked timings by user
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

                    timetv.setText(formattedTime);
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        imageView =(ImageView) findViewById(R.id.imageView2);
        tvDate = (TextView) findViewById(R.id.dateTv);
        timetv =(TextView) findViewById(R.id.timetv);
        btPickDate = (ImageButton) findViewById(R.id.calenderBtn);
         btPickTime = (ImageButton) findViewById(R.id.timeBtn);
        timetv = (TextView) findViewById(R.id.timetv);

        btPickTime.setOnClickListener(view -> {
            TimePickerDialog timePicker = new TimePickerDialog(
                    // pass the Context
                    this,
                    // listener to perform task
                    // when time is picked
                    timePickerDialogListener,
                    // default hour when the time picker
                    // dialog is opened
                    12,
                    // default minute when the time picker
                    // dialog is opened
                    10,
                    // 24 hours time picker is
                    // false (varies according to the region)
                    false
            );

            // then after building the timepicker
            // dialog show the dialog to user
            timePicker.show();
        });

        timetv.setVisibility(View.GONE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainPage();
            }
        });

        btPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.projecmad.Model.DatePicker mDatePickerDialogFragment;
                //tutorials.droid.datepicker.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new com.example.projecmad.Model.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });


    }




    private void openMainPage() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        tvDate.setText(selectedDate);
        timetv.setVisibility(View.VISIBLE);
    }
}