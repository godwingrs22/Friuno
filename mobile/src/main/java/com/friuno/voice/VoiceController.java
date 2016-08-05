package com.friuno.voice;

import android.util.Log;

import com.friuno.fragment.switchboard.BedRoomFragment;
import com.friuno.fragment.switchboard.KitchenFragment;
import com.friuno.fragment.switchboard.LivingRoomFragment;
import com.friuno.fragment.switchboard.MainHallFragment;
import com.friuno.fragment.switchboard.OthersFragment;
import com.friuno.util.Constants;

/**
 * Created by GodwinRoseSamuel on 01-08-2016.
 */
public class VoiceController {
    private static final String TAG = "VoiceController";
    private final LivingRoomFragment livingRoomFragment = new LivingRoomFragment();
    private final MainHallFragment mainHallFragment = new MainHallFragment();
    private final BedRoomFragment bedRoomFragment = new BedRoomFragment();
    private final KitchenFragment kitchenFragment = new KitchenFragment();
    private final OthersFragment othersFragment = new OthersFragment();

    String switch_status = null;

    public void controlSwitch(final String currentSwitch, final String currentSwitchStatus, final boolean isSpeechRecgonized, final String room) {
        Log.d(TAG, "<-----isSpeechRecgonized---->" + isSpeechRecgonized);
        Log.d(TAG, "<-----ROOM---->" + room);

        if (currentSwitch != null && currentSwitchStatus != null) {
            switch (currentSwitch) {
                case Constants.SWITCH_1: {
                    if (currentSwitchStatus.equals(Constants.ON))
                        switch_status = Constants.SWITCH_1_ON;
                    else if (currentSwitchStatus.equals(Constants.OFF))
                        switch_status = Constants.SWITCH_1_OFF;
                    break;
                }
                case Constants.SWITCH_2: {
                    if (currentSwitchStatus.equals(Constants.ON))
                        switch_status = Constants.SWITCH_2_ON;
                    else if (currentSwitchStatus.equals(Constants.OFF))
                        switch_status = Constants.SWITCH_2_OFF;
                    break;
                }
                case Constants.SWITCH_3: {
                    if (currentSwitchStatus.equals(Constants.ON))
                        switch_status = Constants.SWITCH_3_ON;
                    else if (currentSwitchStatus.equals(Constants.OFF))
                        switch_status = Constants.SWITCH_3_OFF;
                    break;
                }
                case Constants.SWITCH_4: {
                    if (currentSwitchStatus.equals(Constants.ON))
                        switch_status = Constants.SWITCH_4_ON;
                    else if (currentSwitchStatus.equals(Constants.OFF))
                        switch_status = Constants.SWITCH_4_OFF;
                    break;
                }
                case Constants.SWITCH_5: {
                    if (currentSwitchStatus.equals(Constants.ON))
                        switch_status = Constants.SWITCH_5_ON;
                    else if (currentSwitchStatus.equals(Constants.OFF))
                        switch_status = Constants.SWITCH_5_OFF;
                    break;
                }
                case Constants.SWITCH_6: {
                    if (currentSwitchStatus.equals(Constants.ON))
                        switch_status = Constants.SWITCH_6_ON;
                    else if (currentSwitchStatus.equals(Constants.OFF))
                        switch_status = Constants.SWITCH_6_OFF;
                    break;
                }
                case Constants.SWITCH_7: {
                    if (currentSwitchStatus.equals(Constants.ON))
                        switch_status = Constants.SWITCH_7_ON;
                    else if (currentSwitchStatus.equals(Constants.OFF))
                        switch_status = Constants.SWITCH_7_OFF;
                    break;
                }
                case Constants.SWITCH_8: {
                    if (currentSwitchStatus.equals(Constants.ON))
                        switch_status = Constants.SWITCH_8_ON;
                    else if (currentSwitchStatus.equals(Constants.OFF))
                        switch_status = Constants.SWITCH_8_OFF;
                    break;
                }
            }
        }
        switch (room) {
            case Constants.LIVING_ROOM:
                livingRoomFragment.speechHandler.obtainMessage(LivingRoomFragment.RECIEVE_SPEECH_MESSAGE, new String[]{String.valueOf(isSpeechRecgonized), switch_status}).sendToTarget();
                break;
            case Constants.MAIN_HALL:
                mainHallFragment.speechHandler.obtainMessage(MainHallFragment.RECIEVE_SPEECH_MESSAGE, new String[]{String.valueOf(isSpeechRecgonized), switch_status}).sendToTarget();
                break;
            case Constants.BED_ROOM:
                bedRoomFragment.speechHandler.obtainMessage(BedRoomFragment.RECIEVE_SPEECH_MESSAGE, new String[]{String.valueOf(isSpeechRecgonized), switch_status}).sendToTarget();
                break;
            case Constants.KITCHEN:
                kitchenFragment.speechHandler.obtainMessage(KitchenFragment.RECIEVE_SPEECH_MESSAGE, new String[]{String.valueOf(isSpeechRecgonized), switch_status}).sendToTarget();
                break;
            case Constants.OTHER_ROOMS:
                othersFragment.speechHandler.obtainMessage(OthersFragment.RECIEVE_SPEECH_MESSAGE, new String[]{String.valueOf(isSpeechRecgonized), switch_status}).sendToTarget();
                break;
        }
    }
}
