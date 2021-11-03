package com.sunyang.utilsdemo.widget.transition;

/**
 * Created by luowuxia
 * 2018/4/8
 */
public interface TransitionListener extends ITransition {

    class DefaultTransitionListener implements TransitionListener {

        @Override
        public void onShowPre() {

        }

        @Override
        public void onShowing(float franc, int start, int end) {

        }

        @Override
        public void onShowEnd() {

        }

        @Override
        public void onHidePre() {

        }

        @Override
        public void onHiding(float franc, int start, int end) {

        }

        @Override
        public void onHideEnd() {

        }
    }
}