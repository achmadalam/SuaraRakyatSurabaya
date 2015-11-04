package com.aldoapps.suararakyat;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by user on 03/11/2015.
 */
public class MainFragment extends Fragment {

    private static final int SPEECH_REQUEST_CODE = 1692;

    @Bind(R.id.vote_by_dialog) FloatingActionButton mDialogBtn;
    @Bind(R.id.vote_by_voice) FloatingActionButton mVoiceBtn;
    @Bind(R.id.fab_menu) FloatingActionsMenu mFabMenu;
    @Bind(R.id.candidate_one) ImageView mCandidateOneImg;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    public MainFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
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

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SPEECH_REQUEST_CODE &&
                resultCode == MainActivity.RESULT_OK){
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
            );

            for(String asfd : results){
                Log.d("asdf", asfd);
            }
        }

        mFabMenu.collapse();
    }
}
