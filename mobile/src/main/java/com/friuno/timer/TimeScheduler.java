package com.friuno.timer;

import com.friuno.fragment.switchboard.BedRoomFragment;
import com.friuno.fragment.switchboard.KitchenFragment;
import com.friuno.fragment.switchboard.LivingRoomFragment;
import com.friuno.fragment.switchboard.MainHallFragment;
import com.friuno.fragment.switchboard.OthersFragment;
import com.friuno.util.Constants;

import java.util.Timer;
import java.util.TimerTask;

public class TimeScheduler {
    private static final LivingRoomFragment livingRoomFragment = new LivingRoomFragment();
    private static int livingRoomSwitch1ElapsedTime, livingRoomSwitch2ElapsedTime, livingRoomSwitch3ElapsedTime, livingRoomSwitch4ElapsedTime, livingRoomSwitch5ElapsedTime, livingRoomSwitch6ElapsedTime, livingRoomSwitch7ElapsedTime, livingRoomSwitch8ElapsedTime;
    private static int mainHallSwitch1ElapsedTime, mainHallSwitch2ElapsedTime, mainHallSwitch3ElapsedTime, mainHallSwitch4ElapsedTime, mainHallSwitch5ElapsedTime, mainHallSwitch6ElapsedTime;
    private static int bedRoomSwitch1ElapsedTime, bedRoomSwitch2ElapsedTime, bedRoomSwitch3ElapsedTime, bedRoomSwitch4ElapsedTime, bedRoomSwitch5ElapsedTime;
    private static int kitchenSwitch1ElapsedTime, kitchenSwitch2ElapsedTime, kitchenSwitch3ElapsedTime, kitchenSwitch4ElapsedTime, kitchenSwitch5ElapsedTime, kitchenSwitch6ElapsedTime, kitchenSwitch7ElapsedTime;
    private static int othersSwitch1ElapsedTime, othersSwitch2ElapsedTime, othersSwitch3ElapsedTime, othersSwitch4ElapsedTime, othersSwitch5ElapsedTime, othersSwitch6ElapsedTime;

    private static Boolean livingRoomswitch1TimerStarted = false,
            livingRoomswitch2TimerStarted = false,
            livingRoomswitch3TimerStarted = false,
            livingRoomswitch4TimerStarted = false,
            livingRoomswitch5TimerStarted = false,
            livingRoomswitch6TimerStarted = false,
            livingRoomswitch7TimerStarted = false,
            livingRoomswitch8TimerStarted = false;
    private static Boolean mainHallSwitch1TimerStarted = false,
            mainHallswitch2TimerStarted = false,
            mainHallswitch3TimerStarted = false,
            mainHallswitch4TimerStarted = false,
            mainHallswitch5TimerStarted = false,
            mainHallswitch6TimerStarted = false;
    private static Boolean bedRoomSwitch1TimerStarted = false,
            bedRoomswitch2TimerStarted = false,
            bedRoomswitch3TimerStarted = false,
            bedRoomswitch4TimerStarted = false,
            bedRoomswitch5TimerStarted = false;
    private static Boolean kitchenSwitch1TimerStarted = false,
            kitchenswitch2TimerStarted = false,
            kitchenswitch3TimerStarted = false,
            kitchenswitch4TimerStarted = false,
            kitchenswitch5TimerStarted = false,
            kitchenswitch6TimerStarted = false,
            kitchenswitch7TimerStarted = false;
    private static Boolean othersSwitch1TimerStarted = false,
            othersswitch2TimerStarted = false,
            othersswitch3TimerStarted = false,
            othersswitch4TimerStarted = false,
            othersswitch5TimerStarted = false,
            othersswitch6TimerStarted = false;

    private static Timer livingRoomSwitch1Timer = new Timer();
    private static Timer livingRoomSwitch2Timer = new Timer();
    private static Timer livingRoomSwitch3Timer = new Timer();
    private static Timer livingRoomSwitch4Timer = new Timer();
    private static Timer livingRoomSwitch5Timer = new Timer();
    private static Timer livingRoomSwitch6Timer = new Timer();
    private static Timer livingRoomSwitch7Timer = new Timer();
    private static Timer livingRoomSwitch8Timer = new Timer();

