package me.spidertyler2005.farminggame.engine.world;

import java.util.HashMap;

public enum TileType {
	
	GDCORNERTL(1,false,"dirt Corner",false), //Grass to Dirt Corner Top Left
	GDCORNERTR(3,false,"dirt Corner",false),
	GDCORNERBL(13,false,"dirt Corner",false),
	GDCORNERBR(15,false,"dirt Corner",false),
	GDEDGET(2,false,"dirt edge",false), //Grass to Dirt Edge Top
	GDEDGEL(7,false,"dirt edge",false),
	GDEDGER(9,false,"dirt edge",false),
	GDEDGEB(14,false,"dirt edge",false),
	DGCORNERTL(4,false,"grass Corner",false), //Dirt to Grass Corner Top Left
	DGCORNERTR(6,false,"grass Corner",false),
	DGCORNERBL(16,false,"grass Corner",false),
	DGCORNERBR(18,false,"grass Corner",false),
	DIRT(8,false,"Dirt",false),
	GRASS(11,false,"Grass",false);
	
	//constructor
	public static int TILE_SIZE = 32;
	
	private int id;
	private boolean collidable;
	private boolean tileEntity;
	private String name;
	
	private TileType(int id, boolean col, String name, boolean updated) {
		this.id = id;
		this.collidable = col;
		this.name = name;
		this.tileEntity = updated;
	}

	public int getId() {
		return id;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public boolean isTileEntity() {
		return tileEntity;
	}


	public String getName() {
		return name;
	}
	
	private static HashMap<Integer,TileType> tilemap = new HashMap<Integer,TileType>();
	public static int[] ids;
	static {
		int x =0;
		for(TileType tiletype : TileType.values()) {
			tilemap.put(tiletype.getId(), tiletype);
		}
		ids = new int[tilemap.size()];
		for(TileType tiletype : TileType.values()) {
			ids[x]= tiletype.getId();
			x++;
		}
	}
	
	public static TileType getTileTypeById(int id) {
		return tilemap.get(id);
	}
	
	public static int getIdFromIndex(int index) {
		return ids[index];
	}
	
	public static int getLength() {
		return ids.length;
	}
	
}
