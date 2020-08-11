package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class PorckerGame {

    private CardStuck cardStuck = new CardStuck();

    private List<Card> playerCards;

    public void Begin() {
        //開始メッセージ
        Msg.showOpeningMsg();
        //ポーカー1ゲームの勝負開始のメッセージ
        Msg.showBeginGameMsg();
        //カードを5枚配る
        deliveryPlayerCard();

        //テスト
        playerCards.set(0,new Card(42));
        playerCards.set(1, new Card(0));
        playerCards.set(2, new Card(2));
        playerCards.set(3, new Card(4));
        playerCards.set(4, new Card(6));
        //手持ちのカードを並び替える
        sortPlayerCard();

        //手持ちのカードを画面に表示する
        showPlayerCards();

        //チェンジするカードの入力受付
        List<Integer> changeCardIndexList = inputChangeCard();
        //カードを入れ替える
        changeCard((changeCardIndexList));

        //手持ちのカードを並び替える
        sortPlayerCard();

        //手持ちのカードを画面に表示する
        showPlayerCards();

        Hand hand = new Hand(playerCards);

        //Handクラスのメソッドの呼び出し
        //役の判定
        //役の名前の出力
        hand.judge();
        System.out.println(hand.get_handName());

    }


    /**
     * カードを5枚配る
     */
    private void deliveryPlayerCard() {
        playerCards = cardStuck.takeCards(5);
    }

    /**
     * カードをS→H→D→Cの順に並び替える
     */
    private void sortPlayerCard() {
        Collections.sort(playerCards);
    }

    /**
     * 手札を出力する
     */
    private void showPlayerCards() {
        for (Card playerCard : playerCards) {
            //カードをs1～c13で出力
            System.out.print("プレイヤー:"+playerCard.getDisplayString() + " ");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * カードを入れ替える
     * エラーメッセージの表示
     *
     * @return changeCardIndexList
     */
    private List<Integer> inputChangeCard() {
        List<Integer> changeCardIndexList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            //チェンジするカードの番号の入力依頼メッセージ
            Msg.showCardChangeMsg();
            String changeCardIndexString = "";
            //ユーザーに入力を促す
            try {
                changeCardIndexString = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }


            // 0-5の文字以外があれば再入力
            if (Pattern.compile("^[0-5]+$").matcher(changeCardIndexString).matches() == false) {
                // エラーメッセージの表示
                Msg.showChangeNumErrMsg();
                continue;
            }

            // 重複した数字があればエラー
            if (hasSameNumberError(changeCardIndexString)) {
                // エラーメッセージの表示
                Msg.showSameNumberErrMsg();
                continue;
            }

            // 0が入力されている場合
            if (changeCardIndexString.contains("0")) {
                // 0が入っているのに他の数字が入っている場合にはエラーとして再度入力を促す
                if (Pattern.compile("[1-5]+").matcher(changeCardIndexString).find()) {
                    // 変更しない場合には0のみを入力させるメッセージ
                    Msg.showZeroOnlyErrMsg();
                    continue;
                }
                // changeCardIndexListには何も入れずに終わる
                // →戻りのListは、Count = 0
                break;
            }

            for (int i = 1; i < 6; i++) {
                // 含まれていたらchangeCardIndexListに追加する。
                if (changeCardIndexString.contains(String.valueOf(i))) {
                    changeCardIndexList.add(i - 1);
                }
            }
            break;
        }
        return changeCardIndexList;
    }


    /**
     * 入力された値の重複チェックを行う
     * @param target 入力された値
     * @return hasSameNumberError 文字列内に同じ数字があればtrueを返す
     */
    private boolean hasSameNumberError(String target) {
        for (int i = 0; i < 10; i++) {
            if (charCount(target, (char) (i + '0')) > 1) {
                //エラーがあったらtrue
                return true;
            }
        }
        return false;
    }

    /**
     *文字列内の対象となる文字列をカウントする
     * @param str 対象となる文字列
     * @param checkChar　カウントの対象となる文字
     * @return
     */
    int charCount(String str, char checkChar) {
        int count = 0;
        for (char x : str.toCharArray()) {
            if (x == checkChar) {
                count++;
            }
        }
        return count;
    }

    /**
     * 入力された数字の
     * @param changeCardIndex 　入れ替え後のカードのリスト
     */
    private void changeCard(List<Integer> changeCardIndex) {
        if (changeCardIndex.size() == 0) {
            //0なら何もしない
        } else {
            //入力された数字に位置するカードを入れ替える
            for (int i = 0; i < changeCardIndex.size(); i++) {
                int playerCardIndexs = changeCardIndex.get(i);
                Card newCard = cardStuck.takeCard();
                playerCards.set(playerCardIndexs, newCard);
            }
        }
    }


}