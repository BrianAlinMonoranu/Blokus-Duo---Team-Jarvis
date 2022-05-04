package Interface.graphical;

/*
BLOKUS DUO
TEAM JARVIS - Assignment 4
Brian Monoranu : 20318381
Oskar Jasiewicz : 20377563
Nikita Dmitriev: 20204397
*/

import Control.Piece;
import Control.Score;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import org.lwjgl.opengl.GL20;

import static Control.Main.game;

/* This class/program will create the current player screen */
public class PlayScreen extends ScreenAdapter {

    StartGame startGame;
    Stage stage;
    Skin skin;
    TiledMap map;
    TiledMapRenderer mapRenderer;
    OrthographicCamera camera;

    MapObject selectedNow = null;
    MapLayer activeObjectLayer;
    MapLayer player1ObjectLayer;
    MapLayer player2ObjectLayer;
    int tileWidth;
    int tileHeight;

    Sprite currentSprite;

    SpriteBatch sb;
    Sprite p1Sprite;
    Sprite p2Sprite;
    Texture blackTextures;
    Texture whiteTextureS;

    Pixmap pixmapPlayer1;
    Pixmap pixmapPlayer2;

    /* Initiates the playing screen where the users play the game */
    public PlayScreen(StartGame startGame) {
        this.startGame = startGame;
        this.stage = startGame.stage;
        this.skin = startGame.skin;
        this.camera = startGame.camera;

        map = new TmxMapLoader().load(Gdx.files.internal("prototype/prototype.tmx").path());//We import the prototype.tmx
        mapRenderer = new OrthogonalTiledMapRenderer(map);//This renders which basically collects the background , pieces etc

        TiledMapTileLayer tiles = map.getLayers().getByType(TiledMapTileLayer.class).get(0);
        startGame.boardOriginX = (int) tiles.getProperties().get("originX");
        startGame.boardOriginY = (int) tiles.getProperties().get("originY");
        startGame.tileWidth = tiles.getTileWidth();
        startGame.tileHeight = tiles.getTileHeight();
        startGame.boardOffsetY = (int) tiles.getProperties().get("offsetY");
        tileWidth = startGame.tileWidth;
        tileHeight = startGame.tileHeight;

        blackTextures = new Texture(Gdx.files.internal("prototype/whitetilesprite.png"));//Here we create a texture which is an image of player 1 piece
        blackTextures.getTextureData().prepare();

        whiteTextureS = new Texture(Gdx.files.internal("prototype/whitetilesprite.png"));//Here we create a texture which is an image of player 1 piece
        whiteTextureS.getTextureData().prepare();

        pixmapPlayer1 = blackTextures.getTextureData().consumePixmap();
        pixmapPlayer2 = whiteTextureS.getTextureData().consumePixmap();

        player1ObjectLayer = map.getLayers().get("BPF");//This is player 1 layer which basically has player 1 pieces
        player2ObjectLayer = map.getLayers().get("WPF");//This is player 2 layer which basically has player 2 pieces

        sb = new SpriteBatch();
    }

    /* Changes the current player and all the following variables. Is called after placing a piece */
    void changePlayer() {
        int current = startGame.game.changePlayer();
        if(current == 1) {
            currentSprite = p1Sprite;
            activeObjectLayer = player1ObjectLayer;
        }
        else if(current == 2){
            currentSprite = p2Sprite;
            activeObjectLayer = player2ObjectLayer;
        }
        show();
    }

