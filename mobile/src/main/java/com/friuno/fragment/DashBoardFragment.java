package com.friuno.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.friuno.R;
import com.friuno.fragment.switchboard.LivingRoomFragment;
import com.friuno.util.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GodwinRoseSamuel on 07-11-2017.
 */
public class DashBoardFragment extends Fragment {

    private static final String TAG = "DashBoardFragment";
    public static final int RECIEVE_DASHBOARD_SWITCH_STATUS = 201;
    private TextSwitcher room_switch_TextSwitcher, room_switch_on_value_TextSwitcher, room_switch_off_value_TextSwitcher;
    private TextSwitcher room_electricity_TextSwitcher, room_electricity_value_TextSwitcher;
    private TextSwitcher room_climate_TextSwitcher, room_climate_value_TextSwitcher;
    private TextSwitcher room_electricity_load_TextSwitcher, room_electricity_load_value_TextSwitcher, room_electricity_load_unit_value_TextSwitcher;
    private String[] livingRoomArray, mainHallArray, bedRoomArray, kitchenArray, otherRoomArray;
    private final Map<String, String[]> roomMap = new HashMap<String, String[]>();
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        context = view.getContext();

        room_switch_TextSwitcher = (TextSwitcher) view.findViewById(R.id.dashboard_room_switch);
        room_switch_on_value_TextSwitcher = (TextSwitcher) view.findViewById(R.id.dashboard_room_switch_on_value);
        room_switch_off_value_TextSwitcher = (TextSwitcher) view.findViewById(R.id.dashboard_room_switch_off_value);

        room_electricity_TextSwitcher = (TextSwitcher) view.findViewById(R.id.dashboard_room_electricity);
        room_electricity_value_TextSwitcher = (TextSwitcher) view.findViewById(R.id.dashboard_room_electricity_value);

        room_climate_TextSwitcher = (TextSwitcher) view.findViewById(R.id.dashboard_room_climate);
        room_climate_value_TextSwitcher = (TextSwitcher) view.findViewById(R.id.dashboard_room_climate_value);

        room_electricity_load_TextSwitcher = (TextSwitcher) view.findViewById(R.id.dashboard_room_electricity_load);
        room_electricity_load_value_TextSwitcher = (TextSwitcher) view.findViewById(R.id.dashboard_room_electricity_load_value);
        room_electricity_load_unit_value_TextSwitcher = (TextSwitcher) view.findViewById(R.id.dashboard_room_electricity_load_unit_value);

        livingRoomArray = new String[]{"3", "5", "12.2", "30\u00b0", "20", "W"};
        mainHallArray = new String[]{"6", "2", "20.9", "25\u00b0", "1", "KW"};
        bedRoomArray = new String[]{"1", "7", "35.0", "38\u00b0", "60", "W"};
        kitchenArray = new String[]{"5", "3", "15.4", "72\u00b0", "2", "KW"};
        otherRoomArray = new String[]{"4", "4", "23.1", "27\u00b0", "5", "KW"};

        roomMap.put(Constants.LIVING_ROOM, livingRoomArray);
        roomMap.put(Constants.MAIN_HALL, mainHallArray);
        roomMap.put(Constants.BED_ROOM, bedRoomArray);
        roomMap.put(Constants.KITCHEN, kitchenArray);
        roomMap.put(Constants.OTHER_ROOMS, otherRoomArray);

        initialiseSwitchView();
        initialiseElectricityView();
        initialiseClimateView();
        initialiseElectricityLoadView();

