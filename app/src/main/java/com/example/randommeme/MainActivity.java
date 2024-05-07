package com.example.randommeme;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONException;


public class MainActivity extends AppCompatActivity {
    private ImageView imageview;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Loadmeme();
    }
    public void Loadmeme()
    {
        progressBar=findViewById(R.id.progressbar);
        imageview=findViewById(R.id.memeimageview);
        progressBar.setVisibility(View.VISIBLE);
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);


// Request a string response from the provided URL.
        String url = "https://meme-api.com/gimme";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET,url, null, response -> {
                    String url1;
                    try {
                        url1 = response.getString("url");
                        Glide.with(MainActivity.this).load(url1).listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.setVisibility(View.INVISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.setVisibility(View.INVISIBLE);
                                return false;
                            }
                        }).into(imageview);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }



                }, error -> {
                    // TODO: Handle error

                });

// Add the request to the RequestQueue.
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }





    public void ShareMeme(View view) {
        String url = "https://meme-api.com/gimme";
        Intent i=new Intent(Intent.ACTION_SEND);
        Uri imageuri=Uri.parse("https://meme-api.com/gimme");
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_STREAM,imageuri);
        Intent chooser=Intent.createChooser(i,"Share this meme using");
        startActivity(chooser);
    }

    public void nextMeme(View view) {
        Loadmeme();
    }
}