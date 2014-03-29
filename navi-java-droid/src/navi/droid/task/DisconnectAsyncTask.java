package navi.droid.task;

import navi.droid.NaviDroidHandler;
import navi.droid.NaviDroidMain;

import java.io.IOException;

public class DisconnectAsyncTask extends NaviAsyncTask<Void> {

    public DisconnectAsyncTask(NaviDroidMain main) {
        super(main);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            NaviDroidHandler.getNetwork().disconnect();
            getMain().disconnected();
        } catch (IOException e) {
            getMain().notDisconnected();
        }
        return null;
    }
}
