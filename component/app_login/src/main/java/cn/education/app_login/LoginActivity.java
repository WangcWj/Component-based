package cn.education.app_login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import cn.router.werouter.annotation.Router;

/**
 * @author WANG
 */

@Router(path = "native://LoginActivity")
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
