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
 * Created by colors2web on 3/26/2018.
 */

public class PatchActivity extends AppCompatActivity{
    TextView Job,Name,information,Pathid;
    Button Patch,Delete;
    String name,job;
    APIInterface apiInterface;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patch);
        Name = findViewById(R.id.tv_name2);
        Job = findViewById(R.id.tv_job2);
        Pathid =findViewById(R.id.tv_pathid);
        Patch = findViewById(R.id.btn_patch);
        Delete = findViewById(R.id.btn_delete);
        information =  findViewById(R.id.information);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Patch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                Log.d("Name",name);
                String job = Job.getText().toString();
                String pathid = Pathid.getText().toString();
                doPatchUser(name,job,pathid);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pathid = Pathid.getText().toString();
                delete(pathid);
            }
        });
    }

    private void delete(final String pathid) {
        String Path = pathid;
//        Patch del = new Patch();
        Call<Patch> call =apiInterface.delete(Path);
        call.enqueue(new Callback<com.journaldev.retrofitintro.pojo.Patch>() {
            @Override
            public void onResponse(Call<Patch> call, Response<Patch> response) {
                Toast.makeText(getApplicationContext(),"USer Id: "+ pathid +"is deleted Successfully",Toast.LENGTH_LONG).show();
                information.setText(null);
            }

            @Override
            public void onFailure(Call<Patch> call, Throwable t) {
                Toast.makeText(getApplicationContext()," On Deleting USer Id: "+ pathid +" error Occured",Toast.LENGTH_LONG).show();
                call.cancel();

            }
        });
    }

    private void doPatchUser(String name, String job, String pathid) {
        String Path = pathid;
        Patch userput = new Patch(name,job);// instance of input class
        Call<Patch> call = apiInterface.doPatchUser(Path,userput);

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
