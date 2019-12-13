package com.something.better.than.huh.shaviankeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View;

public class GhotiKeyboard extends LinearLayout implements View.OnClickListener {
    public static class Key {
        String shifted;
        String unshifted;
        int id = 0;
        static int currentId = 999;

        public Key(String shifted, String unshifted) {
            this.shifted = shifted;
            this.unshifted = unshifted;
        }

        public Key(Shavian.Character shifted, Shavian.Character unshifted) {
            this.unshifted = unshifted.toString();
            this.shifted = shifted.toString();
        }

        int getId() {
            if (id == 0) {
                id = currentId++;
            }
            return id;
        }

        public Button getButton(Context context) {
            Button button = new Button(context);
            button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setId(getId());
            button.setText(this.unshifted);
            return button;
        }
    }

    private SparseArray<Key> keys = new SparseArray<>();
    private InputConnection inputConnection;

    public GhotiKeyboard(Context context) {
        this(context, null, 0);
    }

    public GhotiKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GhotiKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.ghoti, this, true);

        ViewGroup row1 = findViewById(R.id.row1);
        Key[][] keys = {{
                new Key("~", "`"),
                new Key("!", "1"),
                new Key("@", "2"),
                new Key("#", "3"),
                new Key("$", "4"),
                new Key("%", "5"),
                new Key("^", "6"),
                new Key("&", "7"),
                new Key("*", "8"),
                new Key("(", "9"),
                new Key(")", "0"),
                new Key("_", "-"),
                new Key("+", "="),
        }, {
                new Key(Shavian.OIL, Shavian.OUT),
                new Key(Shavian.IAN, Shavian.WOE),
                new Key(Shavian.AGE, Shavian.EGG),
                new Key(Shavian.ARE, Shavian.ROAR),
                new Key(Shavian.THIGH, Shavian.TOT),
                new Key(Shavian.AWE, Shavian.AH),
                new Key(Shavian.WOOL, Shavian.UP),
                new Key(Shavian.EAT, Shavian.IF),
                new Key(Shavian.OAK, Shavian.ON),
                new Key(Shavian.OR, Shavian.PEEP),
                new Key("{", "["),
                new Key("}", "]"),
                new Key("|", "\\"),
        }, {
                new Key(Shavian.ASH, Shavian.ADO),
                new Key(Shavian.SURE, Shavian.SO),
                new Key(Shavian.ARRAY, Shavian.DEAD),
                new Key(Shavian.ICE, Shavian.FEE),
                new Key(Shavian.GAG.toString(), "·"),
                new Key(Shavian.THEY, Shavian.TOT),
                new Key(Shavian.JUDGE, Shavian.YEA),
                new Key(Shavian.KICK, Shavian.KICK),
                new Key(Shavian.LOLL, Shavian.LOLL),
                new Key(":", ";"),
                new Key("\"", "'"),
        }, {
                new Key(Shavian.MEASURE, Shavian.ZOO),
                new Key(Shavian.AIR, Shavian.ERR),
                new Key(Shavian.EAR, Shavian.CHURCH),
                new Key(Shavian.YEW, Shavian.VOW),
                new Key(Shavian.BIB, Shavian.BIB),
                new Key(Shavian.HUNG, Shavian.HAHA),
                new Key(Shavian.MIME, Shavian.MIME),
                new Key(",", ","),
                new Key(".", "."),
                new Key("?", "/"),

        }};

        addRow(row1, keys[0]);
        ViewGroup row2 = findViewById(R.id.row2);
        addRow(row2, keys[1]);

        ViewGroup row3 = findViewById(R.id.row3);
        addRow(row3, keys[2]);

        ViewGroup row4 = findViewById(R.id.row4);
        addRow(row4, keys[3]);
//         ViewGroup row5 = findViewById(R.id.row5);
//         addRow(row5, keys[4]);

    }

    private void addRow(ViewGroup row, Key[] keys) {
        for (int i = 0; i < keys.length; i++) {
            Key key = keys[i];
            Button button = key.getButton(row.getContext());
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            button.setLayoutParams(params);
            //button.setWidth(0);
            this.keys.put(key.getId(), key);
            row.addView(button);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        if (inputConnection == null) {
            return;
        }

        int id = view.getId();
        Key k = keys.get(id);
        inputConnection.commitText(k.unshifted, 1);
    }

    public void setInputConnection(InputConnection inputConnection) {
        this.inputConnection = inputConnection;
    }
}
