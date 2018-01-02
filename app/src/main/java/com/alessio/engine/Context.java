package com.alessio.engine;

public class Context extends android.app.Application{
    private static android.content.Context context;

    public void onCreate() {
        super.onCreate();
        Context.context = getApplicationContext();
    }

    public static android.content.Context getContext() {
        return Context.context;
    }
}
