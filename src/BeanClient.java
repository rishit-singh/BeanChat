import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

public class BeanClient
{
    protected Socket _Socket;

    protected IOStreamPair Streams;

    public void Connect(String address, int port) throws IOException
    {
        this._Socket = new Socket(address, port);
        this.Streams = new IOStreamPair((DataInputStream)this._Socket.getInputStream(), (DataOutputStream)this._Socket.getOutputStream());
    }

    public void Send(String buffer) throws IOException
    {
        this.Streams.OutputStream.writeChars(buffer);
    }

    public BeanClient()
    {
    }

    public BeanClient(String address, int port) throws IOException
    {
        this.Connect(address, port);
    }
}
