package com.rometools.rome.model;

import com.rometools.rome.common.model.ModelId;

public class Models {

  public static final ModelId<Feed> MAIN = new ModelId<>("main", Feed.class);

  public static final ModelId<Atom.Feed> ATOM =
      new ModelId<>("atom", Atom.Feed.class);
}
