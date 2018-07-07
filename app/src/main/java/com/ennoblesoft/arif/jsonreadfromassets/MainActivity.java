package com.ennoblesoft.arif.jsonreadfromassets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ennoblesoft.arif.jsonreadfromassets.adapters.ContactAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    MainActivity activity;
    RecyclerView rvContact;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        rvContact = findViewById(R.id.rv_contact);
        Adapt();
    }

    public void Adapt() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAssets());
            String status = obj.getString("status");
            if (status.equals("Success")) {
                JSONArray contactArray = obj.getJSONArray("data");
                ArrayList<HashMap<String, String>> contactList = new ArrayList<>();
                HashMap<String, String> contact;

                for (int i = 0; i < contactArray.length(); i++) {
                    JSONObject contacts = contactArray.getJSONObject(i);
                    String name = contacts.getString("name");
                    String number = contacts.getString("number");

                    contact = new HashMap<>();
                    contact.put("name", name);
                    contact.put("number", number);

                    contactList.add(contact);
                }
                contactAdapter = new ContactAdapter(activity, contactList);
                rvContact.setLayoutManager(new LinearLayoutManager(this));
                rvContact.setAdapter(contactAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAssets() {
        String json;
        try {
            InputStream is = getApplicationContext().getAssets().open("contact.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
