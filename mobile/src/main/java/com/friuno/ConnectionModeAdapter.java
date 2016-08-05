package com.friuno;

import android.content.Context;
import android.widget.ArrayAdapter;

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