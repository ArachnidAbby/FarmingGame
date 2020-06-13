package me.spidertyler2005.farminggame.scenes.levelEditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import me.spidertyler2005.farminggame.engine.scene.scene;
import me.spidertyler2005.farminggame.engine.world.TileType;
import me.spidertyler2005.farminggame.engine.world.tiles.MapLoader;
import me.spidertyler2005.farminggame.maps.CustomMap;
import me.spidertyler2005.farminggame.scenes.levelEditor.io.EventHandler;

public class LevelEditorMain implements scene {
	SpriteBatch batch;
	private TextureRegion[][] tiles;
	private Texture tileimg;
	public static int tileSelected = 0;
	
	OrthographicCamera cam;
	
	final int SPEED = 500;
	CustomMap gameMap;
	
	@Override
	public boolean start() {
		batch = new SpriteBatch();
		tileimg = new Texture("art/map/tileMap32X.png");
		tiles = TextureRegion.split(tileimg, TileType.TILE_SIZE, TileType.TILE_SIZE);
		
		EventHandler inputProcessor = new EventHandler();
		Gdx.input.setInputProcessor(inputProcessor);
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
		
		gameMap = new CustomMap();
		return true;
	}

	@Override
	public boolean render() {
		Gdx.gl.glClearColor(0.3f, 0.5f, 1, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gameMap.render(cam);
		batch.begin();
		int id = (TileType.getIdFromIndex(tileSelected))-1;
		//System.out.println(id);
		batch.draw(tiles[(int) (id)/tiles[0].length][id%tiles[0].length],0,0);
		batch.end();
		return true;
	}

	@Override
	public void update(float deltaTime) {
		Vector2 movement = new Vector2();
		movement.y = (Gdx.input.isKeyPressed(Input.Keys.W) ? 1 : 0) + (Gdx.input.isKeyPressed(Input.Keys.S) ? -1 : 0);
		movement.x = (Gdx.input.isKeyPressed(Input.Keys.D) ? 1 : 0) + (Gdx.input.isKeyPressed(Input.Keys.A) ? -1 : 0);
		movement = movement.clamp(0, 1);
		//System.out.println(movement.len());
		cam.translate(movement.x*SPEED*deltaTime,movement.y*SPEED*deltaTime);
		cam.update();
		
		
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.X)) {
			MapLoader.saveMap(gameMap.id, gameMap.name, gameMap.map);
		}
		
		if(Gdx.input.isTouched()) {
			Vector3 pos = cam.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
			TileType type = gameMap.getTileTypeByLocation(1, pos.x, pos.y);
			Vector2 coords = gameMap.getTileCoordinateByPosition(0, pos.x, pos.y);
			//System.out.println("x: "+coords.x+"y: "+coords.y);
			if(type!=null) {
				gameMap.map[1][(int) coords.y][(int) coords.x] = (TileType.getIdFromIndex(tileSelected));
			}
		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
