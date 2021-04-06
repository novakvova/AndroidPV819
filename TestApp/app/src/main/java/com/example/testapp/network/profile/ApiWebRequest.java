package com.example.testapp.network.profile;

import com.example.testapp.dto.profile.ProfileResultDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiWebRequest {
    @GET("/api/user/profile")
    public Call<ProfileResultDTO> profile();
}
