package jorgelopez.com.mvpgetstreamsrxjava.http;

import io.reactivex.Observable;
import jorgelopez.com.mvpgetstreamsrxjava.http.twitch.game.TwitchGame;
import jorgelopez.com.mvpgetstreamsrxjava.http.twitch.stream.TwitchStream;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TwitchAPI {

    @GET("streams")
    Observable<TwitchStream> getStreams(@Header("Client-Id") String clientId);

    @GET("games")
    Observable<TwitchGame> getGame(@Header("Client-Id") String clientId, @Query("id") String id);

}
