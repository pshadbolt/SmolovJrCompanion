package com.ssj.shadbolt.smolovjrcompanion.settings;

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
import com.ssj.shadbolt.smolovjrcompanion.ui.MainActivity;

public class DatabaseActivity extends AppCompatActivity {

    private WorkoutDataSource datasource;
    private TextView databaseTotalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        // Create the database connection
        datasource = new WorkoutDataSource(this);

        databaseTotalText = (TextView) findViewById(R.id.databaseTotal);
        setTotal();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_database, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete) {
            delete();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setTotal();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTotal();
    }

    public void setTotal() {
        datasource.open();
        databaseTotalText.setText(Integer.toString(datasource.getAllWorkouts().size()));
        datasource.close();
    }

    public void launchMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void delete() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.message_delete_database)
                .setMessage(R.string.message_confirm_delete)
                .setPositiveButton(R.string.button_delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        datasource.open();
                        datasource.recreate();
                        datasource.close();
                        launchMain();
                    }
                }).setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do nothing.
            }
        }).show();
    }
}
