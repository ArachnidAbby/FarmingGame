package me.spidertyler2005.farminggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import me.spidertyler2005.farminggame.engine.scene.scene;
import me.spidertyler2005.farminggame.scenes.levelEditor.LevelEditorMain;

public class Main extends ApplicationAdapter {
	
	private scene[] scenes = new scene[2];
	
	@Override
	public void create () {
		scenes[0] = new LevelEditorMain();
		scenes[0].start();
	}
	
	public void update(float dt) {
		scenes[0].update(dt);
	}
	

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());
		scenes[0].render();
	}
	
	@Override
	public void dispose () {
		scenes[0].dispose();
	}
}