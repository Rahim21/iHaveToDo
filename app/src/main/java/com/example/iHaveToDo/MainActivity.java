package com.example.iHaveToDo;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.iHaveToDo.Adapter.EventAdapter;
import com.example.iHaveToDo.Database.DatabaseClass;
import com.example.iHaveToDo.Database.EntityClass;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EventAdapter eventAdapter;
    RecyclerView recyclerview;
    DatabaseClass databaseClass;

//   ImageButton supprime le main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = findViewById(R.id.recyclerview);
        databaseClass = DatabaseClass.getDatabase(getApplicationContext());
        recyclerview.setAdapter(eventAdapter);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings2:
                delete_main();
                Toast.makeText(MainActivity.this, getResources().getString(R.string.todo_deleted), Toast.LENGTH_SHORT).show();
                recreate();
                break;

        }
        return true;
    }

    public void delete_main() {
        databaseClass.EventDao().deleteData(true);
//        databaseClass.EventDao().deleteData(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();

    }

    private void setAdapter() {
//        List<EntityClass> classList = databaseClass.EventDao().getDone(false);
        List<EntityClass> classList = databaseClass.EventDao().getDone(true);
        eventAdapter = new EventAdapter(getApplicationContext(), classList);
        recyclerview.setAdapter(eventAdapter);

        StringBuilder sb = new StringBuilder();
        for (EntityClass entityClass : classList) {
            sb.append(entityClass.getEventdate() + "  ");
        }
        Log.e(" setAdapter: ", sb.toString());
    }

}