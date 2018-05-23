package com.journaldev.retrofitintro.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.journaldev.retrofitintro.api.APIClient;
import com.journaldev.retrofitintro.api.APIInterface;
import com.journaldev.retrofitintro.R;
import com.journaldev.retrofitintro.pojo.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by colors2web on 3/23/2018.
 */

public class PostUserActivity extends AppCompatActivity{
    TextView Name,Job,information;
    Button post1;
    APIInterface apiInterface;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Name = findViewById(R.id.tv_name1);
        Job = findViewById(R.id.tv_job1);
        information =findViewById(R.id.information);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        post1 = findViewById(R.id.btn_postR);
        post1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= Name.getText().toString();
                String job= Job.getText().toString();
                postUser(name,job);
            }
        });
    }

    private void postUser(String name, String job) {

            User user = new User(name, job);

            Call<User> call = apiInterface.createUser(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    String displayresponse="";
                    User user1 = response.body();
                    String user = user1.name;
                    String job = user1.job;
                    String id = user1.id;
                    String created = user1.createdAt;
//                    String s= getIntent().getStringExtra("Session Id");
//                    Log.d("Session_id",s);

                    displayresponse+= " User: " + user+"\n"+ " Job: " +  job +"\n"+ " Id:"+ id +"\n"+ " Created At: "+ created +"\n" ; //+" Session Id: "+ s +"\n"
                    information.setText(displayresponse);

                    Toast.makeText(getApplicationContext(), "User : " + user1.name +System.getProperty("line.separator")+ "Job : " + user1.job + System.getProperty("line.separator") + "Id : " + user1.id + System.getProperty("line.separator") + "Created At : " + user1.createdAt, Toast.LENGTH_SHORT).show();
                    // System.getProperty("line.seperator") used for new line in Toast activity
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    call.cancel();
                }
            });

        }
    }

