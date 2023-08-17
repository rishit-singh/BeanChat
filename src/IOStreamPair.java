import java.io.DataInputStream;
import java.io.DataOutputStream;

public class IOStreamPair
{
    public final DataInputStream InputStream;

    public final DataOutputStream OutputStream;

    public IOStreamPair(DataInputStream inputStream, DataOutputStream outputStream)
    {
        this.InputStream = inputStream;
        this.OutputStream = outputStream;
    }
}
