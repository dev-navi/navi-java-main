package navi.main.agent;

import navi.main.NaviListener;
import navi.main.dto.Visibility;

public interface VisibilityListener extends NaviListener {

    public void notifyVisibilityReceived(Visibility visibility);
}
