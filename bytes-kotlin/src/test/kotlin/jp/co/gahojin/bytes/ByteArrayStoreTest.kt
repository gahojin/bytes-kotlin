package jp.co.gahojin.bytes

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.byte
import io.kotest.property.arbitrary.byteArray
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.uInt
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
