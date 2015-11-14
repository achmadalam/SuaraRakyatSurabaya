package com.aldoapps.suararakyat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aldoapps.suararakyat.model.Candidate;
import com.aldoapps.suararakyat.model.VisionMission;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by user on 03/11/2015.
 */
public class MainFragment extends Fragment implements TextToSpeech.OnInitListener {

    private static final int SPEECH_INFORMATION_CODE = 1692;
    private static final int SPEECH_VOTE_CODE = 1693;
    private static final int SPEECH_PORTAL_CODE = 1694;

    private static final int TTS_DATA_CHECK_CODE = 16;
    private static final String SPEECH_CODE = "MERDEKA";

    private static final int MENU_INFO_CALON_PERTAMA = 0;
    private static final int MENU_INFO_CALON_KEDUA = 1;
    private static final int MENU_INFO_JUMLAH_SUARA = 2;
    private static final int MENU_UNDEFINED = 99;

    private static final int MENU_PORTAL_SATU = 11;
    private static final int MENU_PORTAL_DUA = 12;

    // text to speech area

    /**
     * turns out this hashmap is container for utterance id
     * so we create different utterancemap for different tts
     */
    private HashMap<String, String> mUMIDInformation = new HashMap<>();
    private HashMap<String, String> mUMIDPortal = new HashMap<>();
    private HashMap<String, String> mUMIDVote = new HashMap<>();
    private TextToSpeech mTts;
    private static final String TTS_INFORMATION_CODE = "information_code";
    private static final String TTS_VOTE_CODE = "vote_code";
    private static final String TTS_PORTAL_CODE = "portal_code";

    @Bind(R.id.fabulous_button) FloatingActionButton mVoiceBtn;
    @Bind(R.id.candidate_one) ImageView mCandidateOneImg;
    @Bind(R.id.candidate_two) ImageView mCandidateTwoImg;
    private int mCurrentInfoMenu = 0;
    private int mCurrentPortalMenu = 0;
    // yes, main = portal. I'm THAT inconsistent

