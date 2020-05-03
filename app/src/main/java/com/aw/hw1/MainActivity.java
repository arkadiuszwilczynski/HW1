package com.aw.hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aw.hw1.contacts.ContactListContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements
        ContactFragment.OnListFragmentInteractionListener,
        RingingDialog.OnRingingDialogInteractionListener
{

    public static final int ADD_REQUEST = 1;
    public static final String RESPONSE = "odpowiedz";
    public static final String CONTACTKEY = "contactkey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addButton = findViewById(R.id.add_button);
            addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent addView = new
                        Intent(view.getContext(), AddContactActivity.class);
                startActivityForResult(addView, ADD_REQUEST);
        }
    });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == ADD_REQUEST) {
                ContactListContent.Contact newContact;
                newContact = data.getParcelableExtra(RESPONSE);
                ContactListContent.ITEMS.add(newContact);
                Toast.makeText(getApplicationContext(),"Dodano kontakt", Toast.LENGTH_LONG).show();
                ((ContactFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.contactFragment))).notifyDataChange();
            }
        }
        else if(resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(),"Anulowano dodawanie", Toast.LENGTH_LONG).show();
        }
    }

    private void startDetailActivity(ContactListContent.Contact contact, int position) {
        Intent intent = new Intent(this, ContactDetailActivity.class);
        intent.putExtra(CONTACTKEY, contact);
        startActivity(intent);
    }

    private void displayContactInFragment(ContactListContent.Contact contact) {
        ContactDetailFragment contactDetailFragment = ((ContactDetailFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if(contactDetailFragment != null) {
            contactDetailFragment.displayContact(contact);
        }
    }

    public void showDialog() {
        RingingDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.dialog_tag));
    }

    @Override
    public void onListFragmentClickInteraction(ContactListContent.Contact contact, int position) {
        Toast.makeText(this, getString(R.string.selected_contact) + contact.name, Toast.LENGTH_SHORT).show();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            displayContactInFragment(contact);
        }else {
            startDetailActivity(contact, position);
        }
    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        showDialog();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(this, getString(R.string.ringing), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        View v = findViewById(R.id.deleteButton);
        if (v != null) {
            Snackbar.make(v, getString(R.string.ringing_cancel_msg), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry_msg), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDialog();
                        }
                    }).show();
        }
    }
}
