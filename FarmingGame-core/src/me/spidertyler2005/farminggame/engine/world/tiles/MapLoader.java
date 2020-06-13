package me.spidertyler2005.farminggame.engine.world.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import me.spidertyler2005.farminggame.engine.world.TileType;

public class MapLoader {
	private static Json json = new Json();
	private static final int SIZE = 40;
	
	public static MapData loadMap (String id, String name) {
		//System.out.println(id+" E");
		Gdx.files.local("maps/").file().mkdirs();
		FileHandle file = Gdx.files.local("maps/"+id+".map");
		if(file.exists()) {
			MapData data = json.fromJson(MapData.class, file.readString());
			//System.out.println(data.map[1][0][0]);
			return data;
		}else {
			MapData data = generateMap(id, name);
			//System.out.println(data.id+" 2");
			saveMap(data.id, data.name, data.map);
			return data;
		}
	}
	
	public static void saveMap(String id, String name, int[][][] map) {
		MapData data = new MapData();
		data.id = id;
		data.name = name;
		data.map = map;
		
		Gdx.files.local("maps/").file().mkdirs();
		FileHandle file = Gdx.files.local("maps/"+id+".map");
		file.writeString(json.prettyPrint(data), false);
	}
	
	public static MapData generateMap(String id, String name) {
		MapData map = new MapData();
		map.id = id;
		map.name = id;
		map.map = new int[2][SIZE][SIZE];
		for(int x=0;x<SIZE;x++) {
			for(int y=0;y<SIZE;y++) {
				//System.out.println(x);
				map.map[1][x][y] = TileType.GRASS.getId();
			}	
		}
		
		return map;
	}
}
