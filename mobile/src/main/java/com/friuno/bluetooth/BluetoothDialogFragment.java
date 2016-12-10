package com.friuno.bluetooth;

import android.app.DialogFragment;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.friuno.R;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by GodwinRoseSamuel on 02-12-2016.
 */
public class BluetoothDialogFragment extends DialogFragment implements
        AdapterView.OnItemClickListener {

    private static final String TAG = "BluetoothDialogFragment";
    private BluetoothController bluetoothController = new BluetoothController();

    private TextView pairedDeviceText, availableDeviceText;
    private ListView pairedDeviceList, availableDeviceList;
    private Button scan_device;


    private ArrayAdapter<String> pairedDeviceadapter;
    private ArrayAdapter<String> availableDeviceadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bluetooth_dialog, null, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        pairedDeviceText = (TextView) view.findViewById(R.id.title_paired_devices);
        availableDeviceText = (TextView) view.findViewById(R.id.title_new_devices);
        pairedDeviceList = (ListView) view.findViewById(R.id.paired_devices);
        availableDeviceList = (ListView) view.findViewById(R.id.new_devices);


        scan_device = (Button) view.findViewById(R.id.scan_device);
        scan_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getavailableDevices();
            }
        });
//        setCancelable(false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        pairedDeviceadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        pairedDeviceList.setAdapter(pairedDeviceadapter);

        getPairedDevices();
        pairedDeviceList.setOnItemClickListener(this);

        availableDeviceadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        availableDeviceadapter.add("No devices found");
        availableDeviceList.setAdapter(availableDeviceadapter);
        availableDeviceList.setOnItemClickListener(this);


    }

    final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                availableDeviceadapter.add(device.getName() + "\n" + device.getAddress());
                availableDeviceadapter.notifyDataSetChanged();
            }
            if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                final int state = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);
                final int prevState = intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, BluetoothDevice.ERROR);
                if (state == BluetoothDevice.BOND_BONDED && prevState == BluetoothDevice.BOND_BONDING) {
                    Toast.makeText(getActivity(), "PAIRED", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public void setPairedDevice(String address) {
        try {
            Method method = bluetoothController.getBluetoothDevice(address).getClass().getMethod("createBond", (Class[]) null);
            method.invoke(bluetoothController.getBluetoothDevice(address), (Object[]) null);
            getActivity().registerReceiver(bluetoothReceiver, new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPairedDevices() {

        if (bluetoothController.getPairedDevices().isEmpty()) {
            pairedDeviceadapter.add("No devices have been paired");
        } else {
            pairedDeviceadapter.clear();
            for (Map.Entry<String, String> bluetooth : bluetoothController.getPairedDevices().entrySet()) {
                pairedDeviceadapter.add(bluetooth.getKey() + "\n" + bluetooth.getValue());
            }
        }
    }

    public void getavailableDevices() {

        if (bluetoothController.getBluetoothAdapter().isDiscovering()) {
            Log.d(TAG, "<----Finished Searching Bluetooth Devices---->");
            bluetoothController.getBluetoothAdapter().cancelDiscovery();
        } else {
            Log.d(TAG, "<----Searching for bluetooth Devices---->");
            availableDeviceadapter.clear();
            bluetoothController.getBluetoothAdapter().startDiscovery();
//            availableDeviceText.setVisibility(View.VISIBLE);
            try {
                Log.d(TAG, "<----Bluetooth Receiever Registered---->");
                getActivity().registerReceiver(bluetoothReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "<----Error occured during bluetooth brodcast register---->" + e.getMessage());
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String[] bluetoothDevice = adapterView.getItemAtPosition(i).toString().split("\\n");
        if (bluetoothDevice.length > 1) {
            String address = bluetoothDevice[1];
            setPairedDevice(address);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "<----Bluetooth Receiever Destroyed---->");
        try {
            getActivity().unregisterReceiver(bluetoothReceiver);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "<----Error occured during bluetooth brodcast unregister---->" + e.getMessage());
        }
    }
}
