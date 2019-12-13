package com.something.better.than.huh.shaviankeyboard;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ToggleButton;

public class MyKeyboard extends LinearLayout implements View.OnClickListener {
    public static class Key {
        public void onPress(Context context, InputConnection inputConnection, boolean rColoring, boolean iotating) {
            return;
        }

        public String getDisplayName(boolean rColoring, boolean iotating) {
            return "";
        }
    }

    public static class FourStateKey extends Key {
        String clear, rColored, iotated, rColoredAndIotated;

        public FourStateKey(String clear, String rColored, String iotated, String rColoredAndIotated) {
            this.clear = clear;
            this.rColored = rColored;
            this.iotated = iotated;
            this.rColoredAndIotated = rColoredAndIotated;
        }

        public void onPress(Context context, InputConnection inputConnection, boolean rColoring, boolean iotating) {
            String text = getDisplayName(rColoring, iotating);
            inputConnection.commitText(text, text.length());
        }

        public String getDisplayName(boolean rColoring, boolean iotating) {
            if (!rColoring) {
                if (!iotating) {
                    return clear;
                }
                return iotated;
            } else {
                if (!iotating) {
                    return rColored;
                }
                return rColoredAndIotated;
            }
        }
    }

    public static class RColoredLetterKey extends FourStateKey {
        public RColoredLetterKey(String clear, String rColored) {
            super(clear, rColored, "", "");
        }
    }

    public static class IotatedLetterKey extends FourStateKey {
        public IotatedLetterKey(String clear, String iotated) {
            super(clear, "", iotated, "");
        }
    }

    public static class LetterKey extends FourStateKey {
        public LetterKey(String clear) {
            super(clear, "", "", "");
        }
    }


    public static class SpaceKey extends LetterKey {
        public SpaceKey() {
            super(" ");
        }
    }

    public static class BackspaceKey extends Key {
        public void onPress(Context context, InputConnection inputConnection, boolean rColoring, boolean iotating) {
            inputConnection.deleteSurroundingText(1, 0);
        }

        public String getDisplayName(boolean r, boolean i) {
            return "back";
        }
    }

    ToggleButton rColoringToggle;
    ToggleButton iotatingToggleButton;

