package com.github.opentypefont.sample.plugin

import io.github.monun.kommand.kommand
import org.bukkit.plugin.java.JavaPlugin

class SamplePlugin : JavaPlugin() {
    override fun onEnable() {
        logger.info("Plugin Enabled")

//        kommand {
//            register("sample") {
//                executes {
//                    it.source.player.sendMessage("Sample command")
//                }
//            }
//        }
    }
}