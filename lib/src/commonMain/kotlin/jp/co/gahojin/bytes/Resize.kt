package jp.co.gahojin.bytes

import kotlin.math.abs

internal val EMPTY_ARRAY = ByteArray(0)

fun ByteArray.copyOf(newSize: Int, baseStrategy: ResizeStrategy): ByteArray {
    require(newSize >= 0)

    return when (newSize) {
        0 -> EMPTY_ARRAY
        size -> copyOf()
        else -> when (baseStrategy) {
            ResizeStrategy.ZERO_INDEX -> copyOf(newSize)
            ResizeStrategy.MAX_LENGTH -> {
                val currentSize = size
                val max = abs(newSize - currentSize)
                ByteArray(newSize).also {
                    if (newSize > currentSize) {
                        copyInto(it, max, 0, currentSize)
                    } else {
                        copyInto(it, 0, max, max + newSize)
                    }
                }
            }
        }
    }
}

fun UByteArray.copyOf(newSize: Int, strategy: ResizeStrategy): UByteArray {
    return asByteArray().copyOf(newSize, strategy).asUByteArray()
}
