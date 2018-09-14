package com.example.abdulrahman.newslist.data.Local.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.abdulrahman.newslist.di.ApplicationContext;
import com.example.abdulrahman.newslist.di.PreferenceInfo;
import com.example.abdulrahman.newslist.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by abdulrahman on 23/07/17.
 */

@Singleton
public class PrefrencesHelperImp  implements PrefrencesHelper {

    private static final String PREF_KEY_APP_STYLE= "PREF_KEY_APP_STYLE";

    private final SharedPreferences mPrefs;

    @Inject
    public PrefrencesHelperImp(@ApplicationContext Context context,
                               @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }




    @Nullable
   public static PrefrencesHelperImp getInstance(Context context ){
        return  new PrefrencesHelperImp(context, AppConstants.PREF_APP_NAME);
    }
}
