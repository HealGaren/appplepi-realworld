package com.example.retrofit.view.signin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.retrofit.R;
import com.example.retrofit.api.RealWorldClient;
import com.example.retrofit.data.NewUserData;
import com.example.retrofit.data.SignInUserData;
import com.example.retrofit.data.SignInUserRequestData;
import com.example.retrofit.data.SignInUserResponseData;
import com.example.retrofit.data.UserData;
import com.example.retrofit.databinding.ActivitySignInBinding;
import com.example.retrofit.view.articlelist.ArticleListActivity;
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

            final String email = binding.signinEmailEtv.getText().toString();
            final String password = binding.signinPasswordEtv.getText().toString();

            if (email.equals("") || email.equals(" ")) {
                setErrorAlarm("이메일을 입력해주세요.");
            } else if (password.equals("") || password.equals(" ")) {
                setErrorAlarm("비밀번호를 입력해주세요.");
            } else {


                SignInUserRequestData signInUserData = new SignInUserRequestData(new SignInUserData(email, password));
                Call<SignInUserResponseData> call = RealWorldClient.getApiService().login(signInUserData);

                call.enqueue(new Callback<SignInUserResponseData>() {
                    @Override
                    public void onResponse(Call<SignInUserResponseData> call, Response<SignInUserResponseData> response) {
                        Log.e("respose", response.toString());

                        if (response.isSuccessful()) {

                            final SignInUserResponseData responseData = response.body();
                            RealWorldClient.token = responseData != null ? responseData.userData.token : null;
                            Log.e("test", "success");

                            Intent intent = new Intent(getApplicationContext(), ArticleListActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            setErrorAlarm("이메일 또는 비밀번호가 잘못 입력되었습니다.");
                        }

                    }

                    @Override
                    public void onFailure(Call<SignInUserResponseData> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

        });

/*        binding.signinSignupTv.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(intent);
        }*/

    }

    private void setErrorAlarm(String text) {
        binding.signinErrorTv.setText(text);
        if (binding.signinErrorTv.getVisibility() == View.GONE) {
            binding.signinErrorTv.setVisibility(View.VISIBLE);
        }
    }


}