package cn.component.login;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import cn.router.api.router.WeRouter;
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
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeRouter.getInstance().build("native://AdActivity").navigation(LoginActivity.this);
            }
        });
    }

}
