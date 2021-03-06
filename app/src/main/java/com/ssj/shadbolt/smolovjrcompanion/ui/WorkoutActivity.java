package com.ssj.shadbolt.smolovjrcompanion.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ssj.shadbolt.smolovjrcompanion.R;
import com.ssj.shadbolt.smolovjrcompanion.database.WorkoutDataSource;
import com.ssj.shadbolt.smolovjrcompanion.model.Workout;
import com.ssj.shadbolt.smolovjrcompanion.settings.AboutActivity;
import com.ssj.shadbolt.smolovjrcompanion.settings.DatabaseActivity;
import com.ssj.shadbolt.smolovjrcompanion.settings.SettingsActivity;

public class WorkoutActivity extends AppCompatActivity {

    //
    private WorkoutDataSource datasource;
    Workout workout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        // Create the database connection
        datasource = new WorkoutDataSource(this);

        // Set the back button to parent
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int index = getIntent().getExtras().getInt("index");

        datasource.open();
        workout = datasource.getWorkout(index);
        datasource.close();

        ((TextView) findViewById(R.id.workoutLabel)).setText(workout.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Workout?")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            datasource.open();
                            datasource.deleteWorkout(workout);
                            datasource.close();
                            finish();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Do nothing.
                }
            }).show();
        } else if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_database) {
            Intent intent = new Intent(this, DatabaseActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
