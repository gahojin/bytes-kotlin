@file:Suppress("NOTHING_TO_INLINE", "unused")

package jp.co.gahojin.bytes

inline infix fun Byte.shl(bitCount: Int): Int = toInt() and 0xff shl bitCount

inline infix fun Byte.shr(bitCount: Int): Int = toInt() and 0xff ushr bitCount

inline infix fun Byte.and(other: Int): Int = toInt() and other

inline infix fun Byte.and(other: Long): Long = toLong() and other

inline infix fun UByte.shl(bitCount: Int): UInt = toUInt() shl bitCount

inline infix fun UByte.shr(bitCount: Int): UInt = toUInt() shr bitCount
