@file:Suppress("NOTHING_TO_INLINE")

package jp.co.gahojin.bytes

import kotlin.math.abs

internal val EMPTY_ARRAY = ByteArray(0)

fun ByteArray.copyOf(newSize: Int, baseStrategy: ResizeStrategy): ByteArray {
    return when (newSize) {
        0 -> EMPTY_ARRAY
        size -> copyOf()
        else -> when (baseStrategy) {
            ResizeStrategy.ZERO_INDEX -> copyOf(newSize)
            ResizeStrategy.MAX_LENGTH -> {
                val currentSize = size
                val max = maxOf(0, abs(newSize - currentSize))
                ByteArray(newSize).also {
                    if (newSize > currentSize) {
                        System.arraycopy(this, 0, it, max, currentSize)
                    } else {
                        System.arraycopy(this, max, it, 0, newSize)
                    }
                }
            }
        }
    }
}

inline fun UByteArray.copyOf(newSize: Int, strategy: ResizeStrategy): UByteArray {
    return asByteArray().copyOf(newSize, strategy).asUByteArray()
}
