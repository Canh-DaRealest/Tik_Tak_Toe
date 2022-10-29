package com.example.tiktaktoe.model;

public class ChessType {
    private String TypeName;
    private int xImage;
    private int oImage;

    public ChessType(String typeName, int xImage, int oImage) {
        TypeName = typeName;
        this.xImage = xImage;
        this.oImage = oImage;
    }

    public ChessType() {
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public int getxImage() {
        return xImage;
    }

    public void setxImage(int xImage) {
        this.xImage = xImage;
    }

    public int getoImage() {
        return oImage;
    }

    public void setoImage(int oImage) {
        this.oImage = oImage;
    }
}
