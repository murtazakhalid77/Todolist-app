package com.example.projecmad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class NewTask extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    ImageView backbuttonImageView;
    TextView tvDate;
    TextView tvTime;
    ImageView btPickDate;
    ImageView btPickTime;
    Spinner spinner;

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

                    tvTime.setText(formattedTime);
                }
            };

    private void openMainPage() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        backbuttonImageView =(ImageView) findViewById(R.id.imageView2);
        tvDate = (TextView) findViewById(R.id.dateTv);
        tvTime =(TextView) findViewById(R.id.timetv);    // getting all the elements
        btPickDate = (ImageView) findViewById(R.id.calenderBtn);
        btPickTime = (ImageView) findViewById(R.id.timeBtn);
        spinner = (Spinner) findViewById(R.id.dropdown_menu_list);

        tvTime.setVisibility(View.GONE);      //time text view and images not visible
        btPickTime.setVisibility(View.GONE);

        backbuttonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainPage();
            }
        });

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
    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        tvDate.setText(selectedDate);
        tvTime.setVisibility(View.VISIBLE);
        btPickTime.setVisibility(View.VISIBLE);
    }
}