package mobileappcompany.weekonedailyfour;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Person;

public class IntentToSendDataActivity extends AppCompatActivity {

    TextView tvPersonName;
    TextView tvPersonAge;
    TextView tvPersonGender;
    TextView tvPersonAddedConfirm;

    EditText etPersonName;
    EditText etPersonAge;
    EditText etPersonGender;

    Button btnAddPerson;
    Button btnDisplayPersonList;

    String name = "", age = "", gender = "";

    Person person;
    ArrayList<Person> personArrayList;

    public static final String TAG = "IntentToSend";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_to_send_data);

        tvPersonName = findViewById(R.id.tvPersonName);
        tvPersonAge = findViewById(R.id.tvPersonAge);
        tvPersonGender = findViewById(R.id.tvPersonGender);

        etPersonName = findViewById(R.id.etPersonName);
        etPersonAge = findViewById(R.id.etPersonAge);
        etPersonGender = findViewById(R.id.etPersonGender);

        tvPersonAddedConfirm = findViewById(R.id.tvPersonAddedConfirm);

        personArrayList = new ArrayList<Person>();
    }

    @Override
    protected void onSaveInstanceState(Bundle onSaveInstanceState){
        super.onSaveInstanceState(onSaveInstanceState);

        Log.d(TAG, "Savind data");
        String pName = etPersonName.getText().toString();
        String pAge = etPersonAge.getText().toString();
        String pGender = etPersonGender.getText().toString();

        onSaveInstanceState.putString("name", pName);
        onSaveInstanceState.putString("age", pAge);
        onSaveInstanceState.putString("gender", pGender);

    }

    @Override
    protected void onRestoreInstanceState(Bundle onRestoreInstanceState){
        super.onRestoreInstanceState(onRestoreInstanceState);
        Log.d(TAG, "Restoring data");

        String retrievePersonName = onRestoreInstanceState.getString("name");
        String retrievePersonAge = onRestoreInstanceState.getString("age");
        String retrievePersonGender = onRestoreInstanceState.getString("gender");

        etPersonName.setText(retrievePersonName);
        etPersonAge.setText(retrievePersonAge);
        etPersonGender.setText(retrievePersonGender);
    }


    public void addPerson(View view) {
        name = etPersonName.getText().toString();
        age = etPersonAge.getText().toString();
        gender = etPersonGender.getText().toString();

        person = new Person(name, age, gender);
        if(personArrayList.contains(person)){
            tvPersonAddedConfirm.setText("Person already added into the list");
            return;
        }
        personArrayList.add(person);
        etPersonName.setText("");
        etPersonAge.setText("");
        etPersonGender.setText("");
        tvPersonAddedConfirm.setText("Person successfully added to the list");
    }

    public void displayPersonList(View view) {

        /*Iterator<Person> iterator = personArrayList.iterator();
        System.out.println("Name        " + "Age        " + "Gender         ");
        while ((iterator.hasNext())) {
            person = iterator.next();
            System.out.println(person.getName() + "     " + person.getAge() +
                    "       " + person.getGender());
        }*/

        Intent intent = new Intent(this, DisplayPersonListActivity.class);
        intent.putParcelableArrayListExtra("PersonArrayList", personArrayList);
        startActivity(intent);

    }
}
