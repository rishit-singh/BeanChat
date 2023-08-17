import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class BeanConnection implements BeanCallback
{
    protected BeanServer Server;

    protected Socket _Socket;

    protected IOStreamPair Streams;

    @Override
    public void run()
    {
        StringBuilder builder = new StringBuilder();

        String buffer;

        try
        {
            builder.append(this.Streams.InputStream.readUTF());
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        buffer = builder.toString();

        System.out.println(buffer);
    }

    public BeanConnection(Socket socket, BeanServer server) throws IOException
    {
        this._Socket = socket;
        this.Server = server;
        this.Streams = new IOStreamPair(new DataInputStream(this._Socket.getInputStream()), new DataOutputStream(this._Socket.getOutputStream()));
    }
}

public class BeanServer
{
    protected ServerSocket _Socket;

    protected  BeanCallback OnReceivedCallback;

    protected boolean Running;

    public boolean IsRunning()
    {
        return this.Running;
    }

    public BeanServer(int port) throws IOException
    {
        this._Socket = new ServerSocket(port);

        this.Running = false;
    }


    public void SetOnReceivedCallback(BeanCallback callback)
    {
        this.OnReceivedCallback = callback;
    }

    public void Start() throws IOException
    {
        this.Running = true;

        System.out.println(this._Socket.getLocalSocketAddress());

        while (this.Running)
        {
            Socket socket = this._Socket.accept();

            new Thread(new BeanConnection(socket, this)).start();
        }
    }
}
