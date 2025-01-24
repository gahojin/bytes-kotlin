@file:Suppress("NOTHING_TO_INLINE")

package jp.co.gahojin.bytes

inline fun Short.toByteArray(): ByteArray = ByteArray(2).also { it.putShort(0, this) }

inline fun Short.toByteArrayLe(): ByteArray = ByteArray(2).also { it.putShortLe(0, this) }

@JvmOverloads
inline fun Short.toByteArray(dest: ByteArray, index: Int = 0) = dest.putShort(index, this)

@JvmOverloads
inline fun Short.toByteArrayLe(dest: ByteArray, index: Int = 0) = dest.putShortLe(index, this)

inline fun UShort.toByteArray(): ByteArray = ByteArray(2).also { it.putShort(0, this) }

inline fun UShort.toByteArrayLe(): ByteArray = ByteArray(2).also { it.putShortLe(0, this) }

inline fun UShort.toByteArray(dest: ByteArray, index: Int = 0) = dest.putShort(index, this)

inline fun UShort.toByteArrayLe(dest: ByteArray, index: Int = 0) = dest.putShortLe(index, this)

inline fun Int.toByteArray(): ByteArray = ByteArray(4).also { it.putInt(0, this) }

inline fun Int.toByteArray(length: Int): ByteArray = ByteArray(length).also { it.putInt(0, this, length) }

inline fun Int.toByteArrayLe(): ByteArray = ByteArray(4).also { it.putIntLe(0, this) }

inline fun Int.toByteArrayLe(length: Int): ByteArray = ByteArray(length).also { it.putIntLe(0, this, length) }

@JvmOverloads
inline fun Int.toByteArray(dest: ByteArray, index: Int = 0) = dest.putInt(index, this)

inline fun Int.toByteArray(dest: ByteArray, index: Int = 0, length: Int) = dest.putInt(index, this, length)

@JvmOverloads
inline fun Int.toByteArrayLe(dest: ByteArray, index: Int = 0) = dest.putIntLe(index, this)

inline fun Int.toByteArrayLe(dest: ByteArray, index: Int = 0, length: Int) = dest.putIntLe(index, this, length)

inline fun UInt.toByteArray(): ByteArray = ByteArray(4).also { it.putInt(0, this) }

inline fun UInt.toByteArray(length: Int): ByteArray = ByteArray(length).also { it.putInt(0, this, length) }

inline fun UInt.toByteArrayLe(): ByteArray = ByteArray(4).also { it.putIntLe(0, this) }

inline fun UInt.toByteArrayLe(length: Int): ByteArray = ByteArray(length).also { it.putIntLe(0, this, length) }

inline fun UInt.toByteArray(dest: ByteArray, index: Int = 0) = dest.putInt(index, this)

inline fun UInt.toByteArray(dest: ByteArray, index: Int = 0, length: Int) = dest.putInt(index, this, length)

inline fun UInt.toByteArrayLe(dest: ByteArray, index: Int = 0) = dest.putIntLe(index, this)

inline fun UInt.toByteArrayLe(dest: ByteArray, index: Int = 0, length: Int) = dest.putIntLe(index, this, length)

inline fun Long.toByteArray(): ByteArray = ByteArray(8).also { it.putLong(0, this) }

inline fun Long.toByteArray(length: Int): ByteArray = ByteArray(length).also { it.putLong(0, this, length) }

inline fun Long.toByteArrayLe(): ByteArray = ByteArray(8).also { it.putLongLe(0, this) }

inline fun Long.toByteArrayLe(length: Int): ByteArray = ByteArray(length).also { it.putLongLe(0, this, length) }

@JvmOverloads
inline fun Long.toByteArray(dest: ByteArray, index: Int = 0) = dest.putLong(index, this)

inline fun Long.toByteArray(dest: ByteArray, index: Int = 0, length: Int) = dest.putLong(index, this, length)

@JvmOverloads
inline fun Long.toByteArrayLe(dest: ByteArray, index: Int = 0) = dest.putLongLe(index, this)

inline fun Long.toByteArrayLe(dest: ByteArray, index: Int = 0, length: Int) = dest.putLongLe(index, this, length)

inline fun ULong.toByteArray(): ByteArray = ByteArray(8).also { it.putLong(0, this) }

inline fun ULong.toByteArray(length: Int): ByteArray = ByteArray(length).also { it.putLong(0, this, length) }

inline fun ULong.toByteArrayLe(): ByteArray = ByteArray(8).also { it.putLongLe(0, this) }

inline fun ULong.toByteArrayLe(length: Int): ByteArray = ByteArray(length).also { it.putLongLe(0, this,length) }

inline fun ULong.toByteArray(dest: ByteArray, index: Int = 0) = dest.putLong(index, this)

inline fun ULong.toByteArray(dest: ByteArray, index: Int = 0, length: Int) = dest.putLong(index, this, length)

inline fun ULong.toByteArrayLe(dest: ByteArray, index: Int = 0) = dest.putLongLe(index, this)

inline fun ULong.toByteArrayLe(dest: ByteArray, index: Int = 0, length: Int) = dest.putLongLe(index, this, length)
