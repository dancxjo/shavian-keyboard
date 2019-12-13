package com.something.better.than.huh.shaviankeyboard;

import android.text.Html;

public class Shavian {
    public static Character PEEP = new Character(0x10450, "p", "PEEP", Kind.CONSONANT);
    public static Character TOT = new Character(0x10451, "t", "TOT", Kind.CONSONANT);
    public static Character KICK = new Character(0x10452, "k", "KICK", Kind.CONSONANT);
    public static Character FEE = new Character(0x10453, "f", "FEE", Kind.CONSONANT);
    public static Character THIGH = new Character(0x10454, "T", "THIGH", Kind.CONSONANT);
    public static Character SO = new Character(0x10455, "s", "SO", Kind.CONSONANT);
    public static Character SURE = new Character(0x10456, "S", "SURE", Kind.CONSONANT);
    public static Character CHURCH = new Character(0x10457, "c", "CHURCH", Kind.CONSONANT);
    public static Character YEA = new Character(0x10458, "j", "YEA", Kind.CONSONANT);
    public static Character HUNG = new Character(0x10459, "N", "HUNG", Kind.CONSONANT);
    public static Character BIB = new Character(0x1045a, "b", "BIB", Kind.CONSONANT);
    public static Character DEAD = new Character(0x1045b, "d", "DEAD", Kind.CONSONANT);
    public static Character GAG = new Character(0x1045c, "g", "GAG", Kind.CONSONANT);
    public static Character VOW = new Character(0x1045d, "v", "VOW", Kind.CONSONANT);
    public static Character THEY = new Character(0x1045e, "H", "THEY", Kind.CONSONANT);
    public static Character ZOO = new Character(0x1045f, "z", "ZOO", Kind.CONSONANT);
    public static Character MEASURE = new Character(0x10460, "Z", "MEASURE", Kind.CONSONANT);
    public static Character JUDGE = new Character(0x10461, "J", "JUDGE", Kind.CONSONANT);
    public static Character WOE = new Character(0x10462, "w", "WOE", Kind.CONSONANT);
    public static Character HAHA = new Character(0x10463, "h", "HA-HA", Kind.CONSONANT);
    public static Character LOLL = new Character(0x10464, "l", "LOLL", Kind.CONSONANT);
    public static Character MIME = new Character(0x10465, "m", "MIME", Kind.CONSONANT);
    public static Character IF = new Character(0x10466, "i", "IF", Kind.VOWEL);
    public static Character EGG = new Character(0x10467, "e", "EGG", Kind.VOWEL);
    public static Character ASH = new Character(0x10468, "A", "ASH", Kind.VOWEL);
    public static Character ADO = new Character(0x10469, "a", "ADO", Kind.VOWEL);
    public static Character ON = new Character(0x1046a, "o", "ON", Kind.VOWEL);
    public static Character WOOL = new Character(0x1046b, "U", "WOOL", Kind.VOWEL);
    public static Character OUT = new Character(0x1046c, "Q", "OUT", Kind.VOWEL);
    public static Character AH = new Character(0x1046d, "y", "AH", Kind.VOWEL);
    public static Character ROAR = new Character(0x1046e, "r", "ROAR", Kind.CONSONANT);
    public static Character NUN = new Character(0x1046f, "n", "NUN", Kind.CONSONANT);
    public static Character EAT = new Character(0x10470, "I", "EAT", Kind.VOWEL);
    public static Character AGE = new Character(0x10471, "A", "AGE", Kind.VOWEL);
    public static Character ICE = new Character(0x10472, "F", "ICE", Kind.VOWEL);
    public static Character UP = new Character(0x10473, "u", "UP", Kind.VOWEL);
    public static Character OAK = new Character(0x10474, "O", "OAK", Kind.VOWEL);
    public static Character OOZE = new Character(0x10475, "M", "OOZE", Kind.VOWEL);
    public static Character OIL = new Character(0x10476, "q", "OIL", Kind.VOWEL);
    public static Character AWE = new Character(0x10477, "Y", "AWE", Kind.VOWEL);
    public static Character ARE = new Character(0x10478, "R", "ARE", Kind.LIGATURE);
    public static Character OR = new Character(0x10479, "P", "OR", Kind.LIGATURE);
    public static Character AIR = new Character(0x1047a, "X", "AIR", Kind.LIGATURE);
    public static Character ERR = new Character(0x1047b, "x", "ERR", Kind.LIGATURE);
    public static Character ARRAY = new Character(0x1047c, "D", "ARRAY", Kind.LIGATURE);
    public static Character EAR = new Character(0x1047d, "C", "EAR", Kind.LIGATURE);
    public static Character IAN = new Character(0x1047e, "W", "IAN", Kind.LIGATURE);
    public static Character YEW = new Character(0x1047f, "V", "YEW", Kind.LIGATURE);

    public static class Character {
        int codePoint;
        String ghoti;
        String gloss;
        Kind kind;


        public Character(int codePoint, String ghoti, String gloss, Kind kind) {
            this.codePoint = codePoint;
            this.ghoti = ghoti;
            this.gloss = gloss.toLowerCase();
            this.kind = kind;
        }

        public String toString() {
            return Html.fromHtml(String.format("&#x%s;", Integer.toHexString(codePoint))).toString();
        }
    }


    static Character[] ALPHABET = {
            Shavian.PEEP,
            Shavian.TOT,
            Shavian.KICK,
            Shavian.FEE,
            Shavian.THIGH,
            Shavian.SO,
            Shavian.SURE,
            Shavian.CHURCH,
            Shavian.YEA,
            Shavian.HUNG,
            Shavian.BIB,
            Shavian.DEAD,
            Shavian.GAG,
            Shavian.VOW,
            Shavian.THEY,
            Shavian.ZOO,
            Shavian.MEASURE,
            Shavian.JUDGE,
            Shavian.WOE,
            Shavian.HAHA,
            Shavian.LOLL,
            Shavian.MIME,
            Shavian.IF,
            Shavian.EGG,
            Shavian.ASH,
            Shavian.ADO,
            Shavian.ON,
            Shavian.WOOL,
            Shavian.OUT,
            Shavian.AH,
            Shavian.ROAR,
            Shavian.NUN,
            Shavian.EAT,
            Shavian.AGE,
            Shavian.ICE,
            Shavian.UP,
            Shavian.OAK,
            Shavian.OOZE,
            Shavian.OIL,
            Shavian.AWE,
            Shavian.ARE,
            Shavian.OR,
            Shavian.AIR,
            Shavian.ERR,
            Shavian.ARRAY,
            Shavian.EAR,
            Shavian.IAN,
            Shavian.YEW
    };

    static Character getCharFromGhoti(String ghoti) {
        for (Character c : Shavian.ALPHABET) {
            if (c.ghoti.equals(ghoti)) {
                return c;
            }
        }

        return null;
    }
}


























































