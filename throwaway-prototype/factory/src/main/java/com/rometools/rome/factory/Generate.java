package com.rometools.rome.factory;

import com.rometools.rome.factory.generator.Model;
import com.rometools.rome.factory.generator.ModelGenerator;
import com.rometools.rome.factory.generator.ModelMerger;
import com.rometools.rome.factory.generator.ParserGenerator;
import com.rometools.rome.factory.schema.Rss;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Generate {

  public static void main(String[] args) throws Exception {
    Path baseDirectory = Paths.get(args[0]);
    Path targetDirectory = baseDirectory.resolve("../core/src/main/java");

    Model model = ModelMerger.merge(Rss.model());

    new ModelGenerator(model, targetDirectory).generate();
    new ParserGenerator(Rss.model(), targetDirectory).generate();
  }
}
