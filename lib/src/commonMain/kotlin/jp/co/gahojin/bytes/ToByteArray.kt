package jp.co.gahojin.bytes

fun Short.toByteArray(): ByteArray = ByteArray(2).also { it.putShort(0, this) }

fun Short.toByteArrayLe(): ByteArray = ByteArray(2).also { it.putShortLe(0, this) }

@JvmOverloads
fun Short.toByteArray(dest: ByteArray, index: Int = 0) = dest.putShort(index, this)

@JvmOverloads
fun Short.toByteArrayLe(dest: ByteArray, index: Int = 0) = dest.putShortLe(index, this)

fun UShort.toByteArray(): ByteArray = ByteArray(2).also { it.putShort(0, this) }

fun UShort.toByteArrayLe(): ByteArray = ByteArray(2).also { it.putShortLe(0, this) }

fun UShort.toByteArray(dest: ByteArray, index: Int = 0) = dest.putShort(index, this)

fun UShort.toByteArrayLe(dest: ByteArray, index: Int = 0) = dest.putShortLe(index, this)

fun Int.toByteArray(): ByteArray = ByteArray(4).also { it.putInt(0, this) }

fun Int.toByteArray(length: Int): ByteArray = ByteArray(length).also { it.putInt(0, this, length) }

fun Int.toByteArrayLe(): ByteArray = ByteArray(4).also { it.putIntLe(0, this) }

fun Int.toByteArrayLe(length: Int): ByteArray = ByteArray(length).also { it.putIntLe(0, this, length) }

@JvmOverloads
fun Int.toByteArray(dest: ByteArray, index: Int = 0) = dest.putInt(index, this)

fun Int.toByteArray(dest: ByteArray, index: Int = 0, length: Int) = dest.putInt(index, this, length)

@JvmOverloads
fun Int.toByteArrayLe(dest: ByteArray, index: Int = 0) = dest.putIntLe(index, this)

fun Int.toByteArrayLe(dest: ByteArray, index: Int = 0, length: Int) = dest.putIntLe(index, this, length)

fun UInt.toByteArray(): ByteArray = ByteArray(4).also { it.putInt(0, this) }

fun UInt.toByteArray(length: Int): ByteArray = ByteArray(length).also { it.putInt(0, this, length) }

fun UInt.toByteArrayLe(): ByteArray = ByteArray(4).also { it.putIntLe(0, this) }

fun UInt.toByteArrayLe(length: Int): ByteArray = ByteArray(length).also { it.putIntLe(0, this, length) }

fun UInt.toByteArray(dest: ByteArray, index: Int = 0) = dest.putInt(index, this)

fun UInt.toByteArray(dest: ByteArray, index: Int = 0, length: Int) = dest.putInt(index, this, length)

fun UInt.toByteArrayLe(dest: ByteArray, index: Int = 0) = dest.putIntLe(index, this)

fun UInt.toByteArrayLe(dest: ByteArray, index: Int = 0, length: Int) = dest.putIntLe(index, this, length)

fun Long.toByteArray(): ByteArray = ByteArray(8).also { it.putLong(0, this) }

fun Long.toByteArray(length: Int): ByteArray = ByteArray(length).also { it.putLong(0, this, length) }

fun Long.toByteArrayLe(): ByteArray = ByteArray(8).also { it.putLongLe(0, this) }

fun Long.toByteArrayLe(length: Int): ByteArray = ByteArray(length).also { it.putLongLe(0, this, length) }

@JvmOverloads
fun Long.toByteArray(dest: ByteArray, index: Int = 0) = dest.putLong(index, this)

fun Long.toByteArray(dest: ByteArray, index: Int = 0, length: Int) = dest.putLong(index, this, length)

@JvmOverloads
fun Long.toByteArrayLe(dest: ByteArray, index: Int = 0) = dest.putLongLe(index, this)

fun Long.toByteArrayLe(dest: ByteArray, index: Int = 0, length: Int) = dest.putLongLe(index, this, length)

fun ULong.toByteArray(): ByteArray = ByteArray(8).also { it.putLong(0, this) }

fun ULong.toByteArray(length: Int): ByteArray = ByteArray(length).also { it.putLong(0, this, length) }

fun ULong.toByteArrayLe(): ByteArray = ByteArray(8).also { it.putLongLe(0, this) }

fun ULong.toByteArrayLe(length: Int): ByteArray = ByteArray(length).also { it.putLongLe(0, this,length) }

fun ULong.toByteArray(dest: ByteArray, index: Int = 0) = dest.putLong(index, this)

fun ULong.toByteArray(dest: ByteArray, index: Int = 0, length: Int) = dest.putLong(index, this, length)

fun ULong.toByteArrayLe(dest: ByteArray, index: Int = 0) = dest.putLongLe(index, this)

fun ULong.toByteArrayLe(dest: ByteArray, index: Int = 0, length: Int) = dest.putLongLe(index, this, length)
