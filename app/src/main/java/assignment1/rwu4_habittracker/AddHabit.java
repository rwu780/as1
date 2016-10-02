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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddHabit extends Activity {

    private List<Integer> recurDay;
    private ListView  dayListView;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        List<String> days = getDays();

        dayListView = (ListView) findViewById(R.id.selectDaysListView);
        //TextView textView = (TextView) findViewById(R.id.item_tv);
        //textView.setTextColor(Color.BLACK);
        adapter = new ListViewAdapter(getApplicationContext(), days);
        dayListView.setAdapter(adapter);


        Button button = (Button) findViewById(R.id.addHabitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHabit();
            }
        });

    }

    public void addHabit(){
        EditText editText = (EditText) findViewById(R.id.newHabitName);
        HabitList.getHabitList().add(new Habit(editText.getText().toString(), new Date(), adapter.getDaySelected()));
        saveInFile();

        Intent intent = new Intent(AddHabit.this, TodayHabitList.class);
        startActivity(intent);
    }

    public List<String> getDays(){
        List<String> days = new ArrayList<String>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");
        return days;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_habit, menu);
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
