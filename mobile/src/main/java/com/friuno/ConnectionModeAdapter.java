package com.friuno;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by GodwinRoseSamuel on 21-11-2016.
 */
public class ConnectionModeAdapter extends ArrayAdapter<String> {

    public ConnectionModeAdapter(Context context, int simple_spinner_item, String[] stringArray) {
        super(context, simple_spinner_item, stringArray);

    }

    @Override
    public int getCount() {
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}