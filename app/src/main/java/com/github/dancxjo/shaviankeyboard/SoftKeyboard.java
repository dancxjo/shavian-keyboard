package com.github.dancxjo.shaviankeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

public class SoftKeyboard extends InputMethodService {

    private InputMethodManager mInputMethodManager;
    private BipanelKeyboard keyboard;

    @Override
    public void onCreate() {
        super.onCreate();
        mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }

    @Override
    public void onInitializeInterface() {
    }

    @Override
    public View onCreateInputView() {
        keyboard = new BipanelKeyboard(this);
        return keyboard;
    }

    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        keyboard.setEditorInfo(info);
        keyboard.setInputConnection(getCurrentInputConnection());
    }

}
