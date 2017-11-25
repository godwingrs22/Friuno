package com.friuno.fragment.switchboard;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.friuno.R;
import com.friuno.arduino.ArduinoConstants;
import com.friuno.arduino.ArduinoController;
import com.friuno.fragment.SwitchBoardFragment;
import com.friuno.timer.TimeScheduler;
import com.friuno.util.Constants;

import java.text.DecimalFormat;

/**
 * Created by GodwinRoseSamuel on 09-11-2017.
 */
public class LivingRoomFragment extends Fragment {

    private static final String TAG = "LivingRoomFragment";
    public static final int RECIEVE_MESSAGE = 201;
    public static final int RECIEVE_TIMER_ACTION = 202;
    public static final int RECIEVE_SPEECH_MESSAGE = 203;

    private static ToggleButton switch1Button;
    private static ToggleButton switch2Button;
    private static ToggleButton switch3Button;
    private static ToggleButton switch4Button;
    private static ToggleButton switch5Button;
    private static ToggleButton switch6Button;
    private static ToggleButton switch7Button;
    private static ToggleButton switch8Button;
    private static TextView switch1Text, switch2Text, switch3Text, switch4Text, switch5Text, switch6Text, switch7Text, switch8Text;
    private static TextView switch1Time, switch2Time, switch3Time, switch4Time, switch5Time, switch6Time, switch7Time, switch8Time;

    private Vibrator vibrator;
    private final SwitchBoardFragment switchBoardFragment = new SwitchBoardFragment();
    private static ArduinoController arduinoController = null;

    public static int livingRoom_Switch_ON = 0;
    public static int livingRoom_Switch_OFF = 8;

    public static int livingRoom_Load = 80;
    public static Double livingRoom_unit = 20.4;

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
                                TimeScheduler.livingRoomSwitch1StartTimer();
                                switch1Time.setTextColor(Color.parseColor("#4caf50"));
                                switch1Button.setChecked(true);
                                switchText = String.valueOf(switch1Text.getText()) + " turned ON";
                                if (switchBoardFragment.getIsDeviceConnected()) {
                                    livingRoom_Load = 15;
                                    livingRoom_Switch_ON = 1;
                                    livingRoom_Switch_OFF = 7;
                                } else {
                                    livingRoom_Load = 30;
                                    livingRoom_Switch_ON = 3;
                                    livingRoom_Switch_OFF = 5;
                                }

