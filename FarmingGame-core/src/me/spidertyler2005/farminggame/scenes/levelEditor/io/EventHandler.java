package me.spidertyler2005.farminggame.scenes.levelEditor.io;

import com.badlogic.gdx.InputProcessor;

import me.spidertyler2005.farminggame.engine.world.TileType;
import me.spidertyler2005.farminggame.scenes.levelEditor.LevelEditorMain;

public class EventHandler implements InputProcessor{

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		LevelEditorMain.tileSelected += amount;
		if(LevelEditorMain.tileSelected <0) 
			LevelEditorMain.tileSelected=0;
		if(LevelEditorMain.tileSelected>TileType.getLength()-1)
			LevelEditorMain.tileSelected=(TileType.getLength())-1;
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

}
