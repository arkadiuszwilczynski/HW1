package com.aw.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aw.hw1.contacts.ContactListContent;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddContactActivity extends AppCompatActivity {

    private EditText nameEditTxt;
    private EditText surnameEditTxt;
    private EditText birthDateEditTxt;
    private EditText phoneNumberEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    private void setParameters(View v) {
        nameEditTxt = findViewById(R.id.name);
        surnameEditTxt = findViewById(R.id.surname);
        birthDateEditTxt = findViewById(R.id.birthDate);
        phoneNumberEditTxt = findViewById(R.id.phoneNumber);
    }

    private int setRandomImage() {
        int[] images = {
                R.drawable.avatar_1, R.drawable.avatar_2, R.drawable.avatar_3, R.drawable.avatar_4, R.drawable.avatar_5, R.drawable.avatar_6,
                R.drawable.avatar_7, R.drawable.avatar_8, R.drawable.avatar_9, R.drawable.avatar_10, R.drawable.avatar_11, R.drawable.avatar_12,
                R.drawable.avatar_13, R.drawable.avatar_14, R.drawable.avatar_15, R.drawable.avatar_16
        };

        Random rand = new Random();
        int random = rand.nextInt(images.length);

        return images[random];
    }

    private boolean checkName(String name) {
        Pattern goodPattern = Pattern.compile("[A-Z][a-z]+");
        Matcher matcher = goodPattern.matcher(name);
        return matcher.matches();
    }

    private boolean checkSurname(String surname) {
        Pattern goodPattern = Pattern.compile("[A-Z][a-z]+");
        Matcher matcher = goodPattern.matcher(surname);
        return matcher.matches();
    }

    private boolean checkDate(String birthDate) {
        Pattern goodPattern = Pattern.compile("\\d{2}/[0-1][0-9]/\\d{4}");
        Matcher matcher = goodPattern.matcher(birthDate);
        return matcher.matches();
    }

    private boolean checkPhoneNumber(String number) {
        Pattern goodPattern = Pattern.compile("\\d{9}");
        Matcher matcher = goodPattern.matcher(number);
        return matcher.matches();
    }


    public void addClick(View view) {

        setParameters(view);

        String name = nameEditTxt.getText().toString();
        String surname = surnameEditTxt.getText().toString();
        String birthDate = birthDateEditTxt.getText().toString();
        String phoneNumber = phoneNumberEditTxt.getText().toString();

       if(checkName(name) && checkSurname(surname) && checkDate(birthDate) && checkPhoneNumber(phoneNumber)) {
        ContactListContent.Contact contact = new ContactListContent.Contact(setRandomImage() , name, surname, birthDate, Integer.parseInt(phoneNumber));
            Intent data = new Intent();
            data.putExtra(MainActivity.RESPONSE, contact);
            setResult(RESULT_OK, data);
            finish();
        } if(!checkName(name))
            Toast.makeText(this, "Imię jest w złym formacie", Toast.LENGTH_SHORT).show();

        if(!checkSurname(surname))
            Toast.makeText(this, "Nazwisko jest w złym formacie", Toast.LENGTH_SHORT).show();

        if(!checkDate(birthDate))
            Toast.makeText(this, "Data jest w złym formacie", Toast.LENGTH_SHORT).show();

        if(!checkPhoneNumber(phoneNumber))
            Toast.makeText(this, "Numer jest w złym formacie", Toast.LENGTH_SHORT).show();

        if(!checkName(name) && !checkSurname(surname) && !checkDate(birthDate) && !checkPhoneNumber(phoneNumber))
            Toast.makeText(this, "Dane w złym formacie", Toast.LENGTH_SHORT).show();
    }
}
