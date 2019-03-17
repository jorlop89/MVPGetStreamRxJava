package jorgelopez.com.mvpgetstreamsrxjava.root;

import android.app.Application;

import jorgelopez.com.mvpgetstreamsrxjava.main.MainModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
                .mainModule(new MainModule())
                .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