                                arduinoController.sendData(ArduinoConstants.SWITCH_1_ON);
                                break;
                            case Constants.SWITCH_1_OFF:
                                TimeScheduler.livingRoomSwitch1StopTimer();
                                switch1Time.setTextColor(Color.parseColor("#f44336"));
                                switch1Button.setChecked(false);
                                switchText = String.valueOf(switch1Text.getText()) + " turned OFF";
                                if (switchBoardFragment.getIsDeviceConnected()) {
                                    livingRoom_Load = 0;
                                    livingRoom_Switch_ON = 0;
                                    livingRoom_Switch_OFF = 8;
                                } else {
                                    livingRoom_Load = 30;
                                    livingRoom_Switch_ON = 3;
                                    livingRoom_Switch_OFF = 5;
                                }
                                arduinoController.sendData(ArduinoConstants.SWITCH_1_OFF);
                                break;
                            case Constants.SWITCH_2_ON:
                                TimeScheduler.livingRoomSwitch2StartTimer();
                                switch2Time.setTextColor(Color.parseColor("#4caf50"));
                                switch2Button.setChecked(true);
                                switchText = String.valueOf(switch2Text.getText()) + " turned ON";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_2_ON);
                                break;
                            case Constants.SWITCH_2_OFF:
                                TimeScheduler.livingRoomSwitch2StopTimer();
                                switch2Time.setTextColor(Color.parseColor("#f44336"));
                                switch2Button.setChecked(false);
                                switchText = String.valueOf(switch2Text.getText()) + " turned OFF";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_2_OFF);
                                break;
                            case Constants.SWITCH_3_ON:
                                TimeScheduler.livingRoomSwitch3StartTimer();
                                switch3Time.setTextColor(Color.parseColor("#4caf50"));
                                switch3Button.setChecked(true);
                                switchText = String.valueOf(switch3Text.getText()) + " turned ON";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_3_ON);
                                break;
                            case Constants.SWITCH_3_OFF:
                                TimeScheduler.livingRoomSwitch3StopTimer();
                                switch3Time.setTextColor(Color.parseColor("#f44336"));
                                switch3Button.setChecked(false);
                                switchText = String.valueOf(switch3Text.getText()) + " turned OFF";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_3_OFF);
                                break;
                            case Constants.SWITCH_4_ON:
                                TimeScheduler.livingRoomSwitch4StartTimer();
                                switch4Time.setTextColor(Color.parseColor("#4caf50"));
                                switch4Button.setChecked(true);
                                switchText = String.valueOf(switch4Text.getText()) + " turned ON";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_4_ON);
                                break;
                            case Constants.SWITCH_4_OFF:
                                TimeScheduler.livingRoomSwitch4StopTimer();
                                switch4Time.setTextColor(Color.parseColor("#f44336"));
                                switch4Button.setChecked(false);
                                switchText = String.valueOf(switch4Text.getText()) + " turned OFF";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_4_OFF);
                                break;
                            case Constants.SWITCH_5_ON:
                                TimeScheduler.livingRoomSwitch5StartTimer();
                                switch5Time.setTextColor(Color.parseColor("#4caf50"));
                                switch5Button.setChecked(true);
                                switchText = String.valueOf(switch5Text.getText()) + " turned ON";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_5_ON);
                                break;
                            case Constants.SWITCH_5_OFF:
                                TimeScheduler.livingRoomSwitch5StopTimer();
                                switch5Time.setTextColor(Color.parseColor("#f44336"));
                                switch5Button.setChecked(false);
                                switchText = String.valueOf(switch5Text.getText()) + " turned OFF";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_5_OFF);
                                break;
                            case Constants.SWITCH_6_ON:
                                TimeScheduler.livingRoomSwitch6StartTimer();
                                switch6Time.setTextColor(Color.parseColor("#4caf50"));
                                switch6Button.setChecked(true);
                                switchText = String.valueOf(switch6Text.getText()) + " turned ON";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_6_ON);
                                break;
                            case Constants.SWITCH_6_OFF:
                                TimeScheduler.livingRoomSwitch6StopTimer();
                                switch6Time.setTextColor(Color.parseColor("#f44336"));
                                switch6Button.setChecked(false);
                                switchText = String.valueOf(switch6Text.getText()) + " turned OFF";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_6_OFF);
                                break;
                            case Constants.SWITCH_7_ON:
                                TimeScheduler.livingRoomSwitch7StartTimer();
                                switch7Time.setTextColor(Color.parseColor("#4caf50"));
                                switch7Button.setChecked(true);
                                switchText = String.valueOf(switch7Text.getText()) + " turned ON";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_7_ON);
                                break;
                            case Constants.SWITCH_7_OFF:
                                TimeScheduler.livingRoomSwitch7StopTimer();
                                switch7Time.setTextColor(Color.parseColor("#f44336"));
                                switch7Button.setChecked(false);
                                switchText = String.valueOf(switch7Text.getText()) + " turned OFF";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_7_OFF);
                                break;
                            case Constants.SWITCH_8_ON:
                                TimeScheduler.livingRoomSwitch8StartTimer();
                                switch8Time.setTextColor(Color.parseColor("#4caf50"));
                                switch8Button.setChecked(true);
                                switchText = String.valueOf(switch8Text.getText()) + " turned ON";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_8_ON);
                                break;
                            case Constants.SWITCH_8_OFF:
                                TimeScheduler.livingRoomSwitch8StopTimer();
                                switch8Time.setTextColor(Color.parseColor("#f44336"));
                                switch8Button.setChecked(false);
                                switchText = String.valueOf(switch8Text.getText()) + " turned OFF";
                                if (switchBoardFragment.getIsDeviceConnected())
                                    arduinoController.sendData(ArduinoConstants.SWITCH_8_OFF);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_switchboard_living_room, container, false);

        arduinoController = switchBoardFragment.getArduinoController();

        switch1Button = (ToggleButton) view.findViewById(R.id.switch1Button);
        switch2Button = (ToggleButton) view.findViewById(R.id.switch2Button);
        switch3Button = (ToggleButton) view.findViewById(R.id.switch3Button);
        switch4Button = (ToggleButton) view.findViewById(R.id.switch4Button);
        switch5Button = (ToggleButton) view.findViewById(R.id.switch5Button);
        switch6Button = (ToggleButton) view.findViewById(R.id.switch6Button);
        switch7Button = (ToggleButton) view.findViewById(R.id.switch7Button);
        switch8Button = (ToggleButton) view.findViewById(R.id.switch8Button);

        switch1Text = (TextView) view.findViewById(R.id.switch1Text);
        switch2Text = (TextView) view.findViewById(R.id.switch2Text);
        switch3Text = (TextView) view.findViewById(R.id.switch3Text);
        switch4Text = (TextView) view.findViewById(R.id.switch4Text);
        switch5Text = (TextView) view.findViewById(R.id.switch5Text);
        switch6Text = (TextView) view.findViewById(R.id.switch6Text);
        switch7Text = (TextView) view.findViewById(R.id.switch7Text);
        switch8Text = (TextView) view.findViewById(R.id.switch8Text);

        switch1Time = (TextView) view.findViewById(R.id.switch1Time);
        switch2Time = (TextView) view.findViewById(R.id.switch2Time);
        switch3Time = (TextView) view.findViewById(R.id.switch3Time);
        switch4Time = (TextView) view.findViewById(R.id.switch4Time);
        switch5Time = (TextView) view.findViewById(R.id.switch5Time);
        switch6Time = (TextView) view.findViewById(R.id.switch6Time);
        switch7Time = (TextView) view.findViewById(R.id.switch7Time);
        switch8Time = (TextView) view.findViewById(R.id.switch8Time);

        vibrator = (Vibrator) view.getContext().getSystemService(Context.VIBRATOR_SERVICE);

        switch1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch1Button.isChecked()) {
                    TimeScheduler.livingRoomSwitch1StartTimer();
                    switch1Time.setTextColor(getResources().getColor(R.color.md_green_500));
                    if (switchBoardFragment.getIsDeviceConnected()) {
                        livingRoom_Load = 15;
                        livingRoom_Switch_ON = 1;
                        livingRoom_Switch_OFF = 7;
                        arduinoController.sendData(ArduinoConstants.SWITCH_1_ON);
                    } else {
                        livingRoom_Load = 30;
                        livingRoom_Switch_ON = 3;
                        livingRoom_Switch_OFF = 5;
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    TimeScheduler.livingRoomSwitch1StopTimer();
                    switch1Time.setTextColor(getResources().getColor(R.color.md_red_500));
                    if (switchBoardFragment.getIsDeviceConnected()) {
                        livingRoom_Load = 0;
                        livingRoom_Switch_ON = 0;
                        livingRoom_Switch_OFF = 8;
                        arduinoController.sendData(ArduinoConstants.SWITCH_1_OFF);
                    } else {
                        livingRoom_Load = 30;
                        livingRoom_Switch_ON = 3;
                        livingRoom_Switch_OFF = 5;
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        switch2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch2Button.isChecked()) {
                    TimeScheduler.livingRoomSwitch2StartTimer();
                    switch2Time.setTextColor(getResources().getColor(R.color.md_green_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_2_ON);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                } else {
                    TimeScheduler.livingRoomSwitch2StopTimer();
                    switch2Time.setTextColor(getResources().getColor(R.color.md_red_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_2_OFF);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch3Button.isChecked()) {
                    TimeScheduler.livingRoomSwitch3StartTimer();
                    switch3Time.setTextColor(getResources().getColor(R.color.md_green_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_3_ON);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                } else {
                    TimeScheduler.livingRoomSwitch3StopTimer();
                    switch3Time.setTextColor(getResources().getColor(R.color.md_red_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_3_OFF);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch4Button.isChecked()) {
                    TimeScheduler.livingRoomSwitch4StartTimer();
                    switch4Time.setTextColor(getResources().getColor(R.color.md_green_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_4_ON);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                } else {
                    TimeScheduler.livingRoomSwitch4StopTimer();
                    switch4Time.setTextColor(getResources().getColor(R.color.md_red_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_4_OFF);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch5Button.isChecked()) {
                    TimeScheduler.livingRoomSwitch5StartTimer();
                    switch5Time.setTextColor(getResources().getColor(R.color.md_green_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_5_ON);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                } else {
                    TimeScheduler.livingRoomSwitch5StopTimer();
                    switch5Time.setTextColor(getResources().getColor(R.color.md_red_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_5_OFF);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch6Button.isChecked()) {
                    TimeScheduler.livingRoomSwitch6StartTimer();
                    switch6Time.setTextColor(getResources().getColor(R.color.md_green_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_6_ON);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                } else {
                    TimeScheduler.livingRoomSwitch6StopTimer();
                    switch6Time.setTextColor(getResources().getColor(R.color.md_red_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_6_OFF);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch7Button.isChecked()) {
                    TimeScheduler.livingRoomSwitch7StartTimer();
                    switch7Time.setTextColor(getResources().getColor(R.color.md_green_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_7_ON);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                } else {
                    TimeScheduler.livingRoomSwitch7StopTimer();
                    switch7Time.setTextColor(getResources().getColor(R.color.md_red_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_7_OFF);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if (switch8Button.isChecked()) {
                    TimeScheduler.livingRoomSwitch8StartTimer();
                    switch8Time.setTextColor(getResources().getColor(R.color.md_green_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_8_ON);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                } else {
                    TimeScheduler.livingRoomSwitch8StopTimer();
                    switch8Time.setTextColor(getResources().getColor(R.color.md_red_500));
                    if (switchBoardFragment.getIsDeviceConnected())
                        arduinoController.sendData(ArduinoConstants.SWITCH_8_OFF);
                    else
                        Toast.makeText(getActivity(), "Please Connect the device first..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        new SwitchStatusThread().execute();
        return view;
    }

    public Handler livingRoomTimeHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RECIEVE_TIMER_ACTION:
                    String[] switchTimer = (String[]) msg.obj;
                    if (switchTimer[0].equals(Constants.SWITCH_1)) {
                        switch1Time.setText(switchTimer[1]);
                        livingRoom_unit = getSwitch1Unit(Integer.valueOf(switchTimer[2]));
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
                    } else if (switchTimer[0].equals(Constants.SWITCH_6)) {
                        switch6Time.setText(switchTimer[1]);
                    } else if (switchTimer[0].equals(Constants.SWITCH_7)) {
                        switch7Time.setText(switchTimer[1]);
                    } else if (switchTimer[0].equals(Constants.SWITCH_8)) {
                        switch8Time.setText(switchTimer[1]);
                    }
            }
        }
    };


    public Handler statusHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RECIEVE_MESSAGE:
                    char data = (char) msg.obj;
                    Log.d(TAG, "<----Received Data from Arduino ---->" + data);
                    try {
                        if (data == ArduinoConstants.SWITCH_1_ON) {
                            TimeScheduler.livingRoomSwitch1StartTimer();
                            switch1Time.setTextColor(Color.parseColor("#4caf50"));
                            livingRoom_Load = 15;
                            switch1Button.setChecked(true);
                        } else if (data == ArduinoConstants.SWITCH_1_OFF) {
                            TimeScheduler.livingRoomSwitch1StopTimer();
                            switch1Time.setTextColor(Color.parseColor("#f44336"));
                            livingRoom_Load = 0;
                            switch1Button.setChecked(false);
                        } else if (data == ArduinoConstants.SWITCH_2_ON) {
                            switch2Time.setTextColor(Color.parseColor("#4caf50"));
                            switch2Button.setChecked(true);
                        } else if (data == ArduinoConstants.SWITCH_2_OFF) {
                            switch2Time.setTextColor(Color.parseColor("#f44336"));
                            switch2Button.setChecked(false);
                        } else if (data == ArduinoConstants.SWITCH_3_ON) {
                            switch3Time.setTextColor(Color.parseColor("#4caf50"));
                            switch3Button.setChecked(true);
                        } else if (data == ArduinoConstants.SWITCH_3_OFF) {
                            switch3Time.setTextColor(Color.parseColor("#f44336"));
                            switch3Button.setChecked(false);
                        } else if (data == ArduinoConstants.SWITCH_4_ON) {
                            switch4Time.setTextColor(Color.parseColor("#4caf50"));
                            switch4Button.setChecked(true);
                        } else if (data == ArduinoConstants.SWITCH_4_OFF) {
                            switch4Time.setTextColor(Color.parseColor("#f44336"));
                            switch4Button.setChecked(false);
                        } else if (data == ArduinoConstants.SWITCH_5_ON) {
                            switch5Time.setTextColor(Color.parseColor("#4caf50"));
                            switch5Button.setChecked(true);
                        } else if (data == ArduinoConstants.SWITCH_5_OFF) {
                            switch5Time.setTextColor(Color.parseColor("#f44336"));
                            switch5Button.setChecked(false);
                        } else if (data == ArduinoConstants.SWITCH_6_ON) {
                            switch6Time.setTextColor(Color.parseColor("#4caf50"));
                            switch6Button.setChecked(true);
                        } else if (data == ArduinoConstants.SWITCH_6_OFF) {
                            switch6Time.setTextColor(Color.parseColor("#f44336"));
                            switch6Button.setChecked(false);
                        } else if (data == ArduinoConstants.SWITCH_7_ON) {
                            switch7Time.setTextColor(Color.parseColor("#4caf50"));
                            switch7Button.setChecked(true);
                        } else if (data == ArduinoConstants.SWITCH_7_OFF) {
                            switch7Time.setTextColor(Color.parseColor("#f44336"));
                            switch7Button.setChecked(false);
                        } else if (data == ArduinoConstants.SWITCH_8_ON) {
                            switch8Time.setTextColor(Color.parseColor("#4caf50"));
                            switch8Button.setChecked(true);
                        } else if (data == ArduinoConstants.SWITCH_8_OFF) {
                            switch8Time.setTextColor(Color.parseColor("#f44336"));
                            switch8Button.setChecked(false);
                        }
                        break;
                    } catch (Exception e) {
                       e.printStackTrace();
                    }
            }
        }
    };

    public void setTotalSwitchStatus() {
        Log.d(TAG, "<---livingRoom_Switch_ON: " + livingRoom_Switch_ON + "  livingRoom_Switch_OFF: " + livingRoom_Switch_OFF + "--->");
    }


    private class SwitchStatusThread extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            Thread.sleep(1000);
                            if (getActivity() == null)
                                return;
                            setTotalSwitchStatus();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    public void setTotalLoadStatus() {
        Log.d(TAG, "<---livingRoom_Load: " + livingRoom_Load + "--->");
    }

    private class LoadStatusThread extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            Thread.sleep(1000);
                            if (getActivity() == null)
                                return;
                            setTotalLoadStatus();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    public Double getSwitch1Unit(final int seconds) {
        DecimalFormat twoDForm = new DecimalFormat("#.###");
//        float value = (((15 * seconds) / 1000f) / 3600f);
        if (switchBoardFragment.getIsDeviceConnected()) {
            float value = (((15 * (3600 + seconds)) / 1000f) / 3600f);
            return Double.valueOf(twoDForm.format(value));
        }
        return 20.4;
    }
}
