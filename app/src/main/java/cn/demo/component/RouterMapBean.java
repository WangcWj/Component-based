package cn.demo.component;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/2/14
 */
public class RouterMapBean {

    /**
     * StartActivity : native://StartActivity
     * MarketActivity : native://MarketActivity
     * MainOutPutImp : native://provider/MainOutPutImp
     * LoginActivity : native://LoginActivity
     * AdActivity : native://AdActivity
     * LoginOutPutImp : native://provider/LoginOutPutImp
     */

    private String StartActivity;
    private String MarketActivity;
    private String MainOutPutImp;
    private String LoginActivity;
    private String AdActivity;
    private String LoginOutPutImp;

    public String getStartActivity() {
        return StartActivity;
    }

    public void setStartActivity(String StartActivity) {
        this.StartActivity = StartActivity;
    }

    public String getMarketActivity() {
        return MarketActivity;
    }

    public void setMarketActivity(String MarketActivity) {
        this.MarketActivity = MarketActivity;
    }

    public String getMainOutPutImp() {
        return MainOutPutImp;
    }

    public void setMainOutPutImp(String MainOutPutImp) {
        this.MainOutPutImp = MainOutPutImp;
    }

    public String getLoginActivity() {
        return LoginActivity;
    }

    public void setLoginActivity(String LoginActivity) {
        this.LoginActivity = LoginActivity;
    }

    public String getAdActivity() {
        return AdActivity;
    }

    public void setAdActivity(String AdActivity) {
        this.AdActivity = AdActivity;
    }

    public String getLoginOutPutImp() {
        return LoginOutPutImp;
    }

    public void setLoginOutPutImp(String LoginOutPutImp) {
        this.LoginOutPutImp = LoginOutPutImp;
    }
}
