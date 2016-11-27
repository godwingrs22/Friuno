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
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.friuno.AppController;
import com.friuno.LogInActivity;
import com.friuno.MainActivity;
import com.friuno.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

/**
 * Created by GodwinRoseSamuel on 30-10-2016.
 */
public class StatisticsFragment extends Fragment {

    private static final String TAG = "StatisticsFragment";
    private ValueLineChart totalUnitView;
    private ValueLineChart powerstatsView;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_statistics, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh: {
                totalUnitView.invalidate();
                powerstatsView.invalidate();
                totalUnitView.startAnimation();
                powerstatsView.startAnimation();
                System.out.println("Refreshed");
                break;
            }
            case R.id.action_settings_help: {
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
            case R.id.action_settings_about: {
                new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK)
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
            case R.id.action_settings_logout: {
                new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK)
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
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        context = view.getContext();

        totalUnitView = (ValueLineChart) view.findViewById(R.id.totalUnitView);
        powerstatsView = (ValueLineChart) view.findViewById(R.id.powerstats);

        setUnitConsumptionView();
        setPowerStatsView();
        return view;
    }

    public void setUnitConsumptionView() {
        ValueLineSeries totalUnitSeries = new ValueLineSeries();
        totalUnitSeries.setColor(getResources().getColor(R.color.md_pink_500));

        totalUnitSeries.addPoint(new ValueLinePoint("Jan", 100));
        totalUnitSeries.addPoint(new ValueLinePoint("Feb", 120));
        totalUnitSeries.addPoint(new ValueLinePoint("Mar", 103));
        totalUnitSeries.addPoint(new ValueLinePoint("Apr", 174));
        totalUnitSeries.addPoint(new ValueLinePoint("May", 130));
        totalUnitSeries.addPoint(new ValueLinePoint("Jun", 106));
        totalUnitSeries.addPoint(new ValueLinePoint("Jul", 155));
        totalUnitSeries.addPoint(new ValueLinePoint("Aug", 141));
        totalUnitSeries.addPoint(new ValueLinePoint("Sep", 121));
        totalUnitSeries.addPoint(new ValueLinePoint("Oct", 136));
        totalUnitSeries.addPoint(new ValueLinePoint("Nov", 175));
        totalUnitSeries.addPoint(new ValueLinePoint("Dec", 102));

        totalUnitView.addSeries(totalUnitSeries);
//        totalUnitView.setIndicatorLineColor(getResources().getColor(R.color.md_green_500));
        totalUnitView.startAnimation();
    }

    public void setPowerStatsView() {
        ValueLineSeries powerStatSeries = new ValueLineSeries();
        powerStatSeries.setColor(getResources().getColor(R.color.md_deep_orange_500));

        powerStatSeries.addPoint(new ValueLinePoint("Jan", 41));
        powerStatSeries.addPoint(new ValueLinePoint("Feb", 23));
        powerStatSeries.addPoint(new ValueLinePoint("Mar", 56));
        powerStatSeries.addPoint(new ValueLinePoint("Apr", 13));
        powerStatSeries.addPoint(new ValueLinePoint("May", 67));
        powerStatSeries.addPoint(new ValueLinePoint("Jun", 99));
        powerStatSeries.addPoint(new ValueLinePoint("Jul", 16));
        powerStatSeries.addPoint(new ValueLinePoint("Aug", 56));
        powerStatSeries.addPoint(new ValueLinePoint("Sep", 38));
        powerStatSeries.addPoint(new ValueLinePoint("Oct", 69));
        powerStatSeries.addPoint(new ValueLinePoint("Nov", 60));
        powerStatSeries.addPoint(new ValueLinePoint("Dec", 20));

        powerstatsView.addSeries(powerStatSeries);
//        totalUnitView.setIndicatorLineColor(getResources().getColor(R.color.md_green_500));
        powerstatsView.startAnimation();
    }
}
