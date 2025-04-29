package components;

import eng.GameObject;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;

public class Door extends Component {
    @Override
    public void beginCollision(GameObject obj, Contact contact, Vector2f contactNormal){
        PlayerController playerController = obj.getComponent(PlayerController.class);
        if(playerController != null){
            playerController.winScreen(this.gameObject);
        }
    }
}
