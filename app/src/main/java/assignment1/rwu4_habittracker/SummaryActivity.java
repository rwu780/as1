package assignment1.rwu4_habittracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SummaryActivity extends Activity {

    private List<Date> habitList;
    private ListView dateListView;
    private ArrayAdapter<Date> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        //habitList = HabitList.getHabitList();
        int position = getIntent().getIntExtra("index", 0);
        final Habit habit = HabitList.getHabitList().get(position);

        //Set HabitName TextView
        TextView habitNameTextView = (TextView) findViewById(R.id.ShowHabitNameTextView);
        habitNameTextView.setText(habit.getName());

        //Set Habit Completion number TextView
        final TextView numberTextView = (TextView) findViewById(R.id.DisplayNumberOfCompletion);
        numberTextView.setText(habit.numberOfCompletion());

        habitList = habit.getCompletionDay();
        dateListView = (ListView) findViewById(R.id.recentCompleteListView);
        adapter = new ArrayAdapter<Date>(this, android.R.layout.simple_list_item_1, habitList);

        dateListView.setAdapter(adapter);

        dateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                habitList.remove(position);
                adapter.notifyDataSetChanged();
                numberTextView.setText(habit.numberOfCompletion());
                saveInFile();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
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
    private void saveInFile(){
        String FILENAME = "file.sav";
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
}
