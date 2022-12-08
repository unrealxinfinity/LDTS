package pt.up.fe.edu.dozer.controller.GameController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.controller.game.BoulderController;
import pt.up.fe.edu.dozer.controller.game.TargetController;
import pt.up.fe.edu.dozer.model.game.arena.Arena;

public class BoulderControllerTest {
    BoulderController behaviourCheck;
    BoulderController valueCheck;
    BoulderController internalCheck;

    @BeforeEach
    public void setUp(){
        behaviourCheck= Mockito.mock(BoulderController.class);
        internalCheck=new BoulderController(Mockito.mock(Arena.class),Mockito.mock(TargetController.class));
    }
    @Test
    public void findBoulder(){


    }
}
