package com.sunyang.utilsdemo.widget.transition;

import androidx.annotation.NonNull;

/**
 * Created by luowuxia
 * 2018/4/3
 *  <p>
 * Proactively initiate animation
 */

public interface InitiativeTransition extends ITransition {

    void startTransition(ITransition transition);

    void endTransition(ITransition transition);

    void setTransitionListener(@NonNull TransitionListener listener);
}