import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class DecryptFilterReader extends FilterReader {
    private final char key;

    public DecryptFilterReader(Reader in, char key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c == -1) {
            return -1;
        }
        return c - key;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int charsRead = super.read(cbuf, off, len);
        if (charsRead == -1) {
            return -1;
        }
        for (int i = off; i < off + charsRead; i++) {
            cbuf[i] = (char) (cbuf[i] - key);
        }
        return charsRead;
    }
}