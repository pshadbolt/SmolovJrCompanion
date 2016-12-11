package com.ssj.shadbolt.smolovjrcompanion.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.ssj.shadbolt.smolovjrcompanion.R;
import com.ssj.shadbolt.smolovjrcompanion.adapters.ImageAdapter;
import com.ssj.shadbolt.smolovjrcompanion.database.WorkoutDataSource;
import com.ssj.shadbolt.smolovjrcompanion.settings.AboutActivity;
import com.ssj.shadbolt.smolovjrcompanion.settings.DatabaseActivity;
import com.ssj.shadbolt.smolovjrcompanion.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private WorkoutDataSource datasource;
    private ImageAdapter imageAdapter;
    private GridView gridView;

    private int lastIndex;
    private int maxIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.title_activity_main);

        // Create the database connection
        datasource = new WorkoutDataSource(this);

        // Populate the gridview
        gridView = (GridView) findViewById(R.id.grid_view);
        imageAdapter = new ImageAdapter(this, datasource);
        gridView.setAdapter(imageAdapter);

        // Add the gridView listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openWorkout(position);
            }
        });
    }

    /**
     * Control the state of the train button
     */
    private void setTrainButton() {
        datasource.open();
        lastIndex = datasource.getLastIndex();
        maxIndex = datasource.getMaxIndex();
        datasource.close();
        Button trainButton = (Button) findViewById(R.id.train);

        if (lastIndex == maxIndex) {
            trainButton.setVisibility(View.GONE);
        } else {
            trainButton.setVisibility(View.VISIBLE);
            trainButton.setText("TRAIN DAY " + (lastIndex + 2));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        imageAdapter.notifyDataSetChanged();
        gridView.invalidateViews();
        setTrainButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        imageAdapter.notifyDataSetChanged();
        gridView.invalidateViews();
        setTrainButton();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTrainButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_database) {
            Intent intent = new Intent(this, DatabaseActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // View a previous training day or launch new training
    public void openWorkout(int position) {
        Intent intent;
        datasource.open();
        if (datasource.getWorkout(position) != null)
            intent = new Intent(this, WorkoutActivity.class);
        else
            intent = new Intent(this, TrainingActivity.class);
        datasource.close();
        intent.putExtra("index", position);
        startActivity(intent);
    }

    // Launch the next training day
    public void train(View v) {
        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("index", lastIndex + 1);
        startActivity(intent);
    }
}
