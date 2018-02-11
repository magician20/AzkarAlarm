package android.magician.com.myazkar;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Calendar;

public class AzkarActivity extends AppCompatActivity {

    //Alarm Fields
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private AlarmManager alarmManager;
    private PendingIntent mPendingIntent;

    //Fragment fields
    private AzkarPagerAdapter mSectionsPagerAdapter;
    // The {@link ViewPager} that will host the section contents.
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azkar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new AzkarPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

//         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        int value1 = sharedPreferences.getInt(getString(R.string.key_morning), R.string.default_morning);
//        int value2 = sharedPreferences.getInt(getString(R.string.key_evening), R.string.default_evening);
//       // Toast.makeText(this, ""+value1+" "+value2, Toast.LENGTH_SHORT).show();

          /* Alarm Code */
        setAlarm();
    }
   //for get time value by using key and default value
    int getTimeValue(int key,int defaultValue){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getInt(getString(key), defaultValue);
    }
    /* Alarm Code */
    private void setAlarm() {
        //for check
        String toastMessage;
        //get time values
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int value1 = sharedPreferences.getInt(getString(R.string.key_morning), R.string.default_morning);
       // int value2 = sharedPreferences.getInt(getString(R.string.key_evening), R.string.default_evening)
        //this boolean mean on or off
        boolean check1 = true;
       // boolean check2 = true;
        // the alarm and notification is turned on
        if(check1) {
            //Set up the Notification Broadcast Intent
            Intent notifyIntent = new Intent(this, AlarmReceiver.class);

            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            mPendingIntent= PendingIntent.getBroadcast(this,
                    NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Set the alarm to start at the given time
            Calendar calendar = Calendar.getInstance();
            int hours = value1 / 60;
            int minutes = value1 % 60;
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, hours);
            cal.set(Calendar.MINUTE, minutes);

            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, mPendingIntent);
            toastMessage = "ON";
        }else{
            //Cancel the alarm and notification if the alarm is turned off
            if (alarmManager != null) {
                alarmManager.cancel(mPendingIntent);
            }
            mNotificationManager.cancelAll();
            toastMessage = "OFF";
        }


        //Show a toast to say the alarm is turned on or off
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_azkar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //code here for open setting
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }


}
