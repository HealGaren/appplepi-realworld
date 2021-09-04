package com.example.retrofit.view.signin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofit.R;
import com.example.retrofit.api.RealWorldClient;
import com.example.retrofit.data.SignInUserData;
import com.example.retrofit.data.UserData;
import com.example.retrofit.databinding.ActivitySignInBinding;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);

        binding.signinLoginBtn.setOnClickListener(v -> {

            Log.e("click","aa");

            final String email = binding.signinEmailEtv.getText().toString();
            final String password = binding.signinPasswordEtv.getText().toString();
            SignInUserData signInUserData = new SignInUserData(new UserData(email, password));
            Call<SignInUserData> call = RealWorldClient.getApiService().login(signInUserData);

            call.enqueue(new Callback<SignInUserData>() {
                @Override
                public void onResponse(Call<SignInUserData> call, Response<SignInUserData> response) {
                    Log.e("respose", response.toString());

                    if (response.isSuccessful()) {

                        final SignInUserData responseData = response.body();
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

/*    private void addToken(String token) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request;

                return null;
            }
        }
    }*/

}