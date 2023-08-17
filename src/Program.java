import java.io.IOException;

public class Program
{
    public static void main(String[] args) throws IOException
    {
        Thread serverThread = new Thread(new BeanServer("0.0.0.0", 3000));

        serverThread.start();

        BeanClient client = new BeanClient("0.0.0.0", 3000);

        for (;;)
            client.Send("Hello World!");
    }
}
