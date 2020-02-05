package com.aaif_seriex.flo.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aaif_seriex.flo.R;
import com.aaif_seriex.flo.data.CommunityDataAdapter;
import com.aaif_seriex.flo.model.Post;
import com.aaif_seriex.flo.util.Config;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Cliff on 9/28/2017.
 */

public class Community extends AppCompatActivity implements CommunityDataAdapter.ItemClickListener,DialogInterface.OnDismissListener, SwipeRefreshLayout.OnRefreshListener {

        private static ArrayList<Post> dataCommunity;
        private static RecyclerView CommunityRecycler;
        private static CommunityDataAdapter communityDataAdapter;
        private static final String TAG = "MyAmateurVideoList";
        Config mfunc = new Config();
        static String URL_FEED, name,userid;
        //AmEditDialog.EditNameDialogListener listener;
        int mStackLevel = 0;
        public static final int DIALOG_FRAGMENT = 1;
        static Context context;
        SharedPreferences prefs;
        private static final String MY_PREFS_NAME = "kumawood";
        SwipeRefreshLayout mSwipeRefreshLayout;
        Post item;
        public static TextView no_upload;
        //ShareDialog shareDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup main view with navigation drawer
        setContentView(R.layout.community);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = getSharedPreferences(MY_PREFS_NAME, 0);
        name = prefs.getString("username", null);
        userid = prefs.getString("idtoken", null);

        Log.d(TAG, "onCreateView: userid "+userid);
        dataCommunity = new ArrayList<Post>();

        URL_FEED = "https://api.androidhive.info/feed/feed.json";
        Log.d(TAG, "onCreateView: myprofile  "+userid);

        // Inflate the layout for this fragment
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int numb_col = 1;
        if(width > height){
            numb_col = 2;
        }


