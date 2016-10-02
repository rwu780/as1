package assignment1.rwu4_habittracker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Rui on 2016-10-01.
 */
public class HabitList {

    private static List<Habit> habitList;
    private static List<Habit> todayHabitList;

    public HabitList(){
        habitList = new ArrayList<Habit>();
    }
    public static void setHabitList(List<Habit> list){
        habitList = list;
    }

    public void add(Habit habit){
        habitList.add(habit);
    }
    public void delete(int index){
        habitList.remove(index);
    }
    public Habit getHabit(int index){
        return habitList.get(index);
    }
    public static List<Habit> getHabitList(){
        if(habitList == null){
            habitList = new ArrayList<Habit>();
        }
        return habitList;
    }
    public static List<Habit> getTodayHabitList(){
        if(todayHabitList == null) {
            todayHabitList = new ArrayList<Habit>();
        }
        todayHabitList.clear();
        for(Habit i:habitList){
            if(i.containDay(Calendar.DAY_OF_WEEK)){
                todayHabitList.add(i);
            }
        }
        return todayHabitList;
    }

    public int getSize(){
        if(habitList == null){
            return -1;
        }
        return habitList.size();
    }
}
