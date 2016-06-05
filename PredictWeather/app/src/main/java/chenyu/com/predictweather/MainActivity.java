package chenyu.com.predictweather;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SearchView searchView;
    private MenuItem search;
    private String dailyUrl = "https://api.thinkpage.cn/v3/weather/daily.json?key=rot2enzrehaztkdk&start=0&days=4";
    private String livingIndexUrl = "https://api.thinkpage.cn/v3/life/suggestion.json?key=rot2enzrehaztkdk";
    private JsonObjectRequest jsonObjectRequest1;
    private JsonObjectRequest jsonObjectRequest2;
    private String mSearchContent = "";
    private DailyWeatherBean mDailyWeatherBean = new DailyWeatherBean();
    private RequestQueue requestQueue = MyApplication.getRequestQueue();

    private boolean mDailyComplete = false;
    private boolean mLivingIndexComplete = false;

    private TextView mLocation;
    private TextView mTodayDate;
    private TextView mWeather;
    private TextView mWindLv;
    private TextView mTodayTemp;
    private ImageView mTodayWeatherIcon;
    private TextView mAiring;
    private TextView mComfort;
    private TextView mUv;
    private TextView mDressing;
    private TextView mShopping;

    private TextView mTomTemp;
    private ImageView mTomWeatherIcon;

    private TextView mDaysTemp;
    private ImageView mDaysWeatherIcon;

    private TextView mDayssTemp;
    private ImageView mDayssWeatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initData() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(Environment.getExternalStorageDirectory(),"data.txt")));
            DailyWeatherBean mData = (DailyWeatherBean) objectInputStream.readObject();
