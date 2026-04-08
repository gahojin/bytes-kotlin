package jp.co.gahojin.bytes

import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

actual class Bytes actual constructor(
    storage: ByteArray,
) : BaseBytes<Bytes>(storage) {
    actual override fun create(storage: ByteArray) = Bytes(storage)

    actual override val self = this

    actual companion object : BaseBytesCompanion<Bytes>() {
        actual override val empty = Bytes(EMPTY_ARRAY)

        actual override fun create(storage: ByteArray) = Bytes(storage)

        actual fun empty() = empty

        actual fun allocate(size: Int, defaultValue: Byte) = commonAllocate(size, defaultValue)

        actual fun allocate(size: Int, defaultValue: UByte) = commonAllocate(size, defaultValue)

        actual fun allocate(size: Int, init: (Int) -> UByte) = commonAllocate(size, init)

        actual fun wrap(source: ByteArray) = commonWrap(source)

        actual fun wrap(source: UByteArray) = commonWrap(source)

        actual fun from(vararg source: Byte) = commonFrom(*source)

        actual fun from(vararg source: UByte) = commonFrom(*source)

        actual fun from(source: ByteArray, offset: Int, length: Int) = commonFrom(source, offset, length)

        actual fun from(source: UByteArray, offset: Int, length: Int) = commonFrom(source, offset, length)

        actual fun from(vararg source: Bytes) = commonFrom(*source)
    }
}
