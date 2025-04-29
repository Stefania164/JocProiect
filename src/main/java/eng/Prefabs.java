package eng;

import components.*;
import org.joml.Vector2f;
import physics2d.components.Box2DCollider;
import physics2d.components.CircleCollider;
import physics2d.components.PillboxCollider;
import physics2d.components.Rigidbody2D;
import physics2d.enums.BodyType;
import util.AssetPool;

public class Prefabs {

    public static GameObject generateSpriteObject(Sprite sprite, float sizeX, float sizeY) {
        GameObject block = Window.getScene().createGameObject("Sprite_Object_Gen");
        block.transform.scale.x = sizeX;
        block.transform.scale.y = sizeY;
        SpriteRenderer renderer = new SpriteRenderer();
        renderer.setSprite(sprite);
        block.addComponent(renderer);

        return block;
    }

    public static GameObject generateAether() {
        Spritesheet playerSprites = AssetPool.getSpritesheet("assets/images/aether-walking-sprite.png");
        GameObject aether = generateSpriteObject(playerSprites.getSprite(0), 0.5f, 0.5f);

        AnimationState run = new AnimationState();
        run.title = "Run";
        float defaultFrameTime = 0.2f;
        run.addFrame(playerSprites.getSprite(1), defaultFrameTime);
        run.addFrame(playerSprites.getSprite(0), defaultFrameTime);
        run.addFrame(playerSprites.getSprite(1), defaultFrameTime);
        run.addFrame(playerSprites.getSprite(2), defaultFrameTime);
        run.setLoop(true);

        AnimationState idle = new AnimationState();
        idle.title = "Idle";
        idle.addFrame(playerSprites.getSprite(1), 0.1f);
        idle.setLoop(false);

        AnimationState jump = new AnimationState();
        jump.title = "Jump";
        jump.addFrame(playerSprites.getSprite(0), 0.1f);
        jump.setLoop(false);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(idle);
        stateMachine.addState(run);
        stateMachine.addState(jump);

        stateMachine.setDefaultState(idle.title);
        stateMachine.addState(run.title, idle.title, "stopRunning");
        stateMachine.addState(run.title, jump.title, "jump");
        stateMachine.addState(idle.title, run.title, "startRunning");
        stateMachine.addState(idle.title, jump.title, "jump");
        stateMachine.addState(jump.title, idle.title, "stopJumping");
        aether.addComponent(stateMachine);

        PillboxCollider pb = new PillboxCollider();
        pb.width = 0.20f;
        pb.height = 0.35f;
        aether.addComponent(pb);

        Rigidbody2D rb = new Rigidbody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setContinuousCollision(false);
        rb.setFixedRotation(true);
        rb.setMass(25.0f);
        aether.addComponent(rb);

        aether.addComponent(new PlayerController());

        aether.transform.zIndex = 10;
        return aether;
    }

    public static GameObject generateLumine() {
        Spritesheet playerSprites = AssetPool.getSpritesheet("assets/images/lumine-walking-sprite.png");
        GameObject lumine = generateSpriteObject(playerSprites.getSprite(0), 0.5f, 0.5f);

        AnimationState run = new AnimationState();
        run.title = "Run";
        float defaultFrameTime = 0.2f;
        run.addFrame(playerSprites.getSprite(1), defaultFrameTime);
        run.addFrame(playerSprites.getSprite(0), defaultFrameTime);
        run.addFrame(playerSprites.getSprite(1), defaultFrameTime);
        run.addFrame(playerSprites.getSprite(2), defaultFrameTime);
        run.setLoop(true);

        AnimationState idle = new AnimationState();
        idle.title = "Idle";
        idle.addFrame(playerSprites.getSprite(1), 0.1f);
        idle.setLoop(false);

        AnimationState jump = new AnimationState();
        jump.title = "Jump";
        jump.addFrame(playerSprites.getSprite(0), 0.1f);
        jump.setLoop(false);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(idle);
        stateMachine.addState(run);
        stateMachine.addState(jump);

        stateMachine.setDefaultState(idle.title);
        stateMachine.addState(run.title, idle.title, "stopRunning");
        stateMachine.addState(run.title, jump.title, "jump");
        stateMachine.addState(idle.title, run.title, "startRunning");
        stateMachine.addState(idle.title, jump.title, "jump");
        stateMachine.addState(jump.title, idle.title, "stopJumping");
        lumine.addComponent(stateMachine);

        PillboxCollider pb = new PillboxCollider();
        pb.width = 0.20f;
        pb.height = 0.35f;
        lumine.addComponent(pb);

        Rigidbody2D rb = new Rigidbody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setContinuousCollision(false);
        rb.setFixedRotation(true);
        rb.setMass(25.0f);
        lumine.addComponent(rb);

        lumine.addComponent(new PlayerController());

        lumine.transform.zIndex = 10;
        return lumine;
    }

