package chenyu.com.predictweather;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/5/31.
 */
public class DailyWeatherBean implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *  今天的天气情况
     */
    private String mLocation;
    private String mTodayDate;
    private String mTodayWeather;
    private String mTodayWeatherCode;
    private String mTodayTempLow;
    private String mTodayTempHigh;
    private String mTodayWindLv;
    private String mComfort;
    private String mUv;
    private String mAiring;
    private String mShopping;
    private String mDressing;
    private int mTodayWeatherIcon;

    /**
     *  明天的天气情况
     */
    private String mTomWeather;
    private String mTomWeatherCode;
    private int mTomWeatherIcon;
    private String mTomTempLow;
    private String mTomTempHigh;

    /**
     *  后天的天气情况
     */
    private String mDaysWeather;
    private String mDaysWeatherCode;
    private int mDaysWeatherIcon;
    private String mDaysTempLow;
    private String mDaysTempHigh;

    /**
     *  大后天的天气情况
     */
    private String mDayssWeather;
    private String mDayssWeatherCode;
    private int mDayssWeatherIcon;
    private String mDayssTempLow;
    private String mDayssTempHigh;


    private int[] bitmapResource = {R.mipmap.ic_0,R.mipmap.ic_1,R.mipmap.ic_2,R.mipmap.ic_3,R.mipmap.ic_4,R.mipmap.ic_5,R.mipmap.ic_6,
            R.mipmap.ic_7,R.mipmap.ic_8,R.mipmap.ic_9,R.mipmap.ic_10,R.mipmap.ic_11,R.mipmap.ic_12,R.mipmap.ic_13,R.mipmap.ic_14
            ,R.mipmap.ic_15,R.mipmap.ic_16,R.mipmap.ic_17,R.mipmap.ic_18,R.mipmap.ic_19,R.mipmap.ic_20,R.mipmap.ic_21,R.mipmap.ic_22
            ,R.mipmap.ic_23,R.mipmap.ic_24,R.mipmap.ic_25,R.mipmap.ic_26,R.mipmap.ic_27,R.mipmap.ic_28,R.mipmap.ic_29,R.mipmap.ic_30
            ,R.mipmap.ic_31,R.mipmap.ic_32,R.mipmap.ic_33,R.mipmap.ic_34,R.mipmap.ic_35,R.mipmap.ic_36,R.mipmap.ic_37,R.mipmap.ic_38};


    public int getmTodayWeatherIcon() {
        return mTodayWeatherIcon;
    }

    public int getmTomWeatherIcon() {
        return mTomWeatherIcon;
    }

    public int getmDaysWeatherIcon() {
        return mDaysWeatherIcon;
    }

    public int getmDayssWeatherIcon() {
        return mDayssWeatherIcon;
    }

    public String getmTodayTemp() {
        return this.mTodayTempLow+"~"+this.mTodayTempHigh+"℃";
    }

    public String getmTomTemp() {
        return this.mTomTempLow+"~"+this.mTomTempHigh+"℃";
    }

    public String getmDaysTemp() {
        return this.mDaysTempLow+"~"+this.mDaysTempHigh+"℃";
    }

    public String getmDayssTemp() {
        return this.mDayssTempLow+"~"+this.mDayssTempHigh+"℃";
    }

    public String getmLocation() {
        return mLocation;
    }

    public DailyWeatherBean setmLocation(String mLocation) {
        this.mLocation = mLocation;
        return this;
    }

    public String getmTodayDate() {
        return mTodayDate;
    }

    public DailyWeatherBean setmTodayDate(String mTodayDate) {
        this.mTodayDate = mTodayDate;
        return this;
    }

    public String getmTodayWeather() {
        return mTodayWeather;
    }

    public DailyWeatherBean setmTodayWeather(String mTodayWeather) {
        this.mTodayWeather = mTodayWeather;
        return this;
    }

//    public String getmTodayWeatherCode() {
//        return mTodayWeatherCode;
//    }

    public DailyWeatherBean setmTodayWeatherCode(String mTodayWeatherCode) {
        this.mTodayWeatherCode = mTodayWeatherCode;
        this.mTodayWeatherIcon = queryResource(this.mTodayWeatherCode);
        return this;
    }

