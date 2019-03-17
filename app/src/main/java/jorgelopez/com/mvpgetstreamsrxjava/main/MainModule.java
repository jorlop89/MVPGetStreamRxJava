package jorgelopez.com.mvpgetstreamsrxjava.main;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    public MainActivityMVP.Presenter provideLoginActivityPresenter(){
        return new MainActivityPresenter();
    }
}