    // Information Result
    private VisionMission mVisionMissionOne, mVisionMissionTwo;
    private Candidate mCandidateOne, mCandidateTwo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkTTSAvailability();
    }

    /**
     * Inspect whether user has already installed TTS data
     * for this device. Some device has limited stoarge
     * and lack some language specific resource files
     */
    private void checkTTSAvailability() {
        Intent checkTtsIntent = new Intent();
        checkTtsIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTtsIntent, TTS_DATA_CHECK_CODE);
    }

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        mCandidateOneImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });

        mCandidateTwoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    mTts.speak(getString(R.string.text),
//                            TextToSpeech.QUEUE_FLUSH, null, SPEECH_CODE);
//                }
            }
        });

        return view;
    }

    private void listenForInformation(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTts.speak(getString(R.string.text),
                    TextToSpeech.QUEUE_FLUSH, null, TTS_INFORMATION_CODE);
        }else{
            mTts.speak(getString(R.string.text),
                    TextToSpeech.QUEUE_FLUSH, mUMIDInformation);
        }
    }

    private void listenForPortal(){
        String menuPiwali = ("Informasi Pilwali Surabaya 2015, " +
                "untuk informasi mengenai pilwali katakan satu, " +
                "untuk memilih pasangan calon katakan dua");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTts.speak(menuPiwali,
                    TextToSpeech.QUEUE_FLUSH, null, TTS_PORTAL_CODE);
        }else{
            mTts.speak(menuPiwali,
                    TextToSpeech.QUEUE_FLUSH, mUMIDPortal);
        }
    }

    private void listenForVote(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTts.speak(getString(R.string.text),
                    TextToSpeech.QUEUE_FLUSH, null, TTS_VOTE_CODE);
        }else{
            mTts.speak(getString(R.string.text),
                    TextToSpeech.QUEUE_FLUSH, mUMIDVote);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // main menu
        if(requestCode == SPEECH_PORTAL_CODE &&
                resultCode == MainActivity.RESULT_OK){

            /**
             * this return a list of guessed words
             * sorted in descending order
             * the 0 index is the most accurate one
             */
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
            );

            for(int i = 0; i < results.size(); i++){
                String token = results.get(i).toLowerCase();

                Log.d("asdf", "hasil: " + token);
                if(token.contains("1") ||
                        token.contains("satu") ||
                        token.contains("pertama") ||
                        token.contains("informasi") ||
                        token.contains("info")){

                    mCurrentPortalMenu = MENU_PORTAL_SATU;
                    i = results.size() + 1;
                }else if(
                        token.contains("2") ||
                                token.contains("dua") ||
                                token.contains("kedua") ||
                                token.contains("vote") ||
                                token.contains("voting") ||
                                token.contains("pilih") ||
                                token.contains("milih") ||
                                token.contains("coblos")){

                    mCurrentPortalMenu = MENU_PORTAL_DUA;
                    i = results.size() + 1;
                }else{
                    mCurrentPortalMenu = MENU_UNDEFINED;
                }
            }

            if(mCurrentPortalMenu == MENU_PORTAL_SATU){
                listenForInformation();
            }else if(mCurrentPortalMenu == MENU_PORTAL_DUA){
                listenForVote();
            }

        }

        // If user wants to know candidate information
        if(requestCode == SPEECH_INFORMATION_CODE &&
                resultCode == MainActivity.RESULT_OK){

            /**
             * this return a list of guessed words
             * sorted in descending order
             * the 0 index is the most accurate one
             */
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
            );

            for(int i = 0; i < results.size(); i++){
                String token = results.get(i).toLowerCase();

                Log.d("asdf", "hasil: " + token);
                if(token.contains("1") ||
                        token.contains("satu") ||
                        token.contains("pertama") ||
                        token.contains("rasio") ||
                        token.contains("lucy")){

                    mCurrentInfoMenu = MENU_INFO_CALON_PERTAMA;
                    i = results.size() + 1;
                }else if(
                        token.contains("2") ||
                                token.contains("dua") ||
                                token.contains("kedua") ||
                                token.contains("risma") ||
                                token.contains("wisnu") ||
                                token.contains("whisnu")){

                    mCurrentInfoMenu = MENU_INFO_CALON_KEDUA;
                    i = results.size() + 1;
                }else if(
                        token.contains("3") ||
                                token.contains("tiga") ||
                                token.contains("jumlah") ||
                                token.contains("informasi") ||
                                token.contains("suara")){

                    mCurrentInfoMenu = MENU_INFO_JUMLAH_SUARA;
                    i = results.size() + 1;
                }else{
                    mCurrentInfoMenu = MENU_UNDEFINED;
                }
            }

            if(mCurrentInfoMenu == MENU_INFO_CALON_KEDUA
                    || mCurrentInfoMenu == MENU_INFO_CALON_PERTAMA){
                /**
                 * getCandidateInformation() will be called
                 * subsequently to preserve integrity
                 */
                getVisionMission();
            }
        }

        /**
         * Language specific resource file inspection
         */
        if(requestCode == TTS_DATA_CHECK_CODE){
            // Success! File has already been installed
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                mTts = new TextToSpeech(getActivity(), this);
            }else{
                // fail, attempt to install tts
                Intent installTts = new Intent();
                installTts.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTts);
            }
        }

    }

    private void respondInformationRequest() {
        String p1name;
        String p2name;
        String p1dob;
        String p2dob;
        String p1pob;
        String p2pob;
        String p1job;
        String p2job;

        String candidateParty;
        String candidateVision;
        String candidateMission;
        String completeInformation;

        switch (mCurrentInfoMenu){
            case MENU_INFO_CALON_PERTAMA:
                p1name = mCandidateOne.getParticipants().get(0).getName();
                p2name = mCandidateOne.getParticipants().get(1).getName();
                p1dob = mCandidateOne.getParticipants().get(0).getDob().substring(0, 4);
                p2dob = mCandidateOne.getParticipants().get(0).getDob().substring(0, 4);
                p1pob = mCandidateOne.getParticipants().get(0).getPob();
                p2pob = mCandidateOne.getParticipants().get(1).getPob();
                p1job = mCandidateOne.getParticipants().get(0).getWork();
                p2job = mCandidateOne.getParticipants().get(1).getWork();

                candidateParty = mCandidateOne.getEndorsement();
                candidateVision = mVisionMissionOne.getVision();
                candidateMission = mVisionMissionOne.getMision();

                completeInformation =
                        "Pasangan calon pertama adalah " + p1name +
                                " sebagai calon walikota dan " + p2name +
                                " sebagai calon walikota. " + p1name +
                                " lahir di " + p1pob +
                                " , pada tahun " + p1dob +
                                " , dan memiliki pekerjaan sebagai " + p1job +
                                " dan memiliki pekerjaan sebagai " + p1job +
                                ". Sedangkan " + p2name +
                                " lahir di " + p2pob +
                                " , pada tahun " + p2dob +
                                " , dan memiliki pekerjaan sebagai " + p2job +
                                ". Pasangan calon ini berasal dari partai " + candidateParty +
                                ". Pasangan calon pertama memiliki Visi " + candidateVision +
                                " . Dan memiliki misi " + candidateMission;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mTts.speak(completeInformation,
                            TextToSpeech.QUEUE_FLUSH, null, TTS_INFORMATION_CODE);
                }else{
                    mTts.speak(completeInformation,
                            TextToSpeech.QUEUE_FLUSH, mUMIDInformation);
                }

                break;
            case MENU_INFO_CALON_KEDUA:

                p1name = mCandidateTwo.getParticipants().get(0).getName();
                p2name = mCandidateTwo.getParticipants().get(1).getName();
                p1dob = mCandidateTwo.getParticipants().get(0).getDob().substring(0, 4);
                p2dob = mCandidateTwo.getParticipants().get(0).getDob().substring(0, 4);
                p1pob = mCandidateTwo.getParticipants().get(0).getPob();
                p2pob = mCandidateTwo.getParticipants().get(1).getPob();
                p1job = mCandidateTwo.getParticipants().get(0).getWork();
                p2job = mCandidateTwo.getParticipants().get(1).getWork();

                candidateParty = mCandidateTwo.getEndorsement();
                candidateVision = mVisionMissionTwo.getVision();
                candidateMission = mVisionMissionTwo.getMision();

                completeInformation =
                        "Pasangan calon kedua adalah " + p1name +
                                " sebagai calon walikota dan " + p2name +
                                " sebagai calon walikota. " + p1name +
                                " lahir di " + p1pob +
                                " , pada tahun " + p1dob +
                                " , dan memiliki pekerjaan sebagai " + p1job +
                                ". Sedangkan " + p2name +
                                " lahir di " + p2pob +
                                " , pada tahun " + p2dob +
                                " , dan memiliki pekerjaan sebagai " + p2job +
                                ". Pasangan calon ini berasal dari partai " + candidateParty +
                                ". Pasangan calon dua memiliki Visi " + candidateVision +
                                " . Dan memiliki misi " + candidateMission;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mTts.speak(completeInformation,
                            TextToSpeech.QUEUE_FLUSH, null, TTS_INFORMATION_CODE);
                }else{
                    mTts.speak(completeInformation,
                            TextToSpeech.QUEUE_FLUSH, mUMIDInformation);
                }
                break;
            case MENU_INFO_JUMLAH_SUARA:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mTts.speak("anda memilih informasi jumlah suara",
                            TextToSpeech.QUEUE_FLUSH, null, TTS_INFORMATION_CODE);
                }else{
                    mTts.speak("anda memilih informasi jumlah suara",
                            TextToSpeech.QUEUE_FLUSH, mUMIDInformation);
                }
                break;
            case MENU_UNDEFINED:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mTts.speak("Maaf suara Anda ga kedengeran mas, coba lagi aja",
                            TextToSpeech.QUEUE_FLUSH, null, TTS_INFORMATION_CODE);
                }else{
                    mTts.speak("Maaf suara Anda ga kedengeran mas, coba lagi aja",
                            TextToSpeech.QUEUE_FLUSH, mUMIDInformation);
                }
        }
    }

    private void getVisionMission() {
        mTts.speak("Harap tunggu sebentar..",
                TextToSpeech.QUEUE_ADD, null);

        JsonObjectRequest visionMissionRequest = new JsonObjectRequest(
                Request.Method.GET,
                KPUConstant.ENDPOINT_VISION_MISSION,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray visionMissionList = response
                                    .getJSONObject("data")
                                    .getJSONObject("results")
                                    .getJSONArray("vision_missions");

                            Gson gson = new Gson();
                            mVisionMissionOne = gson
                                    .fromJson(visionMissionList.get(0).toString(),
                                            VisionMission.class);
                            mVisionMissionTwo = gson
                                    .fromJson(visionMissionList.get(1).toString(),
                                            VisionMission.class);

                            getCandidateInformation();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("asdf", "Something error");
                    }
                }
        );
        SuaraRakyatApplication.getInstance().addToRequestQueue(visionMissionRequest);
    }

    private void getCandidateInformation() {
        JsonObjectRequest candidateRequest = new JsonObjectRequest(
                Request.Method.GET,
                KPUConstant.ENDPOINT_CANDIDATE,
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
                            mCandidateOne = gson.fromJson(candidateOneJSON.toString(), Candidate.class);
                            mCandidateTwo = gson.fromJson(candidateTwoJSON.toString(), Candidate.class);

                            respondInformationRequest();
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
    }

    private void getCurrentElectionStatus(){

    }

    private void speakForInformation(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Anda ingin tahu Informasi apa?");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, getString(R.string.locale));
        startActivityForResult(intent, SPEECH_INFORMATION_CODE);
    }

    private void speakForPortal(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Informasi atau memilih paslon?");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, getString(R.string.locale));
        startActivityForResult(intent, SPEECH_PORTAL_CODE);
    }

    private void speakForVote(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.voice_choose_your_candidate));
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, getString(R.string.locale));
        startActivityForResult(intent, SPEECH_VOTE_CODE);
    }

    /**
     * Init is called after checking TextToSpeech availability
     * All initialization is created here
     * @param status
     */
    @Override
    public void onInit(int status) {
        final Locale indonesiaLocale = new Locale("in", "ID");
        if(status == TextToSpeech.SUCCESS){
            /**
             * Find existing Indonesian Language in System
             * If doesn't exist, back to default phone :(
             * not that we also can use this method too:
             * if(mTts.isLanguageAvailable(indonesiaLocale)
             * == TextToSpeech.LANG_COUNTRY_AVAILABLE)
             */
            final Locale[] availableLocales=Locale.getAvailableLocales();
            for(final Locale locale : availableLocales){
                if(locale.getCountry().equalsIgnoreCase(indonesiaLocale.getCountry())
                        && locale.getLanguage().equalsIgnoreCase(indonesiaLocale.getLanguage())) {
                    mTts.setLanguage(indonesiaLocale);
                    mUMIDInformation.put(TextToSpeech.Engine.KEY_PARAM_STREAM, TTS_INFORMATION_CODE);
                    mUMIDPortal.put(TextToSpeech.Engine.KEY_PARAM_STREAM, TTS_PORTAL_CODE);
                    mUMIDVote.put(TextToSpeech.Engine.KEY_PARAM_STREAM, TTS_VOTE_CODE);
                }
            }

            /**
             * Finished speaking listener
             */
            mTts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                    Log.d("asdf", "start utteranceId " + utteranceId);
                }

                @Override
                public void onDone(String utteranceId) {
                    Log.d("asdf", "done utteranceId " + utteranceId);

                    switch (utteranceId){
                        case TTS_INFORMATION_CODE:
                            speakForInformation();
                            break;

                        case TTS_PORTAL_CODE:
                            speakForPortal();
                            break;

                        case TTS_VOTE_CODE:
                            speakForVote();
                            break;
                    }
                }

                @Override
                public void onError(String utteranceId) {
                    Log.d("asdf", "error " + utteranceId);
                }
            });

            mVoiceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenForPortal();
                }
            });

        }else if(status == TextToSpeech.ERROR){
            Toast.makeText(getActivity(), "error bang ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        if(mTts != null){
            mTts.shutdown();
        }

        super.onDestroy();
    }
}