    private SparseArray<Key> keys = new SparseArray<>();
    private SparseArray<String> displayNames = new SparseArray<>();
    private InputConnection inputConnection;

    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.bipanel, this, true);

        rColoringToggle = findViewById(R.id.toggle_r_color);
        iotatingToggleButton = findViewById(R.id.iota_toggle);

        rColoringToggle.setOnClickListener(this);
        iotatingToggleButton.setOnClickListener(this);

        addKey(R.id.spacebar, new SpaceKey());
        addKey(R.id.back, new BackspaceKey());
        addKey(R.id.letter_ado, new FourStateKey(getLetter(R.string.letter_ado), getLetter(R.string.letter_array), getLetter(R.string.letter_ian), getLetter(R.string.letter_ear)));
        addKey(R.id.letter_peep, new LetterKey(getLetter(R.string.letter_peep)));
        addKey(R.id.letter_kick, new LetterKey(getLetter(R.string.letter_kick)));
        addKey(R.id.letter_tot, new LetterKey(getLetter(R.string.letter_tot)));
        addKey(R.id.letter_fee, new LetterKey(getLetter(R.string.letter_fee)));
        addKey(R.id.letter_thigh, new LetterKey(getLetter(R.string.letter_thigh)));
        addKey(R.id.letter_so, new LetterKey(getLetter(R.string.letter_so)));
        addKey(R.id.letter_sure, new LetterKey(getLetter(R.string.letter_sure)));
        addKey(R.id.letter_church, new LetterKey(getLetter(R.string.letter_church)));
        addKey(R.id.letter_yea, new LetterKey(getLetter(R.string.letter_yea)));
        addKey(R.id.letter_hung, new LetterKey(getLetter(R.string.letter_hung)));
        addKey(R.id.letter_bib, new LetterKey(getLetter(R.string.letter_bib)));
        addKey(R.id.letter_dead, new LetterKey(getLetter(R.string.letter_dead)));
        addKey(R.id.letter_gag, new LetterKey(getLetter(R.string.letter_gag)));
        addKey(R.id.letter_vow, new LetterKey(getLetter(R.string.letter_vow)));
        addKey(R.id.letter_they, new LetterKey(getLetter(R.string.letter_they)));
        addKey(R.id.letter_zoo, new LetterKey(getLetter(R.string.letter_zoo)));
        addKey(R.id.letter_measure, new LetterKey(getLetter(R.string.letter_measure)));
        addKey(R.id.letter_judge, new LetterKey(getLetter(R.string.letter_judge)));
        addKey(R.id.letter_woe, new LetterKey(getLetter(R.string.letter_woe)));
        addKey(R.id.letter_haha, new LetterKey(getLetter(R.string.letter_haha)));
        addKey(R.id.letter_loll, new LetterKey(getLetter(R.string.letter_loll)));
        addKey(R.id.letter_mime, new LetterKey(getLetter(R.string.letter_mime)));
        addKey(R.id.letter_if, iotated(new RColoredLetterKey(getLetter(R.string.letter_if), getLetter(R.string.letter_ear))));
        addKey(R.id.letter_egg, iotated(lightRColored(new LetterKey(getLetter(R.string.letter_egg)))));
        addKey(R.id.letter_ash, iotated(lightRColored(new LetterKey(getLetter(R.string.letter_ash)))));
        addKey(R.id.letter_on, iotated(new RColoredLetterKey(getLetter(R.string.letter_on), getLetter(R.string.letter_or))));
        addKey(R.id.letter_wool, iotated(rColored(new LetterKey(getLetter(R.string.letter_wool)))));
        addKey(R.id.letter_out, iotated(rColored(new LetterKey(getLetter(R.string.letter_out)))));
        addKey(R.id.letter_ah, iotated(new RColoredLetterKey(getLetter(R.string.letter_ah), getLetter(R.string.letter_are))));
        addKey(R.id.letter_roar, new LetterKey(getLetter(R.string.letter_roar)));
        addKey(R.id.letter_nun, new LetterKey(getLetter(R.string.letter_nun)));
        addKey(R.id.letter_eat, iotated(new RColoredLetterKey(getLetter(R.string.letter_eat), getLetter(R.string.letter_ear))));
        addKey(R.id.letter_age, iotated(new RColoredLetterKey(getLetter(R.string.letter_age), getLetter(R.string.letter_air))));
        addKey(R.id.letter_ice, iotated(lightRColored(new LetterKey(getLetter(R.string.letter_ice)))));
        addKey(R.id.letter_up, iotated(new RColoredLetterKey(getLetter(R.string.letter_up), getLetter(R.string.letter_err))));
        addKey(R.id.letter_oak, iotated(new RColoredLetterKey(getLetter(R.string.letter_oak), getLetter(R.string.letter_or))));
        addKey(R.id.letter_ooze, rColored(new IotatedLetterKey(getLetter(R.string.letter_ooze), getLetter(R.string.letter_yew))));
        addKey(R.id.letter_oil, iotated(rColored(new LetterKey(getLetter(R.string.letter_oil)))));
        addKey(R.id.letter_awe, iotated(new RColoredLetterKey(getLetter(R.string.letter_awe), getLetter(R.string.letter_or))));
    }

    String iotated(String letter) {
        return getLetter(R.string.letter_yea) + letter;
    }

    FourStateKey iotated(RColoredLetterKey letter) {
        return new FourStateKey(letter.clear, letter.rColored, iotated(letter.clear), iotated(letter.rColored));
    }

    RColoredLetterKey lightRColored(LetterKey letter) {
        return new RColoredLetterKey(letter.clear, letter.clear + getLetter(R.string.letter_roar));
    }


    FourStateKey rColored(IotatedLetterKey letter) {
        return new FourStateKey(letter.clear, letter.clear + getLetter(R.string.letter_array), letter.iotated, letter.iotated + getLetter(R.string.letter_array));
    }

    RColoredLetterKey rColored(LetterKey letter) {
        return new RColoredLetterKey(letter.clear, letter.clear + getLetter(R.string.letter_array));
    }

    String rColored(String letter) {
        return letter + getLetter(R.string.letter_roar);
    }

    void addKey(int id, Key key) {
        Button button = findViewById(id);
        button.setOnClickListener(this);
        keys.put(id, key);
    }

    String getLetter(int id) {
        return getContext().getResources().getText(id).toString();
    }

    void updateNames() {
        for (int i = 0; i < keys.size(); i++) {
            int id = keys.keyAt(i);
            Key key = keys.get(id);
            String displayName = key.getDisplayName(rColoringToggle.isChecked(), iotatingToggleButton.isChecked());
            Button button = findViewById(id);
            button.setText(displayName);
        }
    }

    @Override
    public void onClick(View view) {
        if (inputConnection == null) {
            return;
        }

        int id = view.getId();

        if (id == R.id.toggle_r_color || id == R.id.iota_toggle) {
            updateNames();
        }

        Key k = keys.get(id);

        if (k == null) {
            return;
        }

        boolean i = iotatingToggleButton.isChecked();
        boolean r = rColoringToggle.isChecked();

        iotatingToggleButton.setChecked(false);
        rColoringToggle.setChecked(false);
        updateNames();

        k.onPress(getContext(), inputConnection, r, i);
    }

    public void setInputConnection(InputConnection inputConnection) {
        this.inputConnection = inputConnection;
    }
}
