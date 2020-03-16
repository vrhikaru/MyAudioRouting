package com.mdw.myaudiorouting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jp.kshoji.audio.receiver.AudioRouter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AudioRouter audioRouter;
    Button wire_headset_btn, speaker_btn, bluetooth_a2dp_btn, usb_audio_btn, no_routing_btn;
    TextView debug_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioRouter = new AudioRouter(this);

        initUIWithRouter();
    }

    void initUIWithRouter(){
        wire_headset_btn = findViewById(R.id.wire_headphone_button);
        wire_headset_btn.setOnClickListener(this);

        speaker_btn = findViewById(R.id.speaker_button);
        speaker_btn.setOnClickListener(this);

        bluetooth_a2dp_btn = findViewById(R.id.bluetooth_a2dp_button);
        bluetooth_a2dp_btn.setOnClickListener(this);

        usb_audio_btn = findViewById(R.id.useb_audio_button);
        usb_audio_btn.setOnClickListener(this);

        no_routing_btn = findViewById(R.id.no_routing_button);
        no_routing_btn.setOnClickListener(this);


        debug_text = findViewById(R.id.debug_textView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (audioRouter != null) {
            audioRouter.terminate();
            audioRouter = null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.wire_headphone_button:{
                audioRouter.setRouteMode(AudioRouter.AudioRouteMode.WIRED_HEADPHONE);
                debug_text.setText("Routing to WIre headphone");
            }
            break;
            case R.id.speaker_button:{
                audioRouter.setRouteMode(AudioRouter.AudioRouteMode.SPEAKER);
                debug_text.setText("Routing to Speaker");
            }
            break;
            case R.id.bluetooth_a2dp_button:{
                audioRouter.setRouteMode(AudioRouter.AudioRouteMode.BLUETOOTH_A2DP);
                debug_text.setText("Routing to Bluetooth A2DP");
            }
            break;
            case R.id.useb_audio_button:{
                audioRouter.setRouteMode(AudioRouter.AudioRouteMode.USB_AUDIO);
                debug_text.setText("Routing to USB Audio");
            }
            break;
            case R.id.no_routing_button:{
                audioRouter.setRouteMode(AudioRouter.AudioRouteMode.NO_ROUTING);
                debug_text.setText("No Change  routing");
            }
            break;
        }
    }
}
