package jp.co.gahojin.bytes

fun UByteArray.inv(inPlace: Boolean = false): UByteArray {
    return asByteArray().inv(inPlace).asUByteArray()
}

infix fun UByteArray.and(second: UByteArray): UByteArray = and(second, false)

fun UByteArray.and(second: UByteArray, inPlace: Boolean = false): UByteArray {
    return asByteArray().and(second.asByteArray(), inPlace).asUByteArray()
}

infix fun UByteArray.or(second: UByteArray): UByteArray = or(second, false)

fun UByteArray.or(second: UByteArray, inPlace: Boolean = false): UByteArray {
    return asByteArray().or(second.asByteArray(), inPlace).asUByteArray()
}

infix fun UByteArray.xor(second: UByteArray): UByteArray = xor(second, false)

fun UByteArray.xor(second: UByteArray, inPlace: Boolean = false): UByteArray {
    return asByteArray().xor(second.asByteArray(), inPlace).asUByteArray()
}

infix fun UByteArray.shl(bitCount: Int): UByteArray = shl(bitCount, false)

fun UByteArray.shl(bitCount: Int, inPlace: Boolean = false): UByteArray {
    return asByteArray().shl(bitCount, inPlace).asUByteArray()
}

infix fun UByteArray.shr(bitCount: Int): UByteArray = shr(bitCount, false)

fun UByteArray.shr(bitCount: Int, inPlace: Boolean = false): UByteArray {
    return asByteArray().shr(bitCount, inPlace).asUByteArray()
}

fun UByteArray.getBit(bitIndex: Int): Boolean = asByteArray().getBit(bitIndex)

fun UByteArray.getBitLe(bitIndex: Int): Boolean = asByteArray().getBitLe(bitIndex)

fun UByteArray.switchBit(bitIndex: Int, value: Boolean, inPlace: Boolean = false): UByteArray {
    return asByteArray().switchBit(bitIndex, value, inPlace).asUByteArray()
}

fun UByteArray.switchBitLe(bitIndex: Int, value: Boolean, inPlace: Boolean = false): UByteArray {
    return asByteArray().switchBitLe(bitIndex, value, inPlace).asUByteArray()
}

fun UByteArray.switchBit(indexRange: IntProgression, value: Boolean, inPlace: Boolean = false): UByteArray {
    return asByteArray().switchBit(indexRange, value, inPlace).asUByteArray()
}

fun UByteArray.switchBitLe(indexRange: IntProgression, value: Boolean, inPlace: Boolean = false): UByteArray {
    return asByteArray().switchBitLe(indexRange, value, inPlace).asUByteArray()
}

fun UByteArray.flipBit(bitIndex: Int, inPlace: Boolean = false): UByteArray {
    return asByteArray().flipBit(bitIndex, inPlace).asUByteArray()
}

fun UByteArray.flipBitLe(bitIndex: Int, inPlace: Boolean = false): UByteArray {
    return asByteArray().flipBitLe(bitIndex, inPlace).asUByteArray()
}

fun UByteArray.flipBit(indexRange: IntProgression, inPlace: Boolean = false): UByteArray {
    return asByteArray().flipBit(indexRange, inPlace).asUByteArray()
}

fun UByteArray.flipBitLe(indexRange: IntProgression, inPlace: Boolean = false): UByteArray {
    return asByteArray().flipBitLe(indexRange, inPlace).asUByteArray()
}
