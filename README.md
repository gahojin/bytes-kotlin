# Bytes Utility Library for Kotlin

Kotlin向けバイト配列操作ライブラリ

[![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/gahojin/bytes-kotlin/build.yml)](https://github.com/gahojin/bytes-kotlin/actions/workflows/build.yml)
[![Maven Central Version](https://img.shields.io/maven-central/v/jp.co.gahojin.bytes-kotlin/bytes-kotlin)](https://central.sonatype.com/artifact/jp.co.gahojin.bytes-kotlin/bytes-kotlin)
[![javadoc](https://javadoc.io/badge2/jp.co.gahojin.bytes-kotlin/bytes-kotlin/javadoc.svg)](https://javadoc.io/doc/jp.co.gahojin.bytes-kotlin/bytes-kotlin)
[![GitHub License](https://img.shields.io/github/license/gahojin/bytes-kotlin)](LICENSE)

## Get Started

### Gradle

依存を追加します

`${latest.version}` is [![Maven Central Version](https://img.shields.io/maven-central/v/jp.co.gahojin.bytes-kotlin/bytes-kotlin)](https://central.sonatype.com/artifact/jp.co.gahojin.bytes-kotlin/bytes-kotlin)

```groovy
dependencies {
    implementation "jp.co.gahojin.bytes-kotlin:bytes-kotlin:${latest.version}"
}
```

### Examples

```kotlin
val b = Bytes.wrap(someByteArray)
b.put(0, 0x01)
b.putIntLe(1, 12345)
val result = b.array()
```

## API 

### Bytes

`Bytes` クラスは `ByteArray` のラッパーで、ビット操作やエンディアン変換などの便利なメソッドを提供します。

#### パフォーマンスと設計方針

- **ゼロコピー**: `Bytes.wrap(array)` や `Bytes.array()` は内部配列を直接参照します。大規模なデータ操作においてコピーのオーバーヘッドを避け、高いパフォーマンスを実現します。
- **ミュータブル**: `Bytes` クラスのメソッド（`putInt` 等）は、ラップしている内部配列を直接書き換えます。
- **正確なビット操作**: 内部的に `Byte` の符号拡張を適切に処理しており、負の値を含むバイト配列でも正しく数値の読み書きが可能です。

#### インスタンスの生成

```kotlin
// アロケート
val b1 = Bytes.allocate(10)

// ラップ（元の配列を共有、コピーが発生しない）
val b3 = Bytes.wrap(byteArrayOf(0x01, 0x02))

// コピーから生成
val b4 = Bytes.from(0x01.toByte(), 0x02.toByte())
```

#### 基本操作

```kotlin
val b = Bytes.allocate(4)
b.putInt(0, 12345678)
val i = b.getInt(0)
val hex = b.toHexString()
```

#### 配列へのアクセス

```kotlin
val b = Bytes.allocate(4)
val ref = b.array()  // 内部配列への参照を返す（変更はbに反映される）
```

## License

```
Copyright 2025, GAHOJIN, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
