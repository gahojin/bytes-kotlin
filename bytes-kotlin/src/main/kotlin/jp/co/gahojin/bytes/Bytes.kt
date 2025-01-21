@file:Suppress("NOTHING_TO_INLINE")

package jp.co.gahojin.bytes

import java.nio.ByteBuffer
import java.security.SecureRandom
import java.util.*
import kotlin.math.abs
import kotlin.text.HexFormat

@Suppress("unused", "MemberVisibilityCanBePrivate")
class Bytes private constructor(
    private val storage: ByteArray,
) : Collection<Byte> {
    override val size: Int = storage.size

    override fun isEmpty(): Boolean = storage.isEmpty()

    override fun iterator(): Iterator<Byte> = storage.iterator()

    override fun containsAll(elements: Collection<Byte>): Boolean {
        return elements.all { storage.contains(it) }
    }

    fun contains(element: UByte): Boolean = contains(element.toByte())

    override fun contains(element: Byte): Boolean = storage.contains(element)

    override fun hashCode(): Int = storage.contentHashCode()

    override fun toString(): String = storage.contentToString()

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other !is Bytes -> false
            else -> storage.contentEquals(other.storage)
        }
    }

    fun contentEquals(other: Bytes): Boolean {
        return storage.contentEquals(other.storage)
    }

    operator fun get(offset: Int): UByte = storage[offset].toUByte()

    operator fun set(offset: Int, value: Byte) = storage.set(offset, value)

    operator fun set(offset: Int, value: UByte) = storage.set(offset, value.toByte())

    /**
     * 内部配列への参照を返す.
     */
    fun array(): ByteArray = storage

    /**
     * 内部配列への参照を返す.
     */
    fun uarray(): UByteArray = storage.asUByteArray()

    fun copyRange(fromIndex: Int, toIndex: Int): Bytes {
        return Bytes(storage.copyOfRange(fromIndex, toIndex))
    }

    fun copyOf(): Bytes = copyOf(storage.size)

    @JvmOverloads
    fun copyOf(newSize: Int, baseStrategy: ResizeStrategy = ResizeStrategy.ZERO_INDEX): Bytes {
        return when (newSize) {
            0 -> EMPTY
            storage.size -> Bytes(storage.copyOf())
            else -> when (baseStrategy) {
                ResizeStrategy.ZERO_INDEX -> Bytes(storage.copyOf(newSize))
                ResizeStrategy.MAX_LENGTH -> {
                    val dest = ByteArray(newSize)
                    val currentSize = storage.size
                    val max = maxOf(0, abs(newSize - currentSize))
                    if (newSize > currentSize) {
                        System.arraycopy(storage, 0, dest, max, currentSize)
                    } else {
                        System.arraycopy(storage, max, dest, 0, newSize)
                    }
                    Bytes(dest)
                }
            }
        }
    }

    fun getByte(offset: Int): Byte = storage[offset]

    fun getUByte(offset: Int): UByte = storage[offset].toUByte()

    fun getShort(offset: Int): Short = storage.getShort(offset)

    fun getShortLe(offset: Int): Short = storage.getShortLe(offset)

    fun getUShort(offset: Int): UShort = storage.getUShort(offset)

    fun getUShortLe(offset: Int): UShort = storage.getUShortLe(offset)

    fun getInt(offset: Int): Int = storage.getInt(offset)

    fun getIntLe(offset: Int): Int = storage.getIntLe(offset)

    fun getUInt(offset: Int): UInt = storage.getUInt(offset)

    fun getUIntLe(offset: Int): UInt = storage.getUIntLe(offset)

    fun getInt24(offset: Int): Int = storage.getInt24(offset)

    fun getInt24Le(offset: Int): Int = storage.getInt24Le(offset)

    fun getUInt24(offset: Int): UInt = storage.getUInt24(offset)

    fun getUInt24Le(offset: Int): UInt = storage.getUInt24Le(offset)

    fun getLong(offset: Int): Long = storage.getLong(offset)

    fun getLongLe(offset: Int): Long = storage.getLongLe(offset)

    fun getULong(offset: Int): ULong = storage.getULong(offset)

    fun getULongLe(offset: Int): ULong = storage.getULongLe(offset)

    fun put(offset: Int, source: Byte): Bytes {
        storage[offset] = source
        return this
    }

    fun put(offset: Int, source: UByte): Bytes {
        storage[offset] = source.toByte()
        return this
    }

    fun putShort(offset: Int, source: Short): Bytes {
        storage.putShort(offset, source)
        return this
    }

    fun putShortLe(offset: Int, source: Short): Bytes {
        storage.putShortLe(offset, source)
        return this
    }

    fun putShort(offset: Int, source: UShort): Bytes {
        storage.putShort(offset, source)
        return this
    }

    fun putShortLe(offset: Int, source: UShort): Bytes {
        storage.putShortLe(offset, source)
        return this
    }

    fun putInt(offset: Int, source: Int): Bytes {
        storage.putInt(offset, source)
        return this
    }

    fun putIntLe(offset: Int, source: Int): Bytes {
        storage.putIntLe(offset, source)
        return this
    }

    fun putInt(offset: Int, source: UInt): Bytes {
        storage.putInt(offset, source)
        return this
    }

    fun putIntLe(offset: Int, source: UInt): Bytes {
        storage.putIntLe(offset, source)
        return this
    }

    fun putInt24(offset: Int, source: Int): Bytes {
        storage.putInt24(offset, source)
        return this
    }

    fun putInt24Le(offset: Int, source: Int): Bytes {
        storage.putInt24Le(offset, source)
        return this
    }

    fun putInt24(offset: Int, source: UInt): Bytes {
        storage.putInt24(offset, source)
        return this
    }

    fun putInt24Le(offset: Int, source: UInt): Bytes {
        storage.putInt24Le(offset, source)
        return this
    }

    fun putLong(offset: Int, source: Long): Bytes {
        storage.putLong(offset, source)
        return this
    }

    fun putLongLe(offset: Int, source: Long): Bytes {
        storage.putLongLe(offset, source)
        return this
    }

    fun putLong(offset: Int, source: ULong): Bytes {
        storage.putLong(offset, source)
        return this
    }

    fun putLongLe(offset: Int, source: ULong): Bytes {
        storage.putLongLe(offset, source)
        return this
    }

    fun put(offset: Int, first: Byte, vararg source: Byte): Bytes {
        storage[offset] = first
        source.copyInto(storage, offset + 1)
        return this
    }

    fun put(offset: Int, first: UByte, vararg source: UByte): Bytes {
        storage[offset] = first.toByte()
        source.copyInto(storage.asUByteArray(), offset + 1)
        return this
    }

    @JvmOverloads
    fun put(offset: Int, source: Bytes, length: Int = source.size, sourceOffset: Int = 0): Bytes {
        return put(offset, source.storage, length, sourceOffset)
    }

    @JvmOverloads
    fun put(offset: Int, source: ByteArray, length: Int = source.size, sourceOffset: Int = 0): Bytes {
        source.copyInto(storage, offset, sourceOffset, sourceOffset + length)
        return this
    }

    fun put(offset: Int, source: UByteArray, length: Int = source.size, sourceOffset: Int = 0): Bytes {
        source.copyInto(storage.asUByteArray(), offset, sourceOffset, sourceOffset + length)
        return this
    }

    @JvmOverloads
    fun inv(inPlace: Boolean = false): Bytes = Bytes(storage.inv(inPlace))

    inline infix fun and(second: Bytes): Bytes = and(second, false)

    fun and(second: Bytes, inPlace: Boolean): Bytes = and(second.storage, inPlace)

    inline infix fun and(second: ByteArray): Bytes = and(second, false)

    fun and(second: ByteArray, inPlace: Boolean): Bytes = Bytes(storage.and(second, inPlace))

    inline infix fun and(second: UByteArray): Bytes = and(second, false)

    fun and(second: UByteArray, inPlace: Boolean): Bytes = Bytes(storage.and(second.asByteArray(), inPlace))

    inline infix fun or(second: Bytes): Bytes = or(second, false)

    fun or(second: Bytes, inPlace: Boolean): Bytes = or(second.storage, inPlace)

    inline infix fun or(second: ByteArray): Bytes = or(second, false)

    fun or(second: ByteArray, inPlace: Boolean): Bytes = Bytes(storage.or(second, inPlace))

    inline infix fun or(second: UByteArray): Bytes = or(second, false)

    fun or(second: UByteArray, inPlace: Boolean): Bytes = Bytes(storage.or(second.asByteArray(), inPlace))

    inline infix fun xor(second: Bytes): Bytes = xor(second, false)

    fun xor(second: Bytes, inPlace: Boolean): Bytes = xor(second.storage, inPlace)

    inline infix fun xor(second: ByteArray): Bytes = xor(second, false)

    fun xor(second: ByteArray, inPlace: Boolean): Bytes = Bytes(storage.xor(second, inPlace))

    inline infix fun xor(second: UByteArray): Bytes = xor(second, false)

    fun xor(second: UByteArray, inPlace: Boolean): Bytes = Bytes(storage.xor(second.asByteArray(), inPlace))

    fun reverse(inPlace: Boolean = false): Bytes {
        if (inPlace) {
            storage.reverse()
            return this
        }
        return Bytes(storage.reversedArray())
    }

    inline infix fun shl(bitCount: Int): Bytes = shl(bitCount, false)

    fun shl(bitCount: Int, inPlace: Boolean): Bytes = Bytes(storage.shl(bitCount, inPlace))

    inline infix fun shr(bitCount: Int): Bytes = shr(bitCount, false)

    fun shr(shiftBitCount: Int, inPlace: Boolean): Bytes = Bytes(storage.shr(shiftBitCount, inPlace))

    fun getBit(bitIndex: Int): Boolean = storage.getBit(bitIndex)

    fun getBitLe(bitIndex: Int): Boolean = storage.getBitLe(bitIndex)

    @JvmOverloads
    inline fun switchBit(bitIndex: Int, value: Boolean, inPlace: Boolean = false): Bytes {
        return switchBit(bitIndex.rangeTo(bitIndex), value, inPlace)
    }

    @JvmOverloads
    inline fun switchBitLe(bitIndex: Int, value: Boolean, inPlace: Boolean = false): Bytes {
        return switchBitLe(bitIndex.rangeTo(bitIndex), value, inPlace)
    }

    @JvmOverloads
    fun switchBit(indexRange: IntProgression, value: Boolean, inPlace: Boolean = false): Bytes {
        return Bytes(storage.switchBit(indexRange, value, inPlace))
    }

    @JvmOverloads
    fun switchBitLe(indexRange: IntProgression, value: Boolean, inPlace: Boolean = false): Bytes {
        return Bytes(storage.switchBitLe(indexRange, value, inPlace))
    }

    @JvmOverloads
    fun flipBit(bitIndex: Int, inPlace: Boolean = false): Bytes {
        return Bytes(storage.flipBit(bitIndex, inPlace))
    }

    @JvmOverloads
    fun flipBitLe(bitIndex: Int, inPlace: Boolean = false): Bytes {
        return Bytes(storage.flipBitLe(bitIndex, inPlace))
    }

    @JvmOverloads
    fun flipBit(indexRange: IntProgression, inPlace: Boolean = false): Bytes {
        return Bytes(storage.flipBit(indexRange, inPlace))
    }

    @JvmOverloads
    fun flipBitLe(indexRange: IntProgression, inPlace: Boolean = false): Bytes {
        return Bytes(storage.flipBitLe(indexRange, inPlace))
    }

    @JvmOverloads
    fun toHexString(format: HexFormat = HexFormat.Default): String = storage.toHexString(format)

    companion object {
        private val EMPTY: Bytes = Bytes(ByteArray(0))

        @JvmStatic
        @JvmOverloads
        fun allocate(size: Int, defaultValue: Byte = 0x00): Bytes {
            return allocate(size, defaultValue.toUByte())
        }

        fun allocate(size: Int, defaultValue: UByte): Bytes {
            if (size <= 0) {
                return EMPTY
            }
            val storage = UByteArray(size)
            if (defaultValue.toUInt() != 0u) {
                storage.fill(defaultValue)
            }
            return Bytes(storage.asByteArray())
        }

        fun allocate(size: Int, init: (Int) -> UByte): Bytes {
            if (size <= 0) {
                return EMPTY
            }
            return Bytes(UByteArray(size, init).asByteArray())
        }

        @JvmStatic
        fun empty(): Bytes = EMPTY

        @JvmStatic
        @JvmOverloads
        fun random(size: Int, random: Random = SecureRandom()): Bytes {
            if (size <= 0) {
                return EMPTY
            }
            val storage = ByteArray(size)
            random.nextBytes(storage)
            return Bytes(storage)
        }

        @JvmStatic
        fun wrap(source: ByteArray) = Bytes(source)

        @JvmStatic
        fun wrap(source: UByteArray): Bytes = Bytes(source.asByteArray())

        @JvmStatic
        fun wrap(source: ByteBuffer): Bytes = Bytes(source.array())

        @JvmStatic
        fun from(vararg source: Byte) = Bytes(source)

        @JvmStatic
        fun from(vararg source: UByte): Bytes = Bytes(source.asByteArray())

        @JvmStatic
        fun from(source: ByteArray, offset: Int = 0, length: Int = source.size): Bytes {
            return from(source.asUByteArray(), offset, length)
        }

        @JvmStatic
        fun from(source: ByteBuffer, offset: Int = source.arrayOffset(), length: Int = source.limit()): Bytes {
            return from(source.array(), offset, length)
        }

        @JvmStatic
        fun from(source: UByteArray, offset: Int = 0, length: Int = source.size): Bytes {
            if (length <= 0) {
                return EMPTY
            }
            val ret = allocate(length)
            ret.put(offset, source, length, 0)
            return ret
        }

        @JvmStatic
        fun from(vararg source: Bytes): Bytes {
            if (source.isEmpty()) {
                return EMPTY
            }
            val size = source.sumOf { it.size }
            val ret = allocate(size)
            var offset = 0
            source.forEach {
                ret.put(offset, it)
                offset += it.size
            }
            return ret
        }
    }
}
