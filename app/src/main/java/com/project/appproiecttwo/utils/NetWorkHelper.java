package com.project.appproiecttwo.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 网络判断检测工具类
 * Created by beck on 2015/11/24.
 */
public class NetWorkHelper {

    public static final String CTWAP = "ctwap";
    public static final String CMWAP = "cmwap";
    public static final String WAP_3G = "3gwap";
    public static final String UNIWAP = "uniwap";
    public static final int TYPE_NET_WORK_DISABLED = 0;// 网络不可用
    public static final int TYPE_CM_CU_WAP = 4;// 移动联通wap10.0.0.172
    public static final int TYPE_CT_WAP = 5;// 电信wap 10.0.0.200
    public static final int TYPE_OTHER_NET = 6;// 电信,移动,联通,wifi 等net网络
    public static Uri PREFERRED_APN_URI = Uri.parse("content://telephony/carriers/preferapn");
    private static String LOG_TAG = "NetWorkHelper";

    /**
     *
     * 判断是否有网络连接
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isAvailable());
    }

    /**
     * 其中 -c 为发送的次数，1为表示发送1次，-w 100 以毫秒为单位指定超时间隔，是指超时时间为100毫秒
     *
     * @param ip
     * @return
     */
    public static boolean startPing(String ip) {
        Log.e("Ping", "startPing...");
        boolean success = false;
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("ping -c 3 -i 0.2 -W 100 " + ip);
            int status = p.waitFor();
            success = status == 0;
        } catch (IOException e) {
            success = false;
        } catch (InterruptedException e) {
            success = false;
        } finally {
            p.destroy();
        }

