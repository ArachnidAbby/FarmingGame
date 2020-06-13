package me.spidertyler2005.farminggame.engine.scene;

public interface scene {
	
	public boolean start();
	public boolean render();
	public void update(float deltaTime);
	public void dispose();
	
}
