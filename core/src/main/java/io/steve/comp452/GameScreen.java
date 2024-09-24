package io.steve.comp452;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class GameScreen implements Screen {

    Game game;
    TiledMap map;
    TiledMapTileLayer tiledMapTileLayerTerrain;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    Stage stage;
    Viewport viewport;

    final int TILE_WIDTH = 50;
    final int TILE_HEIGHT = 50;
    final int ROW = 16;
    final int COL = 16;

    Node nest;
    int [][] costGraph;


    GameScreen(Game game){
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        viewport = new FillViewport(camera.viewportWidth, camera.viewportHeight);
        stage = new Stage(viewport);

        costGraph = new int[ROW][COL];

        initMap();

    }

    public void initMap(){
        map = new TiledMap();
        tiledMapTileLayerTerrain = new TiledMapTileLayer(ROW, COL,TILE_WIDTH, TILE_HEIGHT);
        map.getLayers().add(tiledMapTileLayerTerrain);

        // randomly generate each tile
        Random rand = new Random();
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){

                int num = rand.nextInt(100 + 1);

                if(num < 5 && num > 0){
                    Texture landTexture = new Texture(Gdx.files.internal("water.png"));
                    TextureRegion landTextureReg = new TextureRegion(landTexture);
                    StaticTiledMapTile myTile = new StaticTiledMapTile(landTextureReg);
                    TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                    cell.setTile(myTile);
                    tiledMapTileLayerTerrain.setCell(i, j, cell);
                    costGraph[i][j] = 2;
                }
                else if(num < 10 && num > 5){
                    Texture landTexture = new Texture(Gdx.files.internal("apple.png"));
                    TextureRegion landTextureReg = new TextureRegion(landTexture);
                    StaticTiledMapTile myTile = new StaticTiledMapTile(landTextureReg);
                    TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                    cell.setTile(myTile);
                    tiledMapTileLayerTerrain.setCell(i, j, cell);
                    costGraph[i][j] = -1;
                }
                else if(num < 25 && num > 10){
                    Texture landTexture = new Texture(Gdx.files.internal("swamp.png"));
                    TextureRegion landTextureReg = new TextureRegion(landTexture);
                    StaticTiledMapTile myTile = new StaticTiledMapTile(landTextureReg);
                    TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                    cell.setTile(myTile);
                    tiledMapTileLayerTerrain.setCell(i, j, cell);
                    costGraph[i][j] = Integer.MAX_VALUE;
                }
                else{
                    Texture landTexture = new Texture(Gdx.files.internal("grass.png"));
                    TextureRegion landTextureReg = new TextureRegion(landTexture);
                    StaticTiledMapTile myTile = new StaticTiledMapTile(landTextureReg);
                    TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                    cell.setTile(myTile);
                    tiledMapTileLayerTerrain.setCell(i, j, cell);
                    costGraph[i][j] = 1;
                }

            }
        }

        //add nest to 0,0
        nest = new Node(0,0);
        Texture nestTexture = new Texture(Gdx.files.internal("dirt.png"));
        TextureRegion nestTextureReg = new TextureRegion(nestTexture);
        StaticTiledMapTile nestTile = new StaticTiledMapTile(nestTextureReg);
        TiledMapTileLayer.Cell nestCell = new TiledMapTileLayer.Cell();
        nestCell.setTile(nestTile);
        tiledMapTileLayerTerrain.setCell(0, 0, nestCell);
        costGraph[0][0] = 0;

        renderer = new OrthogonalTiledMapRenderer(map);
    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(222/225f,184/225f,135/225f, 1);
        camera.update();
        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        stage.dispose();
    }
}
