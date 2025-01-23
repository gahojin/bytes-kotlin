@file:Suppress("NOTHING_TO_INLINE")

package jp.co.gahojin.bytes

inline fun ByteArray.getShort(index: Int): Short {
    val s = (this[index] shl 8) or (this[index + 1] and 0xff)
    return s.toShort()
}

inline fun ByteArray.getShortLe(index: Int): Short {
    val s = (this[index] and 0xff) or (this[index + 1] shl 8)
    return s.toShort()
}

inline fun ByteArray.getUShort(index: Int): UShort = getShort(index).toUShort()

inline fun ByteArray.getUShortLe(index: Int): UShort = getShortLe(index).toUShort()

inline fun ByteArray.getInt(index: Int): Int {
    return (this[index] shl 24) or
            (this[index + 1] shl 16) or
            (this[index + 2] shl 8) or
            (this[index + 3] and 0xff)
}

inline fun ByteArray.getIntLe(index: Int): Int {
    return (this[index] and 0xff) or
            (this[index + 1] shl 8) or
            (this[index + 2] shl 16) or
            (this[index + 3] shl 24)
}

inline fun ByteArray.getInt(index: Int, length: Int): Int {
    return getUInt(index, length).unsignedToSigned(length shl 3)
}

inline fun ByteArray.getIntLe(index: Int, length: Int): Int {
    return getUIntLe(index, length).unsignedToSigned(length shl 3)
}

inline fun ByteArray.getUInt(index: Int): UInt = getInt(index).toUInt()

inline fun ByteArray.getUIntLe(index: Int): UInt = getIntLe(index).toUInt()

inline fun ByteArray.getUInt(index: Int, length: Int): UInt {
    require(length in 1..4) { "must be between 1 and 4: $length" }

    val maxShift = (length - 1) shl 3
    var offset = index
    var tmp = 0
    for (shift in maxShift downTo 8 step 8) {
        tmp = tmp or (this[offset++] shl shift)
    }
    tmp = tmp or (this[offset] and 0xff)
    return tmp.toUInt()
}

inline fun ByteArray.getUIntLe(index: Int, length: Int): UInt {
    require(length in 1..4) { "must be between 1 and 4: $length" }

    val maxShift = (length - 1) shl 3
    var offset = index
    var tmp = this[offset] and 0xff
    for (shift in 8 .. maxShift step 8) {
        tmp = tmp or (this[++offset] shl shift)
    }
    return tmp.toUInt()
}

inline fun ByteArray.getInt24(index: Int): Int {
    return getUInt24(index).unsignedToSigned(24)
}

inline fun ByteArray.getInt24Le(index: Int): Int {
    return getUInt24Le(index).unsignedToSigned(24)
}

inline fun ByteArray.getUInt24(index: Int): UInt {
    val t = (this[index] shl 16) or
            (this[index + 1] shl 8) or
            (this[index + 2] and 0xff)
    return t.toUInt()
}

inline fun ByteArray.getUInt24Le(index: Int): UInt {
    val t = (this[index] and 0xff) or
            (this[index + 1] shl 8) or
            (this[index + 2] shl 16)
    return t.toUInt()
}

inline fun ByteArray.getLong(index: Int): Long {
    return (this[index] and 0xffL shl 56) or
            (this[index + 1] and 0xffL shl 48) or
            (this[index + 2] and 0xffL shl 40) or
            (this[index + 3] and 0xffL shl 32) or
            (this[index + 4] and 0xffL shl 24) or
            (this[index + 5] and 0xffL shl 16) or
            (this[index + 6] and 0xffL shl 8) or
            (this[index + 7] and 0xffL)
}

inline fun ByteArray.getLongLe(index: Int): Long {
    return (this[index] and 0xffL) or
            (this[index + 1] and 0xffL shl 8) or
            (this[index + 2] and 0xffL shl 16) or
            (this[index + 3] and 0xffL shl 24) or
            (this[index + 4] and 0xffL shl 32) or
            (this[index + 5] and 0xffL shl 40) or
            (this[index + 6] and 0xffL shl 48) or
            (this[index + 7] and 0xffL shl 56)
}

inline fun ByteArray.getLong(index: Int, length: Int): Long {
    return getULong(index, length).unsignedToSigned(length shl 3)
}

inline fun ByteArray.getLongLe(index: Int, length: Int): Long {
    require(length in 1..8) { "must be between 1 and 8: $length" }

    val maxShift = (length - 1) shl 3
    var offset = index
    var tmp = this[offset] and 0xffL
    for (shift in 8 .. maxShift step 8) {
        tmp = tmp or (this[++offset] and 0xffL shl shift)
    }
    return tmp.unsignedToSigned(length shl 3)
}

inline fun ByteArray.getULong(index: Int): ULong = getLong(index).toULong()

inline fun ByteArray.getULongLe(index: Int): ULong = getLongLe(index).toULong()

inline fun ByteArray.getULong(index: Int, length: Int): ULong {
    require(length in 1..8) { "must be between 1 and 8: $length" }

    val maxShift = (length - 1) shl 3
    var offset = index
    var tmp = 0L
    for (shift in maxShift downTo 8 step 8) {
        tmp = tmp or (this[offset++] and 0xffL shl shift)
    }
    return (tmp or (this[offset] and 0xffL)).toULong()
}

inline fun ByteArray.getULongLe(index: Int, length: Int): ULong {
    require(length in 1..8) { "must be between 1 and 8: $length" }

    val maxShift = (length - 1) shl 3
    var offset = index
    var tmp = this[offset] and 0xffL
    for (shift in 8 .. maxShift step 8) {
        tmp = tmp or (this[++offset] and 0xffL shl shift)
    }
    return tmp.toULong()
}

