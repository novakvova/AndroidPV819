package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.testapp.constants.Urls;
import com.example.testapp.dto.LoginDto;
import com.example.testapp.dto.LoginResultDto;
import com.example.testapp.dto.LoginValidationDTO;
import com.example.testapp.dto.RegisterValidationDTO;
import com.example.testapp.network.AccountService;
import com.example.testapp.network.ImageRequester;
import com.example.testapp.utils.CommonUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ImageRequester imageRequester;
    private NetworkImageView myImage;
    private TextView textInvalid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String url = Urls.BASE+ "/images/1.jpg";

        imageRequester = ImageRequester.getInstance();
        myImage = findViewById(R.id.myimg);
        imageRequester.setImageFromUrl(myImage, url);
        textInvalid = findViewById(R.id.textLoginInvalid);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //TextView headerView = (TextView) findViewById(R.id.selectedMenuItem);
        switch(id){
            case R.id.exit_settings:
                this.finishAffinity();
                return true;
        }
        //headerView.setText(item.getTitle());
        return super.onOptionsItemSelected(item);
    }


    public void onClickLogin(View v) {
        final TextInputEditText email = findViewById(R.id.textInputEmail);
        final TextInputLayout emailLayout = findViewById(R.id.textFieldEmail);
        final TextInputEditText password = findViewById(R.id.textInputPassword);
        final TextInputLayout passwordLayout = findViewById(R.id.textFieldPassword);

        LoginDto loginDto = new LoginDto(
                email.getText().toString(),
                password.getText().toString()
        );

//        if(loginDto.getEmail().isEmpty()) {
//            emailLayout.setError("Пуста пошта!");
//            return;
//        }
//        else
//            emailLayout.setError("");
//
//        if(loginDto.getPassword().isEmpty()) {
//            passwordLayout.setError("Пустий пароль!");
//            return;
//        }
//        else
//            passwordLayout.setError("");
        CommonUtils.showLoading(this);
        AccountService.getInstance()
                .getJSONApi()
                .login(loginDto)
                .enqueue(new Callback<LoginResultDto>() {
                    @Override
                    public void onResponse(Call<LoginResultDto> call, Response<LoginResultDto> response) {
//                        Log.d("super","Ok result good");
                        CommonUtils.hideLoading();
                        if(response.isSuccessful())
                        {

                        }
                        else
                        {
                            try {
                                String json = response.errorBody().string();
                                LoginValidationDTO result = new Gson().fromJson(json, LoginValidationDTO.class);
                                String str="";
                                if(result.getErrors().getEmail()!=null)
                                {
                                    for (String item: result.getErrors().getEmail()) {
                                        str+=item+"\n";
                                    }
                                }
                                emailLayout.setError(str);

                                str="";
                                if(result.getErrors().getPassword()!=null)
                                {
                                    for (String item: result.getErrors().getPassword()) {
                                        str+=item+"\n";
                                    }
                                }
                                passwordLayout.setError(str);

                                str="";
                                if(result.getErrors().getInvalid()!=null)
                                {
                                    for (String item: result.getErrors().getInvalid()) {
                                        str+=item+"\n";
                                    }
                                }
                                textInvalid.setText(str);

                                Log.d("Bad request: ", json);
                            } catch (Exception ex) {

                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResultDto> call, Throwable t) {
                        Log.e("problem","problem API"+ t.getMessage());
                        CommonUtils.hideLoading();
                    }
                });
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}