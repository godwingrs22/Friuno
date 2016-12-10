package com.friuno.voice;


import com.friuno.util.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GodwinRoseSamuel on 21-11-2016.
 */
public class SpeechDictionary {

    private String[] livingRoomSwitch_1 = {"socket", "switch one", "switch 1", "which one", "which 1", "suck it", "circuit", "saket"};
    private String[] livingRoomSwitch_2 = {"fan", "send", "switch to", "switch 2", "which to", "which 2", "and"};
    private String[] livingRoomSwitch_3 = {"light", "lite", "switch three", "switch 3", "which three", "which 3"};
    private String[] livingRoomSwitch_4 = {"lamp", "amp", "switch four", "switch 4", "switch for", "which four", "which 4"};
    private String[] livingRoomSwitch_5 = {"charger", "switch five", "switch 5", "which five", "which 5","switch wifi"};
    private String[] livingRoomSwitch_6 = {"home theatre", "switch six", "switch 6", "which six", "which 6"};
    private String[] livingRoomSwitch_7 = {"ac", "switch seven", "switch 7", "which seven", "which 7"};
    private String[] livingRoomSwitch_8 = {"fan 2", "fan two", "send two", "send 2", "switch eight", "switch 8", "which eight", "which 8"};

    private String[] mainHallSwitch_1 = {"socket", "switch one", "switch 1", "which one", "which 1", "suck it", "circuit", "saket"};
    private String[] mainHallSwitch_2 = {"tv", "switch to", "switch 2", "which to", "which 2", "and"};
    private String[] mainHallSwitch_3 = {"fan", "send", "switch three", "switch 3", "which three", "which 3"};
    private String[] mainHallSwitch_4 = {"light", "lite", "switch four", "switch 4", "switch for", "which four", "which 4"};
    private String[] mainHallSwitch_5 = {"home theatre", "switch five", "switch 5", "which five", "which 5","switch wifi"};
    private String[] mainHallSwitch_6 = {"playstation", "play station", "switch six", "switch 6", "which six", "which 6"};

    private String[] bedRoomSwitch_1 = {"socket", "switch one", "switch 1", "which one", "which 1", "suck it", "circuit", "saket"};
    private String[] bedRoomSwitch_2 = {"fan", "send", "switch to", "switch 2", "which to", "which 2", "and"};
    private String[] bedRoomSwitch_3 = {"light", "lite", "switch three", "switch 3", "which three", "which 3"};
    private String[] bedRoomSwitch_4 = {"ac", "switch four", "switch 4", "switch for", "which four", "which 4"};
    private String[] bedRoomSwitch_5 = {"lamp", "amp", "switch five", "switch 5", "which five", "which 5","switch wifi"};

    private String[] kitchenSwitch_1 = {"socket", "switch one", "switch 1", "which one", "which 1", "suck it", "circuit", "saket"};
    private String[] kitchenSwitch_2 = {"fan", "send", "switch to", "switch 2", "which to", "which 2", "and"};
    private String[] kitchenSwitch_3 = {"light", "lite", "switch three", "switch 3", "which three", "which 3"};
    private String[] kitchenSwitch_4 = {"oven","women", "van", "switch four", "switch 4", "switch for", "which four", "which 4"};
    private String[] kitchenSwitch_5 = {"electric cooker","cooker", "switch five", "switch 5", "which five", "which 5","switch wifi"};
    private String[] kitchenSwitch_6 = {"refrigerator", "switch six", "switch 6", "which six", "which 6"};
    private String[] kitchenSwitch_7 = {"coffee maker", "switch seven", "switch 7", "which seven", "which 7"};

    private String[] othersSwitch_1 = {"socket", "switch one", "switch 1", "which one", "which 1", "suck it", "circuit", "saket"};
    private String[] othersSwitch_2 = {"motor", "send", "switch to", "switch 2", "which to", "which 2", "and"};
    private String[] othersSwitch_3 = {"fan", "send", "switch three", "switch 3", "which three", "which 3"};
    private String[] othersSwitch_4 = {"light", "lite", "switch four", "switch 4", "switch for", "which four", "which 4"};
    private String[] othersSwitch_5 = {"ac", "switch five", "switch 5", "which five", "which 5","switch wifi"};
    private String[] othersSwitch_6 = {"computer", "switch six", "switch 6", "which six", "which 6"};

