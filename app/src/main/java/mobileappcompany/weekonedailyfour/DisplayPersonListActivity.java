package mobileappcompany.weekonedailyfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import model.Person;

public class DisplayPersonListActivity extends AppCompatActivity {

    TextView tvPersonName;
    TextView tvPersonAge;
    TextView tvPersonGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_person_list);

        TableRow.LayoutParams  params1=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,1.0f);
        TableRow.LayoutParams params2=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
        TableLayout tbl=(TableLayout) findViewById(R.id.tlMarksTable);

        ArrayList<Person> personArrayList = getIntent().getParcelableArrayListExtra("PersonArrayList");
        if(personArrayList == null){
            System.out.println("Empty List of Persons");
            return;
        }
        Iterator<Person> iterator = personArrayList.iterator();
        Person person;
        while (iterator.hasNext()) {
            person = iterator.next();
            System.out.println("Name   " + person.getName() + "   Age   " + person.getAge() +
                    "      Gender   " + person.getGender());

            //Creating new tablerows and textviews
            TableRow row=new TableRow(this);
            TextView tvPersonName=new TextView(this);
            TextView tvPersonAge=new TextView(this);
            TextView tvPersonGender=new TextView(this);

            //setting the text
            tvPersonName.setText(person.getName());
            tvPersonAge.setText(person.getAge());
            tvPersonGender.setText(person.getGender());
            tvPersonName.setLayoutParams(params1);
            tvPersonAge.setLayoutParams(params1);
            tvPersonGender.setLayoutParams(params1);
            //the textviews have to be added to the row created
            row.addView(tvPersonName);
            row.addView(tvPersonAge);
            row.addView(tvPersonGender);
            row.setLayoutParams(params2);
            tbl.addView(row);
        }
    }
}
