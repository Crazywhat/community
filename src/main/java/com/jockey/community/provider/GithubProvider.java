package com.jockey.community.provider;

import com.alibaba.fastjson.JSON;
import com.jockey.community.dto.AccessTokenDTO;
import com.jockey.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public static final MediaType mediaType
            = MediaType.get("application/json; charset=utf-8");

    public String getAccessToken(AccessTokenDTO accessTokenDTO){

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {

            return response.body().string().split("&")[0].split("=")[1];

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }

    public GithubUser getGithubUser(String token){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user" + "?access_token=" + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return JSON.parseObject(response.body().string(),GithubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
