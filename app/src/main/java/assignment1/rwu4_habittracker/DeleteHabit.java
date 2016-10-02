package assignment1.rwu4_habittracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
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

public class DeleteHabit extends Activity {

    private ListView deleteListView;
    private List<Habit> habitList;
    private ArrayAdapter<Habit> adapter;
    private final String FILENAME = "file.sav";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_habit);

        deleteListView = (ListView) findViewById(R.id.deleteHabitListView);

        habitList = HabitList.getHabitList();

        adapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1,habitList);
        deleteListView.setAdapter(adapter);

        deleteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = HabitList.getHabitList().get(position).getName();
                HabitList.getHabitList().remove(position);
                saveInFile();
                adapter.notifyDataSetChanged();

            }
        });
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

        } catch (IOException e) {
            //hList = new HabitList();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete_habit, menu);
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
}
