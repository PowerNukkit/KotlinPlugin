@file:Suppress("NOTHING_TO_INLINE")

package org.powernukkit.plugins.kotlin

import com.github.michaelbull.logging.InlineLogger
import org.apache.logging.slf4j.Log4jMarker

public inline val InlineLogger.isFatalEnabled: Boolean
    get() = (delegate as? KotlinPluginLogger)?.isFatalEnabled ?: delegate.isErrorEnabled

@ExperimentalPowerNukkitKotlinApi
public inline fun InlineLogger.isFatalEnabled(marker: ApacheMarker?): Boolean {
    return (delegate as? KotlinPluginLogger)?.isFatalEnabled(marker) ?: delegate.isErrorEnabled(Log4jMarker(marker))
}

public inline fun InlineLogger.fatal(msg: () -> Any?) {
    val kpl = delegate as? KotlinPluginLogger ?: return error(msg)
    if (kpl.isFatalEnabled) {
        kpl.fatal(msg().toString())
    }
}

public inline fun InlineLogger.fatal(t: Throwable?, msg: () -> Any?) {
    val kpl = delegate as? KotlinPluginLogger ?: return error(t, msg)
    if (kpl.isFatalEnabled) {
        kpl.fatal(msg().toString(), t)
    }
}

@ExperimentalPowerNukkitKotlinApi
public inline fun InlineLogger.fatal(marker: ApacheMarker?, msg: () -> Any?) {
    val kpl = delegate as? KotlinPluginLogger ?: return error(Log4jMarker(marker), msg)
    if (kpl.isFatalEnabled(marker)) {
        kpl.fatal(marker, msg().toString())
    }
}

@ExperimentalPowerNukkitKotlinApi
public inline fun InlineLogger.fatal(marker: ApacheMarker?, t: Throwable?, msg: () -> Any?) {
    val kpl = delegate as? KotlinPluginLogger ?: return error(Log4jMarker(marker), t, msg)
    if (kpl.isFatalEnabled(marker)) {
        kpl.fatal(marker, msg().toString(), t)
    }
}
