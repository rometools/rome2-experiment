package com.rometools.rome;

import com.google.common.io.ByteStreams;
import com.rometools.rome.model.Feed;
import com.rometools.rome.xml.XmlParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class Rome {

  private static final Format DEFAULT_WRITE_FORMAT = Format.RSS;

  private final Format writeFormat;

  public Rome(Format writeFormat) {
    this.writeFormat = writeFormat;
  }

  static Rome minimal() {
    return new Builder().build();
  }

  static Rome.Builder custom() {
    return new Builder();
  }

  public Feed read(byte[] source) {
    return new XmlParser(source).parse();
  }

  public Feed read(String source) {
    return read(source.getBytes());
  }

  public Feed read(Path source) {
    try {
      return read(Files.readAllBytes(source));
    } catch (IOException e) {
      throw new RomeException("Failed to read", e);
    }
  }

  public Feed read(InputStream source) {
    try {
      return read(ByteStreams.toByteArray(source));
    } catch (IOException e) {
      throw new RomeException("Failed to read", e);
    }
  }

  public Feed read(ReadableByteChannel source) {
    return read(Channels.newInputStream(source));
  }

  public byte[] write(Feed feed) {
    // TODO
    return new byte[0];
  }

  public String writeString(Feed feed) {
    // TODO
    return "TODO";
  }

  public void write(Feed feed, Path target) {
    // TODO
  }

  public void write(Feed feed, OutputStream target) {
    // TODO
  }

  public void write(Feed feed, WritableByteChannel target) {
    // TODO
  }

  static class Builder {

    private Format writeFormat = DEFAULT_WRITE_FORMAT;

    public Builder setWriteFormat(Format writeFormat) {
      this.writeFormat = writeFormat;
      return this;
    }

    public Rome.Builder enable(Config.Feature feature) {
      // TODO
      return this;
    }

    public Rome.Builder disable(Config.Feature feature) {
      // TODO
      return this;
    }

    public Rome.Builder register(Config.Extension extension) {
      // TODO
      return this;
    }

    public Rome build() {
      return new Rome(writeFormat);
    }
  }
}
