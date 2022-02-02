# プログラミング言語論 課題3 レポート
情報工学科 4年 7番 市古 空

## 0. はじめに
swi-prolog を処理系に使用した。
```sh
$ swipl --version
SWI-Prolog version 8.4.1 for arm64-darwin
```

ポケモンのタイプ相性などのドキュメントは https://wiki.xn--rckteqa2e.com/wiki/4%E5%80%8D%E5%BC%B1%E7%82%B9 を採用した。


## 1. pokemon.plにおいて定義されている述語をすべて挙げてください.

### `superEffective(X, Y)`

X, Y はポケモンのタイプを表現し、X → Y においてタイプ相性である「こうかばつぐん」であれば true を返す。

```prolog
/* 実行結果 - 格闘はノーマルに対してこうかばつぐん */
?- superEffective(fighting, normal).
true.
```




### `noEffect(X, Y)`

X, Y はポケモンのタイプを表現し、X → Y においてタイプ相性である「こうかなし」であれば true を返す。

```prolog
/* 実行結果 - ノーマルはゴーストに対してこうかなし */
?- noEffect(normal, ghost).
true.
```


### `notEffective(X, Y)`

X, Y はポケモンのタイプを表現し、X → Y においてタイプ相性である「こうかいまひとつ」であれば true を返す。

```prolog
/* 実行結果 - ノーマルはいわに対してこうかいまひとつ */
?- notEffective(normal, rock).
true.
```



### `effective(X, Y)`

X, Y はポケモンのタイプを表現し、X → Y においてタイプ相性である「こうかばつぐん」、「こうかなし」、「こうかいまひとつ」のどれにも当てはまらないとき true を返す。

```prolog
/* 実行結果 - ノーマルはノーマルに対して特に相性はない */
?- effective(normal, normal).
true.
```




### `resists(X, Y)`
X, Y はポケモンのタイプを表現し、X → Y においてタイプ相性が「こうかなし」、「こうかいまひとつ」のどちらかであれば true を返す。


```prolog
/* 実行結果 - ノーマルはいわに対してこうかなし */
?- resists(normal, rock).
true.
```

### `type(X)`

X はポケモンのタイプを表現する。

```prolog
/* 実行結果 - ノーマルというタイプが存在する */
?- type(normal).
true.

/* aaaというタイプは存在しない */
?- type(aaa).
false.
```


### `validDuo(X, Y)`
X, Y はポケモンのタイプを表現し、(X, Y)タイプの複合タイプが存在することを表現する。X ,Y の組み合わせの重複は考慮されていないため機能としては不完全である。

```prolog
/* 実行結果 - (こおり、ドラゴン)という複合タイプが存在する */
?- validDuo(ice, dragon).
true .

/* 実行結果 - (ノーマル、ゴースト)という複合タイプは存在しない */
?- validDuo(normal, ghost).
false.
```


### `pair(X, Y)`
X, Y はポケモンのタイプを表現し、(X, Y)タイプの複合タイプが存在することを表現する。

```prolog
pair(X, Y) :- validDuo(X, Y).
pair(Y, X) :- validDuo(X, Y).
```
のように定義されているため X, Y のタイプの組み合わせの重複に対応している。

```prolog
/* 実行結果 - (こおり、ドラゴン)という複合タイプが存在する */
?- validDuo(ice, dragon).
true .

/* (ノーマル、ゴースト)という複合タイプは存在しない */
?- validDuo(normal, ghost).
false.
```


## 2. 述語quadWeaknessの規則に追加して，4倍弱点のタイプを表す述語にしてください．

私の実装した `quadWeakness` の定義と実行結果を下記に示す。

```prolog
/* 定義 */
quadWeakness(Z, (X, Y)) :- pair(X, Y), X@>Y, superEffective(Z, X), superEffective(Z, Y).
```

```prolog
/* 実行結果 - 水タイプが4倍になる複合タイプは下記の通り */****
?- quadWeakness(water, (X, Y)).
X = rock,
Y = ground ;
X = ground,
Y = fire ;
X = rock,
Y = fire ;
false.
```

`quadWeakness` が true となる条件は

- X, Y の複合タイプが存在する (`pair`)
- Z → X に対してこうかばつぐんである (`superEffective`)
- Z → Y に対してこうかばつぐんである (`superEffective`)

の3つである。これらの条件に加え `X@>Y` を定義しているのは、(X, Y) と (Y, X) の2つの組み合わせを同一の複合タイプとして扱うためである。

実行結果を見ると水タイプが攻撃した際の4倍の複合タイプが正しく表示されていることがわかる。
