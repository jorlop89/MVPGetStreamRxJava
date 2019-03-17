
package jorgelopez.com.mvpgetstreamsrxjava.http.twitch.game;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TwitchGame {

    @SerializedName("data")
    @Expose
    private List<Game> games = null;

    public List<Game> getGames() {
        return games;
    }

    public void setData(List<Game> games) {
        this.games = games;
    }

}
