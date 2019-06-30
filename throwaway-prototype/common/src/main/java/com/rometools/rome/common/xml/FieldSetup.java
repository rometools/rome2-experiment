package com.rometools.rome.common.xml;

import com.rometools.rome.common.model.ModelTarget;
import java.util.function.BiFunction;

/** Temporary holder for a field that is in progress of being built by the parser. */
public class FieldSetup {

  /** Where in the model the value of the field will end up. */
  private final ModelTarget target;

  /** Where in the model the entity owning this field is located. */
  private final ModelTarget parentTarget;

  /**
   * The setter of this field on the parent entity.
   *
   * <p>Is used to put the parsed value in the result object.
   */
  private final BiFunction<Object, Object, Object> setter;

  public FieldSetup(
      ModelTarget target, ModelTarget parentTarget, BiFunction<Object, Object, Object> setter) {
    this.target = target;
    this.parentTarget = parentTarget;
    this.setter = setter;
  }

  public ModelTarget getTarget() {
    return target;
  }

  public ModelTarget getParentTarget() {
    return parentTarget;
  }

  public void set(EntitySetup entity, Object value) {
    setter.apply(entity.getBuilder(), value);
  }
}
