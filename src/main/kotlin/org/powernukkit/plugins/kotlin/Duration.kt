package org.powernukkit.plugins.kotlin

import kotlin.math.ceil
import kotlin.time.*
import kotlin.time.Duration.Companion.milliseconds

@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Duration.inWholeTicks: Long get() = ceil(inWholeMilliseconds / 50.0).toLong()

@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Int.ticks: Duration get() = (50L * this).milliseconds

@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Long.ticks: Duration get() = (50.0 * this).milliseconds

/**
 * @throws IllegalArgumentException if this `Double` value is `NaN`.
 */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Double.ticks: Duration get() = (50.0 * this).milliseconds

/** Returns a [Duration] equal to this [Int] number of nanoseconds. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public inline val Int.nanoseconds: Duration get() = toDuration(DurationUnit.NANOSECONDS)

/** Returns a [Duration] equal to this [Long] number of nanoseconds. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Long.nanoseconds: Duration get() = toDuration(DurationUnit.NANOSECONDS)

/**
 * Returns a [Duration] equal to this [Double] number of nanoseconds.
 *
 * @throws IllegalArgumentException if this `Double` value is `NaN`.
 */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Double.nanoseconds: Duration get() = toDuration(DurationUnit.NANOSECONDS)

/** Returns a [Duration] equal to this [Int] number of microseconds. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Int.microseconds: Duration get() = toDuration(DurationUnit.MICROSECONDS)

/** Returns a [Duration] equal to this [Long] number of microseconds. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Long.microseconds: Duration get() = toDuration(DurationUnit.MICROSECONDS)

/**
 * Returns a [Duration] equal to this [Double] number of microseconds.
 *
 * @throws IllegalArgumentException if this `Double` value is `NaN`.
 */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Double.microseconds: Duration get() = toDuration(DurationUnit.MICROSECONDS)

/** Returns a [Duration] equal to this [Int] number of milliseconds. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Int.milliseconds: Duration get() = toDuration(DurationUnit.MILLISECONDS)

/** Returns a [Duration] equal to this [Long] number of milliseconds. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Long.milliseconds: Duration get() = toDuration(DurationUnit.MILLISECONDS)

/**
 * Returns a [Duration] equal to this [Double] number of milliseconds.
 *
 * @throws IllegalArgumentException if this `Double` value is `NaN`.
 */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Double.milliseconds: Duration get() = toDuration(DurationUnit.MILLISECONDS)

/** Returns a [Duration] equal to this [Int] number of seconds. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Int.seconds: Duration get() = toDuration(DurationUnit.SECONDS)

/** Returns a [Duration] equal to this [Long] number of seconds. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Long.seconds: Duration get() = toDuration(DurationUnit.SECONDS)

/**
 * Returns a [Duration] equal to this [Double] number of seconds.
 *
 * @throws IllegalArgumentException if this `Double` value is `NaN`.
 */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Double.seconds: Duration get() = toDuration(DurationUnit.SECONDS)

/** Returns a [Duration] equal to this [Int] number of minutes. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Int.minutes: Duration get() = toDuration(DurationUnit.MINUTES)

/** Returns a [Duration] equal to this [Long] number of minutes. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Long.minutes: Duration get() = toDuration(DurationUnit.MINUTES)

/**
 * Returns a [Duration] equal to this [Double] number of minutes.
 *
 * @throws IllegalArgumentException if this `Double` value is `NaN`.
 */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Double.minutes: Duration get() = toDuration(DurationUnit.MINUTES)

/** Returns a [Duration] equal to this [Int] number of hours. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Int.hours: Duration get() = toDuration(DurationUnit.HOURS)

/** Returns a [Duration] equal to this [Long] number of hours. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Long.hours: Duration get() = toDuration(DurationUnit.HOURS)

/**
 * Returns a [Duration] equal to this [Double] number of hours.
 *
 * @throws IllegalArgumentException if this `Double` value is `NaN`.
 */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Double.hours: Duration get() = toDuration(DurationUnit.HOURS)

/** Returns a [Duration] equal to this [Int] number of days. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Int.days: Duration get() = toDuration(DurationUnit.DAYS)

/** Returns a [Duration] equal to this [Long] number of days. */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Long.days: Duration get() = toDuration(DurationUnit.DAYS)

/**
 * Returns a [Duration] equal to this [Double] number of days.
 *
 * @throws IllegalArgumentException if this `Double` value is `NaN`.
 */
@ExperimentalTime
@ExperimentalPowerNukkitKotlinApi
public val Double.days: Duration get() = toDuration(DurationUnit.DAYS)
