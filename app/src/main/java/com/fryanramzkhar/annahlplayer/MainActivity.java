package com.fryanramzkhar.annahlplayer;

import android.media.MediaMetadata;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import co.mobiwise.library.InteractivePlayerView;
import co.mobiwise.library.OnActionClickedListener;

public class MainActivity extends AppCompatActivity implements OnActionClickedListener {

    private InteractivePlayerView ipv;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.annahl);

        Uri uri = Uri.parse("android.resource://com.fryanramzkhar.annahlplayer/raw/annahl");
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(MainActivity.this, uri);

        String durastionString = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        int milli = Integer.parseInt(durastionString);
        milli = milli/1000;

        ipv = findViewById(R.id.ipv);
        ipv.setMax(milli);
        ipv.setProgress(0);
        ipv.setOnActionClickedListener(this);

        final Button control = (Button)findViewById(R.id.btnPlay);
        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ipv.isPlaying()){
                    ipv.start();
                    control.setText("PAUSE");

                    mediaPlayer.start();
                }
                else{
                    ipv.stop();
                    control.setText("PLAY");


                    mediaPlayer.pause();

                }
            }
        });
    }
    @Override
    public void onActionClicked(int id){
        switch (id){
            case 1:
                Toast.makeText(this, "Pilihan Shuffle", Toast.LENGTH_SHORT).show();
                break;
            case  2:
                Toast.makeText(this, "Pilihan Like", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "Pilihan Replay", Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }
    }

}
