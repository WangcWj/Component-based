package cn.education.app_main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.router.werouter.annotation.Router;

@Router(path = "native://MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
