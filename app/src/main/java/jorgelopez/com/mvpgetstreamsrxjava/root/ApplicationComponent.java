package jorgelopez.com.mvpgetstreamsrxjava.root;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import jorgelopez.com.mvpgetstreamsrxjava.http.TwitchModule;
import jorgelopez.com.mvpgetstreamsrxjava.main.MainActivity;
import jorgelopez.com.mvpgetstreamsrxjava.main.MainModule;

@Singleton
@Component(modules = {ApplicationModule.class, TwitchModule.class, MainModule.class})
public interface ApplicationComponent {
    void inject(MainActivity target);
}
