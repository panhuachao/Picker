package com.example.pickerview.lib;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParserHandler extends DefaultHandler {

	/**
	 * 通过province_data.xml获取省市区信息
	 * @author PanHuachao
	 */
	public XmlParserHandler() {
		
	}

	public ArrayList<String> getProviceList() {
		return provinceList;
	}
	
	public ArrayList<ArrayList<String>> getCityList()
	{
		return cityList;
	}
	
	public ArrayList<ArrayList<ArrayList<String>>> getDistrictList()
	{
		return districtList;
	}

	@Override
	public void startDocument() throws SAXException {
		// 
	}

	private ArrayList<String> provinceList = new ArrayList<String>();
	ArrayList<ArrayList<String>> cityList = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<ArrayList<String>>> districtList = new ArrayList<ArrayList<ArrayList<String>>>();

	ArrayList<String> _citylist;
	ArrayList<String> _tempdistrictlist;
	ArrayList<ArrayList<String>> _districtlist;
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// xml节点开始解析
		if (qName.equals("province")) {
			provinceList.add(attributes.getValue(0));
			_citylist = new ArrayList<String>();
			_districtlist=new ArrayList<ArrayList<String>>();
		} else if (qName.equals("city")) {
			_citylist.add(attributes.getValue(0));
			_tempdistrictlist=new ArrayList<String>();
		} else if (qName.equals("district")) {
			_tempdistrictlist.add(attributes.getValue(0));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// xml 结束节点
		if (qName.equals("district")) {
//			cityModel.getDistrictList().add(districtModel);
        } else if (qName.equals("city")) {
        	_districtlist.add(_tempdistrictlist);
        } else if (qName.equals("province")) {
        	cityList.add(_citylist);
        	districtList.add(_districtlist);
        }
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

}
