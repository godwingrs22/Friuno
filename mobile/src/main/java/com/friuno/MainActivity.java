package com.friuno;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.friuno.arduino.ArduinoConstants;
import com.friuno.arduino.ArduinoController;
import com.friuno.fragment.DashBoardFragment;
import com.friuno.fragment.LocationFragment;
import com.friuno.fragment.StatisticsFragment;
import com.friuno.fragment.SwitchBoardFragment;
import com.friuno.fragment.TimerFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

/**
 * Created by GodwinRoseSamuel on 23-07-2016.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int PROFILE_PIC_SIZE = 400;
    private static final int NAVIGATION_VIEW_ACTION = 203;
    private static final int BLUETOOTH_TURN_ON_REQUEST_CODE = 101;
    private static final int BLUETOOTH_PAIR_REQUEST_CODE = 102;

    private boolean mIntentInProgress;

    private Boolean doubleBackToExitPressedOnce = false;
    private Boolean isSavedInstance = false;

    private static final String PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=";
    private static final String FACEBOOK_URL = "https://www.facebook.com/friuno";
    private static final String FACEBOOK_PAGE_ID = "fb://page/1456349084675364";
    private static final String TWITTER_URL = "https://twitter.com/friuno";
    private static final String GOOGLE_PLUS_URL = "http://www.google.com/+Grsappstore";
    private static final String APP_STORE_URL = "http://www.friuno.com";
    private static final String CONTACT_US_URL = "http://www.friuno.com/#contact";
    private static final String BLUETOOTH = "BLUETOOTH";
    private static final String INTERNET = "INTERNET";

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private NavigationView navigationView;
    private Spinner connectionMode;
    private ImageView connectionStatusimage;
    private ConnectionModeAdapter1 connectionModeAdapter;
    private Menu menu;
    private Handler deviceHandler;

    private static ArduinoController arduinoController = null;
    private static Boolean isDeviceConnected = false;
    int i = 0;

    DashBoardFragment dashBoardFragment = new DashBoardFragment();
    Fragment switchBoardFragment = new SwitchBoardFragment();
    LocationFragment locationFragment = new LocationFragment();
    StatisticsFragment statisticsFragment = new StatisticsFragment();
    TimerFragment timerFragment = new TimerFragment();

    public String[] modes = {"BlueTooth", "Internet", "Mode"};

    public int modes_images[] = {R.mipmap.bluetooth, R.mipmap.internet, R.mipmap.blank};

    public static Boolean getIsDeviceConnected() {
        return isDeviceConnected;
    }

    public static ArduinoController getArduinoController() {
        return arduinoController;
    }

    public class ConnectionModeAdapter1 extends ArrayAdapter<String> {

        public ConnectionModeAdapter1(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.spinner_dropdown_item, parent, false);
            ImageView icon = (ImageView) row.findViewById(R.id.connect);
            icon.setImageResource(modes_images[position]);
            return row;
        }

        @Override
        public int getCount() {
            int count = super.getCount();
            return count > 0 ? count - 1 : count;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TypedValue typedValueColorPrimaryDark = new TypedValue();
        MainActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValueColorPrimaryDark, true);
        final int colorPrimaryDark = typedValueColorPrimaryDark.data;
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(colorPrimaryDark);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>FRIUNO</font>"));

        arduinoController = new ArduinoController();

        connectionMode = (Spinner) findViewById(R.id.connectionMode);
        connectionStatusimage = (ImageView) findViewById(R.id.connectionStatusimage);

        connectionModeAdapter = new ConnectionModeAdapter1(getApplicationContext(), R.layout.spinner_dropdown_item, modes);
        connectionMode.setAdapter(connectionModeAdapter);
        connectionMode.setSelection(connectionModeAdapter.getCount());

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.userName)).setText(getIntent().getStringExtra("name"));
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.userEmail)).setText(getIntent().getStringExtra("email"));

        Picasso.with(getApplicationContext())
                .load(Uri.parse(getIntent().getStringExtra("image")))
                .placeholder(R.drawable.profile_placeholder)
                .error(R.drawable.profile_placeholder)
                .resize(256, 256)
                .into((ImageView) navigationView.getHeaderView(0).findViewById(R.id.userPhoto));

        setupNavigationDrawerContent(navigationView);

        navigationView.getMenu().performIdentifierAction(R.id.item_navigation_drawer_dashboard, 0);

        if (savedInstanceState == null) {
            isSavedInstance = true;
        }

        connectionStatusimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlDevice();
            }
        });
    }

    private void controlDevice() {
        deviceHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                deviceHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (connectionMode.getSelectedItem().toString().equalsIgnoreCase("Mode")) {
                            Toast.makeText(getApplicationContext(), "Please Select the Connection mode First", Toast.LENGTH_SHORT).show();
                        }
                        if (!isDeviceConnected) {
                            switch (connectionMode.getSelectedItem().toString().toUpperCase()) {
                                case BLUETOOTH:
                                    Log.d(TAG, "<--- Connection Mode Selected--->" + BLUETOOTH);
                                    bluetoothMode();
                                    break;
                                case INTERNET:
                                    Log.d(TAG, "<--- Connection Mode Selected--->" + INTERNET);
                                    connectionStatusimage.setImageResource(R.mipmap.disconnected);
                                    break;
                            }
//                            connectionStatusimage.setImageResource(R.mipmap.connected);
                        } else {
//                            connectionStatusimage.setImageResource(R.mipmap.disconnected);
                            if (arduinoController.isArduinoConnectedviaBluetoothStatus()) {
                                Log.d(TAG, "<---disconnecting arduino--->");
                                arduinoController.disconnectArduinoViaBluetooth();
                                isDeviceConnected = false;
                                connectionStatusimage.setImageResource(R.mipmap.disconnected);
                                destroyBluetoothReceiver();
                                Toast.makeText(getApplicationContext(), "Device Disconnected", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case BLUETOOTH_TURN_ON_REQUEST_CODE: {
                if (arduinoController.isBluetoothEnabled()) {
                    Toast.makeText(getApplicationContext(), "Bluetooth Turned on", Toast.LENGTH_SHORT).show();
                    final String address = arduinoController.getBluetoothAddress();
                    Log.d(TAG, "<----onActivityResult Bluetooth Address---->" + address);
                    if (address == null) {
                        startActivityForResult(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS), BLUETOOTH_PAIR_REQUEST_CODE);
                    } else {
                        bluetoothMode();
                    }
                }
                break;
            }

            case BLUETOOTH_PAIR_REQUEST_CODE: {
                bluetoothMode();
                break;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    public void detectFace() {
        Intent intent = new Intent(this, FaceDetectorActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "<----In onResume---->");

        Log.d(TAG, "<----isDeviceConnected---->" + isDeviceConnected);
        if (!isDeviceConnected) {
            Log.d(TAG, "<----connectionMode.getSelectedItem()---->" + connectionMode.getSelectedItem().toString());
            if (connectionMode.getSelectedItem().toString().toUpperCase().equals(BLUETOOTH)) {
                bluetoothMode();
                isDeviceConnected = true;
                connectionStatusimage.setImageResource(R.mipmap.connected);
            } else if (connectionMode.getSelectedItem().toString().toUpperCase().equals(INTERNET)) {
                connectionStatusimage.setImageResource(R.mipmap.disconnected);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "<---In onDestory()--->");
        if (isDeviceConnected) {
            if (arduinoController.isArduinoConnectedviaBluetoothStatus()) {
                Log.d(TAG, "<---disconnecting arduino--->");
                arduinoController.disconnectArduinoViaBluetooth();
                isDeviceConnected = false;
                connectionStatusimage.setImageResource(R.mipmap.disconnected);
                destroyBluetoothReceiver();
            }
        }
    }

    public void bluetoothMode() {
        if (!arduinoController.isBluetoothEnabled()) {
            Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOnIntent, BLUETOOTH_TURN_ON_REQUEST_CODE);
        } else {
            new BlueToothConnectionThread().execute();
        }
    }

    public void destroyBluetoothReceiver() {
        Log.d(TAG, "<----Bluetooth Receiever Destroyed---->");
        try {
            getApplicationContext().unregisterReceiver(bluetoothReceiver);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "<----Error occured during bluetooth brodcast unregister---->" + e.getMessage());
        }
    }

    final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                Log.d(TAG, "<----Bluetooth Connected---->");
            } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                Log.d(TAG, "<----Bluetooth Disconnected---->");
                connectionStatusimage.setImageResource(R.mipmap.disconnected);
//                setConnectionStatus("NOT CONNECTED", R.color.md_red_500);
                Toast.makeText(getApplicationContext(), "Device Connection Lost", Toast.LENGTH_SHORT).show();
                isDeviceConnected = false;
            }
        }
    };

    public Boolean connectViaBluetooth() {
        Boolean isConnected = false;
        final String address = arduinoController.getBluetoothAddress();
        Log.d(TAG, "<----Bluetooth Address---->" + address);

        if (address != null) {
            arduinoController.connectArduinoViaBluetooth(address);
            if (arduinoController.isArduinoConnectedviaBluetoothStatus()) {
                arduinoController.readData();
                arduinoController.sendData(ArduinoConstants.STATUS);
                getApplicationContext().registerReceiver(bluetoothReceiver, new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED));
                getApplicationContext().registerReceiver(bluetoothReceiver, new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED));
                isConnected = true;
            } else {
                Log.e(TAG, "<----Could Not Connect to Friuno---->");
                isConnected = false;
            }
        } else {
            Log.e(TAG, "<----FRIUNO couldn't be found---->");
            if (i == 0) {
                startActivityForResult(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS), BLUETOOTH_PAIR_REQUEST_CODE);
                i = 1;
            } else {
                isConnected = false;
            }
        }
        return isConnected;
    }

    private class BlueToothConnectionThread extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;
        Boolean isBluetoothConnected = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Please Wait..");
            progressDialog.setMessage("Connecting to Friuno....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            isBluetoothConnected = connectViaBluetooth();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            if (isBluetoothConnected) {
                connectionStatusimage.setImageResource(R.mipmap.connected);
                isDeviceConnected = true;
//                setConnectionStatus("CONNECTED", R.color.md_green_500);
                Toast.makeText(getApplicationContext(), "Connected to Friuno", Toast.LENGTH_SHORT).show();
            } else {
                connectionStatusimage.setImageResource(R.mipmap.disconnected);
                isDeviceConnected = false;
//                setConnectionStatus("NOT CONNECTED", R.color.md_red_500);
                Toast.makeText(getApplicationContext(), "Could Not Connect to Friuno\nPlease Check whether the device is turned on and paired", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
//            case R.id.action_voiceControl: {
//                recognizeVoice();
//                break;
//            }
//            case R.id.action_faceDetection: {
//                detectFace();
//                break;
//            }
            case R.id.action_help: {
                try {
                    Intent i = new Intent("android.intent.action.MAIN");
                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setData(Uri.parse("http://www.friuno.com"));
                    startActivity(i);
                } catch (ActivityNotFoundException e) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.friuno.com"));
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
                break;
            }
            case R.id.action_about: {
                new AlertDialog.Builder(this)
                        .setTitle("About Friuno")
                        .setMessage("Friuno\n------------------------\nVersion:1.0.3\n\nDeveloped By:\nGodwin Rose Samuel\nwww.friuno.com"
                                + "\n\nSupport by Email:\ncontactus@friuno.com"
                                + "\n\nDISCLAIMER:\n"
                                + "The user uses the application it on own and sole responsibity."
                                + "The information and datas appearing in the application serve exculsively "
                                + "as guidance and the creator is not liable for their correctness"
                                + "\n\nGood Luck!!\nGodwin")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
                break;
            }
            case R.id.action_logout: {
                new AlertDialog.Builder(this)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to log out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new SignOutThread().execute();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(AppController.getInstance().getGoogleApiHelper().getGoogleApiClient()).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(AppController.getInstance().getGoogleApiHelper().getGoogleApiClient()).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                    }
                });
    }

    private class SignOutThread extends AsyncTask<String, Void, Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Logging Out...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                Thread.sleep(1000);
                signOut();
                revokeAccess();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            finish();
            Intent intent=new Intent(MainActivity.this,LogInActivity.class);
            startActivity(intent);
        }
    }

    Handler navigationViewHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NAVIGATION_VIEW_ACTION: {
                    MenuItem menuItem = (MenuItem) msg.obj;
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    switch (menuItem.getItemId()) {
                        case R.id.item_navigation_drawer_dashboard:
                            fragmentTransaction.replace(R.id.content_frame, dashBoardFragment).commit();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            menuItem.setChecked(true);
                            break;
                        case R.id.item_navigation_drawer_switchBoards:
                            fragmentTransaction.replace(R.id.content_frame, switchBoardFragment).commit();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            menuItem.setChecked(true);
                            break;
                        case R.id.item_navigation_drawer_timer:
                            fragmentTransaction.replace(R.id.content_frame, timerFragment).commit();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            menuItem.setChecked(true);
                            break;
                        case R.id.item_navigation_drawer_statistics:
                            fragmentTransaction.replace(R.id.content_frame, statisticsFragment).commit();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            menuItem.setChecked(true);
                            break;
                        case R.id.item_navigation_drawer_about:
                            drawerLayout.closeDrawer(GravityCompat.START);
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("About Friuno")
                                    .setMessage("Friuno\n------------------------\nVersion:1.0.3\n\nDeveloped By:\nGodwin Rose Samuel\nwww.friuno.com"
                                            + "\n\nSupport by Email:\ncontactus@friuno.com"
                                            + "\n\nDISCLAIMER:\n"
                                            + "The user uses the application it on own and sole responsibility."
                                            + "The information and datas appearing in the application serve exculsively "
                                            + "as guidance and the creator is not liable for their correctness"
                                            + "\n\nGood Luck!!\nGodwin")
                                    .setIcon(android.R.drawable.ic_dialog_info)
                                    .show();
                            menuItem.setChecked(true);
                            break;
                        case R.id.item_navigation_drawer_share:
                            drawerLayout.closeDrawer(GravityCompat.START);
                            menuItem.setChecked(true);
                            Intent share = new Intent(Intent.ACTION_SEND);
                            share.setType("text/plain");
                            share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            share.putExtra(Intent.EXTRA_SUBJECT, navigationView.getContext().getString(R.string.app_name));
                            share.putExtra(Intent.EXTRA_TEXT, navigationView.getContext().getString(R.string.app_description) + "\n" +
                                    "Website : " + APP_STORE_URL + "\n" +
                                    "FaceBook Page : " + FACEBOOK_URL + "\n" +
                                    "Download from: " + PLAY_STORE_URL + navigationView.getContext().getPackageName());
                            navigationView.getContext().startActivity(Intent.createChooser(share, navigationView.getContext().getString(R.string.app_name)));
                            break;
                        case R.id.item_navigation_drawer_rateus:
                            drawerLayout.closeDrawer(GravityCompat.START);
                            menuItem.setChecked(true);
                            try {
                                Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(PLAY_STORE_URL + navigationView.getContext().getPackageName()));
                                navigationView.getContext().startActivity(rateIntent);
                            } catch (ActivityNotFoundException e) {
                                Toast.makeText(navigationView.getContext(), "No browser is installed", Toast.LENGTH_LONG);
                            }
                            break;
                        case R.id.item_navigation_drawer_location_settings:
                            fragmentTransaction.replace(R.id.content_frame, locationFragment).commit();
//                            Intent intent = new Intent(MainActivity.this, LocationActivity.class);
//                            startActivity(intent);
                            drawerLayout.closeDrawer(GravityCompat.START);
                            menuItem.setChecked(true);
                            break;
                        case R.id.item_navigation_drawer_device_settings:
                            drawerLayout.closeDrawer(GravityCompat.START);
                            menuItem.setChecked(true);
                            break;
                        case R.id.item_navigation_drawer_contact_us:
                            drawerLayout.closeDrawer(GravityCompat.START);
                            menuItem.setChecked(true);
                            try {
                                Intent contactusIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(CONTACT_US_URL));
                                navigationView.getContext().startActivity(contactusIntent);
                            } catch (ActivityNotFoundException e) {
                                Toast.makeText(navigationView.getContext(), "No browser is installed", Toast.LENGTH_LONG);
                            }
                            break;
                        case R.id.item_navigation_drawer_help_and_feedback:
                            drawerLayout.closeDrawer(GravityCompat.START);
                            menuItem.setChecked(true);
                            try {
                                Intent i = new Intent("android.intent.action.MAIN");
                                i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                                i.addCategory("android.intent.category.LAUNCHER");
                                i.setData(Uri.parse("http://www.friuno.com"));
                                navigationView.getContext().startActivity(i);
                            } catch (ActivityNotFoundException e) {
                                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.friuno.com"));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                navigationView.getContext().startActivity(i);
                            }
                            break;
                    }
                }
            }
        }
    };

    private void setupNavigationDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(final MenuItem menuItem) {
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    if (this == null)
                                        return;
                                    navigationViewHandler.obtainMessage(NAVIGATION_VIEW_ACTION, menuItem).sendToTarget();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        return true;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(this, "Press Back again to Exit", Toast.LENGTH_SHORT).show();
            doubleBackToExitPressedOnce = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

}

