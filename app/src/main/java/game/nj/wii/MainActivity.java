package game.nj.wii;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import game.nj.wii.view.GameView;

public class MainActivity extends ActionBarActivity {


    GameView gameView;
    ActionBar actionBar;
    TextView gameLevel;
    Button gameRestart;
    RelativeLayout viewRelativeLayout;
    TextView disGameTimes;
    TextView gameCount;
    AnimatorSet set;

    View view ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        actionBar.hide();

        LayoutInflater inflater= (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main, null);
        setContentView(view);
        view.setBackgroundResource(R.drawable.pic_1);
       // setContentView(R.layout.activity_main);

        disGameTimes = (TextView)findViewById(R.id.dis_game_time);

        /*Restart this level game*/        gameRestart = (Button)findViewById(R.id.bt_restart);
        gameRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.nextLevel(false);
            }
        });
/*
        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("alpha",0.3F,1F);
        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("alpha",1F,0.3F);
        ObjectAnimator.ofPropertyValuesHolder(gameRestart,p1,p2).setDuration(2000).start();
 */
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(gameRestart,"alpha",0.3F,1F);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(gameRestart,"alpha",1F,0.3F);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(disGameTimes,"alpha",0.3F,1F);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(disGameTimes,"alpha",1F,0.3F);

        set = new AnimatorSet();
        set.playSequentially(animator1, animator2);
        set.setDuration(2000);
        set.start();

        /*
        ObjectAnimator animator = ObjectAnimator.ofFloat(gameRestart,"alpha",0.5F,1F);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
        Log.d("Wii","ObjectAminator button restart");
        */
/*
        TranslateAnimation animation1 = new TranslateAnimation(0,200,0,0);
        animation1.setDuration(10000);
        animation1.setFillAfter(true);
        gameRestart.startAnimation(animation1);
*/


        gameLevel = (TextView)findViewById(R.id.dis_game_level);
        gameLevel.setText("Level 1");

        gameCount = (TextView)findViewById(R.id.dis_game_count);
        gameCount.setText("00" + 0);




       /* tv1 = (TextView)findViewById(R.id.tv1);
        if(tv1!=null)
            tv1.setText("99");
*/
        gameView = (GameView)findViewById(R.id.id_gameView);
        Time t = new Time();
        t.setToNow();
        Random rand =new Random(t.second);
        int i;
        i = rand.nextInt(5);
        i = 0;
       // gameView.setBG(i);
        switch (i){
            case 0:
                view.setBackgroundResource(R.drawable.background);gameView.setBG(i);
                break;
            /*
            case 1:
                view.setBackgroundResource(R.drawable.pic_1);gameView.setBG(i);
                break;
            case 2:
                view.setBackgroundResource(R.drawable.pic_1);gameView.setBG(i);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.pic_1);gameView.setBG(i);
                break;
            case 4:
                view.setBackgroundResource(R.drawable.pic_1);gameView.setBG(i);
                break;
            default:
                view.setBackgroundResource(R.drawable.pic_1);gameView.setBG(0);
                break;
                */
        }


        gameCount.setClickable(true);
        Log.d("wii", "gameCount clickable true");
        gameCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                Log.d("wii","menu is click");

            }
        });


        gameView.setTimeEnable(true);
        gameView.setOnGameListener(new GameView.GameListener() {
            @Override
            public void nextLevel(final int nextLevel) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("游戏提示")
                        .setMessage("恭喜过关！！！")
                        .setPositiveButton("下一关", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameLevel.setText("Level "+(nextLevel-1));
                                gameView.nextLevel(true);

                            }
                        }).setNegativeButton("重新开始", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameLevel.setText("Level "+(nextLevel-2));
                        gameView.nextLevel(false);
                    }
                }).show();
            }

            @Override
            public void timeChanged(int currentTimes) {
                String font="";
                if(currentTimes>99) {
                    font = "";
                }else if(currentTimes<100){
                    font = "0";
                }
                disGameTimes.setText(font + currentTimes);

                if( currentTimes%4 == 0 ) set.start(); //repeat animation of gamerestart

            }
            @Override
            public void disGameCount(int count){
                String font = "";
                if(count>99){
                    font = "";
                }
                if(count <100 && count >9){
                    font = "0";
                }
                if(count < 10 ){
                    font = "00";
                }
                gameCount.setText(font+count);

            }

            @Override
            public void gameOver() {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("游戏提示")
                        .setMessage("很抱歉，您失败了")
                        .setNegativeButton("重新开始", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                gameView.nextLevel(false);
                            }
                        }).show();

            }
        });


        PopupWindow mPop = new PopupWindow((LinearLayout)getLayoutInflater().inflate(R.layout.next_game, null),
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
