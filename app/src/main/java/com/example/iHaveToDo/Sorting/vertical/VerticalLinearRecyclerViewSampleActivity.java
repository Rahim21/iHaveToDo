package com.example.iHaveToDo.Sorting.vertical;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iHaveToDo.Adapter.EventAdapter;
import com.example.iHaveToDo.CreateEvent;
import com.example.iHaveToDo.Database.DatabaseClass;
import com.example.iHaveToDo.Database.EntityClass;
import com.example.iHaveToDo.MainActivity;
import com.example.iHaveToDo.R;
import com.example.iHaveToDo.Sorting.Library.ExpandableRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class VerticalLinearRecyclerViewSampleActivity extends AppCompatActivity {

    private ToDoAdapter mAdapter;
    DatabaseClass databaseClass;
    List<EntityClass> sortingList;
    List<EntityClass> todayList;
    List<EntityClass> tomorrowList;
    List<EntityClass> upcomingList;
    List<EntityClass> historyList;
    EventAdapter eventAdapter;
    boolean expandbool=true;
    private CreateEvent createEvent;


    @NonNull
    public static Intent newIntent(Context context) {
        return new Intent(context, VerticalLinearRecyclerViewSampleActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_sample);


        FloatingActionButton fab = findViewById(R.id.btn_createEvent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(VerticalLinearRecyclerViewSampleActivity.this,CreateEvent.class);
                startActivity(i);
            }
        });

        todayList = new ArrayList<EntityClass>();
        tomorrowList = new ArrayList<EntityClass>();
        upcomingList = new ArrayList<EntityClass>();
        historyList = new ArrayList<EntityClass>();

        databaseClass = DatabaseClass.getDatabase(getApplicationContext());
        sortingList = databaseClass.EventDao().getDone(false);
//
//        Ingredient beef = new Ingredient("beef", false);
//        Ingredient cheese = new Ingredient("cheese", true);
//        Ingredient salsa = new Ingredient("salsa", true);
//        Ingredient tortilla = new Ingredient("tortilla", true);
//        Ingredient ketchup = new Ingredient("ketchup", true);
//        Ingredient bun = new Ingredient("bun", true);

//        Recipe taco = new Recipe("Today", Arrays.asList(beef, cheese, salsa, tortilla));
//        Recipe quesadilla = new Recipe("Tomorrow", Arrays.asList(cheese, tortilla));
//        Recipe burger = new Recipe("UpComing", Arrays.asList(beef, cheese, ketchup, bun));


        CreateEvent createEvent = new CreateEvent();
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        for (int i = 0; i< sortingList.size(); i++) {

            String abc =  String.valueOf(day + "-" + (month + 1) + "-" + year);
            String abchistory =  String.valueOf(day-1 + "-" + (month + 1) + "-" + year);
            String abc2 =  String.valueOf(day+1 + "-" + (month + 1) + "-" + year);

//            Log.e("onCreate hgjjhg: ", i + ""+sortingList.get(i).getEventdate()+"  "+abc);

            if (sortingList.get(i).getEventdate().equals(abc)) {
                todayList.add(sortingList.get(i));

                StringBuilder today = new StringBuilder();
                for (EntityClass entityClass : todayList){
                    today.append(entityClass.getEventdate()+"  ");
                }
//                Log.e( " Today List: ", today.toString());
                Log.e("Today List: ",  ""+ todayList.get(0).getEventdate());


            } else if (sortingList.get(i).getEventdate().equals(abc2)) {
                tomorrowList.add(sortingList.get(i));
                StringBuilder tomorrow = new StringBuilder();
                for (EntityClass entityClass : tomorrowList){
                    tomorrow.append(entityClass.getEventdate()+"  ");
                }
                Log.e("Tomorrow List: ",  ""+ tomorrowList.get(0).getEventdate());

            } else if(sortingList.get(i).getEventdate().equals(abchistory)){
//                historyList.add(sortingList.get(i));
            }else {
                upcomingList.add(sortingList.get(i));
                 Log.e( "Upcoming List: ",""+upcomingList.get(0).getEventdate() );
            }
        }




    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPrefExpand", Context.MODE_PRIVATE);
        expandbool=sh.getBoolean("expandlist",true);
        Tache tooday = new Tache(getResources().getString(R.string.todo_name), todayList,expandbool);
//        Log.e("TAG", "tod: "+todayList.get(0).getEventdate() );
        Tache toomorrow = new Tache(getResources().getString(R.string.todo_tomorow), tomorrowList);
//        Log.e("TAG", "tom: "+ tomorrowList);
        Tache upcoming = new Tache(getResources().getString(R.string.todo_upcoming), upcomingList);
        Tache history= new Tache("History", historyList);
//        Log.e("TAG", "upc: " +upcoming);
        final List<Tache> taches = Arrays.asList( tooday, toomorrow, upcoming);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new ToDoAdapter(this, taches);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @UiThread
            @Override
            public void onParentExpanded(int parentPosition) {
                Tache expandedTache = taches.get(parentPosition);

//                String toastMsg = getResources().getString(R.string.expanded, expandedRecipe.getName());
//                Toast.makeText(VerticalLinearRecyclerViewSampleActivity.this,
//                        toastMsg,
//                        Toast.LENGTH_SHORT)
//                        .show();
                if(expandedTache.getName().toLowerCase().contains("today")|| expandedTache.getName().toLowerCase().contains("liste")){
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefExpand",Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putBoolean("expandlist", true);
                    myEdit.apply();
                     expandbool=true;
                }
            }

            @UiThread
            @Override
            public void onParentCollapsed(int parentPosition) {
                Tache collapsedTache = taches.get(parentPosition);

//                String toastMsg = getResources().getString(R.string.collapsed, collapsedRecipe.getName());
//                Toast.makeText(VerticalLinearRecyclerViewSampleActivity.this,
//                        toastMsg,
//                        Toast.LENGTH_SHORT)
//                        .show();


                if(collapsedTache.getName().toLowerCase().contains("today")|| collapsedTache.getName().toLowerCase().contains("liste")){
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefExpand",Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putBoolean("expandlist",false);
                    myEdit.apply();
                    expandbool=false;
                }
            }
        });

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.nav_drawer, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_settings:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        mAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        mAdapter.onRestoreInstanceState(savedInstanceState);
    }
}
