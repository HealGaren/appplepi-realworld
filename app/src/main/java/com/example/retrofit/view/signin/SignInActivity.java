package com.example.retrofit.view.signin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofit.R;
import com.example.retrofit.api.RealWorldClient;
import com.example.retrofit.data.SignInUserData;
import com.example.retrofit.databinding.ActivitySignInBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        Log.e("test","aaa2");
        binding.signinLoginBtn.setOnClickListener(v -> {

            Log.e("test","v3v");
            final String email = binding.signinEmailEtv.getText().toString();
            final String password = binding.signinPasswordEtv.getText().toString();
            SignInUserData signInUserData = new SignInUserData(email, password);
            Call<SignInUserData> call = RealWorldClient.getApiService().login(signInUserData);

            call.enqueue(new Callback<SignInUserData>() {
                @Override
                public void onResponse(Call<SignInUserData> call, Response<SignInUserData> response) {
                    Log.e("respose", response.toString());
                    if (response.isSuccessful()) {

                        final SignInUserData responceData = response.body();
/*                        RealWorldClient.token = responceData.token;*/
                        Log.e("test","success");

                    }

                }

                @Override
                public void onFailure(Call<SignInUserData> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        });
    }

}