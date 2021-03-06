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
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddHabit extends Activity {

    private ListView  dayListView;
    private ListViewAdapter adapter;
    private String date;
    private SimpleDateFormat dt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        List<String> days = getDays();

        dayListView = (ListView) findViewById(R.id.selectDaysListView);

        adapter = new ListViewAdapter(getApplicationContext(), days);
        dayListView.setAdapter(adapter);


        Button button = (Button) findViewById(R.id.addHabitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHabit();
            }
        });

        EditText editText = (EditText) findViewById(R.id.DateEditText);

        dt1 = new SimpleDateFormat("dd/M/yyyy");
        date = dt1.format(new Date());

        editText.setText(date);



    }

    public void addHabit(){
        //Get Habit Name;
        EditText editText = (EditText) findViewById(R.id.newHabitName);

        //Get start Date;
        EditText editTextDate = (EditText) findViewById(R.id.DateEditText);
        try {
            Date setDate = new SimpleDateFormat("dd/MM/yyyy").parse(editTextDate.getText().toString());
            HabitList.getHabitList().add(new Habit(editText.getText().toString(), setDate, adapter.getDaySelected()));
            saveInFile();

            Intent intent = new Intent(AddHabit.this, TodayHabitList.class);
            startActivity(intent);
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(), "Please enter the date according to format(dd/M/yyyy)", Toast.LENGTH_LONG).show();
        }

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
