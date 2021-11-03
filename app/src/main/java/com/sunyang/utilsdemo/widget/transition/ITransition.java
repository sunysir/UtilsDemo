package com.sunyang.utilsdemo.widget.transition;

import androidx.annotation.FloatRange;
import androidx.annotation.IntDef;

/**
 * Created by luowuxia
 * 2018/4/3
 */
public interface ITransition {

    int STATE_NONE = 0;
    int STATE_EXPANDED = 1;
    int STATE_HIDDEN = 2;
    int STATE_SETTLING = 3;

    @IntDef({ITransition.STATE_EXPANDED, ITransition.STATE_HIDDEN,
            ITransition.STATE_SETTLING, ITransition.STATE_NONE}) @interface State {
    }

    /**
     * Callback before the component display animation starts
     */
    void onShowPre();

    /**
     * During component display animation
     */
    void onShowing(@FloatRange(from = 0f, to = 1f) float franc, int start, int end);

    /**
     * End of component display animation
     */
    void onShowEnd();

    /**
     * Component hiding animation starts
     */
    void onHidePre();

    /**
     * Component hiding during animation
     */
    void onHiding(@FloatRange(from = 0f, to = 1f) float franc, int start, int end);

    /**
     * Component hiding animation ends
     */
    void onHideEnd();

}