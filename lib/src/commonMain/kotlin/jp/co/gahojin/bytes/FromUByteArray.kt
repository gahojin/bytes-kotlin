package jp.co.gahojin.bytes

fun UByteArray.getShort(index: Int): Short = asByteArray().getShort(index)

fun UByteArray.getShortLe(index: Int): Short = asByteArray().getShortLe(index)

fun UByteArray.getUShort(index: Int): UShort = asByteArray().getUShort(index)

fun UByteArray.getUShortLe(index: Int): UShort = asByteArray().getUShortLe(index)

fun UByteArray.getInt(index: Int): Int = asByteArray().getInt(index)

fun UByteArray.getIntLe(index: Int): Int = asByteArray().getIntLe(index)

fun UByteArray.getInt(index: Int, length: Int): Int = asByteArray().getInt(index, length)

fun UByteArray.getIntLe(index: Int, length: Int): Int = asByteArray().getIntLe(index, length)

fun UByteArray.getUInt(index: Int): UInt = asByteArray().getUInt(index)

fun UByteArray.getUIntLe(index: Int): UInt = asByteArray().getUIntLe(index)

fun UByteArray.getUInt(index: Int, length: Int): UInt = asByteArray().getUInt(index, length)

fun UByteArray.getUIntLe(index: Int, length: Int): UInt = asByteArray().getUIntLe(index, length)

fun UByteArray.getInt24(index: Int): Int = asByteArray().getInt24(index)

fun UByteArray.getInt24Le(index: Int): Int = asByteArray().getInt24Le(index)

fun UByteArray.getUInt24(index: Int): UInt = asByteArray().getUInt24(index)

fun UByteArray.getUInt24Le(index: Int): UInt = asByteArray().getUInt24Le(index)

fun UByteArray.getLong(index: Int): Long = asByteArray().getLong(index)

fun UByteArray.getLongLe(index: Int): Long = asByteArray().getLongLe(index)

fun UByteArray.getLong(index: Int, length: Int): Long = asByteArray().getLong(index, length)

fun UByteArray.getLongLe(index: Int, length: Int): Long = asByteArray().getLongLe(index, length)

fun UByteArray.getULong(index: Int): ULong = asByteArray().getULong(index)

fun UByteArray.getULongLe(index: Int): ULong = asByteArray().getULongLe(index)

fun UByteArray.getULong(index: Int, length: Int): ULong = asByteArray().getULong(index, length)

fun UByteArray.getULongLe(index: Int, length: Int): ULong = asByteArray().getULongLe(index, length)

fun UByteArray.putShort(index: Int, source: Short) = asByteArray().putShort(index, source)

fun UByteArray.putShortLe(index: Int, source: Short) = asByteArray().putShortLe(index, source)

fun UByteArray.putShort(index: Int, source: UShort) = asByteArray().putShort(index, source)

fun UByteArray.putShortLe(index: Int, source: UShort) = asByteArray().putShortLe(index, source)

fun UByteArray.putShort(index: Int, source: Int) = asByteArray().putShort(index, source)

fun UByteArray.putShortLe(index: Int, source: Int) = asByteArray().putShortLe(index, source)

fun UByteArray.putShort(index: Int, source: UInt) = asByteArray().putShort(index, source)

fun UByteArray.putShortLe(index: Int, source: UInt) = asByteArray().putShortLe(index, source)

fun UByteArray.putInt(index: Int, source: Int) = asByteArray().putInt(index, source)

fun UByteArray.putIntLe(index: Int, source: Int) = asByteArray().putIntLe(index, source)

fun UByteArray.putInt(index: Int, source: Int, length: Int) = asByteArray().putInt(index, source, length)

fun UByteArray.putIntLe(index: Int, source: Int, length: Int) = asByteArray().putIntLe(index, source, length)

fun UByteArray.putInt(index: Int, source: UInt) = asByteArray().putInt(index, source)

fun UByteArray.putIntLe(index: Int, source: UInt) = asByteArray().putIntLe(index, source)

fun UByteArray.putInt(index: Int, source: UInt, length: Int) = asByteArray().putInt(index, source, length)

fun UByteArray.putIntLe(index: Int, source: UInt, length: Int) = asByteArray().putIntLe(index, source, length)

fun UByteArray.putInt24(index: Int, source: Int) = asByteArray().putInt24(index, source)

fun UByteArray.putInt24Le(index: Int, source: Int) = asByteArray().putInt24Le(index, source)

fun UByteArray.putInt24(index: Int, source: UInt) = asByteArray().putInt24(index, source)

fun UByteArray.putInt24Le(index: Int, source: UInt) = asByteArray().putInt24Le(index, source)

fun UByteArray.putLong(index: Int, source: Long) = asByteArray().putLong(index, source)

fun UByteArray.putLongLe(index: Int, source: Long) = asByteArray().putLongLe(index, source)

fun UByteArray.putLong(index: Int, source: Long, length: Int) = asByteArray().putLong(index, source, length)

fun UByteArray.putLongLe(index: Int, source: Long, length: Int) = asByteArray().putLongLe(index, source, length)

fun UByteArray.putLong(index: Int, source: ULong) = asByteArray().putLong(index, source)

fun UByteArray.putLongLe(index: Int, source: ULong) = asByteArray().putLongLe(index, source)

fun UByteArray.putLong(index: Int, source: ULong, length: Int) = asByteArray().putLong(index, source, length)

fun UByteArray.putLongLe(index: Int, source: ULong, length: Int) = asByteArray().putLongLe(index, source, length)

fun UByteArray.put(index: Int, source: Bytes, fromIndex: Int = 0, toIndex: Int = source.size) {
    asByteArray().put(index, source, fromIndex, toIndex)
}
