package org.powernukkit.plugins.kotlin

import cn.nukkit.plugin.PluginBase
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger as ApacheLogger
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.Marker

public class KotlinPluginLogger private constructor(
    private val apacheLogger: ApacheLogger,
    private val pluginName: String,
    private val delegate: Logger
): Logger by delegate {
    public constructor(plugin: PluginBase): this(
        pluginName = plugin.description.prefix?.takeIf { it.isNotBlank() } ?: plugin.description.name,
        apacheLogger = LogManager.getLogger(plugin.description.main),
        delegate = LoggerFactory.getLogger(plugin.description.main)
    )

    private fun String?.prefixed() = "[$pluginName] $this"

    public val isFatalEnabled: Boolean get() = apacheLogger.isFatalEnabled

    @ExperimentalPowerNukkitKotlinApi
    public fun isFatalEnabled(marker: ApacheMarker?): Boolean = apacheLogger.isFatalEnabled(marker)

    public fun fatal(msg: String?) {
        if (isFatalEnabled) {
            apacheLogger.fatal(msg.prefixed())
        }
    }

    public fun fatal(format: String?, arg: Any?) {
        if (isFatalEnabled) {
            apacheLogger.fatal(format.prefixed(), arg)
        }
    }

    public fun fatal(format: String?, arg1: Any?, arg2: Any?) {
        if (isFatalEnabled) {
            apacheLogger.fatal(format.prefixed(), arg1, arg2)
        }
    }

    public fun fatal(format: String?, vararg arguments: Any?) {
        if (isFatalEnabled) {
            apacheLogger.fatal(format.prefixed(), *arguments)
        }
    }

    public fun fatal(msg: String?, t: Throwable?) {
        if (isFatalEnabled) {
            apacheLogger.fatal(msg.prefixed(), t)
        }
    }

    @ExperimentalPowerNukkitKotlinApi
    public fun fatal(marker: ApacheMarker?, msg: String?) {
        if (isFatalEnabled) {
            apacheLogger.fatal(marker, msg.prefixed())
        }
    }

    @ExperimentalPowerNukkitKotlinApi
    public fun fatal(marker: ApacheMarker?, format: String?, arg: Any?) {
        if (isFatalEnabled) {
            apacheLogger.fatal(marker, format.prefixed(), arg)
        }
    }

    @ExperimentalPowerNukkitKotlinApi
    public fun fatal(marker: ApacheMarker?, format: String?, arg1: Any?, arg2: Any?) {
        if (isFatalEnabled) {
            apacheLogger.fatal(marker, format.prefixed(), arg1, arg2)
        }
    }

    @ExperimentalPowerNukkitKotlinApi
    public fun fatal(marker: ApacheMarker?, format: String?, vararg arguments: Any?) {
        if (isFatalEnabled) {
            apacheLogger.fatal(marker, format.prefixed(), *arguments)
        }
    }

    @ExperimentalPowerNukkitKotlinApi
    public fun fatal(marker: ApacheMarker?, msg: String?, t: Throwable?) {
        if (isFatalEnabled) {
            apacheLogger.fatal(marker, msg.prefixed(), t)
        }
    }

    override fun trace(msg: String?) {
        if (isTraceEnabled) {
            delegate.trace(msg.prefixed())
        }
    }

    override fun trace(format: String?, arg: Any?) {
        if (isTraceEnabled) {
            delegate.trace(format.prefixed())
        }
    }

    override fun trace(format: String?, arg1: Any?, arg2: Any?) {
        if (isTraceEnabled) {
            delegate.trace(format.prefixed())
        }
    }

    override fun trace(format: String?, vararg arguments: Any?) {
        if (isTraceEnabled) {
            delegate.trace(format.prefixed())
        }
    }

    override fun trace(msg: String?, t: Throwable?) {
        if (isTraceEnabled) {
            delegate.trace(msg.prefixed())
        }
    }

    override fun trace(marker: Marker?, msg: String?) {
        if (isTraceEnabled) {
            delegate.trace(msg.prefixed())
        }
    }

    override fun trace(marker: Marker?, format: String?, arg: Any?) {
        if (isTraceEnabled) {
            delegate.trace(format.prefixed())
        }
    }

    override fun trace(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) {
        if (isTraceEnabled) {
            delegate.trace(format.prefixed())
        }
    }

    override fun trace(marker: Marker?, format: String?, vararg argArray: Any?) {
        if (isTraceEnabled) {
            delegate.trace(format.prefixed())
        }
    }

    override fun trace(marker: Marker?, msg: String?, t: Throwable?) {
        if (isTraceEnabled) {
            delegate.trace(msg.prefixed())
        }
    }

    override fun debug(msg: String?) {
        if (isDebugEnabled) {
            delegate.debug(msg.prefixed())
        }
    }

    override fun debug(format: String?, arg: Any?) {
        if (isDebugEnabled) {
            delegate.debug(format.prefixed())
        }
    }

    override fun debug(format: String?, arg1: Any?, arg2: Any?) {
        if (isDebugEnabled) {
            delegate.debug(format.prefixed())
        }
    }

    override fun debug(format: String?, vararg arguments: Any?) {
        if (isDebugEnabled) {
            delegate.debug(format.prefixed())
        }
    }

    override fun debug(msg: String?, t: Throwable?) {
        if (isDebugEnabled) {
            delegate.debug(msg.prefixed())
        }
    }

    override fun debug(marker: Marker?, msg: String?) {
        if (isDebugEnabled) {
            delegate.debug(msg.prefixed())
        }
    }

    override fun debug(marker: Marker?, format: String?, arg: Any?) {
        if (isDebugEnabled) {
            delegate.debug(format.prefixed())
        }
    }

    override fun debug(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) {
        if (isDebugEnabled) {
            delegate.debug(format.prefixed())
        }
    }

    override fun debug(marker: Marker?, format: String?, vararg arguments: Any?) {
        if (isDebugEnabled) {
            delegate.debug(format.prefixed())
        }
    }

    override fun debug(marker: Marker?, msg: String?, t: Throwable?) {
        if (isDebugEnabled) {
            delegate.debug(msg.prefixed())
        }
    }

    override fun info(msg: String?) {
        if (isInfoEnabled) {
            delegate.info(msg.prefixed())
        }
    }

    override fun info(format: String?, arg: Any?) {
        if (isInfoEnabled) {
            delegate.info(format.prefixed())
        }
    }

    override fun info(format: String?, arg1: Any?, arg2: Any?) {
        if (isInfoEnabled) {
            delegate.info(format.prefixed())
        }
    }

    override fun info(format: String?, vararg arguments: Any?) {
        if (isInfoEnabled) {
            delegate.info(format.prefixed())
        }
    }

    override fun info(msg: String?, t: Throwable?) {
        if (isInfoEnabled) {
            delegate.info(msg.prefixed())
        }
    }

    override fun info(marker: Marker?, msg: String?) {
        if (isInfoEnabled) {
            delegate.info(msg.prefixed())
        }
    }

    override fun info(marker: Marker?, format: String?, arg: Any?) {
        if (isInfoEnabled) {
            delegate.info(format.prefixed())
        }
    }

    override fun info(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) {
        if (isInfoEnabled) {
            delegate.info(format.prefixed())
        }
    }

    override fun info(marker: Marker?, format: String?, vararg arguments: Any?) {
        if (isInfoEnabled) {
            delegate.info(format.prefixed())
        }
    }

    override fun info(marker: Marker?, msg: String?, t: Throwable?) {
        if (isInfoEnabled) {
            delegate.info(msg.prefixed())
        }
    }

    override fun warn(msg: String?) {
        if (isWarnEnabled) {
            delegate.warn(msg.prefixed())
        }
    }

    override fun warn(format: String?, arg: Any?) {
        if (isWarnEnabled) {
            delegate.warn(format.prefixed())
        }
    }

    override fun warn(format: String?, vararg arguments: Any?) {
        if (isWarnEnabled) {
            delegate.warn(format.prefixed())
        }
    }

    override fun warn(format: String?, arg1: Any?, arg2: Any?) {
        if (isWarnEnabled) {
            delegate.warn(format.prefixed())
        }
    }

    override fun warn(msg: String?, t: Throwable?) {
        if (isWarnEnabled) {
            delegate.warn(msg.prefixed())
        }
    }

    override fun warn(marker: Marker?, msg: String?) {
        if (isWarnEnabled) {
            delegate.warn(msg.prefixed())
        }
    }

    override fun warn(marker: Marker?, format: String?, arg: Any?) {
        if (isWarnEnabled) {
            delegate.warn(format.prefixed())
        }
    }

    override fun warn(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) {
        if (isWarnEnabled) {
            delegate.warn(format.prefixed())
        }
    }

    override fun warn(marker: Marker?, format: String?, vararg arguments: Any?) {
        if (isWarnEnabled) {
            delegate.warn(format.prefixed())
        }
    }

    override fun warn(marker: Marker?, msg: String?, t: Throwable?) {
        if (isWarnEnabled) {
            delegate.warn(msg.prefixed())
        }
    }

    override fun error(msg: String?) {
        if (isErrorEnabled) {
            delegate.error(msg.prefixed())
        }
    }

    override fun error(format: String?, arg: Any?) {
        if (isErrorEnabled) {
            delegate.error(format.prefixed())
        }
    }

    override fun error(format: String?, arg1: Any?, arg2: Any?) {
        if (isErrorEnabled) {
            delegate.error(format.prefixed())
        }
    }

    override fun error(format: String?, vararg arguments: Any?) {
        if (isErrorEnabled) {
            delegate.error(format.prefixed())
        }
    }

    override fun error(msg: String?, t: Throwable?) {
        if (isErrorEnabled) {
            delegate.error(msg.prefixed())
        }
    }

    override fun error(marker: Marker?, msg: String?) {
        if (isErrorEnabled) {
            delegate.error(msg.prefixed())
        }
    }

    override fun error(marker: Marker?, format: String?, arg: Any?) {
        if (isErrorEnabled) {
            delegate.error(format.prefixed())
        }
    }

    override fun error(marker: Marker?, format: String?, arg1: Any?, arg2: Any?) {
        if (isErrorEnabled) {
            delegate.error(format.prefixed())
        }
    }

    override fun error(marker: Marker?, format: String?, vararg arguments: Any?) {
        if (isErrorEnabled) {
            delegate.error(format.prefixed())
        }
    }

    override fun error(marker: Marker?, msg: String?, t: Throwable?) {
        if (isErrorEnabled) {
            delegate.error(msg.prefixed())
        }
    }
}
