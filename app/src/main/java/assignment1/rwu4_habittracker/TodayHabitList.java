package assignment1.rwu4_habittracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodayHabitList extends Activity {

    private ListView habitListView;
    private List<Habit> habitList;
    private List<Habit> todayList;
    private final String FILENAME = "file.sav";
    private ArrayAdapter<Habit> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdoay_habit_list);

        loadFromFile();

        habitListView = (ListView) findViewById(R.id.HabitListView);
        todayList = HabitList.getTodayHabitList();
        adapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1, todayList);
        habitListView.setAdapter(adapter);

        habitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Habit habit = todayList.get(position);
                Toast.makeText(getApplicationContext(), habit.getName() + " Completed", Toast.LENGTH_SHORT).show();
                habit.addCompletion();

                View newView = parent.getChildAt(position);
                newView.setBackgroundColor(Color.YELLOW);
                saveInFile();
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void createListView(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tdoay_habit_list, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listtype = new TypeToken<ArrayList<Habit>>(){}.getType();
            habitList = gson.fromJson(in, listtype);
            HabitList.setHabitList(habitList);

        } catch (FileNotFoundException e) {
            habitList = new ArrayList<Habit>();
            HabitList.setHabitList(habitList);
        }
    }

    private void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(HabitList.getHabitList(), out);
            out.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            //hList = new HabitList();
        } catch (IOException e) {
            //hList = new HabitList();
        }
    }

    public void addNewHabit(MenuItem menu){
        Intent intent = new Intent(TodayHabitList.this, AddHabit.class);
        startActivity(intent);
    }

    public void viewCompletedHabit(MenuItem menu){
        Intent intent = new Intent(TodayHabitList.this, ViewCompleteHabit.class);
        startActivity(intent);
    }

}
