package edu.txstate.dlk94.homeworkfour;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by usafd_000 on 8/9/2017.
 */

public class RestClient {

    private static final String BASE_URL = "http://webapimobile.azurewebsites.net/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String url, Header[] headers,
                           RequestParams params, AsyncHttpResponseHandler handler) {
        client.get(context, getAbsoluteUrl(url), headers, params, handler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
