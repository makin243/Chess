package com.chess.view.nodes;

import com.chess.presenter.GameNotationPresenter;
import com.chess.view.Observer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameNotationNode extends ScrollPane implements Observer {
    private int notationCount;
    private GameNotationPresenter gameNotationPresenter;
    private VBox root;

    public GameNotationNode(GameNotationPresenter gameNotationPresenter) {
        notationCount = 0;
        this.gameNotationPresenter = gameNotationPresenter;
        gameNotationPresenter.attach(this);
        root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        getChildren().add(root);
        setContent(root);
    }

    @Override
    public void update() {
        if (gameNotationPresenter.getSizeOfMovementNotations() <= notationCount)
            throw new RuntimeException("Notation count is off.");

        if (root.getChildren().size() == 0 || ((HBox) root.getChildren().get(root.getChildren().size() - 1)).getChildren().size() == 3) {
            HBox row = new HBox();
            Label numberLabel = new Label(root.getChildren().size() + 1 + ". ");
            numberLabel.setMinWidth(30);
            numberLabel.setMaxWidth(30);
            row.getChildren().add(numberLabel);
            Label label = new Label(gameNotationPresenter.getLastMovement());
            label.setMinWidth(50);
            label.setMaxWidth(50);
            row.getChildren().add(label);
            root.getChildren().add(row);
        } else {
            ((HBox) root.getChildren().get(root.getChildren().size() - 1)).getChildren().add(new Label(gameNotationPresenter.getLastMovement()));
        }

        notationCount++;
    }
}