package com.roy.demoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://www.googleapis.com/books/v1/";
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GoogleBooksAPI googleBooksAPI = retrofit.create(GoogleBooksAPI.class); //retrofit will provide the dec

        Call<SearchResult> call = googleBooksAPI.getPosts();

        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                SearchResult result =  response.body();
                textViewResult.append("No. of Results: "+result.getNoOfitems());

                List<Book> books = result.getBooks();

                for (Book book: books) {
                    String con="\n\n";
                    con+=book.getBookInfo().getTitle()+"\n";
                    con+=book.getBookInfo().getAuthors()[0]+"\n";
                    con+=book.getBookInfo().getDescription()+"\n";

                    textViewResult.append(con);
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                //error in parsing or url
                textViewResult.setText(t.getMessage());
            }
        });

        //for background thread
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                if(!response.isSuccessful()){
//                    textViewResult.setText("Code: "+response.code());
//                    return;
//                }
//                List<Post> posts =  response.body();
//                for (Post post:posts) {
//                    String content ="";
//                    content+="ID: "+post.getId() +"\n";
//                    content+="Title "+post.getTitle()+"\n";
////                    Log.e("Test", "onCreate: "+content);
//                    textViewResult.append(content);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//
//                //error in parsing or url
//                textViewResult.setText(t.getMessage());
//            }
//        });
    }
}