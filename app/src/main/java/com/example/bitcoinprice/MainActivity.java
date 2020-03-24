package com.example.bitcoinprice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpinner;
    private TextView mTextView;
    private String[] countryCodes;
    private ArrayList<CountryCurrency> mCountryCurrencies;
    private CustomAdapter mCustomAdapter;
    private String Base_Url="https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView=findViewById(R.id.price_text_view);

        initialise();

        mSpinner=findViewById(R.id.spinner);

        mCustomAdapter=new CustomAdapter(this,mCountryCurrencies);

        mSpinner.setAdapter(mCustomAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CountryCurrency selected= (CountryCurrency) parent.getItemAtPosition(position);
                String url=Base_Url+selected.getCurrencyCode();
                letsDoSomeNetworking(url);
                Toast.makeText(MainActivity.this, selected.getCurrencyCode(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initialise() {
        countryCodes=getResources().getStringArray(R.array.currency_array);
        mCountryCurrencies=new ArrayList<>();
        for(int i=0;i<countryCodes.length;i++){
            mCountryCurrencies.add(new CountryCurrency(countryCodes[i]));
        }
    }

    private void letsDoSomeNetworking(String url){

        AsyncHttpClient client=new AsyncHttpClient();
        client.get(url,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String last=response.getString("last");
                    mTextView.setText(last);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
