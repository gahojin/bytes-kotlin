package jp.co.gahojin.bytes

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.byte
import io.kotest.property.arbitrary.byteArray
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.long
import io.kotest.property.arbitrary.short
import io.kotest.property.arbitrary.uInt
import io.kotest.property.arbitrary.uLong
import io.kotest.property.arbitrary.uShort
import io.kotest.property.checkAll
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.BitSet

@OptIn(ExperimentalUnsignedTypes::class)
class BytesJvmTest : FunSpec({
    test("clone") {
        val sut = Bytes.wrap("xyz".toByteArray())
        val clone = sut.clone()
        sut shouldBe clone

        sut.put(0, "abc".toByteArray())
        sut shouldNotBe clone
    }

    test("getShort") {
        checkAll(Arb.short()) { a ->
            val buf = ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN)
            buf.putShort(a)
            val sut = Bytes.from(buf)
            sut.getShort(0) shouldBe a
        }

        val sut = Bytes.allocate(3)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getShort(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 2byte = over
            sut.getShort(2)
        }
    }

    test("getShortLe") {
        checkAll(Arb.short()) { a ->
            val buf = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN)
            buf.putShort(a)
            val sut = Bytes.from(buf)
            sut.getShortLe(0) shouldBe a
        }

        val sut = Bytes.allocate(3)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getShortLe(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 2byte = over
            sut.getShortLe(2)
        }
    }

    test("getUShort") {
        checkAll(Arb.uShort()) { a ->
            val buf = ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN)
            buf.putShort(a.toShort())
            val sut = Bytes.from(buf)
            sut.getUShort(0) shouldBe a
        }

        val sut = Bytes.allocate(3)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getUShort(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 2byte = over
            sut.getUShort(2)
        }
    }

    test("getUShortLe") {
        checkAll(Arb.uShort()) { a ->
            val buf = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN)
            buf.putShort(a.toShort())
            val sut = Bytes.from(buf)
            sut.getUShortLe(0) shouldBe a
        }

        val sut = Bytes.allocate(3)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getUShortLe(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 2byte = over
            sut.getUShortLe(2)
        }
    }

    test("getInt") {
        checkAll(Arb.int()) { a ->
            val buf = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN)
            buf.putInt(a)
            val sut = Bytes.from(buf)
            sut.getInt(0) shouldBe a
        }

        val sut = Bytes.allocate(5)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getInt(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 4byte = over
            sut.getInt(2)
        }
    }

    test("getIntLe") {
        checkAll(Arb.int()) { a ->
            val buf = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
            buf.putInt(a)
            val sut = Bytes.from(buf)
            sut.getIntLe(0) shouldBe a
        }

        val sut = Bytes.allocate(5)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getIntLe(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 4byte = over
            sut.getIntLe(2)
        }
    }

    test("getUInt") {
        checkAll(Arb.uInt()) { a ->
            val buf = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN)
            buf.putInt(a.toInt())
            val sut = Bytes.from(buf)
            sut.getUInt(0) shouldBe a
        }

        val sut = Bytes.allocate(5)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getUInt(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 4byte = over
            sut.getUInt(2)
        }
    }

    test("getUIntLe") {
        checkAll(Arb.uInt()) { a ->
            val buf = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
            buf.putInt(a.toInt())
            val sut = Bytes.from(buf)
            sut.getUIntLe(0) shouldBe a
        }

        val sut = Bytes.allocate(5)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getUIntLe(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 4byte = over
            sut.getUIntLe(2)
        }
    }

    test("getInt24") {
        checkAll(Arb.int24()) { a ->
            val buf = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN)
            buf.putInt(a)
            val sut = Bytes.from(buf)
            sut.getInt24(1) shouldBe a
        }

        val sut = Bytes.allocate(4)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getInt24(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 4byte = over
            sut.getInt24(2)
        }
    }

    test("getInt24Le") {
        checkAll(Arb.int24()) { a ->
            val buf = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
            buf.putInt(a)
            val sut = Bytes.from(buf)
            sut.getInt24Le(0) shouldBe a
        }

        val sut = Bytes.allocate(4)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getInt24Le(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 3byte = over
            sut.getInt24Le(2)
        }
    }

    test("getUInt24") {
        checkAll(Arb.uInt24()) { a ->
            val buf = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN)
            buf.putInt(a.toInt())
            val sut = Bytes.from(buf)
            sut.getUInt24(1) shouldBe a
        }

        val sut = Bytes.allocate(4)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getUInt24(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 3byte = over
            sut.getUInt24(2)
        }
    }

    test("getUInt24Le") {
        checkAll(Arb.uInt24()) { a ->
            val buf = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
            buf.putInt(a.toInt())
            val sut = Bytes.from(buf)
            sut.getUInt24Le(0) shouldBe a
        }

        val sut = Bytes.allocate(4)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getUInt24Le(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 3byte = over
            sut.getUInt24Le(2)
        }
    }

    test("getLong") {
        checkAll(Arb.long()) { a ->
            val buf = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN)
            buf.putLong(a)
            val sut = Bytes.from(buf)
            sut.getLong(0) shouldBe a
        }

        val sut = Bytes.allocate(5)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getLong(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 4byte = over
            sut.getLong(2)
        }
    }

    test("getLongLe") {
        checkAll(Arb.long()) { a ->
            val buf = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
            buf.putLong(a)
            val sut = Bytes.from(buf)
            sut.getLongLe(0) shouldBe a
        }

        val sut = Bytes.allocate(9)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getLongLe(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 8byte = over
            sut.getLongLe(2)
        }
    }

    test("getULong") {
        checkAll(Arb.uLong()) { a ->
            val buf = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN)
            buf.putLong(a.toLong())
            val sut = Bytes.from(buf)
            sut.getULong(0) shouldBe a
        }

        val sut = Bytes.allocate(5)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getULong(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 4byte = over
            sut.getULong(2)
        }
    }

    test("getULongLe") {
        checkAll(Arb.uLong()) { a ->
            val buf = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
            buf.putLong(a.toLong())
            val sut = Bytes.from(buf)
            sut.getULongLe(0) shouldBe a
        }

        val sut = Bytes.allocate(9)
        // 範囲外
        shouldThrow<IndexOutOfBoundsException> {
            sut.getULongLe(-1)
        }
        shouldThrow<IndexOutOfBoundsException> {
            // 2 + 8byte = over
            sut.getULongLe(2)
        }
    }


    test("putShort(short)") {
        checkAll(Arb.short()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).putShort(1, a)

            sut.putShort(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putShortLe(short)") {
        checkAll(Arb.short()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putShort(1, a)

            sut.putShortLe(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putShort(ushort)") {
        checkAll(Arb.uShort()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).putShort(1, a.toShort())

            sut.putShort(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putShortLe(ushort)") {
        checkAll(Arb.uShort()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putShort(1, a.toShort())

            sut.putShortLe(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putInt(int)") {
        checkAll(Arb.int()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).putInt(1, a)

            sut.putInt(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putInt(int, length)") {
        checkAll(Arb.int(), Arb.int(2..4)) { a, len ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).putInt(1, a)

            sut.putInt(1, a, len) shouldBe sut
            sut.array().copyOfRange(1, len + 1) shouldBe exp.array().copyOfRange(5 - len, 5)
        }
    }

    test("putIntLe(int)") {
        checkAll(Arb.int()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putInt(1, a)

            sut.putIntLe(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putIntLe(int, length)") {
        checkAll(Arb.int(), Arb.int(2..4)) { a, len ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putInt(1, a)

            sut.putIntLe(1, a, len) shouldBe sut
            sut.array().copyOfRange(1, len + 1) shouldBe exp.array().copyOfRange(1, len + 1)
        }
    }

    test("putInt(uint)") {
        checkAll(Arb.uInt()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).putInt(1, a.toInt())

            sut.putInt(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putInt(uint, length)") {
        checkAll(Arb.uInt(), Arb.int(2..4)) { a, len ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).putInt(1, a.toInt())

            sut.putInt(1, a, len) shouldBe sut
            sut.array().copyOfRange(1, len + 1) shouldBe exp.array().copyOfRange(5 - len, 5)
        }
    }

    test("putIntLe(uint)") {
        checkAll(Arb.uInt()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putInt(1, a.toInt())

            sut.putIntLe(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putIntLe(uint, length)") {
        checkAll(Arb.uInt(), Arb.int(2..4)) { a, len ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putInt(1, a.toInt())

            sut.putIntLe(1, a, len) shouldBe sut
            sut.array().copyOfRange(1, len + 1) shouldBe exp.array().copyOfRange(1, len + 1)
        }
    }

    test("putInt24(int)") {
        checkAll(Arb.int24()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).putInt(1, a)

            sut.putInt24(1, a) shouldBe sut
            sut.array().copyOfRange(1, 4) shouldBe exp.array().copyOfRange(2, 5)
        }
    }

    test("putInt24Le(int)") {
        checkAll(Arb.int24()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putInt(1, a)

            sut.putInt24Le(1, a) shouldBe sut
            sut.array().copyOfRange(1, 4) shouldBe exp.array().copyOfRange(1, 4)
        }
    }

    test("putInt24(uint)") {
        checkAll(Arb.uInt24()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).putInt(1, a.toInt())

            sut.putInt24(1, a) shouldBe sut
            sut.array().copyOfRange(1, 4) shouldBe exp.array().copyOfRange(2, 5)
        }
    }

    test("putInt24Le(uint)") {
        checkAll(Arb.uInt24()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putInt(1, a.toInt())

            sut.putInt24Le(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putLong(long)") {
        checkAll(Arb.long()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).putLong(1, a)

            sut.putLong(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putLong(long, length)") {
        checkAll(Arb.long(), Arb.int(2..8)) { a, len ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).putLong(1, a)

            sut.putLong(1, a, len) shouldBe sut
            sut.array().copyOfRange(1, len + 1) shouldBe exp.array().copyOfRange(9 - len, 9)
        }
    }

    test("putLongLe(long)") {
        checkAll(Arb.long()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putLong(1, a)

            sut.putLongLe(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putLongLe(long, length)") {
        checkAll(Arb.long(), Arb.int(2..8)) { a, len ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putLong(1, a)

            sut.putLongLe(1, a, len) shouldBe sut
            sut.array().copyOfRange(1, len + 1) shouldBe exp.array().copyOfRange(1, len + 1)
        }
    }

    test("putLong(ulong)") {
        checkAll(Arb.uLong()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).putLong(1, a.toLong())

            sut.putLong(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putLong(ulong, length)") {
        checkAll(Arb.uLong(), Arb.int(2..8)) { a, len ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).putLong(1, a.toLong())

            sut.putLong(1, a, len) shouldBe sut
            sut.array().copyOfRange(1, len + 1) shouldBe exp.array().copyOfRange(9 - len, 9)
        }
    }

    test("putLongLe(ulong)") {
        checkAll(Arb.uLong()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putLong(1, a.toLong())

            sut.putLongLe(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("putLongLe(ulong, length)") {
        checkAll(Arb.uLong(), Arb.int(2..8)) { a, len ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putLong(1, a.toLong())

            sut.putLongLe(1, a, len) shouldBe sut
            sut.array().copyOfRange(1, len + 1) shouldBe exp.array().copyOfRange(1, len + 1)
        }
    }

    test("getBit") {
        checkAll(Arb.int(0..31)) { a ->
            val sut = Bytes.random(4)
            val exp = BitSet.valueOf(ByteBuffer.wrap(sut.array().reversedArray()))
            sut.getBit(a) shouldBe exp.get(a)
        }
    }

    test("getBitLe") {
        checkAll(Arb.int(0..31)) { a ->
            val sut = Bytes.random(4)
            val exp = BitSet.valueOf(sut.array())
            sut.getBitLe(a) shouldBe exp.get(a)
        }
    }

    test("switchBit") {
        checkAll(Arb.int(0..31)) { a ->
            val sut = Bytes.allocate(4)
            val exp = BitSet.valueOf(sut.array())
            exp.set(a, true)
            sut.switchBit(a, true, inPlace = true).array() shouldBe exp.toByteArray().copyOf(4).reversedArray()
            exp.set(a, false)
            sut.switchBit(a, false).array() shouldBe exp.toByteArray().copyOf(4).reversedArray()
        }
    }

    test("switchBitLe") {
        checkAll(Arb.int(0..31)) { a ->
            val sut = Bytes.allocate(4)
            val exp = BitSet.valueOf(sut.array())
            exp.set(a, true)
            sut.switchBitLe(a, true, inPlace = true).array() shouldBe exp.toByteArray().copyOf(4)
            exp.set(a, false)
            sut.switchBitLe(a, false).array() shouldBe exp.toByteArray().copyOf(4)
        }
    }

    test("flipBit") {
        checkAll(Arb.int(0..31)) { a ->
            val sut = Bytes.allocate(4)
            val exp = BitSet.valueOf(sut.array())
            exp.flip(a)
            sut.flipBit(a, inPlace = true).array() shouldBe exp.toByteArray().copyOf(4).reversedArray()
            exp.flip(a)
            sut.flipBit(a).array() shouldBe exp.toByteArray().copyOf(4).reversedArray()
        }
    }

    test("flipBitLe") {
        checkAll(Arb.int(0..31)) { a ->
            val sut = Bytes.allocate(4)
            val exp = BitSet.valueOf(sut.array())
            exp.flip(a)
            sut.flipBitLe(a, inPlace = true).array() shouldBe exp.toByteArray().copyOf(4)
            exp.flip(a)
            sut.flipBitLe(a).array() shouldBe exp.toByteArray().copyOf(4)
        }
    }

    context("companion object") {
        test("from") {
            // ByteBuffer
            checkAll(Arb.byteArray(length = Arb.int(1..100), content = Arb.byte())) { a ->
                val buffer = ByteBuffer.wrap(a)
                val sut = Bytes.from(buffer)
                sut.size shouldBe a.size
                sut.array() shouldBe a
                // 同じインスタンスを共有していないこと
                sut.array() shouldNotBeSameInstanceAs a
            }

            // 0size
            Bytes.from(ByteBuffer.allocate(0)) shouldBe Bytes.empty()
        }

        test("random") {
            // サイズ0の時
            Bytes.random(0).size shouldBe 0

            checkAll(Arb.int(10..1024)) { a ->
                val sut = Bytes.random(a)
                sut.size shouldBe a
                sut.all { it == 0.toByte() } shouldBe false
            }
        }

        test("wrap") {
            // ByteBuffer
            checkAll(Arb.byteArray(length = Arb.int(1..100), content = Arb.byte())) { a ->
                val buffer = ByteBuffer.wrap(a)
                val sut = Bytes.wrap(buffer)
                sut.size shouldBe a.size
                sut.array() shouldBe a
                // 同じインスタンスを共有していること
                sut.array() shouldBeSameInstanceAs a
            }
        }
    }
})
