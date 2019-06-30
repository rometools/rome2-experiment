package com.rometools.rome.factory;

import static com.google.common.collect.ImmutableSet.toImmutableSet;

import com.google.common.collect.ImmutableSet;
import com.rometools.rome.factory.generator.ModelGenerator;
import com.rometools.rome.factory.generator.ParserGenerator;
import com.rometools.rome.factory.model.Model;
import com.rometools.rome.factory.model.ModelCompiler;
import com.rometools.rome.factory.xml.XmlSchema;
import com.rometools.rome.factory.xml.schema.Atom;
import com.rometools.rome.factory.xml.schema.Rss;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

public class Generate {

  public static void main(String[] args) throws Exception {
    Path baseDirectory = Paths.get(args[0]);
    Path targetDirectory = baseDirectory.resolve("../core/src/main/java");

    ImmutableSet<XmlSchema> schemas =
        Stream.of(Rss.schemas(), Atom.schemas())
            .flatMap(Collection::stream)
            .collect(toImmutableSet());

    Set<Model> models = ModelCompiler.compile(schemas);

    for (Model model : models) {
      new ModelGenerator(model, targetDirectory).generate();
    }

    new ParserGenerator(schemas, targetDirectory)
        .generate();
  }
}
