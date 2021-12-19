# プログラミング言語論 課題1 レポート
情報工学科 4年 7番 市古 空

## 1. 配布したプログラムについて次のことを説明してください

### 1. models パッケージのクラス、インファーフェースの関係

  このパッケージはインターフェース1つ、クラス3つの構成になっている。具体的には IContent インターフェースとその実装クラスである AbstractContent クラス、さらに AbstractContent のサブクラスである Music、Movie クラスが存在する。

  今回の課題においてこの構成で重要となる点は Movie、 Music クラスは IContent インターフェースの実装を持っているという点だ。それによって外部からは IContent インターフェースを型として扱うことで Movie、Music クラス、あるいは他の IContent インターフェースの実装をもつクラスを透過的に扱うことが出来る。

  続いて、AbstractContent の説明をする。この AbstractContent クラスは前段落の1行目で私が 「Movie、Music クラスは IContent インターフェースの実装である」と言い切れなかったことに関係する。正確な意味でIContent インターフェースの実装はこの AbstractContent クラスである。Movie、Music クラスは AbstractContent クラスのサブクラスである。このクラスの存在意義としては Music、Movie クラスに共通する処理を切り出すことと abstract 句を用いて抽象メソッドを定義することである。つまり AbstractContent クラスは Movie, Music クラスの抽象クラスであるといえる。
  
### 2. players パッケージのクラス、インファーフェースの関係

  このパッケージはインターフェース1つ、そのインターフェースの実装クラス1つの構成になっている。具体的には IPlayer インターフェースと Mp3Player クラスである。Mp3Player クラスは独自の実装（コンストラクタ、インスタンス変数）で models.Music クラスに依存している。後述するがこの依存が今回の課題の改善点である。

## 2. IPlayer インターフェースを用いた実装をし、その実行結果と実装内容を説明してください


#### 新たに追加した IPlayerImpl.class
```java
package players;

import models.IContent;
import java.util.List;

public class IPlayerImpl implements IPlayer {
    List<IContent> iContents;
    int selected = 0;
    boolean playing = false;

    public IPlayerImpl(List<IContent> iContents) {
        this.iContents = iContents;
        System.out.println("IPlayerImpl Created");
    }

    private boolean isInRange(int n){
        if(n < 0 || n >= this.iContents.size()) return false; //out of range
        return true;
    }

    @Override
    public void play(){
        if(this.playing){
            System.out.println("Already Playing!!!");
        }else{
            System.out.println("Play " + this.iContents.get(this.selected).getContentType() + " - " + this.iContents.get(this.selected).getTitle() + " by " + this.iContents.get(this.selected).getArtist());
            this.playing = true;
        }
    }

    @Override
    public void stop(){
        if(this.playing){
            System.out.println("Stop " + this.iContents.get(this.selected).getContentType());
            this.playing = false;
        }else{
            System.out.println("No IContent is Playing");
        }
    }

    @Override
    public boolean select(int n){
        if(!isInRange(n)) return false;

        this.selected = n;
        return true;
    }

    @Override
    public boolean next(){
        return select(this.selected + 1);
    }

    @Override
    public boolean previous(){
        return select(this.selected - 1);
    }
}
```

#### 変更後の Main.java
```java
import players.*;

public class Main{
  public static void main(String[] args){
    var player = new IPlayerImpl(Getpa.getContentLibrary());
    Getpa.doSomething(player);
  }
}
```

#### 実行結果
```sh
/Library/Java/JavaVirtualMachines/jdk-17.0.1.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=64342:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/ichigo/github.com/igsr5/programing-language-assignments/1/out/production/1 Main Main
IPlayerImpl Created
Play Music - Ao by Mature Lady
Stop Music
Play Music - Citrus by Mature Lady
Stop Music
Play Music - トゥモローランド by リーマンZOO
Stop Music
Play Music - 解放区より by リーマンZOO
Stop Music
Play Music - Dear by ラノマインド
Stop Music
Play Music - GRAP by SAUL
Already Playing!!!
Stop Music
No IContent is Playing
Play Movie - GRAP [MV] by SAUL
Stop Movie
Play Movie - 光 [MV] by ラノマインド
Stop Movie
Play Movie - 解放区より [MV] by リーマンZOO
Stop Movie
Play Movie - Ao [Lylic Video] by Mature Lady
Stop Movie
Play Music - GRAP by SAUL
Stop Music

Process finished with exit code 0
```

### 詳細解説
今回の課題内容は「Music クラスしか再生できない Mp3Player クラスで Movie クラスも再生できるようにする」である。この課題を解決するための手段はいくつか考えられるが、私がとったアプローチは「ポリモーフィズムを用いて IContent インターフェースを扱えるプレイヤー(IPlayerImpl クラス)を実装する」方法である。

ポリモーフィズムとはインターフェースや抽象クラスを使用してクラスの持つ関数名とその入出力のみをクラスの外部に公開する概念である。クラスの外部からは関数名とその入出力のみが参照でき、関数内部の具体的な実装は隠蔽される。メリットとして、あるインターフェースを扱える実装はそのインターフェースの実装クラスも扱えることが挙げられる。

今回の課題で私が行ったことをもう少し具体的に説明すると、

- Mp3Player.java を元に新たに IPlayerImple.java を実装
  - models.Music を参照していたコードを models.IContent を参照するように変更
  - `play()` で出力されている文字列で コンテント名がハードコーディングされていたため、`IContent.getContentType()` を呼び出すように変更
  - インポート文の中から models.Music が消えていることから models.Music への依存が消えていることがわかる
- Main.java で 変数 `player` に上述の IPlayerImple クラスのインスタンスを代入
  - クラス初期化の引数に `Getpa.getContentLibrary()` （つまり IContent）を渡すようにした

`Getpa.doSomething()` の実装は公開されていないが、スライドで提供されたインターフェースと実行時の出力結果をみると引数にとった IPlayer インターフェースに対して `play()` や `stop()` などのメソッド呼び出しを複数回行っていることがわかる。


## 3. オブジェクト指向プログラミングに関する知識として、この課題の問題設定では足りていないとあなたが思うことを最低1つ挙げてください
```java
public class IPlayerImpl implements IPlayer {
    List<IContent> iContents;
    int selected = 0;
    boolean playing = false;
    
    // 省略
} 
```

上記のコードはこの課題で私が新たに作成した IPlayerImpl.java のコードの変数宣言部である。
本来ならカプセル化の基本概念に則ってスコープは private 句を指定し必要に応じてゲッター、セッターを定義するが、上記のコードではスコープの制限は行われていない。
今回はわかりやすいようにあえて既存実装をそのまま残したが、例えば`playing` を他クラスで書き換えることで一生再生できないプレイヤーを生み出してしまう可能性があるため変数、メソッドのスコープ管理は行った方が良い。
