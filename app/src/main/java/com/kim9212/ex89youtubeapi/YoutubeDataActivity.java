package com.kim9212.ex89youtubeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class YoutubeDataActivity extends AppCompatActivity {

    EditText et;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_data);


        et=findViewById(R.id.et);
        tv=findViewById(R.id.tv);

        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String key="AIzaSyC_RMZvAHL45VzU2BaxO6fCjrTPMP1MSdg";
                String part="snippet";
                String query=et.getText().toString();
                int maxResults=10;

                Retrofit retrofit=RetrofitHelper.getInstance();
                RetrofitService retrofitService= retrofit.create(RetrofitService.class);
                Call<String> call=retrofitService.searchVideos(key,part,query,maxResults);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String data=response.body();
                        tv.setText(data);

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {


                    }
                });

                return false;
            }
        });

    }

    public void clicksearch(View view) {

        String key="AIzaSyC_RMZvAHL45VzU2BaxO6fCjrTPMP1MSdg";
        String part="snippet";
        String query=et.getText().toString();
        int maxResults=10;



        //data api사용
       //검색기능 api는 rest방식으로 데이터(json)를 제공
        //GET https://www.googleapis.com/
        //요청 파라미터:key(필수),part(필수),q(검색어),masxresult(결과 개수지정0-50)
        //Retrofit -결과를 우선은 String으로 받기
        Retrofit retrofit=RetrofitHelper.getInstance();
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);
        Call<String> call=retrofitService.searchVideos(key,part,query,maxResults);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String data=response.body();
                tv.setText(data);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {


            }
        });



    }
}