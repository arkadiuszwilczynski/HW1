package com.aw.hw1.contacts;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ContactListContent {

    public static final List<Contact> ITEMS = new ArrayList<Contact>();

    public static class Contact implements Parcelable {
        public final int image;
        public final String name;
        public final String surname;
        public final String birthDate;
        public final int phoneNumber;


        public Contact(int image, String name, String surname, String birthDate, int phoneNumber) {
            this.image = image;
            this.name = name;
            this.surname = surname;
            this.birthDate = birthDate;
            this.phoneNumber = phoneNumber;
        }

        protected Contact(Parcel in) {
            image = in.readInt();
            name = in.readString();
            surname = in.readString();
            birthDate = in.readString();
            phoneNumber = in.readInt();
        }

        public static final Creator<Contact> CREATOR = new Creator<Contact>() {
            @Override
            public Contact createFromParcel(Parcel in) {
                return new Contact(in);
            }

            @Override
            public Contact[] newArray(int size) {
                return new Contact[size];
            }
        };

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(image);
            dest.writeString(name);
            dest.writeString(surname);
            dest.writeString(birthDate);
            dest.writeInt(phoneNumber);
        }
    }
}
