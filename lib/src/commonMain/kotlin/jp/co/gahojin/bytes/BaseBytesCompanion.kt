package jp.co.gahojin.bytes

abstract class BaseBytesCompanion<T : BaseBytes<T>> {
    protected abstract val empty: T

    protected abstract fun create(storage: ByteArray): T

    protected fun commonAllocate(size: Int, defaultValue: Byte = 0x00): T {
        return commonAllocate(size, defaultValue.toUByte())
    }

    protected fun commonAllocate(size: Int, defaultValue: UByte): T {
        if (size <= 0) {
            return empty
        }
        val storage = UByteArray(size)
        if (defaultValue.toUInt() != 0u) {
            storage.fill(defaultValue)
        }
        return create(storage.asByteArray())
    }

    protected fun commonAllocate(size: Int, init: (Int) -> UByte): T {
        if (size <= 0) {
            return empty
        }
        return create(UByteArray(size, init).asByteArray())
    }

    protected fun commonWrap(source: ByteArray) = create(source)

    protected fun commonWrap(source: UByteArray) = create(source.asByteArray())

    protected fun commonFrom(vararg source: Byte) = create(source)

    protected fun commonFrom(vararg source: UByte) = create(source.asByteArray())

    protected fun commonFrom(source: ByteArray, offset: Int, length: Int): T {
        return commonFrom(source.asUByteArray(), offset, length)
    }

    protected fun commonFrom(source: UByteArray, offset: Int, length: Int): T {
        if (length <= 0) {
            return empty
        }
        val ret = commonAllocate(length)
        ret.put(offset, source, length, 0)
        return ret
    }

    protected fun commonFrom(vararg source: T): T {
        if (source.isEmpty()) {
            return empty
        }
        val size = source.sumOf { it.size }
        val ret = commonAllocate(size)
        var offset = 0
        source.forEach {
            ret.put(offset, it)
            offset += it.size
        }
        return ret
    }
}
