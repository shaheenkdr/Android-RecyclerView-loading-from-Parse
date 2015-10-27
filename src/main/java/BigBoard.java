package obx.com.futurister;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class BigBoard extends ActionBarActivity {

    private List<Person> persons;
    private RecyclerView rv;
    RVAdapter adapter;
    String a,b;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this, "app-id", "client-key");
        setContentView(R.layout.activity_big_board);
        initializeData();
        adapter = new RVAdapter(persons);


        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);


        initializeAdapter();

    }

    private void initializeData(){
        persons = new ArrayList<>();


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Credentials");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> credentialList, ParseException e) {
                if (e == null) {
                    for(int i=0;i<credentialList.size();i++)
                    {
                        a=credentialList.get(i).getString("Name");
                        b=credentialList.get(i).getString("SurName");
                        persons.add(new Person(a,b));



                        Log.d("OUT", "So the Val::------> " +a +b);
                        adapter.notifyDataSetChanged();

                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
       

    }

    private void initializeAdapter(){

        rv.setAdapter(adapter);
    }

    




}


