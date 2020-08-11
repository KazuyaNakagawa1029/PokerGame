package com.company;
import java.util.List;

public class Hand {

    /**
     * @return 役名称を取得
     */
    public String get_handName() {
        return _handName;
    }

    private final List<Card> _Cards;
    private int[] _cardNumbers;

    //役を判定するためのフラグを用意する
    private boolean _isRoyalStraightFlash = false;
    private boolean _isStraightFlash = false;
    private boolean _isFourCard = false;
    private boolean _isFullHouse = false;
    private boolean _isFlash = false;
    private boolean _isStraight = false;
    private boolean _isThreeCard = false;
    private boolean _isTwoPair = false;
    private boolean _isOnePair = false;


    private boolean _isRoyal = false;

    private String  _handName = "";



    /**
     * コンストラクタ
     * @param cards 役判定対象のカードリスト
     */
    public Hand(List<Card> cards) {
        _Cards = cards;
        countNumber();
    }


    /**
     * 役を判定する
     */
    public void judge() {
        _isFlash = isFlash();
        _isStraight = isStraight();

        // フラッシュではなくストレートでもない場合の処理
        // フラッシュかストレートならペア系の役は発生しない
        if (!_isFlash && !_isStraight) {
            _isFourCard = isFourCard();
            _isThreeCard = isThreeCard();
            _isTwoPair = isTwoPair();
            _isOnePair = isOnePair();
        }

        // 複合役（フルハウスなど）の判定
        handFukugou();

        //役名称の設定
        handName();
    }


    /**
     *役の判定結果から役名を返す
     * @return _handName 役の名前
     */
    private void handName() {
        // 役のフラグにより役名を確定させる
        if (_isRoyalStraightFlash) {
            _handName = "ロイヤルストレートフラッシュ";
        } else if (_isStraightFlash) {
            _handName = "ストレートフラッシュ";
        } else if (_isFourCard) {
            _handName = "4カード";
        } else if (_isFullHouse) {
            _handName = "フルハウス";
        } else if (_isFlash) {
            _handName = "フラッシュ";
        }else if(_isStraight){
            _handName = "ストレート";
        } else if (_isThreeCard) {
            _handName = "3カード";
        } else if (_isTwoPair) {
            _handName = "2ペア";
        } else if (_isOnePair) {
            _handName = "1ペア";
        } else {
            _handName = "ハイカード";
        }

    }

    /**
     * 何番のカードが何枚あるかカウントを行う
     */
    private void countNumber() {
        //枚数を入れる配列の宣言と初期化
        int[] countNumber = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (Card card : _Cards) {
            //取ったカードを定数(13)で割った余りからカードの数字を求める
            int _cardsNumber = card.getCardNumber();
            countNumber[_cardsNumber-1]++;
        }
        _cardNumbers = countNumber;
    }


    /**
     * 同じ数字が4枚存在している場合、フォーカードと判定する
     */
    private boolean isFourCard() {
        for (int cardNumber : _cardNumbers) {
            if (cardNumber == 4) {
                return true;
            }
        }
        return false;
    }


    /**
     * 5枚すべてが同じマークの場合、フラッシュと判定する
     */
    private boolean isFlash() {
        int firstCardMarkNumber = _Cards.get(0).getCardMark();
        for (int i = 1; i < _Cards.size(); i++) {
            //取ったカードが先のカードと同じマークかを判定
            if (firstCardMarkNumber != _Cards.get(i).getCardMark()) {
                return false;
            }
        }
        return true;
    }


    /**
     * 5枚が連続した数字で構成されている場合、ストレートと判定する
     */
    private boolean isStraight() {
        int minNumber = -1;
        int maxNumber = 0;
        for (int i = 0; i < _cardNumbers.length; i++) {
            //　同じカード番号(1~13)の数をチェックして2枚以上となっている場合はストレートでない
            if (_cardNumbers[i] > 1) {
                return false;
            }
            if (_cardNumbers[i] == 1) {
                //iが1の場合、最大値を入れ続ける
                maxNumber = i;
                if (minNumber == -1) {
                    minNumber = i;
                }
            }
        }

        //LoyalStraightFlashの判定
        //10-J-Q-K-A
        if (_cardNumbers[9] == 1 && _cardNumbers[10] == 1 && _cardNumbers[11] == 1 && _cardNumbers[12] == 1 && _cardNumbers[0] == 1) {
            //_isLoyalフラグ
            _isRoyal = true;
            return true;
        }

        //数が入っている最大値-最小値が4なら、5枚が連続で構成されている
        return maxNumber - minNumber == 4;
    }

    /**
     * 　同じ数字が4枚存在している場合、フォーカードと判定する
     */
    private boolean isThreeCard() {
        for (int cardNumber : _cardNumbers) {
            if (cardNumber == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * カウントアップ数が2の場合ツーペアと判定する
     */
    private boolean isTwoPair() {

        //カウントアップした数が2の場合は2ペア
        return countPair() == 2;
    }

    /**
     * 　カウントアップ数が1の場合ワンペアと判定する
     */
    private boolean isOnePair() {

        //カウントアップした数が1の場合は1ペア
        return countPair() == 1;

    }

    /**
     * ワンペアかツーペアかを判定するためのカウントアップを行う
     * @return ペア数（0~2）
     */
    private int countPair(){
        int counter = 0;
        for (int cardNumber : _cardNumbers) {
            if (cardNumber == 2) {
                //iが2ならカウントアップする
                counter++;
            }
        }

        return  counter;
    }

    /**
     * ワンペアでスリーカードの場合にフルハウスの判定を行い、
     * ストレートでフラッシュである場合、_isLoyalを使ってロイヤルストレートフラッシュかストレートフラッシュかを判定
     */
    private void handFukugou(){

        //フルハウスの場合、スリーカードとワンペアのフラグを解除する
        if (_isThreeCard && _isOnePair) {
            _isFullHouse = true;
            _isThreeCard = false;
            _isOnePair = false;
        }

        if (_isStraight && _isFlash) {
            if (_isRoyal) {
                _isRoyalStraightFlash = true;
            } else {
                _isStraightFlash = true;
            }
            //
            _isStraight = false;
            _isFlash = false;
        }

    }


}


