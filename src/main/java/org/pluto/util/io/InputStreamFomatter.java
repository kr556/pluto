package org.pluto.util.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

class InputStreamFomatter implements FileFomatter {
    private final InputStream stream;

    InputStreamFomatter(InputStream stream) {
        this.stream = stream;
    }

    @Override
    public InputStream asStream() {
        return stream;
    }

    @Override
    public BufferedImage asImage() throws IOException {
        return ImageIO.read(stream);
    }

    @Override
    public String asText() throws IOException {
        return new String(asByteArray());
    }

    @Override
    public byte[] asByteArray() throws IOException {
        return stream.readAllBytes();
    }
}
