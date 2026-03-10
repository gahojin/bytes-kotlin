package jp.co.gahojin.bytes

fun Short.toUByteArray(): UByteArray = UByteArray(2).also { it.putShort(0, this) }

fun Short.toUByteArrayLe(): UByteArray = UByteArray(2).also { it.putShortLe(0, this) }

fun Short.toUByteArray(dest: UByteArray, index: Int = 0) = dest.putShort(index, this)

fun Short.toUByteArrayLe(dest: UByteArray, index: Int = 0)= dest.putShortLe(index, this)

fun UShort.toUByteArray(): UByteArray = UByteArray(2).also { it.putShort(0, this) }

fun UShort.toUByteArrayLe(): UByteArray = UByteArray(2).also { it.putShortLe(0, this) }

fun UShort.toUByteArray(dest: UByteArray, index: Int = 0) = dest.putShort(index, this)

fun UShort.toUByteArrayLe(dest: UByteArray, index: Int = 0) = dest.putShortLe(index, this)

fun Int.toUByteArray(): UByteArray = UByteArray(4).also { it.putInt(0, this) }

fun Int.toUByteArray(length: Int): UByteArray = UByteArray(length).also { it.putInt(0, this, length) }

fun Int.toUByteArrayLe(): UByteArray = UByteArray(4).also { it.putIntLe(0, this) }

fun Int.toUByteArrayLe(length: Int): UByteArray = UByteArray(length).also { it.putIntLe(0, this, length) }

fun Int.toUByteArray(dest: UByteArray, index: Int = 0) = dest.putInt(index, this)

fun Int.toUByteArray(dest: UByteArray, index: Int = 0, length: Int) = dest.putInt(index, this, length)

fun Int.toUByteArrayLe(dest: UByteArray, index: Int = 0) = dest.putIntLe(index, this)

fun Int.toUByteArrayLe(dest: UByteArray, index: Int = 0, length: Int) = dest.putIntLe(index, this, length)

fun UInt.toUByteArray(): UByteArray = UByteArray(4).also { it.putInt(0, this) }

fun UInt.toUByteArray(length: Int = 4): UByteArray = UByteArray(length).also { it.putInt(0, this, length) }

fun UInt.toUByteArrayLe(): UByteArray = UByteArray(4).also { it.putIntLe(0, this) }

fun UInt.toUByteArrayLe(length: Int): UByteArray = UByteArray(length).also { it.putIntLe(0, this, length) }

fun UInt.toUByteArray(dest: UByteArray, index: Int = 0) = dest.putInt(index, this)

fun UInt.toUByteArray(dest: UByteArray, index: Int = 0, length: Int) = dest.putInt(index, this, length)

fun UInt.toUByteArrayLe(dest: UByteArray, index: Int = 0) = dest.putIntLe(index, this)

fun UInt.toUByteArrayLe(dest: UByteArray, index: Int = 0, length: Int) = dest.putIntLe(index, this, length)

fun Long.toUByteArray(): UByteArray = UByteArray(8).also { it.putLong(0, this) }

fun Long.toUByteArray(length: Int): UByteArray = UByteArray(length).also { it.putLong(0, this, length) }

fun Long.toUByteArrayLe(): UByteArray = UByteArray(8).also { it.putLongLe(0, this) }

fun Long.toUByteArrayLe(length: Int): UByteArray = UByteArray(length).also { it.putLongLe(0, this, length) }

fun Long.toUByteArray(dest: UByteArray, index: Int = 0) = dest.putLong(index, this)

fun Long.toUByteArray(dest: UByteArray, index: Int = 0, length: Int) = dest.putLong(index, this, length)

fun Long.toUByteArrayLe(dest: UByteArray, index: Int = 0) = dest.putLongLe(index, this)

fun Long.toUByteArrayLe(dest: UByteArray, index: Int = 0, length: Int) = dest.putLongLe(index, this, length)

fun ULong.toUByteArray(): UByteArray = UByteArray(8).also { it.putLong(0, this) }

fun ULong.toUByteArray(length: Int): UByteArray = UByteArray(length).also { it.putLong(0, this, length) }

fun ULong.toUByteArrayLe(): UByteArray = UByteArray(8).also { it.putLongLe(0, this) }

fun ULong.toUByteArrayLe(length: Int): UByteArray = UByteArray(length).also { it.putLongLe(0, this, length) }

fun ULong.toUByteArray(dest: UByteArray, index: Int = 0) = dest.putLong(index, this)

fun ULong.toUByteArray(dest: UByteArray, index: Int = 0, length: Int) = dest.putLong(index, this, length)

fun ULong.toUByteArrayLe(dest: UByteArray, index: Int = 0) = dest.putLongLe(index, this)

fun ULong.toUByteArrayLe(dest: UByteArray, index: Int = 0, length: Int) = dest.putLongLe(index, this, length)