//    public String getmTodayTempLow() {
//        return mTodayTempLow;
//    }

    public DailyWeatherBean setmTodayTempLow(String mTodayTempLow) {
        this.mTodayTempLow = mTodayTempLow;
        return this;
    }

//    public String getmTodayTempHigh() {
//        return mTodayTempHigh;
//    }

    public DailyWeatherBean setmTodayTempHigh(String mTodayTempHigh) {
        this.mTodayTempHigh = mTodayTempHigh;
        return this;
    }

    public String getmTodayWindLv() {
        return mTodayWindLv;
    }

    public DailyWeatherBean setmTodayWindLv(String mTodayWindLv) {
        this.mTodayWindLv = setWindDescription(mTodayWindLv);
        return this;
    }

    public String getmComfort() {
        return mComfort;
    }

    public DailyWeatherBean setmComfort(String mComfort) {
        this.mComfort = mComfort;
        return this;
    }

    public String getmUv() {
        return mUv;
    }

    public DailyWeatherBean setmUv(String mUv) {
        this.mUv = mUv;
        return this;
    }

    public String getmAiring() {
        return mAiring;
    }

    public DailyWeatherBean setmAiring(String mAiring) {
        this.mAiring = mAiring;
        return this;
    }

    public String getmShopping() {
        return mShopping;
    }

    public DailyWeatherBean setmShopping(String mShopping) {
        this.mShopping = mShopping;
        return this;
    }

    public String getmDressing() {
        return mDressing;
    }

    public DailyWeatherBean setmDressing(String mDressing) {
        this.mDressing = mDressing;
        return this;
    }

    public String getmTomWeather() {
        return mTomWeather;
    }

    public DailyWeatherBean setmTomWeather(String mTomWeather) {
        this.mTomWeather = mTomWeather;
        return this;
    }

//    public String getmTomWeatherCode() {
//        return mTomWeatherCode;
//    }

    public DailyWeatherBean setmTomWeatherCode(String mTomWeatherCode) {
        this.mTomWeatherCode = mTomWeatherCode;
        this.mTomWeatherIcon = queryResource(this.mTomWeatherCode);
        return this;
    }

//    public String getmTomTempLow() {
//        return mTomTempLow;
//    }

    public DailyWeatherBean setmTomTempLow(String mTomTempLow) {
        this.mTomTempLow = mTomTempLow;
        return this;
    }

//    public String getmTomTempHigh() {
//        return mTomTempHigh;
//    }

    public DailyWeatherBean setmTomTempHigh(String mTomTempHigh) {
        this.mTomTempHigh = mTomTempHigh;
        return this;
    }

    public String getmDaysWeather() {
        return mDaysWeather;
    }

    public DailyWeatherBean setmDaysWeather(String mDaysWeather) {
        this.mDaysWeather = mDaysWeather;
        return this;
    }

//    public String getmDaysWeatherCode() {
//        return mDaysWeatherCode;
//    }

    public DailyWeatherBean setmDaysWeatherCode(String mDaysWeatherCode) {
        this.mDaysWeatherCode = mDaysWeatherCode;
        this.mDaysWeatherIcon = queryResource(this.mDaysWeatherCode);
        return this;
    }

//    public String getmDaysTempLow() {
//        return mDaysTempLow;
//    }

    public DailyWeatherBean setmDaysTempLow(String mDaysTempLow) {
        this.mDaysTempLow = mDaysTempLow;
        return this;
    }

//    public String getmDaysTempHigh() {
//        return mDaysTempHigh;
//    }

    public DailyWeatherBean setmDaysTempHigh(String mDaysTempHigh) {
        this.mDaysTempHigh = mDaysTempHigh;
        return this;
    }

    public String getmDayssWeather() {
        return mDayssWeather;
    }

    public DailyWeatherBean setmDayssWeather(String mDayssWeather) {
        this.mDayssWeather = mDayssWeather;
        return this;
    }