inline fun ByteArray.putShort(index: Int, source: Short) = putShort(index, source.toInt())

inline fun ByteArray.putShortLe(index: Int, source: Short) = putShortLe(index, source.toInt())

inline fun ByteArray.putShort(index: Int, source: UShort) = putShort(index, source.toUInt())

inline fun ByteArray.putShortLe(index: Int, source: UShort) = putShortLe(index, source.toUInt())

inline fun ByteArray.putShort(index: Int, source: Int) {
    this[index] = (source ushr 8).toByte()
    this[index + 1] = source.toByte()
}

inline fun ByteArray.putShortLe(index: Int, source: Int) {
    this[index] = source.toByte()
    this[index + 1] = (source ushr 8).toByte()
}

inline fun ByteArray.putShort(index: Int, source: UInt) = putShort(index, source.toInt())

inline fun ByteArray.putShortLe(index: Int, source: UInt) = putShortLe(index, source.toInt())

inline fun ByteArray.putInt(index: Int, source: Int) {
    this[index] = (source ushr 24).toByte()
    this[index + 1] = (source ushr 16).toByte()
    this[index + 2] = (source ushr 8).toByte()
    this[index + 3] = source.toByte()
}

inline fun ByteArray.putIntLe(index: Int, source: Int) {
    this[index] = source.toByte()
    this[index + 1] = (source ushr 8).toByte()
    this[index + 2] = (source ushr 16).toByte()
    this[index + 3] = (source ushr 24).toByte()
}

inline fun ByteArray.putInt(index: Int, source: Int, length: Int) {
    require(length in 1..4) { "must be between 1 and 4: $length" }

    val maxShift = (length - 1) shl 3
    var offset = index
    for (shift in maxShift downTo 8 step 8) {
        this[offset++] = (source ushr shift).toByte()
    }
    this[offset] = source.toByte()
}

inline fun ByteArray.putIntLe(index: Int, source: Int, length: Int) {
    require(length in 1..4) { "must be between 1 and 4: $length" }

    val maxShift = (length - 1) shl 3
    var offset = index
    this[offset] = source.toByte()
    for (shift in 8 .. maxShift step 8) {
        this[++offset] = (source ushr shift).toByte()
    }
}

inline fun ByteArray.putInt(index: Int, source: UInt) = putInt(index, source.toInt())

inline fun ByteArray.putIntLe(index: Int, source: UInt) = putIntLe(index, source.toInt())

inline fun ByteArray.putInt(index: Int, source: UInt, length: Int) = putInt(index, source.toInt(), length)

inline fun ByteArray.putIntLe(index: Int, source: UInt, length: Int) = putIntLe(index, source.toInt(), length)

inline fun ByteArray.putInt24(index: Int, source: Int) {
    this[index] = (source ushr 16).toByte()
    this[index + 1] = (source ushr 8).toByte()
    this[index + 2] = source.toByte()
}

inline fun ByteArray.putInt24Le(index: Int, source: Int) {
    this[index] = source.toByte()
    this[index + 1] = (source ushr 8).toByte()
    this[index + 2] = (source ushr 16).toByte()
}

inline fun ByteArray.putInt24(index: Int, source: UInt) = putInt24(index, source.toInt())

inline fun ByteArray.putInt24Le(index: Int, source: UInt) = putInt24Le(index, source.toInt())

inline fun ByteArray.putLong(index: Int, source: Long) {
    this[index] = (source ushr 56).toByte()
    this[index + 1] = (source ushr 48).toByte()
    this[index + 2] = (source ushr 40).toByte()
    this[index + 3] = (source ushr 32).toByte()
    this[index + 4] = (source ushr 24).toByte()
    this[index + 5] = (source ushr 16).toByte()
    this[index + 6] = (source ushr 8).toByte()
    this[index + 7] = source.toByte()
}

inline fun ByteArray.putLongLe(index: Int, source: Long) {
    this[index] = source.toByte()
    this[index + 1] = (source ushr 8).toByte()
    this[index + 2] = (source ushr 16).toByte()
    this[index + 3] = (source ushr 24).toByte()
    this[index + 4] = (source ushr 32).toByte()
    this[index + 5] = (source ushr 40).toByte()
    this[index + 6] = (source ushr 48).toByte()
    this[index + 7] = (source ushr 56).toByte()
}

inline fun ByteArray.putLong(index: Int, source: Long, length: Int) {
    require(length in 1..8) { "must be between 1 and 8: $length" }

    val maxShift = (length - 1) shl 3
    var offset = index
    for (shift in maxShift downTo 8 step 8) {
        this[offset++] = (source ushr shift).toByte()
    }
    this[offset] = source.toByte()
}

inline fun ByteArray.putLongLe(index: Int, source: Long, length: Int) {
    require(length in 1..8) { "must be between 1 and 8: $length" }

    val maxShift = (length - 1) shl 3
    var offset = index
    this[offset] = source.toByte()
    for (shift in 8 .. maxShift step 8) {
        this[++offset] = (source ushr shift).toByte()
    }
}

inline fun ByteArray.putLong(index: Int, source: ULong) = putLong(index, source.toLong())

inline fun ByteArray.putLongLe(index: Int, source: ULong) = putLongLe(index, source.toLong())

inline fun ByteArray.putLong(index: Int, source: ULong, length: Int) = putLong(index, source.toLong(), length)

inline fun ByteArray.putLongLe(index: Int, source: ULong, length: Int) = putLongLe(index, source.toLong(), length)

inline fun ByteArray.put(index: Int, source: Bytes, fromIndex: Int = 0, toIndex: Int = source.size) {
    source.copyInto(this, index, fromIndex, toIndex)
}
