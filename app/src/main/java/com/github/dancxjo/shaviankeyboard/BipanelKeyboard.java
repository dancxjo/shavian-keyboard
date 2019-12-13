package com.github.dancxjo.shaviankeyboard;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ToggleButton;

public class BipanelKeyboard extends LinearLayout implements View.OnClickListener {
    static class Key {
        void onPress(Context context, InputConnection inputConnection, boolean rColoring, boolean iotating) {
        }

        String getDisplayName(boolean rColoring, boolean iotating) {
            return "";
        }
    }

    static class FourStateKey extends Key {
        final String clear;
        final String rColored;
        final String iotated;
        final String rColoredAndIotated;

        FourStateKey(String clear, String rColored, String iotated, String rColoredAndIotated) {
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

    static class RColoredLetterKey extends FourStateKey {
        RColoredLetterKey(String clear, String rColored) {
            super(clear, rColored, "", "");
        }
    }

    static class IotatedLetterKey extends FourStateKey {
        IotatedLetterKey(String clear, String iotated) {
            super(clear, "", iotated, "");
        }
    }

    static class LetterKey extends FourStateKey {
        LetterKey(String clear) {
            super(clear, "", "", "");
        }
    }

    static class SpaceKey extends LetterKey {
        SpaceKey() {
            super(" ");
        }
    }

    static class BackspaceKey extends Key {
        public void onPress(Context context, InputConnection inputConnection, boolean rColoring, boolean iotating) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                inputConnection.deleteSurroundingTextInCodePoints(1, 0);
            } else {
                inputConnection.deleteSurroundingText(1, 0);
            }
        }

        public String getDisplayName(boolean r, boolean i) {
            return "back";
        }
    }

    static class EnterKey extends Key {
        public int action = EditorInfo.IME_ACTION_GO;
        public String message;

        public void onPress(Context context, InputConnection inputConnection, boolean rColoring, boolean iotating) {
            inputConnection.performEditorAction(action);
        }

        public String getDisplayName(boolean r, boolean i) {
            if (message != null) {
                return message;
            }
            return "enter";
        }
    }

    private ToggleButton rColoringToggle;
    private ToggleButton iotatingToggleButton;

    private final SparseArray<Key> keys = new SparseArray<>();
    private InputConnection inputConnection;

    public BipanelKeyboard(Context context) {
        this(context, null, 0);
    }

    public BipanelKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BipanelKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
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
        addKey(R.id.enter, new EnterKey());
        addKey(R.id.letter_peep, new FourStateKey(getLetter(R.string.letter_peep), "!", "A", "a"));
        addKey(R.id.letter_kick, new FourStateKey(getLetter(R.string.letter_kick), "@", "B", "b"));
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
        addKey(R.id.letter_roar, new LetterKey(getLetter(R.string.letter_roar)));
        addKey(R.id.letter_nun, new LetterKey(getLetter(R.string.letter_nun)));

        addKey(R.id.letter_if, iotated(new RColoredLetterKey(getLetter(R.string.letter_if), getLetter(R.string.letter_ear))));
        addKey(R.id.letter_egg, iotated(lightRColored(new LetterKey(getLetter(R.string.letter_egg)))));
        addKey(R.id.letter_ash, iotated(lightRColored(new LetterKey(getLetter(R.string.letter_ash)))));
        addKey(R.id.letter_on, iotated(new RColoredLetterKey(getLetter(R.string.letter_on), getLetter(R.string.letter_or))));
        addKey(R.id.letter_ado, new FourStateKey(getLetter(R.string.letter_ado), getLetter(R.string.letter_array), getLetter(R.string.letter_ian), getLetter(R.string.letter_ear)));
        addKey(R.id.letter_wool, iotated(rColored(new LetterKey(getLetter(R.string.letter_wool)))));
        addKey(R.id.letter_out, iotated(rColored(new LetterKey(getLetter(R.string.letter_out)))));
        addKey(R.id.letter_ah, iotated(new RColoredLetterKey(getLetter(R.string.letter_ah), getLetter(R.string.letter_are))));
        addKey(R.id.letter_eat, iotated(new RColoredLetterKey(getLetter(R.string.letter_eat), getLetter(R.string.letter_ear))));
        addKey(R.id.letter_age, iotated(new RColoredLetterKey(getLetter(R.string.letter_age), getLetter(R.string.letter_air))));
        addKey(R.id.letter_ice, iotated(lightRColored(new LetterKey(getLetter(R.string.letter_ice)))));
        addKey(R.id.letter_up, iotated(new RColoredLetterKey(getLetter(R.string.letter_up), getLetter(R.string.letter_err))));
        addKey(R.id.letter_oak, iotated(new RColoredLetterKey(getLetter(R.string.letter_oak), getLetter(R.string.letter_or))));
        addKey(R.id.letter_ooze, rColored(new IotatedLetterKey(getLetter(R.string.letter_ooze), getLetter(R.string.letter_yew))));
        addKey(R.id.letter_oil, iotated(rColored(new LetterKey(getLetter(R.string.letter_oil)))));
        addKey(R.id.letter_awe, iotated(new RColoredLetterKey(getLetter(R.string.letter_awe), getLetter(R.string.letter_or))));
    }

    public void setEditorInfo(EditorInfo info) {
        setAction(info.actionId);
        if (info.actionLabel != null) {
            setMessage(info.actionLabel.toString());
        } else {
            setMessage("enter");
        }
    }

    public void setAction(int newAction) {
        EnterKey key = (EnterKey) keys.get(R.id.enter);

        if (key == null) {
            return;
        }

        key.action = newAction;
    }

    public void setMessage(String newMessage) {
        EnterKey key = (EnterKey) keys.get(R.id.enter);

        if (key == null) {
            return;
        }

        key.message = newMessage;
    }


    private String iotated(String letter) {
        return getLetter(R.string.letter_yea) + letter;
    }

    private FourStateKey iotated(RColoredLetterKey letter) {
        return new FourStateKey(letter.clear, letter.rColored, iotated(letter.clear), iotated(letter.rColored));
    }

    private RColoredLetterKey lightRColored(LetterKey letter) {
        return new RColoredLetterKey(letter.clear, letter.clear + getLetter(R.string.letter_roar));
    }


    private FourStateKey rColored(IotatedLetterKey letter) {
        return new FourStateKey(letter.clear, letter.clear + getLetter(R.string.letter_array), letter.iotated, letter.iotated + getLetter(R.string.letter_array));
    }

    private RColoredLetterKey rColored(LetterKey letter) {
        return new RColoredLetterKey(letter.clear, letter.clear + getLetter(R.string.letter_array));
    }

    String rColored(String letter) {
        return letter + getLetter(R.string.letter_roar);
    }

    private void addKey(int id, Key key) {
        Button button = findViewById(id);
        button.setOnClickListener(this);
        keys.put(id, key);
    }

    private String getLetter(int id) {
        return getContext().getResources().getText(id).toString();
    }

    private void updateNames() {
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
