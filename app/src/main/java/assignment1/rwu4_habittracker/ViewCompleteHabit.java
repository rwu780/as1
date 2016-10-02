package assignment1.rwu4_habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class ViewCompleteHabit extends Activity {

    private ListView completeHabitListView;
    private List<Habit> habitList;
    private ArrayAdapter<Habit> adapter;
    private final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complete_habit);

        completeHabitListView = (ListView) findViewById(R.id.completedHabitListView);

        habitList = HabitList.getHabitList();
        adapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1, habitList);
        completeHabitListView.setAdapter(adapter);

        completeHabitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                saveInFile();
                Intent intent = new Intent(ViewCompleteHabit.this, SummaryActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_complete_habit, menu);
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
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

    public void DeleteHabit(MenuItem menuItem){
        Intent intent = new Intent(ViewCompleteHabit.this, DeleteHabit.class);
        startActivity(intent);
    }


}
