package com.rometools.rome.common.xml;

import com.rometools.rome.common.model.ModelTarget;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/** Temporary holder for an entity that is in progress of being built by the parser. */
public class EntitySetup {

  /** Builder of the result entity. */
  private final Object builder;

  private final ModelTarget target;
  private final ModelTarget parentTarget;
  private final Function<Object, Object> buildMethod;
  private final BiFunction<Object, Object, Object> setter;

  private EntitySetup(
      Object builder,
      ModelTarget target,
      ModelTarget parentTarget,
      Function<Object, Object> buildMethod,
      BiFunction<Object, Object, Object> setter) {
    this.builder = builder;
    this.target = target;
    this.parentTarget = parentTarget;
    this.buildMethod = buildMethod;
    this.setter = setter;
  }

  public static EntitySetup startTopLevel(
      ModelTarget target,
      Supplier<Object> builderCreator,
      Function<Object, Object> buildMethod) {
    return new EntitySetup(builderCreator.get(), target, null, buildMethod, null);
  }

  public static EntitySetup start(
      ModelTarget target,
      ModelTarget parentTarget,
      Supplier<Object> builderCreator,
      Function<Object, Object> buildMethod,
      BiFunction<Object, Object, Object> setter) {
    return new EntitySetup(builderCreator.get(), target, parentTarget, buildMethod, setter);
  }

  public ModelTarget getTarget() {
    return target;
  }

  public ModelTarget getParentTarget() {
    return parentTarget;
  }

  public Object build() {
    return buildMethod.apply(builder);
  }

  public Object getBuilder() {
    return builder;
  }

  public void set(EntitySetup parent, Object value) {
    setter.apply(parent.getBuilder(), value);
  }
}
