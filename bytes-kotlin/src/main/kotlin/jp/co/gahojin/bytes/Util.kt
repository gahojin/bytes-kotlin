package jp.co.gahojin.bytes

infix fun Byte.shl(bitCount: Int): Int = toInt() and 0xff shl bitCount

infix fun Byte.shr(bitCount: Int): Int = toInt() and 0xff ushr bitCount

infix fun Byte.and(other: Int): Int = toInt() and other

infix fun Byte.and(other: Long): Long = toLong() and other

infix fun UByte.shl(bitCount: Int): UInt = toUInt() shl bitCount

infix fun UByte.shr(bitCount: Int): UInt = toUInt() shr bitCount

infix fun UByte.and(other: UInt): UInt = toUInt() and other

infix fun UByte.and(other: ULong): ULong = toULong() and other

fun Int.unsignedToSigned(size: Int): Int {
    val t = 1 shl (size - 1)
    return takeIf { this and t == 0 } ?: (-1 * (t - (this and t - 1)))
}

fun UInt.unsignedToSigned(size: Int): Int = toInt().unsignedToSigned(size)

fun Long.unsignedToSigned(size: Int): Long {
    val t = 1L shl (size - 1)
    return takeIf { this and t == 0L } ?: (-1 * (t - (this and t - 1L)))
}

fun ULong.unsignedToSigned(size: Int): Long = toLong().unsignedToSigned(size)
