package com.rometools.rome;

import com.google.common.io.ByteStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

class RomeImpl implements Rome {

  private static final Format DEFAULT_WRITE_FORMAT = Format.RSS;

  private final Format writeFormat;

  public RomeImpl(Format writeFormat) {
    this.writeFormat = writeFormat;
  }

  @Override
  public Feed read(byte[] source) {
    return new Reader(new Rss()).read(source);
  }

  @Override
  public Feed read(String source) {
    return read(source.getBytes());
  }

  @Override
  public Feed read(Path source) {
    try {
      return read(Files.readAllBytes(source));
    } catch (IOException e) {
      throw new RomeException("Failed to read", e);
    }
  }

  @Override
  public Feed read(InputStream source) {
    try {
      return read(ByteStreams.toByteArray(source));
    } catch (IOException e) {
      throw new RomeException("Failed to read", e);
    }
  }

  @Override
  public Feed read(ReadableByteChannel source) {
    return read(Channels.newInputStream(source));
  }

  @Override
  public byte[] write(Feed feed) {
    // TODO
    return new byte[0];
  }

  @Override
  public String writeString(Feed feed) {
    // TODO
    return "TODO";
  }

  @Override
  public void write(Feed feed, Path target) {
    // TODO
  }

  @Override
  public void write(Feed feed, OutputStream target) {
    // TODO
  }

  @Override
  public void write(Feed feed, WritableByteChannel target) {
    // TODO
  }

  static class Builder implements Rome.Builder {

    private Format writeFormat = DEFAULT_WRITE_FORMAT;

    @Override
    public Builder setWriteFormat(Format writeFormat) {
      this.writeFormat = writeFormat;
      return this;
    }

    @Override
    public Rome.Builder enable(Config.Feature feature) {
      // TODO
      return this;
    }

    @Override
    public Rome.Builder disable(Config.Feature feature) {
      // TODO
      return this;
    }

    @Override
    public Rome.Builder register(Config.Extension extension) {
      // TODO
      return this;
    }

    @Override
    public Rome build() {
      return new RomeImpl(writeFormat);
    }
  }
}