    private static Timer mainHallSwitch1Timer = new Timer();
    private static Timer mainHallSwitch2Timer = new Timer();
    private static Timer mainHallSwitch3Timer = new Timer();
    private static Timer mainHallSwitch4Timer = new Timer();
    private static Timer mainHallSwitch5Timer = new Timer();
    private static Timer mainHallSwitch6Timer = new Timer();

    private static Timer bedRoomSwitch1Timer = new Timer();
    private static Timer bedRoomSwitch2Timer = new Timer();
    private static Timer bedRoomSwitch3Timer = new Timer();
    private static Timer bedRoomSwitch4Timer = new Timer();
    private static Timer bedRoomSwitch5Timer = new Timer();

    private static Timer kitchenSwitch1Timer = new Timer();
    private static Timer kitchenSwitch2Timer = new Timer();
    private static Timer kitchenSwitch3Timer = new Timer();
    private static Timer kitchenSwitch4Timer = new Timer();
    private static Timer kitchenSwitch5Timer = new Timer();
    private static Timer kitchenSwitch6Timer = new Timer();
    private static Timer kitchenSwitch7Timer = new Timer();

    private static Timer othersSwitch1Timer = new Timer();
    private static Timer othersSwitch2Timer = new Timer();
    private static Timer othersSwitch3Timer = new Timer();
    private static Timer othersSwitch4Timer = new Timer();
    private static Timer othersSwitch5Timer = new Timer();
    private static Timer othersSwitch6Timer = new Timer();

