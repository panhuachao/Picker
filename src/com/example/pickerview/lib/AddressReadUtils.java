package com.example.pickerview.lib;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.content.Context;
import android.content.res.AssetManager;

public class AddressReadUtils {
	  private ArrayList<String> options1Items = new ArrayList<String>();
	  private ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
	  private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();
	  /**
	   * 通过assets xml读取返回arraylist
	   * @param context
	   */
	  public void readXML(Context context)
	  {
		  this.readXML(context, "province_data.xml");
	  }
	  
	  /**
	   * 通过assets xml读取返回arraylist
	   * @param context
	   * @param xml文件名
	   */
	  public void readXML(Context context,String xmlfilename)
	  {
		  AssetManager asset = context.getAssets();
	      InputStream input;
			try {
				input = asset.open(xmlfilename);
				SAXParserFactory spf = SAXParserFactory.newInstance();
				// ����xml
				SAXParser parser = spf.newSAXParser();
				XmlParserHandler handler = new XmlParserHandler();
				parser.parse(input, handler);
				input.close();
				// ��ȡ�������������
				options1Items = handler.getProviceList();
				options2Items=handler.getCityList();
				options3Items=handler.getDistrictList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  
		public ArrayList<String> getProviceList() {
			return options1Items;
		}
		
		public ArrayList<ArrayList<String>> getCityList()
		{
			return options2Items;
		}
		
		public ArrayList<ArrayList<ArrayList<String>>> getDistrictList()
		{
			return options3Items;
		}
	  
}
