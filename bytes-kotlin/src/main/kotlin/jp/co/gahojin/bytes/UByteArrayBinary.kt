@file:Suppress("NOTHING_TO_INLINE")

package jp.co.gahojin.bytes

inline fun UByteArray.inv(inPlace: Boolean = false): UByteArray {
    return asByteArray().inv(inPlace).asUByteArray()
}

inline infix fun UByteArray.and(second: UByteArray): UByteArray = and(second, false)

inline fun UByteArray.and(second: UByteArray, inPlace: Boolean = false): UByteArray {
    return asByteArray().and(second.asByteArray(), inPlace).asUByteArray()
}

inline infix fun UByteArray.or(second: UByteArray): UByteArray = or(second, false)

inline fun UByteArray.or(second: UByteArray, inPlace: Boolean = false): UByteArray {
    return asByteArray().or(second.asByteArray(), inPlace).asUByteArray()
}

inline infix fun UByteArray.xor(second: UByteArray): UByteArray = xor(second, false)

inline fun UByteArray.xor(second: UByteArray, inPlace: Boolean = false): UByteArray {
    return asByteArray().xor(second.asByteArray(), inPlace).asUByteArray()
}

inline infix fun UByteArray.shl(bitCount: Int): UByteArray = shl(bitCount, false)

inline fun UByteArray.shl(bitCount: Int, inPlace: Boolean = false): UByteArray {
    return asByteArray().shl(bitCount, inPlace).asUByteArray()
}

inline infix fun UByteArray.shr(bitCount: Int): UByteArray = shr(bitCount, false)

inline fun UByteArray.shr(bitCount: Int, inPlace: Boolean = false): UByteArray {
    return asByteArray().shr(bitCount, inPlace).asUByteArray()
}

inline fun UByteArray.getBit(bitIndex: Int): Boolean {
    return asByteArray().getBit(bitIndex)
}

inline fun UByteArray.getBitLe(bitIndex: Int): Boolean {
    return asByteArray().getBitLe(bitIndex)
}

inline fun UByteArray.switchBit(bitIndex: Int, value: Boolean, inPlace: Boolean = false): UByteArray {
    return asByteArray().switchBit(bitIndex, value, inPlace).asUByteArray()
}

inline fun UByteArray.switchBitLe(bitIndex: Int, value: Boolean, inPlace: Boolean = false): UByteArray {
    return asByteArray().switchBitLe(bitIndex, value, inPlace).asUByteArray()
}

inline fun UByteArray.switchBit(indexRange: IntProgression, value: Boolean, inPlace: Boolean = false): UByteArray {
    return asByteArray().switchBit(indexRange, value, inPlace).asUByteArray()
}

inline fun UByteArray.switchBitLe(indexRange: IntProgression, value: Boolean, inPlace: Boolean = false): UByteArray {
    return asByteArray().switchBitLe(indexRange, value, inPlace).asUByteArray()
}

inline fun UByteArray.flipBit(bitIndex: Int, inPlace: Boolean = false): UByteArray {
    return asByteArray().flipBit(bitIndex, inPlace).asUByteArray()
}

inline fun UByteArray.flipBitLe(bitIndex: Int, inPlace: Boolean = false): UByteArray {
    return asByteArray().flipBitLe(bitIndex, inPlace).asUByteArray()
}

inline fun UByteArray.flipBit(indexRange: IntProgression, inPlace: Boolean = false): UByteArray {
    return asByteArray().flipBit(indexRange, inPlace).asUByteArray()
}

inline fun UByteArray.flipBitLe(indexRange: IntProgression, inPlace: Boolean = false): UByteArray {
    return asByteArray().flipBitLe(indexRange, inPlace).asUByteArray()
}
