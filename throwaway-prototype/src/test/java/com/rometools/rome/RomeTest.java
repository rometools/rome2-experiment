package com.rometools.rome;

import static com.google.common.io.Resources.getResource;
import static com.google.common.io.Resources.toByteArray;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RomeTest {

  @Test
  public void name() throws Exception {
    Feed feed = Rome.minimal().read(
        toByteArray(getResource(getClass(), "test.xml")));

    assertEquals("test-feed-title", feed.title().asString());
    assertEquals("test-item-title-1", feed.items().get(0).title().asString());
    assertEquals("test-item-title-2", feed.items().get(1).title().asString());
  }
}
