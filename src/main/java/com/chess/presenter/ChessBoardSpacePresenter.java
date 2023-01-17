package com.chess.presenter;

import com.chess.model.Pieces;
import com.chess.view.Observer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

public class ChessBoardSpacePresenter implements Observable {
    private int row, col;
    private boolean selectable, hoveredOver, available;
    private Pieces piece;
    private ObjectProperty<Image> image;
    private List<Observer> observers;

    public ChessBoardSpacePresenter(int row, int col, Pieces piece) {
        this.row = row;
        this.col = col;
        this.piece = piece;
        selectable = false;
        hoveredOver = false;
        available = false;
        image = new SimpleObjectProperty<>(selectImage(piece));
        observers = new LinkedList<>();
        notifyObservers();
    }

    public Image getImage() {
        return selectImage(piece);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Pieces getPiece() {
        return piece;
    }

    public void setPiece(Pieces piece) {
        this.piece = piece;
        image.set(selectImage(piece));
        notifyObservers();
    }

    public Image selectImage(Pieces piece) {
        String path = "file:./src/main/java/com/chess/presenter/resources/";
        if (piece == Pieces.BLACK_KING)
            path += "black_king.png";
        else if (piece == Pieces.BLACK_QUEEN)
            path += "black_queen.png";
        else if (piece == Pieces.BLACK_ROOK)
            path += "black_rook.png";
        else if (piece == Pieces.BLACK_BISHOP)
            path += "black_bishop.png";
        else if (piece == Pieces.BLACK_KNIGHT)
            path += "black_knight.png";
        else if (piece == Pieces.BLACK_PAWN)
            path += "black_pawn.png";
        else if (piece == Pieces.WHITE_KING)
            path += "white_king.png";
        else if (piece == Pieces.WHITE_QUEEN)
            path += "white_queen.png";
        else if (piece == Pieces.WHITE_ROOK)
            path += "white_rook.png";
        else if (piece == Pieces.WHITE_BISHOP)
            path += "white_bishop.png";
        else if (piece == Pieces.WHITE_KNIGHT)
            path += "white_knight.png";
        else if (piece == Pieces.WHITE_PAWN)
            path += "white_pawn.png";
        else
            return null;

        try {
            return new Image(path);
        } catch (Exception e) {
            throw new RuntimeException("No image found for file path: " + path);
        }
    }

    public String getStyle() {
        if (hoveredOver)
            return "-fx-background-color: red;";
        if (row % 2 == col % 2) {
            if (available)
                return "-fx-background-color: rgb(155, 49, 148);";
            else
                return "-fx-background-color: rgb(73, 204, 132);";
        } else {
            if (available)
                return "-fx-background-color: rgb(255, 149, 248);";
            else
                return "-fx-background-color: rgb(255, 249, 248);";
        }
    }

    public void setAvailable(boolean available) {
        this.available = available;
        notifyObservers();
    }

    public void setHoveredOver(boolean hoveredOver) {
        this.hoveredOver = hoveredOver;
        notifyObservers();
    }

    public boolean getHoveredOver() {
        return hoveredOver;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update();
    }
}
