package com.company;

import java.util.ArrayList;
import java.util.List;

public class Card implements Comparable<Card>{
    /**
     * カードマークの画面表示文字列格納リスト
     * S:0 H:1 H:2 C:3
     */
    private static final List<String> CARD_MARK = new ArrayList<String>() {{
        add("S");
        add("H");
        add("D");
        add("C");
    }};
    /**
     * 同一マークのカード枚数 1~13
     */
    public static final int CARD_NUMBER = 13;
    /**
     * 全カード枚数
     */
    public static final int CARD_ALL_NUMBER = 52;



    /**
     * 0~51の番号をもらってS1~C13を返す
     * @param cardIndex 0~51の番号
     * @return S1~C13の文字列
     */
    public static String convertCardIndexToDisplayString(int cardIndex) {
        //カードマーク部分のIndexを取得
        int markIndex = getCardMarkNumber(cardIndex);
        //カードの番号部分を取得
        int cardNumber =getCardNumber(cardIndex);

        return CARD_MARK.get(markIndex) + cardNumber;
    }


    /**
     * 0~51の数字を受け取り、S,H,D,Cのマーク文字列に変換する
     * @param  cardIndex 0~51の数字
     * @return S,H,D,Cのマーク文字列
     */
    public static int getCardMarkNumber(int cardIndex) {
        return cardIndex / CARD_NUMBER;
    }

    /**
     * 0~51の数字を受け取り、1~13のカード番号に変換する
     * @param cardIndex 0~51の数字
     * @return 1~13
     */
    public static int getCardNumber(int cardIndex) {
        return cardIndex % CARD_NUMBER + 1;
    }

    private int cardIndex = -1;
    private int cardMark = -1;
    private int cardNumber = -1;

    /**
     * @return S,H,D,Cの文字列
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * @return 1~13のカード番号
     */
    public int getCardMark() {
        return cardMark;
    }
    public int get_cardIndex() {
        return cardIndex;
    }
    /**
     * コンストラクタ
     * @param cardIndex 0~51の数字
     */
    public  Card(int cardIndex){
        this.cardIndex = cardIndex;
        cardMark = Card.getCardMarkNumber(cardIndex);
        cardNumber = Card.getCardNumber(cardIndex);
    }

    /**
     * 画面に表示するカード文字列
     * @return 例：S2 H13
     */
    public  String getDisplayString(){
        return  Card.convertCardIndexToDisplayString(cardIndex);
    }

    /**
     * カードIndex(0~51)の取得
     * @return 0~51の数値
     */

    @Override
    public int compareTo(Card o) {
        return this.get_cardIndex() - o.get_cardIndex();
    }


}
