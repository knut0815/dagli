// AUTOGENERATED CODE.  DO NOT MODIFY DIRECTLY!  Instead, please modify the transformer/AbstractPreparedTransformerX.ftl file.
// See the README in the module's src/template directory for details.
package com.linkedin.dagli.transformer;

import com.linkedin.dagli.producer.Producer;
import com.linkedin.dagli.transformer.internal.PreparedTransformer1InternalAPI;


/**
 * Base class for prepared transformers of arity 1 that do not cache temporary data during execution and do not
 * minibatch their inputs (transformers that do so should instead derive from
 * {@link AbstractPreparedStatefulTransformer1}.)
 *
 * @param <A> the type of the first input to the transformer
 * @param <R> the type of result produced by the transformer
 * @param <S> the ultimate derived class
 */
public abstract class AbstractPreparedTransformer1<A, R, S extends AbstractPreparedTransformer1<A, R, S>> extends
    AbstractTransformer1<A, R, PreparedTransformer1InternalAPI<A, R, S>, S> implements PreparedTransformer1<A, R> {

  private static final long serialVersionUID = 1;

  public AbstractPreparedTransformer1() {
    super();
  }

  @Override
  public PreparedTransformer1InternalAPI<A, R, S> internalAPI() {
    return (PreparedTransformer1InternalAPI<A, R, S>) super.internalAPI();
  }

  @Override
  protected PreparedTransformer1InternalAPI<A, R, S> createInternalAPI() {
    return new InternalAPI();
  }

  protected class InternalAPI extends
      AbstractTransformer1<A, R, PreparedTransformer1InternalAPI<A, R, S>, S>.InternalAPI implements
      PreparedTransformer1InternalAPI<A, R, S> {
    @Override
    public R apply(Object executionCache, A value1) {
      return AbstractPreparedTransformer1.this.apply(value1);
    }
  }

  public AbstractPreparedTransformer1(Producer<? extends A> input1) {
    super(input1);
  }
}