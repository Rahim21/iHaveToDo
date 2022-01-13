package com.example.iHaveToDo;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iHaveToDo.Database.DatabaseClass;
import com.example.iHaveToDo.Database.EntityClass;
import com.example.iHaveToDo.Sorting.vertical.VerticalLinearRecyclerViewSampleActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;


public class CreateEvent extends AppCompatActivity implements View.OnClickListener {

    private Button btnspeak;

    ThemedButton btn_time, btn_date;

    Button btn_done;

    ImageButton btn_recycle_view;
    EditText editext_message;
    String timeTonotify;
    DatabaseClass databaseClass;
    TextView timeText, dateText;
    Timer timer = new Timer();
    TimerTask tt;
    private String value, timeSch;
    Calendar calendarSch = Calendar.getInstance(); // c'est cette méthode que nous allons utiliser au lieu de Date().


    private static final String SAVE_INSTANCE_TAGS_LAYOUT_EXPANDED = "com.example.reminderapp.TAGS_LAYOUT_EXPANDED";
    private boolean isTimeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        ThemedToggleButtonGroup themedButtonGroup = findViewById(R.id.timegrouping);
        themedButtonGroup.setOnSelectListener((ThemedButton btn) -> {
            // bouton select

            return kotlin.Unit.INSTANCE;
        });
        List<ThemedButton> selectedButtons = themedButtonGroup.getSelectedButtons();
// get all buttons
        List<ThemedButton> allButtons = themedButtonGroup.getButtons();
// get all unselected buttons
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            List<ThemedButton> unselectedButtons = allButtons.stream().filter(btn -> !btn.isSelected()).collect(Collectors.toList());
        }


        tt = new TimerTask() {
            public void run() {
                // obtenir l'heure d'une journée
                Calendar calendar = Calendar.getInstance(); // c'est cette méthode que nous allons utiliser au lieu de Date() également.

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                String time = FormatTime(hour, minute);
//                if(time.equals(timeSch)){
//                    //System.out.println("doing the scheduled task");
//                    ExampleBottomSheetDialog bottomSheet = new ExampleBottomSheetDialog();
//                    bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
//                }
            }
        };
        timer.schedule(tt, 1000, 1000);


        btn_recycle_view = findViewById(R.id.recyclerview);


        btnspeak = findViewById(R.id.btn_speak);
        btn_time = findViewById(R.id.btn_time);
        btn_date = findViewById(R.id.btn_date);
        btn_done = findViewById(R.id.btn_done);


        editext_message = findViewById(R.id.editext_message);

        btn_recycle_view.setOnClickListener(this);

        btn_time.setOnClickListener(this);

        btn_date.setOnClickListener(this);

        btn_done.setOnClickListener(this);

        databaseClass = DatabaseClass.getDatabase(getApplicationContext());

        timeText = findViewById(R.id.showTime);
        dateText = findViewById(R.id.showDate);
        Bundle bundle = getIntent().getBundleExtra("Goal");

        Intent intent1 = getIntent();
        String message1 = intent1.getStringExtra("message");


        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        String name = mPreferences.getString("FGoal", "");
        Boolean voice = true;
        voice = mPreferences.getBoolean("voice", false);
        Log.e("main", name);


        if (name.contains("abc")) {
            getSpeechInput();


        }


        btnspeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpeechInput();
            }
        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.nav_drawer, menu);
//        return super.onCreateOptionsMenu(menu);
//
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId())
//        {
//            case R.id.action_settings:
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                break;
//
//        }
//        return true;
//    }


    @Override
    public void onClick(View view) {
        if (view == btn_time) {
            selectTime();
        } else if (view == btn_date) {
            selectDate();
        } else if (view == btn_recycle_view) {
            Intent intent = new Intent(this, com.example.iHaveToDo.MainActivity.class);
            startActivity(intent);
        } else if (view == btn_done) {
            submit();
        }

    }


    private void submit() {

        String text = editext_message.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(this, "Please Enter the text", Toast.LENGTH_SHORT).show();
        } else {
            if (timeText.getText().toString().equals("Select Time") || dateText.getText().toString().equals("Select date")) {
                Toast.makeText(this, "Please select date and time", Toast.LENGTH_SHORT).show();
            } else {

                EntityClass entityClass = new EntityClass();
                String value = (editext_message.getText().toString().trim());
                String date = (dateText.getText().toString().trim());
                String time = (timeText.getText().toString().trim());
                entityClass.setEventdate(date);
                entityClass.setEventname(value);
                entityClass.setEventtime(time);
//                entityClass.setId(1);
//                databaseClass.EventDao().update(true,"asddsfdsf",1);
                databaseClass.EventDao().addAlarm(entityClass);
                setAlarm(value, date, time, 1);
            }
        }

        Toast.makeText(this, getResources().getString(R.string.to_do_added), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, VerticalLinearRecyclerViewSampleActivity.class);
        startActivity(intent);
        finish();
    }


    private void selectTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeTonotify = i + ":" + i1;
                timeText.setText(FormatTime(i, i1));
            }
        }, hour, minute, false);
        timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                isTimeSelected = true;
            }
        });
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int which) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        isTimeSelected = true;
                    }
                }
            });

            timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int which) {
                    if (which == DialogInterface.BUTTON_NEGATIVE) {
                        isTimeSelected = false;
                    }
                }
            });

            timePickerDialog.setCancelable(false);
            timePickerDialog.setTitle("Select Time");
        }
        timePickerDialog.show();

    }


    private void selectDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dateText.setText(day + "-" + (month + 1) + "-" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }


    public String FormatTime(int hour, int minute) {

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }


        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }


        return time;
    }


    private void setAlarm(String text, String date, String time, int id) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(), AlarmBrodcast.class);
        intent.putExtra("event", text);
        intent.putExtra("time", date);
        intent.putExtra("date", time);
//        intent.putExtra("id",id);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = date + " " + timeTonotify;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date date1 = formatter.parse(dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        finish();

    }

    public void getSpeechInput() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    editext_message.setText(result.get(0));

                    SharedPreferences mPreferencess = PreferenceManager.getDefaultSharedPreferences(this);
                    //mPreferences = getSharedPreferences("tabian.com.sharedpreferencestest", Context.MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = mPreferencess.edit();
                    // mEditor.putString("FGoal", "zzz");
                    mEditor.remove("FGoal").apply();
                    Log.e("m", mEditor.toString());
                    // mEditor.apply();

                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("save", MODE_PRIVATE);
        boolean switchState = prefs.getBoolean("value", false);


        SharedPreferences prefs1 = getSharedPreferences("saveOn", MODE_PRIVATE);
        boolean switchState1 = prefs1.getBoolean("valueOn", false);
        if (switchState1) {
            //  timer.schedule(tt, 1000, 1000);
        }


        SharedPreferences srSchedule = getSharedPreferences("schdlChanged", MODE_PRIVATE);
        // editor1.putBoolean("yes",true);
        String DSchedule = srSchedule.getString("schdlvalue", "9:00 AM");
        timeSch = DSchedule;


        SharedPreferences sr3 = getSharedPreferences("workChanged", MODE_PRIVATE);


        SharedPreferences sr6 = getSharedPreferences("workChanged3", MODE_PRIVATE);


        SharedPreferences sr7 = getSharedPreferences("workChanged4", MODE_PRIVATE);

    }


}