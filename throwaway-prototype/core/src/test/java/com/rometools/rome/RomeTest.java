package com.rometools.rome;

import static com.google.common.io.Resources.getResource;
import static com.google.common.io.Resources.toByteArray;
import static org.junit.Assert.assertEquals;

import com.rometools.rome.model.Enclosure;
import com.rometools.rome.model.Feed;
import com.rometools.rome.model.Image;
import com.rometools.rome.model.Item;
import org.junit.Test;

public class RomeTest {

  @Test
  public void test() throws Exception {
    Feed expected =
        Feed.builder()
            .setTitle("Test feed title")
            .setDescription("Test feed description")
            .setImage(Image.builder().setUrl("https://test.example.com/image.png").build())
            .addItem(
                Item.builder()
                    .setTitle("Test item title 1")
                    .setDescription("Test item description 1")
                    .setLink("https://test.example.com/item-link-1")
                    .addEnclosure(
                        Enclosure.builder()
                            .setUrl("https://test.example.com/audio-1.ogg")
                            .setType("audio/ogg")
                            .setLength(1024)
                            .build())
                    .build())
            .addItem(
                Item.builder()
                    .setTitle("Test item title 2")
                    .setDescription("Test item description 2")
                    .setLink("https://test.example.com/item-link-2")
                    .addEnclosure(
                        Enclosure.builder()
                            .setUrl("https://test.example.com/audio-2.ogg")
                            .setType("audio/ogg")
                            .setLength(2048)
                            .build())
                    .build())
            .build();

    Feed actual = Rome.minimal().read(toByteArray(getResource(getClass(), "test.xml")));

    assertEquals(expected, actual);
  }
}
