package com.rometools.rome;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

public interface Rome {

  static Rome minimal() {
    return new RomeImpl.Builder().build();
  }

  static Rome.Builder custom() {
    return new RomeImpl.Builder();
  }

  Feed read(byte[] source);

  Feed read(String source);

  Feed read(Path source);

  Feed read(InputStream source);

  Feed read(ReadableByteChannel source);

  byte[] write(Feed feed);

  String writeString(Feed feed);

  void write(Feed feed, Path target);

  void write(Feed feed, OutputStream target);

  void write(Feed feed, WritableByteChannel target);

  interface Builder {

    Builder setWriteFormat(Format format);

    Builder enable(Config.Feature feature);

    Builder disable(Config.Feature feature);

    Builder register(Config.Extension extension);

    Rome build();
  }
}
