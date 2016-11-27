package com.friuno.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by GodwinRoseSamuel on 18-10-2016.
 */
public class BluetoothController {

    private static final String TAG = "BluetoothController";
    private static final UUID SERIAL_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private Set<BluetoothDevice> pairedDevices;
    private BluetoothDevice bluetoothDevice = null;
    private BluetoothSocket bluetoothSocket = null;

    public Boolean isEnabled() {
        return bluetoothAdapter.isEnabled();
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter;
    }

    public BluetoothDevice getBluetoothDevice(String address) {
        return bluetoothAdapter.getRemoteDevice(address);
    }

    public Boolean createBluetoothConnection(String address) {
        boolean isCreated = false;

        Log.d(TAG, "<----Establishing Bluetooth Link---->");

        try {
            bluetoothDevice = getBluetoothDevice(address);
            Log.d(TAG, "<---Creating Socket for device--->" + address);
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(SERIAL_UUID);
            Log.d(TAG, "<---Socket Created--->");
        } catch (IOException e) {
            Log.e(TAG, "<---Error creating socket--->" + e.getMessage());
        }

        bluetoothAdapter.cancelDiscovery();

        try {
            Log.d(TAG, "<---Connecting to Socket Created--->");
            bluetoothSocket.connect();
            isCreated = true;
            Log.d(TAG, "<---Connected to Socket--->" + isCreated);
        } catch (IOException e) {
            Log.e(TAG, "<---Error while connecting to Socket--->");

            try {
                Log.e(TAG, "<---Trying FallBack Connection to socket--->");
                bluetoothSocket = (BluetoothSocket) bluetoothDevice.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(bluetoothDevice, 1);
                bluetoothSocket.connect();
                isCreated = true;
                Log.e(TAG, "<---Connected to Socket--->" + isCreated);
            } catch (InvocationTargetException e1) {
                Log.e(TAG, e1.getMessage());
            } catch (NoSuchMethodException e1) {
                Log.e(TAG, e1.getMessage());
            } catch (IOException e1) {
                Log.e(TAG, e1.getMessage());
            } catch (IllegalAccessException e1) {
                Log.e(TAG, e1.getMessage());
            }
        }
        Log.d(TAG, "<--- Bluetooth Link Established Successfully---->" + isCreated);
        return isCreated;
    }

    public BluetoothSocket getBluetoothSocket() {
        return bluetoothSocket;
    }

    public Boolean closeBluetoothConnection() {
        boolean isClosed = false;
        Log.d(TAG, "<---Closing Bluetooth Connection--->");
        try {
            bluetoothSocket.close();
            isClosed = true;
        } catch (IOException e) {
            Log.e(TAG, "<--Unable to close bluetooth connection--->" + e.getMessage());
        }
        Log.d(TAG, "<---Bluetooth Connection Closed--->" + isClosed);

        return isClosed;
    }

    public Map<String, String> getPairedDevices() {
        pairedDevices = bluetoothAdapter.getBondedDevices();
        Map<String, String> bluetoothdevices = new HashMap<String, String>();

        if (pairedDevices.isEmpty()) {
            bluetoothdevices.clear();
        } else {
            for (BluetoothDevice device : pairedDevices) {
                bluetoothdevices.put(device.getName(), device.getAddress());
            }
        }
        return bluetoothdevices;
    }
}
