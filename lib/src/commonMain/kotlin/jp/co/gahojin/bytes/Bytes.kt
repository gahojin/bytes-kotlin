@file:Suppress("NOTHING_TO_INLINE")

package jp.co.gahojin.bytes

import kotlin.jvm.JvmOverloads

expect class Bytes private constructor(
    storage: ByteArray,
) : BaseBytes<Bytes> {
    override fun create(storage: ByteArray): Bytes

    override val self: Bytes

    companion object : BaseBytesCompanion<Bytes> {
        override val empty: Bytes

        override fun create(storage: ByteArray): Bytes

        fun allocate(size: Int, defaultValue: Byte = 0x00): Bytes

        fun allocate(size: Int, defaultValue: UByte): Bytes

        fun allocate(size: Int, init: (Int) -> UByte): Bytes

        fun empty(): Bytes

        fun wrap(source: ByteArray): Bytes

        fun wrap(source: UByteArray): Bytes

        fun from(vararg source: Byte): Bytes

        fun from(vararg source: UByte): Bytes

        fun from(source: ByteArray, offset: Int = 0, length: Int = source.size): Bytes

        fun from(source: UByteArray, offset: Int = 0, length: Int = source.size): Bytes

        fun from(vararg source: Bytes): Bytes
    }
}
