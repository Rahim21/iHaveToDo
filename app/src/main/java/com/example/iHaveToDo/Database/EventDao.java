package com.example.iHaveToDo.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDao {
    @Insert(entity = EntityClass.class, onConflict = OnConflictStrategy.REPLACE)
    void addAlarm(EntityClass alarmEntity);


    @Query("SELECT * FROM myTable")
    List<EntityClass> getallData();

    @Query("SELECT * FROM myTable where eventdate like :Today")
    EntityClass getUserByDate(int Today);

    @Query("SELECT * FROM myTable where eventtime like :eventtime")
    EntityClass getUserByTime(String eventtime);

    @Query("SELECT * FROM myTable where eventdone like :eventdone")
    List<EntityClass> getDone(boolean eventdone);


    @Query("SELECT * FROM myTable where eventdone = eventdone")
    List<EntityClass>getDoneData();

    @Query("SELECT * FROM myTable WHERE id = :id")
    List<EntityClass> getID(int id);

//    @Query("DELETE  FROM myTable where eventdate like eventdate AND eventtime like eventtime ")
//    void deleteAlarm();


    @Query("DELETE FROM myTable where eventdone =:eventdone AND eventname = :eventname")
    void deleteAlarm(boolean eventdone,String eventname);

    @Query("DELETE FROM myTable where eventdone like :eventdone ")
    void deleteData(boolean eventdone);



    @Query("UPDATE myTable SET eventdone=:falsee, eventdate = :date WHERE id= :id")
    void update(boolean falsee,String date, int id);
}