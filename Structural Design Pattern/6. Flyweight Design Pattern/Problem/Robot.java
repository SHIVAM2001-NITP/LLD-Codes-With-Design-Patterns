public class Robot {
    int coordinateX; ////Extrinsic Data:changes based on client input and differs from one object to another
    int coordinateY;//Extrinsic Data
    String type;//Intrinsic DATA : shared among objects and remain same once defined one value
    //Humanoid or Robotic Dog
    Sprites body;//Intrinsic DATA
    //small 2d bitmap(graphic element) heavy object or too much costly

    public Robot(int coordinateX, int coordinateY, String type, Sprites body) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.type = type;
        this.body = body;
    }
    //getters and setters
}
