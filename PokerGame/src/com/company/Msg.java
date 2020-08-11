package com.company;

public class Msg {
    /**
     * *********************************
     * ********  ポーカーゲーム  *******
     * *********************************
     */
    public static void showOpeningMsg() {
        System.out.println("*********************************");
        System.out.println("********  ポーカーゲーム  *******");
        System.out.println("*********************************");
        System.out.println();
    }

    /**
     * ゲーム開始！
     * カードを配ります。
     */
    public static void showBeginGameMsg() {
        System.out.println("ゲーム開始！");
        System.out.println();
        System.out.println("カードを配ります。");
        System.out.println();
    }

    /**
     *チェンジするカードを1～5の数字で入力して下さい。
     * カードの変更がない場合には0を入力して下さい。
     */
    public static void showCardChangeMsg()
    {
        System.out.println("チェンジするカードを1～5の数字で入力して下さい。");
        System.out.println("カードの変更がない場合には0を入力して下さい。");
        System.out.println();
    }

    /**
     * 0～5までの数字で入力して下さい。
     */
    public static void showChangeNumErrMsg()
    {
        System.out.println("0～5までの数字で入力して下さい。");
        System.out.println();
    }

    /**
     *カードを変えない場合には0のみ入力して下さい。
     */
    public static void showZeroOnlyErrMsg()
    {
        System.out.println("カードを変えない場合には0のみ入力して下さい。");
        System.out.println();
    }

    /**
     * 数字が重複しています。
     */
    public static void showSameNumberErrMsg()
    {
        System.out.println("数字が重複しています。");
        System.out.println();
    }



}
