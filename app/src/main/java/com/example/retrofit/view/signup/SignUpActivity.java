package com.example.retrofit.view.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.retrofit.R;
import com.example.retrofit.api.RealWorldClient;
import com.example.retrofit.data.NewUserData;
import com.example.retrofit.data.NewUserRequest;
import com.example.retrofit.data.UserResponseData;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    String emile;
    String pew;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText inputEmileEditText = findViewById(R.id.inputEmileEditText);
        EditText inputPasswordEditText = findViewById(R.id.inputPasswordEditText);
        EditText inputNameEdText = findViewById(R.id.inputNameEdText);
        Button signButton = findViewById(R.id.signButton);





        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emile=inputEmileEditText.getText().toString();
                pew=inputPasswordEditText.getText().toString();
                name=inputNameEdText.getText().toString();

                NewUserData newUserData= new NewUserData(name,emile,pew);
                NewUserRequest newUserRequest=new NewUserRequest(newUserData);


                Log.d("SignUpActivity","이메일"+emile+"\n비밀번호"+pew+"\n이름"+name);


                Call<UserResponseData> call = RealWorldClient.getApiService().signUp(newUserRequest);
                call.enqueue(new Callback<UserResponseData>() {
                    @Override
                    public void onResponse(Call<UserResponseData> call, Response<UserResponseData> response) {
                        if(response.isSuccessful()){
                            finishAffinity();
                        }else{
                            try {

                                Log.e("SignUpActivity",response.errorBody().string());
                                Log.d("SignUpActivity","이메일"+emile+"\n비밀번호"+pew+"\n이름"+name);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                    }



                    @Override
                    public void onFailure(Call<UserResponseData> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });



    }
}