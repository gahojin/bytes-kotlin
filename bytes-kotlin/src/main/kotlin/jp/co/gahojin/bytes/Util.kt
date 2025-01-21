@file:Suppress("NOTHING_TO_INLINE", "unused")

package jp.co.gahojin.bytes

inline infix fun Byte.shl(bitCount: Int): Int = toInt() and 0xff shl bitCount

inline infix fun Byte.shr(bitCount: Int): Int = toInt() and 0xff ushr bitCount

inline infix fun Byte.and(other: Int): Int = toInt() and other

inline infix fun Byte.and(other: Long): Long = toLong() and other

inline infix fun UByte.shl(bitCount: Int): UInt = toUInt() shl bitCount

inline infix fun UByte.shr(bitCount: Int): UInt = toUInt() shr bitCount

inline infix fun UByte.and(other: UInt): UInt = toUInt() and other

inline infix fun UByte.and(other: ULong): ULong = toULong() and other

inline fun Int.unsignedToSigned(size: Int): Int {
    val t = 1 shl (size - 1)
    return takeIf { this and t == 0 } ?: (-1 * (t - (this and t - 1)))
}

inline fun Long.unsignedToSigned(size: Int): Long {
    val t = 1L shl (size - 1)
    return takeIf { this and t == 0L } ?: (-1 * (t - (this and t - 1L)))
}