        CommunityRecycler = (RecyclerView)findViewById(R.id.rv_community);
        CommunityRecycler.setHasFixedSize(false);
        final RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,numb_col);
        CommunityRecycler.setLayoutManager(layoutManager);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        no_upload       = (TextView)findViewById(R.id.no_uploads);

        //  mSwipeRefreshLayout.setOnRefreshListener(getActivity());
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        /*** Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used*/

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivityApp.class);
                startActivity(i);
            }
        });

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                // Fetching data from server
                try{
                    clear();
                    loadjson();
                }catch (Exception e){
                    e.printStackTrace();
                }

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try{
                    clear();
                    loadjson();
                }catch(Exception e){
                    Log.d(TAG, "onRefresh: ---- ");
                }
            }
        });

        //   amateurVideoDataAdapter = new AmVideoDataAdapter(dataAmateur,getContext(),this);
        // loadjson();
        CommunityRecycler.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (CommunityRecycler == null || CommunityRecycler.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                mSwipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });



        if (savedInstanceState != null) {
            mStackLevel = savedInstanceState.getInt("level");
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("level", mStackLevel);
    }

    public void loadjson(){


        RequestQueue queue = Volley.newRequestQueue(this);
        mSwipeRefreshLayout.setRefreshing(true);
        // Request a string response from the provided URL.
        //StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_FEED, new com.android.volley.Response.Listener<String>() {
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // Display the first 500 characters of the response string.
                try {
                    if(response.length() > 25){
                        Log.d(TAG, "onResponse myjson : " + response);
                      //  no_upload.setVisibility(View.GONE);
                        //JsonAmVideo(response,getApplicationContext());
                       // JsonAmVideo(response,getApplicationContext());
                    }else if (response.length() < 20){
                        parseJsonFeed(response);
                        //no_upload.setVisibility(View.VISIBLE);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                  //  Log.d(TAG, "onResponse:::::: "+response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                   Log.d(TAG, "onErrorResponse: It did not work "+ error.getMessage());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsonReq);

    }

    /**
     * Parsing json reponse and passing the data to feed view list adapter
     * */
    private void parseJsonFeed(JSONObject response) {
        try {
            Log.d(TAG, "parseJsonFeed: "+ response);
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) feedArray.get(i);


               /* item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setImge(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));*/

                // url might be null sometimes


                item = new Post();
                Log.d(TAG, "JsonAmVideo: "+ jsonObject.getString("name"));
               /* int id = jsonObject.getInt("id");
                item.setId(id);*/
                item.setTitle(jsonObject.getString("name"));
                item.setDescription(jsonObject.getString("status"));
                // url might be null sometimes
                String feedUrl = jsonObject.isNull("image") ? null : jsonObject.getString("image");
                item.setImg(feedUrl);

                dataCommunity.add(item);
            }

            // notify data changes to list adapater
            // notify data changes to list adapater
            communityDataAdapter = new CommunityDataAdapter(dataCommunity,getApplicationContext(),Community.this);
            CommunityRecycler.setAdapter(communityDataAdapter);
            communityDataAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        } catch (JSONException e) {
            Log.d(TAG, "parseJsonFeed: errr "+e.getMessage());
            e.printStackTrace();
        }
    }

    public void JsonAmVideo(String json, Context context) throws JSONException {

        try {
            Log.d(TAG, "JsonAmVideo: inside "+json);
            JSONObject jsonRootObject;
            //jsonRootObject = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));
                jsonRootObject = new JSONObject(json);
            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = new JSONArray();
            jsonArray = null;
            jsonArray = jsonRootObject.getJSONArray("feed");
            Log.d(TAG, "JsonAmVideo: array "+jsonArray);
            if(jsonArray != null) {

                //Iterate the jsonArray and print the info of JSONObjects
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Log.d(TAG, "JsonAmVideo: ==  "+ jsonObject);
                     item = new Post();
                    Log.d(TAG, "JsonAmVideo: "+ jsonObject.getString("title"));
                    item.setId(jsonObject.getString("id"));
                    item.setTitle(jsonObject.getString("name"));
                    item.setDescription(jsonObject.getString("status"));
                    // url might be null sometimes
                    String feedUrl = jsonObject.isNull("image") ? null : jsonObject.getString("image");
                    item.setImg(feedUrl);

                    dataCommunity.add(item);
                  // Log.d(TAG, "JsonAmVideo: "+jsonObject.getString("no_watched"));
                }
            }
            // notify data changes to list adapater
            communityDataAdapter = new CommunityDataAdapter(dataCommunity,getApplicationContext(),Community.this);
            CommunityRecycler.setAdapter(communityDataAdapter);
            communityDataAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        } catch (JSONException e){
            {e.printStackTrace();}
               Log.e(TAG, "parssing  error " + e.getMessage());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
       //loadjson();
    }

    @Override
    public void onItemClicked(View view, int i, Post m, String action) {
        Log.d(TAG, "onItemClicked: first m:  " + m.getParent() + " description " + m.describeContents());
       if(action.equals("facebook")){
            Log.d(TAG, "onItemClicked: - facebook item ");
            shareContentToFacebook(dataCommunity.get(i).getTitle(),dataCommunity.get(i).getImg(),dataCommunity.get(i).getDescription());
        }else if(action.equals("whatsapp")){
            Log.d(TAG, "onItemClicked: - whatsapp item ");
            String msg = dataCommunity.get(i).getTitle() + "";
            try{
                whatsappShare(msg,dataCommunity.get(i).getImg());
             //   whatsappShare(msg,dataCommunity.get(i).getImg());
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(action.equals("google")){
            //Log.d(TAG, "onItemClicked: - google item ");
            String msg =  dataCommunity.get(i).getTitle() + "";
           try{
               whatsappShare(msg,dataCommunity.get(i).getImg());
             //  googleShare(msg,dataCommunity.get(i).getImg());
           }catch (Exception e){
               Log.d(TAG, "onItemClicked: "+ e.getMessage());
           }
        }else if(action.equals("")){

       /*     Intent resultIntent = new Intent();
            resultIntent.putExtra("911", "String data");
            getActivity().setResult(Activity.RESULT_OK, resultIntent);
            getActivity().finish();*/
           //   goToPreview(m);
        }
    }
    private void shareContentToFacebook(String title,String img,String description){
       /* ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentTitle(title)
                .setImageUrl( Uri.parse(img))
                .setContentDescription(
                       description)
                .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.mtech.kumawood"))
                .build();

        ShareApi.share(linkContent, null);*/

     /*  shareDialog = new ShareDialog(this);
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle(title)
                    .setImageUrl( Uri.parse(img.replace(" ", "%20")))
                    .setContentDescription(
                            "https://kumawoodapp.com/share/share.php?userid="+mfunc.getUserId(getActivity()))
                    .setContentUrl(Uri.parse("https://www.facebook.com/Kumawood-314620652213760/"))
                    .build();

            shareDialog.show(linkContent);
        }*/
    }

    private void googleShare(final String content, String img){
        /*final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Glide
                .with(getContext())
                .load(img)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {

                        Intent shareIntent = new PlusShare.Builder(getActivity())
                                .setText(content)
                                .setType("image/jpeg").setStream(getImageUri(getContext(),resource))
                                .getIntent();

                        startActivityForResult(shareIntent, 0);

                        try {
                            progressDialog.hide();
                            startActivity(shareIntent);
                        } catch (android.content.ActivityNotFoundException ex) {
                            Log.d(TAG, "Google: Google have not been installed");
                        }

                    }
                });


        Uri uri = Uri.parse(img);*/

    }

    private void whatsappShare(final String content, String img){

        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Glide
                .with(getApplicationContext())
                .load(img)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {

                        //image.setImageBitmap(resource); // Possibly runOnUiThread()
                        //Target whatsapp:

                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);

                        shareIntent.setPackage("com.whatsapp");
//Add text and then Image URI
                        shareIntent.putExtra(Intent.EXTRA_TEXT,content);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, getImageUri(getApplicationContext(),resource));
                        shareIntent.setType("image/jpeg");
                        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        try {
                            progressDialog.hide();
                            startActivity(shareIntent);
                        } catch (android.content.ActivityNotFoundException ex) {
                            Log.d(TAG, "whatsappShare: Whatsapp have not been installed");
                        }

                    }
                });


    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Log.d(TAG, "onDismiss: -----");
        try{
            dataCommunity = new ArrayList<Post>();
            communityDataAdapter.notifyDataSetChanged();
            CommunityRecycler.invalidate();
            loadjson();
        }catch (Exception e){
            Log.d(TAG, "onDismiss: -- "+e.getMessage());
        }
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: ");
       onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        try{
            mSwipeRefreshLayout.setRefreshing(true);
            // Update the adapter and notify data set changed
            // ...
            Log.d(TAG, "onItemsLoadComplete: ");
            loadjson();
            // Stop refresh animation
            mSwipeRefreshLayout.setRefreshing(false);
        }catch(Exception e){
            Log.d(TAG, "onItemsLoadComplete: "+ e.getMessage());
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        if (dataCommunity != null) {
            dataCommunity.clear();
        }
    }



}
