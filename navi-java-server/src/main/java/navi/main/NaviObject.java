package navi.main;

public abstract class NaviObject<Listener extends NaviListener> {

    private volatile Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    protected Listener getListener() {
        return listener;
    }

    protected boolean hasListener() {
        return (listener != null);
    }
}
