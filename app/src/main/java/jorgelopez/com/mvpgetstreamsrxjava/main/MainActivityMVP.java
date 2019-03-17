package jorgelopez.com.mvpgetstreamsrxjava.main;

import jorgelopez.com.mvpgetstreamsrxjava.http.TwitchAPI;

public interface MainActivityMVP {
    interface View{
        void setInfo(String info);
        TwitchAPI getTwitchAPI();
        void enableStartButton(boolean enabled);
        void enableFinishButton(boolean enabled);
        void closeApp();
    }

    interface Presenter{
        void setView(MainActivityMVP.View view);
        void sendButtonClicked();
        void finishButtonClicked();
        void getGame(String title, Integer numCount, String gameId);
    }

    interface Model{
    }
}