        return success;
    }

    /**
     * 判断网络是否为漫游
     */
    public static boolean isNetworkRoaming(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Log.w(LOG_TAG, "couldn't get connectivity manager");
        } else {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null
                    && info.getType() == ConnectivityManager.TYPE_MOBILE) {
                TelephonyManager tm = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                if (tm != null && tm.isNetworkRoaming()) {
                    Log.d(LOG_TAG, "network is roaming");
                    return true;
                } else {
                    Log.d(LOG_TAG, "network is not roaming");
                }
            } else {
                Log.d(LOG_TAG, "not using mobile network");
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static boolean isMobileDataEnable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isMobileDataEnable = false;
        isMobileDataEnable = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        return isMobileDataEnable;
    }


    /**
     * 判断wifi 是否可用
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static boolean isWifiDataEnable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiDataEnable = false;
        isWifiDataEnable = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        return isWifiDataEnable;
    }

    /**
     * 检查网络状态
     */
    public static boolean checkNetworkAndSettings(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        // 如果3G、wifi、2G等网络状态是连接的，则退出，否则显示提示信息进入网络设置界面
        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
            return true;
        } else return wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING;
    }

    /**
     * 判断Network具体类型（联通移动wap，电信wap，其他net）
     */
    public static int checkNetworkType(Context mContext) {
        try {
            final ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo mobNetInfoActivity = connectivityManager
                    .getActiveNetworkInfo();
            if (mobNetInfoActivity == null || !mobNetInfoActivity.isAvailable()) {
                // 注意一：
                // NetworkInfo 为空或者不可以用的时候正常情况应该是当前没有可用网络，
                // 但是有些电信机器，仍可以正常联网，
                // 所以当成net网络处理依然尝试连接网络。
                // （然后在socket中捕捉异常，进行二次判断与用户提示）。
                Log.i("", "=====================>无网络");
                return TYPE_OTHER_NET;
            } else {
                // NetworkInfo不为null开始判断是网络类型
                int netType = mobNetInfoActivity.getType();
                if (netType == ConnectivityManager.TYPE_WIFI) {
                    // wifi net处理
                    Log.i("", "=====================>wifi网络");
                    return TYPE_OTHER_NET;
                } else if (netType == ConnectivityManager.TYPE_MOBILE) {
                    // 注意二：
                    // 判断是否电信wap:
                    //不要通过getExtraInfo获取接入点名称来判断类型，
                    // 因为通过目前电信多种机型测试发现接入点名称大都为#777或者null，
                    // 电信机器wap接入点中要比移动联通wap接入点多设置一个用户名和密码,
                    // 所以可以通过这个进行判断！
                    final Cursor c = mContext.getContentResolver().query(
                            PREFERRED_APN_URI, null, null, null, null);
                    if (c != null) {
                        c.moveToFirst();
                        final String user = c.getString(c
                                .getColumnIndex("user"));
                        if (!TextUtils.isEmpty(user)) {
                            Log.i("", "=====================>代理：" + c.getString(c.getColumnIndex("proxy")));
                            if (user.startsWith(CTWAP)) {
                                Log.i("", "=====================>电信wap网络");
                                return TYPE_CT_WAP;
                            }
                        }
                    }
                    c.close();
                    // 注意三：
                    // 判断是移动联通wap:
                    // 其实还有一种方法通过getString(c.getColumnIndex("proxy")获取代理ip
                    //来判断接入点，10.0.0.172就是移动联通wap，10.0.0.200就是电信wap，但在
                    //实际开发中并不是所有机器都能获取到接入点代理信息，例如魅族M9 （2.2）等...
                    // 所以采用getExtraInfo获取接入点名字进行判断
                    String netMode = mobNetInfoActivity.getExtraInfo();
                    Log.i("", "netMode ================== " + netMode);
                    if (netMode != null) {
                        // 通过apn名称判断是否是联通和移动wap
                        netMode = netMode.toLowerCase();
                        if (netMode.equals(CMWAP) || netMode.equals(WAP_3G)
                                || netMode.equals(UNIWAP)) {
                            Log.i("", "=====================>移动联通wap网络");
                            return TYPE_CM_CU_WAP;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return TYPE_OTHER_NET;
        }
        return TYPE_OTHER_NET;
    }

    /**
     * 获取手机本地ip
     * !inetAddress.isLinkLocalAddress()这个判断，因为有些手机获取到的ip是ipv6的
     */
    public static String getHostIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> ipAddr = intf.getInetAddresses(); ipAddr
                        .hasMoreElements(); ) {
                    InetAddress inetAddress = ipAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public List<String> getNetWorkList(Context mContext) {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = cm.getAllNetworkInfo();
        List<String> list = new ArrayList<String>();
        if (infos != null) {
            for (int i = 0; i < infos.length; i++) {
                NetworkInfo info = infos[i];
                String name = null;
                if (info.getTypeName().equals("WIFI")) {
                    name = info.getTypeName();
                } else {
                    name = info.getExtraInfo();
                }
                if (name != null && list.contains(name) == false) {
                    list.add(name);
                    // System.out.println(name);
                }
            }
        }
        return list;
    }

    /**
     * 得到网络类型
     */
    public String getNetWork(Context mContext) {
        String NOWNET = null;
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            if (info.getTypeName().equals("WIFI")) {
                NOWNET = info.getTypeName();
            } else {
                NOWNET = info.getExtraInfo();// cmwap/cmnet/wifi/uniwap/uninet
            }
        }
        return NOWNET;
    }

    /**
     * 获得网络运营商
     * 1.移动
     * 2.联通
     * 3.电信
     */
    private void getProviders(Context mContext) {
        String type = "";
        String net = getNetWork(mContext);
        List<String> infos = getNetWorkList(mContext);
        if (net == null || net.equals("WIFI")) {
            if (infos.size() > 1) {
                infos.remove("WIFI");
                net = infos.get(0);
                if (net.equals("3gwap") || net.equals("uniwap")
                        || net.equals("3gnet") || net.equals("uninet")) {
                    type = "联通";
                } else if (net.equals("cmnet") || net.equals("cmwap")) {
                    type = "移动";
                } else if (net.equals("ctnet") || net.equals("ctwap")) {
                    type = "电信";
                }
            } else {
//                Constants.MB_ID = PhoneUtil.getProvidersName(this);
            }
        } else {
            if (net.equals("3gwap") || net.equals("uniwap")
                    || net.equals("3gnet") || net.equals("uninet")) {
                type = "联通";
            } else if (net.equals("cmnet") || net.equals("cmwap")) {
                type = "移动";
            } else if (net.equals("ctnet") || net.equals("ctwap")) {
                type = "电信";
            }
        }
    }


    /**
     * 根据ip段
     * 获取当前网络是哪个运营商
     * 电信的地址段是：192.168.0.X   192.168.1.X   192.168.2.X   192.168.3.X
     * 联通的地址段是：192.168.4.X   192.168.5.X
     * 移动的地址段是：10.148.72.X
     */
    public static String getNetOperator(String ip) {
        if (ip.startsWith("192.168.0") || ip.startsWith("192.168.1") || ip.startsWith("192.168.2") || ip.startsWith("192.168.3")) {
            return "电信";
        } else if (ip.startsWith("192.168.4") || ip.startsWith("192.168.5")) {
            return "联通";
        } else if (ip.startsWith("10.148.72")) {
            return "移动";
        } else {
            return "其他";
        }
    }


}
