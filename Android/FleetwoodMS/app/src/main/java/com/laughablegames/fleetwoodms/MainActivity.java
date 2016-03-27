package com.laughablegames.fleetwoodms;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    //MARK: Configure URLS
    String oneURL = "";
    String twoURL = "";
    String threeURL = "";
    String fourURL = "";
    String fiveURL = "";
    String sixURL = "";
    Integer Screen = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        //MARK: Buttons

        final ImageButton Back = (ImageButton) findViewById(R.id.imageButton);
        final ImageButton One = (ImageButton) findViewById(R.id.imageButton2);
        final ImageButton Two = (ImageButton) findViewById(R.id.imageButton3);
        final ImageButton Three = (ImageButton) findViewById(R.id.imageButton4);
        final ImageButton Forward = (ImageButton) findViewById(R.id.imageButton5);

        //MARK: Firebase

        Firebase ref = new Firebase("https://FWMS.firebaseio.com/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());
                oneURL = snapshot.child("SixthGradeURL").getValue().toString();
                twoURL = snapshot.child("SeventhGradeURL").getValue().toString();
                threeURL = snapshot.child("EighthGradeURL").getValue().toString();

                fourURL = snapshot.child("LMURL").getValue().toString();
                fiveURL = snapshot.child("CALURL").getValue().toString();
                sixURL = snapshot.child("HACURL").getValue().toString();


                System.out.println("First URL: " + oneURL);
                System.out.println("Second URL: " + twoURL);
                System.out.println("Third URL: " + threeURL);


                System.out.println("LunchMenu URL: " + fourURL);
                System.out.println("Calender URL: " + fiveURL);
                System.out.println("HAC URL: " + sixURL);
            }



            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });



        //MARK: WebView

        mWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.clearCache(true);
        mWebView.loadUrl("file:///android_asset/Welcome.html");

        //MARK: Actions

        One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Screen == 0) {
                    mWebView.loadUrl(oneURL);
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fourURL));
                    startActivity(browserIntent);
                }

            }
        });
        Two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Screen == 0) {
                    mWebView.loadUrl(twoURL);
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fiveURL));
                    startActivity(browserIntent);
                }
            }
        });
        Three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Screen == 0) {
                    mWebView.loadUrl(threeURL);
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sixURL));
                    startActivity(browserIntent);
                }

            }
        });
        Forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Screen:" + Screen);
                if (Screen == 1) {
                    One.setImageResource(R.drawable.sixth);
                    Two.setImageResource(R.drawable.seventh);
                    Three.setImageResource(R.drawable.eighth);
                    Screen = 0;
                } else {
                    One.setImageResource(R.drawable.burger);
                    Two.setImageResource(R.drawable.calender);
                    Three.setImageResource(R.drawable.house);
                    Screen = 1;
                }
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Screen:" + Screen);
                if(Screen == 1){
                    One.setImageResource(R.drawable.sixth);
                    Two.setImageResource(R.drawable.seventh);
                    Three.setImageResource(R.drawable.eighth);
                    Screen = 0;
                }else {
                    One.setImageResource(R.drawable.burger);
                    Two.setImageResource(R.drawable.calender);
                    Three.setImageResource(R.drawable.house);
                    Screen = 1;
                }
            }
        });
    }



}

