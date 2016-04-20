package com.example.jonat_000.assignment1;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int numOfPeople;
    double finalTipPercent;
    String tipPercent;

    TextView total;
    TextView tip;
    TextView perPerson;
    EditText price;
    EditText otherTip;
    Button clear;
    Button submit;
    Spinner people;
    Spinner percentage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        total = (TextView) findViewById(R.id.lblTotal);
        perPerson = (TextView) findViewById(R.id.lblPerPerson);
        tip = (TextView) findViewById(R.id.lblAmount);
        otherTip = (EditText) findViewById(R.id.txtCustomTip);
        price = (EditText) findViewById(R.id.txtAmt);
        clear = (Button) findViewById(R.id.btnClear);
        submit = (Button) findViewById(R.id.btnCalc);
        people = (Spinner) findViewById(R.id.spnNumPeople);
        percentage = (Spinner) findViewById(R.id.spnTipPercent);


        //create adapter to get array from string/int arrays
        Resources res = getResources();
        ArrayAdapter<CharSequence> adapterPpl = ArrayAdapter.createFromResource(this, R.array.persons, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterPcnt = ArrayAdapter.createFromResource(this, R.array.percents, android.R.layout.simple_spinner_item);

        //Just the look?
        adapterPpl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterPcnt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //connect interface spinner with adapter
        people.setAdapter(adapterPpl);
        percentage.setAdapter(adapterPcnt);

        //makes clicked and not clicked methods
        people.setOnItemSelectedListener(this);
        percentage.setOnItemSelectedListener(this);


    }

    public void clearForm(View View) {

        total.setText("");
        perPerson.setText("");
        tip.setText("");
        otherTip.setText("");
        price.setText("");

    }


    public void calcTip(View view) {

        //local variables with values from user
        String enteredTotal = total.getText().toString();
        String enteredTip = otherTip.getText().toString();

        //to int so we can add
        int numTotal = Integer.valueOf(enteredTotal);
        int numTip = Integer.valueOf(enteredTip);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;

        if (spinner.getId() == R.id.spnNumPeople) {

            numOfPeople = Integer.parseInt(parent.getItemAtPosition(position).toString());
            Toast.makeText(MainActivity.this, "This is spnnumpeople " + numOfPeople, Toast.LENGTH_SHORT).show();

        } else {
            //default case in switch
            //get selected # from people spinner
            tipPercent = parent.getItemAtPosition(position).toString();
            Toast.makeText(MainActivity.this, "This is spnTipPercent " + tipPercent, Toast.LENGTH_SHORT).show();

            //if statement in case


            switch (tipPercent) {

                case "10%":

                    finalTipPercent = 0.1;

                    break;

                case "15%":

                    finalTipPercent = 0.15;

                    break;

                case "20%":

                    finalTipPercent = 0.2;
                    break;

                case "Other %":

                    //enable edittext and get value
                    otherTip.setText("0.0");
                    otherTip.setEnabled(true);

                    finalTipPercent = Double.parseDouble(otherTip.getText().toString());

                    // finalTipPercent = Double.parseDouble(otherTip.getText().toString());

                    break;
            }


        }
    }

    //finalTipPercent = Double.parseDouble(tipPercent);

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
