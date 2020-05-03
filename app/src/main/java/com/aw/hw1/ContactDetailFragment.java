package com.aw.hw1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aw.hw1.contacts.ContactListContent;

public class ContactDetailFragment extends Fragment {

    public ContactDetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_detail, container, false);
    }

    public void displayContact(ContactListContent.Contact contact) {
        FragmentActivity activity = getActivity();

        TextView contactDetailName = activity.findViewById(R.id.contactInfoName);
        TextView contactDetailBD = activity.findViewById(R.id.contactInfoBD);
        TextView contactDetailPN = activity.findViewById(R.id.contactInfoPN);
        ImageView contactDetailImage = activity.findViewById(R.id.contactInfoImage);

        contactDetailName.setText(contact.name + " " + contact.surname);
        contactDetailBD.setText(contact.birthDate);
        contactDetailPN.setText(String.valueOf(contact.phoneNumber));
        contactDetailImage.setImageResource(contact.image);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if(intent != null) {
            ContactListContent.Contact receivedContact = intent.getParcelableExtra(MainActivity.CONTACTKEY);
            if(receivedContact != null) {
                displayContact(receivedContact);
            }
        }
    }
}
