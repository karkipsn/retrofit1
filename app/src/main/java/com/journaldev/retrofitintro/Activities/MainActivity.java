package com.journaldev.retrofitintro.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.journaldev.retrofitintro.api.APIClient;
import com.journaldev.retrofitintro.api.APIInterface;
import com.journaldev.retrofitintro.R;
import com.journaldev.retrofitintro.pojo.Datum;
import com.journaldev.retrofitintro.pojo.LoginUser;
import com.journaldev.retrofitintro.pojo.MultipleResource;
import com.journaldev.retrofitintro.pojo.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by colors2web on 3/20/2018.
 */

public class MainActivity extends AppCompatActivity {

    TextView information,Email,Password;
    Button Login,GetUser,PostUser,PostForm,GetMultiResources,PutUser,Patch;
    ImageView img;

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        information =  findViewById(R.id.information);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Email=findViewById(R.id.tv_email);
        Password=findViewById(R.id.tv_password);
        Login=findViewById(R.id.btnlogin);
        GetUser=findViewById(R.id.btn_getuser);
        PostUser=findViewById(R.id.btn_postuser);
        PostForm=findViewById(R.id.btn_postform);
        GetMultiResources=findViewById(R.id.btn_multiresource);
        PutUser=findViewById(R.id.btn_put);
        Patch = findViewById(R.id.btn_patch);
        img= findViewById(R.id.img_view);

        GetMultiResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMultipleResource();
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=Email.getText().toString();
                String password=Password.getText().toString();
                Log.d("Email",email);
                Log.d("Password",password);

                if(email.isEmpty()|| password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Login is not Successful. PLease enter the not null Values",Toast.LENGTH_SHORT).show();

                }
                else {
                    loginUser(email, password);
                }
            }
        });
        GetUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUser();
            }
        });
        PostUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(MainActivity.this,PostUserActivity.class);
//                in.putExtra("Session Id",54);
                startActivity(in);
            }
        });
        PostForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postForm();
            }
        });
        PutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PutActivity.class);
                startActivity(i);

            }
        });
        Patch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PatchActivity.class);
                startActivity(i);

            }
        });
    }

    private void getMultipleResource(){
        /** GET List Resources **/

        Call<MultipleResource> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {

                Log.d("TAG",response.code()+"");
                String displayResponse = "";

                MultipleResource resource = response.body();
                Integer text = resource.page;
                Integer total = resource.total;
                Integer totalPages = resource.totalPages;
                List<MultipleResource.Datum> datumList = resource.data;//for internal class Datum of class MultipleResource
                //List<Datum> datumList = resource.data;//for public POJO class Datum

                displayResponse += " Page: " + text+"\n"+ " Total: " +  total +"\n"+  " Total Pages: "+ totalPages +"\n";
                for (MultipleResource.Datum datum : datumList) {
                    displayResponse += "Id: " + datum.id + " \n" +"Name: "+ datum.name + " \n" +"Pantone_Value: "+ datum.pantoneValue + "\n "+"Year: " + datum.year + "\n";
                }

                information.setText(displayResponse);

            }
            @Override
            public void onFailure(Call<MultipleResource> call, Throwable t) {
                call.cancel();
            }
        });
    }
    private void loginUser(String email,String password){

        LoginUser luser = new LoginUser(email,password);

        Call<LoginUser> call = apiInterface.postUser(luser);
        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                LoginUser login =response.body();
                Log.d("response-success", response.body().toString());
                Log.d("Login-success",login.token.toString());
                Toast.makeText(getApplicationContext(),"Successful Login",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"TOKEN:" + login.token.toString(),Toast.LENGTH_LONG).show();
                information.setText(login.token.toString());
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", call.toString());
            }

        });

    }
    private void getUser(){
        /**
         GET List Users
         **/
        Call<UserList> call = apiInterface.doGetUserList("2");
        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                String displayResponse="";
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
//                List<UserList.Datum> datumList = userList.data;
                List<Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), " page:" + userList.page +System.getProperty("line.Separator") + " total\n" + total + " totalPages\n" + totalPages  , Toast.LENGTH_SHORT).show();
                    displayResponse+=" Page: " + text+"\n"+ "Total: " + total +"\n"+ "TotalPages: " + totalPages +"\n";

                for (Datum datum : datumList) {
                    Object img1 = Glide.with(MainActivity.this).load(datum.avatar).override(200, 100).centerCrop().into(img);

                            Toast.makeText(getApplicationContext(), "id : " + datum.id +"\n"+ " name: " + datum.first_name + " " + datum.last_name +"\n"+ " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                    displayResponse += "Id: "+ datum.id +"\n"+ "Name: "+ datum.first_name +" "+  datum.last_name + "\n" + img1 +"\n";
                   // Glide.with(MainActivity.this).load(datum.avatar).override(200, 100).centerCrop().into(img);

                }
                information.setText(displayResponse);
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });
    }


    private void postForm(){
        /**
         POST name and job Url encoded.
         **/
        Call<UserList> call = apiInterface.doCreateUserWithField("morpheus","leader");
        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
//                List<UserList.Datum> datumList = userList.data;
                List<Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

//                for (UserList.Datum datum : datumList) {
                for (Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }
                information.setText(null);

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });

    }
}
