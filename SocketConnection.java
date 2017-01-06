/**
 * Created by ben on 1/6/17.
 */
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by ben on 1/6/17.
 */
public class SocketConnection {
    private Socket          socket   = null;
    private DataInputStream streamIn =  null;
    private DataInputStream console  =  null;
    private DataOutputStream streamOut = null;

    public SocketConnection(String ip,int port){
        try {
            System.out.println("connecting to "+ip+":"+port+", please wait  ...");
            socket = new Socket(ip,port);
            if(socket.isConnected())
                System.out.println("conncted.");
            open();
            String line = "";
            while (!line.equals(".bye")){
                try {
                    line = console.readLine();
                    streamOut.writeUTF(line);
                    streamOut.flush();
                }
                catch(IOException ioe){
                    System.out.println("Sending error: " + ioe.getMessage());
                }
                try{
                    String s = streamIn.readUTF();
                    System.out.println(s);
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            close();
        }
        catch(IOException ioe) {
            System.out.println(ioe);
        }
    }
    public void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        console = new DataInputStream(System.in);
        streamOut = new DataOutputStream(socket.getOutputStream());
    }
    public void close() throws IOException {
        if (socket != null)
            socket.close();
        if (streamIn != null)
            streamIn.close();
        if (console != null)
            console.close();
    }
}
