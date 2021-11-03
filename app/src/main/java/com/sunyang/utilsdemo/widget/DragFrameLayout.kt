package com.sunyang.utilsdemo.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.math.MathUtils
import androidx.customview.widget.ViewDragHelper
import com.sunyang.utilsdemo.widget.utils.UIUtils
import com.sunyang.utilsdemo.R
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

/**
 * @author  sunyang.888@bytedance.com
 * @date  2021/10/28
 */
class DragFrameLayout(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = -1) :
    CoordinatorLayout(context, attributes, defStyleAttr), IDragEventListener {
    companion object {
        const val STATE_IDLE = 0
        /**
         * The bottom sheet is dragging.
         */
        const val STATE_DRAGGING = 1

        /**
         * The bottom sheet is settling.
         */
        const val STATE_SETTLING = 2

        /**
         * The bottom sheet is expanded.
         */
        const val STATE_EXPANDED = 3

        /**
         * The bottom sheet is collapsed.
         */
        const val STATE_COLLAPSED = 4

        /**
         * The bottom sheet is hidden.
         */
        const val STATE_HIDDEN = 5
    }
    private var dragHelper: ViewDragHelper
    private var initY: Int = 0
    val subject = PublishSubject.create<DragEvent>()

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.text_layout, this, false) as FrameLayout
        dragHelper = ViewDragHelper.create(view, DragCallback())
        addView(view)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun computeScroll() {
        if (dragHelper.continueSettling(true)){
            invalidate()
        }
    }

    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean {
        return super.onStartNestedScroll(child, target, nestedScrollAxes)
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return super.onStartNestedScroll(child, target, axes, type)
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        super.onNestedPreScroll(target, dx, dy, consumed)
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(target, dx, dy, consumed, type)
    }

    override fun onNestedScrollAccepted(child: View, target: View, nestedScrollAxes: Int) {
        super.onNestedScrollAccepted(child, target, nestedScrollAxes)
    }

    override fun onNestedScrollAccepted(
        child: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ) {
        super.onNestedScrollAccepted(child, target, nestedScrollAxes, type)
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        return super.onNestedPreFling(target, velocityX, velocityY)
    }

    override fun onNestedFling(
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return super.onNestedFling(target, velocityX, velocityY, consumed)
    }

    override fun onStopNestedScroll(target: View) {
        super.onStopNestedScroll(target)
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        super.onStopNestedScroll(target, type)
    }

    inner class DragCallback: ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            initY = child.top
            return true
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return child.left
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return MathUtils.clamp(top, UIUtils.dip2Px(100f).toInt(), height)
        }

        override fun onViewDragStateChanged(state: Int) {
            if (state == STATE_DRAGGING) {

            }
        }

        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int
        ) {
            super.onViewPositionChanged(changedView, left, top, dx, dy)
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            val dy = releasedChild.top - initY
            if (dy > UIUtils.dip2Px(200f)) {
                subject.onNext(DragEvent.ON_CLOSE)
            } else {
                dragHelper.settleCapturedViewAt(releasedChild.left, initY)
                invalidate()
            }
        }
    }

    override fun onEvent(): Observable<DragEvent> = subject.hide()
}