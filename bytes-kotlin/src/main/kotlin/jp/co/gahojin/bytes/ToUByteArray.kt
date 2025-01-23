@file:Suppress("NOTHING_TO_INLINE")

package jp.co.gahojin.bytes

inline fun Short.toUByteArray(): UByteArray = UByteArray(2).also { it.putShort(0, this) }

inline fun Short.toUByteArrayLe(): UByteArray = UByteArray(2).also { it.putShortLe(0, this) }

inline fun Short.toUByteArray(dest: UByteArray, index: Int = 0) = dest.putShort(index, this)

inline fun Short.toUByteArrayLe(dest: UByteArray, index: Int = 0)= dest.putShortLe(index, this)

inline fun UShort.toUByteArray(): UByteArray = UByteArray(2).also { it.putShort(0, this) }

inline fun UShort.toUByteArrayLe(): UByteArray = UByteArray(2).also { it.putShortLe(0, this) }

inline fun UShort.toUByteArray(dest: UByteArray, index: Int = 0) = dest.putShort(index, this)

inline fun UShort.toUByteArrayLe(dest: UByteArray, index: Int = 0) = dest.putShortLe(index, this)

inline fun Int.toUByteArray(): UByteArray = UByteArray(4).also { it.putInt(0, this) }

inline fun Int.toUByteArrayLe(): UByteArray = UByteArray(4).also { it.putIntLe(0, this) }

inline fun Int.toUByteArray(dest: UByteArray, index: Int = 0) = dest.putInt(index, this)

inline fun Int.toUByteArrayLe(dest: UByteArray, index: Int = 0) = dest.putIntLe(index, this)

inline fun UInt.toUByteArray(): UByteArray = UByteArray(4).also { it.putInt(0, this) }

inline fun UInt.toUByteArrayLe(): UByteArray = UByteArray(4).also { it.putIntLe(0, this) }

inline fun UInt.toUByteArray(dest: UByteArray, index: Int = 0) = dest.putInt(index, this)

inline fun UInt.toUByteArrayLe(dest: UByteArray, index: Int = 0) = dest.putIntLe(index, this)

inline fun Long.toUByteArray(): UByteArray = UByteArray(8).also { it.putLong(0, this) }

inline fun Long.toUByteArrayLe(): UByteArray = UByteArray(8).also { it.putLongLe(0, this) }

inline fun Long.toUByteArray(dest: UByteArray, index: Int = 0) = dest.putLong(index, this)

inline fun Long.toUByteArrayLe(dest: UByteArray, index: Int = 0) = dest.putLongLe(index, this)

inline fun ULong.toUByteArray(): UByteArray = UByteArray(8).also { it.putLong(0, this) }

inline fun ULong.toUByteArrayLe(): UByteArray = UByteArray(8).also { it.putLongLe(0, this) }

inline fun ULong.toUByteArray(dest: UByteArray, index: Int = 0) = dest.putLong(index, this)

inline fun ULong.toUByteArrayLe(dest: UByteArray, index: Int = 0) = dest.putLongLe(index, this)
