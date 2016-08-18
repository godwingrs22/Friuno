package com.friuno.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.friuno.AppController;
import com.friuno.LogInActivity;
import com.friuno.MainActivity;
import com.friuno.R;
import com.friuno.arduino.ArduinoConstants;
import com.friuno.arduino.ArduinoController;
import com.friuno.tabs.SwitchViewPagerAdapter;
import com.friuno.util.Constants;
import com.friuno.voice.VoiceRecognizer;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by GodwinRoseSamuel on 18-07-2016.
 */
public class SwitchBoardFragment extends Fragment {

    private static final String TAG = "SwitchBoardFragment";

    public static final int RECIEVE_MESSAGE = 201;
    public static final int RECIEVE_SPEECH_MESSAGE = 202;
    public static final int RECIEVE_TIMER_ACTION = 203;
    public static final int RECIEVE_SWITCHES_ALLOWED_ACTION = 204;

    public static final int LIVING_ROOM_VOICE_CONTROL_REQUEST_CODE = 301;
    public static final int MAIN_HALL_VOICE_CONTROL_REQUEST_CODE = 302;
    public static final int BEDROOM_VOICE_CONTROL_REQUEST_CODE = 303;
    public static final int KITCHEN_VOICE_CONTROL_REQUEST_CODE = 304;
    public static final int OTHERS_VOICE_CONTROL_REQUEST_CODE = 305;

    private VoiceRecognizer voiceRecognizer;
    private ViewPager mViewPager;
    public static Context context;
    int i = 0;

    public Boolean getIsDeviceConnected() {
        return MainActivity.getIsDeviceConnected();
    }

    public ArduinoController getArduinoController() {
        return MainActivity.getArduinoController();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_switchboard, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_switchboard_voiceControl: {
                switch (mViewPager.getCurrentItem()) {
                    case 0:
                        recognizeVoice(LIVING_ROOM_VOICE_CONTROL_REQUEST_CODE);
                        break;
                    case 1:
                        recognizeVoice(MAIN_HALL_VOICE_CONTROL_REQUEST_CODE);
                        break;
                    case 2:
                        recognizeVoice(BEDROOM_VOICE_CONTROL_REQUEST_CODE);
                        break;
                    case 3:
                        recognizeVoice(KITCHEN_VOICE_CONTROL_REQUEST_CODE);
                        break;
                    case 4:
                        recognizeVoice(OTHERS_VOICE_CONTROL_REQUEST_CODE);
                        break;
                }
                break;
            }
            case R.id.action_switchboard_help: {
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
            case R.id.action_switchboard_about: {
                new AlertDialog.Builder(getActivity())
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
            case R.id.action_switchboard_logout: {
                new AlertDialog.Builder(getActivity())
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

    public void recognizeVoice(final int requestCode) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "FRIUNO Voice Control");
        try {
            startActivityForResult(intent, requestCode);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getActivity(), "Unable to Access Voice Control System", Toast.LENGTH_SHORT).show();
        }
    }

    private class VoiceRecognizerThread extends AsyncTask<String, Void, Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Please Wait..");
            progressDialog.setMessage("Recognizing your speech...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            voiceRecognizer.speechToText(params[0], params[1]);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
        }
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
            progressDialog = new ProgressDialog(getActivity());
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
            getActivity().finish();
            Intent intent = new Intent(getContext(), LogInActivity.class);
            startActivity(intent);
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            Log.d(TAG, "User is SignedIN!");
        } else {
            Log.e(TAG, "<---User is SignedOUT--->");
            Intent intent = new Intent(getActivity(), LogInActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_viewpager, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setAdapter(new SwitchViewPagerAdapter(getChildFragmentManager()));
        context = view.getContext();

        voiceRecognizer = new VoiceRecognizer();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "<----In onResume---->");
        Log.d(TAG, "<----isDeviceConnected---->" + MainActivity.getIsDeviceConnected());
        if (MainActivity.getIsDeviceConnected()) {
            MainActivity.getArduinoController().readData();
            MainActivity.getArduinoController().sendData(ArduinoConstants.STATUS);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case LIVING_ROOM_VOICE_CONTROL_REQUEST_CODE: {
                if (data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    new VoiceRecognizerThread().execute(result.get(0), Constants.LIVING_ROOM);
                }
                break;
            }

            case MAIN_HALL_VOICE_CONTROL_REQUEST_CODE: {
                if (data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    new VoiceRecognizerThread().execute(result.get(0), Constants.MAIN_HALL);
                }
                break;
            }

            case BEDROOM_VOICE_CONTROL_REQUEST_CODE: {
                if (data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    new VoiceRecognizerThread().execute(result.get(0), Constants.BED_ROOM);
                }
                break;
            }

            case KITCHEN_VOICE_CONTROL_REQUEST_CODE: {
                if (data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    new VoiceRecognizerThread().execute(result.get(0), Constants.KITCHEN);
                }
                break;
            }

            case OTHERS_VOICE_CONTROL_REQUEST_CODE: {
                if (data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    new VoiceRecognizerThread().execute(result.get(0), Constants.OTHER_ROOMS);
                }
                break;
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
