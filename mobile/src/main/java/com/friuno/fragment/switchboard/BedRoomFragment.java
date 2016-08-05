package com.friuno.fragment.switchboard;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.friuno.R;
import com.friuno.fragment.SwitchBoardFragment;
import com.friuno.timer.TimeScheduler;
import com.friuno.util.Constants;

public class BedRoomFragment extends Fragment {

    private static final String TAG = "BedRoomFragment";
    public static final int RECIEVE_TIMER_ACTION = 202;
    public static final int RECIEVE_SPEECH_MESSAGE = 203;

    private static ToggleButton switch1Button, switch2Button, switch3Button, switch4Button, switch5Button;
    private static TextView switch1Text, switch2Text, switch3Text, switch4Text, switch5Text;
    private static TextView switch1Time, switch2Time, switch3Time, switch4Time, switch5Time;
    private final SwitchBoardFragment switchBoardFragment = new SwitchBoardFragment();

    private Vibrator vibrator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_switchboard_bed_room, container, false);

        switch1Button = (ToggleButton) view.findViewById(R.id.switch1Button);
        switch2Button = (ToggleButton) view.findViewById(R.id.switch2Button);
        switch3Button = (ToggleButton) view.findViewById(R.id.switch3Button);
        switch4Button = (ToggleButton) view.findViewById(R.id.switch4Button);
        switch5Button = (ToggleButton) view.findViewById(R.id.switch5Button);

        switch1Text = (TextView) view.findViewById(R.id.switch1Text);
        switch2Text = (TextView) view.findViewById(R.id.switch2Text);
        switch3Text = (TextView) view.findViewById(R.id.switch3Text);
        switch4Text = (TextView) view.findViewById(R.id.switch4Text);
        switch5Text = (TextView) view.findViewById(R.id.switch5Text);

        switch1Time = (TextView) view.findViewById(R.id.switch1Time);
        switch2Time = (TextView) view.findViewById(R.id.switch2Time);
        switch3Time = (TextView) view.findViewById(R.id.switch3Time);
        switch4Time = (TextView) view.findViewById(R.id.switch4Time);
        switch5Time = (TextView) view.findViewById(R.id.switch5Time);

        vibrator = (Vibrator) view.getContext().getSystemService(Context.VIBRATOR_SERVICE);

        switch1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch1Button.isChecked()) {
                    TimeScheduler.bedRoomSwitch1StartTimer();
                    switch1Time.setTextColor(ContextCompat.getColor(getContext(), R.color.md_green_500));
                    if (!switchBoardFragment.getIsDeviceConnected())
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                } else {
                    TimeScheduler.bedRoomSwitch1StopTimer();
                    switch1Time.setTextColor(ContextCompat.getColor(getContext(),R.color.md_red_500));
                    if (!switchBoardFragment.getIsDeviceConnected())
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch2Button.isChecked()) {
                    TimeScheduler.bedRoomSwitch2StartTimer();
                    switch2Time.setTextColor(ContextCompat.getColor(getContext(),R.color.md_green_500));
                    if (!switchBoardFragment.getIsDeviceConnected())
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                } else {
                    TimeScheduler.bedRoomSwitch2StopTimer();
                    switch2Time.setTextColor(ContextCompat.getColor(getContext(), R.color.md_red_500));
                    if (!switchBoardFragment.getIsDeviceConnected())
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch3Button.isChecked()) {
                    TimeScheduler.bedRoomSwitch3StartTimer();
                    switch3Time.setTextColor(ContextCompat.getColor(getContext(), R.color.md_green_500));
                    if (!switchBoardFragment.getIsDeviceConnected())
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                } else {
                    TimeScheduler.bedRoomSwitch3StopTimer();
                    switch3Time.setTextColor(ContextCompat.getColor(getContext(), R.color.md_red_500));
                    if (!switchBoardFragment.getIsDeviceConnected())
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch4Button.isChecked()) {
                    TimeScheduler.bedRoomSwitch4StartTimer();
                    switch4Time.setTextColor(ContextCompat.getColor(getContext(), R.color.md_green_500));
                    if (!switchBoardFragment.getIsDeviceConnected())
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                } else {
                    TimeScheduler.bedRoomSwitch4StopTimer();
                    switch4Time.setTextColor(ContextCompat.getColor(getContext(), R.color.md_red_500));
                    if (!switchBoardFragment.getIsDeviceConnected())
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch5Button.isChecked()) {
                    TimeScheduler.bedRoomSwitch5StartTimer();
                    switch5Time.setTextColor(ContextCompat.getColor(getContext(), R.color.md_green_500));
                    if (!switchBoardFragment.getIsDeviceConnected())
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                } else {
                    TimeScheduler.bedRoomSwitch5StopTimer();
                    switch5Time.setTextColor(ContextCompat.getColor(getContext(), R.color.md_red_500));
                    if (!switchBoardFragment.getIsDeviceConnected())
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public static Handler bedRoomTimeHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RECIEVE_TIMER_ACTION:
                    String[] switchTimer = (String[]) msg.obj;
                    if (switchTimer[0].equals(Constants.SWITCH_1)) {
                        switch1Time.setText(switchTimer[1]);
                    } else if (switchTimer[0].equals(Constants.SWITCH_2)) {
                        switch2Time.setText(switchTimer[1]);
                    } else if (switchTimer[0].equals(Constants.SWITCH_2)) {
                        switch2Time.setText(switchTimer[1]);
                    } else if (switchTimer[0].equals(Constants.SWITCH_3)) {
                        switch3Time.setText(switchTimer[1]);
                    } else if (switchTimer[0].equals(Constants.SWITCH_4)) {
                        switch4Time.setText(switchTimer[1]);
                    } else if (switchTimer[0].equals(Constants.SWITCH_5)) {
                        switch5Time.setText(switchTimer[1]);
                    }
            }
        }
    };

    public Handler speechHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RECIEVE_SPEECH_MESSAGE: {
                    String switchText = null;
                    String[] switchAction = (String[]) msg.obj;
                    if (switchAction[0].equalsIgnoreCase("TRUE")) {
                        switch (switchAction[1]) {
                            case Constants.SWITCH_1_ON:
                                TimeScheduler.bedRoomSwitch1StartTimer();
                                switch1Time.setTextColor(Color.parseColor("#4caf50"));
                                switch1Button.setChecked(true);
                                switchText = String.valueOf(switch1Text.getText()) + " turned ON";
                                break;
                            case Constants.SWITCH_1_OFF:
                                TimeScheduler.bedRoomSwitch1StopTimer();
                                switch1Time.setTextColor(Color.parseColor("#f44336"));
                                switch1Button.setChecked(false);
                                switchText = String.valueOf(switch1Text.getText()) + " turned OFF";
                                break;
                            case Constants.SWITCH_2_ON:
                                TimeScheduler.bedRoomSwitch2StartTimer();
                                switch2Time.setTextColor(Color.parseColor("#4caf50"));
                                switch2Button.setChecked(true);
                                switchText = String.valueOf(switch2Text.getText()) + " turned ON";
                                break;
                            case Constants.SWITCH_2_OFF:
                                TimeScheduler.bedRoomSwitch2StopTimer();
                                switch2Time.setTextColor(Color.parseColor("#f44336"));
                                switch2Button.setChecked(false);
                                switchText = String.valueOf(switch2Text.getText()) + " turned OFF";
                                break;
                            case Constants.SWITCH_3_ON:
                                TimeScheduler.bedRoomSwitch3StartTimer();
                                switch3Time.setTextColor(Color.parseColor("#4caf50"));
                                switch3Button.setChecked(true);
                                switchText = String.valueOf(switch3Text.getText()) + " turned ON";
                                break;
                            case Constants.SWITCH_3_OFF:
                                TimeScheduler.bedRoomSwitch3StopTimer();
                                switch3Time.setTextColor(Color.parseColor("#f44336"));
                                switch3Button.setChecked(false);
                                switchText = String.valueOf(switch3Text.getText()) + " turned OFF";
                                break;
                            case Constants.SWITCH_4_ON:
                                TimeScheduler.bedRoomSwitch4StartTimer();
                                switch4Time.setTextColor(Color.parseColor("#4caf50"));
                                switch4Button.setChecked(true);
                                switchText = String.valueOf(switch4Text.getText()) + " turned ON";
                                break;
                            case Constants.SWITCH_4_OFF:
                                TimeScheduler.bedRoomSwitch4StopTimer();
                                switch4Time.setTextColor(Color.parseColor("#f44336"));
                                switch4Button.setChecked(false);
                                switchText = String.valueOf(switch4Text.getText()) + " turned OFF";
                                break;
                            case Constants.SWITCH_5_ON:
                                TimeScheduler.bedRoomSwitch5StartTimer();
                                switch5Time.setTextColor(Color.parseColor("#4caf50"));
                                switch5Button.setChecked(true);
                                switchText = String.valueOf(switch5Text.getText()) + " turned ON";
                                break;
                            case Constants.SWITCH_5_OFF:
                                TimeScheduler.bedRoomSwitch5StopTimer();
                                switch5Time.setTextColor(Color.parseColor("#f44336"));
                                switch5Button.setChecked(false);
                                switchText = String.valueOf(switch5Text.getText()) + " turned OFF";
                                break;
                        }
                        Toast.makeText(SwitchBoardFragment.context.getApplicationContext(), switchText, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SwitchBoardFragment.context.getApplicationContext(), "Invalid Voice Syntax!\nPlease Try Again...!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
        }
    };
}
