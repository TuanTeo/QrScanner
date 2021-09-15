package com.tuanteo.qrscanner.object;

public class ItemCardView {
    private int mBackground;
    private int mLogo;
    private String mNameItem;
    private String mWebLink;

    public ItemCardView(int mBackground, int logo, String mNameItem, String mWebLink) {
        this.mBackground = mBackground;
        this.mNameItem = mNameItem;
        this.mWebLink = mWebLink;
        this.mLogo = logo;
    }

    public int getLogo() {
        return mLogo;
    }

    public int getBackground() {
        return mBackground;
    }

    public void setBackground(int mBackground) {
        this.mBackground = mBackground;
    }

    public String getNameItem() {
        return mNameItem;
    }

    public void setNameItem(String mNameItem) {
        this.mNameItem = mNameItem;
    }

    public String getWebLink() {
        return mWebLink;
    }

    public void setWebLink(String mWebLink) {
        this.mWebLink = mWebLink;
    }
}
