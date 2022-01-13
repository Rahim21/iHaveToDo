package com.example.iHaveToDo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;

public class NotificationMessage extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    ThemedButton btn_history;
    ThemedButton b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_message);
        textView = findViewById(R.id.tv_message);
        Bundle bundle = getIntent().getExtras();
        textView.setText(bundle.getString("message"));
         b1=findViewById(R.id.b1);
         b2=findViewById(R.id.b2);
         b1.setOnClickListener(this);
         b2.setOnClickListener(this);
        btn_history = findViewById(R.id.history);
        btn_history.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if(view == btn_history){
            Intent intent = new Intent(NotificationMessage.this, com.example.iHaveToDo.MainActivity.class);
            startActivity(intent);
        }
        else if (view==b1)
        {
            Intent in1=new Intent(NotificationMessage.this,CreateEvent.class);
            startActivity(in1);
        }
        else if (view==b2)
        {
            Intent in1=new Intent(NotificationMessage.this,CreateEvent.class);
            startActivity(in1);
        }

    }
}
