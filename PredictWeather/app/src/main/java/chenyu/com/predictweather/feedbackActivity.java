package chenyu.com.predictweather;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/6/1.
 */
public class feedbackActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText mLocation;
    private EditText mWeather;
    private EditText mTemp;
    private Button mSubmit;
    private Button mReset;
    private DailyWeatherBean mDailyWeatherBean;
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_main);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mDailyWeatherBean = (DailyWeatherBean) bundle.getSerializable("data");

        initViews();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("反馈天气状况");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mLocation = (EditText) findViewById(R.id.feedback_location);
        mWeather = (EditText) findViewById(R.id.feedback_weather);
        mTemp = (EditText) findViewById(R.id.feedback_temp);

        resetData();

        alertDialog = new AlertDialog.Builder(feedbackActivity.this);
        alertDialog.setTitle("提示");
        alertDialog.setMessage("你已成功提交数据！点击确定返回上一页.");
        alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        mSubmit = (Button) findViewById(R.id.submit);
        mReset = (Button) findViewById(R.id.reset);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.create().show();
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetData();
            }
        });


    }

    private void resetData() {
        mLocation.setText(mDailyWeatherBean.getmLocation());
        mWeather.setText(mDailyWeatherBean.getmTodayWeather());
        mTemp.setText(mDailyWeatherBean.getmTodayTemp());
    }
}
