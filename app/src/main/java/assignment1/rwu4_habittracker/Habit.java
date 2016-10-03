package assignment1.rwu4_habittracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rui on 2016-10-01.
 */
public class Habit {
    private String Name;
    private Date date;
    private List<Integer> recurDays;
    private List<Date> completionDay;
    
    public Habit(String name, Date d, List<Integer> daySelected){
        this.Name = name;
        this.date = d;
        this.recurDays = daySelected;
        completionDay = new ArrayList<Date>();
    }

    public Habit(){
        this.Name = null;
        this.date = new Date();
        this.recurDays = new ArrayList<Integer>();
        completionDay = new ArrayList<Date>();
    }

    public String getName() {
        return Name;
    }
    public void addCompletion(){
        completionDay.add(new Date());
    }

    public void deleteCompletion(int index){
        completionDay.remove(index);
    }

    public String numberOfCompletion(){
        return Integer.toString(completionDay.size());
    }

    public List<Date> getCompletionDay(){
        return completionDay;
    }
    public Boolean containDay(int day){
        if(recurDays == null){
            return false;
        }
        if(recurDays.contains(day)){
            return true;
        }
        return false;
    }
    public String toString(){
        return getName();
    }
}
