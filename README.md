## StringSplitter

文字列を1行ごとに分割

## Background

ゲームの会話メッセージ表示などで、文字列を１行毎に処理したい場合がある

## Detail

課題1 改行コードで分割、リストに変換<br>
- 複数行の文字列を、改行コードで分割して、１行毎の文字列のリストに変換
- 改行コードが複数連続している場合は、空行もリストに追加
- メソッド名は splitWithLineBreakCode とする

課題2 さらに句点でも分割<br>
課題 1 の処理に次の仕様を加えた StringsMoreSplitter クラスを作成
- 句点（。）でも分割する
- ただし、句点（。）の直後に改行（¥n）がある場合は、句点（。）の直後の改行（¥n）は無視する
- メソッド名は splitWithLineBreakCodeAndPeriod とする

課題3 さらに固定長で分割<br>
課題 2 の処理に次の仕様を加えた StringsFixedLengthSplitter クラスを作成

文字列を指定した長さで分割する
メソッド名は splitFixedLengthWithLineBreakCodeAndPeriod とする

課題4 さらに禁則処理を追加<br>
課題 3 の処理に次の禁則処理を追加した StringsJaHyphenationSplitter クラスを作成

- 行頭禁則文字を句読点（、。）とする
- メソッド名は splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod とする

### インターフェース

#### CUI

実行例

課題1

``` console
１行目。
２行目。
３行目。
４行目。

５行目
```

課題2

``` console
１行目。
２行目。
３行目。
４行目。

５行目。
```

課題3

``` console
このプログラ
ムは、文字列
を指定された
幅で改行する
サンプルプロ
グラムです。
```

課題4

``` console
このプログラムは、
句読点を行頭禁則
処理するサンプル。
最後の行です
```

## Structure Overview

課題1

- src/
    - App
        - main()
        - splitWithLineBreakCode(String str)

課題2

- src/
    - App
        - main()
        - splitWithLineBreakCodeAndPeriod(String str)
        - splitWithPeriod(String str)
        - splitWithLineBreak(List<String> list)
        - hasString(int index)
        - isIgnoreLineBreak(int index)

課題3

- src/
    - App
        - main()
        - splitFixedLengthWithLineBreakCodeAndPeriod(String str, int width)
        - splitFixedLength(String str, int width)
        - isOver(int strLength, int endIndex)
        - splitWithPeriod(String str)
        - splitWithLineBreak(List<String> list)
        - hasString(int index)
        - isIgnoreLineBreak(int index)
        
課題4

- src/
    - App
        - main()
        - splitFixedLengthWithLineBreakCodeAndPeriod(String str, int width)
        - splitFixedLength(String str, int width)
        - isOver(int strLength, int endIndex)
        - splitWithPeriod(String str)
        - splitWithLineBreak(List<String> list)
        - hasString(int index)
        - isIgnoreLineBreak(int index)