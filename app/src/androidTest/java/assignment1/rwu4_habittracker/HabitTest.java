package assignment1.rwu4_habittracker;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

/**
 * Created by Rui on 2016-10-02.
 */
public class HabitTest extends ActivityInstrumentationTestCase2<AddHabit> {
    public HabitTest() {
        super(AddHabit.class);
    }


    public void testAddCompletionTest1() throws Exception {
        Habit habit = new Habit("TestAdd", new Date(), null);

        habit.addCompletion();
        assertEquals(habit.getCompletionDay().size(), 1);
    }

    public void testDeleteCompletionTest1() throws Exception {
        Habit habit = new Habit("TestDelete", new Date(), null);
        habit.addCompletion();
        assertEquals(habit.getCompletionDay().size(), 1);
        habit.deleteCompletion(0);

        assertEquals(habit.getCompletionDay().size(), 0);
    }

    public void testNumberOfCompletionTest1() throws Exception {
        Habit habit = new Habit("TestDelete", new Date(), null);
        habit.addCompletion();
        habit.addCompletion();
        assertEquals(habit.getCompletionDay().size(), 2);
    }
}
