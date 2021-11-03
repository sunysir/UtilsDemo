package com.sunyang.utilsdemo

import com.bytedance.scene.Scene
import com.bytedance.scene.ui.SceneActivity
import com.sunyang.utilsdemo.main.MainScene

class MainActivity : SceneActivity() {
    override fun getHomeSceneClass(): Class<out Scene> {
        return MainScene::class.java
    }

    override fun supportRestore(): Boolean {
        return false
    }
}