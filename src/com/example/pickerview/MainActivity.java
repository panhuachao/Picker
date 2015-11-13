package com.example.pickerview;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.example.pickerview.lib.AddressReadUtils;
import com.example.pickerview.lib.OptionsPopupWindow;
import com.example.pickerview.lib.TimePopupWindow;
import com.example.pickerview.lib.TimePopupWindow.OnTimeSelectListener;
import com.example.pickerview.lib.TimePopupWindow.Type;
import com.example.pickerview.lib.XmlParserHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class MainActivity extends Activity {

    private ArrayList<String> options1Items = new ArrayList<String>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();
    private TextView tvTime, tvOptions;
    TimePopupWindow pwTime;
    OptionsPopupWindow pwOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTime=(TextView) findViewById(R.id.tvTime);
        tvOptions=(TextView) findViewById(R.id.tvOptions);
        //时间选择器
        pwTime = new TimePopupWindow(this, Type.YEAR_MONTH_DAY);
        pwTime.setTime(new Date());
        //时间选择后回调
        pwTime.setOnTimeSelectListener(new OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvTime.setText(getTime(date));
            }
        });
        //弹出时间选择器
        tvTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pwTime.showAtLocation(tvTime, Gravity.BOTTOM, 0, 0,new Date());
            }
        });

        //选项选择器
        pwOptions = new OptionsPopupWindow(this);
        AddressReadUtils addressUtils=new AddressReadUtils();
        addressUtils.readXML(this);
        options1Items=addressUtils.getProviceList();
        options2Items=addressUtils.getCityList();
        options3Items=addressUtils.getDistrictList();
        
        Log.i("options1Items length", options1Items.size()+"");
        //三级联动效果
        pwOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
        //pwOptions.setLabels("省", "市", "区")
        //设置默认选中的三级项目
        pwOptions.setSelectOptions(0, 0, 0);
        //监听确定选择按钮
        pwOptions.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1)
                        +options2Items.get(options1).get(option2)
                        +options3Items.get(options1).get(option2).get(options3);
                tvOptions.setText(tx);
            }
        });
        //点击弹出选项选择器
        tvOptions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pwOptions.showAtLocation(tvTime, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

}
