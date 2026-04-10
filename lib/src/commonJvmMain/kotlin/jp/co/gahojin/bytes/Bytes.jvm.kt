package jp.co.gahojin.bytes

import java.nio.ByteBuffer
import java.security.SecureRandom
import kotlin.random.Random
import kotlin.random.asKotlinRandom

actual class Bytes actual constructor(
    storage: ByteArray,
) : BaseBytes<Bytes>(storage), Cloneable {
    actual override fun create(storage: ByteArray) = Bytes(storage)

    actual override val self = this

    public override fun clone(): Bytes = copyOf()

    actual companion object : BaseBytesCompanion<Bytes>() {
        actual override val empty = Bytes(EMPTY_ARRAY)

        actual override fun create(storage: ByteArray) = Bytes(storage)

        @JvmStatic
        @JvmOverloads
        fun random(size: Int, random: java.util.Random = SecureRandom()): Bytes {
            return random(size, random.asKotlinRandom())
        }

        @JvmStatic
        fun random(size: Int, random: Random): Bytes {
            if (size <= 0) {
                return empty
            }
            val storage = ByteArray(size)
            random.nextBytes(storage)
            return create(storage)
        }

        @JvmStatic
        actual fun empty() = empty

        @JvmStatic
        @JvmOverloads
        actual fun allocate(size: Int, defaultValue: Byte) = commonAllocate(size, defaultValue)

        @JvmStatic
        actual fun allocate(size: Int, defaultValue: UByte) = commonAllocate(size, defaultValue)

        @JvmStatic
        actual fun allocate(size: Int, init: (Int) -> UByte) = commonAllocate(size, init)

        @JvmStatic
        actual fun wrap(source: ByteArray) = commonWrap(source)

        @JvmStatic
        actual fun wrap(source: UByteArray) = commonWrap(source)

        @JvmStatic
        fun wrap(source: ByteBuffer): Bytes = commonWrap(source.array())

        @JvmStatic
        actual fun from(vararg source: Byte) = commonFrom(*source)

        @JvmStatic
        actual fun from(vararg source: UByte) = commonFrom(*source)

        @JvmStatic
        actual fun from(source: ByteArray, offset: Int, length: Int) = commonFrom(source, offset, length)

        @JvmStatic
        actual fun from(source: UByteArray, offset: Int, length: Int) = commonFrom(source, offset, length)

        @JvmStatic
        @JvmOverloads
        fun from(source: ByteBuffer, offset: Int = source.arrayOffset(), length: Int = source.limit()) = commonFrom(source.array(), offset, length)

        @JvmStatic
        actual fun from(vararg source: Bytes) = commonFrom(*source)
    }
}
