package jp.co.gahojin.bytes

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.checkAll

class ByteArrayStoreTest : FunSpec({
    test("getShort") {
        checkAll<Short> { a ->
            val sut = ByteArray(2)
            sut.putShort(0, a)
            sut.getShort(0) shouldBe a
        }

        checkAll(Arb.int(min = Short.MIN_VALUE.toInt(), max = Short.MAX_VALUE.toInt())) { a ->
            val sut = ByteArray(2)
            sut.putShort(0, a)
            sut.getShort(0) shouldBe a.toShort()
        }
    }

    test("getShortLe") {
        checkAll<Short> { a ->
            val sut = ByteArray(2)
            sut.putShortLe(0, a)
            sut.getShortLe(0) shouldBe a
        }

        checkAll(Arb.int(min = Short.MIN_VALUE.toInt(), max = Short.MAX_VALUE.toInt())) { a ->
            val sut = ByteArray(2)
            sut.putShortLe(0, a)
            sut.getShortLe(0) shouldBe a.toShort()
        }
    }

    test("getUShort") {
        checkAll<UShort> { a ->
            val sut = ByteArray(2)
            sut.putShort(0, a)
            sut.getUShort(0) shouldBe a
        }

        checkAll(Arb.uInt(min = UShort.MIN_VALUE.toUInt(), max = UShort.MAX_VALUE.toUInt())) { a ->
            val sut = ByteArray(2)
            sut.putShort(0, a)
            sut.getUShort(0) shouldBe a.toUShort()
        }
    }

    test("getUShortLe") {
        checkAll<UShort> { a ->
            val sut = ByteArray(2)
            sut.putShortLe(0, a)
            sut.getUShortLe(0) shouldBe a
        }

        checkAll(Arb.uInt(min = UShort.MIN_VALUE.toUInt(), max = UShort.MAX_VALUE.toUInt())) { a ->
            val sut = ByteArray(2)
            sut.putShortLe(0, a)
            sut.getUShortLe(0) shouldBe a.toUShort()
        }
    }

    test("getInt") {
        checkAll<Int> { a ->
            val sut = ByteArray(4)
            sut.putInt(0, a)
            sut.getInt(0) shouldBe a
        }
    }

    test("getIntLe") {
        checkAll<Int> { a ->
            val sut = ByteArray(4)
            sut.putIntLe(0, a)
            sut.getIntLe(0) shouldBe a
        }
    }

    test("getInt/putInt with length") {
        checkAll(Arb.int(min = Short.MIN_VALUE.toInt(), max = Short.MAX_VALUE.toInt()), Arb.int(min = 2, max = 4)) { a, b ->
            val sut = ByteArray(4)
            sut.putInt(0, a, b)
            sut.getInt(0, b) shouldBe a
        }
    }

    test("getIntLe/putIntLe with length") {
        checkAll(Arb.int(min = Short.MIN_VALUE.toInt(), max = Short.MAX_VALUE.toInt()), Arb.int(min = 2, max = 4)) { a, b ->
            val sut = ByteArray(4)
            sut.putIntLe(0, a, b)
            sut.getIntLe(0, b) shouldBe a
        }
    }

    test("getUInt") {
        checkAll<UInt> { a ->
            val sut = ByteArray(4)
            sut.putInt(0, a)
            sut.getUInt(0) shouldBe a
        }
    }

    test("getUIntLe") {
        checkAll<UInt> { a ->
            val sut = ByteArray(4)
            sut.putIntLe(0, a)
            sut.getUIntLe(0) shouldBe a
        }
    }

    test("getUInt/putInt with length") {
        checkAll(Arb.uInt(min = UShort.MIN_VALUE.toUInt(), max = UShort.MAX_VALUE.toUInt()), Arb.int(min = 2, max = 4)) { a, b ->
            val sut = ByteArray(4)
            sut.putInt(0, a, b)
            sut.getUInt(0, b) shouldBe a
        }
    }

    test("getUIntLe/putIntLe with length") {
        checkAll(Arb.uInt(min = UShort.MIN_VALUE.toUInt(), max = UShort.MAX_VALUE.toUInt()), Arb.int(min = 2, max = 4)) { a, b ->
            val sut = ByteArray(4)
            sut.putIntLe(0, a, b)
            sut.getUIntLe(0, b) shouldBe a
        }
    }

    test("getInt24") {
        checkAll(Arb.int24()) { a ->
            val sut = ByteArray(3)
            sut.putInt24(0, a)
            sut.getInt24(0) shouldBe a
        }
    }

    test("getInt24Le") {
        checkAll(Arb.int24()) { a ->
            val sut = ByteArray(3)
            sut.putInt24Le(0, a)
            sut.getInt24Le(0) shouldBe a
        }
    }

    test("getUInt24") {
        checkAll(Arb.uInt24()) { a ->
            val sut = ByteArray(3)
            sut.putInt24(0, a)
            sut.getUInt24(0) shouldBe a
        }
    }

    test("getUInt24Le") {
        checkAll(Arb.uInt24()) { a ->
            val sut = ByteArray(3)
            sut.putInt24Le(0, a)
            sut.getUInt24Le(0) shouldBe a
        }
    }

    test("getLong") {
        checkAll<Long> { a ->
            val sut = ByteArray(8)
            sut.putLong(0, a)
            sut.getLong(0) shouldBe a
        }
    }

    test("getLongLe") {
        checkAll<Long> { a ->
            val sut = ByteArray(8)
            sut.putLongLe(0, a)
            sut.getLongLe(0) shouldBe a
        }
    }

    test("getLong/putLong with length") {
        checkAll(Arb.long(min = Int.MIN_VALUE.toLong(), max = Int.MAX_VALUE.toLong()), Arb.int(min = 4, max = 8)) { a, b ->
            val sut = ByteArray(8)
            sut.putLong(0, a, b)
            sut.getLong(0, b) shouldBe a
        }
    }

    test("getLongLe/putLongLe with length") {
        checkAll(Arb.long(min = Int.MIN_VALUE.toLong(), max = Int.MAX_VALUE.toLong()), Arb.int(min = 4, max = 8)) { a, b ->
            val sut = ByteArray(8)
            sut.putLongLe(0, a, b)
            sut.getLongLe(0, b) shouldBe a
        }
    }

    test("getULong") {
        checkAll<ULong> { a ->
            val sut = ByteArray(8)
            sut.putLong(0, a)
            sut.getULong(0) shouldBe a
        }
    }

    test("getULongLe") {
        checkAll<ULong> { a ->
            val sut = ByteArray(8)
            sut.putLongLe(0, a)
            sut.getULongLe(0) shouldBe a
        }
    }

    test("getULong/putLong with length") {
        checkAll(Arb.uLong(min = UInt.MIN_VALUE.toULong(), max = UInt.MAX_VALUE.toULong()), Arb.int(min = 4, max = 8)) { a, b ->
            val sut = ByteArray(8)
            sut.putLong(0, a, b)
            sut.getULong(0, b) shouldBe a
        }
    }

    test("getULongLe/putLongLe with length") {
        checkAll(Arb.uLong(min = UInt.MIN_VALUE.toULong(), max = UInt.MAX_VALUE.toULong()), Arb.int(min = 4, max = 8)) { a, b ->
            val sut = ByteArray(8)
            sut.putLongLe(0, a, b)
            sut.getULongLe(0, b) shouldBe a
        }
    }

    test("put(Bytes)") {
        checkAll(Arb.byteArray(Arb.int(min = 10, max = 256), Arb.byte())) { a ->
            val sut = Bytes.wrap(a)
            val buf = ByteArray(a.size)

            buf.put(0, sut)
            buf shouldBe a

            buf.fill(0)
            buf.put(1, sut, 2, 10)
            buf.copyOfRange(1, 9) shouldBe a.copyOfRange(2, 10)
        }
    }
})
