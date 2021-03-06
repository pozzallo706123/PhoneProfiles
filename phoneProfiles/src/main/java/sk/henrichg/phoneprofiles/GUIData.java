package sk.henrichg.phoneprofiles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;

import java.text.Collator;
import java.util.Locale;

public class GUIData {

	public static BrightnessView brightneesView = null;
	
	public static Collator collator = null;
	
	// import/export
	public static final String DB_FILEPATH = "/data/" + GlobalData.PACKAGE_NAME + "/databases";
	public static final String REMOTE_EXPORT_PATH = "/PhoneProfilesPlus";
	public static final String EXPORT_APP_PREF_FILENAME = "ApplicationPreferences.backup";
	public static final String EXPORT_DEF_PROFILE_PREF_FILENAME = "DefaultProfilePreferences.backup";
	

	public static void setLanguage(Context context)//, boolean restart)
	{
		//long nanoTimeStart = GlobalData.startMeasuringRunTime();
		
		// jazyk na aky zmenit
		String lang = GlobalData.applicationLanguage;
		
		Locale appLocale;
		
		if (!lang.equals("system"))
		{
    		String[] langSplit = lang.split("-");
			if (langSplit.length == 1)
				appLocale = new Locale(lang);
			else
				appLocale = new Locale(langSplit[0], langSplit[1]);
		}
		else
		{
			appLocale = Resources.getSystem().getConfiguration().locale;
		}
		
		Locale.setDefault(appLocale);
		Configuration appConfig = new Configuration();
		appConfig.locale = appLocale;
		context.getResources().updateConfiguration(appConfig, context.getResources().getDisplayMetrics());
		
		// collator for application locale sorting
		collator = getCollator();
		
		//languageChanged = restart;
		
		//GlobalData.getMeasuredRunTime(nanoTimeStart, "GUIData.setLanguage");
		
	}
	
	public static Collator getCollator()
	{
		// get application Locale
		String lang = GlobalData.applicationLanguage;
		Locale appLocale;
		if (!lang.equals("system"))
		{
    		String[] langSplit = lang.split("-");
			if (langSplit.length == 1)
				appLocale = new Locale(lang);
			else
				appLocale = new Locale(langSplit[0], langSplit[1]);
		}
		else
		{
			appLocale = Resources.getSystem().getConfiguration().locale;
		}

		// get collator for application locale
		return Collator.getInstance(appLocale);
	}
	
	public static void setTheme(Activity activity, boolean forPopup)
	{
		//long nanoTimeStart = GlobalData.startMeasuringRunTime();
		
		if (GlobalData.applicationTheme.equals("material"))
		{
			if (forPopup)
				activity.setTheme(R.style.PopupTheme_material);
			else
				activity.setTheme(R.style.Theme_Phoneprofilestheme_material);
		}
		else
		if (GlobalData.applicationTheme.equals("dark"))
		{
			if (forPopup)
				activity.setTheme(R.style.PopupTheme_dark);
			else
				activity.setTheme(R.style.Theme_Phoneprofilestheme_dark);
		}
		else
		if (GlobalData.applicationTheme.equals("dlight"))
		{
			if (forPopup)
				activity.setTheme(R.style.PopupTheme_dlight);
			else
				activity.setTheme(R.style.Theme_Phoneprofilestheme_dlight);
		}
		
		//GlobalData.getMeasuredRunTime(nanoTimeStart, "GUIData.setTheme");
		
	}
	
	public static void reloadActivity(Activity activity, boolean newIntent)
	{
		if (newIntent)
		{

            final Activity _activity = activity;
            new Handler().post(new Runnable() {

                @Override
                public void run()
                {
                    Intent intent = _activity.getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    _activity.overridePendingTransition(0, 0);
                    _activity.finish();

                    _activity.overridePendingTransition(0, 0);
                    _activity.startActivity(intent);
                }
            });
		}
		else
			activity.recreate();
	}
	
}
