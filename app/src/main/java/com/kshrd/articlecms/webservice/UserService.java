package com.kshrd.articlecms.webservice;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by pirang on 6/20/17.
 */

public interface UserService {

    @Multipart
    @POST("/v1/api/users")
    Call<JsonObject> createUser(
            @Part("EMAIL") RequestBody email,
            @Part("USERNAME") RequestBody username,
            @Part("PASSWORD") RequestBody password,
            @Part("GENDER") RequestBody gender,
            @Part("FACEBOOK_ID") RequestBody fbId,
            @Part("TELEPHONE") RequestBody tel,
            @Part MultipartBody.Part photo
    );

}