    public Map<String, String[]> livingRoomSwitchMap = new HashMap<String, String[]>();
    public Map<String, String[]> mainHallSwitchMap = new HashMap<String, String[]>();
    public Map<String, String[]> bedRoomSwitchMap = new HashMap<String, String[]>();
    public Map<String, String[]> kitchenSwitchMap = new HashMap<String, String[]>();
    public Map<String, String[]> otherSwitchMap = new HashMap<String, String[]>();


    public void setLivingRoomSwitch() {
        livingRoomSwitchMap.put(Constants.SWITCH_1, livingRoomSwitch_1);
        livingRoomSwitchMap.put(Constants.SWITCH_2, livingRoomSwitch_2);
        livingRoomSwitchMap.put(Constants.SWITCH_3, livingRoomSwitch_3);
        livingRoomSwitchMap.put(Constants.SWITCH_4, livingRoomSwitch_4);
        livingRoomSwitchMap.put(Constants.SWITCH_5, livingRoomSwitch_5);
        livingRoomSwitchMap.put(Constants.SWITCH_6, livingRoomSwitch_6);
        livingRoomSwitchMap.put(Constants.SWITCH_7, livingRoomSwitch_7);
        livingRoomSwitchMap.put(Constants.SWITCH_8, livingRoomSwitch_8);
    }

    public void setMainHallSwitch() {
        mainHallSwitchMap.put(Constants.SWITCH_1, mainHallSwitch_1);
        mainHallSwitchMap.put(Constants.SWITCH_2, mainHallSwitch_2);
        mainHallSwitchMap.put(Constants.SWITCH_3, mainHallSwitch_3);
        mainHallSwitchMap.put(Constants.SWITCH_4, mainHallSwitch_4);
        mainHallSwitchMap.put(Constants.SWITCH_5, mainHallSwitch_5);
        mainHallSwitchMap.put(Constants.SWITCH_6, mainHallSwitch_6);
    }

    public void setBedRoomSwitch() {
        bedRoomSwitchMap.put(Constants.SWITCH_1, bedRoomSwitch_1);
        bedRoomSwitchMap.put(Constants.SWITCH_2, bedRoomSwitch_2);
        bedRoomSwitchMap.put(Constants.SWITCH_3, bedRoomSwitch_3);
        bedRoomSwitchMap.put(Constants.SWITCH_4, bedRoomSwitch_4);
        bedRoomSwitchMap.put(Constants.SWITCH_5, bedRoomSwitch_5);
    }

    public void setKitchenSwitch() {
        kitchenSwitchMap.put(Constants.SWITCH_1, kitchenSwitch_1);
        kitchenSwitchMap.put(Constants.SWITCH_2, kitchenSwitch_2);
        kitchenSwitchMap.put(Constants.SWITCH_3, kitchenSwitch_3);
        kitchenSwitchMap.put(Constants.SWITCH_4, kitchenSwitch_4);
        kitchenSwitchMap.put(Constants.SWITCH_5, kitchenSwitch_5);
        kitchenSwitchMap.put(Constants.SWITCH_6, kitchenSwitch_6);
        kitchenSwitchMap.put(Constants.SWITCH_7, kitchenSwitch_7);
    }

    public void setOthersSwitch() {
        otherSwitchMap.put(Constants.SWITCH_1, othersSwitch_1);
        otherSwitchMap.put(Constants.SWITCH_2, othersSwitch_2);
        otherSwitchMap.put(Constants.SWITCH_3, othersSwitch_3);
        otherSwitchMap.put(Constants.SWITCH_4, othersSwitch_4);
        otherSwitchMap.put(Constants.SWITCH_5, othersSwitch_5);
        otherSwitchMap.put(Constants.SWITCH_6, othersSwitch_6);
    }

    public SpeechDictionary() {
        setLivingRoomSwitch();
        setMainHallSwitch();
        setBedRoomSwitch();
        setKitchenSwitch();
        setOthersSwitch();
    }

    public Map<String, String[]> getSwitch(final String room) {
        switch (room) {
            case Constants.LIVING_ROOM:
                return livingRoomSwitchMap;
            case Constants.MAIN_HALL:
                return mainHallSwitchMap;
            case Constants.BED_ROOM:
                return bedRoomSwitchMap;
            case Constants.KITCHEN:
                return kitchenSwitchMap;
            case Constants.OTHER_ROOMS:
                return otherSwitchMap;
        }
        return null;
    }
}
