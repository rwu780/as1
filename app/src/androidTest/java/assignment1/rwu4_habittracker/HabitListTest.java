package assignment1.rwu4_habittracker;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rui on 2016-10-02.
 */
public class HabitListTest extends ActivityInstrumentationTestCase2<TodayHabitList> {

    public HabitListTest() {
        super(TodayHabitList.class);
    }

    public void testAddHabit(){
        List<Habit> habitList = HabitList.getHabitList();

        Habit habit = new Habit("TestAdd", new Date(), null);
        assertFalse(habitList.contains(habit));

        habitList.add(habit);
        assertTrue(habitList.contains(habit));
    }

    public void testDeleteHabit(){
        List<Habit> habitList = HabitList.getHabitList();

        Habit habit = new Habit("TestAdd", new Date(), null);
        habitList.add(habit);
        assertTrue(habitList.contains(habit));

        habitList.remove(habit);
        assertFalse(habitList.contains(habit));

    }

    public void testGetHabit(){
        List<Habit> habitList = HabitList.getHabitList();

        Habit habit = new Habit("TestAdd", new Date(), null);
        habitList.add(habit);
        assertTrue(habitList.contains(habit));
        assertEquals(habit, habitList.get(habitList.indexOf(habit)));
    }

    public void getTodayHabit(){
        List<Habit> habitList = HabitList.getHabitList();
        List<Integer> days = new ArrayList<Integer>();
        days.add(1);
        days.add(2);
        days.add(3);
        days.add(4);
        days.add(5);
        days.add(6);
        days.add(7);

        Habit nullHabit = new Habit("TestAdd", new Date(), null);
        Habit habit = new Habit("TestAdd", new Date(), days);
        habitList.add(nullHabit);
        habitList.add(habit);

        assertTrue(habitList.contains(nullHabit));
        assertTrue(habitList.contains(habit));

        List<Habit> todayList= HabitList.getTodayHabitList();
        assertFalse(todayList.contains(nullHabit));
        assertTrue(todayList.contains(habit));

    }

}