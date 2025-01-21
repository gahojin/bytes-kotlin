package jp.co.gahojin.bytes

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.checkAll
import org.junit.jupiter.api.assertThrows
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*
import kotlin.random.Random
import kotlin.random.nextUBytes

class BytesTest : FunSpec({
    test("size") {
        checkAll(Arb.int(min = 0, max = 1024)) { a ->
            Bytes.allocate(a).size shouldBe a
        }
    }

    test("isEmpty") {
        Bytes.empty().isEmpty() shouldBe true
    }

    test("iterator") {
        val iter = Bytes.from(0x01u, 0x02u, 0x03u).iterator()
        iter.hasNext() shouldBe true
        iter.next() shouldBe 0x01
        iter.hasNext() shouldBe true
        iter.next() shouldBe 0x2
        iter.hasNext() shouldBe true
        iter.next() shouldBe 0x03
        iter.hasNext() shouldBe false
    }

    test("containsAll") {
        val sut = Bytes.from(0x01u, 0x02u, 0x03u)
        sut.containsAll(listOf(0x02, 0x01)) shouldBe true
        sut.containsAll(listOf(0x02, 0x04)) shouldBe false
    }

    test("contains") {
        val sut = Bytes.from(0x01u, 0x02u, 0x03u)
        sut.contains(0x02u) shouldBe true
        sut.contains(0x04u) shouldBe false

        sut.contains(0x02) shouldBe true
        sut.contains(0x04) shouldBe false
    }

    test("hashCode") {
        checkAll(Arb.uByteArray(Arb.int(min = 0, max = 1024), Arb.uByte())) { a ->
            val sut = Bytes.wrap(a)
            sut.hashCode() shouldBe a.contentHashCode()
        }
    }

    test("toString") {
        checkAll(Arb.byteArray(Arb.int(min = 0, max = 1024), Arb.byte())) { a ->
            val sut = Bytes.wrap(a)
            sut.toString() shouldBe a.contentToString()
        }
    }

    @Suppress("ReplaceCallWithBinaryOperator")
    test("equals") {
        checkAll(Arb.uByteArray(Arb.int(min = 1, max = 256), Arb.uByte())) { a ->
            val sut = Bytes.wrap(a)
            sut.equals(Bytes.from(a)) shouldBe true
            sut.equals(Bytes.from(a.inv())) shouldBe false
            sut.equals(sut) shouldBe true

            // other type
            sut.equals(a) shouldBe false
        }
        // 0 size
        Bytes.allocate(0).equals(Bytes.empty()) shouldBe true
    }

    test("contentEquals") {
        checkAll(Arb.uByteArray(Arb.int(min = 1, max = 256), Arb.uByte())) { a ->
            val sut = Bytes.wrap(a)
            sut.contentEquals(Bytes.from(a)) shouldBe true
            sut.contentEquals(Bytes.from(a.inv())) shouldBe false
        }
        // 0 size
        Bytes.allocate(0).contentEquals(Bytes.empty()) shouldBe true
    }

    test("get") {
        val sut = Bytes.from(0x01u, 0x02u, 0x03u)
        sut[0] shouldBe 0x01u
        sut[1] shouldBe 0x02u
        sut[2] shouldBe 0x03u

        // 範囲外
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut[-1]
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut[3]
        }
    }

    test("set") {
        val arr = ubyteArrayOf(0x01u, 0x02u, 0x03u)
        val sut = Bytes.wrap(arr)
        sut[0] = 0x04u
        sut[1] = 0x05u
        sut[2] = 0x06u
        arr shouldBe ubyteArrayOf(0x04u, 0x05u, 0x06u)

        sut[0] = 0x07
        sut[1] = 0x08
        sut[2] = 0x09
        arr shouldBe ubyteArrayOf(0x07u, 0x08u, 0x09u)
    }

    test("copyRange") {
        val sut = Bytes.allocate(10) { it.toUByte() }
        sut.copyRange(0, 5) shouldBe Bytes.from(0x00u, 0x01u, 0x02u, 0x03u, 0x04u)
        sut.copyRange(2, 5) shouldBe Bytes.from(0x02u, 0x03u, 0x04u)

        // 範囲外
        assertThrows<IndexOutOfBoundsException> {
            sut.copyRange(2, 11)
        }
        assertThrows<IndexOutOfBoundsException> {
            sut.copyRange(-1, 4)
        }
    }

    test("copyOf") {
        val sut = Bytes.allocate(10) { it.toUByte() }
        sut.copyOf() shouldBe sut

        // 0サイズ
        sut.copyOf(0) shouldBe Bytes.empty()

        // 元のサイズより小さい
        sut.copyOf(2) shouldBe Bytes.from(0x00u, 0x01u)
        sut.copyOf(2, ResizeStrategy.ZERO_INDEX) shouldBe Bytes.from(0x00u, 0x01u)
        sut.copyOf(2, ResizeStrategy.MAX_LENGTH) shouldBe Bytes.from(0x08u, 0x09u)

        // 元のサイズと同じ
        sut.copyOf(10) shouldBe sut

        // 元のサイズより大きい
        sut.copyOf(20) shouldBe Bytes.allocate(20) { if (it < 10) it.toUByte() else 0x00u }
        sut.copyOf(20, ResizeStrategy.ZERO_INDEX) shouldBe Bytes.allocate(20) { if (it < 10) it.toUByte() else 0x00u }
        sut.copyOf(20, ResizeStrategy.MAX_LENGTH) shouldBe Bytes.allocate(20) { maxOf(0, it - 10).toUByte() }

        // 範囲外
        assertThrows<NegativeArraySizeException> {
            sut.copyOf(-1)
        }
    }

    test("getByte") {
        val sut = Bytes.from(0xffu, 0x00u, 0xa5u)
        sut.getByte(0) shouldBe 0xff.toByte()
        sut.getByte(1) shouldBe 0x00.toByte()
        sut.getByte(2) shouldBe 0xa5.toByte()

        // 範囲外
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getByte(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getByte(3)
        }
    }

    test("getUByte") {
        val sut = Bytes.from(0xffu, 0x00u, 0xa5u)
        sut.getUByte(0) shouldBe 0xffu
        sut.getUByte(1) shouldBe 0x00u
        sut.getUByte(2) shouldBe 0xa5u

        // 範囲外
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getUByte(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getUByte(3)
        }
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getShort(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getShortLe(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getUShort(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getUShortLe(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getInt(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getIntLe(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getUInt(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getUIntLe(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getInt24(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getInt24Le(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getUInt24(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getUInt24Le(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getLong(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getLongLe(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getULong(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
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
        assertThrows<ArrayIndexOutOfBoundsException> {
            sut.getULongLe(-1)
        }
        assertThrows<ArrayIndexOutOfBoundsException> {
            // 2 + 8byte = over
            sut.getULongLe(2)
        }
    }

    test("put(byte)") {
        checkAll(Arb.byte()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)

            sut.put(0, a) shouldBe sut
            buf[0] shouldBe a
            buf[1] shouldBe 0
            buf[2] shouldBe 0
            buf[3] shouldBe 0
        }
    }

    test("put(ubyte)") {
        checkAll(Arb.uByte()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)

            sut.put(0, a) shouldBe sut
            buf[0] shouldBe a.toByte()
            buf[1] shouldBe 0
            buf[2] shouldBe 0
            buf[3] shouldBe 0
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

    test("putIntLe(int)") {
        checkAll(Arb.int()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putInt(1, a)

            sut.putIntLe(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
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

    test("putIntLe(uint)") {
        checkAll(Arb.uInt()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putInt(1, a.toInt())

            sut.putIntLe(1, a) shouldBe sut
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

    test("putLongLe(long)") {
        checkAll(Arb.long()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putLong(1, a)

            sut.putLongLe(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
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

    test("putLongLe(ulong)") {
        checkAll(Arb.uLong()) { a ->
            val buf = ByteArray(10)
            val sut = Bytes.wrap(buf)
            val exp = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN).putLong(1, a.toLong())

            sut.putLongLe(1, a) shouldBe sut
            sut.array() shouldBe exp.array()
        }
    }

    test("put(vararg byte)") {
        checkAll(Arb.byteArray(Arb.int(min = 0, max = 10), Arb.byte())) { a ->
            val b = a.copyOf(10)
            val sut = Bytes.allocate(10)

            sut.put(0, b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7], b[8], b[9]) shouldBe sut
            sut.array() shouldBe b
        }
    }

    test("put(vararg ubyte)") {
        checkAll(Arb.uByteArray(Arb.int(min = 0, max = 10), Arb.uByte())) { a ->
            val b = a.copyOf(10)
            val sut = Bytes.allocate(10)

            sut.put(0, b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7], b[8], b[9]) shouldBe sut
            sut.uarray() shouldBe b
        }
    }

    test("put(byteArray)") {
        checkAll(Arb.byteArray(Arb.int(min = 0, max = 10), Arb.byte())) { a ->
            val b = a.copyOf(10)
            val sut = Bytes.allocate(10)

            sut.put(0, a) shouldBe sut
            sut.array() shouldBe b
        }
    }

    test("put(uByteArray)") {
        checkAll(Arb.uByteArray(Arb.int(min = 0, max = 10), Arb.uByte())) { a ->
            val b = a.copyOf(10)
            val sut = Bytes.allocate(10)

            sut.put(0, a) shouldBe sut
            sut.uarray() shouldBe b
        }
    }

    test("inv") {
        val sut = Bytes.from(0xffu, 0x00u, 0xa5u)

        // 反転していること
        // 上書きされていないこと
        sut.inv() shouldBe Bytes.from(0x00u, 0xffu, 0x5au)
        sut.inv(false) shouldBe Bytes.from(0x00u, 0xffu, 0x5au)
        sut shouldBe Bytes.from(0xffu, 0x00u, 0xa5u)

        // 上書きされていること
        sut.inv(true)
        sut shouldBe Bytes.from(0x00u, 0xffu, 0x5au)
    }

    test("and") {
        val sut = Bytes.from(0xffu, 0x00u, 0xa5u)
        sut and sut shouldBe sut
        sut and Bytes.allocate(3, 0xffu) shouldBe sut
        sut and Bytes.allocate(3, 0x00u) shouldBe Bytes.allocate(3, 0x00u)

        // 上書きされていないこと
        sut.and(sut) shouldBe sut
        sut.and(Bytes.allocate(3, 0xffu), false) shouldBe sut
        sut.and(Bytes.allocate(3, 0x00u), false) shouldBe Bytes.allocate(3, 0x00u)
        sut shouldBe Bytes.from(0xffu, 0x00u, 0xa5u)

        // 上書きされていること
        sut.and(Bytes.from(0xf0u, 0x0fu, 0x8fu), true) shouldBe Bytes.from(0xf0u, 0x00u, 0x85u)
        sut shouldBe Bytes.from(0xf0u, 0x00u, 0x85u)

        // サイズ不一致
        assertThrows<IllegalArgumentException> {
            sut and Bytes.allocate(1)
        }
    }

    test("or") {
        val sut = Bytes.from(0xffu, 0x00u, 0xa5u)
        sut or sut shouldBe sut
        sut or Bytes.allocate(3, 0xffu) shouldBe Bytes.allocate(3, 0xffu)
        sut or Bytes.allocate(3, 0x00u) shouldBe sut

        // 上書きされていないこと
        sut.or(sut) shouldBe sut
        sut.or(Bytes.allocate(3, 0xffu), false) shouldBe Bytes.allocate(3, 0xffu)
        sut.or(Bytes.allocate(3, 0x00u), false) shouldBe sut
        sut shouldBe Bytes.from(0xffu, 0x00u, 0xa5u)

        // 上書きされていること
        sut.or(Bytes.from(0x0fu, 0x0au, 0x5au), true) shouldBe Bytes.from(0xffu, 0x0au, 0xffu)
        sut shouldBe Bytes.from(0xffu, 0x0au, 0xffu)

        // サイズ不一致
        assertThrows<IllegalArgumentException> {
            sut or Bytes.allocate(1)
        }
    }

    test("xor") {
        val sut = Bytes.from(0xffu, 0x00u, 0xa5u)
        sut xor sut shouldBe Bytes.allocate(3, 0x00u)
        sut xor Bytes.allocate(3, 0xffu) shouldBe Bytes.from(0x00u, 0xffu, 0x5au)
        sut xor Bytes.allocate(3, 0x00u) shouldBe sut

        // 上書きされていないこと
        sut.xor(sut) shouldBe Bytes.allocate(3, 0x00u)
        sut.xor(Bytes.allocate(3, 0xffu), false) shouldBe Bytes.from(0x00u, 0xffu, 0x5au)
        sut.xor(Bytes.allocate(3, 0x00u), false) shouldBe sut
        sut shouldBe Bytes.from(0xffu, 0x00u, 0xa5u)

        // 上書きされていること
        sut.xor(Bytes.from(0x0fu, 0x0au, 0x55u), true) shouldBe Bytes.from(0xf0u, 0x0au, 0xf0u)
        sut shouldBe Bytes.from(0xf0u, 0x0au, 0xf0u)

        // サイズ不一致
        assertThrows<IllegalArgumentException> {
            sut xor Bytes.allocate(1)
        }
    }

    test("reverse") {
        checkAll(Arb.int(min = 2, max = 256)) { a ->
            val arr = UByteArray(a) { it.toUByte() }
            val sut = Bytes.from(arr)

            // 上書きされないこと
            sut.reverse() shouldBe Bytes.wrap(arr.reversedArray())
            sut.reverse(false) shouldBe Bytes.wrap(arr.reversedArray())
            sut.uarray() shouldBe arr

            // 上書きされること
            sut.reverse(true) shouldBe Bytes.wrap(arr.reversedArray())
            sut.uarray() shouldNotBe arr
        }
        // 0サイズ
        Bytes.empty().reverse() shouldBe Bytes.empty()
        Bytes.empty().reverse(true) shouldBe Bytes.empty()
    }

    test("shl") {
        val sut = Bytes.from(0xffu, 0x00u, 0xa5u)
        sut shl 0 shouldBe sut
        sut shl 1 shouldBe Bytes.from(0xfeu, 0x01u, 0x4au)
        sut shl 2 shouldBe Bytes.from(0xfcu, 0x02u, 0x94u)
        sut shl 8 shouldBe Bytes.from(0x00u, 0xa5u, 0x00u)

        // 上書きされていないこと
        sut.shl(0, false) shouldBe sut
        sut.shl(1) shouldBe Bytes.from(0xfeu, 0x01u, 0x4au)
        sut.shl(1, false) shouldBe Bytes.from(0xfeu, 0x01u, 0x4au)
        sut.shl(2, false) shouldBe Bytes.from(0xfcu, 0x02u, 0x94u)
        sut.shl(8, false) shouldBe Bytes.from(0x00u, 0xa5u, 0x00u)
        sut shouldBe Bytes.from(0xffu, 0x00u, 0xa5u)

        // 上書きされていること
        sut.shl(1, true) shouldBe Bytes.from(0xfeu, 0x01u, 0x4au)
        sut shouldBe Bytes.from(0xfeu, 0x01u, 0x4au)

    }

    test("shr") {
        val sut = Bytes.from(0xffu, 0x00u, 0xa5u)
        sut shr 0 shouldBe sut
        sut shr 1 shouldBe Bytes.from(0x7fu, 0x80u, 0x52u)
        sut shr 2 shouldBe Bytes.from(0x3fu, 0xc0u, 0x29u)
        sut shr 8 shouldBe Bytes.from(0x00u, 0xffu, 0x00u)

        // 上書きされていないこと
        sut.shr(0, false) shouldBe sut
        sut.shr(1) shouldBe Bytes.from(0x7fu, 0x80u, 0x52u)
        sut.shr(1, false) shouldBe Bytes.from(0x7fu, 0x80u, 0x52u)
        sut.shr(2, false) shouldBe Bytes.from(0x3fu, 0xc0u, 0x29u)
        sut.shr(8, false) shouldBe Bytes.from(0x00u, 0xffu, 0x00u)
        sut shouldBe Bytes.from(0xffu, 0x00u, 0xa5u)

        // 上書きされていること
        sut.shr(1, true) shouldBe Bytes.from(0x7fu, 0x80u, 0x52u)
        sut shouldBe Bytes.from(0x7fu, 0x80u, 0x52u)
    }

    test("getBit") {
        checkAll(Arb.int(min = 0, max = 31)) { a ->
            val sut = Bytes.random(4)
            val exp = BitSet.valueOf(ByteBuffer.wrap(sut.array().reversedArray()))
            sut.getBit(a) shouldBe exp.get(a)
        }
    }

    test("getBitLe") {
        checkAll(Arb.int(min = 0, max = 31)) { a ->
            val sut = Bytes.random(4)
            val exp = BitSet.valueOf(sut.array())
            sut.getBitLe(a) shouldBe exp.get(a)
        }
    }

    test("switchBit") {
        checkAll(Arb.int(min = 0, max = 31)) { a ->
            val sut = Bytes.allocate(4)
            val exp = BitSet.valueOf(sut.array())
            exp.set(a, true)
            sut.switchBit(a, true, inPlace = true).array() shouldBe exp.toByteArray().copyOf(4).reversedArray()
            exp.set(a, false)
            sut.switchBit(a, false).array() shouldBe exp.toByteArray().copyOf(4).reversedArray()
        }
    }

    test("switchBitLe") {
        checkAll(Arb.int(min = 0, max = 31)) { a ->
            val sut = Bytes.allocate(4)
            val exp = BitSet.valueOf(sut.array())
            exp.set(a, true)
            sut.switchBitLe(a, true, inPlace = true).array() shouldBe exp.toByteArray().copyOf(4)
            exp.set(a, false)
            sut.switchBitLe(a, false).array() shouldBe exp.toByteArray().copyOf(4)
        }
    }

    test("flipBit") {
        checkAll(Arb.int(min = 0, max = 31)) { a ->
            val sut = Bytes.allocate(4)
            val exp = BitSet.valueOf(sut.array())
            exp.flip(a)
            sut.flipBit(a).array() shouldBe exp.toByteArray().copyOf(4).reversedArray()
        }
    }

    test("flipBitLe") {
        checkAll(Arb.int(min = 0, max = 31)) { a ->
            val sut = Bytes.allocate(4)
            val exp = BitSet.valueOf(sut.array())
            exp.flip(a)
            sut.flipBitLe(a).array() shouldBe exp.toByteArray().copyOf(4)
        }
    }

    test("toHexString") {
        checkAll(Arb.uByteArray(Arb.int(min = 0, max = 1024), Arb.uByte())) { a ->
            val sut = Bytes.wrap(a)
            sut.toHexString() shouldBe a.toHexString()
        }
    }

    context("companion object") {
        test("allocate") {
            checkAll(Arb.int(min = 1, max = 1024)) { a ->
                val sut = Bytes.allocate(a)
                sut.size shouldBe a
                sut.all { it == 0.toByte() } shouldBe true
            }

            // デフォルト値指定
            checkAll(Arb.int(min = 0, max = 1024)) { a ->
                val sut = Bytes.allocate(a, 0x12u)
                sut.size shouldBe a
                sut.all { it == 0x12.toByte() } shouldBe true
            }

            // 0サイズ
            Bytes.allocate(0) shouldBe Bytes.empty()
            Bytes.allocate(0, 0x00) shouldBe Bytes.empty()
            Bytes.allocate(0, 0x00u) shouldBe Bytes.empty()
            Bytes.allocate(0) { 0x00u } shouldBe Bytes.empty()
        }

        test("empty") {
            Bytes.empty().size shouldBe 0
        }

        test("from") {
            // varargs Byte
            checkAll(Arb.byteArray(length = Arb.int(1..100), content = Arb.byte())) { a ->
                val sut = Bytes.from(*a)
                sut.size shouldBe a.size
                sut.array() shouldBe a
                // 同じインスタンスを共有していないこと
                sut.array() shouldNotBeSameInstanceAs a
            }

            // varargs UByte
            checkAll(Arb.uByteArray(length = Arb.int(1..100), content = Arb.uByte())) { a ->
                val sut = Bytes.from(*a)
                sut.size shouldBe a.size
                sut.uarray() shouldBe a
                // 同じインスタンスを共有していないこと
                sut.uarray() shouldNotBeSameInstanceAs a
            }

            // ByteArray
            checkAll(Arb.byteArray(length = Arb.int(1..100), content = Arb.byte())) { a ->
                val sut = Bytes.from(a)
                sut.size shouldBe a.size
                sut.array() shouldBe a
                // 同じインスタンスを共有していないこと
                sut.array() shouldNotBeSameInstanceAs a
            }

            // UByteArray
            checkAll(Arb.uByteArray(length = Arb.int(1..100), content = Arb.uByte())) { a ->
                val sut = Bytes.from(a)
                sut.size shouldBe a.size
                sut.uarray() shouldBe a
                // 同じインスタンスを共有していないこと
                sut.uarray() shouldNotBeSameInstanceAs a
            }

            // ByteBuffer
            checkAll(Arb.byteArray(length = Arb.int(1..100), content = Arb.byte())) { a ->
                val buffer = ByteBuffer.wrap(a)
                val sut = Bytes.from(buffer)
                sut.size shouldBe a.size
                sut.array() shouldBe a
                // 同じインスタンスを共有していないこと
                sut.array() shouldNotBeSameInstanceAs a
            }

            // Bytes
            checkAll(Arb.uByteArray(length = Arb.int(1..100), content = Arb.uByte())) { a ->
                val dummy = Bytes.from(a)
                val sut = Bytes.from(dummy)
                sut.size shouldBe a.size
                sut.uarray() shouldBe a
                // 同じインスタンスを共有していないこと
                sut.uarray() shouldNotBeSameInstanceAs a
                sut.uarray() shouldNotBeSameInstanceAs dummy.uarray()
                sut shouldNotBeSameInstanceAs dummy
            }

            // 0size
            Bytes.from(UByteArray(0)) shouldBe Bytes.empty()
            Bytes.from(ByteArray(0)) shouldBe Bytes.empty()
            Bytes.from(ByteBuffer.allocate(0)) shouldBe Bytes.empty()
            Bytes.from(Bytes.allocate(0)) shouldBe Bytes.empty()
            Bytes.from(*arrayOf()) shouldBe Bytes.empty()
        }

        test("random") {
            // サイズ0の時
            Bytes.random(0).size shouldBe 0

            checkAll(Arb.int(min = 10, max = 1024)) { a ->
                val sut = Bytes.random(a)
                sut.size shouldBe a
                sut.all { it == 0.toByte() } shouldBe false
            }
        }

        test("wrap") {
            // ByteArray
            checkAll(Arb.byteArray(length = Arb.int(1..100), content = Arb.byte())) { a ->
                val sut = Bytes.wrap(a)
                sut.size shouldBe a.size
                sut.array() shouldBe a
                // 同じインスタンスを共有していること
                sut.array() shouldBeSameInstanceAs a
            }

            // UByteArray
            checkAll(Arb.uByteArray(length = Arb.int(1..100), content = Arb.uByte())) { a ->
                val sut = Bytes.wrap(a)
                sut.size shouldBe a.size
                sut.uarray() shouldBe a
                // 同じインスタンスを共有していること
                // inlineクラスは、参照の等価評価が出来ないため、内容を書き換えて等価であることで評価する
                Random.nextUBytes(a)
                sut.uarray() shouldBe a
            }

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
