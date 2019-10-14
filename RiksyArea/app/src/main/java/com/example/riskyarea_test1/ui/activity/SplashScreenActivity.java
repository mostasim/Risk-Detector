package com.example.riskyarea_test1.ui.activity;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.example.riskyarea_test1.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
public class SplashScreenActivity extends AwesomeSplash {
    @Override
    public void initSplash(ConfigSplash configSplash) {

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.colorPrimaryDark); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(700); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.splash_image); //or any other drawable
        configSplash.setAnimLogoSplashDuration(500); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.ZoomIn); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Title
        configSplash.setTitleSplash("Risky Area"); //change your app name here
        configSplash.setTitleTextColor(R.color.colorAccent);
        configSplash.setTitleTextSize(45f); //float value
        configSplash.setAnimTitleDuration(1800);
        configSplash.setAnimTitleTechnique(Techniques.FadeInDown);
        configSplash.setTitleFont("fonts/Pacifico.ttf"); //provide string to your font located in assets/fonts/
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }
}