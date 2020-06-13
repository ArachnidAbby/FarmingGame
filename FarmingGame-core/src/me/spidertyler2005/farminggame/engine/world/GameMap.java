package me.spidertyler2005.farminggame.engine.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;


public abstract class GameMap {
	
	public abstract void render (OrthographicCamera camera);
	public abstract void update (float delta);
	public abstract void dispose ();
	
	
	/**
	 * Gets a tile by pixel position at a specified layer
	 * @param layer
	 * @param x
	 * @param y
	 * @return
	 */
	public TileType getTileTypeByLocation(int layer, float x, float y) {
		return this.getTileByCoordinate(layer,(int) x/TileType.TILE_SIZE, (int) y/TileType.TILE_SIZE);
	}
	
	/**
	 * get a tile at its coordinates in a layer
	 * @param layer
	 * @param col
	 * @param row
	 * @return
	 */
	public abstract TileType getTileByCoordinate(int layer, int col, int row);
	
	public Vector2 getTileCoordinateByPosition(int layer, float x, float y) {
		Vector2 output = new Vector2((int) x/TileType.TILE_SIZE, this.getHeight() - (int) y/TileType.TILE_SIZE - 1);
		if(output.y<0) {
			output.y = 0;
		}
		return output;
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getLayers();
}
