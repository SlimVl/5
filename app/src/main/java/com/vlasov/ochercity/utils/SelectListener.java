package com.vlasov.ochercity.utils;


public interface SelectListener {
    void Select(int position, CharSequence text);
    void Select(int position, CharSequence text, String id);
    /*void Select(View view, int position, CharSequence text, String id, float additional_price);*/
}
