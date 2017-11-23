package com.project.appproiecttwo.utils.xml;


import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by lenovo on 2017/10/27.
 */

public class XMLParser {
    private static String TAG = "XmlParser";
    private String xmlString;
    private ArrayList<Person> mXmlBeenList;
    private Person mXmlBean;

    public boolean Parser(String xml) {

        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(new StringReader(xml));
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d(TAG, "XmlPullParserException: " + e);
        }

        try {
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        mXmlBeenList = new ArrayList<>();
                        break;

                    case XmlPullParser.START_TAG:
//                  Log.d(TAG, "getName:"+parser.getName()+","+parser.getText());
                        if ("app_id".equals(name)) {
                            mXmlBean = new Person();
                            //mXmlBean.setId(Integer.valueOf(parser.getAttributeValue("", Book.ID)));
                            mXmlBean.setApp_id(parser.nextText());
                        } else if ("version".equals(name)) {
                            mXmlBean.setVersion(parser.nextText());
                        } else if ("kc_url".equals(name)) {
                            mXmlBean.setKc_url(parser.nextText());
                        } else if ("home_url".equals(name)) {
                            mXmlBean.setHome_url(parser.nextText());
                        } else if ("service_url".equals(name)) {
                            mXmlBean.setService_url(parser.nextText());
                        } else if ("buttonArr".equals(name)) {
                            mXmlBean.setButtonArr(parser.nextText());
                        } else if ("buttonImage".equals(name)) {
                            mXmlBean.setButtonImage(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("Version".equals(name)) {
                            mXmlBeenList.add(mXmlBean);
                        }
                        break;
                    default:
                        break;
                }

                eventType = parser.next();
            }
        } catch (Exception e) {
            // TODO: handle exception
            Log.e(TAG, "Exception: " + e);
            return false;
        }
        return true;
    }

    public ArrayList<Person> getXmlString() {
        return mXmlBeenList;
    }
}
