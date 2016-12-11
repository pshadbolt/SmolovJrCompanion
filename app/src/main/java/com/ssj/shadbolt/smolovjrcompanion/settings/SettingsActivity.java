package com.ssj.shadbolt.smolovjrcompanion.settings;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.ssj.shadbolt.smolovjrcompanion.R;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NumberPicker.OnValueChangeListener, View.OnClickListener {

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
        }
    };

    public static String pref_name = "settings_pref_v1";
    public static String pref_one_rep_max = "one_rep_max";
    public static String pref_increment = "increment";
    public static String pref_round = "round";
    public static String pref_units = "units";
    public static String pref_rest = "rest";
    public static String pref_plate_diagram = "plate_diagram";

    int value_one_rep_max;
    int value_increment;
    int value_rest;
    String value_units = null;
    int value_round;
    boolean value_plate_diagram;

    TextView tv_one_rep_max = null;
    TextView tv_increment = null;
    TextView tv_rest = null;
    Spinner sp_units = null;
    Spinner sp_round = null;
    CheckBox check_plate_diagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tv_one_rep_max = (TextView) findViewById(R.id.one_rep_max_value);
        tv_increment = (TextView) findViewById(R.id.increment_value);
        tv_rest = (TextView) findViewById(R.id.rest_value);
        sp_units = (Spinner) findViewById(R.id.units_value);
        sp_round = (Spinner) findViewById(R.id.round_value);
        check_plate_diagram = (CheckBox) findViewById(R.id.platediagram_enabled);

        // Set Spinner values
        ArrayAdapter<CharSequence> adapter_units = ArrayAdapter.createFromResource(this, R.array.units_array, android.R.layout.simple_spinner_item);
        adapter_units.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_units.setAdapter(adapter_units);

        ArrayAdapter<CharSequence> adapter_round = ArrayAdapter.createFromResource(this, R.array.round_array, android.R.layout.simple_spinner_item);
        adapter_round.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_round.setAdapter(adapter_round);

        // Restore the preference values
        SharedPreferences settings = getSharedPreferences(pref_name, 0);
        value_one_rep_max = settings.getInt(pref_one_rep_max, 300);
        tv_one_rep_max.setText(Integer.toString(value_one_rep_max));

        value_increment = settings.getInt(pref_increment, 5);
        tv_increment.setText(Integer.toString(value_increment));

        value_rest = settings.getInt(pref_rest, 2);
        tv_rest.setText(Integer.toString(value_rest));

        value_units = settings.getString(pref_units, "lbs");
        sp_units.setSelection(adapter_units.getPosition(value_units));

        value_round = settings.getInt(pref_round, 1);
        sp_round.setSelection(adapter_round.getPosition(Integer.toString(value_round)));

        value_plate_diagram = settings.getBoolean(pref_plate_diagram, true);
        check_plate_diagram.setChecked(value_plate_diagram);

        // Add listeners
        sp_units.setOnItemSelectedListener(this);
        sp_round.setOnItemSelectedListener(this);
        check_plate_diagram.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        savePreferences();
    }

    @Override
    protected void onPause() {
        super.onPause();
        savePreferences();
    }

    // Spinner Selector
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (parent.getId() == R.id.units_value) {
            value_units = (String) parent.getItemAtPosition(pos);
        } else if (parent.getId() == R.id.round_value) {
            value_round = Integer.parseInt((String) parent.getItemAtPosition(pos));
        }
    }

    @Override
    public void onClick(View view) {
        if (view.equals(check_plate_diagram))
            value_plate_diagram = ((CheckBox) view).isChecked();
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    // Update 1RM
    public void updateMax(View v) {
        final Dialog d = new Dialog(SettingsActivity.this);
        d.setTitle("One Rep Max");
        d.setContentView(R.layout.prompt_number);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(1000);
        np.setMinValue(0);
        np.setValue(value_one_rep_max);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value_one_rep_max = np.getValue();
                tv_one_rep_max.setText(String.valueOf(value_one_rep_max));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }

    public void updateIncrement(View v) {
        final Dialog d = new Dialog(SettingsActivity.this);
        d.setTitle("Increment Value");
        d.setContentView(R.layout.prompt_number);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(100);
        np.setMinValue(0);
        np.setValue(value_increment);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value_increment = np.getValue();
                tv_increment.setText(String.valueOf(value_increment));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }

    public void updateRest(View v) {
        final Dialog d = new Dialog(SettingsActivity.this);
        d.setTitle("Rest Time");
        d.setContentView(R.layout.prompt_number);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(5);
        np.setMinValue(1);
        np.setValue(value_rest);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value_rest = np.getValue();
                tv_rest.setText(String.valueOf(value_rest));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }

    /**
     *
     */
    public void savePreferences() {
        SharedPreferences settings = getSharedPreferences(pref_name, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putInt(pref_one_rep_max, value_one_rep_max);
        editor.putInt(pref_increment, value_increment);
        editor.putInt(pref_rest, value_rest);
        editor.putString(pref_units, value_units);
        editor.putInt(pref_round, value_round);
        editor.putBoolean(pref_plate_diagram, value_plate_diagram);

        editor.commit();
    }


}
