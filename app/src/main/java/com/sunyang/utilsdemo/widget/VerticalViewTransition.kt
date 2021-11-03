package com.sunyang.utilsdemo.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import com.sunyang.utilsdemo.widget.animator.EaseInInterpolator
import com.sunyang.utilsdemo.widget.transition.ITransition
import com.sunyang.utilsdemo.widget.transition.TransitionListener

/**
 * @author  sunyang.888@bytedance.com
 * @date  2021/11/2
 */
class VerticalViewTransition(private val target: View, private val duration: Long = DEFAULT_DURATION) {
    companion object {
        const val DEFAULT_DURATION = 300L
    }
    private var listener: TransitionListener? = null

    fun startTransition(transition: ITransition?) {
        listener?.onShowPre()
        transition?.onShowPre()
        target.alpha = 0f
        target.post {
            val end = target.measuredHeight
            listener?.onShowPre()
            transition?.onShowPre()
            val animator = ValueAnimator.ofFloat(0f, 1f)
            animator.interpolator = EaseInInterpolator()
            animator.duration = duration
            animator.addUpdateListener {
                val fraction = it.animatedFraction
                target.alpha = fraction
                val start = 0
                listener?.onShowing(fraction, start, end)
                transition?.onShowing(fraction, start, end)
                target.translationY = end + (start - end) * fraction
                listener?.onShowing(fraction, start, end)
                transition?.onShowing(fraction, start, end)
            }
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    listener?.onShowEnd()
                    transition?.onShowEnd()
                }
            })
            animator.start()
        }
    }

    fun endTransition(transition: ITransition?) {
        listener?.onHidePre()
        transition?.onHidePre()
        target.post {
            val end = target.measuredHeight
            listener?.onHidePre()
            transition?.onHidePre()
            val animator = ValueAnimator.ofFloat(0f, 1f)
            animator.interpolator = EaseInInterpolator()
            animator.duration = duration
            animator.addUpdateListener {
                val fraction = it.animatedFraction
                target.alpha = 1 - fraction
                val start = 0
                listener?.onHiding(fraction, start, end)
                transition?.onHiding(fraction, start, end)
                target.translationY = (end - start) * fraction
                listener?.onHiding(fraction, start, end)
                transition?.onHiding(fraction, start, end)
            }
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    listener?.onHideEnd()
                    transition?.onHideEnd()
                }
            })
            animator.start()
        }
    }

    fun setTransitionListener(listener: TransitionListener) {
        this.listener = listener
    }
}