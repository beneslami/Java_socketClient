/**
 * Created by ben on 1/6/17.
 */
import java.io.IOException;

/**
 * Created by ben on 1/6/17.
 */
public class MainClass {
    public static void main(String[] args) throws IOException {
        SocketConnection socketConnection = new SocketConnection(args[0],Integer.parseInt(args[1]));
    }
}
