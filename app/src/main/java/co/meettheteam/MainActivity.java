package co.meettheteam;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
/**
 * Created by dharma on 11/16/16.
 */
public class MainActivity extends BaseActivity implements  View.OnClickListener{
    private LinearLayoutManager lLayout;
    Activity activity;
    ListView list;
    static TextView emptyView;
    static  RecyclerView rView;
    static RecyclerViewAdapter rcAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // this can be used for getting data from server
        if (isConnectingToInternet()) {

            //geting data from server
            new AsyncLoadJSON().execute();

            // geting data from assets
           // getUsers(this);

        }else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();

        }


        lLayout = new LinearLayoutManager(MainActivity.this);

        // declare the recyclerview
        rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        // declare the recyclerAdapter
        rcAdapter = new RecyclerViewAdapter(MainActivity.this, UserResponse.users);
        rView.setAdapter(rcAdapter);

        emptyView = (TextView) findViewById(R.id.empty_view);
        emptyView.setOnClickListener(this);

        displayViews();



    }

    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.empty_view: {
                // do something for view empty click
                if (isConnectingToInternet()) {
                    //geting data from server
                    new AsyncLoadJSON().execute();
                    // declare the recyclerview
                    Log.e("Data", "empty_view data lenght="+UserResponse.users.size());
                    rcAdapter.notifyDataSetChanged();
                    displayViews();
                }else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();

                }
                break;
            }


        }
    }

    public  void displayViews(){
        Log.e("DisplayView"," Data Size="+UserResponse.users.size());
        if (UserResponse.users.size()<1) {
            rView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            rView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // get the users from the json and add on list of users
    private  void getUsers(Activity activity){
        try {
            JSONArray m_jArray = new JSONArray(Utils.loadJSONFromAsset(activity));
            Utils.setUserData(m_jArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    // execute data
    class AsyncLoadJSON extends AsyncTask<String, String, Void> {

        private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        InputStream inputStream = null;
        String result = "";

        protected void onPreExecute() {
            progressDialog.setMessage("Downloading data...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    AsyncLoadJSON.this.cancel(true);
                }
            });
        }
        @Override
        protected Void doInBackground(String... params) {

            ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

            try {
                // HttpClient is more then less deprecated. Need to change to URLConnection
                HttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(Utils.URL);
                httpPost.setEntity(new UrlEncodedFormEntity(param));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();

                // Read content & Log
                inputStream = httpEntity.getContent();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (IllegalStateException e3) {
                Log.e("IllegalStateException", e3.toString());
                e3.printStackTrace();
            } catch (IOException e4) {
                Log.e("IOException", e4.toString());
                e4.printStackTrace();
            }
            // Convert response to string using String Builder
            try {
                BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                StringBuilder sBuilder = new StringBuilder();

                String line = null;
                while ((line = bReader.readLine()) != null) {
                    sBuilder.append(line + "\n");
                }

                inputStream.close();
                result = sBuilder.toString();

            } catch (Exception e) {
                Log.e("Exeception", "Error converting result " + e.toString());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void v) {
            //parse JSON data
            try {
                JSONArray m_jArray = new JSONArray(result);
                Utils.setUserData(m_jArray);
                displayViews();
                this.progressDialog.dismiss();
            } catch (JSONException e) {
                Log.e("JSONException", "Error: " + e.toString());
            } // catch (JSONException e)
        } // protected void onPostExecute(Void v)
    } //clas
}
