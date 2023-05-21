package com.example.projecmad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projecmad.Model.Task;
import com.example.projecmad.Service.ListService;
import com.example.projecmad.Service.TaskService;
import com.example.projecmad.Utils.DatePicker;
import com.example.projecmad.Utils.MyArrayAdapter;
import com.example.projecmad.Utils.TimePicker;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class NewTask extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    ImageView backbuttonImageView;
    ImageView saveButton;
    EditText taskNameTextBox;
    EditText commentTextBox;
    TextView tvDate;
    TextView tvTime;
    ImageView btPickDate;
    ImageView btPickTime;
    Spinner spinner;
    CheckBox checkBox;


    public static String taskName;
    public static String comment;
    public static String dueDate;
    public static String time;
    public  static String listNameToBeAssigned;

    private void openMainPage() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        taskNameTextBox = (EditText) findViewById(R.id.taskNameTv);
        commentTextBox =(EditText) findViewById(R.id.taskCommentTv);

        backbuttonImageView =(ImageView) findViewById(R.id.imageView2);
        tvDate = (TextView) findViewById(R.id.dateTv);
        tvTime =(TextView) findViewById(R.id.timetv);    // getting all the elements
        btPickDate = (ImageView) findViewById(R.id.calenderBtn);
        btPickTime = (ImageView) findViewById(R.id.timeBtn);
        spinner = (Spinner) findViewById(R.id.dropdown_menu_list);
        checkBox =(CheckBox) findViewById(R.id.checkBox);

        saveButton = (ImageView) findViewById(R.id.SaveAll);

        tvTime.setVisibility(View.GONE);      //time text view and images not visible
        btPickTime.setVisibility(View.GONE);

//        MainActivity mainActivity = new MainActivity();
//        mainActivity.getListNames();

        TimePicker timePicker = new TimePicker(tvTime);

        backbuttonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainPage();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.SaveAll){
                    try{
                        taskName=taskNameTextBox.getText().toString();
                        comment = commentTextBox.getText().toString();
                        dueDate =tvDate.getText().toString();
                        time =tvTime.getText().toString();
                        listNameToBeAssigned=spinner.getSelectedItem().toString();

                        Task task = new Task(taskName,comment,checkBox.isChecked(),dueDate,time);
                        TaskService taskService = new TaskService();
                        if (!taskService.saveTask(task)){
                            Toast.makeText(getApplicationContext(), "Tasked saved", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Tasked alredy present", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    taskNameTextBox.getText().clear();
                    commentTextBox.getText().clear();
                    tvDate.setText("");
                    tvTime.setText("");
                    spinner.setSelection(0);
                  }

                }

        });

        btPickTime.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    this,
                    timePicker,
                    Calendar.HOUR_OF_DAY,
                    Calendar.MINUTE,
                    false
            );
            timePickerDialog.show();
        });
        btPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker mDatePickerDialogFragment;
                //tutorials.droid.datepicker.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

        if (spinner != null) {
//             Create the ArrayAdapter with the appropriate context
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ListService().getListNames());
            ListService listService = new ListService();
            MyArrayAdapter adapter = new MyArrayAdapter(this,listService);
//             Set the adapter for the spinner
            spinner.setAdapter(adapter);
        }
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