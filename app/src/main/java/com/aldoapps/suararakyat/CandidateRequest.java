package com.aldoapps.suararakyat;

import com.aldoapps.suararakyat.model.Candidate;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 06/11/2015.
 */
public class CandidateRequest extends Request<List<Candidate>> {
    List<Candidate> mCandidateList = new ArrayList<>();

    public CandidateRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

//    public CandidateRequest(String url, List<Candidate> candidateList, Map<String, String> headers,
//                            Listener<T> listener, Response.ErrorListener errorListener) {
//        super(Method.GET, url, errorListener);
//
//    }
//
//    /**
//     * Make a GET request and return a parsed object from JSON.
//     *
//     * @param url URL of the request to make
//     * @param clazz Relevant class object, for Gson's reflection
//     * @param headers Map of request headers
//     */
//    public GsonRequest(String url, Class<T> clazz, Map<String, String> headers,
//                       Listener<T> listener, ErrorListener errorListener) {
//        super(Method.GET, url, errorListener);
//        this.clazz = clazz;
//        this.headers = headers;
//        this.listener = listener;
//    }

    @Override
    protected Response<List<Candidate>> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers)
            );

            JSONObject jsonRoot = new JSONObject(json);

            JSONArray candidateList = jsonRoot.getJSONObject("data")
                    .getJSONObject("results")
                    .getJSONArray("candidates");
            JSONObject candidateOneJSON = candidateList.getJSONObject(0);
            JSONObject candidateTwoJSON = candidateList.getJSONObject(1);

            Gson gson = new Gson();
            Candidate candidateOne = gson.fromJson(candidateOneJSON.toString(), Candidate.class);
            Candidate candidateTwo = gson.fromJson(candidateTwoJSON.toString(), Candidate.class);

            mCandidateList.add(candidateOne);
            mCandidateList.add(candidateTwo);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(List<Candidate> response) {

    }
}
