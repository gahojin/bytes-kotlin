package jp.co.gahojin.bytes

import kotlin.jvm.JvmOverloads

@Suppress("unused")
abstract class BaseBytes<T : BaseBytes<T>> internal constructor(
    protected val storage: ByteArray
) : Collection<Byte>, Comparable<T> {
    protected abstract fun create(storage: ByteArray): T

    protected abstract val self: T

    override val size: Int
        get() = storage.size

    override fun isEmpty(): Boolean = storage.isEmpty()

    override fun iterator(): Iterator<Byte> = storage.iterator()

    override fun containsAll(elements: Collection<Byte>): Boolean {
        return elements.all { storage.contains(it) }
    }

    fun contains(element: UByte): Boolean = contains(element.toByte())

    override fun contains(element: Byte): Boolean = storage.contains(element)

    override fun compareTo(other: T): Int {
        val sizeA = size
        val sizeB = other.size
        val size = minOf(sizeA, sizeB)
        var i = 0
        while (i < size) {
            val byteA = this[i]
            val byteB = other[i]
            return when {
                byteA == byteB -> {
                    i++
                    continue
                }

                byteA < byteB -> -1
                else -> 1
            }
        }
        return when {
            sizeA == sizeB -> 0
            sizeA < sizeB -> -1
            else -> 1
        }
    }

    override fun hashCode(): Int = storage.contentHashCode()

    override fun toString(): String = storage.contentToString()

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other !is BaseBytes<*> -> false
            else -> storage.contentEquals(other.storage)
        }
    }

    fun contentEquals(other: T): Boolean {
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

    fun copyOfRange(fromIndex: Int, toIndex: Int): T {
        return create(storage.copyOfRange(fromIndex, toIndex))
    }

    fun copyOf(): T = copyOf(storage.size)

    @JvmOverloads
    fun copyOf(newSize: Int, baseStrategy: ResizeStrategy = ResizeStrategy.ZERO_INDEX): T {
        return create(storage.copyOf(newSize, baseStrategy))
    }

    @JvmOverloads
    fun copyInto(dest: ByteArray, offset: Int = 0, fromIndex: Int = 0, toIndex: Int = dest.size): T {
        storage.copyInto(dest, offset, fromIndex, toIndex)
        return self
    }

    fun copyInto(dest: UByteArray, offset: Int = 0, fromIndex: Int = 0, toIndex: Int = dest.size): T {
        storage.copyInto(dest.asByteArray(), offset, fromIndex, toIndex)
        return self
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

    fun put(offset: Int, source: Byte): T {
        storage[offset] = source
        return self
    }

    fun put(offset: Int, source: UByte): T {
        storage[offset] = source.toByte()
        return self
    }

    fun putShort(offset: Int, source: Short): T {
        storage.putShort(offset, source)
        return self
    }

    fun putShortLe(offset: Int, source: Short): T {
        storage.putShortLe(offset, source)
        return self
    }

    fun putShort(offset: Int, source: UShort): T {
        storage.putShort(offset, source)
        return self
    }

    fun putShortLe(offset: Int, source: UShort): T {
        storage.putShortLe(offset, source)
        return self
    }

    fun putInt(offset: Int, source: Int): T {
        storage.putInt(offset, source)
        return self
    }

    fun putIntLe(offset: Int, source: Int): T {
        storage.putIntLe(offset, source)
        return self
    }

    fun putInt(offset: Int, source: Int, length: Int): T {
        storage.putInt(offset, source, length)
        return self
    }

    fun putIntLe(offset: Int, source: Int, length: Int): T {
        storage.putIntLe(offset, source, length)
        return self
    }

    fun putInt(offset: Int, source: UInt): T {
        storage.putInt(offset, source)
        return self
    }

    fun putIntLe(offset: Int, source: UInt): T {
        storage.putIntLe(offset, source)
        return self
    }

    fun putInt(offset: Int, source: UInt, length: Int): T {
        storage.putInt(offset, source, length)
        return self
    }

    fun putIntLe(offset: Int, source: UInt, length: Int): T {
        storage.putIntLe(offset, source, length)
        return self
    }

    fun putInt24(offset: Int, source: Int): T {
        storage.putInt24(offset, source)
        return self
    }

    fun putInt24Le(offset: Int, source: Int): T {
        storage.putInt24Le(offset, source)
        return self
    }

    fun putInt24(offset: Int, source: UInt): T {
        storage.putInt24(offset, source)
        return self
    }

    fun putInt24Le(offset: Int, source: UInt): T {
        storage.putInt24Le(offset, source)
        return self
    }

    fun putLong(offset: Int, source: Long): T {
        storage.putLong(offset, source)
        return self
    }

    fun putLongLe(offset: Int, source: Long): T {
        storage.putLongLe(offset, source)
        return self
    }

    fun putLong(offset: Int, source: Long, length: Int): T {
        storage.putLong(offset, source, length)
        return self
    }

    fun putLongLe(offset: Int, source: Long, length: Int): T {
        storage.putLongLe(offset, source, length)
        return self
    }

    fun putLong(offset: Int, source: ULong): T {
        storage.putLong(offset, source)
        return self
    }

    fun putLongLe(offset: Int, source: ULong): T {
        storage.putLongLe(offset, source)
        return self
    }

    fun putLong(offset: Int, source: ULong, length: Int): T {
        storage.putLong(offset, source, length)
        return self
    }

    fun putLongLe(offset: Int, source: ULong, length: Int): T {
        storage.putLongLe(offset, source, length)
        return self
    }

    fun put(offset: Int, first: Byte, vararg source: Byte): T {
        storage[offset] = first
        source.copyInto(storage, offset + 1)
        return self
    }

    fun put(offset: Int, first: UByte, vararg source: UByte): T {
        storage[offset] = first.toByte()
        source.copyInto(storage.asUByteArray(), offset + 1)
        return self
    }

    @JvmOverloads
    fun put(offset: Int, source: T, length: Int = source.size, sourceOffset: Int = 0): T {
        return put(offset, source.storage, length, sourceOffset)
    }

    @JvmOverloads
    fun put(offset: Int, source: ByteArray, length: Int = source.size, sourceOffset: Int = 0): T {
        source.copyInto(storage, offset, sourceOffset, sourceOffset + length)
        return self
    }

    fun put(offset: Int, source: UByteArray, length: Int = source.size, sourceOffset: Int = 0): T {
        source.copyInto(storage.asUByteArray(), offset, sourceOffset, sourceOffset + length)
        return self
    }

    @JvmOverloads
    fun inv(inPlace: Boolean = false): T = create(storage.inv(inPlace))

    infix fun and(second: T): T = and(second, false)

    fun and(second: T, inPlace: Boolean): T = and(second.storage, inPlace)

    infix fun and(second: ByteArray): T = and(second, false)

    fun and(second: ByteArray, inPlace: Boolean): T = create(storage.and(second, inPlace))

    infix fun and(second: UByteArray): T = and(second, false)

    fun and(second: UByteArray, inPlace: Boolean): T = create(storage.and(second.asByteArray(), inPlace))

    infix fun or(second: T): T = or(second, false)

    fun or(second: T, inPlace: Boolean): T = or(second.storage, inPlace)

    infix fun or(second: ByteArray): T = or(second, false)

    fun or(second: ByteArray, inPlace: Boolean): T = create(storage.or(second, inPlace))

    infix fun or(second: UByteArray): T = or(second, false)

    fun or(second: UByteArray, inPlace: Boolean): T = create(storage.or(second.asByteArray(), inPlace))

    infix fun xor(second: T): T = xor(second, false)

    fun xor(second: T, inPlace: Boolean): T = xor(second.storage, inPlace)

    infix fun xor(second: ByteArray): T = xor(second, false)

    fun xor(second: ByteArray, inPlace: Boolean): T = create(storage.xor(second, inPlace))

    infix fun xor(second: UByteArray): T = xor(second, false)

    fun xor(second: UByteArray, inPlace: Boolean): T = create(storage.xor(second.asByteArray(), inPlace))

    fun reverse(inPlace: Boolean = false): T {
        if (inPlace) {
            storage.reverse()
            return self
        }
        return create(storage.reversedArray())
    }

    infix fun shl(bitCount: Int): T = shl(bitCount, false)

    fun shl(bitCount: Int, inPlace: Boolean): T = create(storage.shl(bitCount, inPlace))

    infix fun shr(bitCount: Int): T = shr(bitCount, false)

    fun shr(shiftBitCount: Int, inPlace: Boolean): T = create(storage.shr(shiftBitCount, inPlace))

    fun getBit(bitIndex: Int): Boolean = storage.getBit(bitIndex)

    fun getBitLe(bitIndex: Int): Boolean = storage.getBitLe(bitIndex)

    @JvmOverloads
    fun switchBit(bitIndex: Int, value: Boolean, inPlace: Boolean = false): T {
        return switchBit(bitIndex.rangeTo(bitIndex), value, inPlace)
    }

    @JvmOverloads
    fun switchBitLe(bitIndex: Int, value: Boolean, inPlace: Boolean = false): T {
        return switchBitLe(bitIndex.rangeTo(bitIndex), value, inPlace)
    }

    @JvmOverloads
    fun switchBit(indexRange: IntProgression, value: Boolean, inPlace: Boolean = false): T {
        return create(storage.switchBit(indexRange, value, inPlace))
    }

    @JvmOverloads
    fun switchBitLe(indexRange: IntProgression, value: Boolean, inPlace: Boolean = false): T {
        return create(storage.switchBitLe(indexRange, value, inPlace))
    }

    @JvmOverloads
    fun flipBit(bitIndex: Int, inPlace: Boolean = false): T {
        return create(storage.flipBit(bitIndex, inPlace))
    }

    @JvmOverloads
    fun flipBitLe(bitIndex: Int, inPlace: Boolean = false): T {
        return create(storage.flipBitLe(bitIndex, inPlace))
    }

    @JvmOverloads
    fun flipBit(indexRange: IntProgression, inPlace: Boolean = false): T {
        return create(storage.flipBit(indexRange, inPlace))
    }

    @JvmOverloads
    fun flipBitLe(indexRange: IntProgression, inPlace: Boolean = false): T {
        return create(storage.flipBitLe(indexRange, inPlace))
    }

    @JvmOverloads
    fun toHexString(format: HexFormat = HexFormat.Default): String = storage.toHexString(format)
}
