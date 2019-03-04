package cn.component.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.router.werouter.annotation.Router;

/**
 * @author WANG
 */
@Router(path = "native://AdActivity")
public class AdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
    }
}
