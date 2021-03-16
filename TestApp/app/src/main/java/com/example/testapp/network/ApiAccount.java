package com.example.testapp.network;

import com.example.testapp.dto.LoginDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiAccount {
    @POST("/api/account/login")
    public Call login(@Body LoginDto loginDto);
}
