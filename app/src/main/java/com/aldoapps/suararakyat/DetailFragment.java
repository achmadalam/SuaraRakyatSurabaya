package com.aldoapps.suararakyat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aldoapps.suararakyat.model.Candidate;
import com.aldoapps.suararakyat.model.VisionMission;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by user on 04/11/2015.
 */
public class DetailFragment extends Fragment {

    @Bind(R.id.nama) TextView mNama;
    @Bind(R.id.pekerjaan) TextView mPekerjaan;
    @Bind(R.id.tempat_tanggal_lahir) TextView mTTL;
    @Bind(R.id.partai) TextView mPartai;
    @Bind(R.id.visi) TextView mVisi;
    @Bind(R.id.misi) TextView mMisi;

    public DetailFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String endpointVisionMission = "http://api.pemiluapi.org/candidate-pilkada-surabaya/api/vision_missions?apiKey=06ec082d057daa3d310b27483cc3962e";
        String endpointCandidate = "http://api.pemiluapi.org/candidate-pilkada-surabaya/api/candidates?apiKey=06ec082d057daa3d310b27483cc3962e";

        JsonObjectRequest visionMissionRequest = new JsonObjectRequest(
                Request.Method.GET,
                endpointVisionMission,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray visionMissionList = response
                                    .getJSONObject("data")
                                    .getJSONObject("results")
                                    .getJSONArray("vision_missions");

                            Gson gson = new Gson();
                            VisionMission visionMissionOne = gson
                                    .fromJson(visionMissionList.get(0).toString(),
                                            VisionMission.class);
                            VisionMission visionMissionTwo = gson
                                    .fromJson(visionMissionList.get(1).toString(),
                                            VisionMission.class);

                            mVisi.setText(visionMissionOne.getVision());
                            mMisi.setText(visionMissionOne.getMision());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        JsonObjectRequest candidateRequest = new JsonObjectRequest(
                Request.Method.GET,
                endpointCandidate,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray candidateList = response.getJSONObject("data")
                                    .getJSONObject("results")
                                    .getJSONArray("candidates");
                            JSONObject candidateOneJSON = candidateList.getJSONObject(0);
                            JSONObject candidateTwoJSON = candidateList.getJSONObject(1);

                            Gson gson = new Gson();
                            Candidate candidateOne = gson.fromJson(candidateOneJSON.toString(), Candidate.class);
                            Candidate candidateTwo = gson.fromJson(candidateTwoJSON.toString(), Candidate.class);

                            String candidateName = candidateOne.getParticipants().get(0).getName()
                                    + " dan "
                                    + candidateOne.getParticipants().get(1).getName();

                            String candidateOccupation =
                                    candidateOne.getParticipants().get(0).getWork()
                                    + " dan "
                                    + candidateOne.getParticipants().get(1).getWork();

                            String candidateParty =
                                    candidateOne.getEndorsement();

                            mNama.setText(candidateName);
                            mPekerjaan.setText(candidateOccupation);
                            mPartai.setText(candidateTwo.getEndorsement());


                            Log.d("asdf", "candidate 1 " + candidateList.getJSONObject(0).getInt("id"));
                            Log.d("asdf", "participant 1 " + candidateOne.getParticipants().get(0).getName());
                            Log.d("asdf", "participant 2 " + candidateOne.getParticipants().get(1).getName());
                            Log.d("asdf", "participant 1 " + candidateTwo.getParticipants().get(0).getName());
                            Log.d("asdf", "participant 2 " + candidateTwo.getParticipants().get(1).getName());
                            Log.d("asdf", "Endorsement" + candidateTwo.getEndorsement());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("asdf", "error nih " + error.getMessage());
                    }
                }
        );

        SuaraRakyatApplication.getInstance().addToRequestQueue(candidateRequest);
        SuaraRakyatApplication.getInstance().addToRequestQueue(visionMissionRequest);
    }

    public static DetailFragment newInstance(){
        return new DetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
