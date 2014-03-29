package navi.droid.task;

import android.os.AsyncTask;
import navi.droid.NaviDroidMain;

public abstract class NaviAsyncTask<Params> extends AsyncTask<Params, Void, Void> {

    private final NaviDroidMain main;

    public NaviAsyncTask(NaviDroidMain main) {
        this.main = main;
    }

    public NaviDroidMain getMain() {
        return main;
    }

    @Override
    protected void onPreExecute() {
        main.showDialog(NaviDroidMain.PLEASE_WAIT_DIALOG);
    }

    @Override
    protected void onPostExecute(Void result) {
        main.removeDialog(NaviDroidMain.PLEASE_WAIT_DIALOG);
    }
}
