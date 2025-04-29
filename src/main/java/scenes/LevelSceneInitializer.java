package scenes;

import components.GameCamera;
import components.SpriteRenderer;
import components.Spritesheet;
import components.StateMachine;
import eng.GameObject;
import util.AssetPool;

public class LevelSceneInitializer extends SceneInitializer {
    public LevelSceneInitializer() {

    }

    @Override
    public void init(Scene scene) {
        Spritesheet sprites = AssetPool.getSpritesheet("assets/images/spritesheets/decorationsAndBlocks.png");

        GameObject cameraObject = scene.createGameObject("GameCamera");
        cameraObject.addComponent(new GameCamera(scene.camera()));
        cameraObject.start();
        scene.addGameObjectToScene(cameraObject);
    }

    @Override
    public void loadResources(Scene scene) {
        AssetPool.getShader("assets/shaders/default.glsl");

        checkResources();
        AssetPool.addSpritesheet("assets/images/spritesheets/decorationsAndBlocks.png",
                new Spritesheet(AssetPool.getTexture("assets/images/spritesheets/decorationsAndBlocks.png"),
                        16, 16, 81, 0));
        AssetPool.addSpritesheet("assets/images/gizmos.png",
                new Spritesheet(AssetPool.getTexture("assets/images/gizmos.png"),
                        24, 48, 3, 0));
        AssetPool.addSpritesheet("assets/images/aether-walking-sprite.png",
                new Spritesheet(AssetPool.getTexture("assets/images/aether-walking-sprite.png"),
                        64, 64, 3, 0));
        AssetPool.addSpritesheet("assets/images/lumine-walking-sprite.png",
                new Spritesheet(AssetPool.getTexture("assets/images/lumine-walking-sprite.png"),
                        64, 64, 3, 0));
        AssetPool.addSpritesheet("assets/images/hilichiurl-sprite-center.png",
                new Spritesheet(AssetPool.getTexture("assets/images/hilichiurl-sprite-center.png"),
                        64, 64, 3, 0));
        AssetPool.addSpritesheet("assets/images/door.png",
                new Spritesheet(AssetPool.getTexture("assets/images/door.png"),
                        64, 64, 1, 0));
        AssetPool.addSpritesheet("assets/images/mora.png",
                new Spritesheet(AssetPool.getTexture("assets/images/mora.png"),
                        64, 64,1, 0));
        AssetPool.addSpritesheet("assets/images/darkermora.png",
                new Spritesheet(AssetPool.getTexture("assets/images/darkermora.png"),
                        64, 64, 1, 0));
        AssetPool.addSpritesheet("assets/images/anemo.png",
                new Spritesheet(AssetPool.getTexture("assets/images/anemo.png"),
                        64, 64, 1, 0));
        AssetPool.addSpritesheet("assets/images/abyss-mage-sprite.png",
                new Spritesheet(AssetPool.getTexture("assets/images/abyss-mage-sprite.png"),
                        64, 64, 2, 0));
        AssetPool.addSpritesheet("assets/images/next-lvl.png",
                new Spritesheet(AssetPool.getTexture("assets/images/next-lvl.png"),
                        64, 64, 2, 0));

        for (GameObject g : scene.getGameObjects()) {
            if (g.getComponent(SpriteRenderer.class) != null) {
                SpriteRenderer spr = g.getComponent(SpriteRenderer.class);
                if (spr.getTexture() != null) {
                    spr.setTexture(AssetPool.getTexture(spr.getTexture().getFilepath()));
                }
            }

            if (g.getComponent(StateMachine.class) != null) {
                StateMachine stateMachine = g.getComponent(StateMachine.class);
                stateMachine.refreshTextures();
            }
        }
    }

    @Override
    public void imgui() {

    }

    public void checkResources() {
        AssetPool.readSpritesheet("assets/images/spritesheets/decorationsAndBlocks.png");
        AssetPool.readSpritesheet("assets/images/gizmos.png");
        AssetPool.readSpritesheet("assets/images/aether-walking-sprite.png");
        AssetPool.readSpritesheet("assets/images/lumine-walking-sprite.png");
        AssetPool.readSpritesheet("assets/images/hilichiurl-sprite-center.png");
        AssetPool.readSpritesheet("assets/images/door.png");
        AssetPool.readSpritesheet("assets/images/mora.png");
        AssetPool.readSpritesheet("assets/images/darkermora.png");
        AssetPool.readSpritesheet("assets/images/anemo.png");
        AssetPool.readSpritesheet("assets/images/abyss-mage-sprite.png");
        AssetPool.readSpritesheet("assets/images/next-lvl.png");
        AssetPool.readSpritesheet("assets/images/exceptionSpritesheet.png");
    }
}