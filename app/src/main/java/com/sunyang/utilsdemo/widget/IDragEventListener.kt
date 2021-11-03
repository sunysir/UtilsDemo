package com.sunyang.utilsdemo.widget

import io.reactivex.rxjava3.core.Observable

/**
 * @author  sunyang.888@bytedance.com
 * @date  2021/11/3
 */
interface IDragEventListener {
    fun onEvent(): Observable<DragEvent>
}
enum class DragEvent {
    ON_DRAG,
    ON_CLOSE
}