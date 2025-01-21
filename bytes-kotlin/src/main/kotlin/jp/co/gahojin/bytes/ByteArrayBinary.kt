@file:Suppress("NOTHING_TO_INLINE")

package jp.co.gahojin.bytes

import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or
import kotlin.experimental.xor

fun ByteArray.inv(inPlace: Boolean = false): ByteArray {
    val target = if (inPlace) {
        this
    } else {
        ByteArray(size)
    }

    val n = size
    for (i in 0 until n) {
        target[i] = this[i].inv()
    }
    return target
}

inline infix fun ByteArray.and(second: ByteArray): ByteArray = and(second, false)

fun ByteArray.and(second: ByteArray, inPlace: Boolean = false): ByteArray {
    return bitWise(second, inPlace) { a, b -> a and b }
}

inline infix fun ByteArray.or(second: ByteArray): ByteArray = or(second, false)

fun ByteArray.or(second: ByteArray, inPlace: Boolean = false): ByteArray {
    return bitWise(second, inPlace) { a, b -> a or b }
}

inline infix fun ByteArray.xor(second: ByteArray): ByteArray = xor(second, false)

fun ByteArray.xor(second: ByteArray, inPlace: Boolean = false): ByteArray {
    return bitWise(second, inPlace) { a, b -> a xor b }
}

inline infix fun ByteArray.shl(bitCount: Int): ByteArray = shl(bitCount, false)

fun ByteArray.shl(bitCount: Int, inPlace: Boolean = false): ByteArray {
    val target = prepareTarget(inPlace)
    val shiftMod = bitCount % 8
    val carryMask = (0x1 shl shiftMod) - 1
    val offsetBytes = bitCount / 8

    var sourceIndex: Int
    for (i in indices) {
        sourceIndex = i + offsetBytes
        if (sourceIndex >= size) {
            target[i] = 0
        } else {
            val src = get(sourceIndex)
            var dst = src shl shiftMod
            if (sourceIndex < size - 1) {
                dst = dst or (get(sourceIndex + 1) shr (8 - shiftMod) and carryMask)
            }
            target[i] = dst.toByte()
        }
    }
    return target
}

inline infix fun ByteArray.shr(bitCount: Int): ByteArray = shr(bitCount, false)

fun ByteArray.shr(bitCount: Int, inPlace: Boolean = false): ByteArray {
    val target = prepareTarget(inPlace)
    val shiftMod = bitCount % 8
    val carryMask = 0xff shl (8 - shiftMod)
    val offsetBytes = bitCount / 8

    var sourceIndex: Int
    for (i in size - 1 downTo 0) {
        sourceIndex = i - offsetBytes
        if (sourceIndex < 0) {
            target[i] = 0
        } else {
            val src = get(sourceIndex)
            var dst = src shr shiftMod
            if (sourceIndex >= 1) {
                dst = dst or (get(sourceIndex - 1) shl (8 - shiftMod) and carryMask)
            }
            target[i] = dst.toByte()
        }
    }
    return target
}

inline fun ByteArray.getBit(bitIndex: Int): Boolean {
    val byteIndex = size - bitIndex / 8 - 1
    return this[byteIndex] shr (bitIndex % 8) and 0x01 != 0
}

inline fun ByteArray.getBitLe(bitIndex: Int): Boolean {
    val byteIndex = bitIndex / 8
    return this[byteIndex] shr (bitIndex % 8) and 0x01 != 0
}

inline fun ByteArray.switchBit(bitIndex: Int, value: Boolean, inPlace: Boolean = false): ByteArray {
    return switchBit(bitIndex.rangeTo(bitIndex), value, inPlace)
}

inline fun ByteArray.switchBitLe(bitIndex: Int, value: Boolean, inPlace: Boolean = false): ByteArray {
    return switchBitLe(bitIndex.rangeTo(bitIndex), value, inPlace)
}

fun ByteArray.switchBit(indexRange: IntProgression, value: Boolean, inPlace: Boolean = false): ByteArray {
    return if (value) {
        switchBit(indexRange, inPlace, ::getByteIndex) { bit, v -> v or bit }
    } else {
        switchBit(indexRange, inPlace, ::getByteIndex) { bit, v -> v and (bit.inv()) }
    }
}

fun ByteArray.switchBitLe(indexRange: IntProgression, value: Boolean, inPlace: Boolean = false): ByteArray {
    return if (value) {
        switchBit(indexRange, inPlace, ::getByteIndexLe) { bit, v -> v or bit }
    } else {
        switchBit(indexRange, inPlace, ::getByteIndexLe) { bit, v -> v and (bit.inv()) }
    }
}

fun ByteArray.flipBit(bitIndex: Int, inPlace: Boolean = false): ByteArray {
    return switchBit(bitIndex.rangeTo(bitIndex), inPlace, ::getByteIndex) { bit, v -> v xor bit }
}

fun ByteArray.flipBitLe(bitIndex: Int, inPlace: Boolean = false): ByteArray {
    return switchBit(bitIndex.rangeTo(bitIndex), inPlace, ::getByteIndexLe) { bit, v -> v xor bit }
}

fun ByteArray.flipBit(indexRange: IntProgression, inPlace: Boolean = false): ByteArray {
    return switchBit(indexRange, inPlace, ::getByteIndex) { bit, v -> v xor bit }
}

fun ByteArray.flipBitLe(indexRange: IntProgression, inPlace: Boolean = false): ByteArray {
    return switchBit(indexRange, inPlace, ::getByteIndexLe) { bit, v -> v xor bit }
}

private inline fun ByteArray.bitWise(second: ByteArray, inPlace: Boolean, block: (a: Byte, b: Byte) -> Byte): ByteArray {
    require(size == second.size) { "must match exactly one of ${second.size} bytes" }

    val target = prepareTarget(inPlace)
    val n = size
    for (i in 0 until n) {
        target[i] = block(this[i], second[i])
    }
    return target
}

private inline fun ByteArray.switchBit(
    indexRange: IntProgression,
    inPlace: Boolean,
    getByteIndex: (size: Int, index: Int) -> Int,
    block: (bit: Byte, value: Byte) -> Byte,
): ByteArray {
    val maxIndex = size shl 3
    require(indexRange.first in 0 until maxIndex) { "first index ${indexRange.first} out of bounds" }
    require(indexRange.last in 0 until maxIndex) { "last index ${indexRange.last} out of bounds" }

    val target = if (inPlace) this else copyOf()
    for (i in indexRange) {
        val byteIndex = getByteIndex(size, i)
        target[byteIndex] = block((1 shl (i % 8)).toByte(), target[byteIndex])
    }
    return target
}

private inline fun ByteArray.prepareTarget(inPlace: Boolean): ByteArray {
    return if (inPlace) this else ByteArray(size)
}

private inline fun getByteIndex(size: Int, index: Int): Int {
    return size - index / 8 - 1
}

@Suppress("UNUSED_PARAMETER")
private inline fun getByteIndexLe( size: Int, index: Int): Int {
    return index / 8
}
