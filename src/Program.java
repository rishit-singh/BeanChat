import java.io.IOException;

public class Program
{
    public static void main(String[] args) throws IOException
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Server started.");
                    new BeanServer(3000).Start();
                } catch(IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


        BeanClient client = new BeanClient("127.0.0.1", 3000);

        System.out.println(client._Socket.isConnected());

//        for (;;) {
            client.Send("Hello World!");
//        }


    }
}

