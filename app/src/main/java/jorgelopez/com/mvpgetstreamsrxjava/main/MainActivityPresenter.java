package jorgelopez.com.mvpgetstreamsrxjava.main;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import jorgelopez.com.mvpgetstreamsrxjava.BuildConfig;
import jorgelopez.com.mvpgetstreamsrxjava.http.twitch.game.Game;
import jorgelopez.com.mvpgetstreamsrxjava.http.twitch.game.TwitchGame;
import jorgelopez.com.mvpgetstreamsrxjava.http.twitch.stream.StreamData;
import jorgelopez.com.mvpgetstreamsrxjava.http.twitch.stream.TwitchStream;

public class MainActivityPresenter implements MainActivityMVP.Presenter{

    @Nullable
    private MainActivityMVP.View view;
    private String s;
    private String apiKey = BuildConfig.ApiKey;

    public MainActivityPresenter(){

    }

    @Override
    public void setView(MainActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void sendButtonClicked() {
        if(view != null) {
            s = "";

            Observable<StreamData> getStreams = view.getTwitchAPI()
                    .getStreams(apiKey)
                    .flatMap(new Function<TwitchStream, ObservableSource<StreamData>>() {
                        @Override
                        public ObservableSource<StreamData> apply(TwitchStream twitchStream) {
                            return Observable.fromIterable(twitchStream.getData());
                        }
                    })
                    .filter(new Predicate<StreamData>() {
                        @Override
                        public boolean test(StreamData streamData) {
                            return streamData.getLanguage().equals("en") && streamData.getViewerCount() >= 10000;
                        }
                    });

            getStreams.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<StreamData>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(StreamData stream) {
                    //getGame(stream.getId(), stream.getTitle(), stream.getGameId());
                    getGame(stream.getTitle(), stream.getViewerCount(), stream.getGameId());
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {
                }
            });

        }
    }

    @Override
    public void finishButtonClicked() {
        view.closeApp();
    }

    @Override
    public void getGame(final String title, final Integer numCount, String gameId) {

        view.getTwitchAPI().getGame(apiKey, gameId)
            .flatMap(new Function<TwitchGame, ObservableSource<Game>>() {
                @Override
                public ObservableSource<Game> apply(TwitchGame twitchGame) {
                    return Observable.fromIterable(twitchGame.getGames());
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Game>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Game game) {
                    //System.out.println(game.getName());
                    //s += id + "\n";
                    //s += title + "\n";
                    s += "Title: " + title + "\n";
                    s += "Number of views: " + numCount + "\n";
                    s += "Game: " + (game.getName() + "\n\n");
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {
                    view.setInfo(s);
                    view.enableStartButton(false);
                    view.enableFinishButton(true);
                }
            });
    }
}
