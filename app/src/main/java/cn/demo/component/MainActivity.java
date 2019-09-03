package cn.demo.component;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.Map;

import cn.router.api.router.WeRouter;

/**
 * @author WANG
 * @date 2018-10-17
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeRouter.getInstance().build("native://LoginActivity").navigation(MainActivity.this);
            }
        });

        findViewById(R.id.main_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.login_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object navigation = WeRouter.getInstance().build("native://provider/LoginOutPutImp").navigation();
                Log.e("WANG","MainActivity.onClick.LoginOutPutImp "+navigation );
            }
        });

        findViewById(R.id.router_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}