//            Log.d("cylog",mData.toString());
            objectInputStream.close();
            if (mData.exist()){
                invalidateView(mData,1);
                invalidateView(mData,2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        /**
         *  初始化Toolbar
         */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("天气预报");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.action_search:
//                        Toast.makeText(MainActivity.this, "Search !", Toast.LENGTH_LONG).show();
//                        break;
                    case R.id.action_refresh:
                        Toast.makeText(MainActivity.this,"正在刷新数据,请稍候",Toast.LENGTH_SHORT).show();
                        refreshData();
                        break;
                    case R.id.action_improve:
                        Intent intent = new Intent(MainActivity.this,feedbackActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data",mDailyWeatherBean);
                        intent.putExtras(bundle);
                        startActivity(intent);
//                        requestQueue.add(jsonObjectRequest);
                        break;
                }
                return true;
            }
        });


        mLocation = (TextView) findViewById(R.id.location);
        mTodayDate = (TextView) findViewById(R.id.date);
        mWeather = (TextView) findViewById(R.id.weather);
        mWindLv = (TextView) findViewById(R.id.wind_lv);
        mTodayTemp = (TextView) findViewById(R.id.temperature);
        mTodayWeatherIcon = (ImageView) findViewById(R.id.weather_icon);
        mComfort = (TextView) findViewById(R.id.comfort);
        mUv = (TextView) findViewById(R.id.uv);
        mDressing = (TextView) findViewById(R.id.dressing);
        mAiring = (TextView) findViewById(R.id.airing);
        mShopping = (TextView) findViewById(R.id.shopping);

        mTomTemp = (TextView) findViewById(R.id.day_temp);
        mTomWeatherIcon = (ImageView) findViewById(R.id.day_icon);

        mDaysTemp = (TextView) findViewById(R.id.days_temp);
        mDaysWeatherIcon = (ImageView) findViewById(R.id.days_icon);

        mDayssTemp = (TextView) findViewById(R.id.dayss_temp);
        mDayssWeatherIcon = (ImageView) findViewById(R.id.dayss_icon);
    }

    private void refreshData() {
        if(mSearchContent != null){
            String newDailyUrl = dailyUrl + "&location=" + mSearchContent;
            String newLivingIndexUrl = livingIndexUrl + "&location=" + mSearchContent;
            getWeather(newDailyUrl);
            getLivingIndex(newLivingIndexUrl);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        search = menu.findItem(R.id.action_search);
        searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String cityName = "";
                try {
                    cityName = URLEncoder.encode(query, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                mSearchContent = cityName;
                String newDailyUrl = dailyUrl + "&location=" + cityName;
                String newLivingIndexUrl = livingIndexUrl + "&location=" + cityName;
//                Log.d("cylog", newDailyUrl);
                searchView.clearFocus();
                search.collapseActionView();
                getWeather(newDailyUrl);
                getLivingIndex(newLivingIndexUrl);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    public void getWeather(String url){
        jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, url, (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            saveData(jsonObject);
                            invalidateView(mDailyWeatherBean, 1);
                            if(mLivingIndexComplete){
                                saveToLocal();
                            }else {
                                mDailyComplete = true;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Log.d("cylog",jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("cylog",volleyError.toString());
                    }
                });
        requestQueue.add(jsonObjectRequest1);
    }

    public void getLivingIndex(String url){
        jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, url, (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            saveLivingIndexData(jsonObject);
                            invalidateView(mDailyWeatherBean,2);
                            if (mDailyComplete){
                                saveToLocal();
                            }else {
                                mLivingIndexComplete = true;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Log.d("cylog",jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("cylog",volleyError.toString());
                    }
                });
        requestQueue.add(jsonObjectRequest2);
    }

    private void saveToLocal() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory(),"data.txt")));
            objectOutputStream.writeObject(mDailyWeatherBean);
            objectOutputStream.close();
//            Log.d("cylog",mDailyWeatherBean.toString());
            Log.d("cylog","序列化成功...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void invalidateView(DailyWeatherBean mDailyWeatherBean,int condition) {
        if (condition == 1) {

            mLocation.setText(mDailyWeatherBean.getmLocation());
            mTodayDate.setText(mDailyWeatherBean.getmTodayDate());
            mWeather.setText(mDailyWeatherBean.getmTodayWeather());
            mWindLv.setText(mDailyWeatherBean.getmTodayWindLv());
            mTodayTemp.setText(mDailyWeatherBean.getmTodayTemp());
            mTodayWeatherIcon.setImageResource(mDailyWeatherBean.getmTodayWeatherIcon());

            mTomTemp.setText(mDailyWeatherBean.getmTomTemp());
            mTomWeatherIcon.setImageResource(mDailyWeatherBean.getmTomWeatherIcon());

            mDaysTemp.setText(mDailyWeatherBean.getmDaysTemp());
            mDaysWeatherIcon.setImageResource(mDailyWeatherBean.getmDaysWeatherIcon());

            mDayssTemp.setText(mDailyWeatherBean.getmDayssTemp());
            mDayssWeatherIcon.setImageResource(mDailyWeatherBean.getmDayssWeatherIcon());
        }else if(condition == 2){
            mComfort.setText(mDailyWeatherBean.getmComfort());
            mUv.setText(mDailyWeatherBean.getmUv());
            mAiring.setText(mDailyWeatherBean.getmAiring());
            mShopping.setText(mDailyWeatherBean.getmShopping());
            mDressing.setText(mDailyWeatherBean.getmDressing());
        }
    }

    private void saveData(JSONObject jsonObject) throws JSONException {
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        //设置 地点
        String cityName = jsonArray.getJSONObject(0).getJSONObject("location").getString("name");
        mDailyWeatherBean.setmLocation(cityName);

        //设置 今天的天气情况
        JSONObject mTodayJson = jsonArray.getJSONObject(0).getJSONArray("daily").getJSONObject(0);
        mDailyWeatherBean.setmTodayDate(mTodayJson.getString("date")).setmTodayWeather(mTodayJson.getString("text_day"))
                .setmTodayWeatherCode(mTodayJson.getString("code_day")).setmTodayTempLow(mTodayJson.getString("low"))
                .setmTodayTempHigh(mTodayJson.getString("high")).setmTodayWindLv(mTodayJson.getString("wind_scale"));

        //设置 明天的天气情况
        JSONObject mTomJson = jsonArray.getJSONObject(0).getJSONArray("daily").getJSONObject(1);
        mDailyWeatherBean.setmTomWeather(mTomJson.getString("text_day")).setmTomWeatherCode(mTomJson.getString("code_day"))
                .setmTomTempLow(mTomJson.getString("low")).setmTomTempHigh(mTomJson.getString("high"));

        //设置 后天的天气情况
        JSONObject mDaysJson = jsonArray.getJSONObject(0).getJSONArray("daily").getJSONObject(2);
        mDailyWeatherBean.setmDaysWeather(mDaysJson.getString("text_day")).setmDaysWeatherCode(mDaysJson.getString("code_day"))
                .setmDaysTempLow(mDaysJson.getString("low")).setmDaysTempHigh(mDaysJson.getString("high"));

        //设置 大后天的天气情况
        JSONObject mDayssJson = jsonArray.getJSONObject(0).getJSONArray("daily").getJSONObject(3);
        mDailyWeatherBean.setmDayssWeather(mDayssJson.getString("text_day")).setmDayssWeatherCode(mDayssJson.getString("code_day"))
                .setmDayssTempLow(mDayssJson.getString("low")).setmDayssTempHigh(mDayssJson.getString("high"));
//        Log.d("cylog",mDailyWeatherBean.toString());
    }

    private void saveLivingIndexData(JSONObject jsonobject) throws JSONException {
        JSONObject json = jsonobject.getJSONArray("results").getJSONObject(0).getJSONObject("suggestion");
//        Log.d("cylog",json.toString());
        mDailyWeatherBean.setmAiring(json.getJSONObject("airing").getString("brief")).setmComfort(json.getJSONObject("comfort").getString("brief"))
                .setmUv(json.getJSONObject("uv").getString("brief")).setmShopping(json.getJSONObject("shopping").getString("brief"))
                .setmDressing(json.getJSONObject("dressing").getString("details"));
    }
}
