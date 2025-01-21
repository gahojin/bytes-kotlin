# Bytes Utility Library for Kotlin

Kotlin向けバイト配列操作ライブラリ

[![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/gahojin/bytes-kotlin/byild.yml)](https://github.com/gahojin/bytes-kotlin/actions/workflows/build.yml)
[![Maven Central Version](https://img.shields.io/maven-central/v/jp.co.gahojin.bytes-kotlin/bytes-kotlin)](https://central.sonatype.com/artifact/io.github.crow-misia.libyuv/libyuv-android)
[![GitHub License](https://img.shields.io/github/license/gahojin/bytes-kotlin)](LICENSE)

## Get Started

### Gradle

依存を追加します

`${latest.version}` is [![Maven Central Version](https://img.shields.io/maven-central/v/jp.co.gahojin.bytes-kotlin/bytes-kotlin)](https://central.sonatype.com/artifact/io.github.crow-misia.libyuv/libyuv-android)

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
