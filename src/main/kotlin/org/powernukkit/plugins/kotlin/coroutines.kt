package org.powernukkit.plugins.kotlin

import cn.nukkit.Server
import cn.nukkit.plugin.Plugin
import kotlinx.coroutines.*
import java.lang.Runnable
import java.util.*
import java.util.concurrent.Executor
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

private val pluginExecutors = WeakHashMap<Plugin, CoroutineDispatcher>()

internal class SyncPluginExecutor(private val plugin: Plugin): Executor {
    override fun execute(runnable: Runnable) {
        Server.getInstance().scheduler.scheduleTask(plugin, runnable)
    }
}

@ExperimentalPowerNukkitKotlinApi
@Suppress("unused")
public fun Dispatchers.sync(plugin: Plugin): CoroutineDispatcher {
    if (plugin is KotlinPluginBase) {
        return plugin.syncDispatcher
    }

    return synchronized(pluginExecutors) {
        pluginExecutors.compute(plugin) { key, current ->
            current ?: SyncPluginExecutor(key).asCoroutineDispatcher()
        }!!
    }
}

@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public suspend inline fun <R> runInMain(plugin: Plugin, delay: Duration, crossinline block: () -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return Unit.runInMain(plugin, delay) {
        block()
    }
}

@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public suspend inline fun <T, R> T.runInMain(plugin: Plugin, delay: Duration, crossinline block: suspend T.() -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val ticks = delay.inWholeTicks.toInt()
    require(ticks >= 0)
    if (ticks == 0) {
        return runInMain(plugin, block)
    }

    // Await N-1 ticks without blocking the main thread, making use of the scheduler.
    suspendCancellableCoroutine<Unit> { continuation ->
        val task = plugin.server.scheduler.scheduleDelayedTask(plugin, {
            try {
                continuation.resume(Unit)
            } catch (e: Throwable) {
                continuation.resumeWithException(e)
            }
        }, ticks - 1)
        continuation.invokeOnCancellation { task.cancel() }
        CoroutineScope(continuation.context).launch {
            delay(delay)
            while (true) {
                if (continuation.isCompleted) {
                    break
                }
                if (task.isCancelled) {
                    continuation.cancel(CancellationException("The TaskHandler was cancelled"))
                    break
                }
                delay(0.5.ticks)
            }
        }
    }

    return runInMain(plugin, block)
}

@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public suspend inline fun <T, R> withInMain(plugin: Plugin, delay: Duration, receiver: T, crossinline block: suspend T.() -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return receiver.runInMain(plugin, delay, block)
}

@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public suspend inline fun <T> T.applyInMain(plugin: Plugin, delay: Duration, crossinline block: suspend T.() -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    runInMain(plugin, delay, block)
    return this
}

@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public suspend inline fun <T> T.alsoInMain(plugin: Plugin, delay: Duration, crossinline block: suspend (T) -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    runInMain(plugin, delay, block)
    return this
}

@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public suspend inline fun <T, R> T.letInMain(plugin: Plugin, delay: Duration, crossinline block: suspend (T) -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return runInMain(plugin, delay, block)
}

@ExperimentalPowerNukkitKotlinApi
public suspend inline fun <R> runInMain(plugin: Plugin, crossinline block: suspend () -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withContext(Dispatchers.sync(plugin)) {
        block()
    }
}

@ExperimentalPowerNukkitKotlinApi
public suspend inline fun <T, R> T.runInMain(plugin: Plugin, crossinline block: suspend T.() -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withContext(Dispatchers.sync(plugin)) {
        block()
    }
}

@ExperimentalPowerNukkitKotlinApi
public suspend inline fun <T, R> withInMain(plugin: Plugin, receiver: T, crossinline block: suspend T.() -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withContext(Dispatchers.sync(plugin)) {
        receiver.block()
    }
}

@ExperimentalPowerNukkitKotlinApi
public suspend inline fun <T> T.applyInMain(plugin: Plugin, crossinline block: suspend T.() -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    withContext(Dispatchers.sync(plugin)) {
        block()
    }
    return this
}

@ExperimentalPowerNukkitKotlinApi
public suspend inline fun <T> T.alsoInMain(plugin: Plugin, crossinline block: suspend (T) -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    withContext(Dispatchers.sync(plugin)) {
        block(this@alsoInMain)
    }
    return this
}

@ExperimentalPowerNukkitKotlinApi
public suspend inline fun <T, R> T.letInMain(plugin: Plugin, crossinline block: suspend (T) -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withContext(Dispatchers.sync(plugin)) {
        block(this@letInMain)
    }
}
