package com.sunyang.utilsdemo.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.bytedance.scene.Scene
import com.sunyang.utilsdemo.R
import com.sunyang.utilsdemo.widget.DragEvent
import com.sunyang.utilsdemo.widget.DragFrameLayout
import com.sunyang.utilsdemo.widget.VerticalViewTransition
import com.sunyang.utilsdemo.widget.transition.EmptyTransition

/**
 * @author  sunyang.888@bytedance.com
 * @date  2021/11/1
 */
class MainScene : Scene(), View.OnClickListener {
    private var panelLayout: DragFrameLayout? = null
    private var verticalViewTransition: VerticalViewTransition? = null
    private var rootView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedInstanceState: Bundle?
    ): View {
        rootView = LayoutInflater.from(requireActivity())
            .inflate(R.layout.activity_main, container, false)
        return rootView!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val panelBtn = findViewById<Button>(R.id.panel_bt)
        panelBtn?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.panel_bt -> {
                if (panelLayout == null) {
                    panelLayout = DragFrameLayout(requireActivity())
                    panelLayout?.onEvent()?.subscribe {
                        if (it == DragEvent.ON_CLOSE) {
                            verticalViewTransition?.endTransition(EmptyTransition())
                        }
                    }
                    val params = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    (rootView as? ViewGroup)?.addView(panelLayout, params)
                    val touchOutside = findViewById<FrameLayout>(R.id.touch_outside)
                    touchOutside?.setOnClickListener(this)
                    verticalViewTransition = VerticalViewTransition(panelLayout as View)
                }
                verticalViewTransition?.startTransition(EmptyTransition())
            }
            R.id.touch_outside -> {
                verticalViewTransition?.endTransition(EmptyTransition())
            }
        }
    }
}