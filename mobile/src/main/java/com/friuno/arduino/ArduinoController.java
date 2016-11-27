package com.friuno.arduino;

import android.util.Log;

import com.friuno.bluetooth.BluetoothController;
import com.friuno.fragment.switchboard.LivingRoomFragment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;


/**
 * Created by GodwinRoseSamuel on 18-10-2016.
 */
public class ArduinoController {

    private static final String TAG = "ArduinoController";
    private static final String FRIUNO_BLUETOOTH = "HC";
    private BluetoothController bluetoothController = new BluetoothController();
    private OutputStream outputStream = null;
    private ConnectedThread mConnectedThread;
    private boolean isArduinoConnectedviaBluetooth = false;
    private LivingRoomFragment livingRoomFragment = new LivingRoomFragment();

    public Boolean isBluetoothEnabled() {
        return bluetoothController.isEnabled();
    }

    public Boolean isArduinoConnectedviaBluetoothStatus() {
        return isArduinoConnectedviaBluetooth;
    }

    public String getBluetoothAddress() {
        if (!bluetoothController.getPairedDevices().isEmpty()) {
            for (Map.Entry<String, String> bluetooth : bluetoothController.getPairedDevices().entrySet()) {
                if (bluetooth.getKey().startsWith(FRIUNO_BLUETOOTH)) {
                    return bluetooth.getValue();
                }
            }
        }
        return null;
    }

    public void connectArduinoViaBluetooth(String address) {

        //creating bluetooth connection
        if (bluetoothController.createBluetoothConnection(address)) {
            try {
                Log.d(TAG, "<-----Creating a Data Stream to talk to  Arduino------>");
                outputStream = bluetoothController.getBluetoothSocket().getOutputStream();
            } catch (IOException e2) {
                Log.e(TAG, "<---Error While Creating Data Stream--->" + e2.getMessage());
            }
            isArduinoConnectedviaBluetooth = true;
        } else {
            isArduinoConnectedviaBluetooth = false;
        }
    }

    public void disconnectArduinoViaBluetooth() {
        if (outputStream != null) {
            try {
                outputStream.flush();
            } catch (IOException e) {
                Log.e(TAG, "<---Failed to flush output stream--->" + e.getMessage());
            }
        }

        //closing bluetooth connection
        if (bluetoothController.closeBluetoothConnection()) {
            isArduinoConnectedviaBluetooth = false;
        }
    }

    public void sendData(char message) {
        try {
            if (message != 0) {
                Log.d(TAG, "<---Sending data to Arduino--->" + message);
                outputStream.write(message);
            } else {
                Log.e(TAG, "<---Sending message is null--->");
            }
        } catch (IOException e) {
            Log.e(TAG, "<---Error While Sending Data to Arduino--->" + e.getMessage());
        }
    }

    public void readData() {
        Log.d(TAG, "<---readData--->");
        mConnectedThread = new ConnectedThread();
        mConnectedThread.start();
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;

        public ConnectedThread() {
            InputStream tmpIn = null;

            try {
                tmpIn = bluetoothController.getBluetoothSocket().getInputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
        }

        public void run() {
            try {
                int i;
                char c = 0;
                while ((i = mmInStream.read()) != -1) {
                    Log.d(TAG, "<---Ascii Value--->" + i);
                    c = (char) i;
                    if (i != 13 && i != 10) {
                        Log.d(TAG, "<---Reading Data from Arduino--->" + c);
                        livingRoomFragment.statusHandler.obtainMessage(LivingRoomFragment.RECIEVE_MESSAGE, c).sendToTarget();
//                        Message message = HomeFragment.statusHandler.obtainMessage();
//                        Bundle bundle = new Bundle();
//                        bundle.putString("data", String.valueOf(c));
//                        message.setData(bundle);
//                        HomeFragment.receiveDataTextHandler.sendMessage(message);
                    }
                }
            } catch (IOException e) {
                Log.e(TAG, "<---Error while receving data from arduino--->" + e.getMessage());
            }
        }
    }
}
