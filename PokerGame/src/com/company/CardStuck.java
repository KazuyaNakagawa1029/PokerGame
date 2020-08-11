package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardStuck {
    /**
     * 52枚のカードを入れるリストを作る
     */
    public List<Card> cards = new ArrayList<>();

    /**
     * コンストラクタ
     */
    public CardStuck() {
        //初期化：カードを52枚設定する
        createNewStack();
        //シャッフルメソッドを呼ぶ
        Shuffle();
    }

    /**
     *カードをリストに追加し山札を作成する
     * 使用する前にはシャッフルメソッドを呼び出すこと
     */
    public void createNewStack() {
        for (int i = 0; i < Card.CARD_ALL_NUMBER; i++) {
            Card card = new Card(i);
            cards.add(card);
        }
    }

    /**
     * カードをシャッフルする
     */
    public void Shuffle() {
        //山札のシャッフルを行う
        Collections.shuffle(cards);
    }

    /**
     *山札からカードを1枚取得する
     * @return 山札から抜いたカード
     */
    public Card takeCard() {
        //一番上のカードを取得する
        Card card = cards.get(0);
        //所得したカードを山札から取り除く
        cards.remove(0);
        //最初に所得したカードを戻り値として返す
        return card;
    }

    /**
     * 指定した枚数分のカードを取得する
     * @param takeCount 取得するカードの枚数
     * @return　取得されたカードの入ったリスト
     */
    public List<Card> takeCards(int takeCount) {
        //取得したカードを入れるリスト
        List<Card> takeCard = new ArrayList<>();
        //指定された枚数分のカードを取得する
        for (int i = 0; i < takeCount; i++) {
            takeCard.add(takeCard());
        }
        // 取得されたカードの入ったリストを戻り値として返す
        return takeCard;
    }

}