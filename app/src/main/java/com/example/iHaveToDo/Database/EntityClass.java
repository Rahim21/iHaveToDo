package com.example.iHaveToDo.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity(tableName = "myTable")
public class EntityClass {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "eventdone")
    boolean eventdone;

    @ColumnInfo(name = "eventname")
    String eventname;
    @ColumnInfo(name = "eventdate")
    String eventdate;
    @ColumnInfo(name = "eventtime")
    String eventtime;

    public boolean expandable;

    public boolean isExpandable() {
        return expandable;
    }


public static Comparator<EntityClass> EntityClassAZComparator = new Comparator<EntityClass>() {
    @Override
    public int compare(EntityClass o1, EntityClass o2) {
        return o1.getEventdate().compareTo(o2.getEventdate());
    }
};


    public static Comparator<EntityClass> EntityClassZAComparator = new Comparator<EntityClass>() {
        @Override
        public int compare(EntityClass o1, EntityClass o2) {
            return o2.getEventdate().compareTo(o1.getEventdate());
        }
    };
    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEventdone() {

        return eventdone;
    }


    public void setEventdone(boolean eventdone) {
        this.eventdone = eventdone;
    }


    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }
}
