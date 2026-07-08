package jp.co.gahojin.bytes

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

@OptIn(ExperimentalUnsignedTypes::class)
class ResizeTest : FunSpec({
    context("byteArray") {
        test("copyOf") {
            val sut = ByteArray(10) { it.toByte() }
            sut.copyOf() shouldBe sut

            // 0サイズ
            sut.copyOf(0) shouldBe ByteArray(0)

            // 元のサイズより小さい
            sut.copyOf(2) shouldBe byteArrayOf(0x00, 0x01)
            sut.copyOf(2, ResizeStrategy.ZERO_INDEX) shouldBe byteArrayOf(0x00, 0x01)
            sut.copyOf(2, ResizeStrategy.MAX_LENGTH) shouldBe byteArrayOf(0x08, 0x09)

            // 元のサイズと同じ
            sut.copyOf(10) shouldBe sut

            // 元のサイズより大きい
            sut.copyOf(20) shouldBe ByteArray(20) { if (it < 10) it.toByte() else 0x00 }
            sut.copyOf(
                20,
                ResizeStrategy.ZERO_INDEX
            ) shouldBe ByteArray(20) { if (it < 10) it.toByte() else 0x00 }
            sut.copyOf(20, ResizeStrategy.MAX_LENGTH) shouldBe ByteArray(20) { maxOf(0, it - 10).toByte() }

            // strategy 別の挙動 (size=3 -> newSize=5)
            // ZERO_INDEX: [1, 2, 3, 0, 0]
            // MAX_LENGTH: [0, 0, 1, 2, 3]
            val s3 = byteArrayOf(0x01, 0x02, 0x03)
            s3.copyOf(5, ResizeStrategy.ZERO_INDEX) shouldBe byteArrayOf(0x01, 0x02, 0x03, 0x00, 0x00)
            s3.copyOf(5, ResizeStrategy.MAX_LENGTH) shouldBe byteArrayOf(0x00, 0x00, 0x01, 0x02, 0x03)

            // strategy 別の挙動 (size=5 -> newSize=3)
            // ZERO_INDEX: [0, 1, 2]
            // MAX_LENGTH: [2, 3, 4]
            val s5 = byteArrayOf(0x00, 0x01, 0x02, 0x03, 0x04)
            s5.copyOf(3, ResizeStrategy.ZERO_INDEX) shouldBe byteArrayOf(0x00, 0x01, 0x02)
            s5.copyOf(3, ResizeStrategy.MAX_LENGTH) shouldBe byteArrayOf(0x02, 0x03, 0x04)

            // 範囲外
            shouldThrow<IllegalArgumentException> {
                sut.copyOf(-1, ResizeStrategy.MAX_LENGTH)
            }
        }
    }

    context("ubyteArray") {
        test("copyOf") {
            val sut = UByteArray(10) { it.toUByte() }
            sut.copyOf() shouldBe sut

            // 0サイズ
            sut.copyOf(0) shouldBe UByteArray(0)

            // 元のサイズより小さい
            sut.copyOf(2) shouldBe ubyteArrayOf(0x00u, 0x01u)
            sut.copyOf(2, ResizeStrategy.ZERO_INDEX) shouldBe ubyteArrayOf(0x00u, 0x01u)
            sut.copyOf(2, ResizeStrategy.MAX_LENGTH) shouldBe ubyteArrayOf(0x08u, 0x09u)

            // 元のサイズと同じ
            sut.copyOf(10) shouldBe sut

            // 元のサイズより大きい
            sut.copyOf(20) shouldBe UByteArray(20) { if (it < 10) it.toUByte() else 0x00u }
            sut.copyOf(
                20,
                ResizeStrategy.ZERO_INDEX
            ) shouldBe UByteArray(20) { if (it < 10) it.toUByte() else 0x00u }
            sut.copyOf(20, ResizeStrategy.MAX_LENGTH) shouldBe UByteArray(20) { maxOf(0, it - 10).toUByte() }

            // 範囲外
            shouldThrow<IllegalArgumentException> {
                sut.copyOf(-1, ResizeStrategy.ZERO_INDEX)
            }
        }
    }
})