//    public String getmDayssWeatherCode() {
//        return mDayssWeatherCode;
//    }

    public DailyWeatherBean setmDayssWeatherCode(String mDayssWeatherCode) {
        this.mDayssWeatherCode = mDayssWeatherCode;
        this.mDayssWeatherIcon = queryResource(this.mDayssWeatherCode);
        return this;
    }

//    public String getmDayssTempLow() {
//        return mDayssTempLow;
//    }

    public DailyWeatherBean setmDayssTempLow(String mDayssTempLow) {
        this.mDayssTempLow = mDayssTempLow;
        return this;
    }

//    public String getmDayssTempHigh() {
//        return mDayssTempHigh;
//    }

    public DailyWeatherBean setmDayssTempHigh(String mDayssTempHigh) {
        this.mDayssTempHigh = mDayssTempHigh;
        return this;
    }

    public int queryResource(String num){
        return bitmapResource[Integer.parseInt(num)];
    }

    public String setWindDescription(String level){
        String lv = "";
        switch (level){
            case "0":
                lv = "无风";
                break;
            case "1":
                lv = "软风";
                break;
            case "2":
                lv = "轻风";
                break;
            case "3":
                lv = "微风";
                break;
            case "4":
                lv = "和风";
                break;
            case "5":
                lv = "清风";
                break;
            case "6":
                lv = "强风";
                break;
            case "7":
                lv = "疾风";
                break;
            case "8":
                lv = "大风";
                break;
            case "9":
                lv = "烈风";
                break;
            case "10":
                lv = "狂风";
                break;
            case "11":
                lv = "暴风";
                break;
            case "12":
            case "13":
            case "14":
            case "15":
            case "16":
            case "17":
            case "18":
                lv = "台风";
                break;
        }
        return lv;
    }

    public boolean exist(){
        if(this.mTodayDate != null){
            return true;
        }else
            return false;
    }

    @Override
    public String toString() {
        return "DailyWeatherBean{" +
                "mLocation='" + mLocation + '\'' +
                ", mTodayDate='" + mTodayDate + '\'' +
                ", mTodayWeather='" + mTodayWeather + '\'' +
                ", mTodayWeatherCode='" + mTodayWeatherCode + '\'' +
                ", mTodayTempLow='" + mTodayTempLow + '\'' +
                ", mTodayTempHigh='" + mTodayTempHigh + '\'' +
                ", mTodayWindLv='" + mTodayWindLv + '\'' +
                ", mComfort='" + mComfort + '\'' +
                ", mUv='" + mUv + '\'' +
                ", mAiring='" + mAiring + '\'' +
                ", mShopping='" + mShopping + '\'' +
                ", mDressing='" + mDressing + '\'' +
                ", mTodayWeatherIcon=" + mTodayWeatherIcon +
                ", mTomWeather='" + mTomWeather + '\'' +
                ", mTomWeatherCode='" + mTomWeatherCode + '\'' +
                ", mTomWeatherIcon=" + mTomWeatherIcon +
                ", mTomTempLow='" + mTomTempLow + '\'' +
                ", mTomTempHigh='" + mTomTempHigh + '\'' +
                ", mDaysWeather='" + mDaysWeather + '\'' +
                ", mDaysWeatherCode='" + mDaysWeatherCode + '\'' +
                ", mDaysWeatherIcon=" + mDaysWeatherIcon +
                ", mDaysTempLow='" + mDaysTempLow + '\'' +
                ", mDaysTempHigh='" + mDaysTempHigh + '\'' +
                ", mDayssWeather='" + mDayssWeather + '\'' +
                ", mDayssWeatherCode='" + mDayssWeatherCode + '\'' +
                ", mDayssWeatherIcon=" + mDayssWeatherIcon +
                ", mDayssTempLow='" + mDayssTempLow + '\'' +
                ", mDayssTempHigh='" + mDayssTempHigh + '\'' +
                ", bitmapResource=" + Arrays.toString(bitmapResource) +
                '}';
    }
}
