package cn.component.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.router.api.router.WeRouter;
import cn.router.werouter.annotation.Router;
import cn.wenet.networkcomponent.WeNetwork;

/**
 * @author WANG
 */
@Router(path = "native://StartActivity")
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeRouter.getInstance().build("native://LoginActivity").navigation(StartActivity.this);
            }
        });
    }
}
