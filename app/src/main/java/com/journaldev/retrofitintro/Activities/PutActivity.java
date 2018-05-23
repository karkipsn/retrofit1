package com.journaldev.retrofitintro.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.journaldev.retrofitintro.api.APIClient;
import com.journaldev.retrofitintro.api.APIInterface;
import com.journaldev.retrofitintro.R;
import com.journaldev.retrofitintro.pojo.Patch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by colors2web on 3/23/2018.
 */

public class PutActivity extends AppCompatActivity {
    TextView Job,Name,information;
    Button Put;
    String name,job;
    APIInterface apiInterface;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);
        Name = findViewById(R.id.tv_name);
        Job = findViewById(R.id.tv_job);
        information=findViewById(R.id.information);
        Put = findViewById(R.id.btn_putR);
        information =  findViewById(R.id.information);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                Log.d("Name",name);
                String job = Job.getText().toString();
                putUser(name,job);
            }
        });
    }
    private  void putUser(String name,String job){
        String Path = "2";
        Patch userput = new Patch(name,job);// instance of input class
        Call<Patch> call = apiInterface.doPutUser(Path,userput);

        call.enqueue(new Callback<Patch>() {

            @Override
            public void onResponse(Call<Patch> call, Response<Patch> response) {
                String displayResponse = "";
                Patch userput1 = response.body();
                String user =userput1.name;
                String job= userput1.job;
                String updated = userput1.updatedAt;
                displayResponse += " User: " + user+"\n"+ " Job: " +  job +"\n"+  " Updated At: "+ updated +"\n";
                Toast.makeText(getApplicationContext(), "User : " + userput1.name +System.getProperty("line.separator")+ "Job : " + userput1.job + System.getProperty("line.separator") +   "Updated At : " + userput1.updatedAt, Toast.LENGTH_SHORT).show();
                 information.setText(displayResponse);
            }
            @Override
            public void onFailure(Call<Patch> call, Throwable t) {
                call.cancel();

            }

        });}
}
