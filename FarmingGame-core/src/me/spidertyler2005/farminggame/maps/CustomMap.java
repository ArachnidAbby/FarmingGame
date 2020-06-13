package me.spidertyler2005.farminggame.maps;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import me.spidertyler2005.farminggame.engine.world.GameMap;
import me.spidertyler2005.farminggame.engine.world.TileType;
import me.spidertyler2005.farminggame.engine.world.tiles.MapData;
import me.spidertyler2005.farminggame.engine.world.tiles.MapLoader;

public class CustomMap extends GameMap {
	public String id;
	public String name;
	public int[][][] map;
	
	private SpriteBatch batch;
	private TextureRegion[][] tiles;
	private Texture tileimg;
	
	public CustomMap() {
		MapData data = MapLoader.loadMap("basic", "GONK");
		this.id = data.id;
		this.name = data.name;
		this.map = data.map;
		
		batch = new SpriteBatch();
		tileimg = new Texture("art/map/tileMap32X.png");
		tiles = TextureRegion.split(tileimg, TileType.TILE_SIZE, TileType.TILE_SIZE);
	}
	
	@Override
	public void render(OrthographicCamera camera) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(int layer=0; layer<getLayers();layer++) {
			for(int row=0; row<getHeight();row++) {
				for(int col=0; col<getWidth();col++) {
					TileType type = this.getTileByCoordinate(layer, col, row);
					//System.out.println(tiles[0].length);
					if(type!=null) {
						batch.draw(tiles[(int) (type.getId()-1)/tiles[0].length][(type.getId()-1)%tiles[0].length],col*TileType.TILE_SIZE,row*TileType.TILE_SIZE);
					}
				}
			}
		}
		batch.end();
	}

	@Override
	public void update(float delta) {

	}

	@Override
	public void dispose() {
		batch.dispose();
		tileimg.dispose();
	}
	
	@Override
	public TileType getTileTypeByLocation(int layer, float x, float y) {
		return this.getTileByCoordinate(layer, (int) x/TileType.TILE_SIZE,getHeight() - (int) y/TileType.TILE_SIZE);
	}
	
	@Override
	public TileType getTileByCoordinate(int layer, int col, int row) {
		//System.out.println(row);
		if(col < 0 ||col >= getWidth() || row<0 || row>=getHeight()) {
			return null;
		}
		return TileType.getTileTypeById(map[layer][getHeight()-row-1][col]);
	}

	@Override
	public int getWidth() {
		return map[0][0].length;
	}

	@Override
	public int getHeight() {
		//System.out.println(map[0].length);
		return map[0].length;
	}

	@Override
	public int getLayers() {
		return map.length;
	}

}
