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

import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by user on 03/11/2015.
 */
public class MainFragment extends Fragment implements TextToSpeech.OnInitListener {

    private static final int SPEECH_REQUEST_CODE = 1692;

    private static final int TTS_DATA_CHECK_CODE = 16;
    private static final String SPEECH_CODE = "MERDEKA";

    private static final int MENU_CALON_PERTAMA = 0;
    private static final int MENU_CALON_KEDUA = 1;
    private static final int MENU_JUMLAH_SUARA = 2;
    private static final int MENU_UNDEFINED = 3;


    private TextToSpeech mTts;

    @Bind(R.id.fabulous_button) FloatingActionButton mVoiceBtn;
    @Bind(R.id.candidate_one) ImageView mCandidateOneImg;
    @Bind(R.id.candidate_two) ImageView mCandidateTwoImg;
    private int mCurrentMenu = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Inspect first whether user has already installed TTS data
         * for this device
         */
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

        mVoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.voice_choose_your_candidate));
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, getString(R.string.locale));
                startActivityForResult(intent, SPEECH_REQUEST_CODE);
            }
        });

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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mTts.speak(getString(R.string.text),
                            TextToSpeech.QUEUE_FLUSH, null, SPEECH_CODE);
                }
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SPEECH_REQUEST_CODE &&
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

                    mCurrentMenu = MENU_CALON_PERTAMA;
                    i = results.size() + 1;
                }else if(
                        token.contains("2") ||
                                token.contains("dua") ||
                                token.contains("kedua") ||
                                token.contains("risma") ||
                                token.contains("wisnu") ||
                                token.contains("whisnu")){

                    mCurrentMenu = MENU_CALON_KEDUA;
                    i = results.size() + 1;
                }else if(
                        token.contains("3") ||
                                token.contains("tiga") ||
                                token.contains("jumlah") ||
                                token.contains("informasi") ||
                                token.contains("suara")){

                    mCurrentMenu = MENU_JUMLAH_SUARA;
                    i = results.size() + 1;
                }else{
                    mCurrentMenu = MENU_UNDEFINED;
                }
            }

            Log.d("asdf", "mCurent Menu " + mCurrentMenu);

            switch (mCurrentMenu){
                case MENU_CALON_PERTAMA:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mTts.speak("anda memilih informasi calon pertama",
                                TextToSpeech.QUEUE_FLUSH, null, "satu");
                    }else{
                        mTts.speak("anda memilih informasi calon pertama",
                                TextToSpeech.QUEUE_FLUSH, null);
                    }
                    break;
                case MENU_CALON_KEDUA:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mTts.speak("anda memilih informasi calon kedua",
                                TextToSpeech.QUEUE_FLUSH, null, "satu");
                    }else{
                        mTts.speak("anda memilih informasi calon kedua",
                                TextToSpeech.QUEUE_FLUSH, null);
                    }
                    break;
                case MENU_JUMLAH_SUARA:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mTts.speak("anda memilih informasi jumlah suara",
                                TextToSpeech.QUEUE_FLUSH, null, "satu");
                    }else{
                        mTts.speak("anda memilih informasi jumlah suara",
                                TextToSpeech.QUEUE_FLUSH, null);
                    }
                    break;
                case MENU_UNDEFINED:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mTts.speak("Maaf suara Anda ga kedengeran mas, coba lagi aja",
                                TextToSpeech.QUEUE_FLUSH, null, "satu");
                    }else{
                        mTts.speak("Maaf suara Anda ga kedengeran mas, coba lagi aja",
                                TextToSpeech.QUEUE_FLUSH, null);
                    }
            }
        }

        if(requestCode == TTS_DATA_CHECK_CODE){
            // Success!
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                mTts = new TextToSpeech(getActivity(), this);
                Log.d("asdf", "ada data tts");
            }else{
                //  fail, attempt to install tts
                Intent installTts = new Intent();
                installTts.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTts);
            }
        }

    }

    @Override
    public void onInit(int status) {
        final Locale indonesiaLocale = new Locale("in", "ID");
        if(status == TextToSpeech.SUCCESS){
            /**
             * Find existing Indonesian Language in System
             * If doesn't exist, back to default phone :(
             */
            final Locale[] availableLocales=Locale.getAvailableLocales();
            for(final Locale locale : availableLocales){
                if(locale.getCountry().equalsIgnoreCase(indonesiaLocale.getCountry())
                        && locale.getLanguage().equalsIgnoreCase(indonesiaLocale.getLanguage())) {
                    mTts.setLanguage(indonesiaLocale);
                }
            }

            /**
             * Finished speaking listener
             */
            mTts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                    Log.d("asdf", "start " + utteranceId);
                }

                @Override
                public void onDone(String utteranceId) {
                    Log.d("asdf", "done " + utteranceId);
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.voice_choose_your_candidate));
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, getString(R.string.locale));
                    startActivityForResult(intent, SPEECH_REQUEST_CODE);
                }

                @Override
                public void onError(String utteranceId) {
                    Log.d("asdf", "error " + utteranceId);
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