        new dashBoardThread().execute();
        return view;
    }

    public void initialiseSwitchView() {
        room_switch_TextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView room = new TextView(context);
                room.setGravity(Gravity.CENTER);
                room.setTextAppearance(context, R.style.TextAppearance_AppCompat_Button);
                room.setTextColor(getResources().getColor(R.color.md_orange_500));
                return room;
            }
        });

        room_switch_on_value_TextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView switch_on = new TextView(context);
                switch_on.setGravity(Gravity.CENTER);
                switch_on.setTextAppearance(context, R.style.TextAppearance_AppCompat_Title);
                switch_on.setTextColor(getResources().getColor(R.color.md_deep_purple_500));
                return switch_on;
            }
        });

        room_switch_off_value_TextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView switch_off = new TextView(context);
                switch_off.setGravity(Gravity.CENTER);
                switch_off.setTextAppearance(context, R.style.TextAppearance_AppCompat_Title);
                switch_off.setTextColor(getResources().getColor(R.color.md_deep_purple_500));
                return switch_off;
            }
        });

        room_switch_TextSwitcher.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left));
        room_switch_TextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right));

        room_switch_on_value_TextSwitcher.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
        room_switch_on_value_TextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out));

        room_switch_off_value_TextSwitcher.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
        room_switch_off_value_TextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out));

        room_switch_TextSwitcher.setText(Constants.BED_ROOM);
        room_switch_on_value_TextSwitcher.setText("-");
        room_switch_off_value_TextSwitcher.setText("-");
    }

    public void initialiseElectricityView() {
        room_electricity_TextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView room = new TextView(context);
                room.setGravity(Gravity.CENTER);
                room.setTextAppearance(context, R.style.TextAppearance_AppCompat_Button);
                room.setTextColor(getResources().getColor(R.color.md_orange_500));
                return room;
            }
        });

        room_electricity_value_TextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView electricity = new TextView(context);
                electricity.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
                electricity.setTextAppearance(context, R.style.TextAppearance_AppCompat_Display1);
                electricity.setTextColor(getResources().getColor(R.color.md_deep_purple_500));
                return electricity;
            }
        });

        room_electricity_TextSwitcher.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left));
        room_electricity_TextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right));

        room_electricity_value_TextSwitcher.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
        room_electricity_value_TextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out));

        room_electricity_TextSwitcher.setText(Constants.BED_ROOM);
        room_electricity_value_TextSwitcher.setText("-");
    }

    public void initialiseClimateView() {
        room_climate_TextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView room = new TextView(context);
                room.setGravity(Gravity.CENTER);
                room.setTextAppearance(context, R.style.TextAppearance_AppCompat_Button);
                room.setTextColor(getResources().getColor(R.color.md_orange_500));
                return room;
            }
        });

        room_climate_value_TextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView climate = new TextView(context);
                climate.setGravity(Gravity.CENTER);
                climate.setTextAppearance(context, R.style.TextAppearance_AppCompat_Display1);
                climate.setTextColor(getResources().getColor(R.color.md_deep_purple_500));
                return climate;
            }
        });

        room_climate_TextSwitcher.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left));
        room_climate_TextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right));

        room_climate_value_TextSwitcher.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
        room_climate_value_TextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out));

        room_climate_TextSwitcher.setText(Constants.BED_ROOM);
        room_climate_value_TextSwitcher.setText("-\u00b0");
    }

    public void initialiseElectricityLoadView() {
        room_electricity_load_TextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView room = new TextView(context);
                room.setGravity(Gravity.CENTER);
                room.setTextAppearance(context, R.style.TextAppearance_AppCompat_Button);
                room.setTextColor(getResources().getColor(R.color.md_orange_500));
                return room;
            }
        });

        room_electricity_load_value_TextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView load = new TextView(context);
                load.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
                load.setTextAppearance(context, R.style.TextAppearance_AppCompat_Display1);
                load.setTextColor(getResources().getColor(R.color.md_deep_purple_500));
                return load;
            }
        });

        room_electricity_load_unit_value_TextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView unit = new TextView(context);
                unit.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
                unit.setTextAppearance(context, R.style.TextAppearance_AppCompat_Small);
                unit.setTextColor(getResources().getColor(R.color.md_deep_purple_500));
                return unit;
            }
        });

        room_electricity_load_TextSwitcher.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left));
        room_electricity_load_TextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right));

        room_electricity_load_value_TextSwitcher.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
        room_electricity_load_value_TextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out));

        room_electricity_load_unit_value_TextSwitcher.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
        room_electricity_load_unit_value_TextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out));

        room_electricity_load_TextSwitcher.setText(Constants.BED_ROOM);
        room_electricity_load_value_TextSwitcher.setText("-");
    }

    private class dashBoardThread extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            for (Map.Entry<String, String[]> roomClimate : roomMap.entrySet()) {
                                Thread.sleep(3000);
                                if (getActivity() == null)
                                    return;
                                if (roomClimate.getKey().equals(Constants.LIVING_ROOM)) {
                                    livingRoomArray = new String[]{String.valueOf(LivingRoomFragment.livingRoom_Switch_ON), String.valueOf(LivingRoomFragment.livingRoom_Switch_OFF), String.valueOf(LivingRoomFragment.livingRoom_unit), "28\u00b0", String.valueOf(LivingRoomFragment.livingRoom_Load), "W"};
                                    roomClimate.setValue(livingRoomArray);
                                }
                                Message message = dashBoardHandler.obtainMessage();
                                Bundle bundle = new Bundle();
                                bundle.putString("room", roomClimate.getKey());
                                bundle.putStringArray("value", roomClimate.getValue());
                                message.setData(bundle);
                                dashBoardHandler.sendMessage(message);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    private Handler dashBoardHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                final String room = msg.getData().getString("room");
                final String[] values = msg.getData().getStringArray("value");

                room_switch_TextSwitcher.setText(room);
                room_switch_on_value_TextSwitcher.setText(values[0]);
                room_switch_off_value_TextSwitcher.setText(values[1]);

                room_electricity_TextSwitcher.setText(room);
                room_electricity_value_TextSwitcher.setText(values[2]);

                room_climate_TextSwitcher.setText(room);
                room_climate_value_TextSwitcher.setText(values[3]);

                room_electricity_load_TextSwitcher.setText(room);
                room_electricity_load_value_TextSwitcher.setText(values[4]);
                room_electricity_load_unit_value_TextSwitcher.setText(values[5]);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };
}
