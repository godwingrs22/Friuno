package com.friuno.voice;

import android.util.Log;

import com.friuno.util.Constants;

import java.util.Map;

/**
 * Created by GodwinRoseSamuel on 22-07-2016.
 */
public class VoiceRecognizer {

    private static final String TAG = "VoiceRecognizer";
    private String switchText = null;
    private String switchStatus = null;
    private String currentSwitch = null;
    private String currentSwitchStatus = null;
    private SpeechDictionary dictionary;
    private VoiceController voiceController = new VoiceController();
    private boolean isSpeechRecgonized = false;

    public void speechToText(final String command,final String room) {
        Log.d(TAG, "<---Received Speech To Text Command---->" + command);

        final int wordCount = getWordCount(command);
        Log.d(TAG, "<---No of Words in speech---->" + wordCount);

        if (wordCount == 2) {
            switchText = command.split(" ")[0];
            switchStatus = command.split(" ")[1];
            Log.d(TAG, "<---Received switchText :" + switchText + " /switchStatus :" + switchStatus + " ---->");
            processCommand(room);
        } else if (wordCount == 3) {
            switchText = command.split(" ")[0] + " " + command.split(" ")[1];
            switchStatus = command.split(" ")[2];
            Log.d(TAG, "<---Received switchText :" + switchText + " /switchStatus :" + switchStatus + " ---->");
            processCommand(room);
        } else {
            Log.e(TAG, "<---Unable to process speech due to word length---->" + wordCount);
            isSpeechRecgonized = false;
            switchText = null;
            switchStatus = null;
            processCommand(room);
        }
    }

    public void processCommand(final String room) {
        currentSwitch = findSwitch(switchText,room);
        Log.d(TAG, "<-----Current Switch---->" + currentSwitch);
        currentSwitchStatus = findSwitchStatus(switchStatus);
        Log.d(TAG, "<-----Current SwitchStatus---->" + currentSwitchStatus);

        if (currentSwitch != null && currentSwitchStatus != null) {
            isSpeechRecgonized = true;
        } else {
            isSpeechRecgonized = false;
        }
        voiceController.controlSwitch(currentSwitch, currentSwitchStatus, isSpeechRecgonized,room);
    }

    public String findSwitch(final String switchText,final String room) {
        if (switchText != null) {
            dictionary = new SpeechDictionary();
            for (Map.Entry<String, String[]> switchMap : dictionary.getSwitch(room).entrySet()) {
                for (int i = 0; i < switchMap.getValue().length; i++) {
                    if (switchMap.getValue()[i].equalsIgnoreCase(switchText)) {
                        return switchMap.getKey();
                    }
                }
            }
        }
        return null;
    }

    public String findSwitchStatus(final String switchStatus) {
        if (switchStatus != null) {
            if (switchStatus.equalsIgnoreCase("ON")) {
                return Constants.ON;
            } else if (switchStatus.equalsIgnoreCase("OF") || switchStatus.equalsIgnoreCase("OFF")) {
                return Constants.OFF;
            }
        }
        return null;
    }

    public int getWordCount(final String command) {
        return command.split("\\s+").length;
    }
}
