import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BeanServer implements Runnable
{
    protected Socket _Socket;

    protected IOStreamPair Streams;

    protected boolean Running;

    public boolean IsRunning()
    {
        return this.Running;
    }

    public BeanServer(String address, int port) throws IOException
    {
        this._Socket = new Socket(address, port);
        this.Streams = new IOStreamPair((DataInputStream) this._Socket.getInputStream(), (DataOutputStream) this._Socket.getOutputStream());
        this.Running = false;
    }

    @Override
    public void run()
    {
        Scanner scanner = new Scanner(this.Streams.InputStream);

        StringBuilder stringBuilder = new StringBuilder();

        String buffer = null;

        while (this.Running)
        {
            while (scanner.hasNext())
                stringBuilder.append(scanner.next());
            buffer = stringBuilder.toString();

            System.out.println(buffer);

            stringBuilder.delete(0, stringBuilder.length());
        }

    }
}
