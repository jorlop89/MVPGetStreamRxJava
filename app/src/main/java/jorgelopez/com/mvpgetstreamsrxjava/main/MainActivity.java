package jorgelopez.com.mvpgetstreamsrxjava.main;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import jorgelopez.com.mvpgetstreamsrxjava.R;
import jorgelopez.com.mvpgetstreamsrxjava.http.TwitchAPI;
import jorgelopez.com.mvpgetstreamsrxjava.root.App;
import jorgelopez.com.mvpgetstreamsrxjava.root.ApplicationComponent;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.View{

    TextView tvInfo;
    Button btnStart;
    Button btnFinish;

    @Inject
    MainActivityMVP.Presenter presenter;

    @Inject
    TwitchAPI twitchAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ApplicationComponent component = ((App)getApplication()).getComponent();
        component.inject(this);

        tvInfo = findViewById(R.id.tv_info);
        btnStart = findViewById(R.id.btn_start);
        btnFinish = findViewById(R.id.btn_finish);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendButtonClicked();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.finishButtonClicked();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public void setInfo(String info) {
        tvInfo.setText(info);
    }

    @Override
    public TwitchAPI getTwitchAPI() {
        return twitchAPI;
    }

    @Override
    public void enableStartButton(boolean enabled) {
        if(enabled){
            btnStart.setVisibility(View.VISIBLE);
        }
        else{
            btnStart.setVisibility(View.GONE);
        }

    }

    @Override
    public void enableFinishButton(boolean enabled) {
        if(enabled){
            btnFinish.setVisibility(View.VISIBLE);
        }
        else{
            btnFinish.setVisibility(View.GONE);
        }
    }

    @Override
    public void closeApp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.finishAndRemoveTask();
        }
        else{
            finish();
            System.exit(0);
        }
    }
}
