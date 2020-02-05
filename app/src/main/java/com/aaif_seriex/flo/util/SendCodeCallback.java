package com.aaif_seriex.flo.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;


/**
 * Created by Mtech on 19/10/2016.
 */
public class SendCodeCallback {
    private final String TAG = "SendCode";
    private ResponseCallback responseCallback;
    private Context myContext;
    private Config mfunc = new Config();

    public SendCodeCallback(Context myContext,@NonNull ResponseCallback listener) {

        this.myContext = myContext;
        this.responseCallback = listener;
    }

    public interface ResponseCallback {
        void onResponseReceived(int status, String result) throws JSONException;
    }



    public void sendRegistration( String name, String userid, String xxname,String pushid,String passwd) {

        final String url =mfunc.getAppUrl()+"/api/saveuser.php?name="+name+"&userid="+userid+"&pushid="+pushid+"&xxname="+xxname+"&xxpass="+passwd+"&lagent=kw";
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void gloPayment(String userid,String msisdn,String pkg){
       // final String url =  "http://197.220.160.135:8080/includes/classes/videosubscription.php?msisdn="+phone+"&pkg="+pkg;
        final String url =mfunc.getAppUrl()+"/subscription/glorequest.php?opera=request&pkg="+pkg+"&userid="+userid+"&msisdn="+msisdn;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming glo", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming glo", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void sendSmsVerificationCode(String pin, String userid,String msisdn,String pkg){
        final String url =mfunc.getAppUrl()+"/subscription/glorequest.php?opera=pin&pkg="+pkg+"&userid="+userid+"&msisdn="+msisdn+"&pin="+pin;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void resetPassword(String xxname){
        final String url =mfunc.getAppUrl()+"/api/resetpassword.php?opera=sendsms&xxname="+xxname;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void resetPasswordverify(String code, String xxname){
        final String url =mfunc.getAppUrl()+"/api/resetpassword.php?opera=verify&code="+code+"&xxname="+xxname;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public void VoucherCode(String code, String userid){
        final String url =mfunc.getAppUrl()+"/api/voucher.php?userid="+userid+"&code="+code;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void gloaddSubscription(String userid,String pkg, String msisdn){
        final String url = mfunc.getAppUrl()+"/xxxvideoSubscription.php?userid="+userid+"&pkg="+pkg+"&msisdn="+msisdn;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void getPhone() {

        final String url =mfunc.getAppUrl()+"/api/h.php";
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "getPhone: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void mtnAirtimeSubscription2(String phone,String stype,String userid) {
        final String url =mfunc.getAppUrl()+"/subscription/request.php?msisdn="+phone+"&stype="+stype+"&userid="+userid;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "mtnAirtime: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    /*public void uploadImageToServer(String pathToImageFile, String title, String description){
        Map<String, RequestBody> map    =   new HashMap<>();
        File videoFile = new File(pathToImageFile);
        RequestBody videoBody = RequestBody.create(MediaType.parse("video*//*"), videoFile);
      //  RequestBody videoBody = RequestBody.create(MediaType.parse("video*//**//*"), videoFile);
        MultipartBody.Part vFile = MultipartBody.Part.createFormData("video", videoFile.getName(), videoBody);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mfunc.getAppUrl()+"/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        KumawoodRequestInterface vInterface = retrofit.create(KumawoodRequestInterface.class);
        Call<ResultObject> serverCom = vInterface.am_uploadImageToServer(vFile,title,description);
        Log.d(TAG, "uploadImageToServer: "+serverCom.toString());
        serverCom.enqueue(new Callback<ResultObject>() {
            @Override
            public void onResponse(Call<ResultObject> call, retrofit2.Response<ResultObject> response) {
                ResultObject result = response.body();
                if(!TextUtils.isEmpty(result.getSuccess())){
                    Log.d(TAG, "Result " + result.getSuccess());
                }
                try {
                    responseCallback.onResponseReceived(200,result.getSuccess().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResultObject> call, Throwable t) {
                Log.d(TAG, "Error message-- uploadfiles to server -  " + t.getMessage());
            }
        });
    }*/

    public void am_img_details_updater(String userid, String imageString, String title, String description, String video_md5){
        final String url = mfunc.getAppUrl()+"/am_api/am_save_img_details.php?userid="+userid+"&title="+title+"&description="+description+"&video_md5="+video_md5+"&imgg="+imageString;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "image to server : "+ url);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void amGetV(String xxname,String xxpass){
        final String url = mfunc.getAppUrl()+"/api/login.php?xxname="+xxname + "&xxpass="+xxpass;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void serverLogin(String xxname,String xxpass){
        final String url = mfunc.getAppUrl()+"/api/login.php?xxname="+xxname + "&xxpass="+xxpass;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void mmPayment(String name,String phone,String userid,String mode,String pkg){
        final String url = mfunc.getAppUrl()+"/api/initiatemmpayment.php?userid="+userid +"&phone="+phone+"&mode="+mode+"&pkg="+pkg+"&name="+name;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void vodaPayment(String name,String phone,String token,String userid,String mode,String pkg){
        final String url = mfunc.getAppUrl()+"/api/initiatemmpayment.php?userid="+userid +"&phone="+phone+"&mode="+mode+"&pkg="+pkg+"&name="+name+"&token="+token;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void visaPayment(String fname,String lname,String email,String phone,String userid,String mode,String pkg){
        final String url = mfunc.getAppUrl()+"/api/initiatevisapayment.php?userid="+userid +"&phone="+phone+"&mode="+mode+"&pkg="+pkg+"&fname="+fname+"&lname="+lname+"&email="+email;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void checkSubscription(String userid,String canTest){
        final String url = mfunc.getAppUrl()+"/api/checksubscription.php?userid="+userid+"&deviceid="+mfunc.getDeviceId()+"&cantest="+canTest;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.getCache().clear();
        stringRequest.setShouldCache(false);
        queue.add(stringRequest);
    }

    public void getSliderVideos(){
        final String url = mfunc.getAppUrl()+"/api/getslidervideos.php";
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "getSliderVideos: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.getCache().clear();
        stringRequest.setShouldCache(false);
        queue.add(stringRequest);
    }


    public void getTotalQuery(String catg,String sw){
        final String url = mfunc.getAppUrl()+"/api/getquerytotal.php?catg="+catg+"&sw="+sw;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void editAmVideo(String title,String description, String imageurl, String video_md5 ){
        final String url = mfunc.getAppUrl()+"/am_api/am_getvideo.php?opera=edit&title="+title+"&description="+description+"&video_md5="+video_md5+"&imgg="+imageurl;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "editAm: "+ url);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void TermsConditionAmVideo(String userid){
        final String url = mfunc.getAppUrl()+"/am_api/am_getvideo.php?opera=terms&userid="+userid;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "editAm: "+ url);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void deleteAmVideo(String video_md5 ){
        final String url = mfunc.getAppUrl()+"/am_api/am_getvideo.php?opera=delete_video&video_md5="+video_md5;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "delete: "+ url);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not workk "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

  /*  public void AmVideo_views(String videoid){
        final String url = mfunc.getAppUrl()+"/am_api/am_getvideo.php?opera=views_video&videoid="+videoid;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "videoid: "+ url);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not workk "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }*/

    public void getTotalNewsQuery(){
        final String url = mfunc.getAppUrl()+"/api/getquerytotalnews.php";
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void checkSession(String userid,String deviceid){
        final String url = mfunc.getAppUrl()+"/api/sessioncheck.php?userid="+userid+"&deviceid="+deviceid;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void updateSession(String userid,String deviceid){
        final String url = mfunc.getAppUrl()+"/api/sessioncheck.php?userid="+userid+"&deviceid="+deviceid+"&updateit=yes";
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void send_contact_us(String userid,String deviceid,String msisdn,String subject,String msg){
        final String url = mfunc.getAppUrl()+"/api/setcontactus.php?userid="+userid+"&deviceid="+deviceid+"&msisdn="+msisdn+"&subject="+subject+"&msg="+msg;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void sendRegistrationSocial(String name,String xxname, String userid, String pushid, String src, Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url =mfunc.getAppUrl()+"/api/saveuser.php?name="+name+"&userid="+userid+"&pushid="+pushid+"&lagent="+src+"&xxname="+xxname;
        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void sendStats(String key,String videoid, String userid, String value, Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final String url =mfunc.getAppUrl()+"/api/setstats.php?key="+key+"&id="+videoid+"&val="+value+"&user="+userid;
        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ url);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void writeToFile(String data ) throws IOException {
        File out;
        String root = Environment.getExternalStorageDirectory().toString();
        OutputStreamWriter outStreamWriter = null;
        FileOutputStream outStream = null;

        out = new File(root+"/routelog.log");

        if ( out.exists() == false ){
            out.createNewFile();
        }

        outStream = new FileOutputStream(out,true) ;
        outStreamWriter = new OutputStreamWriter(outStream);

        outStreamWriter.append(data+"\n");
        outStreamWriter.flush();
    }


    public void verifyDlink(String lid) {
        RequestQueue queue = Volley.newRequestQueue(myContext);
        final String url =mfunc.getAppUrl()+"/templinks/getlinkinfo.php?lid="+lid;
        Log.d(TAG, "request Dlink: "+ url);


// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    public void getCurrentVersion() {
        RequestQueue queue = Volley.newRequestQueue(myContext);
        final String url =mfunc.getAppUrl()+"/api/getversion.php";



// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    public void getTrial(String deviceid) {
        RequestQueue queue = Volley.newRequestQueue(myContext);
        final String url =mfunc.getAppUrl()+"/api/check_trial.php?deviceid="+deviceid;

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void profile(String userid,String mode){
        final String url =mfunc.getAppUrl()+"/api/profile.php?opera="+mode+"&userid="+userid;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void update_profile(String userid,String mode,String name, String phone1,String phone2){
        final String url =mfunc.getAppUrl()+"/api/profile.php?opera="+mode+"&name="+name+"&phone1="+phone1+"&phone2="+phone2+"&userid="+userid;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "sendRegistration: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void change_password(String userid,String password,String new_password){
        final String url =mfunc.getAppUrl()+"/api/change_password.php?userid="+userid+"&xxpass="+password+"&newxxpass="+new_password;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "password: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void get_location(String userid,String lat,String lon,String address){
        final String url =mfunc.getAppUrl()+"/api/get_location.php?userid="+userid+"&lat="+lat+"&lon="+lon+"&address="+address;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        Log.d(TAG, "password: "+ url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.d("Incoming", "onResponse: "+ response);
                        try {
                            responseCallback.onResponseReceived(200,response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Incoming", "onErrorResponse: It did not work "+ error.getMessage());
                try {
                    responseCallback.onResponseReceived(500,error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}