    /* Creates the current player text and the current player and the button that exits the screen when pressed */
    public void showDialog() {
        pixmapPlayer1.setColor(SettingsScreen.player1PieceColor);//Setting player 1 piece to its color
        pixmapPlayer1.fill();//Filling it up
        p1Sprite = new Sprite(new Texture(pixmapPlayer1));

        pixmapPlayer2.setColor(SettingsScreen.player2PieceColor);//Setting player 1 piece to its color
        pixmapPlayer2.fill();//Filling it up
        p2Sprite = new Sprite(new Texture(pixmapPlayer2));

        Dialog currentPlayerDialog = new Dialog("Current Player", skin, "default");
        currentPlayerDialog.getTitleLabel().setAlignment(Align.center);
        startGame.createPlayers();
        currentPlayerDialog.setWidth(250);
        currentPlayerDialog.text(startGame.game.currentPlayer.name);

        Dialog noMovesLeftDialog = new Dialog("No More Moves!!", skin, "default");
        noMovesLeftDialog.getTitleLabel().setAlignment(Align.center);
        noMovesLeftDialog.setWidth(350);
        noMovesLeftDialog.text(startGame.game.currentPlayer.getNextPlayer().name + " has no more moves");

        Button hideCurrentPlayerDialogButton = new TextButton("OK", skin);
        hideCurrentPlayerDialogButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
            }
        });

        Button hideNoMovesLeftDialogButton = new TextButton("OK", skin);
        hideNoMovesLeftDialogButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                noMovesLeftDialog.hide();
            }
        });
        currentPlayerDialog.button(hideCurrentPlayerDialogButton);
        noMovesLeftDialog.button(hideNoMovesLeftDialogButton);

        currentPlayerDialog.setPosition((stage.getWidth()/2) - (currentPlayerDialog.getWidth()/2), (stage.getHeight()/2) - (currentPlayerDialog.getHeight()/2));
        noMovesLeftDialog.setPosition((stage.getWidth()/2) - (noMovesLeftDialog.getWidth()/2), (stage.getHeight()/2) - (noMovesLeftDialog.getHeight()/2));

        Dialog scoreDialog = new Dialog("Final scores", skin, "default");
        scoreDialog.getTitleLabel().setAlignment(Align.center);
        scoreDialog.setWidth(400);
        scoreDialog.setHeight(200);

        Button closeScoreDialog = new TextButton("Close", skin);
        closeScoreDialog.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        scoreDialog.button(closeScoreDialog);
        scoreDialog.setPosition((stage.getWidth()/2) - (scoreDialog.getWidth()/2), (stage.getHeight()/2) - (currentPlayerDialog.getHeight()/2));

        //This is used when either player 1 and player 2 run out of moves , else we show the current player
        if(startGame.game.currentPlayer.availableLocationsCounter == 0 &&
                startGame.game.currentPlayer.getNextPlayer().availableLocationsCounter == 0){

            Score.pointCounter(startGame.game.currentPlayer);
            Score.pointCounter(startGame.game.currentPlayer.getNextPlayer());

            if (game.currentPlayer.points == game.currentPlayer.getNextPlayer().points) {
                scoreDialog.text(startGame.game.currentPlayer.name + "'s score: "
                        + startGame.game.currentPlayer.points + "\n"
                        + startGame.game.currentPlayer.getNextPlayer().name + "'s score: "
                        + startGame.game.currentPlayer.getNextPlayer().points+ "\n"
                        + "Game ended as a draw!");
            }
            // Checks if the current player's score is greater than the other player's score
            else if (game.currentPlayer.points > game.currentPlayer.getNextPlayer().points) {
                scoreDialog.text(startGame.game.currentPlayer.name + "'s score:"
                        + startGame.game.currentPlayer.points + "\n"
                        + startGame.game.currentPlayer.getNextPlayer().name + "'s score:"
                        + startGame.game.currentPlayer.getNextPlayer().points+ "\n"
                        + game.currentPlayer.name + " is the WINNER!");
            }
            // Else the other player's score is greater than the current player@'s score
            else {
                scoreDialog.text(startGame.game.currentPlayer.name + "'s score:"
                        + startGame.game.currentPlayer.points + "\n"
                        + startGame.game.currentPlayer.getNextPlayer().name + "'s score:"
                        + startGame.game.currentPlayer.getNextPlayer().points+ "\n"
                        + game.currentPlayer.getNextPlayer().name + " is the WINNER!");
            }

            stage.addActor(scoreDialog);

        }
        else{
            stage.addActor(currentPlayerDialog);

            if(startGame.game.currentPlayer.getNextPlayer().availableLocationsCounter == 0){
                stage.addActor(noMovesLeftDialog);
            }
        }
    }

    /* Here we show the current dialog and initiate which pieces the current player can use */
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(stage);
        showDialog();
        if(startGame.game.currentPlayer == startGame.game.player1) {
            activeObjectLayer = player1ObjectLayer;
            currentSprite = p1Sprite;
        }
        else {
            activeObjectLayer = player2ObjectLayer;
            currentSprite = p2Sprite;
        }
    }

    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(SettingsScreen.backgroundColor.r, SettingsScreen.backgroundColor.g, SettingsScreen.backgroundColor.b, SettingsScreen.backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        mapRenderer.setView(camera);
        while (!startGame.game.boardUpdate.isEmpty()) {
            changeTile(startGame.game.boardUpdate.poll());
        }
        mapRenderer.render();
        sb.begin();
        renderPieces();
        sb.end();
        stage.draw();
        stage.act();
    }

    /* Here we create pieces from the Map Objects*/
    private void createPieces(MapLayer layer, Sprite sprite){
        for (PolygonMapObject object:
                layer.getObjects().getByType(PolygonMapObject.class)) {
            if (object != null && selectedNow!=object) {

                float dx = object.getPolygon().getX();
                float dy = object.getPolygon().getY();

                boolean isX = true;
                int[] seq = new Piece(object.getName()).sequence;
                for (int j : seq) {
                    if (isX) {
                        dx += j * tileWidth;
                        isX = false;
                    } else {
                        dy += j * tileWidth;
                        sprite.setX(dx);
                        sprite.setY(dy);
                        sprite.draw(sb);
                        isX = true;
                    }
                }
            }
        }
    }

    private void renderPieces() {
        createPieces(player1ObjectLayer, p1Sprite);
        createPieces(player2ObjectLayer, p2Sprite);

        if (selectedNow != null && startGame.game.selectedNow != null) {
            Vector3 world = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            float dx = world.x - (float)(tileWidth / 2), dy = world.y - (float)(tileHeight / 2);
            boolean isX = true;
            int[] seq = startGame.game.selectedNow.sequence;
            for (int j : seq) {
                if (isX) {
                    dx += j * 32;
                    isX = false;
                } else {
                    dy += j * 32;
                    currentSprite.setX(dx);
                    currentSprite.setY(dy);
                    currentSprite.draw(sb);
                    isX = true;
                }
            }
        }
    }

    /* When we place piece on the board we change will be initiated to place it on the board */
    private void changeTile(int[] input) {
        int x = input[0];
        int y = input[1];
        char player = (char) input[2];
        TiledMapTileLayer l = (TiledMapTileLayer) map.getLayers().get("Board");
        TiledMapTileSet ts = map.getTileSets().getTileSet("tiles");
        TextureRegion t;
        if (player == startGame.game.player1.symbol) {
            t = p1Sprite;
        } else if (player == startGame.game.player2.symbol) {
            t = p2Sprite;
        } else if (player == '*' && currentSprite == p1Sprite) {
            t = ts.getTile(3).getTextureRegion();
        } else if (player == '*' && currentSprite == p2Sprite) {
            t = ts.getTile(2).getTextureRegion();
        } else {
            t = ts.getTile(6).getTextureRegion();
        }
        l.setCell(x+ startGame.boardOriginX, y + startGame.boardOffsetY,
                new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(t)));
    }


    public void hide() {
        super.hide();

        Gdx.input.setInputProcessor(new InputAdapter() {
            //When we click on the button else we check to see if position is blocked and we place the piece and change players
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 world = camera.unproject(new Vector3(screenX, screenY, 0));
                // if you press LMB
                if (button == 0) {
                    // Pick up
                    if (selectedNow == null) {
                        for (MapObject o : activeObjectLayer.getObjects()) {
                            if (o instanceof PolygonMapObject) {
                                boolean touched = ((PolygonMapObject) o).getPolygon().contains(world.x, world.y);
                                if (touched) {
                                    selectedNow = o;
                                    startGame.game.setSelectedNow(selectedNow.getName());
                                }
                            }
                        }
                    } else {
                        int boardValueX = startGame.gui.coordinateInput('x');
                        int boardValueY = startGame.gui.coordinateInput('y');
                        if (!startGame.game.isPositionBlocked(boardValueX, boardValueY)) {
                            startGame.game.placeMove(boardValueX, boardValueY);
                            activeObjectLayer.getObjects().remove(selectedNow);
                            changePlayer();
                            selectedNow = null;
                        }
                    }
                } // if you press any other button
                else {
                    selectedNow = null;
                    startGame.game.selectedNow = null;
                    startGame.uiStream.print("drop");
                }
                return super.touchDown(screenX, screenY, pointer, button);
            }

            @Override
            public boolean keyDown(int keycode) {
                int f = Input.Keys.F;
                int r = Input.Keys.R;
                if(selectedNow != null){
                    if (keycode == f)
                        startGame.game.selectedNow.Flip();
                    else if (keycode == r)
                        startGame.game.selectedNow.Rotate();
                }
                return super.keyDown(keycode);
            }
        });
        stage.clear();
    }

    public void resize(int width, int height) {
        super.resize(width,height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        super.dispose();
        blackTextures.dispose();
        sb.dispose();
    }
}