    public static GameObject generateHilichiurl(){
        Spritesheet spritesheet = AssetPool.getSpritesheet("assets/images/hilichiurl-sprite-center.png");
        GameObject hilichiurl = generateSpriteObject(spritesheet.getSprite(0), 0.5f, 0.5f);

        AnimationState walk = new AnimationState();
        walk.title = "Walk";
        float defaultFrameTime = 0.23f;
        walk.addFrame(spritesheet.getSprite(1), defaultFrameTime);
        walk.setLoop(true);

        AnimationState squashed = new AnimationState();
        squashed.title = "Squashed";
        squashed.addFrame(spritesheet.getSprite(2), 0.1f);
        squashed.setLoop(false);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(walk);
        stateMachine.addState(squashed);
        stateMachine.setDefaultState(walk.title);
        stateMachine.addState(walk.title, squashed.title, "squashMe");
        hilichiurl.addComponent(stateMachine);

        Rigidbody2D rb = new Rigidbody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setMass(0.1f);
        rb.setFixedRotation(true);
        hilichiurl.addComponent(rb);
        CircleCollider circle = new CircleCollider();
        circle.setRadius(0.10f);
        hilichiurl.addComponent(circle);

        hilichiurl.addComponent(new HilichiurlAI());

        return hilichiurl;
    }

    public static GameObject generateAbyssMage(){
        Spritesheet spritesheet = AssetPool.getSpritesheet("assets/images/abyss-mage-sprite.png");
        GameObject mage = generateSpriteObject(spritesheet.getSprite(0), 0.75f, 0.75f);

        AnimationState walk = new AnimationState();
        walk.title = "Walk";
        float defaultFrameTime = 0.23f;
        walk.addFrame(spritesheet.getSprite(0), defaultFrameTime);
        walk.setLoop(true);

        AnimationState squashed = new AnimationState();
        squashed.title = "Squashed";
        squashed.addFrame(spritesheet.getSprite(1), 0.1f);
        squashed.setLoop(false);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(walk);
        stateMachine.addState(squashed);
        stateMachine.setDefaultState(walk.title);
        stateMachine.addState(walk.title, squashed.title, "squashMe");
        mage.addComponent(stateMachine);

        Rigidbody2D rb = new Rigidbody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setMass(0.1f);
        rb.setFixedRotation(true);
        mage.addComponent(rb);
        CircleCollider circle = new CircleCollider();
        circle.setRadius(0.23f);
        mage.addComponent(circle);

        mage.addComponent(new AbyssMageAI());

        return mage;
    }

    public static GameObject generateCoin() {
        Spritesheet moraNormal = AssetPool.getSpritesheet("assets/images/mora.png");
        Spritesheet moraDarker = AssetPool.getSpritesheet("assets/images/darkermora.png");
        GameObject coin = generateSpriteObject(moraNormal.getSprite(0), 0.25f, 0.25f);

        AnimationState coinFlip = new AnimationState();
        coinFlip.title = "CoinFlip";
        float defaultFrameTime = 0.23f;
        coinFlip.addFrame(moraNormal.getSprite(0), 0.57f);
        coinFlip.addFrame(moraDarker.getSprite(0), defaultFrameTime);
        coinFlip.setLoop(true);

        StateMachine stateMachine = new StateMachine();
        stateMachine.addState(coinFlip);
        stateMachine.setDefaultState(coinFlip.title);
        coin.addComponent(stateMachine);
        coin.addComponent(new Coin());

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.setRadius(0.10f);
        coin.addComponent(circleCollider);
        Rigidbody2D rb = new Rigidbody2D();
        rb.setBodyType(BodyType.Static);
        coin.addComponent(rb);

        return coin;
    }

    public static GameObject generateAnemoBall(Vector2f position) {
        Spritesheet items = AssetPool.getSpritesheet("assets/images/anemo.png");
        GameObject anemoBall = generateSpriteObject(items.getSprite(0), 0.25f, 0.25f);
        anemoBall.transform.position = position;

        Rigidbody2D rb = new Rigidbody2D();
        rb.setBodyType(BodyType.Dynamic);
        rb.setFixedRotation(true);
        rb.setContinuousCollision(false);
        anemoBall.addComponent(rb);

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.setRadius(0.08f);
        anemoBall.addComponent(circleCollider);
        anemoBall.addComponent(new AnemoBall());

        anemoBall.transform.zIndex = 10;
        return anemoBall;
    }

    public static GameObject generateDoor() {
        Spritesheet doorSprite = AssetPool.getSpritesheet("assets/images/door.png");
        GameObject door = generateSpriteObject(doorSprite.getSprite(0), 0.5f, 1f);

        Rigidbody2D rb = new Rigidbody2D();
        rb.setBodyType(BodyType.Static);
        rb.setFixedRotation(true);
        rb.setContinuousCollision(false);
        door.addComponent(rb);

        Box2DCollider b2d = new Box2DCollider();
        b2d.setHalfSize(new Vector2f(0.5f, 1f));
        door.addComponent(b2d);

        door.addComponent(new Door());

        return door;
    }
}