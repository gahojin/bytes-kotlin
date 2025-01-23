@file:Suppress("NOTHING_TO_INLINE")

package jp.co.gahojin.bytes

inline fun UByteArray.getShort(index: Int): Short = asByteArray().getShort(index)

inline fun UByteArray.getShortLe(index: Int): Short = asByteArray().getShortLe(index)

inline fun UByteArray.getUShort(index: Int): UShort = asByteArray().getUShort(index)

inline fun UByteArray.getUShortLe(index: Int): UShort = asByteArray().getUShortLe(index)

inline fun UByteArray.getInt(index: Int): Int = asByteArray().getInt(index)

inline fun UByteArray.getIntLe(index: Int): Int = asByteArray().getIntLe(index)

inline fun UByteArray.getInt(index: Int, length: Int): Int = asByteArray().getInt(index, length)

inline fun UByteArray.getIntLe(index: Int, length: Int): Int = asByteArray().getIntLe(index, length)

inline fun UByteArray.getUInt(index: Int): UInt = asByteArray().getUInt(index)

inline fun UByteArray.getUIntLe(index: Int): UInt = asByteArray().getUIntLe(index)

inline fun UByteArray.getUInt(index: Int, length: Int): UInt = asByteArray().getUInt(index, length)

inline fun UByteArray.getUIntLe(index: Int, length: Int): UInt = asByteArray().getUIntLe(index, length)

inline fun UByteArray.getInt24(index: Int): Int = asByteArray().getInt24(index)

inline fun UByteArray.getInt24Le(index: Int): Int = asByteArray().getInt24Le(index)

inline fun UByteArray.getUInt24(index: Int): UInt = asByteArray().getUInt24(index)

inline fun UByteArray.getUInt24Le(index: Int): UInt = asByteArray().getUInt24Le(index)

inline fun UByteArray.getLong(index: Int): Long = asByteArray().getLong(index)

inline fun UByteArray.getLongLe(index: Int): Long = asByteArray().getLongLe(index)

inline fun UByteArray.getLong(index: Int, length: Int): Long = asByteArray().getLong(index, length)

inline fun UByteArray.getLongLe(index: Int, length: Int): Long = asByteArray().getLongLe(index, length)

inline fun UByteArray.getULong(index: Int): ULong = asByteArray().getULong(index)

inline fun UByteArray.getULongLe(index: Int): ULong = asByteArray().getULongLe(index)

inline fun UByteArray.getULong(index: Int, length: Int): ULong = asByteArray().getULong(index, length)

inline fun UByteArray.getULongLe(index: Int, length: Int): ULong = asByteArray().getULongLe(index, length)

inline fun UByteArray.putShort(index: Int, source: Short) = asByteArray().putShort(index, source)

inline fun UByteArray.putShortLe(index: Int, source: Short) = asByteArray().putShortLe(index, source)

inline fun UByteArray.putShort(index: Int, source: UShort) = asByteArray().putShort(index, source)

inline fun UByteArray.putShortLe(index: Int, source: UShort) = asByteArray().putShortLe(index, source)

inline fun UByteArray.putShort(index: Int, source: Int) = asByteArray().putShort(index, source)

inline fun UByteArray.putShortLe(index: Int, source: Int) = asByteArray().putShortLe(index, source)

inline fun UByteArray.putShort(index: Int, source: UInt) = asByteArray().putShort(index, source)

inline fun UByteArray.putShortLe(index: Int, source: UInt) = asByteArray().putShortLe(index, source)

inline fun UByteArray.putInt(index: Int, source: Int) = asByteArray().putInt(index, source)

inline fun UByteArray.putIntLe(index: Int, source: Int) = asByteArray().putIntLe(index, source)

inline fun UByteArray.putInt(index: Int, source: Int, length: Int) = asByteArray().putInt(index, source, length)

inline fun UByteArray.putIntLe(index: Int, source: Int, length: Int) = asByteArray().putIntLe(index, source, length)

inline fun UByteArray.putInt(index: Int, source: UInt) = asByteArray().putInt(index, source)

inline fun UByteArray.putIntLe(index: Int, source: UInt) = asByteArray().putIntLe(index, source)

inline fun UByteArray.putInt(index: Int, source: UInt, length: Int) = asByteArray().putInt(index, source, length)

inline fun UByteArray.putIntLe(index: Int, source: UInt, length: Int) = asByteArray().putIntLe(index, source, length)

inline fun UByteArray.putInt24(index: Int, source: Int) = asByteArray().putInt24(index, source)

inline fun UByteArray.putInt24Le(index: Int, source: Int) = asByteArray().putInt24Le(index, source)

inline fun UByteArray.putInt24(index: Int, source: UInt) = asByteArray().putInt24(index, source)

inline fun UByteArray.putInt24Le(index: Int, source: UInt) = asByteArray().putInt24Le(index, source)

inline fun UByteArray.putLong(index: Int, source: Long) = asByteArray().putLong(index, source)

inline fun UByteArray.putLongLe(index: Int, source: Long) = asByteArray().putLongLe(index, source)

inline fun UByteArray.putLong(index: Int, source: Long, length: Int) = asByteArray().putLong(index, source, length)

inline fun UByteArray.putLongLe(index: Int, source: Long, length: Int) = asByteArray().putLongLe(index, source, length)

inline fun UByteArray.putLong(index: Int, source: ULong) = asByteArray().putLong(index, source)

inline fun UByteArray.putLongLe(index: Int, source: ULong) = asByteArray().putLongLe(index, source)

inline fun UByteArray.putLong(index: Int, source: ULong, length: Int) = asByteArray().putLong(index, source, length)

inline fun UByteArray.putLongLe(index: Int, source: ULong, length: Int) = asByteArray().putLongLe(index, source, length)

inline fun UByteArray.put(index: Int, source: Bytes, fromIndex: Int = 0, toIndex: Int = source.size) {
    asByteArray().put(index, source, fromIndex, toIndex)
}
