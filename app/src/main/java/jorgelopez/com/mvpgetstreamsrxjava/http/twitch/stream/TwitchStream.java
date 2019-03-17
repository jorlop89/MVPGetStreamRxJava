
package jorgelopez.com.mvpgetstreamsrxjava.http.twitch.stream;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TwitchStream {

    @SerializedName("data")
    @Expose
    private List<StreamData> streamData = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public List<StreamData> getData() {
        return streamData;
    }

    public void setData(List<StreamData> streamData) {
        this.streamData = streamData;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