    static String formatIntoHHMMSS(long secondsTotal) {
        long hours = secondsTotal / 3600,
                remainder = secondsTotal % 3600,
                minutes = remainder / 60,
                seconds = remainder % 60;

        return ((hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes + ":" + (seconds < 10 ? "0" : "") + seconds);
    }

    public static void livingRoomSwitch1StartTimer() {
        if (!livingRoomswitch1TimerStarted) {
            livingRoomSwitch1Timer = new Timer();
            livingRoomSwitch1Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    livingRoomSwitch1ElapsedTime += 1;
                    livingRoomFragment.livingRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_1, formatIntoHHMMSS(livingRoomSwitch1ElapsedTime), String.valueOf(livingRoomSwitch1ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        livingRoomswitch1TimerStarted = true;
    }

    public static void livingRoomSwitch1StopTimer() {
        if (livingRoomswitch1TimerStarted) {
            livingRoomSwitch1Timer.cancel();
        }
        livingRoomswitch1TimerStarted = false;
        livingRoomSwitch1ElapsedTime = 0;
    }

    public static void livingRoomSwitch2StartTimer() {
        if (!livingRoomswitch2TimerStarted) {
            livingRoomSwitch2Timer = new Timer();
            livingRoomSwitch2Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    livingRoomSwitch2ElapsedTime += 1;
                    livingRoomFragment.livingRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_2, formatIntoHHMMSS(livingRoomSwitch2ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        livingRoomswitch2TimerStarted = true;
    }

    public static void livingRoomSwitch2StopTimer() {
        if (livingRoomswitch2TimerStarted) {
            livingRoomSwitch2Timer.cancel();
        }
        livingRoomswitch2TimerStarted = false;
        livingRoomSwitch2ElapsedTime = 0;
    }

    public static void livingRoomSwitch3StartTimer() {
        if (!livingRoomswitch3TimerStarted) {
            livingRoomswitch3TimerStarted = true;
            livingRoomSwitch3Timer = new Timer();
            livingRoomSwitch3Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    livingRoomSwitch3ElapsedTime += 1;
                    livingRoomFragment.livingRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_3, formatIntoHHMMSS(livingRoomSwitch3ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        livingRoomswitch3TimerStarted = true;
    }

    public static void livingRoomSwitch3StopTimer() {
        if (livingRoomswitch3TimerStarted) {
            livingRoomSwitch3Timer.cancel();
        }
        livingRoomswitch3TimerStarted = false;
        livingRoomSwitch3ElapsedTime = 0;
    }

    public static void livingRoomSwitch4StartTimer() {
        if (!livingRoomswitch4TimerStarted) {
            livingRoomSwitch4Timer = new Timer();
            livingRoomSwitch4Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    livingRoomSwitch4ElapsedTime += 1;
                    livingRoomFragment.livingRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_4, formatIntoHHMMSS(livingRoomSwitch4ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        livingRoomswitch4TimerStarted = true;

    }

    public static void livingRoomSwitch4StopTimer() {
        if (livingRoomswitch4TimerStarted) {
            livingRoomSwitch4Timer.cancel();
        }
        livingRoomswitch4TimerStarted = false;
        livingRoomSwitch4ElapsedTime = 0;
    }

    public static void livingRoomSwitch5StartTimer() {
        if (!livingRoomswitch5TimerStarted) {
            livingRoomSwitch5Timer = new Timer();
            livingRoomSwitch5Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    livingRoomSwitch5ElapsedTime += 1;
                    livingRoomFragment.livingRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_5, formatIntoHHMMSS(livingRoomSwitch5ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        livingRoomswitch5TimerStarted = true;
    }

    public static void livingRoomSwitch5StopTimer() {
        if (livingRoomswitch5TimerStarted) {
            livingRoomSwitch5Timer.cancel();
        }
        livingRoomswitch5TimerStarted = false;
        livingRoomSwitch5ElapsedTime = 0;
    }

    public static void livingRoomSwitch6StartTimer() {
        if (!livingRoomswitch6TimerStarted) {
            livingRoomswitch6TimerStarted = true;
            livingRoomSwitch6Timer = new Timer();
            livingRoomSwitch6Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    livingRoomSwitch6ElapsedTime += 1;
                    livingRoomFragment.livingRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_6, formatIntoHHMMSS(livingRoomSwitch6ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
    }

    public static void livingRoomSwitch6StopTimer() {
        if (livingRoomswitch6TimerStarted) {
            livingRoomSwitch6Timer.cancel();
        }
        livingRoomswitch6TimerStarted = false;
        livingRoomSwitch6ElapsedTime = 0;
    }

    public static void livingRoomSwitch7StartTimer() {
        if (!livingRoomswitch7TimerStarted) {
            livingRoomSwitch7Timer = new Timer();
            livingRoomSwitch7Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    livingRoomSwitch7ElapsedTime += 1;
                    livingRoomFragment.livingRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_7, formatIntoHHMMSS(livingRoomSwitch7ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        livingRoomswitch7TimerStarted = true;
    }

    public static void livingRoomSwitch7StopTimer() {
        if (livingRoomswitch7TimerStarted) {
            livingRoomSwitch7Timer.cancel();
        }
        livingRoomswitch7TimerStarted = false;
        livingRoomSwitch7ElapsedTime = 0;
    }

    public static void livingRoomSwitch8StartTimer() {
        if (!livingRoomswitch8TimerStarted) {
            livingRoomSwitch8Timer = new Timer();
            livingRoomSwitch8Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    livingRoomSwitch8ElapsedTime += 1;
                    livingRoomFragment.livingRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_8, formatIntoHHMMSS(livingRoomSwitch8ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        livingRoomswitch8TimerStarted = true;
    }

    public static void livingRoomSwitch8StopTimer() {
        if (livingRoomswitch8TimerStarted) {
            livingRoomSwitch8Timer.cancel();
        }
        livingRoomswitch8TimerStarted = false;
        livingRoomSwitch8ElapsedTime = 0;
    }

    public static void mainHallSwitch1StartTimer() {
        if (!mainHallSwitch1TimerStarted) {
            mainHallSwitch1Timer = new Timer();
            mainHallSwitch1Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    mainHallSwitch1ElapsedTime += 1;
                    MainHallFragment.mainHallTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_1, formatIntoHHMMSS(mainHallSwitch1ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        mainHallSwitch1TimerStarted = true;
    }

    public static void mainHallSwitch1StopTimer() {
        if (mainHallSwitch1TimerStarted) {
            mainHallSwitch1Timer.cancel();
        }
        mainHallSwitch1TimerStarted = false;
        mainHallSwitch1ElapsedTime = 0;
    }

    public static void mainHallSwitch2StartTimer() {
        if (!mainHallswitch2TimerStarted) {
            mainHallSwitch2Timer = new Timer();
            mainHallSwitch2Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    mainHallSwitch2ElapsedTime += 1;
                    MainHallFragment.mainHallTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_2, formatIntoHHMMSS(mainHallSwitch2ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        mainHallswitch2TimerStarted = true;
    }

    public static void mainHallSwitch2StopTimer() {
        if (mainHallswitch2TimerStarted) {
            mainHallSwitch2Timer.cancel();
        }
        mainHallswitch2TimerStarted = false;
        mainHallSwitch2ElapsedTime = 0;
    }

    public static void mainHallSwitch3StartTimer() {
        if (!mainHallswitch3TimerStarted) {
            mainHallSwitch3Timer = new Timer();
            mainHallSwitch3Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    mainHallSwitch3ElapsedTime += 1;
                    MainHallFragment.mainHallTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_3, formatIntoHHMMSS(mainHallSwitch3ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        mainHallswitch3TimerStarted = true;
    }

    public static void mainHallSwitch3StopTimer() {
        if (mainHallswitch3TimerStarted) {
            mainHallSwitch3Timer.cancel();
        }
        mainHallswitch3TimerStarted = false;
        mainHallSwitch3ElapsedTime = 0;
    }

    public static void mainHallSwitch4StartTimer() {
        if (!mainHallswitch4TimerStarted) {
            mainHallSwitch4Timer = new Timer();
            mainHallSwitch4Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    mainHallSwitch4ElapsedTime += 1;
                    MainHallFragment.mainHallTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_4, formatIntoHHMMSS(mainHallSwitch4ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        mainHallswitch4TimerStarted = true;
    }

    public static void mainHallSwitch4StopTimer() {
        if (mainHallswitch4TimerStarted) {
            mainHallSwitch4Timer.cancel();
        }
        mainHallswitch4TimerStarted = false;
        mainHallSwitch4ElapsedTime = 0;
    }

    public static void mainHallSwitch5StartTimer() {
        if (!mainHallswitch5TimerStarted) {
            mainHallSwitch5Timer = new Timer();
            mainHallSwitch5Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    mainHallSwitch5ElapsedTime += 1;
                    MainHallFragment.mainHallTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_5, formatIntoHHMMSS(mainHallSwitch5ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        mainHallswitch5TimerStarted = true;
    }

    public static void mainHallSwitch5StopTimer() {
        if (mainHallswitch5TimerStarted) {
            mainHallSwitch5Timer.cancel();
        }
        mainHallswitch5TimerStarted = false;
        mainHallSwitch5ElapsedTime = 0;
    }

    public static void mainHallSwitch6StartTimer() {
        if (!mainHallswitch6TimerStarted) {
            mainHallSwitch6Timer = new Timer();
            mainHallSwitch6Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    mainHallSwitch6ElapsedTime += 1;
                    MainHallFragment.mainHallTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_6, formatIntoHHMMSS(mainHallSwitch6ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        mainHallswitch6TimerStarted = true;
    }

    public static void mainHallSwitch6StopTimer() {
        if (mainHallswitch6TimerStarted) {
            mainHallSwitch6Timer.cancel();
        }
        mainHallswitch6TimerStarted = false;
        mainHallSwitch6ElapsedTime = 0;
    }

    public static void bedRoomSwitch1StartTimer() {
        if (!bedRoomSwitch1TimerStarted) {
            bedRoomSwitch1Timer = new Timer();
            bedRoomSwitch1Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    bedRoomSwitch1ElapsedTime += 1;
                    BedRoomFragment.bedRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_1, formatIntoHHMMSS(bedRoomSwitch1ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        bedRoomSwitch1TimerStarted = true;
    }

    public static void bedRoomSwitch1StopTimer() {
        if (bedRoomSwitch1TimerStarted) {
            bedRoomSwitch1Timer.cancel();
        }
        bedRoomSwitch1TimerStarted = false;
        bedRoomSwitch1ElapsedTime = 0;
    }

    public static void bedRoomSwitch2StartTimer() {
        if (!bedRoomswitch2TimerStarted) {
            bedRoomSwitch2Timer = new Timer();
            bedRoomSwitch2Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    bedRoomSwitch2ElapsedTime += 1;
                    BedRoomFragment.bedRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_2, formatIntoHHMMSS(bedRoomSwitch2ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        bedRoomswitch2TimerStarted = true;
    }

    public static void bedRoomSwitch2StopTimer() {
        if (bedRoomswitch2TimerStarted) {
            bedRoomSwitch2Timer.cancel();
        }
        bedRoomswitch2TimerStarted = false;
        bedRoomSwitch2ElapsedTime = 0;
    }

    public static void bedRoomSwitch3StartTimer() {
        if (!bedRoomswitch3TimerStarted) {
            bedRoomSwitch3Timer = new Timer();
            bedRoomSwitch3Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    bedRoomSwitch3ElapsedTime += 1;
                    BedRoomFragment.bedRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_3, formatIntoHHMMSS(bedRoomSwitch3ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        bedRoomswitch3TimerStarted = true;
    }

    public static void bedRoomSwitch3StopTimer() {
        if (bedRoomswitch3TimerStarted) {
            bedRoomSwitch3Timer.cancel();
        }
        bedRoomswitch3TimerStarted = false;
        bedRoomSwitch3ElapsedTime = 0;
    }

    public static void bedRoomSwitch4StartTimer() {
        if (!bedRoomswitch4TimerStarted) {
            bedRoomSwitch4Timer = new Timer();
            bedRoomSwitch4Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    bedRoomSwitch4ElapsedTime += 1;
                    BedRoomFragment.bedRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_4, formatIntoHHMMSS(bedRoomSwitch4ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        bedRoomswitch4TimerStarted = true;
    }

    public static void bedRoomSwitch4StopTimer() {
        if (bedRoomswitch4TimerStarted) {
            bedRoomSwitch4Timer.cancel();
        }
        bedRoomswitch4TimerStarted = false;
        bedRoomSwitch4ElapsedTime = 0;
    }

    public static void bedRoomSwitch5StartTimer() {
        if (!bedRoomswitch5TimerStarted) {
            bedRoomSwitch5Timer = new Timer();
            bedRoomSwitch5Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    bedRoomSwitch5ElapsedTime += 1;
                    BedRoomFragment.bedRoomTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_5, formatIntoHHMMSS(bedRoomSwitch5ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        bedRoomswitch5TimerStarted = true;
    }

    public static void bedRoomSwitch5StopTimer() {
        if (bedRoomswitch5TimerStarted) {
            bedRoomSwitch5Timer.cancel();
        }
        bedRoomswitch5TimerStarted = false;
        bedRoomSwitch5ElapsedTime = 0;
    }

    public static void kitchenSwitch1StartTimer() {
        if (!kitchenSwitch1TimerStarted) {
            kitchenSwitch1Timer = new Timer();
            kitchenSwitch1Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    kitchenSwitch1ElapsedTime += 1;
                    KitchenFragment.kitchenTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_1, formatIntoHHMMSS(kitchenSwitch1ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        kitchenSwitch1TimerStarted = true;
    }

    public static void kitchenSwitch1StopTimer() {
        if (kitchenSwitch1TimerStarted) {
            kitchenSwitch1Timer.cancel();
        }
        kitchenSwitch1TimerStarted = false;
        kitchenSwitch1ElapsedTime = 0;
    }

    public static void kitchenSwitch2StartTimer() {
        if (!kitchenswitch2TimerStarted) {
            kitchenSwitch2Timer = new Timer();
            kitchenSwitch2Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    kitchenSwitch2ElapsedTime += 1;
                    KitchenFragment.kitchenTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_2, formatIntoHHMMSS(kitchenSwitch2ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        kitchenswitch2TimerStarted = true;
    }

    public static void kitchenSwitch2StopTimer() {
        if (kitchenswitch2TimerStarted) {
            kitchenSwitch2Timer.cancel();
        }
        kitchenswitch2TimerStarted = false;
        kitchenSwitch2ElapsedTime = 0;
    }

    public static void kitchenSwitch3StartTimer() {
        if (!kitchenswitch3TimerStarted) {
            kitchenSwitch3Timer = new Timer();
            kitchenSwitch3Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    kitchenSwitch3ElapsedTime += 1;
                    KitchenFragment.kitchenTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_3, formatIntoHHMMSS(kitchenSwitch3ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        kitchenswitch3TimerStarted = true;
    }

    public static void kitchenSwitch3StopTimer() {
        if (kitchenswitch3TimerStarted) {
            kitchenSwitch3Timer.cancel();
        }
        kitchenswitch3TimerStarted = false;
        kitchenSwitch3ElapsedTime = 0;
    }

    public static void kitchenSwitch4StartTimer() {
        if (!kitchenswitch4TimerStarted) {
            kitchenSwitch4Timer = new Timer();
            kitchenSwitch4Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    kitchenSwitch4ElapsedTime += 1;
                    KitchenFragment.kitchenTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_4, formatIntoHHMMSS(kitchenSwitch4ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        kitchenswitch4TimerStarted = true;
    }

    public static void kitchenSwitch4StopTimer() {
        if (kitchenswitch4TimerStarted) {
            kitchenSwitch4Timer.cancel();
        }
        kitchenswitch4TimerStarted = false;
        kitchenSwitch4ElapsedTime = 0;
    }

    public static void kitchenSwitch5StartTimer() {
        if (!kitchenswitch5TimerStarted) {
            kitchenSwitch5Timer = new Timer();
            kitchenSwitch5Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    kitchenSwitch5ElapsedTime += 1;
                    KitchenFragment.kitchenTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_5, formatIntoHHMMSS(kitchenSwitch5ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        kitchenswitch5TimerStarted = true;
    }

    public static void kitchenSwitch5StopTimer() {
        if (kitchenswitch5TimerStarted) {
            kitchenSwitch5Timer.cancel();
        }
        kitchenswitch5TimerStarted = false;
        kitchenSwitch5ElapsedTime = 0;
    }

    public static void kitchenSwitch6StartTimer() {
        if (!kitchenswitch6TimerStarted) {
            kitchenSwitch6Timer = new Timer();
            kitchenSwitch6Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    kitchenSwitch6ElapsedTime += 1;
                    KitchenFragment.kitchenTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_6, formatIntoHHMMSS(kitchenSwitch6ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        kitchenswitch6TimerStarted = true;
    }

    public static void kitchenSwitch6StopTimer() {
        if (kitchenswitch6TimerStarted) {
            kitchenSwitch6Timer.cancel();
        }
        kitchenswitch6TimerStarted = false;
        kitchenSwitch6ElapsedTime = 0;
    }

    public static void kitchenSwitch7StartTimer() {
        if (!kitchenswitch7TimerStarted) {
            kitchenSwitch7Timer = new Timer();
            kitchenSwitch7Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    kitchenSwitch7ElapsedTime += 1;
                    KitchenFragment.kitchenTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_7, formatIntoHHMMSS(kitchenSwitch7ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        kitchenswitch7TimerStarted = true;
    }

    public static void kitchenSwitch7StopTimer() {
        if (kitchenswitch7TimerStarted) {
            kitchenSwitch7Timer.cancel();
        }
        kitchenswitch7TimerStarted = false;
        kitchenSwitch7ElapsedTime = 0;
    }

    public static void othersSwitch1StartTimer() {
        if (!othersSwitch1TimerStarted) {
            othersSwitch1Timer = new Timer();
            othersSwitch1Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    othersSwitch1ElapsedTime += 1;
                    OthersFragment.othersTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_1, formatIntoHHMMSS(othersSwitch1ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        othersSwitch1TimerStarted = true;
    }

    public static void othersSwitch1StopTimer() {
        if (othersSwitch1TimerStarted) {
            othersSwitch1Timer.cancel();
        }
        othersSwitch1TimerStarted = false;
        othersSwitch1ElapsedTime = 0;
    }

    public static void othersSwitch2StartTimer() {
        if (!othersswitch2TimerStarted) {
            othersSwitch2Timer = new Timer();
            othersSwitch2Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    othersSwitch2ElapsedTime += 1;
                    OthersFragment.othersTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_2, formatIntoHHMMSS(othersSwitch2ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        othersswitch2TimerStarted = true;
    }

    public static void othersSwitch2StopTimer() {
        if (othersswitch2TimerStarted) {
            othersSwitch2Timer.cancel();
        }
        othersswitch2TimerStarted = false;
        othersSwitch2ElapsedTime = 0;
    }

    public static void othersSwitch3StartTimer() {
        if (!othersswitch3TimerStarted) {
            othersSwitch3Timer = new Timer();
            othersSwitch3Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    othersSwitch3ElapsedTime += 1;
                    OthersFragment.othersTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_3, formatIntoHHMMSS(othersSwitch3ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        othersswitch3TimerStarted = true;
    }

    public static void othersSwitch3StopTimer() {
        if (othersswitch3TimerStarted) {
            othersSwitch3Timer.cancel();
        }
        othersswitch3TimerStarted = false;
        othersSwitch3ElapsedTime = 0;
    }

    public static void othersSwitch4StartTimer() {
        if (!othersswitch4TimerStarted) {
            othersSwitch4Timer = new Timer();
            othersSwitch4Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    othersSwitch4ElapsedTime += 1;
                    OthersFragment.othersTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_4, formatIntoHHMMSS(othersSwitch4ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        othersswitch4TimerStarted = true;
    }

    public static void othersSwitch4StopTimer() {
        if (othersswitch4TimerStarted) {
            othersSwitch4Timer.cancel();
        }
        othersswitch4TimerStarted = false;
        othersSwitch4ElapsedTime = 0;
    }

    public static void othersSwitch5StartTimer() {
        if (!othersswitch5TimerStarted) {
            othersSwitch5Timer = new Timer();
            othersSwitch5Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    othersSwitch5ElapsedTime += 1;
                    OthersFragment.othersTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_5, formatIntoHHMMSS(othersSwitch5ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        othersswitch5TimerStarted = true;
    }

    public static void othersSwitch5StopTimer() {
        if (othersswitch5TimerStarted) {
            othersSwitch5Timer.cancel();
        }
        othersswitch5TimerStarted = false;
        othersSwitch5ElapsedTime = 0;
    }

    public static void othersSwitch6StartTimer() {
        if (!othersswitch6TimerStarted) {
            othersSwitch6Timer = new Timer();
            othersSwitch6Timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    othersSwitch6ElapsedTime += 1;
                    OthersFragment.othersTimeHandler.obtainMessage(LivingRoomFragment.RECIEVE_TIMER_ACTION, new String[]{Constants.SWITCH_6, formatIntoHHMMSS(othersSwitch6ElapsedTime)}).sendToTarget();
                }
            }, 0, 1000);
        }
        othersswitch6TimerStarted = true;
    }

    public static void othersSwitch6StopTimer() {
        if (othersswitch6TimerStarted) {
            othersSwitch6Timer.cancel();
        }
        othersswitch6TimerStarted = false;
        othersSwitch6ElapsedTime = 0;
    }
}
