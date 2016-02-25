package game.nj.wii;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashActivity extends Activity {
    ActionBar actionBar;
    TextView tv_version;
    String version = "1.0" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   actionBar = getSupportActionBar();
      //  actionBar.hide();

        setContentView(R.layout.activity_splash);

        tv_version = (TextView)findViewById(R.id.tv_splash_version);
        tv_version.setText("Version: " +getVersion());
    }

    private String getVersion(){
        PackageManager pm = this.getPackageManager();

        try {
            PackageInfo info = pm.getPackageInfo(getPackageName(),0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }

    }
}
